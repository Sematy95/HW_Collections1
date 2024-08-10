package pro.sky.skyprospringdemo.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException() {
        super("Storage is full");
    }
}
