# Practical Lab-01

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Vending Machine Simulation (`VendingMachine.java`)

### Problem Description
1. Define an enum `Coin` with constants `ONE`, `TWO`, `FIVE`, `TEN`.
2. In `main`, set a snack price of 15 and a running total of 0. Create a `Scanner`.
3. Loop: read a coin name, use a `switch` expression to convert the `Coin` to its value (`ONE -> 1`, `TWO -> 2`, `FIVE -> 5`, `TEN -> 10`), add it to the total, and print the total so far.
4. Stop the loop once the total reaches 15 or more.
5. Print the change to return (`total - 15`).

### How to Compile & Run
```bash
javac VendingMachine.java
java VendingMachine
```

### Expected Output
```text
Insert the coins (ONE, TWO, FIVE, TEN): 
TEN
Total so far: 10
FIVE
Total so far: 15
Paid. Change: 0
```

---

## Practical 2: Toll Booth Simulation (`TollBooth.java`)

### Problem Description
1. Define a record `Vehicle(String number, String type)`.
2. In `main`, keep a running toll total and three counters (`bike`, `car`, `truck`).
3. Loop until the user types `done` for the number:
   - Build a `Vehicle`.
   - Use a `switch` expression on its type for the toll (`bike -> 20`, `car -> 50`, `truck -> 150`).
   - Add to the total, and increment that type's counter.
4. After the loop, print the total toll and the type with the highest count.

### How to Compile & Run
```bash
javac TollBooth.java
java TollBooth
```

### Expected Output
```text
Enter vehicle number (or done): GJ01XXXX
Enter vehicle type (bike/car/truck): car
Enter vehicle number (or done): GJ02XXXX
Enter vehicle type (bike/car/truck): car
Enter vehicle number (or done): GJ03XXXX
Enter vehicle type (bike/car/truck): bike
Enter vehicle number (or done): done
Total toll: 120
Most frequent: car
```

