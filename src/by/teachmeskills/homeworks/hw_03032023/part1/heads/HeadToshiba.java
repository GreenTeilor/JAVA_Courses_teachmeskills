package by.teachmeskills.homeworks.hw_03032023.part1.heads;

public class HeadToshiba implements IHead {
    private int price;

    public HeadToshiba(int price) {
        this.price = price;
    }

    @Override
    public void speak() {
        System.out.println("Toshiba head speaks");
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
