package bank.management.system;

import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "Jaiguruji_77");

            // Create statement object
            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();  // Useful for debugging connection issues
        }
    }
}
