package by.teachmeskills.homeworks.hw_17022023;

import java.util.Scanner;

public class Pyramid {
    private static void pyramid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер пирамиды: ");
        int n = scanner.nextInt();

        double[][] pyramid = new double[n][];
        for (int i = 0; i < pyramid.length; ++i) {
            pyramid[i] = new double[i + 1];
            for (int j = 0; j < pyramid[i].length; ++j)
                pyramid[i][j] = Math.random();
        }
        for (double[] line : pyramid) {
            for (double num : line)
                System.out.printf("%15f", num);
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        pyramid();
    }
}
