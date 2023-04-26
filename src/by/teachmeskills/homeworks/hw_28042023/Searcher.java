package by.teachmeskills.homeworks.hw_28042023;

import java.util.List;
import java.util.Optional;

public class Searcher {
    private Searcher() {

    }

    public static Optional<Employee> searchByName(List<Employee> employees, String name) {
        return employees.stream().filter(e -> e.getName().equals(name)).findFirst();
    }
}
