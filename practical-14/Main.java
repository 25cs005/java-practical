/**
 * Practical Lab-14: Practical 1
 * Problem: Layered mini application with Model, Service (ArrayList, File I/O), and interactive Menu.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.io.*;
import java.util.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;

    Student(String name) {
        this.name = name;
    }
}

class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        students.add(new Student(name));
    }

    int getCount() {
        return students.size();
    }

    void save() throws IOException {
        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }
    }

    @SuppressWarnings("unchecked")
    void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (ArrayList<Student>) in.readObject();
        }
    }

    void display() {
        for (Student s : students) {
            System.out.println(s.name);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Show Students");
            System.out.println("3. Show Count");
            System.out.println("4. Save Data");
            System.out.println("5. Load Data");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        service.addStudent(sc.nextLine());
                        System.out.println("Student added");
                        break;
                    case 2:
                        service.display();
                        break;
                    case 3:
                        System.out.println("Total students: " + service.getCount());
                        break;
                    case 4:
                        service.save();
                        System.out.println("Data saved");
                        break;
                    case 5:
                        service.load();
                        System.out.println("Data loaded");
                        break;
                    case 6:
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
