package by.teachmeskills.homeworks.hw_10032023.part1.exceptions;

public class Run {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car(300, 20000, Car.CarBrand.BMW);
        Car car3 = new Car(250, 15000, Car.CarBrand.GEELY);
        try {
            car1.start();
            car2.start();
            car3.start();
        } catch(CarDidntStartException e) {
            System.out.println(e.getMessage());
        }

    }
}
