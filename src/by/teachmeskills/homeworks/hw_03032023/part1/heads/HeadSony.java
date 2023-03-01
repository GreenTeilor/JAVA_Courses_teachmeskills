package by.teachmeskills.homeworks.hw_03032023.part1.heads;

public class HeadSony implements IHead {
    private int price;

    public HeadSony(int price) {
        this.price = price;
    }

    @Override
    public void speak() {
        System.out.println("Sony head speaks");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
