-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20221103.3a7b77d22e
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 01, 2023 lúc 03:56 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `webts`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` text COLLATE utf8_unicode_ci NOT NULL,
  `lastname` text COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `sdt` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `roles` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`username`, `password`, `firstname`, `lastname`, `address`, `sdt`, `roles`) VALUES
('BinhBui', '123456', 'Binh', 'Bui', 'Dai Phong', '01344156', 'KH'),
('NguyenTran', '06042002', 'Nguyen', 'TranJP', 'Cam le, Da Nang', '022223333', 'KH'),
('PhucNguyen', '23102002', 'Phuc', 'Nguyen', 'Dai Cuong', '0111123', 'AD'),
('SangHo', '123456', 'Sang', 'Hồ', 'Dai thanh', '01234', 'KH'),
('TienPham', '040916', 'Tien', 'Pham', 'Dai Phong', '0334455', 'KH');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `iddh` int(11) NOT NULL,
  `idts` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `isdeliver` varchar(1) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`iddh`, `idts`, `username`, `isdeliver`) VALUES
(28, '4', 'NguyenTran', '1'),
(29, '5', 'NguyenTran', '1'),
(30, '1', 'BinhBui', '0'),
(31, '2', 'BinhBui', '0'),
(32, '3', 'BinhBui', '0'),
(33, '1', 'NguyenTran', '0'),
(45, '1', 'TienPham', '1'),
(46, '2', 'TienPham', '1'),
(47, '4', 'TienPham', '0'),
(48, '5', 'TienPham', '0'),
(49, '1', 'NguyenTran', '0'),
(50, '1', 'NguyenTran', '0'),
(51, '1', 'NguyenTran', '0'),
(52, '1', 'NguyenTran', '0'),
(53, '3', 'NguyenTran', '0'),
(54, '1', 'NguyenTran', '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `trasua`
--

CREATE TABLE `trasua` (
  `id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `loai` text COLLATE utf8_unicode_ci NOT NULL,
  `kichthuoc` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `gia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `trasua`
--

INSERT INTO `trasua` (`id`, `loai`, `kichthuoc`, `gia`) VALUES
('1', 'tran chau duong den', 'M', 30000),
('2', 'vi dau tay', 'M', 35000),
('3', 'vi viet quat', 'M', 38000),
('4', 'thai xanh', 'M', 40000),
('5', 'thai xanh', 'L', 60000);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`iddh`),
  ADD KEY `idts` (`idts`),
  ADD KEY `username` (`username`);

--
-- Chỉ mục cho bảng `trasua`
--
ALTER TABLE `trasua`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `iddh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `idts` FOREIGN KEY (`idts`) REFERENCES `trasua` (`id`),
  ADD CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `admin` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
