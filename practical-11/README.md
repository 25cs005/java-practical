# Practical Lab-11

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Object Serialization & Deserialization (`SaveLoad.java`)

### Problem Description
1. Create a `Student` class implementing `Serializable`.
2. Include fields: `int id`, `String name`, and `transient String password`.
3. Serialize an array of `Student` objects to file `students.dat` using `ObjectOutputStream`.
4. Read the objects back from disk using `ObjectInputStream`.
5. Verify data persistence and confirm that the `transient` field `password` is reset to `null`.

### How to Compile & Run
```bash
javac SaveLoad.java
java SaveLoad
```

### Expected Output
```text
ID: 1
Name: Amit
Password: null
ID: 2
Name: Riya
Password: null
```

---

## Practical 2: File System & Log Analyzer (`LogAnalyzer.java`)

### Problem Description
1. Create sample log text files using `Files.write()`.
2. Use modern Java NIO `Path` and `Files.readAllLines()` to process file lines.
3. Count overall line occurrences and occurrences containing a target keyword (e.g. `"Java"`).
4. Read and display file metadata (file size in bytes) using `Files.readAttributes()` with `BasicFileAttributes`.

### How to Compile & Run
```bash
javac LogAnalyzer.java
java LogAnalyzer
```

### Expected Output
```text
log1.txt Size: 45 bytes
log2.txt Size: 38 bytes
Total lines: 5
Lines containing Java: 3
```
