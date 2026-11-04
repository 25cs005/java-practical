# Practical Lab-12

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Word Frequency Counter (`WordFrequency.java`)

### Problem Description
1. Given a text paragraph, split it into words using regex `\\W+`.
2. Count the frequency of each lowercase word using `HashMap<String, Integer>`.
3. Sort the map entries by frequency in descending order, using alphabetical order as a tie-breaker.
4. Print each word and its corresponding occurrence count.

### How to Compile & Run
```bash
javac WordFrequency.java
java WordFrequency
```

### Expected Output
```text
is : 2
java : 2
and : 1
easy : 1
powerful : 1
```

---

## Practical 2: Unique Visitors Tracker (`UniqueVisitors.java`)

### Problem Description
1. Process an array of user names containing duplicate entries.
2. Use `HashSet<String>` to determine total unique user count.
3. Use `LinkedHashSet<String>` to preserve the first-seen insertion order.
4. Output the total unique user count and display the first three distinct names encountered.

### How to Compile & Run
```bash
javac UniqueVisitors.java
java UniqueVisitors
```

### Expected Output
```text
Unique users: 4
First three unique users:
Amit
Riya
Jay
```
