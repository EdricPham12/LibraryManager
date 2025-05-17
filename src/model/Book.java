package model;

public class Book {
    // Thuộc tính của lớp Book
    private int id;
    private String title;
    private String author;
    private int year;

    // Constructor không tham số (mặc định)
    public Book() {}

    // Constructor có tham số để khởi tạo đối tượng Book
    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getter và Setter cho từng thuộc tính
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}

// Lớp BookDAO chứa các phương thức để tương tác với bảng Book trong cơ sở dữ liệu
// Bao gồm các phương thức thêm, lấy tất cả sách từ CSDL