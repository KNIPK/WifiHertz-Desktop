-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 30 Mar 2013, 13:40
-- Wersja serwera: 5.5.24-log
-- Wersja PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `wifihertz`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `images`
--

CREATE TABLE IF NOT EXISTS `images` (
  `img_id` int(11) NOT NULL,
  `user_id_img` int(11) DEFAULT NULL,
  `img_name` varchar(25) DEFAULT NULL,
  `img_scr` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`img_id`),
  KEY `user_id_idx` (`user_id_img`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `images`
--

INSERT INTO `images` (`img_id`, `user_id_img`, `img_name`, `img_scr`) VALUES
(1, 1, 'Moj ogródek', 'd:\\moje_pliki\\krokodyl.png');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`user_id`, `firstname`, `lastname`, `login`, `passwd`) VALUES
(1, 'Maryam', 'Reed', 'scelerisque', 'Duis'),
(2, 'Kevyn', 'Carlson', 'ac', 'massa'),
(3, 'Kay', 'Mullen', 'urna', 'neque'),
(4, 'Timothy', 'Stevens', 'est', 'at'),
(5, 'Sade', 'Marks', 'ad', 'ante'),
(6, 'Christopher', 'Shields', 'Cum', 'Pellentesque'),
(7, 'Althea', 'Camacho', 'sociis', 'Mauris'),
(8, 'Tobias', 'Beard', 'habitant', 'pellentesque'),
(9, 'Allen', 'Osborne', 'tempor', 'parturient'),
(10, 'Armand', 'Johnson', 'mauris', 'elementum'),
(11, 'Driscoll', 'Forbes', 'ornare', 'eros'),
(12, 'Elijah', 'Swanson', 'Vivamus', 'dui'),
(13, 'Kieran', 'Valdez', 'Etiam', 'euismod'),
(14, 'Lael', 'Tucker', 'Sed', 'porttitor'),
(15, 'Malik', 'Baird', 'Proin', 'sapien'),
(16, 'Skyler', 'Hoffman', 'ipsum', 'augue'),
(17, 'Alden', 'Hoover', 'ad', 'cursus'),
(18, 'Miranda', 'Jacobs', 'In', 'Class'),
(19, 'Jillian', 'Mcgowan', 'nonummy', 'arcu'),
(20, 'Amena', 'Miranda', 'nulla', 'consectetuer'),
(21, 'Matthew', 'Rogers', 'mattis', 'vehicula'),
(22, 'Flavia', 'Solomon', 'Mauris', 'non'),
(23, 'Cullen', 'Hurst', 'libero', 'bibendum'),
(24, 'Galena', 'Bonner', 'iaculis', 'Vestibulum'),
(25, 'Brent', 'Dillard', 'tempus', 'natoque'),
(26, 'Shaine', 'Schmidt', 'sociosqu', 'congue'),
(27, 'Signe', 'Dejesus', 'imperdiet', 'volutpat'),
(28, 'Maggie', 'Meyers', 'neque', 'eget'),
(29, 'Lane', 'Hill', 'pulvinar', 'morbi'),
(30, 'Priscilla', 'Tanner', 'senectus', 'fames'),
(31, 'Julie', 'Benson', 'morbi', 'inceptos'),
(32, 'Danielle', 'Salas', 'ullamcorper', 'Ut'),
(33, 'Lysandra', 'Gilliam', 'Ut', 'at'),
(34, 'Ferdinand', 'Martinez', 'accumsan', 'imperdiet'),
(35, 'Amela', 'Henderson', 'quis', 'volutpat'),
(36, 'Kalia', 'Graves', 'Morbi', 'scelerisque'),
(37, 'Jaquelyn', 'Hess', 'iaculis', 'posuere'),
(38, 'Gavin', 'Woodard', 'natoque', 'conubia'),
(39, 'Belle', 'Hoffman', 'Pellentesque', 'enim'),
(40, 'Abdul', 'Jensen', 'Donec', 'justo'),
(41, 'Fletcher', 'Patton', 'Aliquam', 'fames'),
(42, 'Dana', 'Marks', 'convallis', 'eleifend'),
(43, 'Noelle', 'Stuart', 'inceptos', 'semper'),
(44, 'Rachel', 'Irwin', 'felis', 'sit'),
(45, 'Athena', 'Hardy', 'elit', 'dis'),
(46, 'Emi', 'Chase', 'ante', 'placerat'),
(47, 'Blake', 'Rasmussen', 'cursus', 'natoque'),
(48, 'Renee', 'Hardin', 'tincidunt', 'ligula'),
(49, 'Quemby', 'Jackson', 'quam', 'aptent'),
(50, 'Kitra', 'Meyer', 'hymenaeos', 'nostra'),
(51, 'Camden', 'Moon', 'faucibus', 'Class'),
(52, 'Aurora', 'Valentine', 'quam', 'venenatis'),
(53, 'Hyacinth', 'Cooke', 'nunc', 'torquent'),
(54, 'Olympia', 'Lara', 'magna', 'venenatis'),
(55, 'Lane', 'Cole', 'Nunc', 'nascetur'),
(56, 'Levi', 'Jenkins', 'habitant', 'Nam'),
(57, 'Stone', 'Bolton', 'commodo', 'sapien'),
(58, 'Connor', 'Hood', 'arcu', 'hymenaeos'),
(59, 'Amaya', 'Talley', 'blandit', 'tristique'),
(60, 'Wendy', 'Jimenez', 'penatibus', 'massa'),
(61, 'Camille', 'Pittman', 'nibh', 'Aliquam'),
(62, 'Russell', 'Chan', 'sagittis', 'consectetuer'),
(63, 'Rudyard', 'Clarke', 'ultricies', 'malesuada'),
(64, 'Aladdin', 'Christensen', 'mauris', 'Duis'),
(65, 'Lila', 'Barrett', 'sagittis', 'ad'),
(66, 'Odysseus', 'Summers', 'vel', 'blandit'),
(67, 'Xanthus', 'Mcgee', 'nisl', 'sed'),
(68, 'Summer', 'Malone', 'lacinia', 'Maecenas'),
(69, 'Burton', 'Fisher', 'tincidunt', 'tristique'),
(70, 'Kathleen', 'House', 'dui', 'aliquet'),
(71, 'Prescott', 'Peters', 'tempus', 'sociosqu'),
(72, 'Logan', 'Marsh', 'placerat', 'turpis'),
(73, 'Kalia', 'Hood', 'Etiam', 'Pellentesque'),
(74, 'Erica', 'Contreras', 'placerat', 'porttitor'),
(75, 'Dante', 'Zimmerman', 'pretium', 'vulputate'),
(76, 'Dexter', 'Mejia', 'conubia', 'eget'),
(77, 'Cole', 'Henson', 'sem', 'facilisi'),
(78, 'Martina', 'Crawford', 'felis', 'commodo'),
(79, 'Lewis', 'Dominguez', 'pellentesque', 'vitae'),
(80, 'Galvin', 'Rosa', 'ligula', 'Nullam'),
(81, 'Neil', 'Becker', 'Etiam', 'mi'),
(82, 'Jack', 'Calhoun', 'natoque', 'amet'),
(83, 'Kylie', 'Gordon', 'volutpat', 'iaculis'),
(84, 'Arden', 'Gomez', 'eros', 'eros'),
(85, 'Anthony', 'Reynolds', 'euismod', 'Class'),
(86, 'Illiana', 'Britt', 'purus', 'nibh'),
(87, 'Astra', 'Reyes', 'vitae', 'mollis'),
(88, 'Yvonne', 'Fuentes', 'Fusce', 'accumsan'),
(89, 'Basia', 'Chen', 'in', 'porttitor'),
(90, 'Iliana', 'Park', 'sociosqu', 'penatibus'),
(91, 'Maris', 'Hughes', 'Cum', 'Nunc'),
(92, 'Lev', 'Roy', 'montes', 'habitant'),
(93, 'Robert', 'Baker', 'torquent', 'purus'),
(94, 'Caleb', 'Duran', 'ad', 'scelerisque'),
(95, 'Chester', 'Montgomery', 'senectus', 'nisl'),
(96, 'Mira', 'Park', 'dui', 'luctus'),
(97, 'Robert', 'Chang', 'quam', 'conubia'),
(98, 'Kasper', 'Oneil', 'blandit', 'laoreet'),
(99, 'Kylie', 'Baird', 'leo', 'ipsum'),
(100, 'Acton', 'Webster', 'nostra', 'et');

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id_img`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
