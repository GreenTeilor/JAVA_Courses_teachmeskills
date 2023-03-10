package by.teachmeskills.homeworks.hw_17032023.censure;

import by.teachmeskills.homeworks.hw_17032023.formatter.FileSplitterUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

public class CensureUtil {
    private CensureUtil() {

    }

    public static String censure(File fileToCensure, File fileBlackList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileBlackList))) {
            String[] blackList = new String[0];
            String line;
            StringBuilder result = new StringBuilder("");
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] updatedBlackList = new String[blackList.length + 1];
                    System.arraycopy(blackList, 0, updatedBlackList, 0, blackList.length);
                    updatedBlackList[updatedBlackList.length - 1] = line;
                    blackList = updatedBlackList;
                }
            }
            int sentenceNumber = 0;
            int inappropriateSentencesCounter = 0;
            boolean isAppropriateSentence = true;
            String[] sentences = FileSplitterUtil.getSentences(fileToCensure, true);
            if (sentences != null) {
                for (String sentence : sentences) {
                    ++sentenceNumber;
                    String[] words = sentence.split(" +| *, *");
                    for (String word : words) {
                        if (Arrays.asList(blackList).contains(word.toLowerCase())) {
                            result.append("Предложение номер ").append(sentenceNumber).append(" : ").append(sentence).append("; Цензура не пройдена; Запрещенное слово: ").append(word).append("\r\n");
                            isAppropriateSentence = false;
                        }
                    }
                    if (!isAppropriateSentence) {
                        ++inappropriateSentencesCounter;
                        isAppropriateSentence = true;
                    }
                }
            }
            if (inappropriateSentencesCounter == 0) {
                result.append("Текст прошёл проверку!");
            } else {
                result.append("Количество предложений не прошедших проверку: ").append(inappropriateSentencesCounter).append("\r\n");
            }
            return result.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
