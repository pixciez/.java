import java.util.*;
import java.io.*;

class Employee implements Serializable {
    int id;
    String name, designation;
    double salary;
    
    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
}

public class EmployeeManager {
    static final String FILE_NAME = "employees.dat";
    
    public static void addEmployee() {
        try {
            List<Employee> employees = loadEmployees();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            for (Employee e : employees) {
                if (e.id == id) {
                    System.out.println("Error: Employee ID already exists.");
                    return;
                }
            }
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            employees.add(new Employee(id, name, designation, salary));
            saveEmployees(employees);
            System.out.println("Employee Added!");
        } catch (Exception e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }
    
    public static void displayEmployees() {
        try {
            List<Employee> employees = loadEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }
            for (Employee e : employees) {
                System.out.println(e.id + " " + e.name + " " + e.designation + " $" + e.salary);
            }
        } catch (Exception e) {
            System.out.println("Error displaying employees: " + e.getMessage());
        }
    }
    
    private static List<Employee> loadEmployees() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Employee>) in.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        }
    }
    
    private static void saveEmployees(List<Employee> employees) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employees);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Employee\n2. Display All\n3. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> displayEmployees();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }
}
