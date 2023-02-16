package com.example.javafxtest;

public class ArrayOperations {

    public static int sum(int[] array) {
        int sum = 0;
        for (Integer num : array)
            sum += num;
        return sum;
    }

    public static int[] oddEven(int[] array) {
        int odd = 0, even = 0;
        for (int num : array)
            if ((num & 1) == 0)
                ++even;
            else
                ++odd;
        return new int[]{even, odd};
    }

    public static int[] positiveNegative(int[] array) {
        int positive = 0, negative = 0;
        for (int num : array)
            if (num >= 0)
                ++positive;
            else
                ++negative;
        return new int[]{positive, negative};
    }

    public static double average(int[] array) {
        int sum = 0;
        for (int num : array)
            sum += num;
        return (double) sum / array.length;
    }

    public static int[] minMax(int[] array) {
        int min = 0, max = 0;
        for (int num : array) {
            if (num > max)
                max = num;
            if (num < min)
                min = num;
        }
        return new int[]{min, max};
    }

    public static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; ++i) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    private static int[] mergeSort(int[] array, int[] buffer, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middle = (leftIndex + rightIndex) / 2;
            mergeSort(array, buffer, leftIndex, middle);
            mergeSort(array, buffer, middle + 1, rightIndex);
            int current = leftIndex;

            for (int i = leftIndex, j = middle + 1; i <= middle || j <= rightIndex; ) {
                if (j > rightIndex || (i <= middle && array[i] < array[j])) {
                    buffer[current] = array[i];
                    ++i;
                }
                else {
                    buffer[current] = array[j];
                    ++j;
                }
                ++current;
            }

            if (rightIndex + 1 - leftIndex >= 0)
                System.arraycopy(buffer, leftIndex, array, leftIndex, rightIndex + 1 - leftIndex);
        }
        return array;
    }

    public static void sort(int[] array) {
        int[] buffer = new int[array.length];
        mergeSort(array, buffer, 0, array.length - 1);
    }

    public static int find(int[] array, int value) {
        int low = 0, high = array.length - 1, result = -1;
        while (low <= high) {
            int middle = low  + ((high - low) / 2);
            if (array[middle] < value) {
                low = middle + 1;
            } else if (array[middle] > value) {
                high = middle - 1;
            } else if (array[middle] == value) {
                result = middle;
                break;
            }
        }
        return result;
    }
}
