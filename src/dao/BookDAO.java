package dao;

import java.sql.*;
import java.util.*;
import model.Book;

public class BookDAO {
    private Connection conn;

    // Constructor nhận vào đối tượng Connection để tương tác với CSDL
    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    // Phuong thuc de them mot quyen sach moi vao CSDL
    public boolean insert(Book b) throws SQLException {
        String sql = "INSERT INTO Book (title, author, year) VALUES (?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, b.getTitle()); // Đặt giá trị cho title
        st.setString(2, b.getAuthor()); // Đặt giá trị cho author
        st.setInt(3, b.getYear()); // Đặt giá trị cho year
        return st.executeUpdate() > 0; // Thực thi câu lệnh và trả về true nếu thành công
    }

    // Phuong thuc de lay tat ca quyen sach tu CSDL
    public List<Book> getAll() throws SQLException {
        List<Book> list = new ArrayList<>(); // Danh sach de luu cac quyen sach
        String sql = "SELECT * FROM Book";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            // Tao doi tuong Book tu ket qua truy van
            Book b = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("year")
            );
            list.add(b); // Them vao danh sach
        }
        return list; // Tra ve danh sach cac quyen sach
    }
}

// Lớp TestBook để kiểm thử các chức năng của BookDAO
// Gồm chức năng thêm sách và lấy danh sách sách từ cơ sở dữ liệu
