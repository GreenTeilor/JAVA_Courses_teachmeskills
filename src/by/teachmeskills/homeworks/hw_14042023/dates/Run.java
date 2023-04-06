package by.teachmeskills.homeworks.hw_14042023.dates;

import by.teachmeskills.homeworks.hw_14042023.exceptions.InvalidUserDataException;

public class Run {
    private static final String FILEPATH = "data\\dates\\users.txt";

    public static void main(String[] args) {
        try {
            User user = RegistrationUtils.readUserData();
            RegistrationUtils.saveToFile(user, FILEPATH);
        } catch (InvalidUserDataException e) {
            System.out.println(e.getMessage());
        }
        NotificationUtils.sendNotification(FILEPATH);
    }
}
