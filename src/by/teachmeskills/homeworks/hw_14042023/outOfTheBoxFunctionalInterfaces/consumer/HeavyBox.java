package by.teachmeskills.homeworks.hw_14042023.outOfTheBoxFunctionalInterfaces.consumer;

public class HeavyBox {
    private int weight;

    public HeavyBox(int weight) {
        this.weight = weight;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return weight + " kg";
    }
}
