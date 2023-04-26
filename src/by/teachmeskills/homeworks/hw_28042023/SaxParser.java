package by.teachmeskills.homeworks.hw_28042023;

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

public class SaxParser {
    private static List<Employee> employees;
    private static String currentElement;
    private static Employee currentEmployee;
    private static String facility;

    static {
        employees = new ArrayList<>();
        currentEmployee = new Employee();
    }

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(FilesPaths.DATA_PATH), new XMLHandler());
            System.out.println("Facility: " + facility);
            employees.forEach(System.out::println);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
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
                case "workExperience" ->
                        currentEmployee.setWorkExperience(Integer.parseInt(new String(ch, start, length)));
                default -> {
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("employee")) {
                employees.add(currentEmployee);
                currentEmployee = new Employee();
            }
            currentElement = "";
        }
    }
}
