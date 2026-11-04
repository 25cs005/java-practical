# Practical Lab-10

**Student Name:** Ayush Chodvadiya  
**Student ID:** 25cs005  

---

## Practical 1: Thread Pool Runner (`ThreadPoolRunner.java`)

### Problem Description
1. Create a fixed thread pool of 3 worker threads using `Executors.newFixedThreadPool(3)`.
2. Prompt the user for the number of tasks.
3. Submit each task as a `Runnable` lambda:
   - Prints task ID and the name of the thread executing it (`Thread.currentThread().getName()`).
   - Sleeps for 500ms to simulate work.
4. Shut down the pool using `shutdown()` and block until completion with `awaitTermination(1, TimeUnit.MINUTES)`.
5. Observe how worker threads are effectively reused across multiple tasks.

### How to Compile & Run
```bash
javac ThreadPoolRunner.java
java ThreadPoolRunner
```

### Expected Output
```text
Enter number of tasks: 5
Task 1 running on pool-1-thread-1
Task 3 running on pool-1-thread-3
Task 2 running on pool-1-thread-2
Task 5 running on pool-1-thread-2
Task 4 running on pool-1-thread-1
All tasks completed.
```

---

## Practical 2: Producer-Consumer Simulation (`ProducerConsumer.java`)

### Problem Description
1. Create a `Buffer` class with a fixed capacity (e.g. 3) using a `Queue<Integer>`.
2. Implement synchronized methods:
   - `produce(int value)`: waits (`wait()`) while the buffer is full, adds item, and notifies consumers (`notifyAll()`).
   - `consume()`: waits (`wait()`) while the buffer is empty, removes item, and notifies producers (`notifyAll()`).
3. In `main`:
   - Start one producer thread and one consumer thread.
   - Synchronize completion with `.join()`.
   - Confirm all items are produced and consumed in orderly fashion without deadlock or data loss.

### How to Compile & Run
```bash
javac ProducerConsumer.java
java ProducerConsumer
```

### Expected Output
```text
Enter number of items: 3
Produced: 1
Produced: 2
Produced: 3
Consumed: 1
Consumed: 2
Consumed: 3
All items produced and consumed.
```
