package by.teachmeskills.homeworks.codewars_tasks.solutions;

public class Task5 {
    public static String orderingBeers(int nbOfBeers) {
        String result = "";
        try {
            if (nbOfBeers < 0)
                throw new RuntimeException("Tak, już jesteś pijany!");
            else if (nbOfBeers > 99)
                throw new RuntimeException("Nie pociągniesz!");
            else if (nbOfBeers == 0)
                return "Woda mineralna poprosze";

            String[] digits = new String[]{"jedno", "dwa", "trzy", "cztery", "piec", "szesc", "siedem", "osiem", "dziewiec"};
            String[] compoundNumbers1 = new String[]{"jedenascie", "dwanascie", "trzynascie", "czternascie", "pietnascie",
                    "szesnascie", "siedemnascie", "osiemnascie", "dziewietnascie"};
            String[] compoundNumbers2 = new String[]{"dziesiec", "dwadziescia", "trzydziesci", "czterdziesci", "piecdziesiat",
                    "szescdziesiat", "siedemdziesiat", "osiemdziesiat", "dziewiecdziesiat"};

            if (nbOfBeers > 10 && nbOfBeers < 20)
                result += compoundNumbers1[nbOfBeers % 10 - 1] + " ";
            else
                if (nbOfBeers % 10 == 1 && nbOfBeers > 20)
                    result += compoundNumbers2[nbOfBeers / 10 - 1] + " jeden ";
                else
                    result += ((nbOfBeers / 10 == 0) ? "" : compoundNumbers2[nbOfBeers / 10 - 1] + " ") + ((nbOfBeers % 10 == 0) ? "" : digits[nbOfBeers % 10 - 1] + " ");

            if (nbOfBeers == 1)
                result += "piwo";
            else if ((nbOfBeers % 10 == 2 || nbOfBeers % 10 == 3 || nbOfBeers % 10 == 4) && !(nbOfBeers > 10 && nbOfBeers < 20))
                result += "piwa";
            else
                result += "piw";

            result += " poprosze";
            result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
