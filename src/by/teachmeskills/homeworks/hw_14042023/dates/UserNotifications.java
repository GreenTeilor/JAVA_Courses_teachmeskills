package by.teachmeskills.homeworks.hw_14042023.dates;

import java.time.LocalDate;

public record UserNotifications(String name, String lastName, String patronymic, LocalDate firstNotificationDate,
                                LocalDate birthday, LocalDate lastNotificationDate, LocalDate expirationDate) {

    public void firstNotification() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(firstNotificationDate)) {
            System.out.println("Dear " + name + " " + lastName + " " + patronymic + "! bEsTsneakers shop gifts you 15% discount on " +
                    GoodsGeneratorUtils.suggestGood(currentDate) + ".\n" +
                    "Discount is valid from " + birthday.getDayOfMonth() + " of " + birthday.getMonth() + " " + birthday.getYear() + " to " +
                    expirationDate.getDayOfMonth() + " of " + expirationDate.getMonth() + " " + expirationDate.getYear() + "\n" +
                    "We would be happy to see you in our shop!\n");
        }
    }

    public void birthdayNotification() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(birthday)) {
            System.out.println("Dear " + name + " " + lastName + " " + patronymic + ", happy birthday!\n" +
                    "bEsTsneakers shop gifts you 15% discount on " + GoodsGeneratorUtils.suggestGood(currentDate) + ".\n" +
                    "Discount is valid from " + birthday.getDayOfMonth() + " of " + birthday.getMonth() + " " + birthday.getYear() + " to " +
                    expirationDate.getDayOfMonth() + " of " + expirationDate.getMonth() + " " + expirationDate.getYear() + "\n" +
                    "We would be happy to see you in our shop!\n");
        }
    }

    public void lastNotification() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(lastNotificationDate)) {
            System.out.println("Dear " + name + " " + lastName + " " + patronymic + "!\n" +
                    "bEsTsneakers shop reminds about 15% discount on " + GoodsGeneratorUtils.suggestGood(currentDate) + ".\n" +
                    "Discount is valid from " + birthday.getDayOfMonth() + " of " + birthday.getMonth() + " " + birthday.getYear() + " to " +
                    expirationDate + " of " + expirationDate + " " + expirationDate + "\n" +
                    "We would be happy to see you in our shop!\n");
        }
    }


}
