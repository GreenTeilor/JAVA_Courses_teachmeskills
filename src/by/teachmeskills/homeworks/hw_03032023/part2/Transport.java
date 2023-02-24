package by.teachmeskills.homeworks.hw_03032023.part2;

public abstract class Transport {
    protected int power;
    protected int maximumSpeed;
    protected int weight;
    protected TransportBrand brand;

    public enum TransportBrand {
        AUDI("Audi"), BMW("BMW"), BOING("Boing"), AIRBUS("Airbus"),
        SCANIA("Scania"), MAZ("MAZ"), TOYOTA("Toyota"), GEELY("Geely");
        private final String brand;
        TransportBrand(String brand) {
            this.brand = brand;
        }

        @Override
        public String toString() {
            return this.brand;
        }
    }

    public Transport(int power, int maximumSpeed, int weight, TransportBrand brand) {
        this.power = power;
        this.maximumSpeed = maximumSpeed;
        this.weight = weight;
        this.brand = brand;
    }
}
