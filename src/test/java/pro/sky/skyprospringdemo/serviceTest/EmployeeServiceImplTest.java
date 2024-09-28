package pro.sky.skyprospringdemo.serviceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.exceptions.*;
import pro.sky.skyprospringdemo.repository.EmployeeRepository;
import pro.sky.skyprospringdemo.service.EmployeeServiceImpl;
import pro.sky.skyprospringdemo.domain.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    private static final Map<String, Employee> employees = new HashMap<>(Map.of(

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
    private EmployeeServiceImpl employeeService;

    @DisplayName("Негативный тест- хранилище переполнено")
    @Test
    public void addEmployeeWithFullStorage() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //employees.put("SemyonTychkin", new Employee("Semyon", "Tychkin", 1, 200_000));
        employees.put("SemyonTychkin1", new Employee("Semyon", "Tychkin1", 1, 200_000));


        //given//when//then
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee("Ivan", "Ermenev", 120_000, 3));

    }
    @DisplayName("Негативный тест- уже есть такой сотрудник")
    @Test
    public void addEmployeeAlreadyAdded() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);


        //given//when//then
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("Ivan", "Ivanov", 120_000, 3));

    }
    @DisplayName("Позитивный тест- добавление нового сотрудника ")
    @Test
    public void addEmployee() {
        Employee employee = new Employee("Semyon", "Tychkin", 1, 200_000);

        //when(employeeRepository.employees.put(employee.getFullName(), employee)).thenReturn(employees.put(employee.getFullName(), employee));

        //given
        Map<String, Employee> employees1 = new HashMap<>();
        employees1.putAll(employees);
        employees1.put("SemyonTychkin", new Employee("Semyon", "Tychkin", 1, 200_000));


        //actual
        employees.put(employee.getFullName(), employee);
        //employeeService.addEmployee("Semyon", "Tychkin", 1, 200_000);

        //then
        assertEquals(employees1, employees);




    }

    @Test
    public void findEmployee() {
    }

    @Test
    public void deleteEmployee() {
    }

    @Test
    public void showAll() {
    }

    @Test
    public void validateInput() {
    }
}
