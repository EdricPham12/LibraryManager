package UI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import dao.BorrowingDAO;
import dao.BookDAO;
import model.Borrowing;
import model.Book;

public class StatisticsPanel extends JPanel {

    public StatisticsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 250, 255));

        JLabel titleLabel = new JLabel("Panel Thống kê và Báo cáo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Khu vực này sẽ chứa các biểu đồ, bảng thống kê chi tiết
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(235, 245, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Bảng thống kê sách được mượn nhiều nhất
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Mã sách", "Tiêu đề sách", "Số lượt mượn"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép sửa trực tiếp
            }
        };
        JTable statisticsTable = new JTable(tableModel);
        statisticsTable.setRowHeight(25);
        statisticsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statisticsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        statisticsTable.setSelectionBackground(new Color(200, 230, 255));

        // Điều chỉnh độ rộng cột
        statisticsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        statisticsTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        statisticsTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(statisticsTable);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        // ====== PANEL NÚT CHỨC NĂNG Ở DƯỚI ======
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 235, 245));
        JButton btnLoad = new JButton("Tải lại");

        btnLoad.setBackground(new Color(100, 180, 255));
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setFocusPainted(false);
        btnLoad.setFont(new Font("Arial", Font.BOLD, 13));

        buttonPanel.add(btnLoad);
        add(buttonPanel, BorderLayout.SOUTH); // Đặt panel nút ở dưới cùng

        // Load dữ liệu thống kê khi panel được tạo
        loadMostBorrowedBooks(tableModel);
        
        // ====== SỰ KIỆN ======
        btnLoad.addActionListener(e -> loadMostBorrowedBooks(tableModel));
    }

    // Phương thức để tải và hiển thị thống kê sách được mượn nhiều nhất
    private void loadMostBorrowedBooks(DefaultTableModel tableModel) {
        BorrowingDAO borrowingDAO = new BorrowingDAO();
        BookDAO bookDAO = new BookDAO();
        List<Borrowing> borrowings = borrowingDAO.getAllBorrowings();
        List<Book> allBooks = bookDAO.getAllBooks();

        // Đếm số lượt mượn cho mỗi sách (chỉ tính trạng thái "Đang mượn")
        Map<Integer, Integer> borrowedCounts = new HashMap<>();
        for (Borrowing b : borrowings) {
            // Chỉ đếm nếu trạng thái là "Đang mượn"
            if ("Đang mượn".equals(b.getStatus())) {
                borrowedCounts.put(b.getBookId(), borrowedCounts.getOrDefault(b.getBookId(), 0) + 1);
            }
        }

        // Sắp xếp sách theo số lượt mượn giảm dần
        Map<Integer, Integer> sortedBorrowedCounts = borrowedCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Clear bảng hiện tại
        tableModel.setRowCount(0);

        // Thêm dữ liệu vào bảng
        for (Map.Entry<Integer, Integer> entry : sortedBorrowedCounts.entrySet()) {
            int bookId = entry.getKey();
            int count = entry.getValue();
            String bookTitle = "Không rõ";
            for (Book book : allBooks) {
                if (book.getId() == bookId) {
                    bookTitle = book.getTitle();
                    break;
                }
            }
            tableModel.addRow(new Object[]{bookId, bookTitle, count});
        }
    }
} 