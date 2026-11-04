# Practical Lab-14

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Layered Mini Application & Persistence (`Main.java`)

### Problem Description
1. Assemble a modular, layered Java application architecture:
   - **Model (`Student`):** encapsulates student entity and implements `Serializable`.
   - **Service Layer (`StudentService`):** manages in-memory data using `ArrayList<Student>`, validates input, computes counts, and handles disk persistence via `ObjectOutputStream` and `ObjectInputStream` (`students.dat`).
   - **User Interface (`Main`):** interactive command-line console loop offering options to add, view, count, save, and load students.
2. Gracefully handle exceptions such as invalid user input and I/O serialization errors.

### How to Compile & Run
```bash
javac Main.java
java Main
```

### Expected Output
```text
1. Add Student
2. Show Students
3. Show Count
4. Save Data
5. Load Data
6. Exit
Enter choice: 1
Enter name: Ayush
Student added

1. Add Student
2. Show Students
3. Show Count
4. Save Data
5. Load Data
6. Exit
Enter choice: 2
Ayush

1. Add Student
2. Show Students
3. Show Count
4. Save Data
5. Load Data
6. Exit
Enter choice: 3
Total students: 1
```
