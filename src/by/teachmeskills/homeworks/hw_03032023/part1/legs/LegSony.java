package by.teachmeskills.homeworks.hw_03032023.part1.legs;

public class LegSony implements ILeg {
    private int price;

    public LegSony(int price) {
        this.price = price;
    }

    @Override
    public void step() {
        System.out.println("Sony leg stepped");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
