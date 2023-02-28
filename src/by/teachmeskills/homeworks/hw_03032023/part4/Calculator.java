package by.teachmeskills.homeworks.hw_03032023.part4;

public class Calculator {

    private double operand1;
    private double operand2;

    Calculator(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public double add()
    {
        return operand1 + operand2;
    }

    public double subtract()
    {
        return operand1 - operand2;
    }

    public double multiply()
    {
        return operand1 * operand2;
    }

    public double divide()
    {
        return operand1 / operand2;
    }

}
