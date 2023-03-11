package by.teachmeskills.homeworks.hw_17032023.formatter;

public class TextFormatterUtil {

    private TextFormatterUtil() {

    }

    private static boolean isPalindrome(String word) {
        StringBuilder builder = new StringBuilder(word);
        StringBuilder builderCopy = new StringBuilder(builder);
        builder.reverse();
        return builder.toString().equals(builderCopy.toString());
    }

    public static int countWords(String sentence) {
        sentence = sentence.trim();
        if (sentence.charAt(sentence.length() - 1) == '.') {
            sentence = sentence.substring(0, sentence.length() - 1);
        }
        String[] words = sentence.split(" +| *, *| *\r\n *");
        return words.length;
    }

    public static boolean containsPalindrome(String sentence) {
        sentence = sentence.trim();
        if (sentence.charAt(sentence.length() - 1) == '.') {
            sentence = sentence.substring(0, sentence.length() - 1);
        }
        String[] words = sentence.split(" +| *, *| *\r\n *");
        for (String word : words)
            if (isPalindrome(word))
                return true;
        return false;
    }
}
