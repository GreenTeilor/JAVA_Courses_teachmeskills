package by.teachmeskills.homeworks.hw_05052023;

import java.util.concurrent.Callable;

public class Customer implements Callable<Integer> {
    private final String name;
    private final JewelryShop shop;

    Customer(String name, JewelryShop shop) {
        this.name = name;
        this.shop = shop;
    }

    public void goToShop() {
        System.out.println("Customer " + name + " stay in line");
        try {
            while (!shop.addCustomer(this)) {
                Thread.sleep(2000);
                System.out.println("Customer " + name + " is waiting in line");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Customer " + name + " entered the shop");
    }


    public int shopping() {
        try {
            Thread.sleep((int) (Math.random() * 8000) + 1000);
            JewelryItem item = shop.removeItem();
            System.out.println("Customer " + name + " bought " + item.getName() + " for " + item.cost());
            return item.cost();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void leaveShop() {
        shop.removeCustomer(this);
        System.out.println("Customer " + name + " leaved the shop");
    }

    @Override
    public Integer call() {
        goToShop();
        int boughtItemPrice = shopping();
        leaveShop();
        return boughtItemPrice;
    }
}
