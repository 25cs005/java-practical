/**
 * Practical Lab-10: Practical 1
 * Problem: Thread Pool Runner using ExecutorService with fixed thread pool.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of tasks: ");
        int n = sc.nextInt();

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= n; i++) {
            int id = i;
            pool.submit(() -> {
                System.out.println("Task " + id + " running on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("All tasks completed.");
        sc.close();
    }
}

