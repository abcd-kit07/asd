import java.util.InputMismatchException;
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) {
        super(msg);
    }
}

public class Q12_BankWithdrawal {
    static double withdraw(double balance, double amount)
            throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0)
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient balance. Available: Rs " + balance);
        return balance - amount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter account balance: ");
            double balance = sc.nextDouble();
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();

            double updated = withdraw(balance, amount);
            System.out.println("Withdrawal successful!");
            System.out.println("Updated balance: Rs " + updated);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter numeric values only.");
        } finally {
            System.out.println("Transaction completed. Thank you for banking with us.");
            sc.close();
        }
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
5000
1500
[Output]
Enter account balance: Enter withdrawal amount: Withdrawal successful!
Updated balance: Rs 3500.0
Transaction completed. Thank you for banking with us.

--- Sample run 2 ---
[Input]
5000
6000
[Output]
Enter account balance: Enter withdrawal amount: Error: Insufficient balance. Available: Rs 5000.0
Transaction completed. Thank you for banking with us.

--- Sample run 3 ---
[Input]
5000
-200
[Output]
Enter account balance: Enter withdrawal amount: Error: Withdrawal amount must be positive.
Transaction completed. Thank you for banking with us.

--- Sample run 4 ---
[Input]
5000
abc
[Output]
Enter account balance: Enter withdrawal amount: Error: Invalid input. Please enter numeric values only.
Transaction completed. Thank you for banking with us.

===============================================================
*/
