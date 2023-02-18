package by.teachmeskills.homeworks.hw_24022023.Recursion;

/*
Дан массив из 10 положительных элементов.
Используя рекурсивную функцию, найти сумму элементов массива.
*/

import java.util.Scanner;

public class ArrayElementsSum {
    private static int sum(int[] array, int index) {
        return array[index] + (index == array.length - 1 ? 0 : sum(array, index + 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int[] array = new int[scanner.nextInt()];
        System.out.print("Введите элементы массива: ");
        for (int i = 0; i < array.length; ++i)
            array[i] = scanner.nextInt();
        System.out.println(sum(array, 0));
    }
}
