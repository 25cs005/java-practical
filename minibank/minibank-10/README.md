# MiniBank Lab 10: Thread Pools, Producer-Consumer & Deadlock Prevention

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

### Objectives
1. Implement `TransactionProcessor` managing an `ExecutorService` fixed thread pool of 4 workers.
2. Coordinate concurrent transactions with `TransactionBuffer` using `wait()` and `notifyAll()`.
3. Demonstrate account transfer deadlock using `CountDownLatch` and resolve it using ordered resource locking (comparing `accountNumber`).

### How to Compile & Run
```bash
javac MiniBank10.java
java MiniBank10
```
