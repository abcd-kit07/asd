import java.sql.*;
import java.util.Scanner;

/*
 * SQL (run once in MySQL):
 *   CREATE DATABASE IF NOT EXISTS library;
 *   USE library;
 *   CREATE TABLE IF NOT EXISTS Book (
 *       book_id          INT PRIMARY KEY,
 *       title            VARCHAR(100) NOT NULL,
 *       author           VARCHAR(50),
 *       available_copies INT
 *   );
 *
 * Run : java -cp .:mysql-connector-j-8.x.x.jar Q16_BookJDBC   (use ; instead of : on Windows)
 * Change URL, USER and PASS below to match your MySQL setup.
 */
public class Q16_BookJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/library";
    static final String USER = "root";
    static final String PASS = "root";
    static Scanner sc = new Scanner(System.in);

    static String read(String p) {
        System.out.print(p);
        return sc.nextLine().trim();
    }

    static void printBooks(ResultSet rs) throws SQLException {
        System.out.printf("%-6s %-30s %-20s %-6s%n", "ID", "Title", "Author", "Copies");
        System.out.println("-----------------------------------------------------------------");
        boolean any = false;
        while (rs.next()) {
            any = true;
            System.out.printf("%-6d %-30s %-20s %-6d%n", rs.getInt("book_id"), rs.getString("title"),
                    rs.getString("author"), rs.getInt("available_copies"));
        }
        if (!any) System.out.println("No books found.");
    }

    static void addBook(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Book(book_id, title, author, available_copies) VALUES (?,?,?,?)")) {
            ps.setInt(1, Integer.parseInt(read("Book ID          : ")));
            ps.setString(2, read("Title            : "));
            ps.setString(3, read("Author           : "));
            ps.setInt(4, Integer.parseInt(read("Available copies : ")));
            ps.executeUpdate();
            System.out.println("Book added successfully.");
        }
    }

    static void searchById(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Book WHERE book_id = ?")) {
            ps.setInt(1, Integer.parseInt(read("Enter Book ID: ")));
            try (ResultSet rs = ps.executeQuery()) {
                printBooks(rs);
            }
        }
    }

    static void searchByTitle(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Book WHERE title LIKE ?")) {
            ps.setString(1, "%" + read("Enter title (or part of it): ") + "%");
            try (ResultSet rs = ps.executeQuery()) {
                printBooks(rs);
            }
        }
    }

    static void updateCopies(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("UPDATE Book SET available_copies = ? WHERE book_id = ?")) {
            int id = Integer.parseInt(read("Enter Book ID: "));
            ps.setInt(1, Integer.parseInt(read("Enter new number of copies: ")));
            ps.setInt(2, id);
            System.out.println(ps.executeUpdate() > 0 ? "Copies updated successfully." : "Book not found.");
        }
    }

    static void displayAvailable(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Book WHERE available_copies > 0")) {
            printBooks(rs);
        }
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to database.");
            int choice;
            do {
                System.out.println("\n1. Add Book  2. Search by ID  3. Search by Title  4. Update Copies  5. Show Available Books  6. Exit");
                try {
                    choice = Integer.parseInt(read("Enter choice: "));
                    switch (choice) {
                        case 1: addBook(con); break;
                        case 2: searchById(con); break;
                        case 3: searchByTitle(con); break;
                        case 4: updateCopies(con); break;
                        case 5: displayAvailable(con); break;
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

Enter choice: 1
Book ID          : 1
Title            : Java The Complete Reference
Author           : Herbert Schildt
Available copies : 5
Book added successfully.

Enter choice: 1   (Book ID: 2, Title: Data Structures, Author: Mark Weiss, Copies: 0)
Book added successfully.

Enter choice: 2
Enter Book ID: 1
ID     Title                          Author               Copies
-----------------------------------------------------------------
1      Java The Complete Reference    Herbert Schildt      5

Enter choice: 3
Enter title (or part of it): Data
ID     Title                          Author               Copies
-----------------------------------------------------------------
2      Data Structures                Mark Weiss           0

Enter choice: 4
Enter Book ID: 2
Enter new number of copies: 3
Copies updated successfully.

Enter choice: 5
ID     Title                          Author               Copies
-----------------------------------------------------------------
1      Java The Complete Reference    Herbert Schildt      5
2      Data Structures                Mark Weiss           3

Enter choice: 6
Goodbye!
==================================================================================
*/
