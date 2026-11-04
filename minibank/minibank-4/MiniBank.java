/**
 * MiniBank Project - Lab 4: Regular Expressions, Command Parser & Statement Formatter
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

record BankInfo(String name, String branch) {
}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

enum TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER
}

record Command(TransactionType type, String accountNumber, long amount) {
}

class Validator {
    private static final Pattern MOBILE = Pattern.compile("[6-9][0-9]{9}");
    private static final Pattern EMAIL = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
    private static final Pattern PAN = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]");
    private static final Pattern IFSC = Pattern.compile("[A-Z]{4}0[A-Z0-9]{6}");
    private static final Pattern AMOUNT = Pattern.compile("[1-9][0-9]*");

    public static boolean isValidMobile(String mobile) {
        return mobile != null && MOBILE.matcher(mobile).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    public static boolean isValidPan(String pan) {
        return pan != null && PAN.matcher(pan).matches();
    }

    public static boolean isValidIfsc(String ifsc) {
        return ifsc != null && IFSC.matcher(ifsc).matches();
    }

    public static boolean isValidAmount(String amount) {
        return amount != null && AMOUNT.matcher(amount).matches();
    }
}

class CommandParser {
    public static Command parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid command format");
        }

        TransactionType type;
        try {
            type = TransactionType.valueOf(parts[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        String accountNumber = parts[1];
        if (accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }

        if (!Validator.isValidAmount(parts[2])) {
            throw new IllegalArgumentException("Invalid amount");
        }

        long amount;
        try {
            amount = Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount is too large");
        }

        return new Command(type, accountNumber, amount);
    }
}

class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }
}

class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("========== ACCOUNT STATEMENT ==========\n");
        sb.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        sb.append("Account Holder: ").append(account.getAccountHolder()).append("\n");
        sb.append("Balance: ").append(account.getBalance()).append("\n");
        sb.append("=======================================");
        return sb.toString();
    }
}

public class MiniBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankInfo bank = new BankInfo("MiniBank", "Main Branch");

        System.out.println("==================================");
        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch : " + bank.branch());
        System.out.println("==================================");

        System.out.println("\n------ VALIDATOR TESTS ------");
        System.out.println("Mobile 9876543210: " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile 12345: " + Validator.isValidMobile("12345"));
        System.out.println("Email abc@xyz.com: " + Validator.isValidEmail("abc@xyz.com"));
        System.out.println("Email abcxyz.com: " + Validator.isValidEmail("abcxyz.com"));
        System.out.println("PAN ABCDE1234F: " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("PAN ABC123: " + Validator.isValidPan("ABC123"));
        System.out.println("IFSC SBIN0001234: " + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("IFSC SBI123: " + Validator.isValidIfsc("SBI123"));
        System.out.println("Amount 500: " + Validator.isValidAmount("500"));
        System.out.println("Amount -10: " + Validator.isValidAmount("-10"));

        System.out.println("\n------ COMMAND PARSER ------");
        Command command = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Type: " + command.type());
        System.out.println("Account Number: " + command.accountNumber());
        System.out.println("Amount: " + command.amount());

        Account account = new Account("AC0001", "Anushka", 5000);
        System.out.println("\n" + StatementFormatter.buildStatement(account));

        int choice;
        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            String message = switch (choice) {
                case 1 -> "Open Account - To be implemented in a later lab.";
                case 2 -> "Deposit - To be implemented in a later lab.";
                case 3 -> "Withdraw - To be implemented in a later lab.";
                case 4 -> "Transfer - To be implemented in a later lab.";
                case 5 -> "Thank you for using MiniBank!";
                default -> "Invalid choice! Please try again.";
            };
            System.out.println(message);
        } while (choice != 5);

        sc.close();
    }
}
