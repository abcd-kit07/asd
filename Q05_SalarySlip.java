import java.util.Scanner;

public class Q05_SalarySlip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Designation: ");
        String desig = sc.nextLine();
        System.out.print("Enter Basic Salary: ");
        double basic = sc.nextDouble();
        System.out.print("Enter HRA: ");
        double hra = sc.nextDouble();
        System.out.print("Enter DA: ");
        double da = sc.nextDouble();
        System.out.print("Enter Deductions: ");
        double deductions = sc.nextDouble();

        double gross = basic + hra + da;
        double net = gross - deductions;

        System.out.println("\n==========================================");
        System.out.println("               SALARY SLIP");
        System.out.println("==========================================");
        System.out.printf("%-18s: %d%n", "Employee ID", id);
        System.out.printf("%-18s: %s%n", "Name", name);
        System.out.printf("%-18s: %s%n", "Department", dept);
        System.out.printf("%-18s: %s%n", "Designation", desig);
        System.out.println("------------------------------------------");
        System.out.printf("%-18s: %10.2f%n", "Basic Salary", basic);
        System.out.printf("%-18s: %10.2f%n", "HRA", hra);
        System.out.printf("%-18s: %10.2f%n", "DA", da);
        System.out.printf("%-18s: %10.2f%n", "Gross Salary", gross);
        System.out.printf("%-18s: %10.2f%n", "Deductions", deductions);
        System.out.println("------------------------------------------");
        System.out.printf("%-18s: %10.2f%n", "Net Salary", net);
        System.out.println("==========================================");
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
101
Arun Kumar
AI&DS
Software Engineer
40000
8000
4000
3500
[Output]
Enter Employee ID: Enter Name: Enter Department: Enter Designation: Enter Basic Salary: Enter HRA: Enter DA: Enter Deductions: 
==========================================
               SALARY SLIP
==========================================
Employee ID       : 101
Name              : Arun Kumar
Department        : AI&DS
Designation       : Software Engineer
------------------------------------------
Basic Salary      :   40000.00
HRA               :    8000.00
DA                :    4000.00
Gross Salary      :   52000.00
Deductions        :    3500.00
------------------------------------------
Net Salary        :   48500.00
==========================================

===============================================================
*/
