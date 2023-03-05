package by.teachmeskills.homeworks.hw_10032023.part2.star;

import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EmptyProductListException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityAlreadyExistsException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.EntityNotFoundException;
import by.teachmeskills.homeworks.hw_10032023.part2.exceptions.WrongOptionException;
import by.teachmeskills.homeworks.hw_10032023.part2.shop.Product;
import by.teachmeskills.homeworks.hw_10032023.part2.shop.Shop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationMenu {
    private Shop shop;
    Scanner scanner;

    public ApplicationMenu(Shop shop) {
        this.shop = shop;
        scanner = new Scanner(System.in);
    }

    public void run() {
        int option = 0;
        while (option != 4) {
            System.out.print("=============================================================\nInput option(1 - add, 2 - remove, 3 - edit, 4 - exit): ");
            try {
                option = scanner.nextInt();
                if (option > 0 && option < 4) {
                    try {
                        this.printProducts();
                    } catch (WrongOptionException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        scanner = new Scanner(System.in);
                        System.out.println("Not integer number!");
                        continue;
                    }
                }

                switch (option) {
                    case 1 -> {
                        this.addProduct();
                    }
                    case 2 -> {
                        this.removeProduct();
                    }
                    case 3 -> {
                        this.editProduct();
                    }
                    case 4 -> {

                    }
                    default -> {
                        System.out.println("There is no such option!");
                    }
                }
            } catch (InputMismatchException e) {
                scanner = new Scanner(System.in);
                System.out.println("Not integer number!");
            }
        }
    }

    private void addProduct() {
        System.out.print("Input product information(id, price, name): ");
        int id;
        int price;
        try {
            id = scanner.nextInt();
            price = scanner.nextInt();
            String name = scanner.next();
            try {
                shop.add(new Product(id, price, name));
            } catch (EntityAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputMismatchException e) {
            scanner = new Scanner(System.in);
            System.out.println("Not integer number!");
        }
    }

    private void removeProduct() {
        System.out.print("Input id: ");
        int id;
        try {
            id = scanner.nextInt();
            try {
                shop.remove(id);
            } catch (EmptyProductListException | EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputMismatchException e) {
            scanner = new Scanner(System.in);
            System.out.println("Not integer number!");
        }
    }

    private void editProduct() {
        System.out.print("Input id: ");
        int id, price;
        try {
            id = scanner.nextInt();
            System.out.print("Input new product information(new price, new name): ");
            price = scanner.nextInt();
            String name = scanner.next();
            try {
                shop.edit(id, price, name);
            } catch (EmptyProductListException | EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputMismatchException e) {
            scanner = new Scanner(System.in);
            System.out.println("Not integer number!");
        }
    }

    private void printProducts() throws WrongOptionException, InputMismatchException {
        System.out.print("Print products list in ascending(1) or descending order(2)?: ");
        int option = scanner.nextInt();
        if (option != 1 && option != 2) {
            throw new WrongOptionException("Only 2 options available: 1 and 2!");
        }
        if (option == 1) {
            shop.sortInAscendingOrder();
        } else {
            shop.sortInDescendingOrder();
        }
        System.out.println(shop);
    }
}
