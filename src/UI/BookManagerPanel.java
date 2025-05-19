package UI;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.CategoryDAO;
import dao.PublisherDAO;
import model.Author;
import model.Book;
import model.Category;
import model.Publisher;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// Lớp panel quản lý thông tin Sách
public class BookManagerPanel extends JPanel {

    // Khai báo các thành phần giao diện
    private JTable table; // Bảng hiển thị danh sách sách
    private DefaultTableModel tableModel; // Model cho bảng
    private JTextField tfId, tfTitle, tfYear, tfPrice, tfStock, tfSearch; // Các trường nhập liệu và tìm kiếm
    private JComboBox<Author> cbAuthor; // ComboBox chọn Tác giả
    private JComboBox<Publisher> cbPublisher; // ComboBox chọn Nhà xuất bản
    private JComboBox<Category> cbCategory; // ComboBox chọn Thể loại

    // Khai báo DAO để tương tác với cơ sở dữ liệu sách
    private BookDAO bookDAO = new BookDAO();

    // Constructor của lớp BookManagerPanel
    public BookManagerPanel() {
        // Thiết lập layout cho panel chính là BorderLayout
        setLayout(new BorderLayout(10, 10));
        // Thiết lập màu nền cho panel
        setBackground(new Color(245, 250, 255));

        // ====== PANEL TÌM KIẾM Ở TRÊN ======
        // Tạo panel chứa ô tìm kiếm và nút tìm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(220, 235, 245));
        // Ô nhập liệu tìm kiếm
        tfSearch = new JTextField(20);
        tfSearch.setBorder(new RoundedBorder(8));
        // Nút tìm kiếm
        JButton btnSearch = new JButton("Tìm");
        // Thiết lập style cho nút tìm kiếm
        btnSearch.setBackground(new Color(100, 180, 255));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 13));
        // Thêm nhãn, ô tìm kiếm và nút tìm vào searchPanel
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(tfSearch);
        searchPanel.add(btnSearch);

        // ====== PANEL NHẬP LIỆU Ở TRÊN ======
        // Tạo panel chứa các ô nhập liệu và nhãn
        JPanel inputPanel = new JPanel(new GridBagLayout());
        // Thiết lập border cho inputPanel
        inputPanel.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new TitledBorder("Thông tin sách")
        ));
        inputPanel.setBackground(new Color(235, 245, 255));
        // Khởi tạo GridBagConstraints để cấu hình vị trí và kích thước các thành phần trong GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL; // Cho phép giãn theo chiều ngang
        gbc.weightx = 1.0; // Give extra horizontal space to components that can expand

        // Khởi tạo các trường nhập liệu
        tfId = new JTextField(15); // Increased preferred size
        tfTitle = new JTextField(25); // Increased preferred size
        cbAuthor = new JComboBox<>();
        cbPublisher = new JComboBox<>();
        cbCategory = new JComboBox<>();
        tfYear = new JTextField(10); // Increased preferred size
        tfPrice = new JTextField(15); // Increased preferred size
        tfStock = new JTextField(10); // Increased preferred size

        // Load dữ liệu vào ComboBox từ các DAO tương ứng
        AuthorDAO authorDAO = new AuthorDAO();
        PublisherDAO publisherDAO = new PublisherDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        for (Author a : authorDAO.getAllAuthors()) cbAuthor.addItem(a);
        for (Publisher p : publisherDAO.getAllPublishers()) cbPublisher.addItem(p);
        for (Category c : categoryDAO.getAllCategories()) cbCategory.addItem(c);

        // Bo góc cho các ô nhập liệu và ComboBox
        JTextField[] fields = {tfId, tfTitle, tfYear, tfPrice, tfStock};
        for (JTextField f : fields) {
            f.setBorder(new RoundedBorder(8));
            f.setBackground(new Color(255, 255, 255));
        }
        cbAuthor.setBackground(Color.WHITE);
        cbPublisher.setBackground(Color.WHITE);
        cbCategory.setBackground(Color.WHITE);

        // Thêm các components vào panel nhập liệu với GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; // Align label to the right
        gbc.weightx = 0; // Label doesn't need extra horizontal space
        inputPanel.add(new JLabel("Mã sách:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Align field to the left and give it weight
        inputPanel.add(tfId, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("Tên sách:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(tfTitle, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("Tác giả:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(cbAuthor, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("NXB:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(cbPublisher, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("Thể loại:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(cbCategory, gbc);
        
        gbc.gridx = 2; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("Năm XB:"), gbc);
        gbc.gridx = 3; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(tfYear, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("Giá:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(tfPrice, gbc);
        
        gbc.gridx = 2; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; gbc.weightx = 0; // Reset weight for label
        inputPanel.add(new JLabel("Tồn kho:"), gbc);
        gbc.gridx = 3; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST; gbc.weightx = 1.0; // Give weight to field
        inputPanel.add(tfStock, gbc);

        // ====== GỘP PANEL TÌM KIẾM VÀ NHẬP LIỆU Ở TRÊN ======
        // Tạo panel chứa cả searchPanel và inputPanel và đặt ở phía trên cùng của panel chính
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(inputPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // ====== BẢNG HIỂN THỊ DỮ LIỆU ======
        // Khởi tạo tableModel với các cột và không cho phép sửa trực tiếp
        tableModel = new DefaultTableModel(new String[]{
                "Mã sách", "Tiêu đề", "Tác giả", "Nhà xuất bản", "Năm xuất bản", "Thể loại", "Giá", "Tồn kho"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Không cho phép sửa trực tiếp trên bảng
                return false;
            }
        };
        // Khởi tạo bảng với tableModel
        table = new JTable(tableModel);
        // Thiết lập chiều cao hàng, font chữ, màu chọn cho bảng
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setSelectionBackground(new Color(200, 230, 255));
        
        // Điều chỉnh độ rộng các cột
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // Mã sách
        table.getColumnModel().getColumn(1).setPreferredWidth(250); // Tiêu đề
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Tác giả
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Nhà xuất bản
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Năm xuất bản
        table.getColumnModel().getColumn(5).setPreferredWidth(80);  // Thể loại
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Giá
        table.getColumnModel().getColumn(7).setPreferredWidth(100); // Tồn kho
        
        // Thêm thanh cuộn cho bảng
        JScrollPane scrollPane = new JScrollPane(table);
        // Đặt bảng vào giữa panel chính
        add(scrollPane, BorderLayout.CENTER);

        // ====== PANEL NÚT CHỨC NĂNG Ở DƯỚI ======
        // Tạo panel chứa các nút chức năng CRUD
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 235, 245));
        // Khởi tạo các nút
        JButton btnLoad = new JButton("Tải lại");
        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");

        // Thiết lập style chung cho các nút
        JButton[] btns = {btnLoad, btnAdd, btnUpdate, btnDelete};
        for (JButton b : btns) {
            b.setBackground(new Color(100, 180, 255));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 13));
        }

        // Thêm các nút vào buttonPanel
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        // Đặt buttonPanel ở phía dưới cùng của panel chính
        add(buttonPanel, BorderLayout.SOUTH);

        // ====== SỰ KIỆN ======
        // Thêm các ActionListener cho các nút để xử lý sự kiện click
        btnLoad.addActionListener(e -> loadBooks());
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnSearch.addActionListener(e -> searchBook());
        tfSearch.addActionListener(e -> searchBook()); // Thêm listener cho Enter trong ô tìm kiếm

        // Thêm MouseListener cho bảng để xử lý sự kiện click chuột vào hàng
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Lấy chỉ số hàng được chọn
                int row = table.getSelectedRow();
                // Điền dữ liệu từ hàng được chọn vào các ô nhập liệu
                tfId.setText(tableModel.getValueAt(row, 0).toString());
                tfTitle.setText(tableModel.getValueAt(row, 1).toString());
                setSelectedComboBox(cbAuthor, tableModel.getValueAt(row, 2).toString());
                setSelectedComboBox(cbPublisher, tableModel.getValueAt(row, 3).toString());
                setSelectedComboBox(cbCategory, tableModel.getValueAt(row, 4).toString());
                tfYear.setText(tableModel.getValueAt(row, 5).toString());
                tfPrice.setText(tableModel.getValueAt(row, 6).toString());
                tfStock.setText(tableModel.getValueAt(row, 7).toString());
            }
        });

        // Tải dữ liệu sách lần đầu khi panel được hiển thị
        loadBooks();
    }

    // Phương thức để tải danh sách sách từ database và hiển thị lên bảng
    private void loadBooks() {
        // Lấy danh sách sách từ DAO
        List<Book> books = bookDAO.getAllBooks();
        // Khởi tạo DAO cho Tác giả, NXB, Thể loại để lấy tên hiển thị
        AuthorDAO authorDAO = new AuthorDAO();
        PublisherDAO publisherDAO = new PublisherDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        // Xóa dữ liệu cũ trên bảng
        tableModel.setRowCount(0);
        // Duyệt qua danh sách sách và thêm vào bảng
        for (Book b : books) {
            // Khởi tạo tên mặc định
            String authorName = "";
            String publisherName = "";
            String categoryName = "";

            // Tìm tên Tác giả dựa trên ID
            for (Author a : authorDAO.getAllAuthors()) {
                if (a.getId() == b.getAuthorId()) {
                    authorName = a.getName();
                    break;
                }
            }
            // Tìm tên Nhà xuất bản dựa trên ID
            for (Publisher p : publisherDAO.getAllPublishers()) {
                if (p.getId() == b.getPublisherId()) {
                    publisherName = p.getName();
                    break;
                }
            }
            // Tìm tên Thể loại dựa trên ID
            for (Category c : categoryDAO.getAllCategories()) {
                if (c.getId() == b.getCategoryId()) {
                    categoryName = c.getName();
                    break;
                }
            }

            // Thêm hàng dữ liệu vào bảng
            tableModel.addRow(new Object[]{
                    b.getId(), b.getTitle(), authorName, publisherName, categoryName,
                    b.getYear(), b.getPrice(), b.getStock()
            });
        }
    }

    // Phương thức xử lý thêm sách mới
    private void addBook() {
        try {
            // Tạo đối tượng Book từ dữ liệu nhập liệu
            Book b = new Book(0, // ID tự tăng, gán 0
                    tfTitle.getText(),
                    // Lấy ID từ item được chọn trong ComboBox
                    ((Author) cbAuthor.getSelectedItem()).getId(),
                    ((Publisher) cbPublisher.getSelectedItem()).getId(),
                    ((Category) cbCategory.getSelectedItem()).getId(),
                    // Chuyển đổi dữ liệu từ String sang kiểu số
                    Integer.parseInt(tfYear.getText()),
                    Double.parseDouble(tfPrice.getText()),
                    Integer.parseInt(tfStock.getText())
            );
            // Gọi phương thức insert của DAO
            if (bookDAO.insertBook(b)) {
                // Hiển thị thông báo thành công và tải lại bảng
                JOptionPane.showMessageDialog(this, "Thêm sách thành công");
                loadBooks();
            } else {
                // Hiển thị thông báo thất bại
                JOptionPane.showMessageDialog(this, "Thêm sách thất bại");
            }
        } catch (Exception ex) {
            // Xử lý lỗi và hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    // Phương thức xử lý cập nhật thông tin sách
    private void updateBook() {
        try {
            // Tạo đối tượng Book từ dữ liệu nhập liệu (bao gồm ID)
            Book b = new Book(
                    Integer.parseInt(tfId.getText()), // Lấy ID từ ô nhập liệu
                    tfTitle.getText(),
                    ((Author) cbAuthor.getSelectedItem()).getId(),
                    ((Publisher) cbPublisher.getSelectedItem()).getId(),
                    ((Category) cbCategory.getSelectedItem()).getId(),
                    Integer.parseInt(tfYear.getText()),
                    Double.parseDouble(tfPrice.getText()),
                    Integer.parseInt(tfStock.getText())
            );
            // Gọi phương thức update của DAO
            if (bookDAO.updateBook(b)) {
                // Hiển thị thông báo thành công và tải lại bảng
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadBooks();
            } else {
                // Hiển thị thông báo thất bại
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        } catch (Exception ex) {
            // Xử lý lỗi và hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    // Phương thức xử lý xóa sách
    private void deleteBook() {
        try {
            // Lấy ID sách cần xóa
            int id = Integer.parseInt(tfId.getText());
            // Hiển thị hộp thoại xác nhận xóa
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xoá?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            // Nếu người dùng xác nhận xóa
            if (confirm == JOptionPane.YES_OPTION) {
                // Gọi phương thức delete của DAO
                if (bookDAO.deleteBook(id)) {
                    // Hiển thị thông báo thành công và tải lại bảng
                    JOptionPane.showMessageDialog(this, "Xoá thành công");
                    loadBooks();
                } else {
                    // Hiển thị thông báo thất bại
                    JOptionPane.showMessageDialog(this, "Xoá thất bại");
                }
            }
        } catch (Exception ex) {
            // Xử lý lỗi và hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    // Phương thức xử lý tìm kiếm sách
    private void searchBook() {
        // Lấy từ khóa tìm kiếm và chuyển về chữ thường
        String keyword = tfSearch.getText().trim().toLowerCase();
        // Nếu từ khóa rỗng, tải lại toàn bộ danh sách
        if (keyword.isEmpty()) {
            loadBooks();
            return;
        }
        // Lấy toàn bộ danh sách sách và các DAO liên quan
        List<Book> books = bookDAO.getAllBooks();
        AuthorDAO authorDAO = new AuthorDAO();
        PublisherDAO publisherDAO = new PublisherDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        // Xóa dữ liệu cũ trên bảng
        tableModel.setRowCount(0);
        // Duyệt qua danh sách sách để tìm kiếm
        for (Book b : books) {
            // Khởi tạo tên mặc định
            String authorName = "";
            String publisherName = "";
            String categoryName = "";

            // Tìm tên Tác giả, NXB, Thể loại
            for (Author a : authorDAO.getAllAuthors()) { if (a.getId() == b.getAuthorId()) { authorName = a.getName(); break; } }
            for (Publisher p : publisherDAO.getAllPublishers()) { if (p.getId() == b.getPublisherId()) { publisherName = p.getName(); break; } }
            for (Category c : categoryDAO.getAllCategories()) { if (c.getId() == b.getCategoryId()) { categoryName = c.getName(); break; } }

            // Kiểm tra nếu từ khóa khớp với bất kỳ trường nào (ID, tiêu đề, tên tác giả/NXB/thể loại, năm, giá, tồn kho)
            if (String.valueOf(b.getId()).contains(keyword) ||
                b.getTitle().toLowerCase().contains(keyword) ||
                authorName.toLowerCase().contains(keyword) ||
                publisherName.toLowerCase().contains(keyword) ||
                categoryName.toLowerCase().contains(keyword) ||
                String.valueOf(b.getYear()).contains(keyword) ||
                String.valueOf(b.getPrice()).contains(keyword) ||
                String.valueOf(b.getStock()).contains(keyword)) {
                // Thêm hàng dữ liệu nếu khớp
                tableModel.addRow(new Object[]{
                        b.getId(), b.getTitle(), authorName, publisherName, categoryName,
                        b.getYear(), b.getPrice(), b.getStock()
                });
            }
        }
    }

    // ====== CLASS BO GÓC CHO Ô NHẬP ======
    // Lớp nội bộ để tạo hiệu ứng bo tròn cho viền các ô nhập liệu
    static class RoundedBorder extends AbstractBorder {
        private int radius;
        RoundedBorder(int radius) { this.radius = radius; }
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            // Vẽ hình chữ nhật bo góc
            g.setColor(Color.LIGHT_GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
        @Override
        public Insets getBorderInsets(Component c) {
            // Điều chỉnh khoảng cách nội dung bên trong border
            return new Insets(this.radius+1, this.radius+1, this.radius+1, this.radius+1);
        }
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            // Điều chỉnh khoảng cách nội dung bên trong border (override)
            insets.left = insets.right = insets.top = insets.bottom = this.radius+1;
            return insets;
        }
    }

    // ====== HÀM TIỆN ÍCH CHỌN COMBOBOX THEO TÊN ======
    // Phương thức helper để chọn item trong JComboBox dựa trên tên hiển thị
    private <T> void setSelectedComboBox(JComboBox<T> comboBox, String name) {
        // Duyệt qua tất cả các item trong ComboBox
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            Object item = comboBox.getItemAt(i);
            String itemName = "";
            // Lấy tên của item (tùy thuộc vào kiểu dữ liệu Tác giả, NXB, Thể loại)
            if (item instanceof Author) itemName = ((Author) item).getName();
            else if (item instanceof Publisher) itemName = ((Publisher) item).getName();
            else if (item instanceof Category) itemName = ((Category) item).getName();
            // Nếu tên khớp với tên cần chọn
            if (itemName.equals(name)) {
                // Chọn item đó trong ComboBox
                comboBox.setSelectedIndex(i);
                break; // Thoát vòng lặp sau khi tìm thấy
            }
        }
    }
}