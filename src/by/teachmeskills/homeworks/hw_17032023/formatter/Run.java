package by.teachmeskills.homeworks.hw_17032023.formatter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Run {
    private static final String directory = "D:\\files\\formatter\\";

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "output.txt"))) {
            String[] sentences = FileSplitterUtil.getSentences(new File(directory + "input.txt"), false);
            if (sentences != null) {
                for (String sentence : sentences) {
                    if (TextFormatterUtil.countWords(sentence) > 2 && TextFormatterUtil.countWords(sentence) < 5 || TextFormatterUtil.containsPalindrome(sentence)) {
                        writer.write(sentence);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
