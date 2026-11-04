/**
 * Practical Lab-01: Practical 1
 * Problem: Vending Machine Simulation using Enum and Switch Expression.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

public class VendingMachine {
    // Enum representing valid coin denominations
    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {
        final int snackPrice = 15;
        int total = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert the coins (ONE, TWO, FIVE, TEN): ");
        while (total < snackPrice) {
            String input = sc.next().toUpperCase();
            Coin coin;
            try {
                coin = Coin.valueOf(input);
            } catch (Exception e) {
                System.out.println("Invalid coin. Please insert a valid coin.");
                continue;
            }

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total += value;
            System.out.println("Total so far: " + total);
        }

        System.out.println("Paid. Change: " + (total - snackPrice));
        sc.close();
    }
}

