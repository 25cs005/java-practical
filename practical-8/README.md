# Practical Lab-08

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Guarded Calculator (`GuardedCalculator.java`)

### Problem Description
1. Read two numbers and an operator (`+`, `-`, `*`, `/`).
2. Throw a custom checked exception `DivideByZeroException` when divisor is 0.
3. Catch `NumberFormatException` when numeric parsing fails.
4. Loop until a valid calculation succeeds.
5. Use a `finally` block to log each attempt number.

### How to Compile & Run
```bash
javac GuardedCalculator.java
java GuardedCalculator
```

### Expected Output
```text
Enter first number: 3
Enter operator (+, -, *, /): +
Enter second number: 3
Result: 6.0
Attempt 1
```

---

## Practical 2: Warehouse Stock Management (`Warehouse.java`)

### Problem Description
1. Create custom checked exceptions:
   - `OutOfStockException`: holds shortfall count.
   - `InvalidQuantityException`: triggered when quantity <= 0.
2. In `Warehouse`:
   - Initialize stock (e.g. 10).
   - `issue(String item, int qty)`: checks validity and stock availability, updating stock or throwing corresponding exceptions.
3. In `Stock.main`:
   - Process multiple item requests.
   - Catch and print exceptions gracefully without halting program execution.

### How to Compile & Run
```bash
javac Warehouse.java
java Warehouse
# or: java Stock
```

### Expected Output
```text
Issued 3 Pen, Remaining stock = 7
Failed: Book - Out of stock! Shortfall = 1
Failed: Pencil - Invalid quantity for Pencil
Issued 5 Eraser, Remaining stock = 2
All requests processed.
```
