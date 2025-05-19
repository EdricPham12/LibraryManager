package dao;

import java.sql.*;
import java.util.*;
import model.Publisher;
import util.DBConnect;

public class PublisherDAO {

    // Lấy danh sách tất cả nhà xuất bản
    public List<Publisher> getAllPublishers() {
        List<Publisher> list = new ArrayList<>();
        String sql = "SELECT * FROM publisher";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Publisher publisher = new Publisher(
                    rs.getInt("publisher_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone")
                );
                list.add(publisher);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm nhà xuất bản mới
    public boolean insertPublisher(Publisher publisher) {
        String sql = "INSERT INTO publisher (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getAddress());
            stmt.setString(3, publisher.getPhone());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin nhà xuất bản
    public boolean updatePublisher(Publisher publisher) {
        String sql = "UPDATE publisher SET name=?, address=?, phone=? WHERE publisher_id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, publisher.getName());
            stmt.setString(2, publisher.getAddress());
            stmt.setString(3, publisher.getPhone());
            stmt.setInt(4, publisher.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa nhà xuất bản
    public boolean deletePublisher(int publisherId) {
        String sql = "DELETE FROM publisher WHERE publisher_id=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, publisherId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
