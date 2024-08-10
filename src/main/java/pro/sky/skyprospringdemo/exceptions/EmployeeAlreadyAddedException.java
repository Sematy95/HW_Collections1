package pro.sky.skyprospringdemo.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException {
    private String firstName;
    private String lastName;

    public EmployeeAlreadyAddedException(String firstName, String lastName) {
        super("Employee already added");
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
