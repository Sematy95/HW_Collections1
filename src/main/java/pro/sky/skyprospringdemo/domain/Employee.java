package pro.sky.skyprospringdemo.domain;

import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastName;
    private int department;
    private int salary;
    private static int counter;
    private int id = 0;


    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.id = ++counter;

    }

    @Override
    public String toString() {
        return
                "ФИО: " + firstName +
                        " " + lastName +
                        ", отдел: " + department +
                        ", зарплата: " + salary + " рублей" +
                        ", id сотрудника: " + id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return (Objects.equals(lastName, employee.lastName)
                && Objects.equals(firstName, employee.firstName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }
}
