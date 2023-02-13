package com.example.javafxtest;


import java.util.ArrayList;
import java.util.Collections;

public class ArrayOperations {

    public static int sum(ArrayList<Integer> array) {
        int sum = 0;
        for (Integer num: array)
            sum += num;
        return sum;
    }

    public static int[] oddEven(ArrayList<Integer> array) {
        int odd = 0, even = 0;
        for (int num: array)
            if ((num & 1) == 0)
                ++even;
            else
                ++odd;
        return new int[]{even, odd};
    }

    public static int[] positiveNegative(ArrayList<Integer> array) {
        int positive = 0, negative = 0;
        for (int num: array)
            if (num >= 0)
                ++positive;
            else
                ++negative;
        return new int[]{positive, negative};
    }

    public static double average(ArrayList<Integer> array) {
        int sum = 0;
        for (int num: array)
            sum += num;
        return (double)sum / array.size();
    }

    public static int[] minMax(ArrayList<Integer> array) {
        int min = 0, max = 0;
        for (int num: array) {
            if (num > max)
                max = num;
            if (num < min)
                min = num;
        }
        return new int[]{min, max};
    }

    public static void reverse(ArrayList<Integer> array) {
        for (int i = 0; i < array.size() / 2; ++i) {
            int temp = array.get(i);
            array.set(i, array.get(array.size() - i - 1));
            array.set(array.size() - i - 1, temp);
        }
    }

    public static void sort(ArrayList<Integer> array) {
        Collections.sort(array);
    }

    public static int find(ArrayList<Integer> array, int value) {
        return array.indexOf(value);
    }
}
