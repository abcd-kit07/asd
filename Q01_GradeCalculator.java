import java.util.Scanner;

public class Q01_GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        int[] marks = new int[5];
        int total = 0;
        boolean passedAll = true;

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter marks in subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
            total += marks[i];
            if (marks[i] < 35) {
                passedAll = false;
            }
        }

        double average = total / 5.0;
        char grade;
        if (average >= 90) grade = 'A';
        else if (average >= 80) grade = 'B';
        else if (average >= 70) grade = 'C';
        else if (average >= 50) grade = 'D';
        else grade = 'F';

        System.out.println("\n----- RESULT -----");
        System.out.println("Student Name : " + name);
        System.out.println("Total Marks  : " + total + " / 500");
        System.out.printf("Average      : %.2f%n", average);
        System.out.println("Grade        : " + grade);
        System.out.println("Status       : " + (passedAll ? "Passed in all subjects" : "Failed in one or more subjects"));
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
Priya
88
92
79
85
90
[Output]
Enter student name: Enter marks in subject 1: Enter marks in subject 2: Enter marks in subject 3: Enter marks in subject 4: Enter marks in subject 5: 
----- RESULT -----
Student Name : Priya
Total Marks  : 434 / 500
Average      : 86.80
Grade        : B
Status       : Passed in all subjects

--- Sample run 2 ---
[Input]
Rahul
95
30
60
45
50
[Output]
Enter student name: Enter marks in subject 1: Enter marks in subject 2: Enter marks in subject 3: Enter marks in subject 4: Enter marks in subject 5: 
----- RESULT -----
Student Name : Rahul
Total Marks  : 280 / 500
Average      : 56.00
Grade        : D
Status       : Failed in one or more subjects

===============================================================
*/
