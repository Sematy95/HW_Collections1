package pro.sky.skyprospringdemo.service;

import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.service.EmployeeService;

import java.util.Collection;

public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;


    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Collection<Employee> showAllEmployeeDep(int department) {
        return employeeService.showAll().stream()
                .filter(d -> d.getDepartment() == department).toList();
    }

}



