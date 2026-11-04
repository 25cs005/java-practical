# Practical Lab-06

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Remote Control (`RemoteControl.java`)

### Problem Description
1. Create an interface `Switchable` with `on()`, `off()`, `isOn()`, `getName()`, and a default method `toggle()`.
2. Implement classes `Fan` and `Light` implementing `Switchable`.
3. Loop over an array `Switchable[]` and call `toggle()` on each device.
4. Define a functional interface `SwitchOnRule` with `maySwitchOn(Switchable device, int hour)` to decide if a device may switch on.
5. Provide implementations using a regular class (`DayTimeRule`), an anonymous inner class, and a lambda expression.

### How to Compile & Run
```bash
javac RemoteControl.java
java RemoteControl
```

### Expected Output
```text
Fan is ON
Light is ON
Fan is OFF
Light is OFF
cannot on the deviceFan
```

---

## Practical 2: Notification Broadcast System (`NotificationSystem.java`)

### Problem Description
1. Create a `@FunctionalInterface Notifier` with `void send(String msg)`.
2. Define a marker interface `Urgent`.
3. Implement `UrgentEmail` and `UrgentSMS` implementing both `Notifier` and `Urgent`.
4. Create standard email and SMS senders as lambda expressions.
5. In `main`:
   - Store all senders in a `Notifier[]` array.
   - Run a normal broadcast (each sender delivers once).
   - Run an urgent broadcast using `instanceof Urgent` (urgent senders deliver twice).

### How to Compile & Run
```bash
javac NotificationSystem.java
java NotificationSystem
```

### Expected Output
```text
Enter notificationmsg: Meeting at 10AM

Normal Broadcast
EMAIL: Meeting at 10AM
SMS: Meeting at 10AM
URGENT EMAIL: Meeting at 10AM
URGENT SMS: Meeting at 10AM

Urgent Broadcast
URGENT EMAIL: Meeting at 10AM
URGENT EMAIL: Meeting at 10AM
URGENT SMS: Meeting at 10AM
URGENT SMS: Meeting at 10AM
```
