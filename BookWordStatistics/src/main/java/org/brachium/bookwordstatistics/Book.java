package org.brachium.bookwordstatistics;

import java.util.HashMap;
import java.util.HashSet;

public class Book {

    HashSet<String> words = new HashSet<>();

    HashMap<String, Integer> frequency = new HashMap<>();
    HashMap<String, Long> totalPage = new HashMap<>();
    HashMap<String, Integer> uppercase = new HashMap<>();
    HashMap<String, Long> totalPosition = new HashMap<>();
    HashMap<String, Integer> firstOccurence = new HashMap<>();
    HashMap<String, Integer> lastOccurence = new HashMap<>();

    int totalSentences = 0;
    int totalWords = 0;
    int totalPages = 0;


    public int getTotalDistinctWords() {
        return words.size();
    }

    public void incTotalSentences() {
        this.totalSentences++;
    }

    public int getTotalSentences() {
        return totalSentences;
    }

    public HashMap<String, Integer> getFirstOccurence() {
        return this.firstOccurence;
    }

    public void setFirstOccurence(HashMap<String, Integer> firstOccurence) {
        this.firstOccurence = firstOccurence;
    }

    public HashMap<String, Integer> getLastOccurence() {
        return this.lastOccurence;
    }

    public void setLastOccurence(HashMap<String, Integer> lastOccurence) {
        this.lastOccurence = lastOccurence;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
        this.totalPages = totalWords / Parser.pageLength + 1;
    }

    public HashSet<String> getWords() {
        return words;
    }

    public void setWords(HashSet<String> words) {
        this.words = words;
    }

    public HashMap<String, Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(HashMap<String, Integer> frequency) {
        this.frequency = frequency;
    }

    public HashMap<String, Long> getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(HashMap<String, Long> totalPage) {
        this.totalPage = totalPage;
    }

    public HashMap<String, Integer> getUppercase() {
        return uppercase;
    }

    public void setUppercase(HashMap<String, Integer> uppercase) {
        this.uppercase = uppercase;
    }

    public HashMap<String, Long> getTotalPosition() {
        return totalPosition;
    }

    public void setTotalPosition(HashMap<String, Long> totalPosition) {
        this.totalPosition = totalPosition;
    }

    public void addWord(String word) {
        words.add(word);
    }

    public void incFrequency(String word) {
        if (frequency.get(word) == null) {
            frequency.put(word, 0);
        }
        frequency.put(word, frequency.get(word) + 1);
    }

    public void addTotalPage(String word, int page) {
        if (totalPage.get(word) == null) {
            totalPage.put(word, (long) 0);
        }
        totalPage.put(word, totalPage.get(word) + page);
    }

    public void incUppercase(String word) {
        if (uppercase.get(word) == null) {
            uppercase.put(word, 0);
        }
        uppercase.put(word, uppercase.get(word) + 1);
    }

    public void addTotalPosition(String word, int position) {
        if (totalPosition.get(word) == null) {
            totalPosition.put(word, (long) 0);
        }
        totalPosition.put(word, totalPosition.get(word) + position);
    }
}