package by.teachmeskills.homeworks.hw_17022023;

import java.util.Scanner;

public class DayOfWeek {
    private enum DayOfWeekE {
        MONDAY(1), TUESDAY(2),
        WEDNESDAY(3), THURSDAY(4),
        FRIDAY(5), SATURDAY(6), SUNDAY(7);

        private final String NAME;
        private final int INDEX;

        DayOfWeekE(int index) {
            this.INDEX = (index - 1) % 7 + 1;
            NAME = switch (this.INDEX) {
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
            return this.NAME;
        }

    }

    private static void dayOfWeek(int index) {
        System.out.println("Name of the day: " + DayOfWeekE.values()[(index - 1) % 7]);
    }

    public static void main(String[] args) {
        dayOfWeek(10);
    }
}
