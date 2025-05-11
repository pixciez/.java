import java.io.*;

class Student implements Serializable {
    int id;
    String name;
    double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        Student student = new Student(1, "Alice", 3.8);
        String filename = "student.ser";
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(student);
            System.out.println("Student serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student deserialized = (Student) in.readObject();
            System.out.println("Deserialized Student: " + deserialized.name + " GPA: " + deserialized.gpa);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}