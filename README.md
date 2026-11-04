# Java Practical Lab Repository (`java-practical`)

Comprehensive repository containing all 14 Object-Oriented Programming with Java practical lab assignments, implementations, tests, and documentation.

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
├── practical-11/
│   ├── SaveLoad.java            # Object serialization & deserialization with transient fields
│   ├── LogAnalyzer.java         # Modern Java NIO (Path, Files, BasicFileAttributes)
│   └── README.md
├── practical-12/
│   ├── WordFrequency.java       # Map counting & custom Comparator frequency sorting
│   ├── UniqueVisitors.java      # HashSet & LinkedHashSet order preservation
│   └── README.md
├── practical-13/
│   ├── GenericExample.java      # Generic containers (Box<T>, Pair<A, B>) and methods
│   ├── BoundedStats.java        # Bounded type generics (<N extends Number>)
│   └── README.md
├── practical-14/
│   ├── Main.java                # Layered student management app with persistence
│   └── README.md
├── push.ps1                     # Automated PowerShell script to push to GitHub
├── push.bat                     # Automated Windows batch script to push to GitHub
└── README.md
```

---

## 📋 Complete Practicals Index (1 to 14)

| Practical | Topics / Concepts Covered | Primary Source Files |
| :--- | :--- | :--- |
| **Practical 1** | Enums, Switch Expressions, Java Records | [`VendingMachine.java`](practical-1/VendingMachine.java), [`TollBooth.java`](practical-1/TollBooth.java) |
| **Practical 2** | Constructors, `this()` Chaining, Static Trackers | [`Thermostat.java`](practical-2/Thermostat.java), [`CinemaShow.java`](practical-2/CinemaShow.java) |
| **Practical 3** | Overriding `toString`, `equals`, `hashCode` | [`Point.java`](practical-3/Point.java), [`Card.java`](practical-3/Card.java) |
| **Practical 4** | Regular Expressions, String Parsing, `StringBuilder` | [`PasswordChecker.java`](practical-4/PasswordChecker.java), [`Chat.java`](practical-4/Chat.java) |
| **Practical 5** | Abstract Classes, Polymorphism, `instanceof` Operator | [`ShapeDrive.java`](practical-5/ShapeDrive.java), [`Payroll.java`](practical-5/Payroll.java) |
| **Practical 6** | Interfaces, Default Methods, Anonymous Classes, Lambdas, Marker Interfaces | [`RemoteControl.java`](practical-6/RemoteControl.java), [`NotificationSystem.java`](practical-6/NotificationSystem.java) |
| **Practical 7** | Custom Runtime Annotations, Java Reflection API | [`FormValidator.java`](practical-7/FormValidator.java), [`MiniTestRunner.java`](practical-7/MiniTestRunner.java) |
| **Practical 8** | Custom Checked Exceptions, Propagation, `finally` Cleanup | [`GuardedCalculator.java`](practical-8/GuardedCalculator.java), [`Warehouse.java`](practical-8/Warehouse.java) |
| **Practical 9** | Multithreading, Concurrency Race Conditions, Thread Synchronization | [`CounterRace.java`](practical-9/CounterRace.java), [`SeatBookingRace.java`](practical-9/SeatBookingRace.java) |
| **Practical 10** | `ExecutorService` Thread Pools, Producer-Consumer with `wait()` / `notifyAll()` | [`ThreadPoolRunner.java`](practical-10/ThreadPoolRunner.java), [`ProducerConsumer.java`](practical-10/ProducerConsumer.java) |
| **Practical 11** | Object Serialization (`ObjectOutputStream`), `transient` fields, Java NIO `Files` | [`SaveLoad.java`](practical-11/SaveLoad.java), [`LogAnalyzer.java`](practical-11/LogAnalyzer.java) |
| **Practical 12** | Java Collections Framework: `HashMap`, `Comparator`, `HashSet`, `LinkedHashSet` | [`WordFrequency.java`](practical-12/WordFrequency.java), [`UniqueVisitors.java`](practical-12/UniqueVisitors.java) |
| **Practical 13** | Generic Classes (`Box<T>`, `Pair<A, B>`) and Bounded Generic Methods (`<N extends Number>`) | [`GenericExample.java`](practical-13/GenericExample.java), [`BoundedStats.java`](practical-13/BoundedStats.java) |
| **Practical 14** | Layered Application Architecture (Model-Service-UI) with Object Serialization | [`Main.java`](practical-14/Main.java) |

---

## 🚀 How to Run the Practicals

Navigate to any practical directory and execute:

```bash
# Example: Practical 11
cd practical-11
javac SaveLoad.java
java SaveLoad

# Example: Practical 14
cd practical-14
javac Main.java
java Main
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
git commit -m "Complete practicals 1 to 14 with full implementation and documentation"
git push -u origin main
```
