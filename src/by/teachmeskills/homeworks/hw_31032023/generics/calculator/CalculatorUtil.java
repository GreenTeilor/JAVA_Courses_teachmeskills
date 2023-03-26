package by.teachmeskills.homeworks.hw_31032023.generics.calculator;

public class CalculatorUtil {
    private CalculatorUtil() {

    }

    public static <T extends Number, Y extends Number> Double sum(T number1, Y number2) {
        return number1.doubleValue() + number2.doubleValue();
    }

    public static <T extends Number, Y extends Number> Double difference(T number1, Y number2) {
        return number1.doubleValue() - number2.doubleValue();
    }

    public static <T extends Number, Y extends Number> Double division(T number1, Y number2) {
        return number1.doubleValue() / number2.doubleValue();
    }

    public static <T extends Number, Y extends Number> Double multiplication(T number1, Y number2) {
        return number1.doubleValue() * number2.doubleValue();
    }
}
