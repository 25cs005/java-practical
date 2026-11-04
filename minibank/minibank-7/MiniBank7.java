/**
 * MiniBank Project - Lab 7: Custom Annotations & Reflection Validator
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import static java.lang.System.out;

record BankInfo(String name, String branch) {
}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Positive {
    String message() default "must be > 0";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
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
    @Id
    @MaxLength(20)
    protected String accountNumber;

    @MaxLength(50)
    protected String holderName;

    @Positive(message = "Balance must be greater than zero")
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

    @Override
    public String toString() {
        return "Account{accountNumber='" + accountNumber
                + "', holderName='" + holderName
                + "', balance=" + balance + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account other = (Account) obj;
        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
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

class AnnotationValidator {
    public static String[] validate(Object obj) {
        java.util.List<String> errors = new java.util.ArrayList<>();
        if (obj == null) {
            errors.add("Object cannot be null");
            return errors.toArray(new String[0]);
        }
        Class<?> currentClass = obj.getClass();
        while (currentClass != null && currentClass != Object.class) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (field.isAnnotationPresent(Positive.class)) {
                        Positive annotation = field.getAnnotation(Positive.class);
                        if (value instanceof Number number
                                && number.doubleValue() <= 0) {
                            errors.add(field.getName() + ": "
                                    + annotation.message());
                        }
                    }
                    if (field.isAnnotationPresent(MaxLength.class)) {
                        MaxLength annotation = field.getAnnotation(MaxLength.class);
                        if (value instanceof String text
                                && text.length() > annotation.value()) {
                            errors.add(field.getName()
                                    + " length must not exceed "
                                    + annotation.value() + " characters");
                        }
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Unable to validate field: " + field.getName());
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return errors.toArray(new String[0]);
    }
}

public class MiniBank7 {
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
        WithdrawRule lambdaRule
                = (account, amount) -> account.canWithdraw(amount);
        for (Account account : accounts) {
            out.println(account.accountNumber + ": "
                    + (lambdaRule.allow(account, 3000)
                    ? "Withdrawal allowed"
                    : "Withdrawal not allowed"));
        }

        out.println("\nANNOTATION VALIDATION");
        Account invalidAccount
                = new SavingsAccount("S102", "David", -5000, 1000);
        String[] errors = AnnotationValidator.validate(invalidAccount);
        if (errors.length == 0) {
            out.println("Account validation successful.");
        } else {
            for (String error : errors) {
                out.println("Validation Error: " + error);
            }
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
                case 5 -> "Thank you for using MiniBank7!";
                default -> "Invalid choice! Please try again.";
            };
            out.println(message);
        } while (choice != 5);

        sc.close();
    }
}
