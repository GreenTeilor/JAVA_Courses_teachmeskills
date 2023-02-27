package by.teachmeskills.homeworks.hw_03032023.star1;


import by.teachmeskills.homeworks.hw_03032023.part3.*;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Animal.AnimalsArray animals = new Animal.AnimalsArray(0);
        Scanner scanner = new Scanner(System.in);
        //scanner.useDelimiter(" ");
        Animal animal;
        String picture;
        String food;
        int hunger;
        Animal.Boundaries boundaries;
        Animal.Location location;
        int option;
        System.out.print("Input option(1 - add, 2 - print info, 3 - edit, 4 - delete, 5 - exit): ");
        while ((option = scanner.nextInt()) != 5) {
            switch (option) {
                case 1 -> {
                    System.out.print("Input animal characteristics(picture, food, hunger, boundaries, location): ");
                    picture = scanner.next();
                    food = scanner.next();
                    hunger = scanner.nextInt();
                    boundaries = new Animal.Boundaries(scanner.nextInt(), scanner.nextInt());
                    location = new Animal.Location(scanner.nextInt(), scanner.nextInt());
                    System.out.print("Input animal type(1 - Cat, 2 - Dog, 3 - Hippo, 4 - Lion, 5 - Tiger, 6 - Wolf): ");
                    switch (scanner.nextInt()) {
                        case 1 -> animals.add(new Cat(picture, food, hunger, boundaries, location));
                        case 2 -> animals.add(new Dog(picture, food, hunger, boundaries, location));
                        case 3 -> animals.add(new Hippo(picture, food, hunger, boundaries, location));
                        case 4 -> animals.add(new Lion(picture, food, hunger, boundaries, location));
                        case 5 -> animals.add(new Tiger(picture, food, hunger, boundaries, location));
                        case 6 -> animals.add(new Wolf(picture, food, hunger, boundaries, location));
                        default -> System.out.println("Error!");
                    }
                }
                case 2 -> {
                    System.out.print("Input picture: ");
                    if ((animal = animals.find(scanner.next())) != null)
                        animal.printInfo();
                    else
                        System.out.println("Not found!");
                }
                case 3 -> {
                    System.out.print("Input picture: ");
                    if ((animal = animals.find(scanner.next())) != null) {
                        System.out.println("Current animal characteristics: ");
                        animal.printInfo();
                        System.out.print("Input new animal characteristics(picture, food, hunger, boundaries, location): ");
                        picture = scanner.next();
                        food = scanner.next();
                        hunger = scanner.nextInt();
                        boundaries = new Animal.Boundaries(scanner.nextInt(), scanner.nextInt());
                        location = new Animal.Location(scanner.nextInt(), scanner.nextInt());
                        Animal.AnimalsArray.editAnimal(animal, picture, food, hunger, boundaries, location);
                    } else
                        System.out.println("Not found!");
                }
                case 4 -> {
                    System.out.print("Input picture: ");
                    picture = scanner.next();
                    if ((animal = animals.find(picture)) != null)
                        animals.delete(picture);
                    else
                        System.out.println("Not found!");
                }
                default -> {
                        System.out.println("There is no such option!");
                }
            }
            System.out.print("Input option(1 - add, 2 - print info, 3 - edit, 4 - delete, 5 - exit): ");
        }
    }
}
