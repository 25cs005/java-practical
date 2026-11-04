# Practical Lab-02

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Smart Thermostat Simulation (`Thermostat.java`)

### Problem Description
1. Create class `Thermostat` with private `location`, private `temperature`, constants `MIN = 16`, `MAX = 30`, and static `activeCount = 0`.
2. Constructor `(location, startTemp)`: store location; set temperature to `startTemp` if within range `MIN..MAX` else `22`; increment `activeCount`.
3. Constructor `(location)`: chain with `this(location, 22)`.
4. `raise()`: if `temperature < MAX` add 1, else print `"Already at maximum (30)"`.
5. `lower()`: if `temperature > MIN` subtract 1, else print `"Already at minimum (16)"`.
6. Add `getTemperature()` and static `getActiveCount()`.
7. In `main`: create two thermostats, call `raise()` 10 times then `lower()` 20 times in loops (printing temperature each time), then print `getActiveCount()`.

### How to Compile & Run
```bash
javac Thermostat.java
java Thermostat
# or: java ThermostatDemo
```

### Expected Output
- Temperature rises until 30, prints `"Already at maximum (30)"`.
- Temperature decreases until 16, prints `"Already at minimum (16)"`.
- Active Count = 2.

---

## Practical 2: Cinema Show Booking Simulation (`CinemaShow.java`)

### Problem Description
1. Create class `CinemaShow` with private `title`, private `seatsAvailable`, private final `capacity`, and private static `totalBooked = 0`.
2. Constructor `(title, capacity)`: set both and `seatsAvailable = capacity`.
3. Constructor `(title)`: chain with `this(title, 100)`.
4. `book(int n)`: if `n <= seatsAvailable`, reduce `seatsAvailable` by `n`, add `n` to `totalBooked`, return `true`; else return `false` unchanged.
5. `cancel(int n)`: increase `seatsAvailable` by `n` but never above `capacity`.
6. Add `getSeatsAvailable()` and static `getTotalBooked()`.
7. In `main`: run a sequence of book and cancel calls, printing the result and seats after each, then print `getTotalBooked()`.

### How to Compile & Run
```bash
javac CinemaShow.java
java CinemaShow
```

### Expected Output
```text
Book 20 seats: true
Seats Available: 30

Book 15 seats: true
Seats Available: 15

Cancel 10 seats
Seats Available: 25

Book 40 seats: false
Seats Available: 25

Total Booked Seats: 35
```

