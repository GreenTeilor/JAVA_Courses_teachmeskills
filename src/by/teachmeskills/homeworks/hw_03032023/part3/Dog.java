package by.teachmeskills.homeworks.hw_03032023.part3;

public class Dog extends Canine {

    public Dog(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        super(picture, food, hunger, boundaries, location);
    }

    @Override
    protected void makeNoise() {
        System.out.println("Woof! woof!");
    }

    @Override
    protected void eat() {
        System.out.println("Hungry dog is pleasantly enjoying " + food);
    }
}
