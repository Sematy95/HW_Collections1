package pro.sky.skyprospringdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEmployeesInDepartmentException extends RuntimeException {

    public NoEmployeesInDepartmentException() {
        super("There are no employees is such department");
    }
}
