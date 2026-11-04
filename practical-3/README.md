# Practical Lab-03

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Distinct Points (`Point.java`)

### Problem Description
1. Create class `Point` with private `int x, y` and a constructor.
2. Override `toString()` to return `"(x, y)"`.
3. Override `equals(Object)` to be true when both `x` and `y` match, and `hashCode()` to agree.
4. In `main`: build a `Point[]` with some repeated coordinates.
5. Count distinct points: for each point, use `equals()` to check whether it already appeared earlier; count those that did not.
6. Print `"Total number of distinct points: N"`.

### How to Compile & Run
```bash
javac Point.java
java Point
# or: java Driver
```

### Expected Output
```text
Enter x and y coordinates for point 1:
1 2
Enter x and y coordinates for point 2:
3 4
Enter x and y coordinates for point 3:
1 2
Enter x and y coordinates for point 4:
5 6
Enter x and y coordinates for point 5:
3 4
Total number of distinct points: 3
```

---

## Practical 2: Duplicate Card Check (`Card.java`)

### Problem Description
1. Create class `Card` with private `String rank, suit` and a constructor.
2. Override `toString()` to return `rank + " of " + suit` (e.g. `"Queen of Hearts"`).
3. Override `equals()` and `hashCode()` by rank and suit.
4. In `main`: add cards to a `Card[]` one at a time; before adding each, use `equals()` to check it against earlier cards.
5. Print `"Duplicate found: <card>"` the first time a repeat appears.

### How to Compile & Run
```bash
javac Card.java
java Card
# or: java Driver1
```

### Expected Output
```text
Enter rank and suit for card 1:
Ace Spades
Enter rank and suit for card 2:
Ace Hearts
Enter rank and suit for card 3:
Ace Spades
Checking for duplicates...
Duplicate found: Ace of Spades
```
