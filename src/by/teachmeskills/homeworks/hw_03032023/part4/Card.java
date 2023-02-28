package by.teachmeskills.homeworks.hw_03032023.part4;

public class Card {
    private String number;
    private CardType type;
    private String owner;
    private Date expirationDate;
    private String cvv;

    public Card(String number, CardType type, String owner, Date expirationDate, String cvv) {
        this.number = number;
        this.type = type;
        this.owner = owner;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public boolean isValid(String number, CardType cardType, String owner, Date expirationDate, String cvv) {
        class Validator {
            private final Card card;

            public Validator(Card card) {
                this.card = card;
            }

            public boolean checkNumber() {
                return number.length() == 16 && card.number.equals(number);
            }

            public boolean checkType() {
                return cardType == card.type;
            }

            public boolean checkOwner() {
                return !owner.isBlank() && owner.equals(card.owner);
            }

            public boolean checkExpirationDate() {
                return expirationDate.month == card.expirationDate.month && expirationDate.year == card.expirationDate.year
                        && expirationDate.month > 0 && expirationDate.month < 13 && expirationDate.year > 0;
            }

            public boolean checkCvv() {
                return cvv.length() == 3 && cvv.equals(card.cvv);
            }

        }

        Validator validator = new Validator(this);
        return validator.checkNumber() && validator.checkType() && validator.checkOwner() && validator.checkExpirationDate() && validator.checkCvv();
    }


    protected enum CardType {
        DEBIT, CREDIT
    }

    protected static class Date {
        private int month;
        private int year;

        Date(int month, int year) {
            this.month = month;
            this.year = year;
        }

    }



}
