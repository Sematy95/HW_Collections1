package pro.sky.skyprospringdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.service.EmployeeService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("department") Integer department,
                              @RequestParam("salary") Integer salary) {
        Employee employee = new Employee(
                firstName,
                lastName,
                department,
                salary
        );
        employeeService.addEmployee(employee);
        return "Employee added";
    }

    @GetMapping(path = "/delete")
    public String deleteEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {

        employeeService.deleteEmployee(firstName, lastName);
        return "Employee deleted";
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {

        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> showAll() {
        return employeeService.showAll();
    }

    @GetMapping(path = "/departments/max-salary")
    public Optional<Employee> findEmployeeMaxSalaryDepartment(@RequestParam("department") int department) {
        return employeeService.findMaxSalaryEmpDep(department);
    }

    @GetMapping(path = "/departments/min-salary")
    public Optional<Employee> findEmployeeMinSalaryDepartment(@RequestParam("department") int department) {
        return employeeService.findMinSalaryEmpDep(department);

    }

    @GetMapping(path = "/departments/all")
    public Collection<Employee> showAllEmployeesInDepartment(@RequestParam("department") int department) {
        return employeeService.showAllEmployeeDep(department);

    }

    @GetMapping(path = "/departments")
    public Collection<Employee> showAllDep() {
        return employeeService.showAllEmployeeAllDep();

    }

}