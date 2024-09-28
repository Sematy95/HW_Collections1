package pro.sky.skyprospringdemo.serviceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    private static final  Map<String, Employee> employees = new HashMap<>(Map.of(

            "IvanIvanov", new Employee("Ivan", "Ivanov", 1, 200_000),
            "AlexanderPetrov", new Employee("Alexander", "Petrov", 1, 100_000),
            "MikhailSidorov", new Employee("Mikhail", "Sidorov", 3, 200_000),
            "StepanProkhorov", new Employee("Stepan", "Prokhorov", 4, 250_000),
            "LidiaStephanova", new Employee("Lidia", "Stephanova", 5, 92_000),
            "VictoriaGavrilova", new Employee("Victoria", "Gavrilova", 5, 90_000),
            "YuliaKarelina", new Employee("Yulia", "Karelina", 3, 220_000),
            "MikhailVasiliev", new Employee("Mikhail", "Vasiliev", 3, 350_000),
            "OlegMokrousov", new Employee("Oleg", "Mokrousov", 3, 250_000),
            "OlegSmagin", new Employee("Oleg", "Smagin", 3, 150_000)
    ));

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;



    @DisplayName("Положительный тест- показать всех сотрудников в отделе")
    @Test
    public void showAllEmployeeDep() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given

        Collection <Employee> expected = employees.values().stream()
                .filter(d -> d.getDepartment() == 1).toList();


        //when
        Collection <Employee> actual =departmentService.showAllEmployeeDep(1);


        //then
        assertEquals(expected,actual);


    }
    @DisplayName("Положительный тест - показать всех сотрудников из несуществующего отдела")
    @Test
    public void showAllEmployeeDepNeg() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given

        Collection<Employee> expected = Collections.emptyList();


        //when
        Collection <Employee> actual =departmentService.showAllEmployeeDep(199);

        //then
        assertEquals(expected,actual);

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

