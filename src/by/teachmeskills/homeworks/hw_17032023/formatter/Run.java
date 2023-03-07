package by.teachmeskills.homeworks.hw_17032023.formatter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src\\by\\teachmeskills\\homeworks\\hw_17032023\\formatter\\output.txt"));
            String[] sentences = FileSplitter.getSentences(new File("src\\by\\teachmeskills\\homeworks\\hw_17032023\\formatter\\input.txt"), false);
            if (sentences != null) {
                for (String sentence : sentences) {
                    if (TextFormatter.countWords(sentence) > 2 && TextFormatter.countWords(sentence) < 5 || TextFormatter.containsPalindrome(sentence)) {
                        writer.write(sentence);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Данные потеряны...");
                }
            }
        }
    }
}
