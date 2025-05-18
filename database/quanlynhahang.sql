-- Host:                         127.0.0.1
-- Server version:               8.4.4 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quanlynhahang
CREATE DATABASE IF NOT EXISTS `quanlynhahang` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlynhahang`;

-- Dumping structure for table quanlynhahang.banan
CREATE TABLE IF NOT EXISTS `banan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.chitiethoadon
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_hoaDon` int DEFAULT NULL,
  `id_monAn` int DEFAULT NULL,
  `soLuong` int DEFAULT NULL,
  `tongGia` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_hoaDon` (`id_hoaDon`),
  KEY `id_monAn` (`id_monAn`),
  CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`id_hoaDon`) REFERENCES `hoadon` (`id`),
  CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`id_monAn`) REFERENCES `monan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.chitietquyen
CREATE TABLE IF NOT EXISTS `chitietquyen` (
  `id_quyen` int DEFAULT NULL,
  `id_chucNang` int DEFAULT NULL,
  `hanhDong` varchar(255) DEFAULT NULL,
  KEY `id_quyen` (`id_quyen`),
  KEY `id_chucNang` (`id_chucNang`),
  CONSTRAINT `chitietquyen_ibfk_1` FOREIGN KEY (`id_quyen`) REFERENCES `quyen` (`id`),
  CONSTRAINT `chitietquyen_ibfk_2` FOREIGN KEY (`id_chucNang`) REFERENCES `chucnang` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.chucnang
CREATE TABLE IF NOT EXISTS `chucnang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.danhmucmonan
CREATE TABLE IF NOT EXISTS `danhmucmonan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_nhanVien` int DEFAULT NULL,
  `id_banAn` int DEFAULT NULL,
  `tenKhach` varchar(255) DEFAULT NULL,
  `tongTien` int DEFAULT NULL,
  `thoigian` datetime DEFAULT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_banAn` (`id_banAn`),
  KEY `id_nhanVien` (`id_nhanVien`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`id_banAn`) REFERENCES `banan` (`id`),
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`id_nhanVien`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.monan
CREATE TABLE IF NOT EXISTS `monan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_danhMuc` int DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `hinhAnh` varchar(255) DEFAULT NULL,
  `gia` int DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_danhMuc` (`id_danhMuc`),
  CONSTRAINT `monan_ibfk_1` FOREIGN KEY (`id_danhMuc`) REFERENCES `danhmucmonan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoTen` varchar(255) DEFAULT NULL,
  `gioiTinh` varchar(255) DEFAULT NULL,
  `chucVu` varchar(255) DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.quyen
