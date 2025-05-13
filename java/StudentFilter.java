import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    double marks;
    
    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentFilter {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("mi", 88),
            new Student("pi", 73),
            new Student("li", 80.5)
        );
        
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> -s.marks))
                .forEach(s -> System.out.println(s.name + " - " + s.marks));
    }
}