package by.teachmeskills.homeworks.hw_05052023;

import java.util.ArrayList;
import java.util.List;

public class JewelryShop implements Runnable{
    private final String name;
    private final List<JewelryItem> items;
    private final List<Customer> customers = new ArrayList<>();
    private boolean isOpened = false;

    public JewelryShop(String name, List<JewelryItem> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public synchronized JewelryItem removeItem() {
        return items.remove((int)(Math.random() * items.size()));
    }

    public synchronized boolean addCustomer(Customer customer) {
        if (customers.size() < 5 && isOpened) {
            customers.add(customer);
            return true;
        }
        return false;
    }

    public synchronized void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void run() {
        while (items.size() > 0) {
            try {
                Thread.sleep(3000); //Gap to serve customers even if less than 4 left(let them in)
                if (customers.size() < 4) {
                    isOpened = false;
                    System.out.println("Break for 10 seconds");
                    Thread.sleep(10000);
                }
                isOpened = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
