
CREATE DATABASE IF NOT EXISTS `ltwebct4st6`
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `ltwebct4st6`;

CREATE TABLE IF NOT EXISTS `GiaoVien` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `User` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `GiaoVien` (`name`, `address`)
VALUES
    ('Nguyễn H. Trung', 'Hồ Chí Minh');

INSERT INTO `User` (`username`, `email`, `password`)
VALUES
    ('minhnt', 'minhnt@gmail.com', '12345');

SELECT '--- Dữ liệu trong bảng GiaoVien ---' AS ThongBao;
SELECT * FROM `GiaoVien`;

SELECT '--- Dữ liệu trong bảng User ---' AS ThongBao;
SELECT * FROM `User`;