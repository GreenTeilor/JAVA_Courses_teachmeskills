package by.teachmeskills.homeworks.codewars_tasks.solutions;

public class Task4 {
    public static long[] productFib(long prod) {
        long current = 0, next = 1;
        while (prod > current * next) {
            long temp = next;
            next += current;
            current = temp;
        }
        return (prod == current * next) ? new long[]{current, next, 1} : new long[]{current, next, 0};
    }
}
