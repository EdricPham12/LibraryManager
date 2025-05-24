# Library Manager

## 📎 Link GitHub
[https://github.com/ten-tai-khoan/ten-repo](https://github.com/EdricPham12/LibraryManager.git)

## 📋 Link Trello quản lý công việc
[https://trello.com/b/duong-dan-bang-trello](https://trello.com/invite/b/67f8c7e34cdb12321e15ebca/ATTIe987c5a3a4b26c2569c86ea640e6024d22E3FC8D/application-development)

---

## 📚 Thư viện sử dụng

- [AbsoluteLayout](https://mvnrepository.com/artifact/org.netbeans.modules/absolutelayout) (`AbsoluteLayout.jar`)
- [JCalendar](https://toedter.com/jcalendar/) (`jcalendar-1.4.jar`)
- [JCommon](https://mvnrepository.com/artifact/org.jfree/jcommon) (`jcommon-1.0.23.jar`)
- [JFreeChart](https://www.jfree.org/jfreechart/) (`jfreechart-1.0.19.jar`)
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (`mysql-connector-j-9.3.0.jar`)

> **Lưu ý:**  
> **Tất cả các file `.jar` này đã được đặt trong thư mục `lib/` (trong file SOURCE CODE). Khi mở project, hãy add toàn bộ file trong `lib/` vào phần Libraries của project.**

---

## 🚀 Hướng dẫn chạy code

1. **Clone project về máy:**
   ```bash
   git clone https://github.com/EdricPham12/LibraryManager.git
   ```

2. **Mở project bằng NetBeans.**

3. **Add các thư viện trong thư mục `lib/` vào project:**
   - Chuột phải vào project → Properties → Libraries → Add JAR/Folder → chọn tất cả file `.jar` trong `lib/` (trong file SOURCE CODE).

4. **Cấu hình kết nối database:**
   - Đảm bảo đã tạo database `library` và import dữ liệu mẫu SQL từ file `library` (trong file MySQL).
   - Sửa file cấu hình kết nối DB trong code (`DBConnect.java`) tương ứng theo MySQL Connections nếu cần (URL, USER, PASSWORD).

5. **Clean and Build project.**

6. **Run project.**

---
## 👨‍💻 NHÓM THỰC HIỆN

- **Nguyễn Hồng Doãn Hân** – Trưởng nhóm  
  MSSV: 29211151029  
  Vai trò: Viết panel nhân viên, panel danh mục, kiểm thử phần mềm.

- **Phạm Thuận Hoàng**  
  MSSV: 29219039436 
  Vai trò: Viết DBconnect(lớp để kết nối cơ sỡ dữ liệu), các class DAO(Database Access Object) xử lý tương tác với dữ liệu,các lớp Model (Mô hình dữ liệu), panel mượn trả, và Thống kê/ Báo Cáo(xem thống kê và biểu đồ tròn hiển thị thống kê)

- **Thái Nhật Duy**  
  MSSV: 29211154744  
  Vai trò: Viết Main(Quản lý các class panel), panel sách, panel độc giả, kiểm thử phần mềm.

- **Trần Lê Khải Hoàn**  
  MSSV: 29219038416  
  Vai trò: Viết panel nhà suât bản, panel tác giả, kiểm thử phần mềm.

---

> **Giáo viên hướng dẫn:** Msc.Nguyen Trung Thuan

