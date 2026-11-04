/**
 * MiniBank Project - Lab 10: Thread Pools, Producer-Consumer & Deadlock Prevention
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import static java.lang.System.out;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

record BankInfo(String name, String branch) {
}

enum MenuOption {
    OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Positive {
    String message() default "must be greater than zero";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class BankException extends Exception {
    public BankException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends BankException {
    private final long shortfall;

    public InsufficientFundsException(String message, long shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public long getShortfall() {
        return shortfall;
    }
}

class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class InvalidAmountException extends BankException {
    public InvalidAmountException(String message) {
        super(message);
    }
}

interface Transactable {
    void deposit(long amount) throws InvalidAmountException;
    boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException;
}

interface InterestBearing {
    default double yearlyInterest() {
        Account account = (Account) this;
        return account.getBalance() * account.interestRate() / 100.0;
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
    public synchronized void deposit(long amount)
            throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }
        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException(
                    "Insufficient funds in account " + accountNumber,
                    Math.max(0, amount - balance));
        }
        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws BankException {
        if (to == null) {
            throw new AccountNotFoundException(
                    "Destination account was not found.");
        }
        withdraw(amount);
        to.deposit(amount);
        out.println("Transfer successful.");
    }

    synchronized long getBalance() {
        return balance;
    }

    void showAccount() {
        out.println("Account Number: " + accountNumber);
        out.println("Holder Name: " + holderName);
        out.println("Balance: " + getBalance());
    }

    @Override
    public String toString() {
        return "Account{accountNumber='" + accountNumber
                + "', holderName='" + holderName
                + "', balance=" + getBalance() + "}";
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
        List<String> errors = new ArrayList<>();
        if (obj == null) {
            errors.add("Object cannot be null.");
            return errors.toArray(new String[0]);
        }
        Class<?> currentClass = obj.getClass();
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (field.isAnnotationPresent(Positive.class)) {
                        Positive annotation
                                = field.getAnnotation(Positive.class);
                        if (value instanceof Number number
                                && number.doubleValue() <= 0) {
                            errors.add(field.getName() + ": "
                                    + annotation.message());
                        }
                    }
                    if (field.isAnnotationPresent(MaxLength.class)) {
                        MaxLength annotation
                                = field.getAnnotation(MaxLength.class);
                        if (value instanceof String text
                                && text.length() > annotation.value()) {
                            errors.add(field.getName()
                                    + " exceeds maximum length "
                                    + annotation.value());
                        }
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Unable to validate " + field.getName());
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return errors.toArray(new String[0]);
    }
}

class BankSession implements AutoCloseable {
    BankSession() {
        out.println("Bank session opened.");
    }

    @Override
    public void close() {
        out.println("Bank session closed.");
    }
}

class AccountWorker implements Runnable {
    private Account account;
    private int times;
    private long amount;

    AccountWorker(Account account, int times, long amount) {
        this.account = account;
        this.times = times;
        this.amount = amount;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        out.println(name + " started.");
        for (int i = 0; i < times; i++) {
            try {
                account.deposit(amount);
            } catch (InvalidAmountException e) {
                out.println(e.getMessage());
            }
        }
        out.println(name + " finished.");
    }
}

class TransactionProcessor {
    private final ExecutorService executor
            = Executors.newFixedThreadPool(4);

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                executor.awaitTermination(10, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

class TransactionTask implements Runnable {
    private final Account account;
    private final long amount;
    private final boolean deposit;

    TransactionTask(Account account, long amount, boolean deposit) {
        this.account = account;
        this.amount = amount;
        this.deposit = deposit;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            if (deposit) {
                account.deposit(amount);
                out.println(name + " deposited " + amount
                        + " into " + account.accountNumber);
            } else {
                account.withdraw(amount);
                out.println(name + " withdrew " + amount
                        + " from " + account.accountNumber);
            }
        } catch (BankException e) {
            out.println(name + " transaction failed: " + e.getMessage());
        }
    }
}

class TransactionBuffer {
    private final TransactionTask[] buffer;
    private int in;
    private int outIndex;
    private int count;
    private boolean finished;

    TransactionBuffer(int capacity) {
        buffer = new TransactionTask[capacity];
    }

    public synchronized void put(TransactionTask task)
            throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[in] = task;
        in = (in + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized TransactionTask take()
            throws InterruptedException {
        while (count == 0 && !finished) {
            wait();
        }
        if (count == 0 && finished) {
            return null;
        }
        TransactionTask task = buffer[outIndex];
        buffer[outIndex] = null;
        outIndex = (outIndex + 1) % buffer.length;
        count--;
        notifyAll();
        return task;
    }

    public synchronized void finish() {
        finished = true;
        notifyAll();
    }
}

class DeadlockTools {
    public static void transferUnsafe(
            Account from, Account to, long amount,
            CountDownLatch latch) {
        synchronized (from) {
            out.println(Thread.currentThread().getName()
                    + " locked " + from.accountNumber);
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            synchronized (to) {
                try {
                    from.withdraw(amount);
                    to.deposit(amount);
                } catch (BankException e) {
                    out.println("Transfer failed: " + e.getMessage());
                }
            }
        }
    }

    public static void transferSafely(
            Account from, Account to, long amount) {
        Account first;
        Account second;
        if (from.accountNumber.compareTo(to.accountNumber) < 0) {
            first = from;
            second = to;
        } else {
            first = to;
            second = from;
        }

        synchronized (first) {
            synchronized (second) {
                try {
                    from.withdraw(amount);
                    to.deposit(amount);
                    out.println(Thread.currentThread().getName()
                            + " transferred " + amount
                            + " from " + from.accountNumber
                            + " to " + to.accountNumber);
                } catch (BankException e) {
                    out.println("Transfer failed: " + e.getMessage());
                }
            }
        }
    }
}

public class MiniBank10 {
    public static void main(String[] args) {
        try (BankSession session = new BankSession(); Scanner sc = new Scanner(System.in)) {
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
                out.printf("Yearly Interest: %.2f%n", account.yearlyInterest());
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

            out.println("\nANNOTATION VALIDATION");
            Account invalidAccount = new SavingsAccount("S102", "David", -5000, 1000);
            for (String error : AnnotationValidator.validate(invalidAccount)) {
                out.println("Validation Error: " + error);
            }

            out.println("\nWITHDRAWAL RULE USING AN ANONYMOUS CLASS");
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
            WithdrawRule lambdaRule = (account, amount) -> account.canWithdraw(amount);
            for (Account account : accounts) {
                out.println(account.accountNumber + ": "
                        + (lambdaRule.allow(account, 3000)
                        ? "Withdrawal allowed"
                        : "Withdrawal not allowed"));
            }

            try {
                accounts[0].deposit(2000);
                accounts[0].withdraw(3000);
                accounts[0].withdraw(50000);
            } catch (InsufficientFundsException e) {
                out.println("Insufficient funds: " + e.getMessage());
                out.println("Shortfall: " + e.getShortfall());
            } catch (InvalidAmountException e) {
                out.println("Invalid amount: " + e.getMessage());
            }

            try {
                accounts[0].transfer(accounts[1], 1000);
            } catch (BankException e) {
                out.println("Transfer failed: " + e.getMessage());
            }

            out.println("\nTHREAD SYNCHRONIZATION TEST");
            Account sharedAccount = new SavingsAccount("S999", "Shared Account", 0, 0);
            int numberOfThreads = 10;
            int times = 1000;
            long amount = 100;

            Thread[] threads = new Thread[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++) {
                AccountWorker worker = new AccountWorker(sharedAccount, times, amount);
                threads[i] = new Thread(worker, "BankThread-" + (i + 1));
                threads[i].start();
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            long expectedBalance = (long) numberOfThreads * times * amount;
            out.println("Expected Balance: " + expectedBalance);
            out.println("Final Balance: " + sharedAccount.getBalance());

            out.println("\nTRANSACTION PROCESSOR TEST");
            Account batchAccount = new CurrentAccount("C201", "Batch Account", 10000, 5000);
            TransactionProcessor processor = new TransactionProcessor();
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0) {
                    processor.submit(new TransactionTask(batchAccount, 100, true));
                } else {
                    processor.submit(new TransactionTask(batchAccount, 50, false));
                }
            }
            processor.stop();
            out.println("Batch processing complete.");
            out.println("Batch account balance: " + batchAccount.getBalance());

            out.println("\nPRODUCER-CONSUMER TEST");
            Account bufferAccount = new CurrentAccount("C202", "Buffer Account", 5000, 3000);
            TransactionBuffer buffer = new TransactionBuffer(5);

            Thread producer = new Thread(() -> {
                try {
                    for (int i = 1; i <= 10; i++) {
                        boolean isDeposit = i % 2 == 0;
                        buffer.put(new TransactionTask(bufferAccount, 100, isDeposit));
                        out.println("Producer added transaction " + i);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    buffer.finish();
                    out.println("Producer finished.");
                }
            }, "TransactionProducer");

            Thread consumer = new Thread(() -> {
                try {
                    TransactionTask task;
                    while ((task = buffer.take()) != null) {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                out.println("Consumer finished.");
            }, "TransactionConsumer");

            producer.start();
            consumer.start();
            try {
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            out.println("Buffer account balance: " + bufferAccount.getBalance());

            out.println("\nDEADLOCK DEMONSTRATION");
            Account deadlockA = new CurrentAccount("A301", "Deadlock A", 5000, 1000);
            Account deadlockB = new CurrentAccount("B301", "Deadlock B", 5000, 1000);
            CountDownLatch latch = new CountDownLatch(2);

            Thread unsafeOne = new Thread(
                    () -> DeadlockTools.transferUnsafe(deadlockA, deadlockB, 100, latch),
                    "Unsafe-A-to-B");
            Thread unsafeTwo = new Thread(
                    () -> DeadlockTools.transferUnsafe(deadlockB, deadlockA, 100, latch),
                    "Unsafe-B-to-A");
            unsafeOne.setDaemon(true);
            unsafeTwo.setDaemon(true);
            unsafeOne.start();
            unsafeTwo.start();
            try {
                unsafeOne.join(1000);
                unsafeTwo.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (unsafeOne.isAlive() && unsafeTwo.isAlive()) {
                out.println("Deadlock detected: both threads are waiting.");
            } else {
                out.println("Unsafe transfer threads completed.");
            }

            out.println("\nDEADLOCK PREVENTION");
            Account safeA = new CurrentAccount("A401", "Safe A", 5000, 1000);
            Account safeB = new CurrentAccount("B401", "Safe B", 5000, 1000);

            Thread safeOne = new Thread(
                    () -> DeadlockTools.transferSafely(safeA, safeB, 100),
                    "Safe-A-to-B");
            Thread safeTwo = new Thread(
                    () -> DeadlockTools.transferSafely(safeB, safeA, 100),
                    "Safe-B-to-A");
            safeOne.start();
            safeTwo.start();
            try {
                safeOne.join();
                safeTwo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            out.println("Safe transfer threads completed.");
            out.println("Safe A balance: " + safeA.getBalance());
            out.println("Safe B balance: " + safeB.getBalance());
        }
    }
}
