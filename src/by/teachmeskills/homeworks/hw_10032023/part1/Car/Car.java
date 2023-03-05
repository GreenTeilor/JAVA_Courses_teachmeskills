package by.teachmeskills.homeworks.hw_10032023.part1.Car;

import by.teachmeskills.homeworks.hw_10032023.part1.exceptions.CarNotStartedException;

public class Car {
    private int speed;
    private int price;
    private CarBrand brand;

    {
        speed = 0;
        price = 0;
        brand = CarBrand.AUDI;
    }

    public enum CarBrand {
        AUDI("Audi"), BMW("BMW"), BOING("Boing"), AIRBUS("Airbus"),
        SCANIA("Scania"), MAZ("MAZ"), TOYOTA("Toyota"), GEELY("Geely");
        private final String brand;
        CarBrand(String brand) {
            this.brand = brand;
        }

        @Override
        public String toString() {
            return this.brand;
        }
    }

    public Car() {

    }

    public Car(int speed, int price, CarBrand brand) {
        this.speed = speed;
        this.price = price;
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public void start() throws CarNotStartedException {
        if (((int) ( Math.random() * 21 ) & 1) == 0) {
            throw new CarNotStartedException("Машина не была заведена!");
        }
        else {
            System.out.println("Автомобиль с маркой " + brand +  " завёлся!");
        }
    }

}
