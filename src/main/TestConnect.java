package main;

import java.sql.Connection;
import util.DBConnect;

public class TestConnect {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnect.getConnection();
            if (conn != null) {
                System.out.println("✅ Ket noi thanh cong!");
            } else {
                System.out.println("❌ Ket noi that bai!");
            }
        } catch (Exception e) {
            System.out.println("❌ Loi ket noi: " + e.getMessage());
        }
    }
}
