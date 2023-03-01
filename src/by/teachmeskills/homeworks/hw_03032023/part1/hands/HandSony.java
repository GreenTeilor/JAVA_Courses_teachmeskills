package by.teachmeskills.homeworks.hw_03032023.part1.hands;

public class HandSony implements IHand {
    private int price;

    public HandSony(int price) {
        this.price = price;
    }

    @Override
    public void upHand() {
        System.out.println("Sony hand up");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
