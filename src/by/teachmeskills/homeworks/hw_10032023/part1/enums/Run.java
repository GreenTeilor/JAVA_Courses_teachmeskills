package by.teachmeskills.homeworks.hw_10032023.part1.enums;

public class Run {
    public static void main(String[] args) {
        Alphabet alphabet = Alphabet.C;
        System.out.println(alphabet.getLetterPosition());

        University university1 = new University(56, 450, University.Season.SPRING);
        University university2 = new University(56, 450, University.Season.SUMMER);
        System.out.println(university1);
        System.out.println(university2);
    }
}
