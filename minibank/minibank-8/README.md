# MiniBank Lab 8: Custom Exception Hierarchy & Fault Tolerance

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

### Objectives
1. Implement checked exception base class `BankException` and subclasses:
   - `InsufficientFundsException` (holding the computed `shortfall`).
   - `AccountNotFoundException`.
   - `InvalidAmountException`.
2. Implement transaction `transfer(Account to, long amount)` with `try-catch-finally`.
3. Resource management with `AutoCloseable` `BankSession` inside `try-with-resources`.

### How to Compile & Run
```bash
javac MiniBank8.java
java MiniBank8
```
