# MiniBank Lab 14: Final Capstone Assembly & Service Architecture

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

### Objectives
1. Assemble the entire layered application:
   - **Model:** `Account`, `SavingsAccount`, `CurrentAccount` with `@Id`, `@Positive`.
   - **Data Access:** Generic `Repository<Account>` with reflective key extraction.
   - **Storage Layer:** `AccountStore` with `ConcurrentHashMap`.
   - **Persistence:** `StatePersister` (`accounts.ser`), `TransactionLog` (`transactions.txt`), and `ReportGenerator` (`report.txt`).
   - **Concurrency:** `batchDeposit` managed by `ExecutorService`.
   - **Service & UI:** `BankService` driving the full 9-option interactive menu.

### How to Compile & Run
```bash
javac MiniBank14.java
java MiniBank14
```
