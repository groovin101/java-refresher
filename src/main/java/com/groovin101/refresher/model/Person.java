package com.groovin101.refresher.model;

import java.util.Arrays;
import java.util.List;

public class Person {

    private String firstName;
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.age = age;
        assignLastName(lastName);
    }

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
        assignLastName(null);
    }

    private void assignLastName(String lastName) {
        this.lastName = lastName == null ? "Doe" : lastName;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s, %s", age, lastName, firstName);
    }

    /**
     * Convenience for testing
     * @return
     */
    public static List<Person> reservoirDogs() {
        return Arrays.asList(
                new Person("Mr.", "White", 68),
                new Person("Mr.", "Blonde", 49),
                new Person("Mr.", "Brown", 60),
                new Person("Mr.", "Orange", 29),
                new Person("Mr.", "Pink", 56));
    }
}
