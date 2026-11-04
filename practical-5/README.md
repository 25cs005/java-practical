# Practical Lab-05

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Shape Areas Polymorphism (`ShapeDrive.java`)

### Problem Description
1. Create an abstract class `Shape` with an abstract method `double area()`.
2. Implement subclasses:
   - `Circle(double radius)`: Area = `3.14 * r * r`
   - `Rectangle(double length, double width)`: Area = `l * w`
   - `Triangle(double base, double height)`: Area = `0.5 * b * h`
3. In `main`: create a mixed `Shape[]` array.
4. Iterate through the array once using polymorphism:
   - Print each shape's area.
   - Accumulate total area.
   - Track and report the shape type with the largest area.

### How to Compile & Run
```bash
javac ShapeDrive.java
java ShapeDrive
```

### Expected Output
```text
Area is 1384.74
Area is 30.0
Area is 15.0
Largest shape is : Circle
```

---

## Practical 2: Employee Payroll System (`Payroll.java`)

### Problem Description
1. Create an abstract class `Employee` with fields `name` and `id`, constructor, and abstract method `double monthlySalary()`.
2. Implement subclasses:
   - `FullTime`: fixed salary.
   - `PartTime`: `hoursWorked * hourlyRate`.
   - `Intern`: stipend.
   - Each subclass constructor invokes `super(name, id)`.
3. In `main`: create an array of `Employee` objects with mixed types.
4. Loop through the array to:
   - Print each employee's name, ID, and salary.
   - Use `instanceof` to identify interns and append `[Note: This is an intern]`.
   - Calculate and display total salary payout.

### How to Compile & Run
```bash
javac Payroll.java
java Payroll
```

### Expected Output
```text
Alice (ID: 101) - Salary: $5000.0
Bob (ID: 102) - Salary: $1600.0
Charlie (ID: 103) - Salary: $1000.0 [Note: This is an intern]
David (ID: 104) - Salary: $6000.0
Eve (ID: 105) - Salary: $1500.0
Total Salary for all employees: $15100.0
```
