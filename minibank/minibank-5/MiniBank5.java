/**
 * MiniBank Project - Lab 5: Polymorphism and Account Hierarchy
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

record BankInfo(String name, String branch) {
}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

abstract class Account {
    protected String accountNumber;
    protected String holderName;
    protected long balance;

    Account(String accountNumber, String holderName, long balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    abstract double interestRate();
    abstract boolean canWithdraw(long amount);

    void showAccount() {
        System.out.println("Account: " + accountNumber);
        System.out.println("Holder: " + holderName);
        System.out.println("Balance: " + balance);
    }
}

class SavingsAccount extends Account {
    long minBalance;

    SavingsAccount(String accountNumber, String holderName, long balance, long minBalance) {
        super(accountNumber, holderName, balance);
        this.minBalance = minBalance;
    }

    double interestRate() {
        return 4.0;
    }

    boolean canWithdraw(long amount) {
        return balance - amount >= minBalance;
    }
}

class CurrentAccount extends Account {
    long overdraftLimit;

    CurrentAccount(String accountNumber, String holderName, long balance, long overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    double interestRate() {
        return 0.0;
    }

    boolean canWithdraw(long amount) {
        return balance - amount >= -overdraftLimit;
    }
}

class FixedDepositAccount extends Account {
    FixedDepositAccount(String accountNumber, String holderName, long balance) {
        super(accountNumber, holderName, balance);
    }

    double interestRate() {
        return 7.0;
    }

    boolean canWithdraw(long amount) {
        return false;
    }
}

public class MiniBank5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankInfo bank = new BankInfo("MiniBank", "Main Branch");

        System.out.println("==================================");
        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch : " + bank.branch());
        System.out.println("==================================");

        Account[] accounts = {
            new SavingsAccount("S101", "Anushka", 10000, 2000),
            new CurrentAccount("C101", "Shrusti", 5000, 3000),
            new FixedDepositAccount("F101", "Bhavya", 20000)
        };

        System.out.println("\nAccount Details");
        for (Account account : accounts) {
            account.showAccount();
            System.out.println("Interest Rate: " + account.interestRate() + "%");
            if (account instanceof SavingsAccount) {
                System.out.println("Type: Savings Account");
            }
            System.out.println();
        }

        System.out.println("Withdrawal Test");
        for (Account account : accounts) {
            System.out.println(account.accountNumber);
            if (account.canWithdraw(3000)) {
                System.out.println("Withdrawal of 3000 is allowed");
            } else {
                System.out.println("Withdrawal of 3000 is not allowed");
            }
            System.out.println();
        }

        int choice;
        do {
            System.out.println("------ MENU ------");
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
                case 5 -> "Thank you for using minibank5!";
                default -> "Invalid choice! Please try again.";
            };
            System.out.println(message);
        } while (choice != 5);

        sc.close();
    }
}

// Alias class matching filename minibank5.java
class minibank5 {
    public static void main(String[] args) {
        MiniBank5.main(args);
    }
}
