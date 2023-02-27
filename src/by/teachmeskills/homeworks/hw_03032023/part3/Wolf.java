package by.teachmeskills.homeworks.hw_03032023.part3;

public class Wolf extends Canine {

    public Wolf(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        super(picture, food, hunger, boundaries, location);
    }

    @Override
    protected void makeNoise() {
        System.out.println("Aouuuuu");
    }

    @Override
    protected void eat() {
        System.out.println("Hungry wolf is eating "+ food);
    }
}
