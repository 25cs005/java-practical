# Practical Lab-09

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Counter Race Condition (`CounterRace.java` / `cerRace.java`)

### Problem Description
1. Create a `Counter` class with:
   - `count` variable.
   - `synchronized void increment()`
   - `void incrementWithoutSync()`
2. In `main`: prompt for number of threads and number of increments per thread.
3. Compare executions:
   - **Without synchronization**: race condition causes final count to be lower than expected.
   - **With synchronization**: all increments are atomic, producing exact expected total every time.

### How to Compile & Run
```bash
javac CounterRace.java
java CounterRace
# Or using original alias:
# javac cerRace.java
# java cerRace
```

### Expected Output
```text
Enter number of threads: 10
Enter increments per thread: 10000
1. Without Synchronization
2. With Synchronization
Enter choice: 1
Expected count: 100000
Actual count: 82123

Enter choice: 2
Expected count: 100000
Actual count: 100000
```

---

## Practical 2: Seat Booking Race Condition (`SeatBookingRace.java`)

### Problem Description
1. Create a `Booking` class with `seatsLeft = 5` and `booked = 0`.
2. Provide two booking methods:
   - `bookWithoutSync()`: prone to race conditions and overselling due to non-atomic check-then-act.
   - `synchronized book()`: ensures thread-safe booking where only 5 tickets can be booked.
3. In `main`: spawn 10 threads simulating concurrent user booking requests.

### How to Compile & Run
```bash
javac SeatBookingRace.java
java SeatBookingRace
```

### Expected Output
- **With Synchronization:**
```text
Seat booked successfully
Seat booked successfully
Seat booked successfully
Seat booked successfully
Seat booked successfully
No seats available
No seats available
No seats available
No seats available
No seats available

Successful bookings: 5
Seats left: 0
```
