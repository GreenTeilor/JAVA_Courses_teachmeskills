package by.teachmeskills.homeworks.hw_05052023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(11);
        List<JewelryItem> items = new ArrayList<>(Arrays.asList(new JewelryItem("ring", 50), new JewelryItem("earring", 40), new JewelryItem("gold", 30),
                new JewelryItem("silver", 20), new JewelryItem("platinum", 25), new JewelryItem("copper", 5), new JewelryItem("necklace", 70),
                new JewelryItem("chain", 30), new JewelryItem("bracelet", 20), new JewelryItem("diamond", 100)));
        JewelryShop shop = new JewelryShop("7Karat", items);
        pool.execute(shop);
        for (int i = 0; i < 10; ++i) {
            Future<Integer> purchaseCost = pool.submit(new Customer("Customer " + i, shop));
        }
        Thread.sleep(25000);
        while (true) {

        }
        //pool.shutdown();
    }
}
