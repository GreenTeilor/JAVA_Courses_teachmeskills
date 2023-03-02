package by.teachmeskills.homeworks.hw_03032023.part3;

public class Hippo extends Animal{

    public Hippo(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        super(picture, food, hunger, boundaries, location);
    }

    @Override
    protected void makeNoise() {
        System.out.println("Hippo! hippo!");
    }

    @Override
    protected void eat() {
        System.out.println("Hungry hippo is chewing "+ food);
    }
}
