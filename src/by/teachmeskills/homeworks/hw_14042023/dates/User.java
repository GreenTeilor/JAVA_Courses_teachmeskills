package by.teachmeskills.homeworks.hw_14042023.dates;

import java.time.LocalDate;

public class User {
    private String name;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private Sex sex;

    public enum Sex {
        MALE("male"), FEMALE("female");
        private final String sex;

        Sex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return sex;
        }
    }

    public User(String name, String lastName, String patronymic, LocalDate birthday, String phoneNumber, Sex sex) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + patronymic + " " + birthday + " " + phoneNumber + " " + sex;
    }

}
