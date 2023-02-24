package by.teachmeskills.homeworks.hw_03032023.part2;

public class PassengerGroundTransport extends GroundTransport {
    protected String bodyType;
    protected int passengerCapacity;

    public PassengerGroundTransport(int power, int maximumSpeed, int weight, TransportBrand brand, int wheelsAmount, double fuelConsumption, String bodyType, int passengerCapacity) {
        super(power, maximumSpeed, weight, brand, wheelsAmount, fuelConsumption);
        this.bodyType = bodyType;
        this.passengerCapacity = passengerCapacity;
    }

    private double fuelConsumed(double hoursPassed) {
        return ( hoursPassed * maximumSpeed ) / 100 * fuelConsumption;
    }

    //result[0] - kilometers passed, result[1] - fuel consumed
    public void printTripResults(double hoursPassed) {
        System.out.println("In " + hoursPassed + " hours, an " +  brand + " car, driving at a maximum speed of " + maximumSpeed +  "km/h, will drive "
                +  hoursPassed*maximumSpeed + "km and consume " + fuelConsumed(hoursPassed) + " liters of fuel");
    }

    @Override
    public String toString() {
        return "Power(horsepower): " + power + ", Power(kW): " + power*0.74 + "; Maximum speed: " + maximumSpeed + "; Weight: " + weight
                + "; Brand: " + brand + "; Wheels amount: " + wheelsAmount + "; Fuel consumption: " + fuelConsumption + "; Body type: "
                + bodyType + "; passengerCapacity: " + passengerCapacity;
    }

}
