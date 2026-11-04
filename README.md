# Java Practical Lab Repository (`java-practical`)

Repository containing Object-Oriented Programming with Java practical lab assignments and implementations.

---

## 👤 Student Profile
- **Name:** Ayush Chodvadiya
- **Student ID:** 25cs005
- **Email:** 25cs005@charusat.edu.in
- **GitHub:** [@25cs005](https://github.com/25cs005)

---

## 📂 Repository Structure

```text
java-practical/
├── practical-1/
│   ├── VendingMachine.java  # Vending machine simulation using enum & switch expressions
│   ├── TollBooth.java       # Toll booth simulation using records & switch expressions
│   └── README.md
├── practical-2/
│   ├── Thermostat.java      # Smart thermostat with constructor chaining & static count
│   ├── CinemaShow.java      # Cinema booking system with capacity tracking
│   └── README.md
├── practical-3/
│   ├── Point.java           # Point coordinate comparison with equals() and hashCode()
│   ├── Card.java            # Playing card duplicate detection
│   └── README.md
├── practical-4/
│   ├── PasswordChecker.java # Regex-based password strength evaluator
│   ├── Chat.java            # Log parsing and filter using StringBuilder
│   └── README.md
├── practical-5/
│   ├── ShapeDrive.java      # Polymorphic shape area calculation with abstract classes
│   ├── Payroll.java         # Employee payroll management with instanceof checking
│   └── README.md
├── practical-6/             # (Upcoming Lab)
├── practical-7/             # (Upcoming Lab)
├── practical-8/             # (Upcoming Lab)
├── practical-9/             # (Upcoming Lab)
├── practical-10/            # (Upcoming Lab)
├── practical-11/            # (Upcoming Lab)
├── practical-12/            # (Upcoming Lab)
├── practical-13/            # (Upcoming Lab)
├── practical-14/            # (Upcoming Lab)
├── push.ps1                 # Automated PowerShell script to push to GitHub
├── push.bat                 # Automated Windows batch script to push to GitHub
└── README.md
```

---

## 📋 Practicals Overview

| Practical | Topics / Concepts Covered | Primary Source Files |
| :--- | :--- | :--- |
| **Practical 1** | Enums, Switch Expressions, Java Records | [`VendingMachine.java`](practical-1/VendingMachine.java), [`TollBooth.java`](practical-1/TollBooth.java) |
| **Practical 2** | Constructors, `this()` chaining, Static Fields, Encapsulation | [`Thermostat.java`](practical-2/Thermostat.java), [`CinemaShow.java`](practical-2/CinemaShow.java) |
| **Practical 3** | Object Methods Overriding (`toString`, `equals`, `hashCode`) | [`Point.java`](practical-3/Point.java), [`Card.java`](practical-3/Card.java) |
| **Practical 4** | String Processing, Regular Expressions, `StringBuilder` | [`PasswordChecker.java`](practical-4/PasswordChecker.java), [`Chat.java`](practical-4/Chat.java) |
| **Practical 5** | Abstract Classes, Inheritance, Polymorphism, `instanceof` | [`ShapeDrive.java`](practical-5/ShapeDrive.java), [`Payroll.java`](practical-5/Payroll.java) |
| **Practical 6 - 14** | *To be attached in subsequent submissions* | Reserved folders initialized |

---

## 🚀 How to Run the Practicals

Navigate to any practical directory and use the standard Java compiler and runtime:

### Example: Practical 1
```bash
cd practical-1
javac VendingMachine.java
java VendingMachine

javac TollBooth.java
java TollBooth
```

### Example: Practical 5
```bash
cd practical-5
javac ShapeDrive.java
java ShapeDrive

javac Payroll.java
java Payroll
```

---

## 🔄 Automated Git Push

To automatically push this repository to GitHub under account **25cs005**:

### Option 1: Using PowerShell
```powershell
.\push.ps1
```

### Option 2: Using Command Prompt / Batch
```cmd
push.bat
```

### Option 3: Manual Git Commands
```bash
git init
git add .
git commit -m "Add practicals 1 to 5 with proper structure and documentation"
git branch -M main
git remote add origin https://github.com/25cs005/java-practical.git
git push -u origin main
```
