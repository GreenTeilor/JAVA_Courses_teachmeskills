package filesOperations;

import bankAccount.AccountStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConverterUtils {
    public static LocalDate toLocalDate(String dateString) {
        LocalDate localDate = null;
        try {
            SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
            Date date = pattern.parse(dateString);
            localDate = Instant.ofEpochMilli(date.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return localDate;
    }

    public static AccountStatus toAccountStatus(String status) {
        return status.equals("active") ? AccountStatus.ACTIVE : AccountStatus.DELETED;
    }
}
