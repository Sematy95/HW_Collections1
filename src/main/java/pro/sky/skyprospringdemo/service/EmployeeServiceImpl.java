package pro.sky.skyprospringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyprospringdemo.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringdemo.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyprospringdemo.exceptions.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public final int maxEmployeeNumber = 10;

    private final Map<String, Employee> employees = new HashMap<>(Map.of(

            "IvanIvanov", new Employee("Ivan", "Ivanov", 1, 200_000),
            "AlexanderPetrov", new Employee("Alexander", "Petrov", 1, 100_000),
            "MikhailSidorov", new Employee("Mikhail", "Sidorov", 3, 200_000),
            "StepanProkhorov", new Employee("Stepan", "Prokhorov", 4, 250_000),
            "LidiaStephanova", new Employee("Lidia", "Stephanova", 5, 92_000),
            "VictoriaGavrilova", new Employee("Victoria", "Gavrilova", 5, 90_000),
            "YuliaKarelina", new Employee("Yulia", "Karelina", 3, 220_000),
            "MikhailVasiliev", new Employee("Mikhail", "Vasiliev", 3, 350_000),
            "OlegMokrousov", new Employee("Oleg", "Mokrousov", 3, 250_000),
            "OlegSmagin", new Employee("Oleg", "Smagin", 3, 150_000)
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int departmentId) {
        Employee employee = new Employee(
                firstName,
                lastName,
                departmentId,
                salary
        );
        validateInput(firstName, lastName);
        if (employees.size() > maxEmployeeNumber) {
            throw new EmployeeStorageIsFullException();
        } else if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException(employee.getFirstName(), employee.getLastName());
        } else {
            employees.put(employee.getFullName(), employee);
            return employee;
        }


    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, 1, 1);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException(firstName, lastName);
    }

    @Override
    public void deleteEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, 1, 1);
        if (!employees.containsKey(employee.getFullName())) throw new EmployeeNotFoundException(firstName, lastName);
        employees.remove(employee.getFullName());
    }

    @Override
    public Collection<Employee> showAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public Optional<Employee> findMinSalaryEmpDep(int department) {
        return Optional.ofNullable(employees.values().stream()
                .filter(d -> d.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("There are no employee is such department")));
    }

    @Override
    public Optional<Employee> findMaxSalaryEmpDep(int department) {
        return Optional.ofNullable(employees.values().stream()
                .filter(d -> d.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("There are no employee is such department")));
    }

    @Override
    public Collection<Employee> showAllEmployeeDep(int department) {
        return employees.values().stream()
                .filter(d -> d.getDepartment() == department).toList();
    }

    @Override
    public Map<Integer, List<Employee>> showAllEmployeeAllDep() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}

