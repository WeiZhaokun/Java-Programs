-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 07, 2023 at 09:41 AM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flights`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookedflights`
--

DROP TABLE IF EXISTS `bookedflights`;
CREATE TABLE IF NOT EXISTS `bookedflights` (
  `FlightID` int NOT NULL,
  `CustomerID` int NOT NULL,
  PRIMARY KEY (`FlightID`,`CustomerID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bookedflights`
--

INSERT INTO `bookedflights` (`FlightID`, `CustomerID`) VALUES
(123, 456);

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
CREATE TABLE IF NOT EXISTS `bookings` (
  `BookingID` int NOT NULL,
  `CustomerID` int DEFAULT NULL,
  `FlightID` int DEFAULT NULL,
  `SeatTypeID` int DEFAULT NULL,
  `NumberOfSeats` int DEFAULT NULL,
  `TotalPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`BookingID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `FlightID` (`FlightID`),
  KEY `SeatTypeID` (`SeatTypeID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `CustomerID` int NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
CREATE TABLE IF NOT EXISTS `flights` (
  `flight_id` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Origin` varchar(50) DEFAULT NULL,
  `Destination` varchar(50) DEFAULT NULL,
  `flight_date` date DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`flight_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`flight_id`, `Origin`, `Destination`, `flight_date`, `price`) VALUES
('1', 'Wenzhou', 'Ningbo', '2023-12-07', NULL),
('2', 'Shanghai', 'Beijing', '2023-12-12', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `flightseatprices`
--

DROP TABLE IF EXISTS `flightseatprices`;
CREATE TABLE IF NOT EXISTS `flightseatprices` (
  `PriceID` int NOT NULL,
  `FlightID` int DEFAULT NULL,
  `SeatTypeID` int DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`PriceID`),
  KEY `FlightID` (`FlightID`),
  KEY `SeatTypeID` (`SeatTypeID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `flightseatprices`
--

INSERT INTO `flightseatprices` (`PriceID`, `FlightID`, `SeatTypeID`, `Price`) VALUES
(1, 1, 0, 100000.00),
(2, 2, 0, 50000.00);

-- --------------------------------------------------------

--
-- Table structure for table `pricechanges`
--

DROP TABLE IF EXISTS `pricechanges`;
CREATE TABLE IF NOT EXISTS `pricechanges` (
  `PriceID` int NOT NULL AUTO_INCREMENT,
  `IsApproved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PriceID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seattypes`
--

DROP TABLE IF EXISTS `seattypes`;
CREATE TABLE IF NOT EXISTS `seattypes` (
  `SeatTypeID` int NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Features` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SeatTypeID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `seattypes`
--

INSERT INTO `seattypes` (`SeatTypeID`, `Type`, `Features`) VALUES
(0, 'Business', NULL),
(1, 'Firstclass', 'Nothing'),
(2, 'Business', 'Nothing');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
