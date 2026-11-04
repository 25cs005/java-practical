# Object-Oriented Programming with Java - Complete Lab Repository

Comprehensive repository containing all **Part A (Lab Practicals 1 to 14)** and **Part B (MiniBank Project Milestones 1 to 14)** for Object-Oriented Programming with Java.

---

## 👤 Student Profile
- **Name:** Ayush Chodvadiya
- **Student ID:** 25cs005
- **Email:** 25cs005@charusat.edu.in
- **GitHub:** [@25cs005](https://github.com/25cs005)
- **Repository:** [`https://github.com/25cs005/java-practical`](https://github.com/25cs005/java-practical)

---

## 📂 Repository Architecture

```text
java-practical/
├── practical-1/ ... practical-14/   # PART A: Core Java Laboratory Practicals
│   ├── *.java                       # Java source implementations
│   └── README.md                    # Lab documentation & output
├── minibank/                        # PART B: Semester-long MiniBank Project
│   ├── minibank-1/ ... minibank-14/ # Iterative development milestones
│   └── README.md                    # Full architecture & milestones guide
├── push.ps1                         # Automated PowerShell push script
├── push.bat                         # Automated Windows batch push script
├── .gitignore                       # Clean Git tracking
└── README.md                        # Master repository documentation
```

---

## 📑 Part A: Core Java Practicals Index (1 to 14)

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

## 🏦 Part B: MiniBank Progressive Project Index (1 to 14)

| Milestone | Project Focus & Key Implementation | Source File |
| :--- | :--- | :--- |
| **MiniBank 1** | Project setup, `BankInfo` record, `MenuOption` enum & switch loop | [`minibank-1/MiniBank.java`](minibank/minibank-1/MiniBank.java) |
| **MiniBank 2** | Encapsulated `Customer` & `Account` with auto ID generation | [`minibank-2/MiniBank2.java`](minibank/minibank-2/MiniBank2.java) |
| **MiniBank 3** | `toString()`, `equals()`, `hashCode()`, static nested `Address`, `Cloneable` | [`minibank-3/MiniBank.java`](minibank/minibank-3/MiniBank.java) |
| **MiniBank 4** | Regex `Validator`, `CommandParser`, `StatementFormatter` with `StringBuilder` | [`minibank-4/MiniBank.java`](minibank/minibank-4/MiniBank.java) |
| **MiniBank 5** | Abstract `Account`, subclasses (`Savings`, `Current`, `FixedDeposit`) polymorphism | [`minibank-5/MiniBank5.java`](minibank/minibank-5/MiniBank5.java) |
| **MiniBank 6** | `Transactable`, `InterestBearing`, `WithdrawRule` lambdas & `Premium` marker | [`minibank-6/MiniBank6.java`](minibank/minibank-6/MiniBank6.java) |
| **MiniBank 7** | Custom annotations (`@Id`, `@Positive`, `@MaxLength`) & reflection validator | [`minibank-7/MiniBank7.java`](minibank/minibank-7/MiniBank7.java) |
| **MiniBank 8** | Fault tolerance: `BankException` hierarchy, `transfer()`, `AutoCloseable` session | [`minibank-8/MiniBank8.java`](minibank/minibank-8/MiniBank8.java) |
| **MiniBank 9** | Concurrent deposits with `AccountWorker`, synchronization race prevention | [`minibank-9/MiniBank9.java`](minibank/minibank-9/MiniBank9.java) |
| **MiniBank 10** | `ExecutorService` thread pool, producer-consumer queue, deadlock avoidance | [`minibank-10/MiniBank10.java`](minibank/minibank-10/MiniBank10.java) |
| **MiniBank 11** | Serialization `StatePersister`, append-only `TransactionLog`, NIO reports | [`minibank-11/MiniBank11.java`](minibank/minibank-11/MiniBank11.java) |
| **MiniBank 12** | Storage via `HashMap`, `ArrayList`, `Comparator` sorting & `ConcurrentHashMap` | [`minibank-12/MiniBank12.java`](minibank/minibank-12/MiniBank12.java) |
| **MiniBank 13** | Generic `Repository<T>`, dynamic reflection `@Id`, bounded generics | [`minibank-13/MiniBank13.java`](minibank/minibank-13/MiniBank13.java) |
| **MiniBank 14** | Complete capstone layered application (`BankService`, CLI UI, batch processing) | [`minibank-14/MiniBank14.java`](minibank/minibank-14/MiniBank14.java) |

---

## 🚀 How to Run

Navigate to any desired practical or MiniBank folder:

```bash
# Example: Running Part A Practical 14
cd practical-14
javac Main.java
java Main

# Example: Running Part B MiniBank 14 Capstone
cd minibank/minibank-14
javac MiniBank14.java
java MiniBank14
```

---

## 🔄 Automated Git Push

To push all changes to GitHub:
```powershell
.\push.ps1
```
*(Or run `push.bat` or `git push origin main`)*
