package by.teachmeskills.homeworks.hw_24022023.Cycles;

/*
Начав тренировки, спортсмен в первый день пробежал 10 км.
Каждый день он увеличивал дневную норму на 10% нормы предыдущего дня.
Напишите программу, определяющую какой суммарный путь пробежит спортсмен за 7 дней?
*/

public class Sportsman {
    private static double calculateMileage(double standard, double standardRiseRate, int duration) {
        double result = 0;
        for (int currentDay = 0; currentDay < duration; ++currentDay) {
            result += standard;
            standard *= 1 + standardRiseRate;
        }
        return result;
    }

    public static void main(String[] args) {
        double standard = 10.0, standardRiseRate = 0.1;
        int duration = 7;
        System.out.println(calculateMileage(standard, standardRiseRate, duration));
    }
}
