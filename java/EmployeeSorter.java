import java.util.*;

class Employee {
    String name;
    int age;
    double salary;
    
    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return name + " - Age: " + age + ", Salary: " + salary;
    }
}

public class EmployeeSorter {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Pi", 31, 314159),
            new Employee("Lambda", 34, 34895),
            new Employee("Epsilon", 28, 88541)
        );
        
        employees.sort(Comparator.comparingDouble(emp -> emp.salary));
        employees.forEach(System.out::println);
    }
}
