package by.teachmeskills.homeworks.hw_14042023.dates;

import by.teachmeskills.homeworks.hw_14042023.exceptions.InvalidUserDataException;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class RegistrationUtils {

    public static class ValidatorUtils {
        private ValidatorUtils() {

        }

        public static boolean isValidName(String name) {
            return !name.isBlank();
        }

        public static boolean isValidPhoneNumber(String phoneNumber) {
            return phoneNumber.matches("^\\+375((29)|(44)|(25)|(33))[0-9]{7}$");
        }

        public static User.Sex validateSex(String sex) throws InvalidUserDataException {
            if (sex.equals("male")) {
                return User.Sex.MALE;
            } else if (sex.equals("female")) {
                return User.Sex.FEMALE;
            } else {
                throw new InvalidUserDataException("Invalid sex");
            }
        }

        public static LocalDate validateDate(String dateStr) throws InvalidUserDataException {
            SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = pattern.parse(dateStr);
                return Instant.ofEpochMilli(date.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            } catch (ParseException e) {
                throw new InvalidUserDataException("Inputted date is invalid");
            }
        }
    }

    public static User readUserData() throws InvalidUserDataException {
        String name;
        String lastName;
        String patronymic;
        String birthdayStr;
        String phoneNumber;
        String sexStr;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input name: ");
        name = scanner.nextLine();
        System.out.print("Input last name: ");
        lastName = scanner.nextLine();
        System.out.print("Input patronymic: ");
        patronymic = scanner.nextLine();
        System.out.print("Input birthday(yyyy-MM-dd): ");
        birthdayStr = scanner.nextLine();
        System.out.print("Input phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Input sex: ");
        sexStr = scanner.nextLine();

        LocalDate birthday = ValidatorUtils.validateDate(birthdayStr);
        User.Sex sex = ValidatorUtils.validateSex(sexStr);
        if (!ValidatorUtils.isValidName(name) || !ValidatorUtils.isValidName(lastName) ||
                !ValidatorUtils.isValidName(patronymic)) {
            throw new InvalidUserDataException("Invalid name/last name/patronymic");
        }
        if (!ValidatorUtils.isValidPhoneNumber(phoneNumber)) {
            throw new InvalidUserDataException("Invalid phone number");
        }
        return new User(name, lastName, patronymic, birthday, phoneNumber, sex);
    }

    public static void saveToFile(User user, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(user.toString() + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
