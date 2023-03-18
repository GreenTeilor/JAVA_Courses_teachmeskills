package by.teachmeskills.homeworks.hw_31032023.ioStreamsAndCollections;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Run {
    private static final String REPORT_PATH = "data\\ioStreamsAndCollections\\report.txt";

    public static void main(String[] args) {
        String filePath;
        HashMap<String, ValidatorUtil.StateAndCode> report = new HashMap<>();
        ArrayList<String> files = new ArrayList<>();
        HashSet<String> documents = new HashSet<>();
        System.out.print("Input line: ");
        Scanner scanner = new Scanner(System.in);
        while (!(filePath = scanner.next()).equals("0")) {
            files.add(filePath);
            File file = new File(filePath);
            try {
                documents.addAll(FileReaderWriterUtil.readLines(file));
                for (String document : documents) {
                    ValidatorUtil.StateAndCode stateAndCode = ValidatorUtil.isValidDocument(document);
                    report.put(document, stateAndCode);
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Input line: ");
        }
        FileReaderWriterUtil.writeLines(new File(REPORT_PATH), report);
    }
}
