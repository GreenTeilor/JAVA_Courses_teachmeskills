package by.teachmeskills.homeworks.hw_03032023.part3;

public class Lion extends Feline{

    public Lion(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        super(picture, food, hunger, boundaries, location);
    }

    @Override
    protected void makeNoise() {
        System.out.println("Roar!!!");
    }

    @Override
    protected void eat() {
        System.out.println("Hungry lion is hunting and eating " + food);
    }
}
