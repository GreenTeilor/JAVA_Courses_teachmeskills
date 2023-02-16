package by.teachmeskills.homeworks.hw_17022023;

public class Stars {
    private static void stars() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 5; ++j)
                System.out.print("*");
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        stars();
    }
}
