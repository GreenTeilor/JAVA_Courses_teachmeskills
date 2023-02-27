package by.teachmeskills.homeworks.hw_03032023.part3;

public class Run {
    public static void main(String[] args) {
        Cat cat = new Cat("catPicture.png", "bird", 10, new Animal.Boundaries(4, 3), new Animal.Location(153, 456));
        Dog dog = new Dog("dogPicture.png", "pork", 8, new Animal.Boundaries(5, 4), new Animal.Location(58, 76));
        Hippo hippo = new Hippo("hippoPicture.png", "grass", 4, new Animal.Boundaries(10, 7), new Animal.Location(400, 341));
        Lion lion = new Lion("lionPicture.png", "antelope", 13, new Animal.Boundaries(8, 6), new Animal.Location(500, 12));
        Tiger tiger = new Tiger("tigerPicture.png", "boar", 9, new Animal.Boundaries(8, 7), new Animal.Location(300, 400));
        Wolf wolf = new Wolf("wolfPicture.png", "berries", 20, new Animal.Boundaries(6, 5), new Animal.Location(40, 501));
        Animal[] animals = { cat, dog, hippo, lion, tiger, wolf };
        Feline[] felines = { cat, lion, tiger };
        Canine[] canines = { dog, wolf };

        for (Animal animal : animals) {
            animal.eat();
            animal.makeNoise();
            animal.roam();
            animal.sleep();
            System.out.println("\n");
        }

        System.out.println("============================================");

        for (Feline feline : felines) {
            feline.eat();
            feline.makeNoise();
            feline.roam();
            feline.sleep();
            System.out.println("\n");
        }

        System.out.println("============================================");

        for (Canine canine : canines) {
            canine.eat();
            canine.makeNoise();
            canine.roam();
            canine.sleep();
            System.out.println("\n");
        }

    }
}
