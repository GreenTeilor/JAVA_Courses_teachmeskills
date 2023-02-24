package by.teachmeskills.homeworks.hw_03032023.part1.hands;

public class HandSamsung implements IHand {
    private int price;

    public HandSamsung(int price) {
        this.price = price;
    }

    public HandSamsung() {

    }

    @Override
    public void upHand() {
        System.out.println("Samsung hand up");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
