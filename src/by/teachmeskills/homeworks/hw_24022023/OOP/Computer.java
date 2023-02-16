package by.teachmeskills.homeworks.hw_24022023.OOP;

/*
Создать класс компьютер со следующими полями:
тип процессора
объем оперативной памяти
объем жесткого диска

И методами:
- метод для вывода значений всех полей
- метод включения компьютера.

При включении может произойти сбой.
При вызове метода рандомно загадывается число (0 либо 1), а Вы вводите число с клавиатуры.

Если угадали - комп включается, если нет сгорает.

Если комп сгорел, то при попытке его включить нужно выдать сообщение что ему конец.

 - метод выключения компа (аналогично включению).
*/

import java.util.Scanner;

public class Computer {

    private enum State {
        NORMAL("Успешно"), BROKEN("Ошибка");
        private final String STATE;

        State(String state) {
            this.STATE = state;
        }

        @Override
        public String toString() {
            return this.STATE;
        }
    }

    private String processorType;
    private String ramType;
    private String hardDriveType;

    private State state;

    {
        processorType = "неизвестно";
        ramType = "неизвестно";
        hardDriveType = "неизвестно";
        state = State.BROKEN;
    }

    public Computer() {

    }

    public Computer(String processorType, String ramType, String hardDriveType) {
        this.processorType = processorType;
        this.ramType = ramType;
        this.hardDriveType = hardDriveType;
        state = State.NORMAL;
    }


    public String getProcessorType() {
        return processorType;
    }

    public String getRamType() {
        return ramType;
    }

    public String getHardDriveType() {
        return hardDriveType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public void setHardDriveType(String hardDriveType) {
        this.hardDriveType = hardDriveType;
    }

    public void printHardwareInfo() {
        System.out.println("Процессор: " + getProcessorType() + "; Оперативная память: " + getRamType() + "; Жесткий диск: " + getHardDriveType());
    }

    private void powerOffOn(String stateOf) {
        if (state == State.NORMAL) {
            System.out.print("Введите число: ");
            Scanner scanner = new Scanner(System.in);
            state = scanner.nextInt() == (int) (Math.random() * 2) ? State.NORMAL : State.BROKEN;
        }
        System.out.println(stateOf + ": " + state);
    }

    public void turnOn() {
        powerOffOn("Включение");
    }

    public void turnOff() {
        powerOffOn("Выключение");
    }

    public static void main(String[] args) {
        Computer computer1 = new Computer("Intel", "Kingston technology", "Seagate");
        Computer computer2 = new Computer();
        computer1.turnOn();
        computer2.turnOn();
        computer1.printHardwareInfo();
        computer2.printHardwareInfo();
        computer1.turnOff();
        computer2.turnOff();
    }

}
