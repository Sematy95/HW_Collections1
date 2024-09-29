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
import static org.junit.jupiter.api.Assertions.*;
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

    @DisplayName("Положительный тест - поиск сотрудника по имени и фамилии")
    @Test
    public void findEmployee() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);
        //given
        Employee employee = new Employee("Ivan", "Ivanov", 1, 200_000);
        Employee expected = employees.get(employee.getFullName());

        //when
        Employee actual = employeeService.findEmployee("Ivan", "Ivanov");

        //then
        assertEquals(expected, actual);
    }

    @DisplayName("Неативный тест - нет сотрудника с такими именем и фамилией")
    @Test
    public void findEmployeeNoEmployeeWithSuchName() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //when//then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Пётр", "Первый"));

    }

    @DisplayName("Положительный тест - удаление сотрудника по имени и фамилии")
    @Test
    public void deleteEmployee() {


        //given
        Employee employee = new Employee("Oleg", "Smagin", 1, 200_000);

        //when(employeeRepository.employees.remove( employee))
        // .thenReturn(employees.remove(employee));

        // when(employeeRepository.getEmployees())
        //   .thenReturn(employees);

        Map<String, Employee> employees1 = new HashMap<>();
        employees1.putAll(employees);
        employees1.remove(employee.getFullName());

        //when
        employees.remove(employee.getFullName());

        //then
        assertEquals(employees1, employees);
    }

    @DisplayName("Негативный  тест - удаление сотрудника по имени и фамилии - нет такого сотрудника")
    @Test
    public void deleteEmployeeNoSuchEmployee() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //when//then
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee("Пётр", "Первый"));

    }

    @DisplayName("Положительный  тест - вывести всех")
    @Test
    public void showAll() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given
        Collection<Employee> expected = Collections.unmodifiableCollection(employees.values());

        //when
        Collection<Employee> actual = employeeService.showAll();

        //then
        assertIterableEquals(expected,actual);


    }


}
