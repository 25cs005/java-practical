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
│   ├── VendingMachine.java      # Vending machine simulation using enum & switch expressions
│   ├── TollBooth.java           # Toll booth simulation using records & switch expressions
│   └── README.md
├── practical-2/
│   ├── Thermostat.java          # Smart thermostat with constructor chaining & static count
│   ├── CinemaShow.java          # Cinema booking system with capacity tracking
│   └── README.md
├── practical-3/
│   ├── Point.java               # Point coordinate comparison with equals() and hashCode()
│   ├── Card.java                # Playing card duplicate detection
│   └── README.md
├── practical-4/
│   ├── PasswordChecker.java     # Regex-based password strength evaluator
│   ├── Chat.java                # Log parsing and filter using StringBuilder
│   └── README.md
├── practical-5/
│   ├── ShapeDrive.java          # Polymorphic shape area calculation with abstract classes
│   ├── Payroll.java             # Employee payroll management with instanceof checking
│   └── README.md
├── practical-6/
│   ├── RemoteControl.java       # Interface default methods, anonymous class & lambda rules
│   ├── NotificationSystem.java  # Functional interface Notifier & Urgent marker interface
│   └── README.md
├── practical-7/
│   ├── FormValidator.java       # Custom annotations (@NotBlank, @MaxLength) & Reflection
│   ├── MiniTestRunner.java      # Mini test runner executing @Run annotated methods
│   └── README.md
├── practical-8/
│   ├── GuardedCalculator.java   # Exception handling, DivideByZeroException & finally block
│   ├── Warehouse.java           # Checked exceptions: OutOfStockException & InvalidQuantity
│   └── README.md
├── practical-9/
│   ├── CounterRace.java         # Race condition demonstration & synchronized counters
│   ├── SeatBookingRace.java     # Concurrent seat booking & thread synchronization
│   └── README.md
├── practical-10/
│   ├── ThreadPoolRunner.java    # ExecutorService fixed thread pool & task execution
│   ├── ProducerConsumer.java    # Inter-thread coordination using wait() and notifyAll()
│   └── README.md
├── practical-11/                # (Upcoming Lab)
├── practical-12/                # (Upcoming Lab)
├── practical-13/                # (Upcoming Lab)
├── practical-14/                # (Upcoming Lab)
├── push.ps1                     # Automated PowerShell script to push to GitHub
├── push.bat                     # Automated Windows batch script to push to GitHub
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
| **Practical 6** | Interfaces, Default Methods, Anonymous Classes, Lambdas, Marker Interfaces | [`RemoteControl.java`](practical-6/RemoteControl.java), [`NotificationSystem.java`](practical-6/NotificationSystem.java) |
| **Practical 7** | Custom Annotations, Reflection API (`Field`, `Method`, `invoke`) | [`FormValidator.java`](practical-7/FormValidator.java), [`MiniTestRunner.java`](practical-7/MiniTestRunner.java) |
| **Practical 8** | Custom Checked Exceptions, Exception Propagation, `finally` Block | [`GuardedCalculator.java`](practical-8/GuardedCalculator.java), [`Warehouse.java`](practical-8/Warehouse.java) |
| **Practical 9** | Multithreading, Race Conditions, `synchronized` Methods/Blocks | [`CounterRace.java`](practical-9/CounterRace.java), [`SeatBookingRace.java`](practical-9/SeatBookingRace.java) |
| **Practical 10** | Concurrency, `ExecutorService`, Thread Pools, `wait()` / `notifyAll()` | [`ThreadPoolRunner.java`](practical-10/ThreadPoolRunner.java), [`ProducerConsumer.java`](practical-10/ProducerConsumer.java) |
| **Practical 11 - 14** | *To be attached in subsequent submissions* | Reserved folders initialized |

---

## 🚀 How to Run the Practicals

Navigate to any practical directory and use the standard Java compiler and runtime:

### Example: Practical 6
```bash
cd practical-6
javac RemoteControl.java
java RemoteControl

javac NotificationSystem.java
java NotificationSystem
```

### Example: Practical 10
```bash
cd practical-10
javac ThreadPoolRunner.java
java ThreadPoolRunner

javac ProducerConsumer.java
java ProducerConsumer
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
git add .
git commit -m "Add practicals 6 to 10 with full implementation and documentation"
git push origin main
```
