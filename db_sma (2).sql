-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 08:23 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_sma`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_assets`
--

CREATE TABLE `t_assets` (
  `id_asset` varchar(10) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `qty` int(10) NOT NULL,
  `uom` varchar(10) NOT NULL,
  `lokasi_barang` varchar(10) NOT NULL,
  `jenis` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `total` double NOT NULL,
  `kondisi` varchar(100) NOT NULL,
  `tanggal_terima` date NOT NULL,
  `keterangan` text NOT NULL,
  `foto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_assets`
--

INSERT INTO `t_assets` (`id_asset`, `nama_barang`, `brand`, `qty`, `uom`, `lokasi_barang`, `jenis`, `category`, `price`, `total`, `kondisi`, `tanggal_terima`, `keterangan`, `foto`) VALUES
('ID01', 'Infocus', '', 2, '', 'TU', 'Barang Untuk Dipinjam', 'Elektronik', 2000000, 0, 'Baik', '2020-03-30', 'Boleh dipinjam di ruang TI', '-'),
('ID02', 'Terminals', '', 0, '', '', 'Barang Untuk Dipinjam', 'Elektronik', 0, 0, '', '2020-04-09', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `t_data_diri`
--

CREATE TABLE `t_data_diri` (
  `id_data` int(255) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `nis` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_data_diri`
--

INSERT INTO `t_data_diri` (`id_data`, `id_kelas`, `username`, `nis`) VALUES
(23, 3, 'Ayyasyi', '-'),
(24, 2, 'Abah', '1819117617'),
(25, 1, 'dean', '181711523');

-- --------------------------------------------------------

--
-- Table structure for table `t_kelas`
--

CREATE TABLE `t_kelas` (
  `id_kelas` int(11) NOT NULL,
  `nama_kelas` varchar(255) NOT NULL,
  `jurusan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_kelas`
--

INSERT INTO `t_kelas` (`id_kelas`, `nama_kelas`, `jurusan`) VALUES
(1, '-', '-'),
(2, 'XI RPL 1', 'Rekayasa Perangkat Lunak'),
(3, 'XI RPL 2', 'Rekayasa Perangkat Lunak');

-- --------------------------------------------------------

--
-- Table structure for table `t_login`
--

CREATE TABLE `t_login` (
  `username` varchar(100) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_login`
--

INSERT INTO `t_login` (`username`, `fullname`, `password`, `email`, `status`) VALUES
('Abah', 'Fajar Sidiq A', '6b849259f839228e28c9a8bb2fbfcc9a8077f9c6', 'Abah@gmail.com', 'user'),
('Ayyasyi', 'Hanif Abyan Ayyasyi', '97918c2e2419e2d62537312319eb2224e4438968', 'bbyasyi@gmail.com', 'admin'),
('dean', 'deanasu', 'e1bac5f80cb2b728addad6ca08a5c0142f43ddba', 'dean@gmail.com', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `t_planning`
--

CREATE TABLE `t_planning` (
  `id_planning` varchar(12) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` double NOT NULL,
  `description` text NOT NULL,
  `planning_date` date NOT NULL,
  `date_purchased` date NOT NULL,
  `status` enum('approved','not yet approved','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_planning`
--

INSERT INTO `t_planning` (`id_planning`, `nama_barang`, `qty`, `price`, `description`, `planning_date`, `date_purchased`, `status`) VALUES
('ID02', 'Headset', 12, 20000, 'YES', '2020-05-07', '2020-05-20', 'not yet approved'),
('ID03', 'PARFUM', 12, 23333, 'YES', '2020-05-07', '2020-05-13', 'not yet approved');

-- --------------------------------------------------------

--
-- Table structure for table `t_report`
--

CREATE TABLE `t_report` (
  `id_report` int(11) NOT NULL,
  `id_data` int(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `isi_report` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `t_transaksi`
--

CREATE TABLE `t_transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `id_asset` varchar(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `jmlh_pinjam` varchar(10) NOT NULL,
  `tgl_pinjam` datetime NOT NULL,
  `tgl_kembali` datetime DEFAULT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_transaksi`
--

INSERT INTO `t_transaksi` (`id_transaksi`, `id_asset`, `username`, `jmlh_pinjam`, `tgl_pinjam`, `tgl_kembali`, `status`) VALUES
('1', 'ID01', 'fadillf', '3', '2020-03-31 21:44:16', '2020-04-09 21:47:40', 'Selesai'),
('1c4f638a', 'ID01', 'fadil', '1', '2020-05-02 21:54:40', NULL, 'pinjam'),
('2', 'ID02', 'faldurahman', '1', '2020-04-04 00:00:00', '2020-04-06 22:13:31', 'Selesai'),
('46962b66', 'ID01', 'fadil', '', '2020-05-02 22:01:21', NULL, 'pinjam');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_assets`
--
ALTER TABLE `t_assets`
  ADD PRIMARY KEY (`id_asset`);

--
-- Indexes for table `t_data_diri`
--
ALTER TABLE `t_data_diri`
  ADD PRIMARY KEY (`id_data`),
  ADD KEY `username` (`username`),
  ADD KEY `id_kelas` (`id_kelas`);

--
-- Indexes for table `t_kelas`
--
ALTER TABLE `t_kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `t_login`
--
ALTER TABLE `t_login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `t_planning`
--
ALTER TABLE `t_planning`
  ADD PRIMARY KEY (`id_planning`);

--
-- Indexes for table `t_report`
--
ALTER TABLE `t_report`
  ADD PRIMARY KEY (`id_report`),
  ADD KEY `id_data` (`id_data`) USING BTREE,
  ADD KEY `username` (`username`) USING BTREE;

--
-- Indexes for table `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `username` (`username`),
  ADD KEY `id_assets` (`id_asset`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_data_diri`
--
ALTER TABLE `t_data_diri`
  MODIFY `id_data` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `t_kelas`
--
ALTER TABLE `t_kelas`
  MODIFY `id_kelas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_data_diri`
--
ALTER TABLE `t_data_diri`
  ADD CONSTRAINT `t_data_diri_ibfk_1` FOREIGN KEY (`username`) REFERENCES `t_login` (`username`),
  ADD CONSTRAINT `t_data_diri_ibfk_2` FOREIGN KEY (`id_kelas`) REFERENCES `t_kelas` (`id_kelas`);

--
-- Constraints for table `t_report`
--
ALTER TABLE `t_report`
  ADD CONSTRAINT `t_report_ibfk_1` FOREIGN KEY (`id_data`) REFERENCES `t_data_diri` (`id_data`),
  ADD CONSTRAINT `t_report_ibfk_2` FOREIGN KEY (`username`) REFERENCES `t_login` (`username`);

--
-- Constraints for table `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD CONSTRAINT `t_transaksi_ibfk_3` FOREIGN KEY (`id_asset`) REFERENCES `t_assets` (`id_asset`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
