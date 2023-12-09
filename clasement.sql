-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Des 2023 pada 07.08
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clasement`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `matchs`
--

CREATE TABLE `matchs` (
  `match_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `schedule` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `away_team_id` bigint(20) DEFAULT NULL,
  `home_team_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `matchs`
--

INSERT INTO `matchs` (`match_id`, `created_at`, `schedule`, `status`, `updated_at`, `away_team_id`, `home_team_id`) VALUES
(10, '2023-06-22 19:21:57', '2023-07-01', 'DONE', '2023-06-22 19:29:59', 1, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `teams`
--

CREATE TABLE `teams` (
  `team_id` bigint(20) NOT NULL,
  `away_goal` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `draw` int(11) DEFAULT NULL,
  `home_goal` int(11) DEFAULT NULL,
  `lose` int(11) DEFAULT NULL,
  `number_of_match` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `team_name` varchar(255) DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `win` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `teams`
--

INSERT INTO `teams` (`team_id`, `away_goal`, `city`, `created_at`, `draw`, `home_goal`, `lose`, `number_of_match`, `points`, `team_name`, `updated_at`, `win`) VALUES
(1, 1, 'Jakarta', '2023-06-21 14:40:37', 0, 0, 0, 1, 3, 'Suka Bola FC', '2023-06-22 19:29:59', 1),
(2, 0, 'Surabaya', '2023-06-21 20:31:14', 0, 0, 1, 1, 0, 'Kopi Enak FC', '2023-06-22 19:29:59', 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `matchs`
--
ALTER TABLE `matchs`
  ADD PRIMARY KEY (`match_id`),
  ADD KEY `FKdawwnkcp75ktrbub2u7t6nb9a` (`away_team_id`),
  ADD KEY `FKht2hjkakc5q74xq4b22icugh0` (`home_team_id`);

--
-- Indeks untuk tabel `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`team_id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `matchs`
--
ALTER TABLE `matchs`
  MODIFY `match_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `teams`
--
ALTER TABLE `teams`
  MODIFY `team_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `matchs`
--
ALTER TABLE `matchs`
  ADD CONSTRAINT `FKdawwnkcp75ktrbub2u7t6nb9a` FOREIGN KEY (`away_team_id`) REFERENCES `teams` (`team_id`),
  ADD CONSTRAINT `FKht2hjkakc5q74xq4b22icugh0` FOREIGN KEY (`home_team_id`) REFERENCES `teams` (`team_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