CREATE TABLE IF NOT EXISTS `quyen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table quanlynhahang.taikhoan
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_nhanVien` int DEFAULT NULL,
  `id_quyen` int DEFAULT NULL,
  `taiKhoan` varchar(255) DEFAULT NULL,
  `matKhau` varchar(255) DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_nhanVien` (`id_nhanVien`),
  KEY `id_quyen` (`id_quyen`),
  CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`id_nhanVien`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`id_quyen`) REFERENCES `quyen` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Insert sample data for quyen (roles)
-- INSERT INTO `quyen` (`id`, `ten`) VALUES
-- (1, 'Admin'),
-- (2, 'Nhân viên');

-- Dữ liệu cho bảng danhmucmonan (danh mục món ăn)
INSERT INTO `danhmucmonan` (`id`, `ten`) VALUES
(1, 'Món chính'),
(2, 'Đồ uống');

-- Dữ liệu cho bảng banan
INSERT INTO `banan` (`ten`, `trangThai`) VALUES
('Bàn 1', 1),
('Bàn 2', 0),
('Bàn 3', 1),
('Bàn 4', 0),
('Bàn 5', 1);

-- Dữ liệu cho bảng monan
INSERT INTO `monan` (`id_danhMuc`, `ten`, `hinhAnh`, `gia`, `trangThai`) VALUES
(1, 'Phở Bò', 'pho_bo.jpg', 40000, 1),
(1, 'Bún Chả', 'bun_cha.jpg', 35000, 1),
(2, 'Cà phê sữa', 'ca_phe_sua.jpg', 20000, 1),
(2, 'Trà đá', 'tra_da.jpg', 5000, 1),
(1, 'Cơm Tấm', 'com_tam.jpg', 30000, 1);

-- Dữ liệu cho bảng nhanvien
INSERT INTO `nhanvien` (`hoTen`, `gioiTinh`, `chucVu`, `trangThai`) VALUES
('Nguyễn Văn A', 'Nam', 'Quản lý', 1),   -- id = 1
('Trần Thị B', 'Nữ', 'Nhân viên', 1),    -- id = 2
('Lê Văn C', 'Nam', 'Nhân viên', 1),     -- id = 3
('Phạm Thị D', 'Nữ', 'Nhân viên', 0),    -- id = 4
('Vũ Văn E', 'Nam', 'Nhân viên', 1);     -- id = 5

-- Dữ liệu cho bảng taikhoan (liên kết với nhanvien)
INSERT INTO `taikhoan` (`id_nhanVien`, `id_quyen`, `taiKhoan`, `matKhau`, `trangThai`) VALUES
(1, 1, 'admin', '123', 1),
(2, 2, 'nvb', 'nvb123', 1),
(3, 2, 'nvc', 'nvc123', 1),
(4, 2, 'nvd', 'nvd123', 0),
(5, 2, 'nve', 'nve123', 1);

-- Dữ liệu cho bảng hoadon (giả sử đã có dữ liệu cho banan và nhanvien)
INSERT INTO `hoadon` (`id_nhanVien`, `id_banAn`, `tenKhach`, `tongTien`, `thoigian`, `ghiChu`) VALUES
(1, 1, 'Khách 1', 120000, '2024-05-17 10:00:00', 'Thanh toán tiền mặt'),
(2, 2, 'Khách 2', 80000, '2024-05-17 11:00:00', ''),
(3, 3, 'Khách 3', 150000, '2024-05-17 12:00:00', 'Đã chuyển khoản'),
(5, 5, 'Khách 4', 50000, '2024-05-17 13:00:00', ''),
(1, 4, 'Khách 5', 200000, '2024-05-17 14:00:00', 'Khách quen');

INSERT INTO `quyen` (`id`, `ten`) VALUES
(1, 'Admin'),
(2, 'Nhân viên');

-- Dữ liệu cho bảng chucnang
INSERT INTO `chucnang` (`id`, `ten`) VALUES
(1, 'Nhân viên'),
(2, 'Bàn ăn'),
(3, 'Món ăn'),
(4, 'Hóa đơn'),
(5, 'Quyền'),
(6, 'Tài khoản');



-- Dữ liệu cho bảng chitietquyen (role permissions)
-- Admin có tất cả quyền
INSERT INTO `chitietquyen` (`id_quyen`, `id_chucNang`, `hanhDong`) VALUES
-- Món ăn
(1, 1, 'Xem'),
(1, 1, 'Thêm'),
(1, 1, 'Sửa'),
(1, 1, 'Xóa'),
-- Bàn ăn
(1, 2, 'Xem'),
(1, 2, 'Thêm'),
(1, 2, 'Sửa'),
(1, 2, 'Xóa'),
-- Hóa đơn
(1, 3, 'Xem'),
(1, 3, 'Thêm'),
(1, 3, 'Sửa'),
(1, 3, 'Xóa'),
(1, 3, 'In'),
-- Nhân viên
(1, 4, 'Xem'),
(1, 4, 'Thêm'),
(1, 4, 'Sửa'),
(1, 4, 'Xóa'),
-- Phân quyền
(1, 5, 'Xem'),
(1, 5, 'Thêm'),
(1, 5, 'Sửa'),
(1, 5, 'Xóa'),
-- Tài khoản
(1, 6, 'Xem'),
(1, 6, 'Thêm'),
(1, 6, 'Sửa'),
(1, 6, 'Xóa');

-- Nhân viên chỉ có một số quyền hạn chế
INSERT INTO `chitietquyen` (`id_quyen`, `id_chucNang`, `hanhDong`) VALUES
-- Món ăn
(2, 1, 'Xem'),
-- Bàn ăn
(2, 2, 'Xem'),
(2, 2, 'Sửa'),
-- Hóa đơn
(2, 3, 'Xem'),
(2, 3, 'Thêm'),
(2, 3, 'Sửa'),
(2, 3, 'In'),
-- Nhân viên
(2, 4, 'Xem'),
-- Phân quyền
(2, 5, 'Xem'),
-- Tài khoản
(2, 6, 'Xem'),
(2, 6, 'Sửa');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
