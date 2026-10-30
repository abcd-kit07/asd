import java.util.Scanner;

interface Payment {
    void makePayment();
    void displayPaymentDetails();
}

class CreditCardPayment implements Payment {
    double amount;
    String cardNumber, holderName;

    CreditCardPayment(double amount, String cardNumber, String holderName) {
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.holderName = holderName;
    }

    public void makePayment() {
        System.out.println("Processing credit card payment of Rs " + amount + " ...");
        System.out.println("Payment successful via Credit Card.");
    }

    public void displayPaymentDetails() {
        String masked = "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("\n----- TRANSACTION SUMMARY -----");
        System.out.println("Mode        : Credit Card");
        System.out.println("Card Holder : " + holderName);
        System.out.println("Card Number : " + masked);
        System.out.println("Amount Paid : Rs " + amount);
        System.out.println("Status      : SUCCESS");
    }
}

class UPIPayment implements Payment {
    double amount;
    String upiId, name;

    UPIPayment(double amount, String upiId, String name) {
        this.amount = amount;
        this.upiId = upiId;
        this.name = name;
    }

    public void makePayment() {
        System.out.println("Sending UPI request to " + upiId + " for Rs " + amount + " ...");
        System.out.println("Payment successful via UPI.");
    }

    public void displayPaymentDetails() {
        System.out.println("\n----- TRANSACTION SUMMARY -----");
        System.out.println("Mode        : UPI");
        System.out.println("Name        : " + name);
        System.out.println("UPI ID      : " + upiId);
        System.out.println("Amount Paid : Rs " + amount);
        System.out.println("Status      : SUCCESS");
    }
}

public class Q10_PaymentInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter payment amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        System.out.print("Choose payment mode (1 - Credit Card, 2 - UPI): ");
        int choice = Integer.parseInt(sc.nextLine());

        Payment payment;
        if (choice == 1) {
            System.out.print("Enter card holder name: ");
            String holder = sc.nextLine();
            System.out.print("Enter 16-digit card number: ");
            String card = sc.nextLine();
            payment = new CreditCardPayment(amount, card, holder);
        } else if (choice == 2) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter UPI ID: ");
            String upi = sc.nextLine();
            payment = new UPIPayment(amount, upi, name);
        } else {
            System.out.println("Invalid payment mode!");
            sc.close();
            return;
        }

        payment.makePayment();
        payment.displayPaymentDetails();
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
2500
1
Priya Sharma
1234567812345678
[Output]
Enter payment amount: Choose payment mode (1 - Credit Card, 2 - UPI): Enter card holder name: Enter 16-digit card number: Processing credit card payment of Rs 2500.0 ...
Payment successful via Credit Card.

----- TRANSACTION SUMMARY -----
Mode        : Credit Card
Card Holder : Priya Sharma
Card Number : XXXX-XXXX-XXXX-5678
Amount Paid : Rs 2500.0
Status      : SUCCESS

--- Sample run 2 ---
[Input]
1800
2
Ravi Kumar
ravi@oksbi
[Output]
Enter payment amount: Choose payment mode (1 - Credit Card, 2 - UPI): Enter name: Enter UPI ID: Sending UPI request to ravi@oksbi for Rs 1800.0 ...
Payment successful via UPI.

----- TRANSACTION SUMMARY -----
Mode        : UPI
Name        : Ravi Kumar
UPI ID      : ravi@oksbi
Amount Paid : Rs 1800.0
Status      : SUCCESS

===============================================================
*/
