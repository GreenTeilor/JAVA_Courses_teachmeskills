package by.teachmeskills.homeworks.hw_21042023;

import by.teachmeskills.homeworks.hw_21042023.exceptions.BankAccountNotFoundException;
import by.teachmeskills.homeworks.hw_21042023.exceptions.NoBankAccountsFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Collections;

public class Merchant {
    private String id;
    private String name;
    private List<BankAccount> bankAccounts;
    private LocalDate createdAt;

    public Merchant(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.bankAccounts = new ArrayList<>();
        this.createdAt = LocalDate.now();
    }

    public BankAccount addBankAccount(BankAccount bankAccount) throws IllegalArgumentException {
        Optional<BankAccount> account = bankAccounts.stream().
                filter(a -> a.getAccountNumber().equals(bankAccount.getAccountNumber())).findFirst();
        if (bankAccount.getAccountNumber().length() != 8 || !bankAccount.getAccountNumber().matches("\\d{8}")) {
            throw new IllegalArgumentException("Illegal account number");
        }
        account.ifPresentOrElse(a -> a.setStatus(AccountStatus.ACTIVE), () -> bankAccounts.add(bankAccount));
        return bankAccount;
    }

    public List<BankAccount> getBankAccounts() throws NoBankAccountsFoundException{
        if (bankAccounts.size() == 0) {
            throw new NoBankAccountsFoundException("No accounts found");
        }
        Collections.sort(bankAccounts);
        return bankAccounts;
    }

}
