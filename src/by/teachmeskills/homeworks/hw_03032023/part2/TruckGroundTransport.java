package by.teachmeskills.homeworks.hw_03032023.part2;

public class TruckGroundTransport extends GroundTransport{
    protected double loadCapacity;

    public TruckGroundTransport(int power, int maximumSpeed, int weight, TransportBrand brand, int wheelsAmount, double fuelConsumption, double loadCapacity) {
        super(power, maximumSpeed, weight, brand, wheelsAmount, fuelConsumption);
        this.loadCapacity = loadCapacity;
    }

    void load(double loadWeight) {
        System.out.println(loadWeight <= loadCapacity ? "Truck is loaded" : "You need bigger truck");
    }

    @Override
    public String toString() {
        return "Power(horsepower): " + power + ", Power(kW): " + power*0.74 + "; Maximum speed: " + maximumSpeed + "; Weight: " + weight
                + "; Brand: " + brand + "; Wheels amount: " + wheelsAmount + "; Fuel consumption: " + fuelConsumption + "; Load capacity: "
                + loadCapacity;
    }
}
