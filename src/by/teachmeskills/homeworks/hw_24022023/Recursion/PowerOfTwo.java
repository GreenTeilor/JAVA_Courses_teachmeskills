package by.teachmeskills.homeworks.hw_24022023.Recursion;

/*
Дано натуральное число N. Выведите слово YES, если число N является точной степенью двойки, или слово NO в противном случае.
Операцией возведения в степень пользоваться нельзя!
*/

import java.util.Scanner;

public class PowerOfTwo {
    private static boolean isPowerOfTwo(int num) {
        if (num == 1)
            return true;
        return (num & 1) == 0 && isPowerOfTwo(num / 2);
    }

    public static void main(String[] args) {
        System.out.print("Input integer number: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println(isPowerOfTwo(scanner.nextInt()) ? "YES" : "NO");
    }
}
