package exceptions;

public class MerchantAlreadyHasBankAccountNumberException extends Exception {
    public MerchantAlreadyHasBankAccountNumberException(String message) {
        super(message);
    }
}
