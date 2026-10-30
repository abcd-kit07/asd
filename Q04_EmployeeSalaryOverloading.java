class Employee {
    int id;
    String name;
    double basic, allowances, deductions;

    // Constructor overloading
    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Employee(int id, String name, double basic) {
        this(id, name);
        this.basic = basic;
    }

    Employee(int id, String name, double basic, double allowances, double deductions) {
        this(id, name, basic);
        this.allowances = allowances;
        this.deductions = deductions;
    }

    // Method overloading
    double calculateSalary(double basic) {
        return basic;
    }

    double calculateSalary(double basic, double allowances) {
        return basic + allowances;
    }

    double calculateSalary(double basic, double allowances, double deductions) {
        return basic + allowances - deductions;
    }

    void display(String label, double salary) {
        System.out.println("[" + label + "]");
        System.out.println("ID: " + id + " | Name: " + name + " | Basic: " + basic
                + " | Allowances: " + allowances + " | Deductions: " + deductions);
        System.out.println("Calculated Salary: Rs " + salary + "\n");
    }
}

public class Q04_EmployeeSalaryOverloading {
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Arun");
        e1.display("ID and name only", e1.calculateSalary(e1.basic));

        Employee e2 = new Employee(102, "Meena", 30000);
        e2.display("ID, name and basic salary", e2.calculateSalary(e2.basic));

        Employee e3 = new Employee(103, "Kiran", 40000, 8000, 2500);
        e3.display("All details (basic + allowances - deductions)",
                e3.calculateSalary(e3.basic, e3.allowances, e3.deductions));

        System.out.println("[Basic + Allowances only for Kiran]");
        System.out.println("Salary: Rs " + e3.calculateSalary(e3.basic, e3.allowances));
    }
}

/*
==================== SAMPLE OUTPUT ====================
[ID and name only]
ID: 101 | Name: Arun | Basic: 0.0 | Allowances: 0.0 | Deductions: 0.0
Calculated Salary: Rs 0.0

[ID, name and basic salary]
ID: 102 | Name: Meena | Basic: 30000.0 | Allowances: 0.0 | Deductions: 0.0
Calculated Salary: Rs 30000.0

[All details (basic + allowances - deductions)]
ID: 103 | Name: Kiran | Basic: 40000.0 | Allowances: 8000.0 | Deductions: 2500.0
Calculated Salary: Rs 45500.0

[Basic + Allowances only for Kiran]
Salary: Rs 48000.0
=======================================================
*/
