class Person {
    String name;
    int age;
    String address;

    Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    void displayPerson() {
        System.out.println("Name    : " + name);
        System.out.println("Age     : " + age);
        System.out.println("Address : " + address);
    }
}

class Student extends Person {
    String registerNo;
    String course;

    Student(String name, int age, String address, String registerNo, String course) {
        super(name, age, address);
        this.registerNo = registerNo;
        this.course = course;
    }

    void display() {
        System.out.println("=== STUDENT DETAILS ===");
        displayPerson();
        System.out.println("Register No : " + registerNo);
        System.out.println("Course      : " + course);
    }
}

class Faculty extends Person {
    String employeeId;
    String department;

    Faculty(String name, int age, String address, String employeeId, String department) {
        super(name, age, address);
        this.employeeId = employeeId;
        this.department = department;
    }

    void display() {
        System.out.println("=== FACULTY DETAILS ===");
        displayPerson();
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Department  : " + department);
    }
}

public class Q08_PersonInheritance {
    public static void main(String[] args) {
        Student s = new Student("Karthik", 19, "Coimbatore", "927625BAD001", "B.Tech AI&DS");
        Faculty f = new Faculty("Dr. Lakshmi", 42, "Tiruppur", "FAC1024", "AI&DS");
        s.display();
        System.out.println();
        f.display();
    }
}

/*
==================== SAMPLE OUTPUT ====================
=== STUDENT DETAILS ===
Name    : Karthik
Age     : 19
Address : Coimbatore
Register No : 927625BAD001
Course      : B.Tech AI&DS

=== FACULTY DETAILS ===
Name    : Dr. Lakshmi
Age     : 42
Address : Tiruppur
Employee ID : FAC1024
Department  : AI&DS
=======================================================
*/
