package filesOperations;

import bankAccount.AccountStatus;
import bankAccount.BankAccount;
import merchant.Merchant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;

public class FilesOperationsUtils {

    public static void saveMerchant(File file, Merchant merchant) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(merchant.getId() + " " + merchant.getName() + " " + merchant.getCreationDate());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBankAccount(File file, BankAccount bankAccount) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(bankAccount.getMerchantId() + " " + bankAccount.getStatus() + " " +
                    bankAccount.getAccountNumber() + " " + bankAccount.getCreationDate());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Merchant> readMerchants(File file) {
        List<Merchant> merchants = new ArrayList<>();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(str -> {
                String[] words = str.split(" ");
                Function<String[], Map<String, String>> toMap = data -> {
                    Map<String, String> merchantData = new HashMap<>();
                    merchantData.put("id", data[0]);
                    merchantData.put("name", data[1]);
                    merchantData.put("createdAt", data[2]);
                    return merchantData;
                };

                Map<String, String> merchantData = toMap.apply(words);
                LocalDate createdAt = ConverterUtils.toLocalDate(merchantData.get("createdAt"));
                merchants.add(new Merchant(merchantData.get("id"), merchantData.get("name"), createdAt));
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static List<BankAccount> readBankAccounts(File file) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(str -> {
                String[] words = str.split(" ");
                Function<String[], Map<String, String>> toMap = data -> {
                    Map<String, String> bankAccountData = new HashMap<>();
                    bankAccountData.put("merchantId", data[0]);
                    bankAccountData.put("status", data[1]);
                    bankAccountData.put("accountNumber", data[2]);
                    bankAccountData.put("createdAt", data[3]);
                    return bankAccountData;
                };

                Map<String, String> bankAccountData = toMap.apply(words);
                LocalDate createdAt = ConverterUtils.toLocalDate(bankAccountData.get("createdAt"));
                AccountStatus status = ConverterUtils.toAccountStatus(bankAccountData.get("status"));
                bankAccounts.add(new BankAccount(Integer.parseInt(bankAccountData.get("merchantId")), status, bankAccountData.get("accountNumber"), createdAt));
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }
}
