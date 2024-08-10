package pro.sky.skyprospringdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.service.EmployeeService;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping(path = "/employee/add")
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

    @GetMapping(path = "/employee/delete")
    public String deleteEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {

        employeeService.deleteEmployee(firstName, lastName);
        return "Employee deleted";
    }

    @GetMapping(path = "employee/find")
    public String findEmployee(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName) {

        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "employee/showAll")
    public String showAll() {
        return employeeService.showAllEmployees();
    }
}