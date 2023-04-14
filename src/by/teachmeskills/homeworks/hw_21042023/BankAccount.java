package by.teachmeskills.homeworks.hw_21042023;

import java.time.LocalDate;

public class BankAccount implements Comparable<BankAccount> {
    private int merchantId;
    private AccountStatus status;
    private String accountNumber;
    LocalDate createdAt;

    public BankAccount(int merchantId, String accountNumber) {
        this.merchantId = merchantId;
        this.status = AccountStatus.ACTIVE;
        this.accountNumber = accountNumber;
        this.createdAt = LocalDate.now();
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public int compareTo(BankAccount bankAccount) {
        if (this.status != bankAccount.status) {
            return this.status == AccountStatus.ACTIVE ? -1 : 1;
        }
        return this.createdAt.compareTo(bankAccount.createdAt);
    }
}
