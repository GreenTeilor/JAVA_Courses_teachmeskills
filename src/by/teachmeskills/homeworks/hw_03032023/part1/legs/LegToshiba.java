package by.teachmeskills.homeworks.hw_03032023.part1.legs;

public class LegToshiba implements ILeg {
    private int price;

    public LegToshiba(int price) {
        this.price = price;
    }

    @Override
    public void step() {
        System.out.println("Toshiba leg stepped");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
