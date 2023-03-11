package by.teachmeskills.homeworks.hw_17032023.palindromes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Run {
    private static final String directory = "D:\\files\\palindromes\\";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(directory + "input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder builder = new StringBuilder(line);
                StringBuilder builderCopy = new StringBuilder(builder);
                builder.reverse();
                if (builder.toString().equals(builderCopy.toString()))
                    writer.write(line + '\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
