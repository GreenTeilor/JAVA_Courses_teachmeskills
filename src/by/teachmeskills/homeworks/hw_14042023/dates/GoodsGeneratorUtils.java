package by.teachmeskills.homeworks.hw_14042023.dates;

import java.time.LocalDate;

public class GoodsGeneratorUtils {
    public static String suggestGood(LocalDate date) {
        if (date.getMonthValue() < 3 || date.getMonthValue() == 12) {
            return "hats, gloves";
        } else if (date.getMonthValue() < 6) {
            return "shoes, coats";
        } else if (date.getMonthValue() < 9) {
            return "sneakers, T-shirts, sunglasses";
        } else
            return "umbrellas";
    }
}
