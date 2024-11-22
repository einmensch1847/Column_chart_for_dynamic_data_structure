-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2024 at 08:56 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `data_structure`
--

-- --------------------------------------------------------

--
-- Table structure for table `execution_data`
--

CREATE TABLE `execution_data` (
  `id` int(11) NOT NULL,
  `execution_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `array_size` int(11) DEFAULT NULL,
  `insert_time` bigint(20) DEFAULT NULL,
  `remove_time` bigint(20) DEFAULT NULL,
  `at_time` bigint(20) DEFAULT NULL,
  `set_time` bigint(20) DEFAULT NULL,
  `find_time` bigint(20) DEFAULT NULL,
  `max_time` bigint(20) DEFAULT NULL,
  `min_time` bigint(20) DEFAULT NULL,
  `size_time` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `execution_data`
--

INSERT INTO `execution_data` (`id`, `execution_time`, `array_size`, `insert_time`, `remove_time`, `at_time`, `set_time`, `find_time`, `max_time`, `min_time`, `size_time`) VALUES
(3, '2024-10-16 17:50:50', 10, 4295, 1741, 862, 1570, 1099, 665, 768, 152),
(4, '2024-10-16 17:51:54', 10, 5038, 1506, 1521, 2332, 1319, 560, 438, 106),
(9, '2024-10-16 18:32:53', 10, 5217, 2129, 1179, 1586, 1074, 407, 434, 129),
(10, '2024-10-16 18:34:48', 10, 4240, 2996, 1369, 1378, 915, 372, 398, 105),
(14, '2024-10-16 18:45:21', 10, 4798, 2697, 752, 1182, 884, 739, 407, 148),
(16, '2024-10-17 05:29:29', 10, 4765, 1782, 1758, 2911, 2455, 657, 727, 127),
(23, '2024-10-17 05:55:38', 10, 7532, 2935, 792, 1305, 1062, 463, 408, 161),
(26, '2024-10-17 06:04:07', 10, 5602, 4964, 1823, 1822, 2062, 625, 366, 153),
(30, '2024-10-17 06:21:14', 10, 4470, 1952, 1647, 3627, 2165, 757, 364, 111),
(31, '2024-10-17 06:24:08', 10, 4018, 1450, 1436, 1318, 954, 328, 1120, 172),
(32, '2024-10-17 06:24:25', 10, 4861, 2628, 1608, 2096, 884, 655, 512, 111),
(33, '2024-10-17 06:25:12', 10, 14420, 6643, 2726, 4391, 2925, 681, 694, 185),
(34, '2024-10-17 06:25:47', 10, 7180, 4674, 1240, 1631, 1216, 458, 422, 180),
(35, '2024-10-17 06:27:01', 10, 4336, 3875, 1553, 1358, 1380, 411, 329, 103);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `execution_data`
--
ALTER TABLE `execution_data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `execution_data`
--
ALTER TABLE `execution_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
