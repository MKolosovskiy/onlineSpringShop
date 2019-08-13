package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1111";

    public static Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL,LOGIN,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
        }
        return null;
    }
}
