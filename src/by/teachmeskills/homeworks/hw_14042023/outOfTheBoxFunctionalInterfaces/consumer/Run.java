package by.teachmeskills.homeworks.hw_14042023.outOfTheBoxFunctionalInterfaces.consumer;

import java.util.function.Consumer;

public class Run {
    public static void main(String[] args) {
        Consumer<HeavyBox> ship = box -> System.out.println("Shipped box with weight " + box);
        Consumer<HeavyBox> send = box -> System.out.println("Sent box with weight " + box);
        ship.andThen(send).accept(new HeavyBox(20));
    }
}
