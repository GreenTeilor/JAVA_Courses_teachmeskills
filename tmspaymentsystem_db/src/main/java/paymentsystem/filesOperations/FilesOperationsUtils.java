package paymentsystem.filesOperations;

import paymentsystem.bankAccount.AccountStatus;
import paymentsystem.bankAccount.BankAccount;
import paymentsystem.encryption.EncryptorDecryptorUtils;
import paymentsystem.merchant.Merchant;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class FilesOperationsUtils {
    private FilesOperationsUtils() {

    }

    public static void saveMerchant(Connection connection, Merchant merchant) {
        String query = "INSERT INTO merchants (id, name, created_at) Values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, merchant.getId());
            statement.setString(2, merchant.getName());
            statement.setTimestamp(3, Timestamp.valueOf(merchant.getCreationDate().atStartOfDay()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBankAccount(Connection connection, BankAccount bankAccount) {
        String querySEARCH = "SELECT * FROM bank_accounts WHERE merchant_id = ? AND account_number = ?";
        String queryUPDATE = "UPDATE bank_accounts SET status = ? WHERE merchant_id = ? AND account_number = ?";
        String queryCREATE = "INSERT INTO bank_accounts (id, merchant_id, status, account_number, created_at) Values (?, ?, ?, ?, ?)";
        Function<String, Boolean> isPresent = query -> {
            try(PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, bankAccount.getMerchantId());
                statement.setString(2, EncryptorDecryptorUtils.encrypt(bankAccount.getAccountNumber()));
                ResultSet set = statement.executeQuery();
                int size = 0;
                while (set.next()) {
                    ++size;
                }
                set.close();
                return size != 0;
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        };
        if (isPresent.apply(querySEARCH)) {
            try(PreparedStatement statement = connection.prepareStatement(queryUPDATE)) {
                statement.setString(1, "active");
                statement.setString(2, bankAccount.getMerchantId());
                statement.setString(3, EncryptorDecryptorUtils.encrypt(bankAccount.getAccountNumber()));
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try(PreparedStatement statement = connection.prepareStatement(queryCREATE)) {
                statement.setString(1, String.valueOf(UUID.randomUUID()));
                statement.setString(2, bankAccount.getMerchantId());
                statement.setString(3, bankAccount.getStatus().toString());
                statement.setString(4, EncryptorDecryptorUtils.encrypt(bankAccount.getAccountNumber()));
                statement.setTimestamp(5, Timestamp.valueOf(bankAccount.getCreationDate().atStartOfDay()));
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static List<Merchant> readMerchants(Connection connection) {
        List<Merchant> merchants = new ArrayList<>();
        String query = "SELECT * FROM merchants";
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                merchants.add(new Merchant(set.getString(1), set.getString(2), set.getTimestamp(3).toLocalDateTime().toLocalDate()));
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static List<BankAccount> readBankAccounts(Connection connection) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        String query = "SELECT * FROM bank_accounts ORDER BY status ASC, created_at ASC";
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                bankAccounts.add(new BankAccount(set.getString(2), ConverterUtils.toAccountStatus(set.getString(3)),
                        EncryptorDecryptorUtils.decrypt(set.getString(4)), set.getTimestamp(5).toLocalDateTime().toLocalDate()));
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }

    public static void deleteBankAccountsFromFile(File file, List<BankAccount> bankAccountsToDelete) {
        /*
        List<BankAccount> bankAccounts = readBankAccounts(file);
        file.delete();
        bankAccountsToDelete.forEach(toDelete -> bankAccounts.stream().filter(a -> a.getAccountNumber().equals(toDelete.getAccountNumber()) &&
                a.getMerchantId().equals(toDelete.getMerchantId())).forEach(a -> a.setStatus(AccountStatus.DELETED)));
        bankAccounts.forEach(a -> saveBankAccount(file, a));
         */
    }

    public static void deleteMerchantFromFile(File file, Merchant merchant) {
        /*
        List<Merchant> merchants = readMerchants(file);
        file.delete();
        Optional<Merchant> mer = merchants.stream().filter(m -> m.getId().equals(merchant.getId())).findFirst();
        mer.ifPresent(merchants::remove);
        merchants.forEach(m -> saveMerchant(file, m));
        */
    }

    public static void updateBankAccountInFile(Connection connection, BankAccount bankAccount, String newBankAccountNumber) {
        String query = "UPDATE bank_accounts SET account_number = ? WHERE merchant_id = ? AND account_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, EncryptorDecryptorUtils.encrypt(newBankAccountNumber));
            statement.setString(2, bankAccount.getMerchantId());
            statement.setString(3, EncryptorDecryptorUtils.encrypt(bankAccount.getAccountNumber()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
