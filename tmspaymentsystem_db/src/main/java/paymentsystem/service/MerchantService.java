package paymentsystem.service;


import paymentsystem.bankAccount.AccountStatus;
import paymentsystem.bankAccount.BankAccount;
import paymentsystem.dbConnection.DbUtils;
import paymentsystem.exceptions.BankAccountNotFoundException;
import paymentsystem.exceptions.MerchantAlreadyHasBankAccountNumberException;
import paymentsystem.exceptions.NoBankAccountsFoundException;
import paymentsystem.dbOperations.DBOperationsUtils;
import paymentsystem.merchant.Merchant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class MerchantService {
    private List<Merchant> merchants;
    private Connection connection;

    public MerchantService(Supplier<Connection> connector) throws SQLException{
        connection = connector.get();
        if (connection == null) {
            throw new SQLException("Connection is not established");
        }
        merchants = DBOperationsUtils.readMerchantsDB(connection);
        List<BankAccount> bankAccounts = DBOperationsUtils.readBankAccountsDB(connection);
        for (Merchant merchant : merchants) {
            for (BankAccount bankAccount : bankAccounts) {
                if (merchant.getId().equals(bankAccount.getMerchantId())) {
                    merchant.addBankAccount(bankAccount);
                }
            }
        }
    }

    public BankAccount addBankAccount(Merchant merchant, BankAccount bankAccount) throws IllegalArgumentException {
        if (merchant != null) {
            merchant.addBankAccount(bankAccount);
            try {
                DBOperationsUtils.saveBankAccountDB(connection, bankAccount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no such merchant!");
        }
        return bankAccount;
    }

    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) throws NoBankAccountsFoundException {
        return merchant.getBankAccounts();
    }

    public BankAccount updateBankAccount(BankAccount bankAccount, String newBankAccountNumber) throws BankAccountNotFoundException, IllegalArgumentException,
            MerchantAlreadyHasBankAccountNumberException {
        if (bankAccount != null) {
            try {
                BankAccount accountCopy = new BankAccount(bankAccount.getMerchantId(), bankAccount.getStatus(), bankAccount.getAccountNumber(), bankAccount.getCreationDate());
                AtomicBoolean isAccountFound = new AtomicBoolean(false);
                AtomicBoolean isMerchantHasBankAccountNumber = new AtomicBoolean(false);
                if (!newBankAccountNumber.matches("\\d{8}")) {
                    throw new IllegalArgumentException("New bank account number is invalid");
                }
                merchants.forEach(m -> {
                    try {
                        Optional<BankAccount> account = m.getBankAccounts().stream().filter(a -> a.equals(bankAccount)).findFirst();
                        if (account.isPresent()) {
                            isAccountFound.set(true);
                            Optional<BankAccount> accountWithNewNumber = m.getBankAccounts().stream().filter(a -> a.getAccountNumber().equals(newBankAccountNumber)).findFirst();
                            accountWithNewNumber.ifPresentOrElse(a -> isMerchantHasBankAccountNumber.set(true), () -> bankAccount.setAccountNumber(newBankAccountNumber));
                        }
                    } catch (NoBankAccountsFoundException e) {
                        System.out.println("Merchant doesn't have searched bank account");
                    }
                });
                if (!isAccountFound.get()) {
                    throw new BankAccountNotFoundException("Bank account is not found");
                }
                if (isMerchantHasBankAccountNumber.get()) {
                    throw new MerchantAlreadyHasBankAccountNumberException("Merchant already has this bank account number");
                }
                DBOperationsUtils.updateBankAccountDB(connection, accountCopy, newBankAccountNumber);
            } catch (BankAccountNotFoundException | MerchantAlreadyHasBankAccountNumberException |
                     IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no such account!");
        }
        return bankAccount;
    }

    public boolean deleteBankAccount(BankAccount bankAccount) throws BankAccountNotFoundException {
        AtomicBoolean isAccountAlreadyDeleted = new AtomicBoolean(false);
        if (bankAccount != null) {
            try {
                AtomicBoolean isAccountFound = new AtomicBoolean(false);
                merchants.forEach(m -> {
                    try {
                        Optional<BankAccount> account = m.getBankAccounts().stream().filter(a -> a.equals(bankAccount)).findFirst();
                        if (account.isPresent()) {
                            isAccountFound.set(true);
                            if (account.get().getStatus() == AccountStatus.DELETED) {
                                isAccountAlreadyDeleted.set(true);
                            } else {
                                account.get().setStatus(AccountStatus.DELETED);
                            }
                        }
                    } catch (NoBankAccountsFoundException e) {
                        System.out.println("Merchant doesn't have searched bank account");
                    }
                });
                if (!isAccountFound.get()) {
                    throw new BankAccountNotFoundException("Bank account is not found");
                }
                if (!isAccountAlreadyDeleted.get()) {
                    DBOperationsUtils.deleteBankAccountDB(connection, bankAccount);
                }
            } catch (BankAccountNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no such account!");
        }

        return !isAccountAlreadyDeleted.get();
    }

    public Merchant createMerchant(Merchant merchant) {
        merchants.add(merchant);
        DBOperationsUtils.saveMerchantDB(connection, merchant);
        return merchant;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public Merchant getMerchantById(String id) {
        Optional<Merchant> merchant = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        return merchant.orElse(null);
    }

    public void deleteMerchant(Merchant merchant) {
        merchants.remove(merchant);
        DBOperationsUtils.deleteMerchantDB(connection, merchant);
        DBOperationsUtils.deleteMerchantBankAccountsDB(connection, merchant);
    }

    public void finishWork() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
