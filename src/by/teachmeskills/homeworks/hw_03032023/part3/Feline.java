package by.teachmeskills.homeworks.hw_03032023.part3;

public abstract class Feline extends Animal{

    public Feline(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        super(picture, food, hunger, boundaries, location);
    }

    @Override
    protected void roam() {
        System.out.println("Roaming alone...");
    }

}
