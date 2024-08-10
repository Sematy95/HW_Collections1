package pro.sky.skyprospringdemo.service;

import pro.sky.skyprospringdemo.domain.Employee;


public interface EmployeeService {
    void addEmployee(Employee employee);

    String findEmployee(String firstName, String lastName);

    void deleteEmployee(String firstName, String lastName);

    String showAllEmployees();
}