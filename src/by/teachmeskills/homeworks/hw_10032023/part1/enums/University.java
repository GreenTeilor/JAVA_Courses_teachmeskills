package by.teachmeskills.homeworks.hw_10032023.part1.enums;

public class University {
    private int number;
    private int studentsAmount;

    private Season season;

    public enum Season {
        WINTER("Зима"), SPRING("Весна"), SUMMER("Лето"), AUTUMN("Осень");

        private String russianName;

        Season(String russianName) {
            this.russianName = russianName;
        }
    }

    public University(int number, int studentsAmount, Season season) {
        this.number = number;
        this.studentsAmount = studentsAmount;
        this.season = season;
    }

    @Override
    public String toString() {
        return "Университет №" + number + ", учащихся " + studentsAmount + ", сейчас мы "  + (season == Season.SUMMER ? "отдыхаем" : "учимся");
    }
}
