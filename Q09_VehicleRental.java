import java.util.Scanner;

class Vehicle {
    String regNo;
    String brand;
    double ratePerDay;

    Vehicle(String regNo, String brand, double ratePerDay) {
        this.regNo = regNo;
        this.brand = brand;
        this.ratePerDay = ratePerDay;
    }

    double calculateRent(int days) {
        return ratePerDay * days;
    }

    void display() {
        System.out.println("Registration No : " + regNo);
        System.out.println("Brand           : " + brand);
        System.out.println("Rate per day    : Rs " + ratePerDay);
    }
}

class Car extends Vehicle {
    int seats;
    boolean ac;

    Car(String regNo, String brand, double rate, int seats, boolean ac) {
        super(regNo, brand, rate);
        this.seats = seats;
        this.ac = ac;
    }

    @Override
    double calculateRent(int days) {
        double rent = super.calculateRent(days);
        if (ac) rent += 200 * days;      // AC charge per day
        return rent;
    }

    void display() {
        System.out.println("--- CAR ---");
        super.display();
        System.out.println("Seats           : " + seats + "  AC: " + (ac ? "Yes" : "No"));
    }
}

class Bike extends Vehicle {
    int engineCC;
    boolean helmetIncluded;

    Bike(String regNo, String brand, double rate, int engineCC, boolean helmetIncluded) {
        super(regNo, brand, rate);
        this.engineCC = engineCC;
        this.helmetIncluded = helmetIncluded;
    }

    @Override
    double calculateRent(int days) {
        double rent = super.calculateRent(days);
        if (helmetIncluded) rent += 50 * days;   // helmet charge per day
        return rent;
    }

    void display() {
        System.out.println("--- BIKE ---");
        super.display();
        System.out.println("Engine          : " + engineCC + " cc  Helmet: " + (helmetIncluded ? "Yes" : "No"));
    }
}

public class Q09_VehicleRental {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car car = new Car("TN37AB1234", "Hyundai i20", 2000, 5, true);
        Bike bike = new Bike("TN38CD5678", "Royal Enfield", 800, 350, true);

        System.out.print("Enter number of rental days for the car : ");
        int carDays = sc.nextInt();
        System.out.print("Enter number of rental days for the bike: ");
        int bikeDays = sc.nextInt();

        System.out.println();
        car.display();
        System.out.println("Days            : " + carDays);
        System.out.printf("Total Rent      : Rs %.2f%n%n", car.calculateRent(carDays));

        bike.display();
        System.out.println("Days            : " + bikeDays);
        System.out.printf("Total Rent      : Rs %.2f%n", bike.calculateRent(bikeDays));
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
3
2
[Output]
Enter number of rental days for the car : Enter number of rental days for the bike: 
--- CAR ---
Registration No : TN37AB1234
Brand           : Hyundai i20
Rate per day    : Rs 2000.0
Seats           : 5  AC: Yes
Days            : 3
Total Rent      : Rs 6600.00

--- BIKE ---
Registration No : TN38CD5678
Brand           : Royal Enfield
Rate per day    : Rs 800.0
Engine          : 350 cc  Helmet: Yes
Days            : 2
Total Rent      : Rs 1700.00

===============================================================
*/
