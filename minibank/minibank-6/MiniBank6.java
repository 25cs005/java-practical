/**
 * MiniBank Project - Lab 6: Interfaces, Lambdas, Anonymous Classes & Marker Interface
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import static java.lang.System.out;
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

interface Transactable {
    void deposit(long amount);
    boolean withdraw(long amount);
}

interface InterestBearing {
    default double yearlyInterest() {
        return ((Account) this).balance
                * ((Account) this).interestRate() / 100.0;
    }
}

@FunctionalInterface
interface WithdrawRule {
    boolean allow(Account account, long amount);
}

interface Premium {
}

abstract class Account implements Transactable, InterestBearing {
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

    @Override
    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
            out.println("Deposit successful. New balance: " + balance);
        } else {
            out.println("Invalid deposit amount!");
        }
    }

    @Override
    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            out.println("Withdrawal successful. New balance: " + balance);
            return true;
        }
        out.println("Withdrawal denied!");
        return false;
    }

    void showAccount() {
        out.println("Account: " + accountNumber);
        out.println("Holder: " + holderName);
        out.println("Balance: " + balance);
    }
}

class SavingsAccount extends Account implements Premium {
    private long minBalance;

    SavingsAccount(String accountNumber, String holderName,
                   long balance, long minBalance) {
        super(accountNumber, holderName, balance);
        this.minBalance = minBalance;
    }

    @Override
    double interestRate() {
        return 4.0;
    }

    @Override
    boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= minBalance;
    }
}

class CurrentAccount extends Account {
    private long overdraftLimit;

    CurrentAccount(String accountNumber, String holderName,
                   long balance, long overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    double interestRate() {
        return 0.0;
    }

    @Override
    boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= -overdraftLimit;
    }
}

class FixedDepositAccount extends Account {
    FixedDepositAccount(String accountNumber, String holderName,
                        long balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    double interestRate() {
        return 7.0;
    }

    @Override
    boolean canWithdraw(long amount) {
        return false;
    }
}

public class MiniBank6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankInfo bank = new BankInfo("MiniBank", "Main Branch");

        out.println("==================================");
        out.println("Welcome to " + bank.name());
        out.println("Branch: " + bank.branch());
        out.println("==================================");

        Account[] accounts = {
            new SavingsAccount("S101", "Anushka", 10000, 2000),
            new CurrentAccount("C101", "Shrusti", 5000, 3000),
            new FixedDepositAccount("F101", "Bhavya", 20000)
        };

        out.println("\nACCOUNT DETAILS");
        for (Account account : accounts) {
            account.showAccount();
            out.println("Interest Rate: " + account.interestRate() + "%");
            out.printf("Yearly Interest: %.2f%n",
                       account.yearlyInterest());
            if (account instanceof SavingsAccount) {
                out.println("Type: Savings Account");
            } else if (account instanceof CurrentAccount) {
                out.println("Type: Current Account");
            } else if (account instanceof FixedDepositAccount) {
                out.println("Type: Fixed Deposit Account");
            }
            if (account instanceof Premium) {
                out.println("Premium Member: Yes");
            }
            out.println();
        }

        out.println("WITHDRAWAL RULE USING AN ANONYMOUS CLASS");
        WithdrawRule anonymousRule = new WithdrawRule() {
            @Override
            public boolean allow(Account account, long amount) {
                return account.canWithdraw(amount);
            }
        };

        for (Account account : accounts) {
            out.println(account.accountNumber + ": "
                    + (anonymousRule.allow(account, 3000)
                    ? "Withdrawal allowed"
                    : "Withdrawal not allowed"));
        }

        out.println("\nWITHDRAWAL RULE USING A LAMBDA");
        WithdrawRule lambdaRule =
                (account, amount) -> account.canWithdraw(amount);
        for (Account account : accounts) {
            out.println(account.accountNumber + ": "
                    + (lambdaRule.allow(account, 3000)
                    ? "Withdrawal allowed"
                    : "Withdrawal not allowed"));
        }

        int choice = 0;
        do {
            out.println("\n------ MENU ------");
            out.println("1. Open Account");
            out.println("2. Deposit");
            out.println("3. Withdraw");
            out.println("4. Transfer");
            out.println("5. Exit");
            out.print("Enter your choice: ");
            if (!sc.hasNextInt()) {
                out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            String message = switch (choice) {
                case 1 -> "Open Account - To be implemented in a later lab.";
                case 2 -> "Deposit - To be implemented in a later lab.";
                case 3 -> "Withdraw - To be implemented in a later lab.";
                case 4 -> "Transfer - To be implemented in a later lab.";
                case 5 -> "Thank you for using MiniBank6!";
                default -> "Invalid choice! Please try again.";
            };
            out.println(message);
        } while (choice != 5);

        sc.close();
    }
}
