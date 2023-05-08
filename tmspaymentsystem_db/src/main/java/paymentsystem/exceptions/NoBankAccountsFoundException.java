package paymentsystem.exceptions;

public class NoBankAccountsFoundException extends Exception{
    public NoBankAccountsFoundException(String message) {
        super(message);
    }
}
