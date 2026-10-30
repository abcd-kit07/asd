abstract class Employee {
    int empId;
    String empName;

    Employee(int empId, String empName) {
        this.empId = empId;
        this.empName = empName;
    }

    abstract double calculateSalary();

    void display() {
        System.out.println("Employee ID   : " + empId);
        System.out.println("Employee Name : " + empName);
    }
}

class PermanentEmployee extends Employee {
    double basic, hra, da, pf;

    PermanentEmployee(int id, String name, double basic) {
        super(id, name);
        this.basic = basic;
        this.hra = 0.20 * basic;   // 20% HRA
        this.da = 0.10 * basic;    // 10% DA
        this.pf = 0.12 * basic;    // 12% PF deduction
    }

    @Override
    double calculateSalary() {
        return basic + hra + da - pf;
    }

    @Override
    void display() {
        System.out.println("Type          : Permanent");
        super.display();
        System.out.printf("Basic: %.2f  HRA: %.2f  DA: %.2f  PF: %.2f%n", basic, hra, da, pf);
        System.out.printf("Net Salary    : Rs %.2f%n", calculateSalary());
    }
}

class ContractEmployee extends Employee {
    int hoursWorked;
    double ratePerHour;

    ContractEmployee(int id, String name, int hoursWorked, double ratePerHour) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.ratePerHour = ratePerHour;
    }

    @Override
    double calculateSalary() {
        return hoursWorked * ratePerHour;
    }

    @Override
    void display() {
        System.out.println("Type          : Contract");
        super.display();
        System.out.println("Hours Worked  : " + hoursWorked + "  Rate/Hour: " + ratePerHour);
        System.out.printf("Salary        : Rs %.2f%n", calculateSalary());
    }
}

public class Q07_AbstractEmployee {
    public static void main(String[] args) {
        Employee[] employees = {
            new PermanentEmployee(101, "Arun", 40000),
            new ContractEmployee(201, "Divya", 160, 350)
        };
        for (Employee e : employees) {
            e.display();
            System.out.println("------------------------------------");
        }
    }
}

/*
==================== SAMPLE OUTPUT ====================
Type          : Permanent
Employee ID   : 101
Employee Name : Arun
Basic: 40000.00  HRA: 8000.00  DA: 4000.00  PF: 4800.00
Net Salary    : Rs 47200.00
------------------------------------
Type          : Contract
Employee ID   : 201
Employee Name : Divya
Hours Worked  : 160  Rate/Hour: 350.0
Salary        : Rs 56000.00
------------------------------------
=======================================================
*/
