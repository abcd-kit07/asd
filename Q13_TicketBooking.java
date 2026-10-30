import java.util.Scanner;

class TicketCounter {
    private int availableTickets;

    TicketCounter(int tickets) {
        availableTickets = tickets;
    }

    // synchronized: only one customer can book at a time
    public synchronized void bookTickets(String customer, int count) {
        if (count <= availableTickets) {
            availableTickets -= count;
            System.out.println(customer + " booked " + count + " ticket(s) -> SUCCESS. Remaining: " + availableTickets);
        } else {
            System.out.println(customer + " requested " + count + " ticket(s) -> FAILED (only " + availableTickets + " left).");
        }
    }

    public synchronized int getAvailableTickets() {
        return availableTickets;
    }
}

class Customer extends Thread {
    private final TicketCounter counter;
    private final int seats;

    Customer(String name, TicketCounter counter, int seats) {
        super(name);
        this.counter = counter;
        this.seats = seats;
    }

    @Override
    public void run() {
        counter.bookTickets(getName(), seats);
    }
}

public class Q13_TicketBooking {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total tickets available: ");
        int total = sc.nextInt();
        System.out.print("Enter number of customers: ");
        int n = sc.nextInt();

        TicketCounter counter = new TicketCounter(total);
        Customer[] customers = new Customer[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter tickets needed by Customer-" + (i + 1) + ": ");
            customers[i] = new Customer("Customer-" + (i + 1), counter, sc.nextInt());
        }

        System.out.println("\n--- Booking Started ---");
        for (Customer c : customers) c.start();
        for (Customer c : customers) c.join();

        System.out.println("--- Booking Closed ---");
        System.out.println("Tickets remaining: " + counter.getAvailableTickets());
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
5
4
2
1
3
2
[Output]
Enter total tickets available: Enter number of customers: Enter tickets needed by Customer-1: Enter tickets needed by Customer-2: Enter tickets needed by Customer-3: Enter tickets needed by Customer-4: 
--- Booking Started ---
Customer-1 booked 2 ticket(s) -> SUCCESS. Remaining: 3
Customer-3 booked 3 ticket(s) -> SUCCESS. Remaining: 0
Customer-4 requested 2 ticket(s) -> FAILED (only 0 left).
Customer-2 requested 1 ticket(s) -> FAILED (only 0 left).
--- Booking Closed ---
Tickets remaining: 0

===============================================================
*/
