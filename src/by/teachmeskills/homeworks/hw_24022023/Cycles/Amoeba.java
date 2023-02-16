package by.teachmeskills.homeworks.hw_24022023.Cycles;

/*
Одноклеточная амеба каждые 3 часа делится на 2 клетки.
Необходимо определить, сколько амеб будет через 3, 6, 9, 12,..., 24 часа.
*/

public class Amoeba {
    private static int calculateAmoebas(int initialAmount, int amoebaProductivity, int divisionDuration, int time) {
        int currentAmount = initialAmount;
        for (int timePassed = 0; timePassed < time; timePassed += divisionDuration) {
            currentAmount *= amoebaProductivity;
        }
        return currentAmount;
    }

    public static void main(String[] args) {
        for (int timePassed = 3; timePassed <= 24; timePassed += 3) {
            System.out.println(calculateAmoebas(1, 2, 3, timePassed));
        }
    }
}
