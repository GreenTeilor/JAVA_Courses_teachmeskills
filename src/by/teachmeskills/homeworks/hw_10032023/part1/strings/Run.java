package by.teachmeskills.homeworks.hw_10032023.part1.strings;

public class Run {
    public static void main(String[] args) {
        System.out.println(StringUtils.lastCharacter("Что-нибудь"));
        System.out.println(StringUtils.isExpressed("Что-нибудь!!!"));
        System.out.println(StringUtils.isExpressed("Что-нибудь"));
        System.out.println(StringUtils.isExpressed("Я"));
        System.out.println(StringUtils.isBeleberda("Сиреневенький синхрофазатрон ха-ха-ха"));
        System.out.println(StringUtils.isBeleberda("Не Сиреневенький синхрофазатрон ха-ха-ха"));
        System.out.println(StringUtils.containsString("Не Сиреневенький синхрофазатрон ха-ха-ха", "синхрофазатрон"));
        System.out.println(StringUtils.doArithmetic(1, 2, '-'));
        System.out.println(StringUtils.upCase("Медведь ест ягоды"));
        System.out.println(StringUtils.lowCase("мЕДВЕДЬ ЕСТ ЯГОДЫ"));
        System.out.println(StringUtils.replace("3 + 2 = 5"));
        System.out.println(StringUtils.middle("Миша", "Вася"));
        System.out.println(StringUtils.findPalindroms("Око за око, зуб за зуб"));
        StringUtils.printSplit("Самое высшее в мире искусство");
    }
}
