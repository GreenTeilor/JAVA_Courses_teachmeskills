package by.teachmeskills.homeworks.hw_31032023.generics.arrayListImplementation;

import java.util.Arrays;

public class Run {
    public static void main(String[] args) {
        ArrayListImplementation<String> list = new ArrayListImplementation<>(Arrays.asList("1", "2"));
        list.add(0, "Some");
        list.add(1, "new");
        list.add(2, "info");
        System.out.println(list);
        list.add(0, "begin");
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(list.get(2));
        System.out.println(list.contains("begin"));
        System.out.println(list.contains(new String("begin")));
        System.out.println(list.contains(null));
        list.add(0, null);
        System.out.println(list.contains(null));
        list.clear();
        System.out.println(list);
    }
}
