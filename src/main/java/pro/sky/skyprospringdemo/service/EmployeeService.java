package pro.sky.skyprospringdemo.service;

import pro.sky.skyprospringdemo.domain.Employee;

import java.util.Collection;


public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee findEmployee(String firstName, String lastName);

    void deleteEmployee(String firstName, String lastName);

    Collection<Employee> showAll();
}