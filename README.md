# Project Lập Trình Web

Đây là project web demo môn Lập trình Web, sử dụng Java Servlets, JSP và MySQL theo mô hình MVC (Model-View-Controller). Project tập trung vào việc quản lý người dùng (đăng nhập, đăng ký, phân quyền) và các chức năng CRUD (Thêm, Sửa, Xóa, Xem) cho Danh mục sản phẩm (Category) có kèm upload hình ảnh.

## 1. Tính năng chính

### 1.1. Quản lý Người dùng (User)
* **Trang chào mừng (Guest):** Trang chủ mặc định cho khách (chưa đăng nhập).
* **Đăng ký (Register):** Cho phép người dùng tạo tài khoản mới. Tài khoản mới tự động được gán `roleid = 2` (User).
* **Đăng nhập (Login):** Xác thực người dùng.
* **Phân quyền (Role-based):** Sử dụng `WaitingController` để phân luồng:
    * **Admin (`roleid == 1`):** Chuyển đến trang quản lý Category.
    * **User (`roleid == 2`):** Chuyển đến trang chủ của User.
* **Quên mật khẩu (Forgot Password):** Luồng cho phép người dùng nhập email và đặt lại mật khẩu mới.
* **Đăng xuất (Logout):** Hủy session và chuyển về trang login.

### 1.2. Quản lý Danh mục (Category - Yêu cầu Admin)
* **Hiển thị (Read):** Hiển thị danh sách Category (kèm hình ảnh).
* **Thêm (Create):** Thêm Category mới (kèm upload ảnh).
* **Sửa (Update):** Sửa Category (hiển thị ảnh cũ, cho phép upload ảnh mới và tự động xóa ảnh cũ).
* **Xóa (Delete):** Xóa Category.
* **Hiển thị ảnh:** Sử dụng `DownloadImageController` để đọc file ảnh từ thư mục lưu trữ bên ngoài và hiển thị lên web.

## 2. Công nghệ sử dụng
* **Backend:** Java (JDK 21+), Jakarta Servlet 6.1, JSP 4.0.
* **Frontend:** JSP, JSTL 3.0, Bootstrap 5.
* **Database:** MySQL.
* **Server:** Apache Tomcat v10.1+ (bắt buộc để hỗ trợ Jakarta EE).
* **Build:** Apache Maven.
* **Upload:** Dùng `@MultipartConfig` (tích hợp trong Servlet).
* **Thư viện khác:** Apache Commons IO (để hiển thị ảnh).

## 3. Hướng dẫn cài đặt và sử dụng

### 3.1. Yêu cầu phần mềm
* JDK 21 (hoặc JDK 24 như bạn đang dùng).
* Apache Maven 3.x+.
* MySQL Server.
* Apache Tomcat v10.1+.
* Spring Tool Suite (STS) hoặc Eclipse IDE for Enterprise Java.

### 3.2. Cài đặt Database
1.  Mở `MySQL Workbench` (hoặc công cụ CSDL của bạn).
2.  Chạy lệnh sau để tạo database:
    ```sql
    CREATE DATABASE IF NOT EXISTS ltwebct4st6
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    ```
   
3.  Chạy toàn bộ nội dung file `data.sql` để tạo bảng `User`, `Category` và dữ liệu mẫu.

### 3.3. Cài đặt Thư mục Upload (Xử lý nâng cấp sau)
1.  **Bắt buộc:** Trên máy của bạn, hãy tạo một thư mục tại ổ đĩa `E:` với tên `upload`.
2.  Đường dẫn tuyệt đối phải là: `E:\upload` (đúng như trong file `Constant.java`).
3.  **Tùy chọn (Khuyến nghị):** Để dữ liệu mẫu (`data.sql`) hiển thị ảnh, bạn hãy:
    * Tạo một thư mục con tên `category` bên trong `E:\upload`.

### 3.4. Cài đặt Project
1.  Import project vào STS/Eclipse (`File` -> `Import...` -> `Existing Maven Projects`).
2.  Chuột phải vào project -> `Maven` -> `Update Project...` (và tick `Force Update...`) để tải các thư viện trong `pom.xml`.
3.  Chuột phải vào project -> `Run As` -> `Run on Server` (chọn Tomcat 10.1 của bạn).

### 3.5. Hướng dẫn sử dụng
1.  **Trang chủ (Khách):**
    * Truy cập `http://localhost:8080/[tên_project]/`
    * Bạn sẽ thấy trang `welcome.jsp`.

2.  **Đăng nhập Admin:**
    * Nhấn "Đăng nhập".
    * Sử dụng tài khoản admin mẫu (từ `data.sql`):
        * **Username:** `minhnt`
        * **Password:** `12345`
    * Bạn sẽ được chuyển đến trang Quản lý Category (`/admin/category/list`).

3.  **Đăng ký User (Role 2):**
    * Từ trang Login, nhấn "Đăng ký".
    * Tạo tài khoản mới. Tài khoản này sẽ tự động có `roleid = 2`.
    * Đăng nhập bằng tài khoản này sẽ chuyển bạn đến trang User (`/home`).
