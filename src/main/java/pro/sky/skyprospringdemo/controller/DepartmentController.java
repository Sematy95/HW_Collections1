package pro.sky.skyprospringdemo.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.service.DepartmentService;
import pro.sky.skyprospringdemo.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final EmployeeService employeeService;


    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> showAllEmployeeDep(@PathVariable int id) {
        return employeeService.showAllEmployeeDep(id);
    }

    @GetMapping("/{id}/salary/sum")
    public String showSalarySumInDepartment(@PathVariable int id) {
        return "Сумма";
    }
    @GetMapping("/{id}/salary/max")
    public Optional<Employee> showMaxSalaryInDepartment(@PathVariable int id) {
        return employeeService.findMaxSalaryEmpDep(id);
    }
    @GetMapping("/{id}/salary/min")
    public Optional<Employee> showMinSalaryInDepartment(@PathVariable int id) {
        return employeeService.findMinSalaryEmpDep(id);
    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> showAllDep() {
        return employeeService.showAllEmployeeAllDep();

    }
}