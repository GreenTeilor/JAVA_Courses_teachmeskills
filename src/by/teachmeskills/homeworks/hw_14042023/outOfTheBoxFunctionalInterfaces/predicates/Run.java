package by.teachmeskills.homeworks.hw_14042023.outOfTheBoxFunctionalInterfaces.predicates;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

public class Run {
    public static void main(String[] args) {
        Predicate<String> nonNullString = Objects::nonNull;
        Predicate<String> notEmptyString = s -> !s.isEmpty();
        System.out.print("Input string: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(nonNullString.test(input));
        System.out.println(notEmptyString.test(input));
        System.out.println(nonNullString.and(notEmptyString).test(input));
    }
}
