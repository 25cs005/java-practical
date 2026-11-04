# MiniBank Lab 13: Generic Repository & Reflection-based ID

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

### Objectives
1. Implement a type-safe generic `Repository<T>` with `save`, `findById`, `findAll`, and `delete`.
2. Extract the entity primary key dynamically using reflection to find fields annotated with `@Id`.
3. Provide `@SafeVarargs` method `saveAll(T... entities)` and bounded generic calculation methods.

### How to Compile & Run
```bash
javac MiniBank13.java
java MiniBank13
```
