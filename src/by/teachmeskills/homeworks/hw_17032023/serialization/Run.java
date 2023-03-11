package by.teachmeskills.homeworks.hw_17032023.serialization;

import by.teachmeskills.homeworks.hw_10032023.part1.Car.Car;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;

public class Run {
    private static final String directory = "D:\\files\\serialization\\";

    public static void main(String[] args) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(directory + "data.dt"))) {
            File file = new File(directory + "data.dt");
            if (file.createNewFile()) {
            } else {
                file.delete();
            }
            Car car = new Car(250, 10000, Car.CarBrand.AUDI);
            writer.writeObject(car);
            System.out.printf("Цена: %d, Скорость: %d, Марка: %s\n", car.getPrice(), car.getSpeed(), car.getBrand());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(directory + "data.dt"))) {
            Car car = (Car) reader.readObject();
            System.out.printf("Цена: %d, Скорость: %d, Марка: %s\n", car.getPrice(), car.getSpeed(), car.getBrand());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
