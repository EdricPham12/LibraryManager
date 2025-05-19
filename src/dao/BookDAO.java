package dao;

import java.sql.*;
import java.util.*;
import model.Book;
import util.DBConnect; // Sử dụng lớp DBConnect từ package util

public class BookDAO {

    // Lấy toàn bộ danh sách sách từ cơ sở dữ liệu
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getInt("author_id"),
                    rs.getInt("publisher_id"),
                    rs.getInt("category_id"),
                    rs.getInt("year"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Thêm một cuốn sách mới vào cơ sở dữ liệu
    public boolean insertBook(Book book) {
        String sql = "INSERT INTO book(title, author_id, publisher_id, category_id, year, price, stock) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorId());
            stmt.setInt(3, book.getPublisherId());
            stmt.setInt(4, book.getCategoryId());
            stmt.setInt(5, book.getYear());
            stmt.setDouble(6, book.getPrice());
            stmt.setInt(7, book.getStock());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cập nhật thông tin sách
    public boolean updateBook(Book book) {
        String sql = "UPDATE book SET title=?, author_id=?, publisher_id=?, category_id=?, year=?, price=?, stock=? WHERE book_id=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorId());
            stmt.setInt(3, book.getPublisherId());
            stmt.setInt(4, book.getCategoryId());
            stmt.setInt(5, book.getYear());
            stmt.setDouble(6, book.getPrice());
            stmt.setInt(7, book.getStock());
            stmt.setInt(8, book.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Xoá sách theo mã book_id
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM book WHERE book_id=?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
