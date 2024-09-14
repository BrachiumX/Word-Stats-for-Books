package org.brachium.bookwordstatistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class TextFile {
    public static void run(File inputFile, File resultFile) {

        Book book = new Book();

        BufferedReader br = null;
        PrintWriter print = null;
        try {
            br = new BufferedReader(new FileReader(inputFile));
            print = new PrintWriter(resultFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parser parser = new Parser(book);
        parser.parse(br);

        print.println("Total Words: " + book.getTotalWords());
        print.println("Total Pages: " + book.getTotalPages());
        print.println("Total Sentences: " + book.getTotalSentences());
        print.println("Total Distinct Words: " + book.getTotalDistinctWords());
        print.println();

        for (String a : book.getWords()) {
            print.println("Word: " + a);
            if (book.getUppercase().get(a) == null) {
                print.println("Frequency: " + book.getFrequency().get(a) + "  Uppercase: 0" + "  Lowercase: " + book.getFrequency().get(a));
            } else {
                print.println("Frequency: " + book.getFrequency().get(a) + "  Uppercase: " + book.getUppercase().get(a) + "  Lowercase: " + (book.getFrequency().get(a) - book.getUppercase().get(a)));
            }
            print.println("Average Page Location: " + book.getTotalPage().get(a) / book.getFrequency().get(a));
            print.println("Average Location in Sentence: " + book.getTotalPosition().get(a) / book.getFrequency().get(a));
            print.println("First Occurence of the Word: " + ((book.getFirstOccurence().get(a) / Parser.pageLength) + 1));
            print.println("Last Occurence of the Word: " + ((book.getLastOccurence().get(a) / Parser.pageLength) + 1));
            print.println();
        }
    }
}
