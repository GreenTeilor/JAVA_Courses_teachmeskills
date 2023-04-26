import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "lastName", "patronymic", "position", "department", "workExperience"})
public class Employee {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "lastName")
    private String lastName;
    @XmlElement(name = "patronymic")
    private String patronymic;
    @XmlElement(name = "position")
    private String position;
    @XmlElement(name = "department")
    private String department;
    @XmlElement(name = "workExperience")
    private String workExperience;

    public Employee() {

    }

    public Employee(String name, String lastName, String patronymic, String position, String department, String workExperience) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.department = department;
        this.workExperience = workExperience;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + patronymic + " " + position + " " + department + " " + workExperience;
    }
}
