package by.teachmeskills.homeworks.hw_17032023.formatter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileSplitter {
    private FileSplitter() {

    }

    public static String[] getSentences(File file, boolean removeNextLineCharacters) {
        String[] sentences = new String[0];
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            int code;
            while ((code = reader.read()) != -1) {
                StringBuilder sentence = new StringBuilder("");
                while (code != -1 && code != '.' && code != '?' && code != '!') {
                    if (removeNextLineCharacters) {
                        if ((char) code != '\r' && (char) code != '\n') {
                            sentence.append((char) code);
                        }
                    } else
                        sentence.append((char) code);
                    code = reader.read();
                }
                if (code != -1) {
                    sentence.append((char) code);
                }
                if (!sentence.toString().equals("\r\n") && !sentence.toString().isEmpty()) {
                    String[] updatedSentences = new String[sentences.length + 1];
                    System.arraycopy(sentences, 0, updatedSentences, 0, sentences.length);
                    updatedSentences[updatedSentences.length - 1] = sentence.toString();
                    sentences = updatedSentences;
                }
            }
            return sentences;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Данные потеряны...");
                }
            }
        }
        return null;
    }
}
