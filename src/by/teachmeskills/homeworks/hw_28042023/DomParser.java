package by.teachmeskills.homeworks.hw_28042023;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private static List<Employee> employeeList;
    private static String facility;
    private static Employee currentEmployee;

    static {
        employeeList = new ArrayList<>();
        currentEmployee = new Employee();
    }

    public static void main(String[] args) {
        try {
            XMLHandler.parse(FilesPaths.DATA_PATH);
            System.out.println("Facility: " + facility);
            employeeList.forEach(System.out::println);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }

    private static class XMLHandler {
        public static void parse(String filePath) throws ParserConfigurationException, IOException, SAXException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));

            NodeList employees = document.getDocumentElement().getElementsByTagName("employee");
            for (int i = 0; i < employees.getLength(); ++i) {
                if (employees.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) employees.item(i);
                    facility = element.getParentNode().getAttributes().getNamedItem("facility").getNodeValue();
                    currentEmployee.setName(getTagValue("name", element));
                    currentEmployee.setLastName(getTagValue("lastName", element));
                    currentEmployee.setPatronymic(getTagValue("patronymic", element));
                    currentEmployee.setPosition(getTagValue("position", element));
                    currentEmployee.setDepartment(getTagValue("department", element));
                    currentEmployee.setWorkExperience(Integer.parseInt(getTagValue("workExperience", element)));
                    employeeList.add(currentEmployee);
                    currentEmployee = new Employee();
                }
            }
        }

        private static String getTagValue(String tag, Element element) {
            NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        }
    }
}
