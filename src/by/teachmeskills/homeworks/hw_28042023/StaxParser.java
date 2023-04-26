package by.teachmeskills.homeworks.hw_28042023;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    private static List<Employee> employeeList;
    private static Employee currentEmployee;
    private static String facility;

    static {
        employeeList = new ArrayList<>();
        currentEmployee = new Employee();
    }

    public static void main(String[] args) {
        XMLHandler.parse(FilesPaths.DATA_PATH);
        System.out.println("Facility: " + facility);
        employeeList.forEach(System.out::println);
    }

    private static class XMLHandler {

        public static void parse(String filePath) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            try {
                XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
                while (reader.hasNext()) {
                    XMLEvent xmlEvent = reader.nextEvent();
                    if (xmlEvent.isStartElement()) {
                        StartElement startElement = xmlEvent.asStartElement();
                        if (startElement.getName().getLocalPart().equals("employee")) {
                            currentEmployee = new Employee();
                        } else if (startElement.getName().getLocalPart().equals("employees")) {
                            facility = startElement.getAttributeByName(QName.valueOf("facility")).getValue();
                        } else {
                            xmlEvent = reader.nextEvent();
                            switch (startElement.getName().getLocalPart()) {
                                case "name" -> currentEmployee.setName(xmlEvent.asCharacters().getData());
                                case "lastName" -> currentEmployee.setLastName(xmlEvent.asCharacters().getData());
                                case "patronymic" -> currentEmployee.setPatronymic(xmlEvent.asCharacters().getData());
                                case "position" -> currentEmployee.setPosition(xmlEvent.asCharacters().getData());
                                case "department" -> currentEmployee.setDepartment(xmlEvent.asCharacters().getData());
                                case "workExperience" -> currentEmployee.setWorkExperience(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            }
                        }
                    }
                    if (xmlEvent.isEndElement()) {
                        EndElement endElement = xmlEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("employee")) {
                            employeeList.add(currentEmployee);
                            currentEmployee = new Employee();
                        }
                    }
                }
            } catch (XMLStreamException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
