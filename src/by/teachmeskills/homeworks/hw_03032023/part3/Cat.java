package by.teachmeskills.homeworks.hw_03032023.part3;

public class Cat extends Feline{

    public Cat(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        super(picture, food, hunger, boundaries, location);
    }

    @Override
    protected void makeNoise() {
        System.out.println("Meow");
    }

    @Override
    protected void eat() {
        System.out.println("Hungry cat is eating " + food);
    }
}
