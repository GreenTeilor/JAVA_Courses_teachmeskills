import by.teachmeskills.homeworks.hw_28042023.FilesPaths;
import by.teachmeskills.homeworks.hw_28042023.ValidatorUtils;
import by.teachmeskills.homeworks.hw_28042023.exceptions.ValidationException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JaxbParser {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FilesPaths.DATA_PATH));
            String body = br.lines().collect(Collectors.joining());
            StringReader reader = new StringReader(body);
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employees employees = (Employees) unmarshaller.unmarshal(reader);
            System.out.println(employees.getFacility());
            employees.getEmployeeList().forEach(employee -> {
                try {
                    if (ValidatorUtils.isValidName(employee.getName()) && ValidatorUtils.isValidName(employee.getLastName()) &&
                            ValidatorUtils.isValidName(employee.getPatronymic()) && ValidatorUtils.isValidPosition(employee.getPosition()) &&
                            ValidatorUtils.isValidDepartment(employee.getDepartment()) && ValidatorUtils.isValidWorkExperience(employee.getWorkExperience())) {
                        System.out.println(employee);
                    } else {
                        throw new ValidationException("Validation is not passed");
                    }
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.println("Input employee name: ");
            Scanner scanner = new Scanner(System.in);
            Optional<Employee> employee = Searcher.searchByName(employees, scanner.next());
            employee.ifPresentOrElse(System.out::println, () -> System.out.println("Employee is not found"));
        } catch (FileNotFoundException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
