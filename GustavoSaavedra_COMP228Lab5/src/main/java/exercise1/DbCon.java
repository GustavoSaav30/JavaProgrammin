package exercise1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
    private static final String DB_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    private static final String DB_USER = "COMP228_F24_soh_24";
    private static final String DB_PASSWORD = "saavedraSanchez";

    public static Connection connection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Oracle JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found.");
            e.printStackTrace();
            throw new SQLException("Unable to load JDBC driver.", e);
        }
        // Use correct variable names for connection parameters
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}