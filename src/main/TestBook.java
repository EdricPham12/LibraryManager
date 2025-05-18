package main;

import dao.BookDAO;
import model.Book;
import util.DBConnect;
import java.sql.Connection;
import java.util.List;

public class TestBook {

    public static void main(String[] args) {
        try {
// Kết nối đến cơ sở dữ liệu
            Connection conn = DBConnect.getConnection();

            // Tạo đối tượng DAO để thao tác với bảng sách
            BookDAO dao = new BookDAO(conn);

            // Tạo đối tượng Book mới
            Book b = new Book(0, "Clean Code", "Robert C. Martin", 2008);

            // Thực hiện thêm sách
            if (dao.insert(b)) {
                System.out.println("Them sach thanh cong!");
            } else {
                System.out.println("Them sach that bai!");
            }

            // Lấy danh sách tất cả các sách và in ra màn hình
            List<Book> books = dao.getAll();
            for (Book book : books) {
                System.out.println(book.getId() + ": " + book.getTitle());
            }

            // Đóng kết nối
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
