/**
 * Practical Lab-06: Practical 2
 * Problem: Notification system with functional interfaces, lambdas, and marker interface.
 * Student Name: Ayush Chodvadiya
 * Student ID: 25cs005
 */

import java.util.Scanner;

@FunctionalInterface
interface Notifier {
    void send(String msg);
}

// Marker interface indicating urgent message delivery
interface Urgent {}

class UrgentEmail implements Notifier, Urgent {
    @Override
    public void send(String msg) {
        System.out.println("URGENT EMAIL: " + msg);
    }
}

class UrgentSMS implements Notifier, Urgent {
    @Override
    public void send(String msg) {
        System.out.println("URGENT SMS: " + msg);
    }
}

public class NotificationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter notificationmsg: ");
        String msg = sc.nextLine();

        Notifier email = text -> System.out.println("EMAIL: " + text);
        Notifier sms = text -> System.out.println("SMS: " + text);
        Notifier urgentEmail = new UrgentEmail();
        Notifier urgentSMS = new UrgentSMS();

        Notifier[] senders = {
            email, sms, urgentEmail, urgentSMS
        };

        System.out.println("\nNormal Broadcast");
        for (Notifier sender : senders) {
            sender.send(msg);
        }

        System.out.println("\nUrgent Broadcast");
        for (Notifier sender : senders) {
            if (sender instanceof Urgent) {
                sender.send(msg);
                sender.send(msg);
            }
        }

        sc.close();
    }
}

