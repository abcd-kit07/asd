import java.sql.*;
import java.util.Scanner;

/*
 * SQL (run once in MySQL):
 *   CREATE DATABASE IF NOT EXISTS shop;
 *   USE shop;
 *   CREATE TABLE IF NOT EXISTS Orders (
 *       order_id      INT PRIMARY KEY,
 *       customer_name VARCHAR(50) NOT NULL,
 *       product_name  VARCHAR(50) NOT NULL,
 *       quantity      INT,
 *       price         DECIMAL(10,2)
 *   );
 *
 * Run : java -cp .:mysql-connector-j-8.x.x.jar Q17_OrdersJDBC   (use ; instead of : on Windows)
 * Change URL, USER and PASS below to match your MySQL setup.
 */
public class Q17_OrdersJDBC {
    static final String URL = "jdbc:mysql://localhost:3306/shop";
    static final String USER = "root";
    static final String PASS = "root";
    static Scanner sc = new Scanner(System.in);

    static String read(String p) {
        System.out.print(p);
        return sc.nextLine().trim();
    }

    // Prints rows along with total cost = quantity * price
    static void printOrders(ResultSet rs) throws SQLException {
        System.out.printf("%-6s %-15s %-15s %-5s %-10s %-12s%n", "ID", "Customer", "Product", "Qty", "Price", "Total Cost");
        System.out.println("-----------------------------------------------------------------------");
        boolean any = false;
        while (rs.next()) {
            any = true;
            int qty = rs.getInt("quantity");
            double price = rs.getDouble("price");
            System.out.printf("%-6d %-15s %-15s %-5d %-10.2f %-12.2f%n", rs.getInt("order_id"),
                    rs.getString("customer_name"), rs.getString("product_name"), qty, price, qty * price);
        }
        if (!any) System.out.println("No orders found.");
    }

    static void insertOrder(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Orders(order_id, customer_name, product_name, quantity, price) VALUES (?,?,?,?,?)")) {
            ps.setInt(1, Integer.parseInt(read("Order ID      : ")));
            ps.setString(2, read("Customer Name : "));
            ps.setString(3, read("Product Name  : "));
            ps.setInt(4, Integer.parseInt(read("Quantity      : ")));
            ps.setDouble(5, Double.parseDouble(read("Price         : ")));
            ps.executeUpdate();
            System.out.println("Order inserted successfully.");
        }
    }

    static void displayAll(Connection con) throws SQLException {
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM Orders")) {
            printOrders(rs);
        }
    }

    static void searchByCustomer(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Orders WHERE customer_name = ?")) {
            ps.setString(1, read("Enter customer name: "));
            try (ResultSet rs = ps.executeQuery()) {
                printOrders(rs);
            }
        }
    }

    static void updateQuantity(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("UPDATE Orders SET quantity = ? WHERE order_id = ?")) {
            int id = Integer.parseInt(read("Enter Order ID: "));
            ps.setInt(1, Integer.parseInt(read("Enter new quantity: ")));
            ps.setInt(2, id);
            System.out.println(ps.executeUpdate() > 0 ? "Quantity updated successfully." : "Order not found.");
        }
    }

    static void totalCost(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT order_id, customer_name, quantity * price AS total FROM Orders WHERE order_id = ?")) {
            ps.setInt(1, Integer.parseInt(read("Enter Order ID: ")));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    System.out.printf("Order %d (%s) total cost = Rs %.2f%n", rs.getInt("order_id"),
                            rs.getString("customer_name"), rs.getDouble("total"));
                else
                    System.out.println("Order not found.");
            }
        }
    }

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to database.");
            int choice;
            do {
                System.out.println("\n1. Insert Order  2. Display All  3. Search by Customer  4. Update Quantity  5. Total Cost of Order  6. Exit");
                try {
                    choice = Integer.parseInt(read("Enter choice: "));
                    switch (choice) {
                        case 1: insertOrder(con); break;
                        case 2: displayAll(con); break;
                        case 3: searchByCustomer(con); break;
                        case 4: updateQuantity(con); break;
                        case 5: totalCost(con); break;
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
Order ID      : 1
Customer Name : Ravi
Product Name  : Keyboard
Quantity      : 2
Price         : 750
Order inserted successfully.

Enter choice: 1   (Order ID: 2, Customer: Ravi, Product: Mouse, Quantity: 1, Price: 400)
Order inserted successfully.

Enter choice: 2
ID     Customer        Product         Qty   Price      Total Cost
-----------------------------------------------------------------------
1      Ravi            Keyboard        2     750.00     1500.00
2      Ravi            Mouse           1     400.00     400.00

Enter choice: 3
Enter customer name: Ravi
(same two rows are displayed)

Enter choice: 4
Enter Order ID: 2
Enter new quantity: 3
Quantity updated successfully.

Enter choice: 5
Enter Order ID: 2
Order 2 (Ravi) total cost = Rs 1200.00

Enter choice: 6
Goodbye!
==================================================================================
*/
