package by.teachmeskills.homeworks.hw_03032023.part2;

public abstract class AirTransport extends Transport {
    protected double wingSpan;
    protected int minimalRunWayLength;

    public AirTransport(int power, int maximumSpeed, int weight, TransportBrand brand, double wingSpan, int minimalRunWayLength) {
        super(power, maximumSpeed, weight, brand);
        this.wingSpan = wingSpan;
        this.minimalRunWayLength = minimalRunWayLength;
    }

}
