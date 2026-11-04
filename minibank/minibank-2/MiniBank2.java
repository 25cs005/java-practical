/**
 * MiniBank Project - Lab 2: Encapsulation with Customer and Account
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

class Customer {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private static long customerCounter = 101;

    private static String generateCustomerId() {
        return "CUST" + customerCounter++;
    }

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
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

    public Account(String ownerName) {
        this(ownerName, 0);
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }
}

public class MiniBank2 {
    public static void main(String[] args) {
        Customer c1 = new Customer("Anushka", "anushka@gmail.com", "9876543210");
        Customer c2 = new Customer("Bhavya", "bhavya@gmail.com", "9876501234");
        Customer c3 = new Customer("Shrusti", "shrusti@gmail.com", "9876511111");

        Account[] accounts = new Account[3];
        accounts[0] = new Account(c1.getName(), 5000);
        accounts[1] = new Account(c2.getName());
        accounts[2] = new Account(c3.getName(), 10000);

        accounts[0].deposit(2000);
        accounts[0].withdraw(1000);
        accounts[1].deposit(3000);
        accounts[1].withdraw(500);
        accounts[2].withdraw(12000); // fails due to insufficient balance
        accounts[2].deposit(5000);

        System.out.println("========== MINI BANK ==========");
        for (Account acc : accounts) {
            System.out.println("Account Number : " + acc.getAccountNumber());
            System.out.println("Owner Name     : " + acc.getOwnerName());
            System.out.println("Balance        : Rs" + acc.getBalance());
            System.out.println("Active         : " + acc.isActive());
            System.out.println("------------------------------");
        }
    }
}
