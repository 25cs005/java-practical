/**
 * MiniBank Project - Lab 11: File Persistence, Append-only Logs & NIO Reporting
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MiniBank11 {
    static class Account implements Serializable {
        private static final long serialVersionUID = 1L;
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
        public String toString() {
            return accountNumber + " | " + holderName + " | " + balance;
        }
    }

    static class SavingsAccount extends Account {
        private static final long serialVersionUID = 1L;

        SavingsAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class CurrentAccount extends Account {
        private static final long serialVersionUID = 1L;

        CurrentAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class FixedDepositAccount extends Account {
        private static final long serialVersionUID = 1L;

        FixedDepositAccount(String number, String name, double balance) {
            super(number, name, balance);
        }
    }

    static class StatePersister {
        static void save(Account[] accounts, Path path) throws IOException {
            Path parent = path.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(path))) {
                out.writeObject(accounts);
            }
        }

        static Account[] load(Path path) throws IOException, ClassNotFoundException {
            try (ObjectInputStream in = new ObjectInputStream(
                    Files.newInputStream(path))) {
                return (Account[]) in.readObject();
            }
        }
    }

    static class TransactionLog {
        static void append(Path path, String accountNumber, String type,
                double amount) throws IOException {
            Path parent = path.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            String entry = accountNumber + "," + type + "," + amount
                    + System.lineSeparator();
            Files.write(path, entry.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    static class ReportGenerator {
        static void generate(Path directory, Path output) throws IOException {
            Files.createDirectories(directory);
            long deposits = 0;
            long withdrawals = 0;
            StringBuilder report = new StringBuilder();

            try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
                for (Path file : files) {
                    if (!Files.isRegularFile(file)) {
                        continue;
                    }
                    try (BufferedReader reader = Files.newBufferedReader(file)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts.length != 3) {
                                continue;
                            }
                            try {
                                long amount = (long) Double.parseDouble(parts[2]);
                                if (parts[1].equalsIgnoreCase("DEPOSIT")) {
                                    deposits += amount;
                                } else if (parts[1].equalsIgnoreCase("WITHDRAW")) {
                                    withdrawals += amount;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                    BasicFileAttributes attributes
                            = Files.readAttributes(file, BasicFileAttributes.class);
                    report.append(file.getFileName())
                            .append(" | Size: ").append(attributes.size())
                            .append(" bytes | Modified: ")
                            .append(attributes.lastModifiedTime()).append("\n");
                }
            }

            report.append("Total Deposits: ").append(deposits).append("\n");
            report.append("Total Withdrawals: ").append(withdrawals).append("\n");
            Files.write(output, report.toString().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println(report);
        }
    }

    public static void main(String[] args) {
        Path dataFile = Paths.get("bank-data", "accounts.ser");
        Path logDirectory = Paths.get("bank-data", "logs");
        Path transactionFile = logDirectory.resolve("transactions.txt");
        Path reportFile = Paths.get("bank-data", "report.txt");

        try {
            Account[] accounts;
            if (Files.exists(dataFile)) {
                accounts = StatePersister.load(dataFile);
            } else {
                accounts = new Account[]{
                    new SavingsAccount("S101", "Anushka", 10000),
                    new CurrentAccount("C101", "Riddhi", 15000),
                    new FixedDepositAccount("F101", "Aarav", 20000)
                };
            }

            StatePersister.save(accounts, dataFile);
            accounts = StatePersister.load(dataFile);
            for (Account account : accounts) {
                System.out.println(account);
            }

            accounts[0].deposit(2000);
            TransactionLog.append(transactionFile, accounts[0].accountNumber,
                    "DEPOSIT", 2000);
            accounts[0].withdraw(500);
            TransactionLog.append(transactionFile, accounts[0].accountNumber,
                    "WITHDRAW", 500);

            StatePersister.save(accounts, dataFile);
            ReportGenerator.generate(logDirectory, reportFile);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Transaction error: " + e.getMessage());
        }
    }
}
