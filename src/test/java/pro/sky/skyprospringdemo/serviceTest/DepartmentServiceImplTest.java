package pro.sky.skyprospringdemo.serviceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import pro.sky.skyprospringdemo.domain.Employee;
import pro.sky.skyprospringdemo.exceptions.EmployeeNotFoundException;
import pro.sky.skyprospringdemo.exceptions.NoEmployeesInDepartmentException;
import pro.sky.skyprospringdemo.repository.EmployeeRepository;
import pro.sky.skyprospringdemo.service.DepartmentService;
import pro.sky.skyprospringdemo.service.DepartmentServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

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
    private DepartmentServiceImpl departmentService;


    @DisplayName("Положительный тест- показать всех сотрудников в отделе")
    @Test
    public void showAllEmployeeDep() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given

        Collection<Employee> expected = employees.values().stream()
                .filter(d -> d.getDepartment() == 1).toList();


        //when
        Collection<Employee> actual = departmentService.showAllEmployeeDep(1);


        //then
        assertEquals(expected, actual);


    }

    @DisplayName("Положительный тест - показать всех сотрудников из несуществующего отдела")
    @Test
    public void showAllEmployeeDepNeg() {

        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given

        Collection<Employee> expected = Collections.emptyList();


        //when
        Collection<Employee> actual = departmentService.showAllEmployeeDep(199);

        //then
        assertEquals(expected, actual);

    }

    @DisplayName("Положительный тест - найти сотрудника с минимальной зп в отделе")
    @Test
    public void findMinSalaryEmpDep() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);
        //given
        Optional<Employee> expected = employees.values().stream()
                .filter(Objects::nonNull)
                .filter(d -> d.getDepartment() == 1)
                .min(Comparator.comparingInt(Employee::getSalary));

        //when
        Optional<Employee> actual = departmentService.findMinSalaryEmpDep(1);

        //then
        assertEquals(expected, actual);
    }

    @DisplayName("Негативный тест - найти сотрудника с минимальной зп в отделе, если отдела нет")
    @Test
    public void findMinSalaryEmpDepNoDepartment() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given//when//then
        assertThrows(NoEmployeesInDepartmentException.class, () -> departmentService.findMinSalaryEmpDep(100));
    }


    @DisplayName("Положительный тест - найти сотрудника с максимальной зп в отделе")
    @Test
    public void findMaxSalaryEmpDep() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);
        //given
        Optional<Employee> expected = employees.values().stream()
                .filter(Objects::nonNull)
                .filter(d -> d.getDepartment() == 1)
                .max(Comparator.comparingInt(Employee::getSalary));

        //when
        Optional<Employee> actual = departmentService.findMaxSalaryEmpDep(1);

        //then
        assertEquals(expected, actual);

    }
    @DisplayName("Негативный тест - найти сотрудника с максимальной зп в отделе, если отдела нет")
    @Test
    public void findMaxSalaryEmpDepNoDepartment() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //given//when//then
        assertThrows(NoEmployeesInDepartmentException.class, () -> departmentService.findMaxSalaryEmpDep(100));
    }
    @DisplayName("Позитивный тест - сортировка по отделам")
    @Test
    public void showAllEmployeeAllDep() {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);
        //given
        Map<Integer, List<Employee>> expected = employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        //when
        Map<Integer, List<Employee>> actual = departmentService.showAllEmployeeAllDep();

        //then
        assertEquals(expected, actual);
    }
    @DisplayName("Позитивный тест - поиск суммы зп по отделу")
    @ParameterizedTest
    @MethodSource("provideDataForShowSalarySumInDepartment")
    public void showSalarySumInDepartment(int department, int expected) {
        when(employeeRepository.getEmployees())
                .thenReturn(employees);

        //when
        int actual = departmentService.showSalarySumInDepartment(department);

        //then
        assertEquals(expected,actual);

    }

    private static Stream<Arguments> provideDataForShowSalarySumInDepartment() {
        return Stream.of(
                Arguments.arguments(1, 300_000),
                Arguments.arguments(4, 250_000));


    }
}

