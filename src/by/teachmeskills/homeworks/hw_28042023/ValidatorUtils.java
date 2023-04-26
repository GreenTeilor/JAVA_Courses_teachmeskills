package by.teachmeskills.homeworks.hw_28042023;

public class ValidatorUtils {
    private ValidatorUtils() {

    }

    //Also can be applied to last name and patronymic
    public static boolean isValidName(String name) {
        return name.matches("[A-Z][a-z]*");
    }

    public static boolean isValidPosition(String position) {
        return position.matches("[A-Z][A-Za-z\\s\\-]*");
    }

    public static boolean isValidDepartment(String department) {
        return department.matches("[A-Za-z0-9\\s\\-]+");
    }

    public static boolean isValidWorkExperience(String workExperience) {
        return workExperience.matches("\\d*");
    }
}
