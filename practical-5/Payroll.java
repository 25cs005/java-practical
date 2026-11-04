/**
 * Practical Lab-05: Practical 2
 * Problem: Employee Payroll system using abstract classes, inheritance, and instanceof.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

abstract class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double fixedSalary;

    FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    @Override
    double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    double hoursWorked;
    double hourlyRate;

    PartTime(String name, int id, double hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    double monthlySalary() {
        return hoursWorked * hourlyRate;
    }
}

class Intern extends Employee {
    double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    double monthlySalary() {
        return stipend;
    }
}

public class Payroll {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new FullTime("Alice", 101, 5000);
        employees[1] = new PartTime("Bob", 102, 80, 20);
        employees[2] = new Intern("Charlie", 103, 1000);
        employees[3] = new FullTime("David", 104, 6000);
        employees[4] = new PartTime("Eve", 105, 60, 25);

        double totalSalary = 0;
        for (Employee emp : employees) {
            double salary = emp.monthlySalary();
            totalSalary += salary;
            System.out.print(emp.name + " (ID: " + emp.id + ") - Salary: $" + salary);
            if (emp instanceof Intern) {
                System.out.print(" [Note: This is an intern]");
            }
            System.out.println();
        }

        System.out.println("Total Salary for all employees: $" + totalSalary);
    }
}
