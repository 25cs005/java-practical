# MiniBank Project Repository (Part B)

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  
**Institute:** CHARUSAT  

The **MiniBank** project is a semester-long progressive banking application developed across 14 laboratory milestones. Each lab enhances the application architecture from a basic menu shell to an enterprise-grade, concurrent, persistent, and reflective layered banking system.

---

## 🏛️ Project Progression Road Map

| Lab | Milestone | Key Concepts & Patterns | Source File |
| :--- | :--- | :--- | :--- |
| **MiniBank 1** | Project Setup & Shell | Java records (`BankInfo`), enums (`MenuOption`), switch expressions | [`MiniBank.java`](minibank-1/MiniBank.java) |
| **MiniBank 2** | Encapsulation & Domain Models | Static counters (`CUST101`, `AC0001`), immutable account numbers, `Customer` & `Account` | [`MiniBank2.java`](minibank-2/MiniBank2.java) |
| **MiniBank 3** | Object Contracts & Composition | Overriding `toString()`, `equals()`, `hashCode()`, static nested `Address`, `Cloneable` | [`MiniBank.java`](minibank-3/MiniBank.java) |
| **MiniBank 4** | Validation & Parsing | Regex `Pattern` matching (Mobile, Email, PAN, IFSC), `CommandParser`, `StringBuilder` statement | [`MiniBank.java`](minibank-4/MiniBank.java) |
| **MiniBank 5** | Inheritance & Polymorphism | Abstract `Account`, subclasses (`SavingsAccount`, `CurrentAccount`, `FixedDepositAccount`), `instanceof` | [`MiniBank5.java`](minibank-5/MiniBank5.java) |
| **MiniBank 6** | Interfaces & Lambdas | Default methods (`InterestBearing`), functional interfaces (`WithdrawRule`), marker interface (`Premium`) | [`MiniBank6.java`](minibank-6/MiniBank6.java) |
| **MiniBank 7** | Annotations & Reflection | Custom runtime annotations (`@Id`, `@Positive`, `@MaxLength`), reflective `AnnotationValidator` | [`MiniBank7.java`](minibank-7/MiniBank7.java) |
| **MiniBank 8** | Fault Tolerance & Exceptions | Checked exception hierarchy (`BankException`, `InsufficientFundsException`), `AutoCloseable` session | [`MiniBank8.java`](minibank-8/MiniBank8.java) |
| **MiniBank 9** | Concurrency & Race Conditions | Multithreading race conditions with `AccountWorker`, synchronization prevention | [`MiniBank9.java`](minibank-9/MiniBank9.java) |
| **MiniBank 10** | Thread Pools & Deadlock Safety | `ExecutorService`, producer-consumer buffer with `wait()`/`notifyAll()`, ordered locking deadlock prevention | [`MiniBank10.java`](minibank-10/MiniBank10.java) |
| **MiniBank 11** | I/O Persistence & Reports | Object serialization (`StatePersister`), append-only `TransactionLog`, Java NIO directory reporting | [`MiniBank11.java`](minibank-11/MiniBank11.java) |
| **MiniBank 12** | Collections & Thread-Safe Maps | `HashMap`, `ArrayList`, `Comparable`, `Comparator` sorting, concurrent storage with `ConcurrentHashMap` | [`MiniBank12.java`](minibank-12/MiniBank12.java) |
| **MiniBank 13** | Generic Repository Pattern | Generic `Repository<T>`, dynamic `@Id` key extraction via reflection, `@SafeVarargs`, bounded generics | [`MiniBank13.java`](minibank-13/MiniBank13.java) |
| **MiniBank 14** | Capstone Layered Application | End-to-end layered architecture: `BankService`, repository, validation, persistence, batch concurrency & interactive menu | [`MiniBank14.java`](minibank-14/MiniBank14.java) |
