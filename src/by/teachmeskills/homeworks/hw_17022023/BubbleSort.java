package by.teachmeskills.homeworks.hw_17022023;

import java.util.Arrays;

public class BubbleSort {
    private static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i)
            for (int j = 0; j < arr.length - i; ++j)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, -4, 8, 2, 13};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
