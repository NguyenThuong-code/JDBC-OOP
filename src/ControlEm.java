import java.util.ArrayList;
import java.util.List;

public class ControlEm {
    List<Employee> employeeList= new ArrayList<>();
    public Employee addEmployee(Employee employee){
        employeeList.add(employee);
        return employee;
    }
}
