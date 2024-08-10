package pro.sky.skyprospringdemo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    private String firstName;
    private String lastName;

    public EmployeeNotFoundException(String firstName, String lastName) {
        super("Employee is not found");
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
