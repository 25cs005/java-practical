/**
 * Practical Lab-09: Practical 1
 * Problem: Counter Race demonstration showing race conditions with and without synchronization.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }

    void incrementWithoutSync() {
        count++;
    }
}

public class CounterRace {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of threads: ");
        int th = sc.nextInt();
        System.out.print("Enter increments per thread: ");
        int tm = sc.nextInt();
        System.out.println("1. Without Synchronization");
        System.out.println("2. With Synchronization");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        Counter counter = new Counter();
        Thread[] threads = new Thread[th];

        for (int i = 0; i < th; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < tm; j++) {
                    if (choice == 1) {
                        counter.incrementWithoutSync();
                    } else {
                        counter.increment();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Expected count: " + (th * tm));
        System.out.println("Actual count: " + counter.count);
        sc.close();
    }
}

