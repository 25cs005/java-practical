# Practical Lab-04

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Password Strength Checker (`PasswordChecker.java`)

### Problem Description
1. In `PasswordChecker`, check four rules:
   - Length >= 8 (`hasMinLength`)
   - Contains an uppercase letter (`hasUpperCase`)
   - Contains a digit (`hasDigit`)
   - Contains a special character (`hasSpecialCharacter`)
2. Write `strength(String pw)` that returns:
   - `"Weak"` (0-1 rules passed)
   - `"Medium"` (2-3 rules passed)
   - `"Strong"` (4 rules passed)
3. In `main`: prompt for a password, print rule evaluation results, and output the final strength rating.

### How to Compile & Run
```bash
javac PasswordChecker.java
java PasswordChecker
# or: java Driver3
```

### Expected Output
```text
Enter your password: ABCD1234!
Minimum length: Passed
Uppercase letter: Passed
Digit: Passed
Special character: Passed
Password Strength: Strong
```

---

## Practical 2: Chat-log Filter (`Chat.java`)

### Problem Description
1. Define a `String[]` of log lines (e.g. `"10:05 alice Hello there"`, including one malformed line).
2. Read a keyword from the user.
3. For each line, split into `time/user/message` using `split(" ", 3)`. Skip malformed lines.
4. If the message contains the keyword (case-insensitive), increment a counter and append `"time user: message\n"` to a `StringBuilder`.
5. Print `"Matches: N"` and the built report.

### How to Compile & Run
```bash
javac Chat.java
java Chat
```

### Expected Output
```text
Enter keyword: hello
Matches: 1
10:05 alice: Hello there
```
