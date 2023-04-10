package by.teachmeskills.homeworks.hw_14042023.lambda;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        IOperationable<String> reverse = str -> new StringBuilder(str).reverse().toString();
        IOperationable<Integer> factorial = num -> {
            int base = 1;
            for (int i = 2; i < num + 1; ++i) {
                base *= i;
            }
            return base;
        };
        System.out.print("Input 1 or 2: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1) {
            System.out.print("Input string: ");
            scanner.nextLine();//To consume \n
            System.out.println(reverse.get(scanner.nextLine()));
        } else {
            System.out.print("Input number: ");
            System.out.println(factorial.get(scanner.nextInt()));
        }
    }
}
