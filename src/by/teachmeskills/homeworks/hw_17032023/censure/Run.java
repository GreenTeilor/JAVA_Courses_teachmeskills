package by.teachmeskills.homeworks.hw_17032023.censure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        File fileToCensure = new File("src\\by\\teachmeskills\\homeworks\\hw_17032023\\censure\\input.txt");
        File fileBlackList = new File("src\\by\\teachmeskills\\homeworks\\hw_17032023\\censure\\blackList.txt");
        String result = Censurer.censure(fileToCensure, fileBlackList);
        System.out.println(result);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src\\by\\teachmeskills\\homeworks\\hw_17032023\\censure\\output.txt"));
            if (result != null) {
                writer.write(result);
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
