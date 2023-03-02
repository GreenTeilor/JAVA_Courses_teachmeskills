package by.teachmeskills.homeworks.hw_10032023.part2.star;

import by.teachmeskills.homeworks.hw_10032023.part2.shop.Shop;

public class Run {
    public static void main(String[] args) {
        Shop shop = new Shop();
        ApplicationMenu applicationMenu = new ApplicationMenu(shop);
        applicationMenu.run();
    }
}
