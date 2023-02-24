package by.teachmeskills.homeworks.hw_03032023.part2;

public class MilitaryAirTransport extends AirTransport{
    protected int missilesAmount;
    protected boolean hasBailoutSystem;

    public MilitaryAirTransport(int power, int maximumSpeed, int weight, TransportBrand brand, double wingSpan, int minimalRunWayLength, int missilesAmount, boolean hasBailoutSystem) {
        super(power, maximumSpeed, weight, brand, wingSpan, minimalRunWayLength);
        this.missilesAmount = missilesAmount;
        this.hasBailoutSystem = hasBailoutSystem;
    }

    void shoot() {
        if (missilesAmount > 0) {
            System.out.println("Missile is launched");
            --missilesAmount;
        }
        else
            System.out.println("No more ammo");
    }

    void eject() {
        System.out.println(hasBailoutSystem ? "Successfully ejected" : "You don't have bailout system");
    }

    @Override
    public String toString() {
        return "Power(horsepower): " + power + ", Power(kW): " + power*0.74 + "; Maximum speed: " + maximumSpeed + "; Weight: " + weight
                + "; Brand: " + brand + "; Wingspan: " + wingSpan + "; Minimal runway length: " + minimalRunWayLength + "; Rockets amount: "
                + missilesAmount + "; Has bailout system: " + hasBailoutSystem;
    }
}
