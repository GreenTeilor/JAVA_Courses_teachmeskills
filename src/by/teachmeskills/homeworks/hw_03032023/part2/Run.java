package by.teachmeskills.homeworks.hw_03032023.part2;

public class Run {
    public static void main(String[] args) {
        PassengerGroundTransport car = new PassengerGroundTransport(250, 300, 3000,
                Transport.TransportBrand.BMW, 4, 5, "sedan", 4);
        car.printTripResults(0.5);
        System.out.println(car + "\n\n");
        TruckGroundTransport truck = new TruckGroundTransport(400, 250, 5000,
                Transport.TransportBrand.AUDI, 4, 8, 4.5);
        truck.load(3.43);
        truck.load(5.0);
        System.out.println(truck + "\n\n");
        CitizenAirTransport civilianPlane = new CitizenAirTransport(700, 400, 20000,
                Transport.TransportBrand.AIRBUS, 60, 1000, 120, true);
        civilianPlane.load(121);
        civilianPlane.load(120);
        System.out.println(civilianPlane + "\n\n");
        MilitaryAirTransport militaryPlane = new MilitaryAirTransport(1200, 800, 5500,
                Transport.TransportBrand.BOING, 25, 800, 1, true);
        militaryPlane.shoot();
        militaryPlane.shoot();
        militaryPlane.shoot();
        militaryPlane.eject();
        System.out.println(militaryPlane);
    }
}
