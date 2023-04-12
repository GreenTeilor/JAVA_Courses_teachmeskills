package by.teachmeskills.homeworks.hw_14042023.dates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class NotificationUtils {
    public static void sendNotification(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(str -> {
                String[] words = str.split(" ");

                Function<String[], Map<String, String>> toMap = data -> {
                    Map<String, String> userData = new HashMap<>();
                    userData.put("name", data[0]);
                    userData.put("lastName", data[1]);
                    userData.put("patronymic", data[2]);
                    userData.put("birthday", data[3]);
                    userData.put("phoneNumber", data[4]);
                    userData.put("sex", data[5]);
                    return userData;
                };

                Map<String, String> userData = toMap.apply(words);
                SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = pattern.parse(userData.get("birthday"));
                    LocalDate birthday = Instant.ofEpochMilli(date.getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    birthday = birthday.plusYears(currentDate.getYear() - birthday.getYear());
                    LocalDate weekBeforeBirthday = birthday.minusDays(7);
                    LocalDate weekAfterBirthday = birthday.plusDays(7);
                    LocalDate dayBeforeExpiration = birthday.plusDays(6);

                    if (currentDate.equals(weekBeforeBirthday)) {
                        System.out.println("Dear " + userData.get("name") + " " + userData.get("lastName") + " " + userData.get("patronymic") + "! bEsTsneakers shop gifts you 15% discount on " +
                                GoodsGeneratorUtils.suggestGood(currentDate) + ".\n" +
                                "Discount is valid from " + birthday.getDayOfMonth() + " of " + birthday.getMonth() + " " + birthday.getYear() + " to " +
                                weekAfterBirthday.getDayOfMonth() + " of " + weekAfterBirthday.getMonth() + " " + weekAfterBirthday.getYear() + "\n" +
                                "We would be happy to see you in our shop!\n");
                    } else if (currentDate.equals(birthday)) {
                        System.out.println("Dear " + userData.get("name") + " " + userData.get("lastName") + " " + userData.get("patronymic") + ", happy birthday!\n" +
                                "bEsTsneakers shop gifts you 15% discount on " + GoodsGeneratorUtils.suggestGood(currentDate) + ".\n" +
                                "Discount is valid from " + birthday.getDayOfMonth() + " of " + birthday.getMonth() + " " + birthday.getYear() + " to " +
                                weekAfterBirthday.getDayOfMonth() + " of " + weekAfterBirthday.getMonth() + " " + weekAfterBirthday.getYear() + "\n" +
                                "We would be happy to see you in our shop!\n");
                    } else if (currentDate.equals(dayBeforeExpiration)) {
                        System.out.println("Dear " + userData.get("name") + " " + userData.get("lastName") + " " + userData.get("patronymic") + "!\n" +
                                "bEsTsneakers shop reminds about 15% discount on " + GoodsGeneratorUtils.suggestGood(currentDate) + ".\n" +
                                "Discount is valid from " + birthday.getDayOfMonth() + " of " + birthday.getMonth() + " " + birthday.getYear() + " to " +
                                weekAfterBirthday.getDayOfMonth() + " of " + weekAfterBirthday.getMonth() + " " + weekAfterBirthday.getYear() + "\n" +
                                "We would be happy to see you in our shop!\n");
                    }

                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
