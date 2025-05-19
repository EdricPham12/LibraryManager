package main;

import dao.BookDAO;
import model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import UI.BookManagerPanel;
import UI.ReaderManagerPanel;
import UI.BorrowingManagerPanel;
import UI.StaffManagerPanel;
import UI.CategoryManagerPanel;
import UI.PublisherManagerPanel;
import UI.AuthorManagerPanel;
import UI.StatisticsPanel;

// Lớp chính khởi chạy ứng dụng và quản lý các panel chức năng dưới dạng tab
public class LibraryManagerUI extends JFrame {

    // Constructor của lớp LibraryManagerUI
    public LibraryManagerUI() {
        // Thiết lập tiêu đề cho cửa sổ chính
        super("Quản lý Thư viện");

        // Thiết lập kích thước mặc định của cửa sổ
        setSize(1000, 700);
        // Thiết lập hành động khi đóng cửa sổ (thoát ứng dụng)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Đặt vị trí cửa sổ ở giữa màn hình
        setLocationRelativeTo(null);

        // Tạo JTabbedPane để chứa các panel chức năng
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tạo các instance của từng panel quản lý
        BookManagerPanel bookPanel = new BookManagerPanel();
        ReaderManagerPanel readerPanel = new ReaderManagerPanel();
        BorrowingManagerPanel borrowingPanel = new BorrowingManagerPanel();
        StaffManagerPanel staffPanel = new StaffManagerPanel();
        CategoryManagerPanel categoryPanel = new CategoryManagerPanel();
        PublisherManagerPanel publisherPanel = new PublisherManagerPanel();
        AuthorManagerPanel authorPanel = new AuthorManagerPanel();
        StatisticsPanel statisticsPanel = new StatisticsPanel();

        // Thêm các panel vào JTabbedPane dưới dạng các tab
        tabbedPane.addTab("Quản lý Sách", bookPanel);
        tabbedPane.addTab("Quản lý Độc giả", readerPanel);
        tabbedPane.addTab("Quản lý Mượn/Trả", borrowingPanel);
        tabbedPane.addTab("Quản lý Nhân viên", staffPanel);
        tabbedPane.addTab("Quản lý Danh mục", categoryPanel);
        tabbedPane.addTab("Quản lý Nhà xuất bản", publisherPanel);
        tabbedPane.addTab("Quản lý Tác giả", authorPanel);
        tabbedPane.addTab("Thống kê/Báo cáo", statisticsPanel);

        // Thêm JTabbedPane vào content pane của JFrame
        getContentPane().add(tabbedPane);
    }

    // Phương thức main - điểm khởi đầu của ứng dụng
    public static void main(String[] args) {
        // Chạy giao diện trên Event Dispatch Thread (EDT) để đảm bảo an toàn luồng
        SwingUtilities.invokeLater(() -> {
            LibraryManagerUI ui = new LibraryManagerUI();
            // Hiển thị cửa sổ chính
            ui.setVisible(true);
        });
    }
}