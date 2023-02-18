package by.teachmeskills.homeworks.hw_24022023.Recursion;

/*
Даны два целых числа A и В (каждое в отдельной строке).
Используя рекурсивную функцию, выведите все числа от A до B включительно, в порядке возрастания,
если A < B, или в порядке убывания в противном случае (A > B).
*/

import java.util.Scanner;

public class NumbersBetween {
    private static void numbersBetween(int a, int b) {
        if (Math.abs(a - b) <= 1)
            return;
        if (a < b) {
            System.out.print(a + 1 + " ");
            numbersBetween(a + 1, b);
        } else {
            System.out.print(a - 1 + " ");
            numbersBetween(a - 1, b);
        }
    }

    public static void main(String[] args) {
        System.out.println("Введите числа A и B: ");
        Scanner scanner = new Scanner(System.in);
        numbersBetween(scanner.nextInt(), scanner.nextInt());
    }
}
