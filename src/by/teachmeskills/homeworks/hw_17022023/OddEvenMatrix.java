package by.teachmeskills.homeworks.hw_17022023;

import java.util.Scanner;

public class OddEvenMatrix {
    private static void oddEvenMatrix(int[][] matrix) {
        for (int[] line : matrix) {
            for (int num : line)
                if ((num & 1) == 0)
                    System.out.print("0");
                else
                    System.out.print("1");
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                matrix[i][j] = (int) (Math.random() * 11);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        oddEvenMatrix(matrix);
    }
}
