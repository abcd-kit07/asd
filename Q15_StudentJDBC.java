import java.sql.*;
import java.util.Scanner;

/*
 * SQL (run once in MySQL):
 *   CREATE DATABASE IF NOT EXISTS college;
 *   USE college;
 *   CREATE TABLE IF NOT EXISTS Student (
 *       register_no VARCHAR(20) PRIMARY KEY,
 *       name        VARCHAR(50) NOT NULL,
 *       department  VARCHAR(30),
 *       mark        INT
 *   );
 *
 * Compile : javac Q15_StudentJDBC.java
 * Run     : java -cp .:mysql-connector-j-8.x.x.jar Q15_StudentJDBC      (Linux/Mac)
 *           java -cp .;mysql-connector-j-8.x.x.jar Q15_StudentJDBC      (Windows)
 * Change URL, USER and PASS below to match your MySQL setup.
 */
public class Q15_StudentJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASS = "root";
    static Scanner sc = new Scanner(System.in);

    static String read(String p) {
        System.out.print(p);
        return sc.nextLine().trim();
    }

    static void insert(Connection con) throws SQLException {
        String sql = "INSERT INTO Student(register_no, name, department, mark) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, read("Register No : "));
            ps.setString(2, read("Name        : "));
            ps.setString(3, read("Department  : "));
            ps.setInt(4, Integer.parseInt(read("Mark        : ")));
            ps.executeUpdate();
            System.out.println("Record inserted successfully.");
        }
    }

    static void displayAll(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Student")) {
            System.out.printf("%-12s %-15s %-12s %-5s%n", "RegNo", "Name", "Dept", "Mark");
            System.out.println("------------------------------------------------");
            boolean any = false;
            while (rs.next()) {
                any = true;
                System.out.printf("%-12s %-15s %-12s %-5d%n", rs.getString("register_no"),
                        rs.getString("name"), rs.getString("department"), rs.getInt("mark"));
            }
            if (!any) System.out.println("No records found.");
        }
    }

    static void search(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Student WHERE register_no = ?")) {
            ps.setString(1, read("Enter Register No to search: "));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Register No: " + rs.getString("register_no"));
                    System.out.println("Name       : " + rs.getString("name"));
                    System.out.println("Department : " + rs.getString("department"));
                    System.out.println("Mark       : " + rs.getInt("mark"));
                } else {
                    System.out.println("Student not found.");
                }
            }
        }
    }

    static void update(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("UPDATE Student SET mark = ? WHERE register_no = ?")) {
            String reg = read("Enter Register No: ");
            ps.setInt(1, Integer.parseInt(read("Enter new mark: ")));
            ps.setString(2, reg);
            System.out.println(ps.executeUpdate() > 0 ? "Marks updated successfully." : "Student not found.");
        }
    }

    static void delete(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM Student WHERE register_no = ?")) {
            ps.setString(1, read("Enter Register No to delete: "));
            System.out.println(ps.executeUpdate() > 0 ? "Record deleted successfully." : "Student not found.");
        }
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to database.");
            int choice;
            do {
                System.out.println("\n1. Insert  2. Display All  3. Search  4. Update Marks  5. Delete  6. Exit");
                try {
                    choice = Integer.parseInt(read("Enter choice: "));
                    switch (choice) {
                        case 1: insert(con); break;
                        case 2: displayAll(con); break;
                        case 3: search(con); break;
                        case 4: update(con); break;
                        case 5: delete(con); break;
                        case 6: System.out.println("Goodbye!"); break;
                        default: System.out.println("Invalid choice!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    choice = 0;
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                    choice = 0;
                }
            } while (choice != 6);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        sc.close();
    }
}

/*
===================== SAMPLE INPUT / OUTPUT (MySQL required) =====================
Connected to database.

1. Insert  2. Display All  3. Search  4. Update Marks  5. Delete  6. Exit
Enter choice: 1
Register No : 101
Name        : Arun
Department  : AI&DS
Mark        : 85
Record inserted successfully.

Enter choice: 1   (Register No: 102, Name: Meena, Department: AI&DS, Mark: 92)
Record inserted successfully.

Enter choice: 2
RegNo        Name            Dept         Mark
------------------------------------------------
101          Arun            AI&DS        85
102          Meena           AI&DS        92

Enter choice: 3
Enter Register No to search: 102
Register No: 102
Name       : Meena
Department : AI&DS
Mark       : 92

Enter choice: 4
Enter Register No: 101
Enter new mark: 90
Marks updated successfully.

Enter choice: 5
Enter Register No to delete: 102
Record deleted successfully.

Enter choice: 6
Goodbye!
==================================================================================
*/
