/**
 * Practical Lab-08: Practical 1
 * Problem: Guarded Calculator with custom DivideByZeroException and finally block.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempt = 0;
        boolean success = false;

        while (!success) {
            attempt++;
            try {
                System.out.print("Enter first number: ");
                double n1 = Double.parseDouble(sc.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                String op = sc.nextLine().trim();

                System.out.print("Enter second number: ");
                double n2 = Double.parseDouble(sc.nextLine());

                // Check division by zero
                if (n2 == 0 && op.equals("/")) {
                    throw new DivideByZeroException("Division by zero is not allowed");
                }

                double result;
                switch (op) {
                    case "+":
                        result = n1 + n2;
                        break;
                    case "-":
                        result = n1 - n2;
                        break;
                    case "*":
                        result = n1 * n2;
                        break;
                    case "/":
                        result = n1 / n2;
                        break;
                    default:
                        System.out.println("Invalid operator");
                        continue;
                }

                System.out.println("Result: " + result);
                success = true;
            } catch (DivideByZeroException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            } finally {
                System.out.println("Attempt " + attempt);
                System.out.println();
            }
        }

        sc.close();
    }
}

