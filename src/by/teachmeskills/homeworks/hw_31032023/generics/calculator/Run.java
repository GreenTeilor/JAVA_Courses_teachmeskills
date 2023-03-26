package by.teachmeskills.homeworks.hw_31032023.generics.calculator;

public class Run {
    public static void main(String[] args) {
        double number1 = 5.5, number2 = 6.7;
        System.out.println("Sum: " + CalculatorUtil.sum(number1, number2) + "\n");
        System.out.println("Difference: " + CalculatorUtil.difference(number1, number2) + "\n");
        System.out.println("Division: " + CalculatorUtil.division(number1, number2) + "\n");
        System.out.println("Multiplication: " + CalculatorUtil.multiplication(number1, number2) + "\n");
    }
}
