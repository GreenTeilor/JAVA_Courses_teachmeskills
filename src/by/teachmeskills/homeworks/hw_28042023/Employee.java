package by.teachmeskills.homeworks.hw_28042023;

public class Employee {
    private String name;
    private String lastName;
    private String patronymic;
    private String position;
    private String department;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + patronymic + " " + position + " " + department + " " + workExperience;
    }
}
