package paymentsystem.dbOperations;

import paymentsystem.bankAccount.AccountStatus;

public class ConverterUtils {
    public static AccountStatus toAccountStatus(String status) {
        return status.equals("active") ? AccountStatus.ACTIVE : AccountStatus.DELETED;
    }
}
