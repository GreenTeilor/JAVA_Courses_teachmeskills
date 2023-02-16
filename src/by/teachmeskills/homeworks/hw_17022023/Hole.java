package by.teachmeskills.homeworks.hw_17022023;

import java.util.Scanner;

public class Hole {
    private static boolean hole(int a, int b, int r) {
        return Math.sqrt(a * a + b * b) <= 2 * r;
    }

    public static void main(String[] args) {
        System.out.print("Input a, b, r: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(hole(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
    }

}
