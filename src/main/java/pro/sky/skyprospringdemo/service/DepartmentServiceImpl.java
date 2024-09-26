package pro.sky.skyprospringdemo.service;

import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.repository.EmployeeRepository;
import pro.sky.skyprospringdemo.service.EmployeeService;

import java.util.Collection;

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

}



