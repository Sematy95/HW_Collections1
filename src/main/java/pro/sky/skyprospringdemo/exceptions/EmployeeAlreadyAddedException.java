package pro.sky.skyprospringdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends RuntimeException {
    private String firstName;
    private String lastName;

    public EmployeeAlreadyAddedException(String firstName, String lastName) {
        super("Employee already added");
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
