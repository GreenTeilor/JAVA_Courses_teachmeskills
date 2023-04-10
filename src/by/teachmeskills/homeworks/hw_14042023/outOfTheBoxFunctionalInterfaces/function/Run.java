package by.teachmeskills.homeworks.hw_14042023.outOfTheBoxFunctionalInterfaces.function;

import java.util.function.Function;

public class Run {
    public static void main(String[] args) {
        Function<Integer, String> toString = num -> {
            if (num > 0) {
                return "Positive number";
            } else if (num == 0) {
                return "Zero";
            } else {
                return "Negative number";
            }
        };
        System.out.println(toString.apply(5));
        System.out.println(toString.apply(0));
        System.out.println(toString.apply(-6));
    }
}
