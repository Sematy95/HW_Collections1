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

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}/employees")
    public Collection<Employee> showAllEmployeeDep(@PathVariable int id) {
        return departmentService.showAllEmployeeDep(id);
    }

    @GetMapping("/{id}/salary/sum")
    public String showSalarySumInDepartment(@PathVariable int id) {
        return "Сумма";
    }
    @GetMapping("/{id}/salary/max")
    public Optional<Employee> showMaxSalaryInDepartment(@PathVariable int id) {
        return null;
    }
    @GetMapping("/{id}/salary/min")
    public Optional<Employee> showMinSalaryInDepartment(@PathVariable int id) {
        return null;
    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> showAllDep() {
        return null;

    }
}