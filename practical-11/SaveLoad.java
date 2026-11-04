/**
 * Practical Lab-11: Practical 1
 * Problem: Object Serialization and Deserialization with transient fields.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    transient String password; // transient field: will not be serialized

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}

public class SaveLoad {
    public static void main(String[] args) {
        try {
            Student[] students = {
                new Student(1, "Amit", "abc123"),
                new Student(2, "Riya", "xyz456")
            };

            // Serialization
            ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("students.dat"));
            out.writeObject(students);
            out.close();

            // Deserialization
            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("students.dat"));
            Student[] loaded = (Student[]) in.readObject();
            in.close();

            // Display loaded students
            for (Student s : loaded) {
                System.out.println("ID: " + s.id);
                System.out.println("Name: " + s.name);
                System.out.println("Password: " + s.password); // prints null due to transient
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

