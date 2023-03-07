package by.teachmeskills.homeworks.hw_17032023.palindromes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader("src\\by\\teachmeskills\\homeworks\\hw_17032023\\palindroms\\input.txt"));
            writer = new BufferedWriter(new FileWriter("src\\by\\teachmeskills\\homeworks\\hw_17032023\\palindroms\\output.txt"));
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
        } finally {
            if (reader != null && writer != null) {
                try {
                    writer.close();
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Данные потеряны...");
                }
            }
        }

    }
}
