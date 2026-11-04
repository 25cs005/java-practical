/**
 * Practical Lab-10: Practical 2
 * Problem: Producer-Consumer coordination using shared buffer, wait(), and notifyAll().
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int size;

    Buffer(int size) {
        this.size = size;
    }

    synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notifyAll();
    }

    synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int value = queue.remove();
        System.out.println("Consumed: " + value);
        notifyAll();
        return value;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Buffer buffer = new Buffer(3);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= n; i++) {
                    buffer.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= n; i++) {
                    buffer.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed.");
        sc.close();
    }
}

