package filesOperations;

import bankAccount.AccountStatus;
import bankAccount.BankAccount;
import encryption.EncryptorDecryptorUtils;
import merchant.Merchant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class FilesOperationsUtils {
    private FilesOperationsUtils() {

    }

    public static void saveMerchant(File file, Merchant merchant) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(merchant.getId() + " " + merchant.getName() + " " + merchant.getCreationDate() + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBankAccount(File file, BankAccount bankAccount) {
        List<BankAccount> bankAccounts = readBankAccounts(file);
        Optional<BankAccount> account = bankAccounts.stream().filter(a -> a.getAccountNumber().equals(bankAccount.getAccountNumber()) &&
                a.getMerchantId().equals(bankAccount.getMerchantId())).findFirst();
        account.ifPresentOrElse(a -> a.setStatus(AccountStatus.ACTIVE), () -> bankAccounts.add(bankAccount));
        file.delete();
        bankAccounts.forEach(a -> {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(a.getMerchantId() + " " + a.getStatus() + " " +
                        EncryptorDecryptorUtils.encrypt(a.getAccountNumber()) + " " + a.getCreationDate() + "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static List<Merchant> readMerchants(File file) {
        List<Merchant> merchants = new ArrayList<>();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(str -> {
                Map<String, String> merchantData = ConverterUtils.toMerchantMap(str);
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
                Map<String, String> bankAccountData = ConverterUtils.toBankAccountMap(str);
                LocalDate createdAt = ConverterUtils.toLocalDate(bankAccountData.get("createdAt"));
                AccountStatus status = ConverterUtils.toAccountStatus(bankAccountData.get("status"));
                bankAccounts.add(new BankAccount(bankAccountData.get("merchantId"), status, EncryptorDecryptorUtils.decrypt(bankAccountData.get("accountNumber")), createdAt));
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }

    public static void deleteBankAccountsFromFile(File file, List<BankAccount> bankAccountsToDelete) {
        List<BankAccount> bankAccounts = readBankAccounts(file);
        file.delete();
        bankAccountsToDelete.forEach(toDelete -> bankAccounts.stream().filter(a -> a.getAccountNumber().equals(toDelete.getAccountNumber()) &&
                a.getMerchantId().equals(toDelete.getMerchantId())).forEach(a -> a.setStatus(AccountStatus.DELETED)));
        bankAccounts.forEach(a -> saveBankAccount(file, a));
    }

    public static void deleteMerchantFromFile(File file, Merchant merchant) {
        List<Merchant> merchants = readMerchants(file);
        file.delete();
        Optional<Merchant> mer = merchants.stream().filter(m -> m.getId().equals(merchant.getId())).findFirst();
        mer.ifPresent(merchants::remove);
        merchants.forEach(m -> saveMerchant(file, m));
    }

    public static void updateBankAccountInFile(File file, BankAccount bankAccount, String newBankAccountNumber) {
        List<BankAccount> bankAccounts = readBankAccounts(file);
        file.delete();
        Optional<BankAccount> account = bankAccounts.stream().filter(a -> a.getMerchantId().equals(bankAccount.getMerchantId()) &&
                a.getAccountNumber().equals(bankAccount.getAccountNumber())).findFirst();
        account.ifPresent(a -> a.setAccountNumber(newBankAccountNumber));
        bankAccounts.forEach(a -> saveBankAccount(file, a));
    }
}
