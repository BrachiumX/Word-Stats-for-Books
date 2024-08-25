package org.brachium.bookwordstatistics;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Parser{

    Book book;
    String document;
    
    int currentWord = 1;
    int currentPosition = 1;

    boolean isSentenceEnd = false;

    static final HashSet<String> sentenceEnds = new HashSet<>(Arrays.asList("?", ".","!"));
    static final int pageLength = 250;

    Parser(Book book){
        this.book = book;
    }

    public void parse(BufferedReader br){

        String line = "";

        while(true){

            try {
                line = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            if(line==null){
                break;
            }
            Scanner scan = new Scanner(line);

            while(scan.hasNext()){


                String word = cleanse(scan.next());
                
                if(!word.equals("")){
                    
                  
                    boolean isUppercase = Character.isUpperCase(word.charAt(0));
    
                    word = word.toLowerCase();
                    book.addWord(word);
                    book.incFrequency(word);
    
                    if(isUppercase){
                        book.incUppercase(word);
                    }
    
                    book.addTotalPage(word, currentWord/pageLength + 1);
                    book.addTotalPosition(word, currentPosition);

                    if(book.getFirstOccurence().get(word)==null){
                        book.getFirstOccurence().put(word, currentWord);
                    }

                    book.getLastOccurence().put(word, currentWord);
    
                    currentPosition++;
                    currentWord++;
    
                    if(isSentenceEnd){
                        book.incTotalSentences();
                        currentPosition = 1;
                        isSentenceEnd = false;
                    }
                }
            }
        }

        book.setTotalWords(currentWord-1);
    }

    public String cleanse(String word){

        String beginString = word.substring(0,1);
        String endString = word.substring(word.length()-1,word.length());

        if(sentenceEnds.contains(endString)){
            isSentenceEnd = true;
        }

        beginString = beginString.replaceAll("[\\p{Punct}\\p{IsPunctuation}]", "");
        endString = endString.replaceAll("[\\p{Punct}\\p{IsPunctuation}]", "");
        
        if(beginString.equals("")){
            if(word.length()==1){
                return "";
            }
            return cleanse(word.substring(1));
        }
        if(endString.equals("")){
            if(word.length()==1){
                return "";
            }
            return cleanse(word.substring(0, word.length()-1));
        }
        return word;
    }
}