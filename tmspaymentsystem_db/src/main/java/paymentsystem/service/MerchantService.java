package paymentsystem.service;


import paymentsystem.bankAccount.AccountStatus;
import paymentsystem.bankAccount.BankAccount;
import paymentsystem.exceptions.BankAccountNotFoundException;
import paymentsystem.exceptions.MerchantAlreadyHasBankAccountNumberException;
import paymentsystem.exceptions.NoBankAccountsFoundException;
import paymentsystem.dbOperations.DBOperationsUtils;
import paymentsystem.merchant.Merchant;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MerchantService {
    private List<Merchant> merchants;

    public MerchantService(Connection connection) {
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
        merchant.addBankAccount(bankAccount);
        return bankAccount;
    }

    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) throws NoBankAccountsFoundException {
        return merchant.getBankAccounts();
    }

    public BankAccount updateBankAccount(BankAccount bankAccount, String newBankAccountNumber) throws BankAccountNotFoundException, IllegalArgumentException,
            MerchantAlreadyHasBankAccountNumberException {
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
        return bankAccount;
    }

    public boolean deleteBankAccount(BankAccount bankAccount) throws BankAccountNotFoundException {
        AtomicBoolean isAccountFound = new AtomicBoolean(false);
        AtomicBoolean isAccountAlreadyDeleted = new AtomicBoolean(false);
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
        return !isAccountAlreadyDeleted.get();
    }

    public Merchant createMerchant(Merchant merchant) {
        merchants.add(merchant);
        return merchant;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public Merchant getMerchantById(String id) {
        Optional<Merchant> merchant = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        return merchant.orElse(null);
    }

    public boolean deleteMerchant(String id) {
        Optional<Merchant> merchant = merchants.stream().filter(m -> m.getId().equals(id)).findFirst();
        merchant.ifPresent(m -> merchants.remove(merchant.get()));
        return merchant.isPresent();
    }
}
