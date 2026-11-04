# Practical Lab-13

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Generic Containers & Methods (`GenericExample.java`)

### Problem Description
1. Create a generic class `Box<T>` with getter and setter methods.
2. Create a generic class `Pair<A, B>` to store two values of distinct types.
3. Demonstrate compile-time type safety with `Box<Integer>`, `Box<String>`, and `Pair<String, Integer>` without explicit typecasting.
4. Write a generic method `<T> T firstElement(T[] array)` returning the first element of any reference type array.

### How to Compile & Run
```bash
javac GenericExample.java
java GenericExample
```

### Expected Output
```text
100
Hello Java
Amit 20
10
Riya
```

---

## Practical 2: Bounded Type Parameters (`BoundedStats.java`)

### Problem Description
1. Implement a generic method `<N extends Number> double average(N[] values)`.
2. Compute the arithmetic mean using `value.doubleValue()`.
3. Test with `Integer[]`, `Double[]`, and `Long[]`.
4. **Observation:** The bound `extends Number` ensures numeric operations can be called safely and causes compile-time errors if non-numeric types (e.g., `String[]`) are passed.

### How to Compile & Run
```bash
javac BoundedStats.java
java BoundedStats
```

### Expected Output
```text
Integer average: 20.0
Double average: 3.5
Long average: 200.0
```
