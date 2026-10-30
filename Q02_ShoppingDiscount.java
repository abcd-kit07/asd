import java.util.Scanner;

public class Q02_ShoppingDiscount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter purchase amount (Rs): ");
        double amount = sc.nextDouble();

        if (amount < 0) {
            System.out.println("Invalid amount!");
            sc.close();
            return;
        }

        double discountPercent;
        if (amount >= 10000) discountPercent = 20;
        else if (amount >= 5000) discountPercent = 10;
        else if (amount >= 2000) discountPercent = 5;
        else discountPercent = 0;

        double discount = amount * discountPercent / 100;
        double finalBill = amount - discount;

        System.out.println("\n----- BILL -----");
        System.out.printf("Purchase Amount : Rs %.2f%n", amount);
        System.out.printf("Discount (%.0f%%)  : Rs %.2f%n", discountPercent, discount);
        System.out.printf("Final Bill      : Rs %.2f%n", finalBill);
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
12000
[Output]
Enter purchase amount (Rs): 
----- BILL -----
Purchase Amount : Rs 12000.00
Discount (20%)  : Rs 2400.00
Final Bill      : Rs 9600.00

--- Sample run 2 ---
[Input]
6500
[Output]
Enter purchase amount (Rs): 
----- BILL -----
Purchase Amount : Rs 6500.00
Discount (10%)  : Rs 650.00
Final Bill      : Rs 5850.00

--- Sample run 3 ---
[Input]
3000
[Output]
Enter purchase amount (Rs): 
----- BILL -----
Purchase Amount : Rs 3000.00
Discount (5%)  : Rs 150.00
Final Bill      : Rs 2850.00

--- Sample run 4 ---
[Input]
1500
[Output]
Enter purchase amount (Rs): 
----- BILL -----
Purchase Amount : Rs 1500.00
Discount (0%)  : Rs 0.00
Final Bill      : Rs 1500.00

===============================================================
*/
