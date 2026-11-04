/**
 * Practical Lab-09: Practical 2
 * Problem: Seat Booking Race condition and synchronization prevention.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

class Booking {
    int seatsLeft = 5;
    int booked = 0;

    void bookWithoutSync() {
        if (seatsLeft > 0) {
            Thread.yield();
            seatsLeft--;
            booked++;
            System.out.println("Seat booked successfully");
        } else {
            System.out.println("No seats available");
        }
    }

    synchronized void book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;
            System.out.println("Seat booked successfully");
        } else {
            System.out.println("No seats available");
        }
    }
}

public class SeatBookingRace {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Without Synchronization");
        System.out.println("2. With Synchronization");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        Booking booking = new Booking();
        Thread[] users = new Thread[10];

        for (int i = 0; i < 10; i++) {
            users[i] = new Thread(() -> {
                if (choice == 1) {
                    booking.bookWithoutSync();
                } else {
                    booking.book();
                }
            });
            users[i].start();
        }

        for (Thread t : users) {
            t.join();
        }

        System.out.println("\nSuccessful bookings: " + booking.booked);
        System.out.println("Seats left: " + booking.seatsLeft);
        sc.close();
    }
}

