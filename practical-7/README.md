# Practical Lab-07

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Form Validator with Annotations & Reflection (`FormValidator.java`)

### Problem Description
1. Define custom runtime annotations:
   - `@NotBlank`: target field.
   - `@MaxLength(int value)`: target field.
2. Create `SignupForm` with annotated `name` and `email` fields.
3. In `main`: prompt for name and email, then inspect each field using Java Reflection (`getDeclaredFields()`).
4. Read annotations using `isAnnotationPresent()` and enforce:
   - Not blank check.
   - Maximum character length check.
5. Report errors or confirm `"Form is valid"`.

### How to Compile & Run
```bash
javac FormValidator.java
java FormValidator
```

### Expected Output
```text
Enter name: Ayush
Enter email: 25cs005@charusat.edu.in
Form is valid
```

---

## Practical 2: Mini Test Runner (`MiniTestRunner.java`)

### Problem Description
1. Define custom runtime annotation `@Run` for methods.
2. Create `MyTests` with several methods, marking test methods with `@Run`.
3. In `main`: use Reflection (`getDeclaredMethods()`) to discover and invoke only `@Run` methods with 0 arguments.
4. Report total number of executed tests.

### How to Compile & Run
```bash
javac MiniTestRunner.java
java MiniTestRunner
```

### Expected Output
```text
Test 1 passed
Test 2 passed
Tests run: 2
```
