package by.teachmeskills.homeworks.codewars_tasks.solutions;

public class Task3 {
    public static int sequence(int[] arr) {
        int result = 0, sum = 0, min_sum = 0;
        for (int num : arr) {
            sum += num;
            result = Math.max(result, sum - min_sum);
            min_sum = Math.min(min_sum, sum);
        }
        return result;
    }
}
