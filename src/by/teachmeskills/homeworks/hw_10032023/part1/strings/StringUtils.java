package by.teachmeskills.homeworks.hw_10032023.part1.strings;

public class StringUtils {

    private StringUtils() {

    }

    public static char lastCharacter(String string) {
        return string.charAt(string.length() - 1);
    }

    public static boolean isExpressed(String string) {
        return string.endsWith("!!!");
    }

    public static boolean isBeleberda(String string) {
        return string.startsWith("Сиреневенький синхрофазатрон");
    }

    public static boolean containsString(String processedsString, String subString) {
        return processedsString.contains(subString);
    }

    public static String upCase(String string) {
        return string.toUpperCase();
    }

    public static String lowCase(String string) {
        return string.toLowerCase();
    }

    public static String doArithmetic(int operand1, int operand2, char operation) {
        StringBuilder builder = new StringBuilder();
        builder.append(operand1).append(" ").append(operation).append(" ").append(operand2).append(" = ");
        switch (operation) {
            case '+' -> builder.append(operand1 + operand2);
            case '-' -> builder.append(operand1 - operand2);
            case '*' -> builder.append(operand1 * operand2);
            case '/' -> builder.append(operand1 / operand2);
            default -> builder.append("unknown");
        }
        return String.valueOf(builder);
    }

    public static String replace(String expression) {
        StringBuilder builder = new StringBuilder(expression);
        builder.deleteCharAt(expression.indexOf("="));
        builder.insert(expression.indexOf("="), "равно");
        return String.valueOf(builder);
    }

    public static String middle(String string1, String string2) {
        String result = string1 + string2;
        return Character.toString(result.charAt(result.length() / 2 - 1)) + result.charAt(result.length() / 2);
    }

    public static String findPalindroms(String string) {
        String[] words = string.split(" |, ");
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            StringBuilder newWord = new StringBuilder(word);
            StringBuilder reversedNewWord = new StringBuilder(newWord.reverse());
            newWord.reverse();
            if (newWord.toString().equals(reversedNewWord.toString())) {
                builder.append(newWord).append(" ");
            }
        }
        return builder.toString();
    }

    public static void printSplit(String string) {
        String[] words = string.split(" ");
        for (String word : words) {
            System.out.println(word);
        }
    }

}
