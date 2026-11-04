/**
 * MiniBank Project - Lab 12: Java Collections Framework, Sorting & Concurrent Collections
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.*;
import java.util.concurrent.*;

public class MiniBank12 {
    static class Account implements Comparable<Account> {
        String accountNumber;
        String holderName;
        double balance;

        Account(String accountNumber, String holderName, double balance) {
            this.accountNumber = accountNumber;
            this.holderName = holderName;
            this.balance = balance;
        }

        synchronized void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
            balance += amount;
        }

        synchronized void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
            if (balance < amount) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            balance -= amount;
        }

        @Override
        public int compareTo(Account other) {
            return accountNumber.compareTo(other.accountNumber);
        }

        @Override
        public String toString() {
            return accountNumber + " | " + holderName + " | " + balance;
        }
    }

    static class AccountStore {
        private final HashMap<String, Account> accounts = new HashMap<>();
        private final ArrayList<String> history = new ArrayList<>();

        synchronized void add(Account account) {
            if (accounts.containsKey(account.accountNumber)) {
                throw new IllegalArgumentException("Account already exists");
            }
            accounts.put(account.accountNumber, account);
            history.add("Created account " + account.accountNumber);
        }

        synchronized Account get(String accountNumber) {
            return accounts.get(accountNumber);
        }

        synchronized ArrayList<Account> getAll() {
            return new ArrayList<>(accounts.values());
        }

        synchronized ArrayList<String> getHistory() {
            return new ArrayList<>(history);
        }

        synchronized void addHistory(String entry) {
            history.add(entry);
        }

        synchronized ArrayList<Account> sortedByBalance() {
            ArrayList<Account> result = new ArrayList<>(accounts.values());
            result.sort(Comparator.comparingDouble(a -> a.balance));
            return result;
        }
    }

    static class TransactionTask implements Runnable {
        private final Account account;
        private final double amount;
        private final ConcurrentHashMap<String, Account> accounts;

        TransactionTask(Account account, double amount,
                ConcurrentHashMap<String, Account> accounts) {
            this.account = account;
            this.amount = amount;
            this.accounts = accounts;
        }

        @Override
        public void run() {
            try {
                account.deposit(amount);
                accounts.put(account.accountNumber, account);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static class TransactionProcessor {
        private final ConcurrentHashMap<String, Account> accounts
                = new ConcurrentHashMap<>();
        private final ExecutorService executor
                = Executors.newFixedThreadPool(3);

        void addAccount(Account account) {
            accounts.put(account.accountNumber, account);
        }

        void submit(Account account, double amount) {
            executor.submit(new TransactionTask(account, amount, accounts));
        }

        void finish() {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        ConcurrentHashMap<String, Account> getAccounts() {
            return accounts;
        }
    }

    public static void main(String[] args) {
        AccountStore store = new AccountStore();
        store.add(new Account("A103", "Shrusti", 15000));
        store.add(new Account("A101", "Anushka", 10000));
        store.add(new Account("A102", "Bhavya", 20000));

        System.out.println("Accounts sorted by account number:");
        ArrayList<Account> byNumber = store.getAll();
        Collections.sort(byNumber);
        for (Account account : byNumber) {
            System.out.println(account);
        }

        System.out.println("\nAccounts sorted by balance:");
        for (Account account : store.sortedByBalance()) {
            System.out.println(account);
        }

        TransactionProcessor processor = new TransactionProcessor();
        for (Account account : store.getAll()) {
            processor.addAccount(account);
        }

        Account target = store.get("A101");
        for (int i = 0; i < 10; i++) {
            processor.submit(target, 100);
        }
        processor.finish();

        System.out.println("\nAfter concurrent deposits:");
        System.out.println(processor.getAccounts().get("A101"));

        store.addHistory("Deposited 1000 into A101");
        System.out.println("\nTransaction History:");
        for (String entry : store.getHistory()) {
            System.out.println(entry);
        }
    }
}
