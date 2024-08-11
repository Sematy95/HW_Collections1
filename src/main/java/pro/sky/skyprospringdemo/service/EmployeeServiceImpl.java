package pro.sky.skyprospringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringdemo.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringdemo.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public final int maxEmployeeNumber = 10;
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 1, 110_000),
            new Employee("Alexander", "Petrov", 1, 100_000),
            new Employee("Mikhail", "Sidorov", 3, 200_000),
            new Employee("Stepan", "Prokhorov", 4, 250_000),
            new Employee("Lidia", "Stephanova", 5, 92_000),
            new Employee("Victoria", "Gavrilova", 5, 90_000),
            new Employee("Yulia", "Karelina", 3, 220_000),
            new Employee("Mikhail", "Vasiliev", 3, 350_000),
            new Employee("Oleg", "Mokrousov", 3, 250_000),
            new Employee("Oleg", "Smagin", 3, 150_000)
    ));

    @Override
    public void addEmployee(Employee employee) {
        if (employees.size() > maxEmployeeNumber) {
            throw new EmployeeStorageIsFullException();
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException(employee.getFirstNameName(), employee.getLastNameName());
        } else {
            employees.add(employee);
        }


    }

    @Override
    public String findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstNameName().equals(firstName) & employee.getLastNameName().equals(lastName)) {
                final String employeeDescription =
                        "Employee firstname - " + employee.getFirstNameName() + ", " +
                                "Employee lastname - " + employee.getLastNameName() + ", " +
                                "department - " + employee.getDepartment() + ", " +
                                "salary - " + employee.getSalary();
                return employeeDescription;

            }
        }
        throw new EmployeeNotFoundException(firstName, lastName);

    }

    @Override
    public void deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, 1, 1);
        if (!employees.contains(employee)) throw new EmployeeNotFoundException(firstName, lastName);
        employees.remove(employee);
    }

    @Override
    public Collection<Employee> showAll() {
        return Collections.unmodifiableList(employees);
    }

}
