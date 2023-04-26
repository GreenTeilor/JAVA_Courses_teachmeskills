package by.teachmeskills.hw_21042023.paymentsystem.exceptions;

public class NoBankAccountsFoundException extends Exception{
    public NoBankAccountsFoundException(String message) {
        super(message);
    }
}
