import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    String id;
    String name;
    double salary;

    Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s $%,10.2f", id, name, salary);
    }
}

public class Employee_List {
    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display Employees");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> remove();
                case 4 -> search();
                case 5 -> display();
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1-6.");
            }
        }
    }

    private static void add() {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty() || find(id) != null) {
            System.out.println("Error: Employee with ID '" + id + "' already exists or ID cannot be empty.");
            return;
        }

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully.");
    }

    private static void update() {
        System.out.print("Enter Employee ID to update: ");
        String id = scanner.nextLine().trim();

        Employee emp = find(id);
        if (emp == null) {
            System.out.println("Error: Employee not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        emp.name = scanner.nextLine();

        System.out.print("Enter New Salary: ");
        emp.salary = scanner.nextDouble();

        System.out.println("Employee updated successfully.");
    }

    private static void remove() {
        System.out.print("Enter Employee ID to remove: ");
        String id = scanner.nextLine().trim();

        Employee emp = find(id);
        if (emp == null) {
            System.out.println("Error: Employee not found.");
            return;
        }

        employees.remove(emp);
        System.out.println("Employee removed successfully.");
    }

    private static void search() {
        System.out.print("Enter Employee ID to search: ");
        String id = scanner.nextLine().trim();

        Employee emp = find(id);
        if (emp == null) {
            System.out.println("Error: Employee not found.");
            return;
        }

        System.out.println("Employee Found: " + emp);
    }

    private static void display() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n===============================================");
        System.out.println(String.format("%-10s %-15s %10s", "ID", "Name", "Salary"));
        System.out.println("===============================================");

        for (Employee emp : employees) {
            System.out.println(emp);
        }
        System.out.println("===============================================");
    }

    private static Employee find(String id) {
        for (Employee emp : employees) {
            if (emp.id.equalsIgnoreCase(id)) {
                return emp;
            }
        }
        return null;
    }
}