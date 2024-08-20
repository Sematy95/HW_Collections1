package pro.sky.skyprospringdemo.service;

import pro.sky.skyprospringdemo.domain.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee findEmployee(String firstName, String lastName);

    void deleteEmployee(String firstName, String lastName);

    Collection<Employee> showAll();

    Optional<Employee> findMinSalaryEmpDep(int department);

    Optional<Employee> findMaxSalaryEmpDep(int department);

    Collection<Employee> showAllEmployeeDep(int department);

    Map<Integer, List<Employee>> showAllEmployeeAllDep();
}