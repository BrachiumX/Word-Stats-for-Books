package org.brachium.bookwordstatistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Terminal{

    public static void run(File inputFile) {

        Scanner scanner = new Scanner(System.in);
        
        Book book = new Book();

        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(inputFile));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Parser parser = new Parser(book);
        parser.parse(br);

        System.out.println("Total Words: "+book.getTotalWords());
        System.out.println("Total Pages: "+book.getTotalPages());
        System.out.println();

        for(String a:book.getWords()){
            System.out.println("Word: "+a);
            if(book.getUppercase().get(a)==null){
                System.out.println("Frequency: "+book.getFrequency().get(a)+"  Uppercase: 0" + "  Lowercase: "+book.getFrequency().get(a));
            }
            else{
                System.out.println("Frequency: "+book.getFrequency().get(a)+"  Uppercase: "+book.getUppercase().get(a)+"  Lowercase: "+(book.getFrequency().get(a)-book.getUppercase().get(a)));
            }
            System.out.println("Average Page Location: "+book.getTotalPage().get(a)/book.getFrequency().get(a));
            System.out.println("Average Location in Sentence: "+book.getTotalPosition().get(a)/book.getFrequency().get(a));
            System.out.println("First Occurence of the Word: "+((book.getFirstOccurence().get(a)/Parser.pageLength)+1));
            System.out.println("Last Occurence of the Word: "+((book.getLastOccurence().get(a)/Parser.pageLength)+1));
            System.out.println();
        }
    }
}