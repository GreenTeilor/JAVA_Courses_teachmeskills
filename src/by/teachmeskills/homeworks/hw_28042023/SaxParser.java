package by.teachmeskills.homeworks.hw_28042023;

import by.teachmeskills.homeworks.hw_28042023.exceptions.ValidationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SaxParser {
    private static List<Employee> employeeList;
    private static String currentElement;
    private static Employee currentEmployee;
    private static String facility;

    static {
        employeeList = new ArrayList<>();
        currentEmployee = new Employee();
    }

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(FilesPaths.DATA_PATH), new XMLHandler());
            System.out.println("Facility: " + facility);
            employeeList.forEach(System.out::println);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Input employee name: ");
        Scanner scanner = new Scanner(System.in);
        Optional<Employee> employee = Searcher.searchByName(employeeList, scanner.next());
        employee.ifPresentOrElse(System.out::println, () -> System.out.println("Employee is not found"));
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("employees")) {
                facility = attributes.getValue("facility");
            }
            currentElement = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            switch (currentElement) {
                case "name" -> currentEmployee.setName(new String(ch, start, length));
                case "lastName" -> currentEmployee.setLastName(new String(ch, start, length));
                case "patronymic" -> currentEmployee.setPatronymic(new String(ch, start, length));
                case "position" -> currentEmployee.setPosition(new String(ch, start, length));
                case "department" -> currentEmployee.setDepartment(new String(ch, start, length));
                case "workExperience" -> currentEmployee.setWorkExperience(new String(ch, start, length));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("employee")) {
                try {
                    if (ValidatorUtils.isValidName(currentEmployee.getName()) && ValidatorUtils.isValidName(currentEmployee.getLastName()) &&
                            ValidatorUtils.isValidName(currentEmployee.getPatronymic()) && ValidatorUtils.isValidPosition(currentEmployee.getPosition()) &&
                            ValidatorUtils.isValidDepartment(currentEmployee.getDepartment()) && ValidatorUtils.isValidWorkExperience(currentEmployee.getWorkExperience())) {
                        employeeList.add(currentEmployee);
                        currentEmployee = new Employee();
                    } else {
                        throw new ValidationException("Validation is not passed");
                    }
                } catch (ValidationException e) {
                    System.out.println(e.getMessage());
                }
            }
            currentElement = "";
        }
    }
}
