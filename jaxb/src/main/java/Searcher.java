import java.util.Optional;

public class Searcher {
    private Searcher() {

    }

    public static Optional<Employee> searchByName(Employees employees, String name) {
        return employees.getEmployeeList().stream().filter(e -> e.getName().equals(name)).findFirst();
    }
}
