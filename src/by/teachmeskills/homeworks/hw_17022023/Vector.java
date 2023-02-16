package by.teachmeskills.homeworks.hw_17022023;

import java.util.Arrays;
import java.util.Scanner;

public class Vector {
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
        boolean isIterationHappened = minIndex + 1 < maxIndex;
        for (int i = minIndex + 1; i < maxIndex; ++i)
            product *= arr[i];

        return isIterationHappened ? new double[]{sumOfNegative, product} : new double[]{sumOfNegative};

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input size of array: ");
        double[] array = new double[scanner.nextInt()];
        System.out.print("Input array: ");
        for (int i = 0; i < array.length; ++i)
            array[i] = scanner.nextDouble();
        System.out.println(Arrays.toString(vector(array)));
    }
}
