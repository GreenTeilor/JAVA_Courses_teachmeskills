package by.teachmeskills.hw_21042023.paymentsystem.merchant;


import by.teachmeskills.hw_21042023.paymentsystem.bankAccount.AccountStatus;
import by.teachmeskills.hw_21042023.paymentsystem.bankAccount.BankAccount;
import by.teachmeskills.hw_21042023.paymentsystem.exceptions.NoBankAccountsFoundException;

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

    //Constructor to add new merchant
    public Merchant(String name) {
        this.id = String.valueOf(UUID.randomUUID());
        this.name = name;
        this.bankAccounts = new ArrayList<>();
        this.createdAt = LocalDate.now();
    }

    //Constructor to read merchant info from file
    public Merchant(String id, String name, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        bankAccounts = new ArrayList<>();
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

    public List<BankAccount> getBankAccounts() throws NoBankAccountsFoundException {
        if (bankAccounts.size() == 0) {
            throw new NoBankAccountsFoundException("No accounts found");
        }
        Collections.sort(bankAccounts);
        return bankAccounts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return createdAt;
    }

    public BankAccount getAccountByNumber(String number) {
        Optional<BankAccount> account = bankAccounts.stream().filter(a -> a.getAccountNumber().equals(number)).findFirst();
        return account.orElse(null);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + bankAccounts.toString() + " " + createdAt;
    }
}
