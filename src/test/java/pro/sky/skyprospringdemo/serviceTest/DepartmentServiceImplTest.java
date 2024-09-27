package pro.sky.skyprospringdemo.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.repository.EmployeeRepository;
import pro.sky.skyprospringdemo.service.DepartmentService;
import pro.sky.skyprospringdemo.service.DepartmentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Test
    public void showAllEmployeeDep() {


    }

    @Test
    public void findMinSalaryEmpDep() {
    }

    @Test
    public void findMaxSalaryEmpDep() {

    }

    @Test
    public void showAllEmployeeAllDep() {

    }

    @Test
    public void showSalarySumInDepartment() {
    }
}

