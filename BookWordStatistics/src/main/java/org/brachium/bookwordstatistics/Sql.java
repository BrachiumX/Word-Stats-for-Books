package org.brachium.bookwordstatistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {

    static final String totalTrackerTableName = "Total";

    public static void run(File inputFile, String url, String tableName, String user, String password) {

        Book book = new Book();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parser parser = new Parser(book);
        parser.parse(br);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {

                StringBuilder str = new StringBuilder();

                str.append("create table ");
                str.append(tableName);
                str.append(" (WordID int NOT NULL PRIMARY KEY AUTO_INCREMENT,");
                str.append(" Word varchar(255) NOT NULL,");
                str.append(" Frequency int NOT NULL,");
                str.append(" Uppercase int NOT NULL,");
                str.append(" Lowercase int NOT NULL,");
                str.append(" FirstOccurrence int NOT NULL,");
                str.append(" LastOccurrence int NOT NULL,");
                str.append(" TotalPosition bigint NOT NULL,");
                str.append(" TotalPage bigint NOT NULL);");

                statement.executeUpdate(str.toString());

                str = new StringBuilder();

                for (String a : book.getWords()) {

                    //The inserts have to be sent one by one since some edge cases cause SQL syntax errors which would affect
                    // other rows if the insert was done in parallel.
                    str = new StringBuilder();
                    str.append("insert into ");
                    str.append(tableName);
                    str.append(" (Word,Frequency,Uppercase,Lowercase,FirstOccurrence,LastOccurrence,TotalPosition,TotalPage) ");
                    str.append(" VALUES ");
                    str.append("(\"");
                    str.append(a);
                    str.append("\",");
                    str.append(book.getFrequency().get(a));
                    str.append(",");
                    if (book.getUppercase().get(a) == null) {
                        str.append(0);
                        str.append(",");
                        str.append(book.getFrequency().get(a));
                    } else {
                        str.append(book.getUppercase().get(a));
                        str.append(",");
                        str.append(book.getFrequency().get(a) - book.getFrequency().get(a));
                    }
                    str.append(",");
                    str.append(book.getFirstOccurence().get(a));
                    str.append(",");
                    str.append(book.getLastOccurence().get(a));
                    str.append(",");
                    str.append(book.getTotalPosition().get(a));
                    str.append(",");
                    str.append(book.getTotalPage().get(a));
                    str.append(");");
                    try {
                        System.out.println(str.toString());
                        statement.executeUpdate(str.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                str = new StringBuilder();

                str.append("insert into ");
                str.append(totalTrackerTableName);
                str.append(" (Book,TotalPages,TotalWords,TotalSentences,TotalDistinctWords) VALUES ");
                str.append(" (\"");
                str.append(tableName);
                str.append("\",");
                str.append(book.getTotalPages());
                str.append(",");
                str.append(book.getTotalWords());
                str.append(",");
                str.append(book.getTotalSentences());
                str.append(",");
                str.append(book.getTotalDistinctWords());
                str.append(");");

                System.out.println(str.toString());
                statement.executeUpdate(str.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static StringBuilder restart(String tableName) {
        StringBuilder str = new StringBuilder();

        str.append("insert into ");
        str.append(tableName);
        str.append(" (Word,Frequency,Uppercase,Lowercase,FirstOccurrence,LastOccurrence,TotalPosition,TotalPage) ");
        str.append(" VALUES ");
        return str;
    }
}
