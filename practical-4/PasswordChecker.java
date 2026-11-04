/**
 * Practical Lab-04: Practical 1
 * Problem: Password Strength evaluation using Regular Expressions.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

public class PasswordChecker {
    public static boolean hasMinLength(String pw) {
        return (pw.length() >= 8);
    }

    public static boolean hasUpperCase(String pw) {
        return pw.matches(".*[A-Z].*");
    }

    public static boolean hasDigit(String pw) {
        return pw.matches(".*[0-9].*");
    }

    public static boolean hasSpecialCharacter(String pw) {
        return pw.matches(".*[%#@!^&*/:<>?].*");
    }

    public static String strength(String pw) {
        int count = 0;
        if (hasMinLength(pw)) count++;
        if (hasUpperCase(pw)) count++;
        if (hasDigit(pw)) count++;
        if (hasSpecialCharacter(pw)) count++;

        if (count <= 1) {
            return "Weak";
        } else if (count <= 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    public static void main(String[] args) {
        Driver3.main(args);
    }
}

class Driver3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        if (PasswordChecker.hasMinLength(password)) {
            System.out.println("Minimum length: Passed");
        } else {
            System.out.println("Minimum length: Failed");
        }

        if (PasswordChecker.hasUpperCase(password)) {
            System.out.println("Uppercase letter: Passed");
        } else {
            System.out.println("Uppercase letter: Failed");
        }

        if (PasswordChecker.hasDigit(password)) {
            System.out.println("Digit: Passed");
        } else {
            System.out.println("Digit: Failed");
        }

        if (PasswordChecker.hasSpecialCharacter(password)) {
            System.out.println("Special character: Passed");
        } else {
            System.out.println("Special character: Failed");
        }

        System.out.println("Password Strength: " + PasswordChecker.strength(password));
        sc.close();
    }
}

