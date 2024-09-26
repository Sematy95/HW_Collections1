package pro.sky.skyprospringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringdemo.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringdemo.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyprospringdemo.exceptions.InvalidInputException;
import pro.sky.skyprospringdemo.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int departmentId) {
        Employee employee = new Employee(
                firstName,
                lastName,
                departmentId,
                salary
        );
        validateInput(firstName, lastName);
        if (employeeRepository.getEmployees().size() > employeeRepository.maxEmployeeNumber) {
            throw new EmployeeStorageIsFullException();
        } else if (employeeRepository.getEmployees().containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException(employee.getFirstName(), employee.getLastName());
        } else {
            employeeRepository.employees.put(employee.getFullName(), employee);
            return employee;
        }


    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, 1, 1);
        if (employeeRepository.employees.containsKey(employee.getFullName())) {
            return employeeRepository.employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException(firstName, lastName);
    }

    @Override
    public void deleteEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, 1, 1);
        if (!employeeRepository.employees.containsKey(employee.getFullName())) throw new EmployeeNotFoundException(firstName, lastName);
        employeeRepository.employees.remove(employee.getFullName());
    }

    @Override
    public Collection<Employee> showAll() {
        return Collections.unmodifiableCollection(employeeRepository.employees.values());
    }



    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}

