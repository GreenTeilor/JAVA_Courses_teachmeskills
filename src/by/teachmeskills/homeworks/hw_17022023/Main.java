package by.teachmeskills.homeworks.hw_17022023;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static boolean hole(int a, int b, int r) {
        return Math.sqrt(a*a + b*b) <= 2*r;
    }

    private enum Week_day {
        MONDAY(1), TUESDAY(2),
        WEDNESDAY(3), THURSDAY(4),
        FRIDAY(5), SATURDAY(6), SUNDAY(7);

        private final String m_stringRepresentation;
        private final int m_index;

        Week_day(int index) {
            this.m_index = (index - 1) % 7 + 1;
            m_stringRepresentation = switch(this.m_index) {
                case 1 -> "monday";
                case 2 -> "tuesday";
                case 3 -> "wednesday";
                case 4 -> "thursday";
                case 5 -> "friday";
                case 6 -> "saturday";
                default -> "sunday";
            };
        }

        @Override
        public String toString() {
            return this.m_stringRepresentation;
        }

    }

    private static void week_day (int index) {
        System.out.println("Name of the day: " + Week_day.values()[(index - 1) % 7]);
    }

    private static void stars() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 5; ++j)
                System.out.print("*");
            System.out.print("\n");
        }
    }

    private static double[] vector(double[] arr) {
        double sumOfNegative = 0;
        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < 0)
                sumOfNegative += arr[i];
            if (arr[i] < arr[minIndex])
                minIndex = i;
            if (arr[i] > arr[maxIndex])
                maxIndex = i;
        }

        double product = 1;
        for (int i = Math.min(minIndex, maxIndex) + 1; i < Math.max(minIndex, maxIndex); ++i)
            product *= arr[i];

        return new double[]{sumOfNegative, product};

    }

    private static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i)
            for (int j = 0; j < arr.length - i; ++j)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    private static void oddEvenMatrix(int[][] matrix) {
        for (int[] line: matrix) {
            for (int num : line)
                if ((num & 1) == 0)
                    System.out.print("0");
                else
                    System.out.print("1");
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        System.out.print("Input a, b, r: ");
        Scanner scanner= new Scanner(System.in);
        System.out.println(hole(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));

        week_day(10);

        stars();

        System.out.println(Arrays.toString(vector(new double[]{1.0, -2.3, 4.5, 8.2})));

        int[] arr = new int[]{1, 5, -4, 8, 2, 13};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

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

        double[][] pyramid = new double[n][];
        for (int i = 0; i < pyramid.length; ++i) {
            pyramid[i] = new double[i + 1];
            for (int j = 0; j < pyramid[i].length; ++j)
                pyramid[i][j] = Math.random();
        }
        for (double[] line: pyramid) {
            for (double num : line)
                System.out.printf("%15f", num);
            System.out.println("\n");
        }
    }
}
