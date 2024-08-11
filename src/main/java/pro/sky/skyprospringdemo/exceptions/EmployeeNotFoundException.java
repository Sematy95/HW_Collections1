package pro.sky.skyprospringdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends RuntimeException {
    private String firstName;
    private String lastName;

    public EmployeeNotFoundException(String firstName, String lastName) {
        super("Employee is not found");
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
