package paymentsystem.filesOperations;





import paymentsystem.bankAccount.AccountStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    public static Map<String, String> toMerchantMap(String merchantInfo) {
        String[] words = merchantInfo.split(" ");
        Function<String[], Map<String, String>> toMap = data -> {
            Map<String, String> merchantData = new HashMap<>();
            merchantData.put("id", data[0]);
            merchantData.put("name", data[1]);
            merchantData.put("createdAt", data[2]);
            return merchantData;
        };
        return toMap.apply(words);
    }

    public static Map<String, String> toBankAccountMap(String accountInfo) {
        String[] words = accountInfo.split(" ");
        Function<String[], Map<String, String>> toMap = data -> {
            Map<String, String> bankAccountData = new HashMap<>();
            bankAccountData.put("merchantId", data[0]);
            bankAccountData.put("status", data[1]);
            bankAccountData.put("accountNumber", data[2]);
            bankAccountData.put("createdAt", data[3]);
            return bankAccountData;
        };
        return toMap.apply(words);
    }
}
