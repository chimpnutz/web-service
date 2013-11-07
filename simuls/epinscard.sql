-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 07, 2013 at 10:12 AM
-- Server version: 5.6.11
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `epinscard`
--
CREATE DATABASE IF NOT EXISTS `epinscard` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `epinscard`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`USERNAME`, `PASSWORD`) VALUES
('admin', 'MVRFYUNGMDVYOGM9');

-- --------------------------------------------------------

--
-- Table structure for table `epins`
--

CREATE TABLE IF NOT EXISTS `epins` (
  `ID` int(10) unsigned NOT NULL,
  `EPIN` varchar(100) NOT NULL,
  `DENOM` int(10) unsigned NOT NULL,
  `TELCO_TYPE` varchar(2) NOT NULL,
  `UPLOADED_BY` varchar(50) NOT NULL,
  `TO_WHOM` varchar(50) DEFAULT NULL,
  `DATE_UPLOADED` datetime DEFAULT NULL,
  `DATE_USED` datetime DEFAULT NULL,
  `STATUS` varchar(1) NOT NULL,
  `TRANSACTIONID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`,`EPIN`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `epins`
--

INSERT INTO `epins` (`ID`, `EPIN`, `DENOM`, `TELCO_TYPE`, `UPLOADED_BY`, `TO_WHOM`, `DATE_UPLOADED`, `DATE_USED`, `STATUS`, `TRANSACTIONID`) VALUES
(0, 'QVZSQVp1WXo3TlQ5cG1OZHh4d2xBeFdDKzJNamRVbHpFdkd6aUQ3bXdKMHFmQ3I0RVlSNWtBPT0= ', 100, 'GT', 'lalala', 'retailer', '2013-10-23 09:53:04', '2013-10-23 09:53:06', '6', '1'),
(1, 'SG1yNkQ4cVpyajZCNXptclN4Zm9RREQ2a0luSzExVTR5R09IL1VTY1QrdGZKMWFBQTBqUDFBPT0= ', 100, 'GT', 'Admin', 'Retailer', '2013-10-17 11:01:51', '2013-10-17 11:01:54', '6', '1'),
(2, 'NEg2bUVTcGh3T0pldDNZZ3BoUmRYNDVFN2JhVXh4NG9Fdkd6aUQ3bXdKMkt2dzBSTXA0SE93PT0= ', 100, 'GT', 'asfsaf', 'fsasf', '2013-10-29 14:18:55', '2013-10-06 14:19:02', '6', '1'),
(3, 'dGd1aUZpcEVOSkN3TFhhR3pMdHlETENFcmFNcGthTDNFdkd6aUQ3bXdKMVcxakVOcW8zN0ZRPT0= ', 100, 'GT', 'asfsafasf', 'nmbnmb', '2013-10-18 14:19:00', '2013-10-18 14:19:05', '6', '1');

-- --------------------------------------------------------

--
-- Table structure for table `upload`
--

CREATE TABLE IF NOT EXISTS `upload` (
  `ID` int(10) unsigned NOT NULL,
  `FILENAME` varchar(100) NOT NULL,
  `UPLOADED_BY` varchar(50) NOT NULL,
  `DATE_UPLOADED` datetime NOT NULL,
  `STATUS` varchar(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
