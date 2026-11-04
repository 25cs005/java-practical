/**
 * Practical Lab-01: Practical 2
 * Problem: Toll Booth Simulation using Java Records and Switch Expression.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

record Vehicle(String number, String type) {}

public class TollBooth {
    public static void main(String[] args) {
        int total = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter vehicle number (or done): ");
            String number = sc.nextLine();
            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.nextLine().toLowerCase();
            Vehicle vehicle = new Vehicle(number, type);

            try {
                int value = switch (vehicle.type()) {
                    case "bike" -> {
                        bike++;
                        yield 20;
                    }
                    case "car" -> {
                        car++;
                        yield 50;
                    }
                    case "truck" -> {
                        truck++;
                        yield 150;
                    }
                    default -> throw new Exception("Invalid vehicle type");
                };
                total += value;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Total toll: " + total);
        if (bike >= car && bike >= truck) {
            System.out.println("Most frequent: bike");
        } else if (car >= bike && car >= truck) {
            System.out.println("Most frequent: car");
        } else {
            System.out.println("Most frequent: truck");
        }

        sc.close();
    }
}
