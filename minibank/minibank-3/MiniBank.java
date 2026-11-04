/**
 * MiniBank Project - Lab 3: Object Overriding, Nested Address, and Cloning
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Objects;
import java.util.Scanner;

record BankInfo(String name, String branch) {}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

class Customer implements Cloneable {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;
    private static long customerCounter = 101;

    // Static nested class Address
    public static class Address {
        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }

        @Override
        public String toString() {
            return line + ", " + city + " - " + pincode;
        }
    }

    private static String generateCustomerId() {
        return "CUST" + customerCounter++;
    }

    public Customer(String name, String email, String mobile, Address address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.customerId = generateCustomerId();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getCustomerId() { return customerId; }
    public Address getAddress() { return address; }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Customer(this.name, this.email, this.mobile, this.address);
        }
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;
    private static int accountCounter = 1;

    private static String generateAccountNumber() {
        return String.format("AC%04d", accountCounter++);
    }

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }

    @Override
    public String toString() {
        return "Account[" + accountNumber + " | Owner: " + ownerName + " | Balance: Rs" + balance + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Account other) {
            return Objects.equals(this.accountNumber, other.accountNumber);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
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
