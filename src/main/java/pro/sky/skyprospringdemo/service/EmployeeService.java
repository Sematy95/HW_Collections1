package pro.sky.skyprospringdemo.service;

import pro.sky.skyprospringdemo.domain.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EmployeeService {


    Employee addEmployee(String firstName, String lastName, int salary, int departmentId);

    Employee findEmployee(String firstName, String lastName);

    void deleteEmployee(String firstName, String lastName);

    Collection<Employee> showAll();


}