package by.teachmeskills.homeworks.hw_10032023.part2.shop;

import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EmptyProductListException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityAlreadyExistsException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityNotFoundException;

public class Run {
    public static void main(String[] args) {
        Shop shop = new Shop();
        try {
            shop.add(new Product(48, 500, "Shoes"));
            shop.add(new Product(49, 1000, "Coat"));
            shop.add(new Product(61, 400, "Gloves"));
            shop.add(new Product(61, 1500, "Cane"));
        }
        catch (EntityAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        shop.sortInAscendingOrder();
        System.out.println(shop);
        try {
            shop.remove(100);
            shop.remove(61);
            shop.edit(48, 600, "Glowing shoes");
        }
         catch (EmptyProductListException | EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}
