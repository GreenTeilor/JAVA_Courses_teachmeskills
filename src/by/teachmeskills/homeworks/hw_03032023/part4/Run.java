package by.teachmeskills.homeworks.hw_03032023.part4;

public class Run {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(4.5, 2.3);
        System.out.printf("Add: %f\nSubtract: %f\nMultiply: %f\nDivide: %f\n\n", calculator.add(), calculator.subtract(), calculator.multiply(), calculator.divide());

        Card card1 = new Card("1234567809873456", Card.CardType.DEBIT, "Raman", new Card.Date(12, 24), "123");

        //Valid card
        if (card1.isValid("1234567809873456", Card.CardType.DEBIT, "Raman", new Card.Date(12, 24), "123"))
            System.out.println("Card is validated");
        else
            System.out.println("Card is NOT validated");

        //Wrong card number in parameter
        if (card1.isValid("123456780", Card.CardType.DEBIT, "Raman", new Card.Date(12, 24), "123"))
            System.out.println("Card is validated");
        else
            System.out.println("Card is NOT validated");

        //Wrong card type in parameter
        if (card1.isValid("1234567809873456", Card.CardType.CREDIT, "Raman", new Card.Date(12, 24), "123"))
            System.out.println("Card is validated");
        else
            System.out.println("Card is NOT validated");

        //Wrong card owner in parameter
        if (card1.isValid("1234567809873456", Card.CardType.DEBIT, "Alexandr", new Card.Date(12, 24), "123"))
            System.out.println("Card is validated");
        else
            System.out.println("Card is NOT validated");

        //Wrong card expiration date in parameter
        if (card1.isValid("1234567809873456", Card.CardType.DEBIT, "Raman", new Card.Date(12, 25), "123"))
            System.out.println("Card is validated");
        else
            System.out.println("Card is NOT validated");

        //Wrong card cvv in parameter
        if (card1.isValid("1234567809873456", Card.CardType.DEBIT, "Raman", new Card.Date(12, 24), "321"))
            System.out.println("Card is validated");
        else
            System.out.println("Card is NOT validated");
    }
}
