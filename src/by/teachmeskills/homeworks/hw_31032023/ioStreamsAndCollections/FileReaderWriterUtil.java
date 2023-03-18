package by.teachmeskills.homeworks.hw_31032023.ioStreamsAndCollections;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class FileReaderWriterUtil {
    private FileReaderWriterUtil() {

    }

    public static HashSet<String> readLines(File file) {
        HashSet<String> result = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void writeLines(File file, HashMap<String, ValidatorUtil.StateAndCode> list) {
        try (FileWriter writer = new FileWriter(file)) {
            list.forEach((document, stateAndCode) -> {
                try {
                    writer.write("Document: " + document + "; Validity: " + stateAndCode + "\n");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
