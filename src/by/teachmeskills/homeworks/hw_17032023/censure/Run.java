package by.teachmeskills.homeworks.hw_17032023.censure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Run {
    private static final String directory = "D:\\files\\censure\\";

    public static void main(String[] args) {
        File fileToCensure = new File(directory + "input.txt");
        File fileBlackList = new File(directory + "blackList.txt");
        String result = CensureUtil.censure(fileToCensure, fileBlackList);
        System.out.println(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "output.txt"))) {
            if (result != null) {
                writer.write(result);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
