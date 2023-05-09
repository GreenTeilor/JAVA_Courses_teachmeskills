package paymentsystem.dbOperations;

import paymentsystem.bankAccount.BankAccount;
import paymentsystem.encryption.EncryptorDecryptorUtils;
import paymentsystem.merchant.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class DBOperationsUtils {
    private final static String ADD_QUERY = "INSERT INTO merchants (id, name, created_at) Values (?, ?, ?)";
    private final static String SEARCH_ACCOUNT_QUERY = "SELECT * FROM bank_accounts WHERE merchant_id = ? AND account_number = ?";
    private final static String UPDATE_STATUS_QUERY = "UPDATE bank_accounts SET status = ? WHERE merchant_id = ? AND account_number = ?";
    private final static String CREATE_QUERY = "INSERT INTO bank_accounts (id, merchant_id, status, account_number, created_at) Values (?, ?, ?, ?, ?)";
    private final static String GET_MERCHANTS_QUERY = "SELECT * FROM merchants";
    private final static String GET_ACCOUNTS_QUERY = "SELECT * FROM bank_accounts ORDER BY status ASC, created_at ASC";

    private final static String UPDATE_ACCOUNT_NUMBER_QUERY = "UPDATE bank_accounts SET account_number = ? WHERE merchant_id = ? AND account_number = ?";
    private final static String DELETE_ACCOUNTS_QUERY = "DELETE FROM bank_accounts WHERE merchant_id = ?";
    private final static String DELETE_MERCHANT_QUERY = "DELETE FROM merchants WHERE id = ?";

    private DBOperationsUtils() {

    }

    public static void saveMerchantDB(Connection connection, Merchant merchant) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_QUERY)){
            statement.setString(1, merchant.getId());
            statement.setString(2, merchant.getName());
            statement.setTimestamp(3, Timestamp.valueOf(merchant.getCreationDate().atStartOfDay()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBankAccountDB(Connection connection, BankAccount bankAccount) {
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
        if (isPresent.apply(SEARCH_ACCOUNT_QUERY)) {
            try(PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {
                statement.setString(1, "active");
                statement.setString(2, bankAccount.getMerchantId());
                statement.setString(3, EncryptorDecryptorUtils.encrypt(bankAccount.getAccountNumber()));
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try(PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
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

    public static List<Merchant> readMerchantsDB(Connection connection) {
        List<Merchant> merchants = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(GET_MERCHANTS_QUERY);
            while (set.next()) {
                merchants.add(new Merchant(set.getString(1), set.getString(2), set.getTimestamp(3).toLocalDateTime().toLocalDate()));
            }
            set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static List<BankAccount> readBankAccountsDB(Connection connection) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(GET_ACCOUNTS_QUERY);
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

    public static void deleteBankAccountDB(Connection connection, BankAccount account) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {
            statement.setString(1, "deleted");
            statement.setString(2, account.getMerchantId());
            statement.setString(3, EncryptorDecryptorUtils.encrypt(account.getAccountNumber()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteMerchantBankAccountsDB(Connection connection, Merchant merchant) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNTS_QUERY)) {
            statement.setString(1, merchant.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMerchantDB(Connection connection, Merchant merchant) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MERCHANT_QUERY)) {
            statement.setString(1, merchant.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBankAccountDB(Connection connection, BankAccount bankAccount, String newBankAccountNumber) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_NUMBER_QUERY)) {
            statement.setString(1, EncryptorDecryptorUtils.encrypt(newBankAccountNumber));
            statement.setString(2, bankAccount.getMerchantId());
            statement.setString(3, EncryptorDecryptorUtils.encrypt(bankAccount.getAccountNumber()));
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
