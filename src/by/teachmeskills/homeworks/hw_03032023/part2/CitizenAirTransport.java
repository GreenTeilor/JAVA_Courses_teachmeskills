package by.teachmeskills.homeworks.hw_03032023.part2;

public class CitizenAirTransport extends AirTransport{
    protected int passengerCapacity;
    protected boolean hasBusinessClass;

    public CitizenAirTransport(int power, int maximumSpeed, int weight, TransportBrand brand, double wingSpan, int minimalRunWayLength, int passengerCapacity, boolean hasBusinessClass) {
        super(power, maximumSpeed, weight, brand, wingSpan, minimalRunWayLength);
        this.passengerCapacity = passengerCapacity;
        this.hasBusinessClass = hasBusinessClass;
    }

    void load(int passengerAmount) {
        System.out.println(passengerAmount <= passengerCapacity ? "Plane is loaded" : "You need bigger plane");
    }

    @Override
    public String toString() {
        return "Power(horsepower): " + power + ", Power(kW): " + power*0.74 + "; Maximum speed: " + maximumSpeed + "; Weight: " + weight
                + "; Brand: " + brand + "; Wingspan: " + wingSpan + "; Minimal runway length: " + minimalRunWayLength + "; Passenger capacity: "
                + passengerCapacity + "; Has business class: " + hasBusinessClass;
    }
}
