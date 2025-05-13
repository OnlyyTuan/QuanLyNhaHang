bananquanlynhahangquanlynhahang-- --------------------------------------------------------
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

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
