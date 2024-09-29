package pro.sky.skyprospringdemo.service;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.exceptions.NoEmployeesInDepartmentException;
import pro.sky.skyprospringdemo.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeRepository employeeRepository;


    public DepartmentServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Collection<Employee> showAllEmployeeDep(int department) {
        return employeeRepository.getEmployees().values().stream()
                .filter(d -> d.getDepartment() == department).toList();
    }

    @Override
    public Optional<Employee> findMinSalaryEmpDep(int department) {
        return Optional.ofNullable(employeeRepository.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(d -> d.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(NoEmployeesInDepartmentException::new));
    }

    @Override
    public Optional<Employee> findMaxSalaryEmpDep(int department) {
        return Optional.ofNullable(employeeRepository.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(d -> d.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(NoEmployeesInDepartmentException::new));
    }

    @Override
    public Map<Integer, List<Employee>> showAllEmployeeAllDep() {
        return employeeRepository.getEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

    @Override
    public int showSalarySumInDepartment(int department) {
        return employeeRepository.getEmployees().values().stream()
                .filter(d -> d.getDepartment() == department).mapToInt(Employee::getSalary).sum();
    }
}



