package pro.sky.skyprospringdemo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pro.sky.skyprospringdemo.domain.Employee;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {


    public final int maxEmployeeNumber = 10;

    public final Map<String, Employee> employees = new HashMap<>(Map.of(

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

    public Map<String, Employee> getEmployees() {
        return employees;
    }


}
