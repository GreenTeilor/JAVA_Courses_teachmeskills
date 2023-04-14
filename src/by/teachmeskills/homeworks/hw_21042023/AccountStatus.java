package by.teachmeskills.homeworks.hw_21042023;

public enum AccountStatus {
    ACTIVE("active"), DELETED("deleted");
    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
