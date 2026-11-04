# MiniBank Lab 11: Serialization, Transaction Logs & Reports

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

### Objectives
1. Implement serialization via `StatePersister` using `ObjectOutputStream` / `ObjectInputStream` (`accounts.ser`).
2. Maintain append-only transaction logs using NIO `Files.write` with `CREATE` and `APPEND`.
3. Generate financial summaries via `ReportGenerator` using `DirectoryStream` and `BasicFileAttributes`.

### How to Compile & Run
```bash
javac MiniBank11.java
java MiniBank11
```
