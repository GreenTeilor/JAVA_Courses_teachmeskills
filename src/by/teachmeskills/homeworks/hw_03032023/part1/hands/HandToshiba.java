package by.teachmeskills.homeworks.hw_03032023.part1.hands;

public class HandToshiba implements IHand {
    private int price;

    public HandToshiba(int price) {
        this.price = price;
    }

    public HandToshiba() {

    }

    @Override
    public void upHand() {
        System.out.println("Toshiba hand up");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
