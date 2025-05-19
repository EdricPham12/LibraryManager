// Lớp DBConnect giúp kết nối tới cơ sở dữ liệu MySQL
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "0905632186aA"; // pass MySQL

    // Hàm trả về kết nối CSDL, nếu lỗi thì in ra và trả về null
    public static Connection getConnection() {
        try {
            // Nạp driver JDBC cho MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console
            return null;
        }
    }
}
