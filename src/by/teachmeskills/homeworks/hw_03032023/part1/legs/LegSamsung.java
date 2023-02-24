package by.teachmeskills.homeworks.hw_03032023.part1.legs;

public class LegSamsung implements ILeg {
    private int price;

    public LegSamsung(int price) {
        this.price = price;
    }

    public LegSamsung() {

    }

    @Override
    public void step() {
        System.out.println("Samsung leg stepped");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
