package by.teachmeskills.homeworks.hw_17032023.serialization;

import by.teachmeskills.homeworks.hw_10032023.part1.Car.Car;

import java.io.*;

public class Run {
    public static void main(String[] args) {
        ObjectOutputStream writer = null;
        try {
            File file = new File("src\\by\\teachmeskills\\homeworks\\hw_17032023\\serialization\\data.dt");
            if (file.createNewFile()) {
            } else {
                file.delete();
            }
            writer = new ObjectOutputStream(new FileOutputStream("src\\by\\teachmeskills\\homeworks\\hw_17032023\\serialization\\data.dt"));
            Car car = new Car(250, 10000, Car.CarBrand.AUDI);
            writer.writeObject(car);
            System.out.printf("Цена: %d, Скорость: %d, Марка: %s\n", car.getPrice(), car.getSpeed(), car.getBrand());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Данные потеряны...");
                }
            }
        }

        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream("src\\by\\teachmeskills\\homeworks\\hw_17032023\\serialization\\data.dt"));
            Car car = (Car) reader.readObject();
            System.out.printf("Цена: %d, Скорость: %d, Марка: %s\n", car.getPrice(), car.getSpeed(), car.getBrand());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Данные потеряны...");
                }
            }
        }
    }

}
