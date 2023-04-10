package by.teachmeskills.homeworks.hw_14042023.outOfTheBoxFunctionalInterfaces.randomNumber;

import java.util.function.Supplier;

public class Run {
    public static void main(String[] args) {
        Supplier<Integer> random = () -> (int)(Math.random() * 10);
        System.out.printf("%d %d %d", random.get(), random.get(), random.get());
    }
}
