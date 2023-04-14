package by.teachmeskills.homeworks.hw_21042023;

import by.teachmeskills.homeworks.hw_21042023.exceptions.BankAccountNotFoundException;
import by.teachmeskills.homeworks.hw_21042023.exceptions.MerchantAlreadyHasBankAccountNumberException;
import by.teachmeskills.homeworks.hw_21042023.exceptions.NoBankAccountsFoundException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class MerchantService {
    List<Merchant> merchants;

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
                /*
                    Empty because there is no need to do anything if we didn't find any bank accounts of this merchant
                    Purpose is to find that one account we want to update
                */
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

    public boolean deleteBankAccount(BankAccount bankAccount) throws BankAccountNotFoundException{
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
                /*
                    Empty because there is no need to do anything if we didn't find any bank accounts of this merchant
                    Purpose is to find that one account we want to delete
                */
            }
        });
        if (!isAccountFound.get()) {
            throw new BankAccountNotFoundException("Bank account is not found");
        }
        return !isAccountAlreadyDeleted.get();
    }
}
