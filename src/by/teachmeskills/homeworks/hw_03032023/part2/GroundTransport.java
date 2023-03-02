package by.teachmeskills.homeworks.hw_03032023.part2;

public abstract class GroundTransport extends Transport {
    protected int wheelsAmount;
    protected double fuelConsumption;

    public GroundTransport(int power, int maximumSpeed, int weight, TransportBrand brand, int wheelsAmount, double fuelConsumption) {
        super(power, maximumSpeed, weight, brand);
        this.wheelsAmount = wheelsAmount;
        this.fuelConsumption = fuelConsumption;
    }


}
