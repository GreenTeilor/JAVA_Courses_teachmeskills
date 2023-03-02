package by.teachmeskills.homeworks.hw_03032023.part1.heads;

public class HeadSamsung implements IHead {
    private int price;

    public HeadSamsung(int price) {
        this.price = price;
    }

    @Override
    public void speak() {
        System.out.println("Samsung head speaks");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
