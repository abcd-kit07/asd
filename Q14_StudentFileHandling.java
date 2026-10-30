import java.io.*;
import java.util.Scanner;

public class Q14_StudentFileHandling {
    static final String FILE_NAME = "students.txt";
    static Scanner sc = new Scanner(System.in);

    static String read(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    // append = false -> create/overwrite, append = true -> add to the end
    static void writeRecords(boolean append) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, append)))) {
            int n = Integer.parseInt(read("How many records to " + (append ? "append" : "write") + "? "));
            for (int i = 1; i <= n; i++) {
                System.out.println("Student " + i + ":");
                String reg = read("  Register No : ");
                String name = read("  Name        : ");
                String dept = read("  Department  : ");
                String mark = read("  Marks       : ");
                pw.println(reg + "," + name + "," + dept + "," + mark);
            }
            System.out.println("Records saved successfully.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
        }
    }

    static void displayRecords() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.println("\n" + String.format("%-12s %-12s %-12s %-6s", "RegNo", "Name", "Dept", "Marks"));
            System.out.println("--------------------------------------------");
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 4)
                    System.out.println(String.format("%-12s %-12s %-12s %-6s", p[0], p[1], p[2], p[3]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please write records first.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int choice = 0;
        do {
            System.out.println("\n1. Write new records (overwrite)\n2. Append records\n3. Display records\n4. Exit");
            try {
                choice = Integer.parseInt(read("Enter choice: "));
            } catch (NumberFormatException e) {
                choice = 0;
            }
            switch (choice) {
                case 1: writeRecords(false); break;
                case 2: writeRecords(true); break;
                case 3: displayRecords(); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 4);
        sc.close();
    }
}

/*
==================== SAMPLE INPUT / OUTPUT ====================
--- Sample run 1 ---
[Input]
1
2
101
Arun
AI&DS
88
102
Meena
AI&DS
92
2
1
103
Kiran
CSE
76
3
4
[Output]

1. Write new records (overwrite)
2. Append records
3. Display records
4. Exit
Enter choice: How many records to write? Student 1:
  Register No :   Name        :   Department  :   Marks       : Student 2:
  Register No :   Name        :   Department  :   Marks       : Records saved successfully.

1. Write new records (overwrite)
2. Append records
3. Display records
4. Exit
Enter choice: How many records to append? Student 1:
  Register No :   Name        :   Department  :   Marks       : Records saved successfully.

1. Write new records (overwrite)
2. Append records
3. Display records
4. Exit
Enter choice: 
RegNo        Name         Dept         Marks 
--------------------------------------------
101          Arun         AI&DS        88    
102          Meena        AI&DS        92    
103          Kiran        CSE          76    

1. Write new records (overwrite)
2. Append records
3. Display records
4. Exit
Enter choice: Exiting...

===============================================================
*/
