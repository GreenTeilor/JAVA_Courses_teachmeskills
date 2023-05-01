package by.teachmeskills.homeworks.hw_05052023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Run {
    private static boolean areAllResultsReady(List<Future<Integer>> results) {
        for (Future<Integer> element : results) {
            if (!element.isDone()) {
                return false;
            }
        }
        return true;
    }

    private static int calculateTotalGain(List<Future<Integer>> results) {
        int result = 0;
        try {
            for (Future<Integer> element : results) {
                result += element.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(11);
        List<JewelryItem> items = new ArrayList<>(Arrays.asList(new JewelryItem("ring", 50), new JewelryItem("earring", 40), new JewelryItem("gold", 30),
                new JewelryItem("silver", 20), new JewelryItem("platinum", 25), new JewelryItem("copper", 5), new JewelryItem("necklace", 70),
                new JewelryItem("chain", 30), new JewelryItem("bracelet", 20), new JewelryItem("diamond", 100)));
        JewelryShop shop = new JewelryShop("7Karat", items);
        pool.execute(shop);
        List<Future<Integer>> purchasesCosts = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Future<Integer> purchaseCost = pool.submit(new Customer("Customer " + i, shop));
            purchasesCosts.add(purchaseCost);
        }
        while (!areAllResultsReady(purchasesCosts)) {
            System.out.println("Waiting for result...");
            Thread.sleep(5000);
        }
        System.out.println("Total purchased goods cost: " + calculateTotalGain(purchasesCosts));
        pool.shutdown();
    }
}
