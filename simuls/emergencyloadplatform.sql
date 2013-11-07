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
-- Database: `emergencyloadplatform`
--
CREATE DATABASE IF NOT EXISTS `emergencyloadplatform` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `emergencyloadplatform`;

-- --------------------------------------------------------

--
-- Table structure for table `application_tbl`
--

CREATE TABLE IF NOT EXISTS `application_tbl` (
  `appname` varchar(50) NOT NULL,
  UNIQUE KEY `appname` (`appname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `application_tbl`
--

INSERT INTO `application_tbl` (`appname`) VALUES
('main');

-- --------------------------------------------------------

--
-- Table structure for table `company_docs`
--

CREATE TABLE IF NOT EXISTS `company_docs` (
  `fileid` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(250) NOT NULL,
  `companyid` int(11) NOT NULL,
  `uploaddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uploadby` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `company_tbl`
--

CREATE TABLE IF NOT EXISTS `company_tbl` (
  `companyid` int(11) NOT NULL AUTO_INCREMENT,
  `levelid` int(11) DEFAULT NULL,
  `parentcompanyid` int(11) DEFAULT '0',
  `companyname` varchar(50) DEFAULT NULL,
  `companyaddress` varchar(250) DEFAULT NULL,
  `contactperson` varchar(50) DEFAULT NULL,
  `datecreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdby` varchar(50) DEFAULT NULL,
  `status` enum('inactive','active') DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `retailersim` varchar(20) DEFAULT NULL,
  `opensim` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`companyid`),
  UNIQUE KEY `companyname` (`companyname`),
  UNIQUE KEY `companyname_2` (`companyname`),
  UNIQUE KEY `mobile` (`mobile`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=42 ;

--
-- Dumping data for table `company_tbl`
--

INSERT INTO `company_tbl` (`companyid`, `levelid`, `parentcompanyid`, `companyname`, `companyaddress`, `contactperson`, `datecreated`, `createdby`, `status`, `mobile`, `retailersim`, `opensim`) VALUES
(1, 1, 0, 'Comworks Inc.', NULL, NULL, '2013-04-02 11:10:26', '1', 'active', '123467', NULL, '09171234667'),
(2, 2, 1, 'Arga J, Inc.', NULL, NULL, '2013-04-02 11:10:27', '1', 'active', '111111', NULL, '09171234667'),
(3, 2, 1, 'Arga J Pure Bliss', NULL, NULL, '2013-05-09 06:24:00', '1', 'active', '09201111111', '09202222222', '09171234667'),
(4, 2, 1, 'RGG Communication Enterprises', NULL, NULL, '2013-04-02 11:10:31', '1', 'active', NULL, NULL, '09171234667'),
(5, 2, 1, 'RGG Loading Stations', NULL, NULL, '2013-04-02 11:10:33', '1', 'active', NULL, NULL, '09171234667'),
(6, 2, 1, 'Light Shine 88 Corporation', NULL, NULL, '2013-04-02 11:10:34', '1', 'active', NULL, NULL, '09171234667'),
(7, 2, 1, 'Wenselcom Corporation', NULL, NULL, '2013-04-02 11:10:35', '1', 'active', NULL, NULL, '09171234667'),
(8, 2, 1, 'North East GMA Inc.', NULL, NULL, '2013-04-02 11:10:38', '1', 'active', NULL, NULL, '09171234667'),
(9, 2, 1, 'Sales By Load Inc.', NULL, NULL, '2013-04-02 11:10:36', '1', 'active', NULL, NULL, '09171234667'),
(10, 2, 1, 'Orion Marketing', NULL, NULL, '2013-04-02 11:10:39', '1', 'active', NULL, NULL, '09171234667'),
(11, 2, 1, 'Migav Marketing', NULL, NULL, '2013-04-02 11:10:38', '1', 'active', NULL, NULL, '09171234667'),
(12, 3, 2, 'Arga J, Inc. DSP', NULL, NULL, '2013-04-02 11:10:43', '1', 'active', NULL, NULL, '09171234667'),
(13, 3, 3, 'Arga J Pure Bliss DSP', NULL, NULL, '2013-04-02 11:10:41', '1', 'active', NULL, NULL, '09171234667'),
(14, 3, 4, 'RGG Communication Enterprises DSP', NULL, NULL, '2013-04-02 11:10:45', '1', 'active', NULL, NULL, '09171234667'),
(15, 3, 5, 'RGG Loading Stations DSP', NULL, NULL, '2013-04-02 11:10:45', '1', 'active', NULL, NULL, '09171234667'),
(16, 3, 6, 'Light Shine 88 Corporation DSP', NULL, NULL, '2013-04-02 11:10:47', '1', 'active', NULL, NULL, '09171234667'),
(17, 3, 7, 'Wenselcom Corporation DSP', NULL, NULL, '2013-04-02 11:10:50', '1', 'active', NULL, NULL, '09171234667'),
(18, 3, 8, 'North East GMA Inc. DSP', NULL, NULL, '2013-04-02 11:10:50', '1', 'active', NULL, NULL, '09171234667'),
(19, 3, 9, 'Sales By Load Inc. DSP', NULL, NULL, '2013-04-02 11:10:50', '1', 'active', NULL, NULL, '09171234667'),
(20, 3, 10, 'Orion Marketing DSP', NULL, NULL, '2013-04-02 11:10:51', '1', 'active', NULL, NULL, '09171234667'),
(21, 3, 11, 'Migav Marketing DSP', NULL, NULL, '2013-04-02 11:10:52', '1', 'active', NULL, NULL, '09171234667'),
(22, 4, 12, 'Arga J, Inc. RETAILER', NULL, NULL, '2013-04-03 01:45:30', '1', 'active', NULL, '09191234562', '09171234667'),
(23, 4, 13, 'Arga J Pure Bliss RETAILER', NULL, NULL, '2013-04-02 11:10:53', '1', 'active', NULL, '09191234562', '09171234667'),
(24, 4, 14, 'RGG Communication Enterprises RETAILER', NULL, NULL, '2013-04-02 11:10:53', '1', 'active', NULL, '09191234563', '09171234667'),
(25, 4, 15, 'RGG Loading Stations RETAILER', NULL, NULL, '2013-04-02 11:10:53', '1', 'active', NULL, '09191234564', '09171234667'),
(26, 4, 16, 'Light Shine 88 Corporation RETAILER', NULL, NULL, '2013-04-02 11:10:54', '1', 'active', NULL, '09191234565', '09171234667'),
(27, 4, 17, 'Wenselcom Corporation RETAILER', NULL, NULL, '2013-04-02 11:10:54', '1', 'active', NULL, '09191234567', '09171234667'),
(28, 4, 18, 'North East GMA Inc. RETAILER', NULL, NULL, '2013-04-02 11:10:55', '1', 'active', NULL, '09191234568', '09171234667'),
(29, 4, 19, 'Sales By Load Inc. RETAILER', NULL, NULL, '2013-04-02 11:10:55', '1', 'active', NULL, '09191234569', '09171234667'),
(30, 4, 20, 'Orion Marketing RETAILER', NULL, NULL, '2013-04-02 11:10:55', '1', 'active', NULL, '09191234560', '09171234667'),
(31, 4, 21, 'Migav Marketing RETAILER', NULL, NULL, '2013-04-02 11:10:56', '1', 'active', NULL, '09191234527', '09171234667'),
(33, 2, 1, 'TEST', 'TEST', 'ghel', '2013-04-02 11:10:56', 'runjel', 'active', '1234567', NULL, '09171234667'),
(34, 3, 2, 'Techbytes', 'QC', 'ronald', '2013-04-02 11:10:57', 'cwi', 'active', '09178558927', NULL, '09171234667'),
(35, 3, 2, 'Hyperworks', 'QC', 'gel', '2013-04-02 11:10:57', 'cwi', 'active', '09175589201', NULL, '09171234667'),
(36, 3, 2, 'Mycompany', 'QC', 'gel', '2013-04-02 11:10:58', 'cwi', 'active', '091712345678', '09179874562', '09171234667'),
(37, 4, 36, 'Mambacurial', 'Mambacurial', 'Mambacurial', '2013-05-09 10:55:34', 'arga', 'active', '12345232', '12345232', NULL),
(38, 2, 0, 'PDPDPDPDP', 'PDPDPDPDP', 'PDPDPDPDP', '2013-09-25 06:20:26', 'cwi', 'active', '', '123051213', NULL),
(39, 2, 0, 'comworksssss', 'comworks', 'arga', '2013-09-25 06:43:22', 'cwi', 'active', 'jhgvjh', 'tfrygug', NULL),
(40, 2, 1, 'cowmorks', 'qc', 'arga', '2013-09-25 06:45:03', 'cwi', 'active', 'adsdffd', 'dsddfd', NULL),
(41, 3, 2, 'arga-arga', 'arga-arga-arga', 'arga', '2013-09-26 02:06:50', 'arga', 'active', 'arga', 'arga', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `creditidcounter`
--

CREATE TABLE IF NOT EXISTS `creditidcounter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `creditidcounter`
--

INSERT INTO `creditidcounter` (`id`) VALUES
(25);

-- --------------------------------------------------------

--
-- Table structure for table `credit_limit`
--

CREATE TABLE IF NOT EXISTS `credit_limit` (
  `creditlimitid` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL,
  `creditlimit` int(10) DEFAULT NULL,
  `outstanding_balance` int(10) DEFAULT NULL,
  `remaining_balance` int(10) DEFAULT NULL,
  `remaininglimit` int(10) DEFAULT NULL,
  `credit_id` int(10) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `credit_term` int(10) DEFAULT '7',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valid_from` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  PRIMARY KEY (`creditlimitid`),
  KEY `creditlimit_idx` (`companyid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `credit_limit`
--

INSERT INTO `credit_limit` (`creditlimitid`, `companyid`, `creditlimit`, `outstanding_balance`, `remaining_balance`, `remaininglimit`, `credit_id`, `status`, `credit_term`, `created_date`, `valid_from`, `to_date`) VALUES
(5, 2, 5000, 2579, 0, 2131, 76, 'approved', 7, '2013-10-04 10:00:09', '2013-04-04', NULL),
(6, 22, 500, 0, 0, 500, 33, 'expired', 7, '2013-04-11 01:02:05', '2013-04-04', NULL),
(7, 22, 500, 17, 17, 500, 39, 'expired', 7, '2013-09-25 02:46:50', '2013-04-19', NULL),
(8, 22, 5000, 0, 0, 5000, 40, 'expired', 7, '2013-09-25 03:02:02', '2013-09-25', NULL),
(9, 22, 500, 0, 0, 500, 54, 'expired', 7, '2013-09-26 06:23:33', '2013-09-25', NULL),
(10, 22, 2500, 497, 497, 2500, 76, 'approved', 7, '2013-10-04 10:00:08', '2013-09-26', NULL);

--
-- Triggers `credit_limit`
--
DROP TRIGGER IF EXISTS `credit_limit_insert`;
DELIMITER //
CREATE TRIGGER `credit_limit_insert` AFTER INSERT ON `credit_limit`
 FOR EACH ROW begin
	insert into credit_limit_changes_history (companyid,  new_remaininglimit,new_outstandingbalance,new_creditlimit, date_changed, new_status,new_valid_from,new_valid_to, credit_id,creditlimitid,new_remainingbalance) values (NEW.companyid, NEW.remaininglimit,NEW.outstanding_balance, NEW.creditlimit,now(),NEW.status,NEW.valid_from,NEW.to_date,NEW.credit_id, NEW.creditlimitid,NEW.remaining_balance);
end
//
DELIMITER ;
DROP TRIGGER IF EXISTS `credit_limit_update`;
DELIMITER //
CREATE TRIGGER `credit_limit_update` AFTER UPDATE ON `credit_limit`
 FOR EACH ROW begin
 insert into credit_limit_changes_history (companyid, old_remaininglimit, new_remaininglimit,old_outstandingbalance,new_outstandingbalance,old_creditlimit,new_creditlimit, 
            date_changed,old_status, new_status,old_valid_from,old_valid_to,new_valid_from,new_valid_to,credit_id,creditlimitid,old_remainingbalance,new_remainingbalance) values (NEW.companyid, OLD.remaininglimit, NEW.remaininglimit,
         OLD.outstanding_balance,NEW.outstanding_balance,OLD.creditlimit,  NEW.creditlimit, now(), OLD.status,NEW.status,OLD.valid_from,OLD.to_date,NEW.valid_from,NEW.to_date,NEW.credit_id, NEW.creditlimitid,
	OLD.remaining_balance, NEW.remaining_balance);
end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `credit_limit_changes_history`
--

CREATE TABLE IF NOT EXISTS `credit_limit_changes_history` (
  `companyid` int(11) NOT NULL,
  `old_remaininglimit` int(10) DEFAULT NULL,
  `new_remaininglimit` int(10) DEFAULT NULL,
  `old_outstandingbalance` int(10) DEFAULT NULL,
  `new_outstandingbalance` int(10) DEFAULT NULL,
  `old_remainingbalance` int(10) DEFAULT NULL,
  `new_remainingbalance` int(10) DEFAULT NULL,
  `old_creditlimit` int(10) DEFAULT NULL,
  `new_creditlimit` int(10) DEFAULT NULL,
  `credit_id` int(10) DEFAULT NULL,
  `creditlimitid` int(10) DEFAULT NULL,
  `old_status` varchar(30) DEFAULT NULL,
  `new_status` varchar(30) DEFAULT NULL,
  `date_changed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `old_valid_from` date DEFAULT NULL,
  `old_valid_to` date DEFAULT NULL,
  `new_valid_from` date DEFAULT NULL,
  `new_valid_to` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_limit_changes_history`
--

INSERT INTO `credit_limit_changes_history` (`companyid`, `old_remaininglimit`, `new_remaininglimit`, `old_outstandingbalance`, `new_outstandingbalance`, `old_remainingbalance`, `new_remainingbalance`, `old_creditlimit`, `new_creditlimit`, `credit_id`, `creditlimitid`, `old_status`, `new_status`, `date_changed`, `old_valid_from`, `old_valid_to`, `new_valid_from`, `new_valid_to`) VALUES
(2, NULL, 5000, NULL, 0, NULL, NULL, NULL, 5000, NULL, NULL, NULL, 'approved', '2013-04-02 05:40:02', NULL, NULL, NULL, NULL),
(2, 5000, 5000, 0, 0, NULL, NULL, 5000, 5000, NULL, NULL, 'approved', 'expired', '2013-04-02 05:40:35', NULL, NULL, NULL, NULL),
(2, NULL, 7500, NULL, 0, NULL, NULL, NULL, 7500, NULL, NULL, NULL, 'approved', '2013-04-02 05:40:35', NULL, NULL, NULL, NULL),
(2, 7500, 5000, 0, 0, NULL, NULL, 7500, 7500, NULL, NULL, 'approved', 'approved', '2013-04-02 05:41:20', NULL, NULL, NULL, NULL),
(22, NULL, 2500, NULL, 0, NULL, NULL, NULL, 2500, NULL, NULL, NULL, 'approved', '2013-04-02 05:41:21', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, NULL, NULL, 2500, 2500, 5, NULL, 'approved', 'approved', '2013-04-03 02:00:17', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 1000, NULL, NULL, 2500, 2500, 6, NULL, 'approved', 'approved', '2013-04-03 02:01:40', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 1000, 0, NULL, NULL, 2500, 2500, 6, NULL, 'approved', 'approved', '2013-04-03 02:02:07', NULL, NULL, NULL, NULL),
(2, 5000, 7500, 0, 0, NULL, NULL, 7500, 7500, NULL, NULL, 'approved', 'approved', '2013-04-03 02:02:23', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, NULL, NULL, 2500, 2500, 7, NULL, 'approved', 'approved', '2013-04-03 02:11:44', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 0, NULL, NULL, 2500, 2500, 7, NULL, 'approved', 'approved', '2013-04-03 02:13:04', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, NULL, NULL, 2500, 2500, 8, NULL, 'approved', 'approved', '2013-04-03 02:14:34', NULL, NULL, NULL, NULL),
(2, 7500, 7500, 0, 500, NULL, NULL, 7500, 7500, 8, NULL, 'approved', 'approved', '2013-04-03 02:14:34', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 500, NULL, NULL, 2500, 2500, 8, NULL, 'approved', 'approved', '2013-04-03 02:14:34', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 0, NULL, NULL, 2500, 2500, 8, NULL, 'approved', 'approved', '2013-04-03 02:14:58', NULL, NULL, NULL, NULL),
(2, 7500, 7500, 500, 0, NULL, NULL, 7500, 7500, 8, NULL, 'approved', 'approved', '2013-04-03 02:14:59', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, NULL, NULL, 2500, 2500, 9, NULL, 'approved', 'approved', '2013-04-03 02:24:38', NULL, NULL, NULL, NULL),
(2, 7500, 7500, 0, 500, NULL, NULL, 7500, 7500, 9, NULL, 'approved', 'approved', '2013-04-03 02:24:38', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 500, NULL, NULL, 2500, 2500, 9, NULL, 'approved', 'approved', '2013-04-03 02:24:38', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 0, 500, 0, 2500, 2500, 9, 3, 'approved', 'approved', '2013-04-03 07:34:39', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, -500, 0, -500, 2500, 2500, 9, 3, 'approved', 'approved', '2013-04-03 07:35:43', NULL, NULL, NULL, NULL),
(22, 2500, 2500, -500, -1000, -500, -1000, 2500, 2500, 9, 3, 'approved', 'approved', '2013-04-03 07:40:39', NULL, NULL, NULL, NULL),
(22, 2500, 2500, -1000, 0, -1000, 0, 2500, 2500, 9, 3, 'approved', 'approved', '2013-04-03 07:41:17', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, 0, 500, 2500, 2500, 11, 3, 'approved', 'approved', '2013-04-03 07:44:47', NULL, NULL, NULL, NULL),
(2, 7500, 7500, 500, 1000, 0, 0, 7500, 7500, 11, 2, 'approved', 'approved', '2013-04-03 07:44:47', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 500, 500, 500, 2500, 2500, 11, 3, 'approved', 'approved', '2013-04-03 07:44:47', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 0, 500, 0, 2500, 2500, 11, 3, 'approved', 'approved', '2013-04-03 07:45:18', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, 0, 500, 2500, 2500, 12, 3, 'approved', 'approved', '2013-04-03 07:46:32', NULL, NULL, NULL, NULL),
(2, 7500, 7500, 1000, 1500, 0, 0, 7500, 7500, 12, 2, 'approved', 'approved', '2013-04-03 07:46:32', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 500, 500, 500, 2500, 2500, 12, 3, 'approved', 'approved', '2013-04-03 07:46:32', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 500, 0, 500, 0, 2500, 2500, 12, 3, 'approved', 'approved', '2013-04-03 07:50:20', NULL, NULL, NULL, NULL),
(22, 2500, 2500, 0, 500, 0, 500, 2500, 2500, 12, 3, 'approved', 'approved', '2013-04-03 07:58:19', NULL, NULL, NULL, NULL),
(2, 7500, 7500, 1500, 0, 0, 0, 7500, 7500, 12, 2, 'approved', 'approved', '2013-04-04 07:47:24', NULL, NULL, NULL, NULL),
(2, 7500, 2500, 0, 0, 0, 0, 7500, 7500, 12, 2, 'approved', 'approved', '2013-04-04 07:47:51', NULL, NULL, NULL, NULL),
(22, NULL, 5000, NULL, 0, NULL, 0, NULL, 5000, NULL, 4, NULL, 'approved', '2013-04-04 07:47:51', NULL, NULL, '2013-04-04', NULL),
(22, 5000, 5000, 0, 1000, 0, 1000, 5000, 5000, 13, 4, 'approved', 'approved', '2013-04-04 07:50:49', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 2500, 2500, 0, 1000, 0, 0, 7500, 7500, 13, 2, 'approved', 'approved', '2013-04-04 07:50:49', NULL, NULL, NULL, NULL),
(22, 5000, 5000, 1000, 1000, 1000, 1000, 5000, 5000, 13, 4, 'approved', 'approved', '2013-04-04 07:50:49', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 5000, 5000, 1000, 0, 1000, 0, 5000, 5000, 13, 4, 'approved', 'approved', '2013-04-04 08:48:08', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 5000, 5000, 0, 500, 0, 500, 5000, 5000, 14, 4, 'approved', 'approved', '2013-04-04 09:05:23', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 2500, 2500, 1000, 1500, 0, 0, 7500, 7500, 14, 2, 'approved', 'approved', '2013-04-04 09:05:23', NULL, NULL, NULL, NULL),
(22, 5000, 5000, 500, 500, 500, 500, 5000, 5000, 14, 4, 'approved', 'approved', '2013-04-04 09:05:23', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 2500, 2500, 1500, 0, 0, 1, 7500, 7500, 14, 2, 'approved', 'approved', '2013-04-04 10:35:55', NULL, NULL, NULL, NULL),
(2, 2500, 2500, 0, 5000, 1, 5000, 7500, 7500, 14, 2, 'approved', 'approved', '2013-04-04 10:36:47', NULL, NULL, NULL, NULL),
(2, 2500, 2500, 5000, 0, 5000, 0, 7500, 7500, 14, 2, 'approved', 'approved', '2013-04-04 10:36:53', NULL, NULL, NULL, NULL),
(2, NULL, 5000, NULL, 0, NULL, 0, NULL, 5000, NULL, 5, NULL, 'approved', '2013-04-04 10:40:32', NULL, NULL, '2013-04-04', NULL),
(2, 5000, 3000, 0, 0, 0, 0, 5000, 5000, NULL, 5, 'approved', 'approved', '2013-04-04 10:41:02', '2013-04-04', NULL, '2013-04-04', NULL),
(22, NULL, 2000, NULL, 0, NULL, 0, NULL, 2000, NULL, 6, NULL, 'approved', '2013-04-04 10:41:02', NULL, NULL, '2013-04-04', NULL),
(22, 2000, 2000, 0, 1000, 0, 1000, 2000, 2000, 16, 6, 'approved', 'approved', '2013-04-04 10:41:20', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 0, 1000, 0, 0, 5000, 5000, 16, 5, 'approved', 'approved', '2013-04-04 10:41:20', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1000, 1000, 1000, 1000, 2000, 2000, 16, 6, 'approved', 'approved', '2013-04-04 10:41:20', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1000, 0, 1000, 0, 2000, 2000, 16, 6, 'approved', 'approved', '2013-04-04 10:44:06', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1000, 0, 0, 1, 5000, 5000, 16, 5, 'approved', 'approved', '2013-04-04 10:45:52', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 0, 1000, 1, 1, 5000, 5000, 16, 5, 'approved', 'approved', '2013-04-04 10:50:14', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1000, 0, 1, 0, 5000, 5000, 16, 5, 'approved', 'approved', '2013-04-04 10:50:18', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 0, 500, 0, 500, 2000, 2000, 17, 6, 'approved', 'approved', '2013-04-10 01:14:58', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 0, 500, 0, 0, 5000, 5000, 17, 5, 'approved', 'approved', '2013-04-10 01:14:58', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 500, 500, 500, 500, 2000, 2000, 17, 6, 'approved', 'approved', '2013-04-10 01:14:59', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 500, 600, 500, 600, 2000, 2000, 18, 6, 'approved', 'approved', '2013-04-09 20:24:50', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 500, 600, 0, 0, 5000, 5000, 18, 5, 'approved', 'approved', '2013-04-09 20:24:50', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 600, 600, 600, 600, 2000, 2000, 18, 6, 'approved', 'approved', '2013-04-09 20:24:50', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 600, 700, 600, 700, 2000, 2000, 19, 6, 'approved', 'approved', '2013-04-09 20:33:58', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 600, 700, 0, 0, 5000, 5000, 19, 5, 'approved', 'approved', '2013-04-09 20:33:58', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 700, 700, 700, 700, 2000, 2000, 19, 6, 'approved', 'approved', '2013-04-09 20:33:58', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 700, 800, 700, 800, 2000, 2000, 20, 6, 'approved', 'approved', '2013-04-09 20:35:35', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 700, 800, 0, 0, 5000, 5000, 20, 5, 'approved', 'approved', '2013-04-09 20:35:35', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 800, 800, 800, 800, 2000, 2000, 20, 6, 'approved', 'approved', '2013-04-09 20:35:35', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 800, 900, 800, 900, 2000, 2000, 21, 6, 'approved', 'approved', '2013-04-10 11:36:20', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 800, 900, 0, 0, 5000, 5000, 21, 5, 'approved', 'approved', '2013-04-10 11:36:20', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 900, 900, 900, 900, 2000, 2000, 21, 6, 'approved', 'approved', '2013-04-10 11:36:20', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 900, 950, 900, 950, 2000, 2000, 22, 6, 'approved', 'approved', '2013-04-10 11:42:13', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 900, 950, 0, 0, 5000, 5000, 22, 5, 'approved', 'approved', '2013-04-10 11:42:14', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 950, 950, 950, 950, 2000, 2000, 22, 6, 'approved', 'approved', '2013-04-10 11:42:14', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 950, 1000, 950, 1000, 2000, 2000, 23, 6, 'approved', 'approved', '2013-04-10 02:47:28', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 950, 1000, 0, 0, 5000, 5000, 23, 5, 'approved', 'approved', '2013-04-10 02:47:28', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1000, 1000, 1000, 1000, 2000, 2000, 23, 6, 'approved', 'approved', '2013-04-10 02:47:28', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1000, 1050, 1000, 1050, 2000, 2000, 24, 6, 'approved', 'approved', '2013-04-10 02:48:41', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1000, 1050, 0, 0, 5000, 5000, 24, 5, 'approved', 'approved', '2013-04-10 02:48:41', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1050, 1050, 1050, 1050, 2000, 2000, 24, 6, 'approved', 'approved', '2013-04-10 02:48:41', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1050, 1100, 1050, 1100, 2000, 2000, 25, 6, 'approved', 'approved', '2013-04-10 14:51:34', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1050, 1100, 0, 0, 5000, 5000, 25, 5, 'approved', 'approved', '2013-04-10 14:51:34', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1100, 1100, 1100, 1100, 2000, 2000, 25, 6, 'approved', 'approved', '2013-04-10 14:51:34', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1100, 1150, 1100, 1150, 2000, 2000, 26, 6, 'approved', 'approved', '2013-04-10 02:56:05', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1100, 1150, 0, 0, 5000, 5000, 26, 5, 'approved', 'approved', '2013-04-10 02:56:05', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1150, 1150, 1150, 1150, 2000, 2000, 26, 6, 'approved', 'approved', '2013-04-10 02:56:05', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1150, 1200, 1150, 1200, 2000, 2000, 27, 6, 'approved', 'approved', '2013-04-09 23:57:54', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1150, 1200, 0, 0, 5000, 5000, 27, 5, 'approved', 'approved', '2013-04-09 23:57:54', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1200, 1200, 1200, 1200, 2000, 2000, 27, 6, 'approved', 'approved', '2013-04-09 23:57:54', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1200, 1150, 1200, 1150, 2000, 2000, 27, 6, 'approved', 'approved', '2013-04-10 04:08:53', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1150, 1100, 1150, 1100, 2000, 2000, 26, 6, 'approved', 'approved', '2013-04-10 05:52:28', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1100, 1150, 1100, 1150, 2000, 2000, 28, 6, 'approved', 'approved', '2013-04-10 08:00:49', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1200, 1250, 0, 0, 5000, 5000, 28, 5, 'approved', 'approved', '2013-04-10 08:00:49', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1150, 1150, 1150, 1150, 2000, 2000, 28, 6, 'approved', 'approved', '2013-04-10 08:00:49', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1150, 1200, 1150, 1200, 2000, 2000, 29, 6, 'approved', 'approved', '2013-04-12 08:09:09', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1250, 1300, 0, 0, 5000, 5000, 29, 5, 'approved', 'approved', '2013-04-12 08:09:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1200, 1200, 1200, 1200, 2000, 2000, 29, 6, 'approved', 'approved', '2013-04-12 08:09:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1200, 1250, 1200, 1250, 2000, 2000, 30, 6, 'approved', 'approved', '2013-04-12 08:09:57', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1300, 1350, 0, 0, 5000, 5000, 30, 5, 'approved', 'approved', '2013-04-12 08:09:57', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1250, 1250, 1250, 1250, 2000, 2000, 30, 6, 'approved', 'approved', '2013-04-12 08:09:57', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1250, 1300, 1250, 1300, 2000, 2000, 31, 6, 'approved', 'approved', '2013-04-12 08:16:20', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1350, 1400, 0, 0, 5000, 5000, 31, 5, 'approved', 'approved', '2013-04-12 08:16:20', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1300, 1300, 1300, 1300, 2000, 2000, 31, 6, 'approved', 'approved', '2013-04-12 08:16:20', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1300, 1250, 1300, 1250, 2000, 2000, 28, 6, 'approved', 'approved', '2013-04-11 00:33:15', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1250, 1200, 1250, 1200, 2000, 2000, 29, 6, 'approved', 'approved', '2013-04-11 00:36:49', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1200, 1150, 1200, 1150, 2000, 2000, 30, 6, 'approved', 'approved', '2013-04-11 00:37:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1150, 1100, 1150, 1100, 2000, 2000, 31, 6, 'approved', 'approved', '2013-04-11 00:37:14', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1100, 1050, 1100, 1050, 2000, 2000, 25, 6, 'approved', 'approved', '2013-04-11 00:38:24', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1050, 0, 1050, 0, 2000, 2000, 25, 6, 'approved', 'approved', '2013-04-11 00:40:49', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 1400, 0, 0, 0, 5000, 5000, 31, 5, 'approved', 'approved', '2013-04-11 00:40:54', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 0, 1, 0, 1, 2000, 2000, 32, 6, 'approved', 'approved', '2013-04-11 00:41:15', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 3000, 0, 1, 0, 0, 5000, 5000, 32, 5, 'approved', 'approved', '2013-04-11 00:41:15', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 2000, 1, 1, 1, 1, 2000, 2000, 32, 6, 'approved', 'approved', '2013-04-11 00:41:15', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2000, 1000, 1, 1, 1, 1, 2000, 1000, 32, 6, 'approved', 'approved', '2013-04-11 00:43:15', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 3000, 4000, 1, 1, 0, 0, 5000, 5000, 32, 5, 'approved', 'approved', '2013-04-11 00:43:16', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 1000, 1000, 1, 2, 1, 2, 1000, 1000, 33, 6, 'approved', 'approved', '2013-04-11 00:44:28', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 4000, 4000, 1, 2, 0, 0, 5000, 5000, 33, 5, 'approved', 'approved', '2013-04-11 00:44:28', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 1000, 1000, 2, 2, 2, 2, 1000, 1000, 33, 6, 'approved', 'approved', '2013-04-11 00:44:28', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 1000, 500, 2, 2, 2, 2, 1000, 500, 33, 6, 'approved', 'approved', '2013-04-11 00:51:32', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 4000, 4500, 2, 2, 0, 0, 5000, 5000, 33, 5, 'approved', 'approved', '2013-04-11 00:51:32', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 2, 1, 2, 1, 500, 500, 32, 6, 'approved', 'approved', '2013-04-11 00:54:47', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 1, 0, 1, 0, 500, 500, 33, 6, 'approved', 'approved', '2013-04-11 01:01:51', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 4500, 5000, 2, 2, 0, 0, 5000, 5000, 33, 5, 'approved', 'approved', '2013-04-11 01:02:05', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 0, 0, 0, 0, 500, 500, 33, 6, 'approved', 'expired', '2013-04-11 01:02:05', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 5000, 4500, 2, 2, 0, 0, 5000, 5000, 33, 5, 'approved', 'approved', '2013-04-19 06:01:53', '2013-04-04', NULL, '2013-04-04', NULL),
(22, NULL, 500, NULL, 0, NULL, 0, NULL, 500, NULL, 7, NULL, 'approved', '2013-04-19 06:01:54', NULL, NULL, '2013-04-19', NULL),
(22, 500, 500, 0, 100, 0, 100, 500, 500, 34, 7, 'approved', 'approved', '2013-04-19 06:02:07', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 4500, 2, 102, 0, 0, 5000, 5000, 34, 5, 'approved', 'approved', '2013-04-19 06:02:07', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 100, 100, 100, 100, 500, 500, 34, 7, 'approved', 'approved', '2013-04-19 06:02:07', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 100, 105, 100, 105, 500, 500, 35, 7, 'approved', 'approved', '2013-05-09 09:49:11', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 4500, 102, 107, 0, 0, 5000, 5000, 35, 5, 'approved', 'approved', '2013-05-09 09:49:12', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 105, 105, 105, 105, 500, 500, 35, 7, 'approved', 'approved', '2013-05-09 09:49:12', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 105, 110, 105, 110, 500, 500, 36, 7, 'approved', 'approved', '2013-05-12 10:07:29', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 4500, 107, 112, 0, 0, 5000, 5000, 36, 5, 'approved', 'approved', '2013-05-12 10:07:29', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 110, 110, 110, 110, 500, 500, 36, 7, 'approved', 'approved', '2013-05-12 10:07:29', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 110, 115, 110, 115, 500, 500, 37, 7, 'approved', 'approved', '2013-05-09 10:37:02', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 4500, 112, 117, 0, 0, 5000, 5000, 37, 5, 'approved', 'approved', '2013-05-09 10:37:02', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 115, 115, 115, 115, 500, 500, 37, 7, 'approved', 'approved', '2013-05-09 10:37:02', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 115, 15, 115, 15, 500, 500, 34, 7, 'approved', 'approved', '2013-05-14 10:41:41', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 15, 50, 15, 50, 500, 500, 38, 7, 'approved', 'approved', '2013-05-14 11:21:52', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 4500, 117, 152, 0, 0, 5000, 5000, 38, 5, 'approved', 'approved', '2013-05-14 11:21:52', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 50, 50, 50, 50, 500, 500, 38, 7, 'approved', 'approved', '2013-05-14 11:21:53', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 50, 51, 50, 51, 500, 500, 38, 7, 'approved', 'approved', '2013-06-18 06:30:02', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 51, 52, 51, 52, 500, 500, 38, 7, 'approved', 'approved', '2013-06-18 06:35:16', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 52, 51, 52, 51, 500, 500, 32, 7, 'approved', 'approved', '2013-06-18 06:46:02', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 51, 52, 51, 52, 500, 500, 32, 7, 'approved', 'approved', '2013-06-18 07:40:16', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 52, 53, 52, 53, 500, 500, 33, 7, 'approved', 'approved', '2013-06-19 06:41:58', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 53, 153, 53, 153, 500, 500, 34, 7, 'approved', 'approved', '2013-06-19 07:45:33', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 153, 152, 153, 152, 500, 500, 32, 7, 'approved', 'approved', '2013-06-19 07:51:51', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 152, 151, 152, 151, 500, 500, 33, 7, 'approved', 'approved', '2013-06-19 07:52:04', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 151, 51, 151, 51, 500, 500, 34, 7, 'approved', 'approved', '2013-06-19 07:52:13', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 51, 52, 51, 52, 500, 500, 32, 7, 'approved', 'approved', '2013-06-19 07:52:22', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 52, 47, 52, 47, 500, 500, 35, 7, 'approved', 'approved', '2013-06-19 07:52:58', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 47, 42, 47, 42, 500, 500, 35, 7, 'approved', 'approved', '2013-06-19 07:59:49', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 42, 37, 42, 37, 500, 500, 35, 7, 'approved', 'approved', '2013-06-19 07:59:51', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 37, 32, 37, 32, 500, 500, 35, 7, 'approved', 'approved', '2013-06-19 08:00:10', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 32, 27, 32, 27, 500, 500, 35, 7, 'approved', 'approved', '2013-06-19 08:04:52', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 27, 28, 27, 28, 500, 500, 33, 7, 'approved', 'approved', '2013-06-19 09:42:15', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 28, 27, 28, 27, 500, 500, 32, 7, 'approved', 'approved', '2013-06-19 09:42:46', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 27, 127, 27, 127, 500, 500, 34, 7, 'approved', 'approved', '2013-06-20 04:08:10', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 127, 128, 127, 128, 500, 500, 39, 7, 'approved', 'approved', '2013-09-24 06:15:03', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 4500, 152, 153, 0, 0, 5000, 5000, 39, 5, 'approved', 'approved', '2013-09-24 06:15:03', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 128, 128, 128, 128, 500, 500, 39, 7, 'approved', 'approved', '2013-09-24 06:15:03', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 128, 28, 128, 28, 500, 500, 34, 7, 'approved', 'approved', '2013-09-25 02:41:11', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 28, 23, 28, 23, 500, 500, 36, 7, 'approved', 'approved', '2013-09-25 02:43:05', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 23, 18, 23, 18, 500, 500, 37, 7, 'approved', 'approved', '2013-09-25 02:43:44', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 18, 17, 18, 17, 500, 500, 39, 7, 'approved', 'approved', '2013-09-25 02:43:55', '2013-04-19', NULL, '2013-04-19', NULL),
(22, 500, 500, 17, 17, 17, 17, 500, 500, 39, 7, 'approved', 'approved', '2013-09-25 02:46:43', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 4500, 5000, 153, 153, 0, 0, 5000, 5000, 39, 5, 'approved', 'approved', '2013-09-25 02:46:50', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 17, 17, 17, 17, 500, 500, 39, 7, 'approved', 'expired', '2013-09-25 02:46:50', '2013-04-19', NULL, '2013-04-19', NULL),
(2, 5000, 0, 153, 153, 0, 0, 5000, 5000, 39, 5, 'approved', 'approved', '2013-09-25 02:47:05', '2013-04-04', NULL, '2013-04-04', NULL),
(22, NULL, 5000, NULL, 0, NULL, 0, NULL, 5000, NULL, 8, NULL, 'approved', '2013-09-25 02:47:05', NULL, NULL, '2013-09-25', NULL),
(22, 5000, 5000, 0, 100, 0, 100, 5000, 5000, 40, 8, 'approved', 'approved', '2013-09-25 02:47:37', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 0, 0, 153, 253, 0, 0, 5000, 5000, 40, 5, 'approved', 'approved', '2013-09-25 02:47:37', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 5000, 5000, 100, 100, 100, 100, 5000, 5000, 40, 8, 'approved', 'approved', '2013-09-25 02:47:37', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 5000, 5000, 100, 0, 100, 0, 5000, 5000, 40, 8, 'approved', 'approved', '2013-09-25 02:50:17', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 5000, 5000, 0, 100, 0, 100, 5000, 5000, 40, 8, 'approved', 'approved', '2013-09-25 02:50:43', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 5000, 5000, 100, 0, 100, 0, 5000, 5000, 40, 8, 'approved', 'approved', '2013-09-25 02:52:15', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 0, 0, 253, 0, 0, 1, 5000, 5000, 40, 5, 'approved', 'approved', '2013-09-25 02:52:32', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 0, 5000, 0, 0, 1, 1, 5000, 5000, 40, 5, 'approved', 'approved', '2013-09-25 03:02:02', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 5000, 5000, 0, 0, 0, 0, 5000, 5000, 40, 8, 'approved', 'expired', '2013-09-25 03:02:02', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 5000, 2500, 0, 0, 1, 1, 5000, 5000, 40, 5, 'approved', 'approved', '2013-09-25 03:02:14', '2013-04-04', NULL, '2013-04-04', NULL),
(22, NULL, 2500, NULL, 0, NULL, 0, NULL, 2500, NULL, 9, NULL, 'approved', '2013-09-25 03:02:14', NULL, NULL, '2013-09-25', NULL),
(22, 2500, 2500, 0, 150, 0, 150, 2500, 2500, 41, 9, 'approved', 'approved', '2013-09-25 03:04:16', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 0, 150, 1, 0, 5000, 5000, 41, 5, 'approved', 'approved', '2013-09-25 03:04:16', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 150, 150, 150, 150, 2500, 2500, 41, 9, 'approved', 'approved', '2013-09-25 03:04:16', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 150, 0, 150, 0, 2500, 2500, 41, 9, 'approved', 'approved', '2013-09-25 03:05:36', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 0, 237, 0, 237, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 03:06:10', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 150, 387, 0, 0, 5000, 5000, 42, 5, 'approved', 'approved', '2013-09-25 03:06:11', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 237, 237, 237, 237, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 03:06:11', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 237, 387, 237, 387, 2500, 2500, 43, 9, 'approved', 'approved', '2013-09-25 03:08:02', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 387, 537, 0, 0, 5000, 5000, 43, 5, 'approved', 'approved', '2013-09-25 03:08:02', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 387, 387, 387, 387, 2500, 2500, 43, 9, 'approved', 'approved', '2013-09-25 03:08:02', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 387, 455, 387, 455, 2500, 2500, 44, 9, 'approved', 'approved', '2013-09-25 03:08:17', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 537, 605, 0, 0, 5000, 5000, 44, 5, 'approved', 'approved', '2013-09-25 03:08:17', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 455, 455, 455, 455, 2500, 2500, 44, 9, 'approved', 'approved', '2013-09-25 03:08:17', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 455, 686, 455, 686, 2500, 2500, 45, 9, 'approved', 'approved', '2013-09-25 03:08:34', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 605, 836, 0, 0, 5000, 5000, 45, 5, 'approved', 'approved', '2013-09-25 03:08:34', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 686, 686, 686, 686, 2500, 2500, 45, 9, 'approved', 'approved', '2013-09-25 03:08:35', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 686, 1186, 686, 1186, 2500, 2500, 46, 9, 'approved', 'approved', '2013-09-25 03:08:43', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 836, 1336, 0, 0, 5000, 5000, 46, 5, 'approved', 'approved', '2013-09-25 03:08:43', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1186, 1186, 1186, 1186, 2500, 2500, 46, 9, 'approved', 'approved', '2013-09-25 03:08:43', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1186, 1429, 1186, 1429, 2500, 2500, 47, 9, 'approved', 'approved', '2013-09-25 03:08:50', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1336, 1579, 0, 0, 5000, 5000, 47, 5, 'approved', 'approved', '2013-09-25 03:08:51', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1429, 1429, 1429, 1429, 2500, 2500, 47, 9, 'approved', 'approved', '2013-09-25 03:08:51', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1429, 1529, 1429, 1529, 2500, 2500, 48, 9, 'approved', 'approved', '2013-09-25 03:09:09', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1579, 1679, 0, 0, 5000, 5000, 48, 5, 'approved', 'approved', '2013-09-25 03:09:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1529, 1529, 1529, 1529, 2500, 2500, 48, 9, 'approved', 'approved', '2013-09-25 03:09:09', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1529, 1554, 1529, 1554, 2500, 2500, 49, 9, 'approved', 'approved', '2013-09-25 03:11:15', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1679, 1704, 0, 0, 5000, 5000, 49, 5, 'approved', 'approved', '2013-09-25 03:11:15', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1554, 1554, 1554, 1554, 2500, 2500, 49, 9, 'approved', 'approved', '2013-09-25 03:11:15', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1554, 1604, 1554, 1604, 2500, 2500, 50, 9, 'approved', 'approved', '2013-09-25 03:11:22', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1704, 1754, 0, 0, 5000, 5000, 50, 5, 'approved', 'approved', '2013-09-25 03:11:22', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1604, 1604, 1604, 1604, 2500, 2500, 50, 9, 'approved', 'approved', '2013-09-25 03:11:22', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1604, 1367, 1604, 1367, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 03:19:17', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1367, 1604, 1367, 1604, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 03:20:14', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1604, 1367, 1604, 1367, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 03:20:30', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1367, 1217, 1367, 1217, 2500, 2500, 43, 9, 'approved', 'approved', '2013-09-25 03:20:37', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1217, 1149, 1217, 1149, 2500, 2500, 44, 9, 'approved', 'approved', '2013-09-25 05:24:41', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1149, 1217, 1149, 1217, 2500, 2500, 44, 9, 'approved', 'approved', '2013-09-25 07:08:34', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1217, 1149, 1217, 1149, 2500, 2500, 44, 9, 'approved', 'approved', '2013-09-25 07:08:56', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1149, 1386, 1149, 1386, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 07:09:40', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1386, 1149, 1386, 1149, 2500, 2500, 42, 9, 'approved', 'approved', '2013-09-25 07:10:00', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1149, 1182, 1149, 1182, 2500, 2500, 51, 9, 'approved', 'approved', '2013-09-25 07:10:44', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1754, 1787, 0, 0, 5000, 5000, 51, 5, 'approved', 'approved', '2013-09-25 07:10:44', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1182, 1182, 1182, 1182, 2500, 2500, 51, 9, 'approved', 'approved', '2013-09-25 07:10:45', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1182, 951, 1182, 951, 2500, 2500, 45, 9, 'approved', 'approved', '2013-09-25 08:49:06', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 951, 451, 951, 451, 2500, 2500, 46, 9, 'approved', 'approved', '2013-09-25 08:53:53', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 451, 208, 451, 208, 2500, 2500, 47, 9, 'approved', 'approved', '2013-09-25 09:08:37', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 208, 108, 208, 108, 2500, 2500, 48, 9, 'approved', 'approved', '2013-09-25 09:12:35', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 108, 83, 108, 83, 2500, 2500, 49, 9, 'approved', 'approved', '2013-09-25 09:17:05', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 83, 33, 83, 33, 2500, 2500, 50, 9, 'approved', 'approved', '2013-09-25 09:17:42', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 33, 0, 33, 0, 2500, 2500, 51, 9, 'approved', 'approved', '2013-09-25 09:18:05', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1787, 0, 0, 1, 5000, 5000, 51, 5, 'approved', 'approved', '2013-09-25 09:30:15', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 0, 1000, 0, 1000, 2500, 2500, 52, 9, 'approved', 'approved', '2013-09-26 02:05:03', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 0, 1000, 1, 0, 5000, 5000, 52, 5, 'approved', 'approved', '2013-09-26 02:05:03', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1000, 1000, 1000, 1000, 2500, 2500, 52, 9, 'approved', 'approved', '2013-09-26 02:05:03', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1000, 0, 1000, 0, 2500, 2500, 52, 9, 'approved', 'approved', '2013-09-26 02:31:06', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 0, 1000, 0, 1000, 2500, 2500, 53, 9, 'approved', 'approved', '2013-09-26 05:19:16', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 1000, 2000, 0, 0, 5000, 5000, 53, 5, 'approved', 'approved', '2013-09-26 05:19:16', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1000, 1000, 1000, 1000, 2500, 2500, 53, 9, 'approved', 'approved', '2013-09-26 05:19:17', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1000, 0, 1000, 0, 2500, 2500, 53, 9, 'approved', 'approved', '2013-09-26 05:41:43', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 0, 1200, 0, 1200, 2500, 2500, 54, 9, 'approved', 'approved', '2013-09-26 06:20:55', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 2500, 2000, 3200, 0, 0, 5000, 5000, 54, 5, 'approved', 'approved', '2013-09-26 06:20:55', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 1200, 1200, 1200, 1200, 2500, 2500, 54, 9, 'approved', 'approved', '2013-09-26 06:20:55', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 2500, 1200, 0, 1200, 0, 2500, 2500, 54, 9, 'approved', 'approved', '2013-09-26 06:21:37', '2013-09-25', NULL, '2013-09-25', NULL),
(22, 2500, 500, 0, 0, 0, 0, 2500, 500, 54, 9, 'approved', 'approved', '2013-09-26 06:22:45', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 2500, 4500, 3200, 3200, 0, 0, 5000, 5000, 54, 5, 'approved', 'approved', '2013-09-26 06:22:45', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 4500, 5000, 3200, 3200, 0, 0, 5000, 5000, 54, 5, 'approved', 'approved', '2013-09-26 06:23:33', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 500, 500, 0, 0, 0, 0, 500, 500, 54, 9, 'approved', 'expired', '2013-09-26 06:23:33', '2013-09-25', NULL, '2013-09-25', NULL),
(2, 5000, 2500, 3200, 3200, 0, 0, 5000, 5000, 54, 5, 'approved', 'approved', '2013-09-26 06:24:14', '2013-04-04', NULL, '2013-04-04', NULL),
(22, NULL, 2500, NULL, 0, NULL, 0, NULL, 2500, NULL, 10, NULL, 'approved', '2013-09-26 06:24:14', NULL, NULL, '2013-09-26', NULL),
(22, 2500, 2500, 0, 500, 0, 500, 2500, 2500, 55, 10, 'approved', 'approved', '2013-09-26 06:25:31', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2500, 2500, 3200, 3700, 0, 0, 5000, 5000, 55, 5, 'approved', 'approved', '2013-09-26 06:25:31', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 500, 500, 500, 500, 2500, 2500, 55, 10, 'approved', 'approved', '2013-09-26 06:25:31', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 500, 600, 500, 600, 2500, 2500, 56, 10, 'approved', 'approved', '2013-09-26 06:36:23', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2500, 2500, 3700, 3800, 0, 0, 5000, 5000, 56, 5, 'approved', 'approved', '2013-09-26 06:36:23', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 600, 600, 600, 600, 2500, 2500, 56, 10, 'approved', 'approved', '2013-09-26 06:36:23', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2500, 2500, 3800, 0, 0, 1, 5000, 5000, 56, 5, 'approved', 'approved', '2013-09-26 06:55:28', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 600, 100, 600, 100, 2500, 2500, 55, 10, 'approved', 'approved', '2013-09-26 08:12:17', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2500, 2377, 0, 0, 1, 1, 5000, 5000, 56, 5, 'approved', 'approved', '2013-09-27 03:01:35', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 2377, 2254, 0, 0, 1, 1, 5000, 5000, 56, 5, 'approved', 'approved', '2013-09-27 03:01:39', '2013-04-04', NULL, '2013-04-04', NULL),
(2, 2254, 2131, 0, 0, 1, 1, 5000, 5000, 56, 5, 'approved', 'approved', '2013-09-27 03:02:00', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 100, 170, 100, 170, 2500, 2500, 57, 10, 'approved', 'approved', '2013-09-27 03:11:45', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 0, 70, 1, 0, 5000, 5000, 57, 5, 'approved', 'approved', '2013-09-27 03:11:45', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 170, 170, 170, 170, 2500, 2500, 57, 10, 'approved', 'approved', '2013-09-27 03:11:46', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 170, 2170, 170, 2170, 2500, 2500, 58, 10, 'approved', 'approved', '2013-09-27 03:28:33', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 70, 2070, 0, 0, 5000, 5000, 58, 5, 'approved', 'approved', '2013-09-27 03:28:33', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 2170, 2170, 2170, 2170, 2500, 2500, 58, 10, 'approved', 'approved', '2013-09-27 03:28:34', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 2170, 2370, 2170, 2370, 2500, 2500, 59, 10, 'approved', 'approved', '2013-09-27 03:29:09', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2070, 2270, 0, 0, 5000, 5000, 59, 5, 'approved', 'approved', '2013-09-27 03:29:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 2370, 2370, 2370, 2370, 2500, 2500, 59, 10, 'approved', 'approved', '2013-09-27 03:29:09', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 2370, 2499, 2370, 2499, 2500, 2500, 60, 10, 'approved', 'approved', '2013-09-27 03:29:31', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2270, 2399, 0, 0, 5000, 5000, 60, 5, 'approved', 'approved', '2013-09-27 03:29:31', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 2499, 2499, 2499, 2499, 2500, 2500, 60, 10, 'approved', 'approved', '2013-09-27 03:29:31', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 2499, 499, 2499, 499, 2500, 2500, 58, 10, 'approved', 'approved', '2013-09-27 05:50:27', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 499, 399, 499, 399, 2500, 2500, 56, 10, 'approved', 'approved', '2013-09-27 07:45:45', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 399, 400, 399, 400, 2500, 2500, 61, 10, 'approved', 'approved', '2013-09-27 08:43:02', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2399, 2400, 0, 0, 5000, 5000, 61, 5, 'approved', 'approved', '2013-09-27 08:43:02', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 400, 400, 400, 400, 2500, 2500, 61, 10, 'approved', 'approved', '2013-09-27 08:43:02', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 400, 401, 400, 401, 2500, 2500, 62, 10, 'approved', 'approved', '2013-09-27 10:44:01', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2400, 2401, 0, 0, 5000, 5000, 62, 5, 'approved', 'approved', '2013-09-27 10:44:01', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 401, 401, 401, 401, 2500, 2500, 62, 10, 'approved', 'approved', '2013-09-27 10:44:01', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 401, 402, 401, 402, 2500, 2500, 63, 10, 'approved', 'approved', '2013-09-27 08:47:08', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2401, 2402, 0, 0, 5000, 5000, 63, 5, 'approved', 'approved', '2013-09-27 08:47:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 402, 402, 402, 402, 2500, 2500, 63, 10, 'approved', 'approved', '2013-09-27 08:47:09', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 402, 403, 402, 403, 2500, 2500, 64, 10, 'approved', 'approved', '2013-09-27 08:47:42', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2402, 2403, 0, 0, 5000, 5000, 64, 5, 'approved', 'approved', '2013-09-27 08:47:42', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 403, 403, 403, 403, 2500, 2500, 64, 10, 'approved', 'approved', '2013-09-27 08:47:42', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 403, 404, 403, 404, 2500, 2500, 65, 10, 'approved', 'approved', '2013-09-27 08:52:39', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2403, 2404, 0, 0, 5000, 5000, 65, 5, 'approved', 'approved', '2013-09-27 08:52:39', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 404, 404, 404, 404, 2500, 2500, 65, 10, 'approved', 'approved', '2013-09-27 08:52:40', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 404, 405, 404, 405, 2500, 2500, 66, 10, 'approved', 'approved', '2013-09-30 14:05:15', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2404, 2405, 0, 0, 5000, 5000, 66, 5, 'approved', 'approved', '2013-09-30 14:05:15', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 405, 405, 405, 405, 2500, 2500, 66, 10, 'approved', 'approved', '2013-09-30 14:05:15', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 405, 406, 405, 406, 2500, 2500, 67, 10, 'approved', 'approved', '2013-09-30 14:05:31', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2405, 2406, 0, 0, 5000, 5000, 67, 5, 'approved', 'approved', '2013-09-30 14:05:31', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 406, 406, 406, 406, 2500, 2500, 67, 10, 'approved', 'approved', '2013-09-30 14:05:32', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 406, 408, 406, 408, 2500, 2500, 68, 10, 'approved', 'approved', '2013-09-30 04:32:10', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2406, 2408, 0, 0, 5000, 5000, 68, 5, 'approved', 'approved', '2013-09-30 04:32:10', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 408, 408, 408, 408, 2500, 2500, 68, 10, 'approved', 'approved', '2013-09-30 04:32:10', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 408, 411, 408, 411, 2500, 2500, 69, 10, 'approved', 'approved', '2013-10-03 10:34:38', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2408, 2411, 0, 0, 5000, 5000, 69, 5, 'approved', 'approved', '2013-10-03 10:34:38', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 411, 411, 411, 411, 2500, 2500, 69, 10, 'approved', 'approved', '2013-10-03 10:34:39', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 411, 511, 411, 511, 2500, 2500, 70, 10, 'approved', 'approved', '2013-09-30 05:04:48', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2411, 2511, 0, 0, 5000, 5000, 70, 5, 'approved', 'approved', '2013-09-30 05:04:48', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 511, 511, 511, 511, 2500, 2500, 70, 10, 'approved', 'approved', '2013-09-30 05:04:48', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 511, 561, 511, 561, 2500, 2500, 71, 10, 'approved', 'approved', '2013-09-30 11:05:35', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2511, 2561, 0, 0, 5000, 5000, 71, 5, 'approved', 'approved', '2013-09-30 11:05:36', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 561, 561, 561, 561, 2500, 2500, 71, 10, 'approved', 'approved', '2013-09-30 11:05:36', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 561, 562, 561, 562, 2500, 2500, 72, 10, 'approved', 'approved', '2013-09-30 06:31:48', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2561, 2562, 0, 0, 5000, 5000, 72, 5, 'approved', 'approved', '2013-09-30 06:31:48', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 562, 562, 562, 562, 2500, 2500, 72, 10, 'approved', 'approved', '2013-09-30 06:31:48', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 562, 564, 562, 564, 2500, 2500, 73, 10, 'approved', 'approved', '2013-09-30 06:42:56', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2562, 2564, 0, 0, 5000, 5000, 73, 5, 'approved', 'approved', '2013-09-30 06:42:56', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 564, 564, 564, 564, 2500, 2500, 73, 10, 'approved', 'approved', '2013-09-30 06:42:56', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 564, 494, 564, 494, 2500, 2500, 57, 10, 'approved', 'approved', '2013-09-30 06:46:00', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 494, 498, 494, 498, 2500, 2500, 74, 10, 'approved', 'approved', '2013-10-01 00:59:05', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2564, 2568, 0, 0, 5000, 5000, 74, 5, 'approved', 'approved', '2013-10-01 00:59:05', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 498, 498, 498, 498, 2500, 2500, 74, 10, 'approved', 'approved', '2013-10-01 00:59:05', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 498, 502, 498, 502, 2500, 2500, 75, 10, 'approved', 'approved', '2013-10-02 07:52:22', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2568, 2572, 0, 0, 5000, 5000, 75, 5, 'approved', 'approved', '2013-10-02 07:52:22', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 502, 502, 502, 502, 2500, 2500, 75, 10, 'approved', 'approved', '2013-10-02 07:52:22', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 502, 501, 502, 501, 2500, 2500, 61, 10, 'approved', 'approved', '2013-10-02 08:00:18', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 501, 500, 501, 500, 2500, 2500, 62, 10, 'approved', 'approved', '2013-10-02 08:00:23', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 500, 499, 500, 499, 2500, 2500, 63, 10, 'approved', 'approved', '2013-10-02 08:00:27', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 499, 498, 499, 498, 2500, 2500, 64, 10, 'approved', 'approved', '2013-10-02 08:00:31', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 498, 497, 498, 497, 2500, 2500, 65, 10, 'approved', 'approved', '2013-10-02 08:00:36', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 497, 496, 497, 496, 2500, 2500, 66, 10, 'approved', 'approved', '2013-10-02 08:00:40', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 496, 495, 496, 495, 2500, 2500, 67, 10, 'approved', 'approved', '2013-10-02 08:00:44', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 495, 494, 495, 494, 2500, 2500, 72, 10, 'approved', 'approved', '2013-10-02 08:00:48', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 494, 490, 494, 490, 2500, 2500, 75, 10, 'approved', 'approved', '2013-10-02 08:00:52', '2013-09-26', NULL, '2013-09-26', NULL),
(22, 2500, 2500, 490, 497, 490, 497, 2500, 2500, 76, 10, 'approved', 'approved', '2013-10-04 10:00:08', '2013-09-26', NULL, '2013-09-26', NULL),
(2, 2131, 2131, 2572, 2579, 0, 0, 5000, 5000, 76, 5, 'approved', 'approved', '2013-10-04 10:00:09', '2013-04-04', NULL, '2013-04-04', NULL),
(22, 2500, 2500, 497, 497, 497, 497, 2500, 2500, 76, 10, 'approved', 'approved', '2013-10-04 10:00:09', '2013-09-26', NULL, '2013-09-26', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `credit_logs`
--

CREATE TABLE IF NOT EXISTS `credit_logs` (
  `credit_id` int(10) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL,
  `amount` int(10) DEFAULT NULL,
  `date_borrowed` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(30) DEFAULT NULL,
  `approval_status` varchar(30) DEFAULT 'initial',
  `delivery_status` varchar(30) DEFAULT 'requested',
  `delivered_amount` int(10) DEFAULT '0',
  `amount_paid` int(10) DEFAULT '0',
  `date_due` date DEFAULT NULL,
  PRIMARY KEY (`credit_id`),
  KEY `credit_idx` (`credit_id`),
  KEY `credit_pb_idx` (`companyid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=77 ;

--
-- Dumping data for table `credit_logs`
--

INSERT INTO `credit_logs` (`credit_id`, `companyid`, `amount`, `date_borrowed`, `status`, `approval_status`, `delivery_status`, `delivered_amount`, `amount_paid`, `date_due`) VALUES
(32, 22, 1, '2013-06-19 09:42:46', 'paid', 'approved', 'completed', 0, 0, '2013-04-11'),
(33, 22, 1, '2013-06-19 09:42:15', 'cancelled', 'approved', 'completed', 0, 1, '2013-04-11'),
(34, 22, 100, '2013-09-25 02:41:11', 'paid', 'approved', 'completed', 0, 100, '2013-05-08'),
(35, 22, 5, '2013-06-19 08:04:52', 'paid', 'approved', 'completed', 0, 5, '2013-05-09'),
(36, 22, 5, '2013-09-25 02:43:05', 'paid', 'approved', 'completed', 0, 5, '2013-05-12'),
(37, 22, 5, '2013-09-25 02:43:44', 'paid', 'approved', 'completed', 0, 5, '2013-05-09'),
(39, 22, 1, '2013-09-25 02:43:56', 'paid', 'approved', 'completed', 0, 1, '2013-09-24'),
(40, 22, 100, '2013-09-25 02:52:15', 'paid', 'approved', 'completed', 0, 100, '2013-09-25'),
(41, 22, 150, '2013-09-25 03:05:36', 'paid', 'approved', 'completed', 0, 150, '2013-09-25'),
(42, 22, 237, '2013-09-25 07:10:00', 'paid', 'approved', 'completed', 0, 237, '2013-09-25'),
(43, 22, 150, '2013-09-25 03:20:37', 'paid', 'approved', 'completed', 0, 150, '2013-09-25'),
(44, 22, 68, '2013-09-25 07:08:56', 'paid', 'approved', 'completed', 0, 68, '2013-09-25'),
(45, 22, 231, '2013-09-25 08:49:06', 'paid', 'approved', 'completed', 231, 231, '2013-09-25'),
(46, 22, 500, '2013-09-25 08:53:53', 'paid', 'approved', 'completed', 500, 500, '2013-09-25'),
(47, 22, 243, '2013-09-25 09:08:38', 'paid', 'approved', 'completed', 243, 243, '2013-09-25'),
(48, 22, 100, '2013-09-25 09:12:35', 'paid', 'approved', 'completed', 100, 100, '2013-09-25'),
(49, 22, 25, '2013-09-25 09:17:05', 'paid', 'approved', 'completed', 25, 25, '2013-09-25'),
(50, 22, 50, '2013-09-25 09:17:42', 'paid', 'approved', 'completed', 50, 50, '2013-09-25'),
(51, 22, 33, '2013-09-25 09:18:06', 'paid', 'approved', 'completed', 33, 33, '2013-09-25'),
(52, 22, 1000, '2013-09-26 02:31:07', 'paid', 'approved', 'completed', 1000, 1000, '2013-09-26'),
(53, 22, 1000, '2013-09-26 05:41:43', 'paid', 'approved', 'completed', 1000, 1000, '2013-09-26'),
(54, 22, 1200, '2013-09-26 06:21:37', 'paid', 'approved', 'completed', 1200, 1200, '2013-09-26'),
(55, 22, 500, '2013-09-26 08:12:18', 'paid', 'approved', 'completed', 500, 500, '2013-09-26'),
(56, 22, 100, '2013-09-27 07:45:45', 'paid', 'approved', 'completed', 100, 100, '2013-09-26'),
(57, 22, 70, '2013-09-30 06:46:01', 'paid', 'approved', 'completed', 70, 70, '2013-09-27'),
(58, 22, 2000, '2013-09-27 05:50:28', 'paid', 'approved', 'completed', 2000, 2000, '2013-09-27'),
(59, 22, 200, '2013-09-27 03:29:09', 'unpaid', 'approved', 'requested', 0, 0, '2013-09-27'),
(60, 22, 129, '2013-09-27 03:29:31', 'unpaid', 'approved', 'requested', 0, 0, '2013-09-27'),
(61, 22, 1, '2013-10-02 08:00:19', 'paid', 'approved', 'completed', 1, 1, '2013-09-27'),
(62, 22, 1, '2013-10-02 08:00:23', 'paid', 'approved', 'completed', 1, 1, '2013-09-27'),
(63, 22, 1, '2013-10-02 08:00:27', 'paid', 'approved', 'completed', 1, 1, '2013-09-27'),
(64, 22, 1, '2013-10-02 08:00:32', 'paid', 'approved', 'completed', 1, 1, '2013-09-27'),
(65, 22, 1, '2013-10-02 08:00:36', 'paid', 'approved', 'completed', 1, 1, '2013-09-27'),
(66, 22, 1, '2013-10-02 08:00:41', 'paid', 'approved', 'completed', 1, 1, '2013-09-30'),
(67, 22, 1, '2013-10-02 08:00:44', 'paid', 'approved', 'completed', 1, 1, '2013-09-30'),
(68, 22, 2, '2013-09-30 04:32:09', 'unpaid', 'approved', 'requested', 0, 0, '2013-09-30'),
(69, 22, 3, '2013-10-03 10:34:38', 'unpaid', 'approved', 'requested', 0, 0, '2013-10-03'),
(70, 22, 100, '2013-09-30 05:04:47', 'unpaid', 'approved', 'requested', 0, 0, '2013-09-30'),
(71, 22, 50, '2013-09-30 11:05:35', 'unpaid', 'approved', 'requested', 0, 0, '2013-09-30'),
(72, 22, 1, '2013-10-02 08:00:49', 'paid', 'approved', 'completed', 1, 1, '2013-09-30'),
(73, 22, 2, '2013-09-30 06:42:56', 'unpaid', 'approved', 'requested', 0, 0, '2013-09-30'),
(74, 22, 4, '2013-10-01 00:59:05', 'unpaid', 'approved', 'requested', 0, 0, '2013-10-01'),
(75, 22, 4, '2013-10-02 08:00:52', 'paid', 'approved', 'completed', 4, 4, '2013-10-02'),
(76, 22, 7, '2013-10-04 10:00:08', 'unpaid', 'approved', 'requested', 0, 0, '2013-10-04');

-- --------------------------------------------------------

--
-- Table structure for table `credit_payment_logs`
--

CREATE TABLE IF NOT EXISTS `credit_payment_logs` (
  `credit_id` int(10) DEFAULT NULL,
  `amount` int(10) DEFAULT NULL,
  `date_payment` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(30) DEFAULT NULL,
  `companyid` int(11) DEFAULT NULL,
  `userid` varchar(30) DEFAULT NULL,
  `levelid` int(11) DEFAULT NULL,
  KEY `credit_payment_idx` (`credit_id`),
  KEY `credit_payment_pb_idx` (`companyid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_payment_logs`
--

INSERT INTO `credit_payment_logs` (`credit_id`, `amount`, `date_payment`, `status`, `companyid`, `userid`, `levelid`) VALUES
(32, 1, '2013-06-18 07:40:16', 'cancelled', 12, 'arga', 3),
(33, 1, '2013-04-11 01:01:51', 'paid', 2, 'arga', 2),
(34, 100, '2013-05-14 10:41:41', 'paid', 2, 'arga', 2),
(35, 5, '2013-06-19 07:52:58', 'paid', 2, 'arga', 2),
(35, 5, '2013-06-19 07:59:50', 'paid', 2, 'arga', 2),
(35, 5, '2013-06-19 07:59:51', 'paid', 2, 'arga', 2),
(35, 5, '2013-06-19 08:00:10', 'paid', 2, 'arga', 2),
(35, 5, '2013-06-19 08:04:52', 'paid', 2, 'arga', 2),
(34, 100, '2013-09-25 02:41:11', 'paid', 2, 'arga', 2),
(36, 5, '2013-09-25 02:43:05', 'paid', 2, 'arga', 2),
(37, 5, '2013-09-25 02:43:44', 'paid', 2, 'arga', 2),
(39, 1, '2013-09-25 02:43:56', 'paid', 2, 'arga', 2),
(40, 100, '2013-09-25 02:50:17', 'paid', 2, 'arga', 2),
(40, 100, '2013-09-25 02:52:15', 'paid', 2, 'arga', 2),
(41, 150, '2013-09-25 03:05:36', 'paid', 2, 'arga', 2),
(42, 237, '2013-09-25 03:19:17', 'paid', 2, 'arga', 2),
(42, 237, '2013-09-25 03:20:30', 'paid', 2, 'arga', 2),
(43, 150, '2013-09-25 03:20:37', 'paid', 2, 'arga', 2),
(44, 68, '2013-09-25 05:24:42', 'paid', 2, 'arga', 2),
(44, 68, '2013-09-25 07:08:56', 'paid', 2, 'arga', 2),
(42, 237, '2013-09-25 07:10:01', 'paid', 2, 'arga', 2),
(45, 231, '2013-09-25 08:49:06', 'paid', 2, 'arga', 2),
(46, 500, '2013-09-25 08:53:53', 'paid', 2, 'arga', 2),
(47, 243, '2013-09-25 09:08:38', 'paid', 2, 'arga', 2),
(48, 100, '2013-09-25 09:12:35', 'paid', 2, 'arga', 2),
(49, 25, '2013-09-25 09:17:05', 'paid', 2, 'arga', 2),
(50, 50, '2013-09-25 09:17:42', 'paid', 2, 'arga', 2),
(51, 33, '2013-09-25 09:18:06', 'paid', 2, 'arga', 2),
(52, 1000, '2013-09-26 02:31:07', 'paid', 2, 'arga', 2),
(53, 1000, '2013-09-26 05:41:43', 'paid', 2, 'arga', 2),
(54, 1200, '2013-09-26 06:21:37', 'paid', 2, 'arga', 2),
(55, 500, '2013-09-26 08:12:18', 'paid', 2, 'arga', 2),
(58, 2000, '2013-09-27 05:50:28', 'paid', 2, 'arga', 2),
(56, 100, '2013-09-27 07:45:45', 'paid', 2, 'arga', 2),
(57, 70, '2013-09-30 06:46:01', 'paid', 2, 'arga', 2),
(61, 1, '2013-10-02 08:00:19', 'paid', 2, 'arga', 2),
(62, 1, '2013-10-02 08:00:23', 'paid', 2, 'arga', 2),
(63, 1, '2013-10-02 08:00:27', 'paid', 2, 'arga', 2),
(64, 1, '2013-10-02 08:00:32', 'paid', 2, 'arga', 2),
(65, 1, '2013-10-02 08:00:36', 'paid', 2, 'arga', 2),
(66, 1, '2013-10-02 08:00:41', 'paid', 2, 'arga', 2),
(67, 1, '2013-10-02 08:00:44', 'paid', 2, 'arga', 2),
(72, 1, '2013-10-02 08:00:49', 'paid', 2, 'arga', 2),
(75, 4, '2013-10-02 08:00:52', 'paid', 2, 'arga', 2);

--
-- Triggers `credit_payment_logs`
--
DROP TRIGGER IF EXISTS `credit_payment_insert`;
DELIMITER //
CREATE TRIGGER `credit_payment_insert` AFTER INSERT ON `credit_payment_logs`
 FOR EACH ROW begin
	if NEW.amount > 0 THEN
	
			if NEW.status = 'paid' then
				update credit_logs set amount_paid = amount_paid + NEW.amount,status = case when amount_paid + NEW.amount >= amount then 'paid' when amount_paid + NEW.amount > 0 then 'partial' else status end where credit_id = NEW.credit_id and amount_paid + NEW.amount <= amount;  					
			else	
				update credit_logs set status = case when amount_paid  >= amount then 'paid' when amount_paid  > 0 then 'partial' else status end where credit_id = NEW.credit_id ;
			end if;
		
   	end if;
	 insert into credit_payment_logs_history (credit_id, newstatus, changedate,  new_userid) values (NEW.credit_id, NEW.status,now(),  NEW.userid);
end
//
DELIMITER ;
DROP TRIGGER IF EXISTS `credit_payment_update`;
DELIMITER //
CREATE TRIGGER `credit_payment_update` AFTER UPDATE ON `credit_payment_logs`
 FOR EACH ROW begin
	if NEW.amount > 0 THEN
		
			if NEW.status = 'paid' then
				if OLD.status <> 'paid' then
					UPDATE credit_logs SET amount_paid = amount_paid + NEW.amount,STATUS = CASE WHEN amount_paid + NEW.amount >= amount THEN 'paid' WHEN amount_paid + NEW.amount > 0 THEN 'partial'  when OLD.status = 'unpaid' then 'unpaid' else 'cancelled' END WHERE credit_id = NEW.credit_id AND amount_paid + NEW.amount <= amount;
				end if;
			elseif NEW.status = 'cancelled' then
				if OLD.status = 'paid' then
					UPDATE credit_logs SET amount_paid = amount_paid - NEW.amount,STATUS =  CASE WHEN amount_paid - NEW.amount >= amount THEN 'paid' WHEN amount_paid - NEW.amount  > 0 THEN 'partial' WHEN OLD.status = 'unpaid' THEN 'unpaid' ELSE 'cancelled' END WHERE credit_id = NEW.credit_id AND amount_paid - NEW.amount <= amount AND amount_paid - NEW.amount >= 0;  					
				end if;
			else	
				update credit_logs set status = case when amount_paid  >= amount then 'paid' when amount_paid  > 0 then 'partial' else 'unpaid' end where credit_id = NEW.credit_id ;
			end if;
	
   	end if;
	 insert into credit_payment_logs_history (credit_id, oldstatus,newstatus, changedate,  old_userid, new_userid) values (NEW.credit_id, OLD.status,NEW.status,now(), OLD.userid, NEW.userid);
end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `credit_payment_logs_history`
--

CREATE TABLE IF NOT EXISTS `credit_payment_logs_history` (
  `credit_id` int(10) DEFAULT NULL,
  `oldstatus` varchar(30) DEFAULT NULL,
  `newstatus` varchar(30) DEFAULT NULL,
  `changedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `companyid` int(11) DEFAULT NULL,
  `old_userid` varchar(30) DEFAULT NULL,
  `new_userid` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_payment_logs_history`
--

INSERT INTO `credit_payment_logs_history` (`credit_id`, `oldstatus`, `newstatus`, `changedate`, `companyid`, `old_userid`, `new_userid`) VALUES
(9, NULL, 'paid', '2013-04-03 07:27:31', NULL, NULL, 'arga'),
(9, NULL, 'paid', '2013-04-03 07:31:50', NULL, NULL, 'arga'),
(9, NULL, 'paid', '2013-04-03 07:34:39', NULL, NULL, 'arga'),
(9, NULL, 'paid', '2013-04-03 07:35:42', NULL, NULL, 'arga'),
(9, NULL, 'paid', '2013-04-03 07:40:39', NULL, NULL, 'arga'),
(11, NULL, 'paid', '2013-04-03 07:45:18', NULL, NULL, 'arga'),
(12, NULL, 'paid', '2013-04-03 07:50:20', NULL, NULL, 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 07:58:19', NULL, 'arga', 'arga'),
(12, 'cancelled', 'paid', '2013-04-03 08:14:26', NULL, 'arga', 'arga'),
(12, 'paid', 'paid', '2013-04-03 08:15:08', NULL, 'arga', 'arga'),
(12, 'paid', 'paid', '2013-04-03 08:15:11', NULL, 'arga', 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 08:16:31', NULL, 'arga', 'arga'),
(12, 'cancelled', 'paid', '2013-04-03 08:19:07', NULL, 'arga', 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 08:19:21', NULL, 'arga', 'arga'),
(12, 'cancelled', 'paid', '2013-04-03 08:22:54', NULL, 'arga', 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 08:23:16', NULL, 'arga', 'arga'),
(12, 'cancelled', 'paid', '2013-04-03 08:25:25', NULL, 'arga', 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 08:27:35', NULL, 'arga', 'arga'),
(12, 'cancelled', 'paid', '2013-04-03 08:28:11', NULL, 'arga', 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 08:33:05', NULL, 'arga', 'arga'),
(12, 'cancelled', 'paid', '2013-04-03 08:33:24', NULL, 'arga', 'arga'),
(12, 'paid', 'cancelled', '2013-04-03 08:42:34', NULL, 'arga', 'arga'),
(13, NULL, 'paid', '2013-04-04 08:48:08', NULL, NULL, 'arga'),
(16, NULL, 'paid', '2013-04-04 10:44:06', NULL, NULL, 'arga'),
(27, NULL, 'paid', '2013-04-10 04:08:53', NULL, NULL, 'arga'),
(26, NULL, 'paid', '2013-04-10 05:52:29', NULL, NULL, 'arga'),
(26, 'paid', 'unpaid', '2013-04-12 08:11:47', NULL, 'arga', 'arga'),
(28, NULL, 'paid', '2013-04-11 00:33:16', NULL, NULL, 'arga'),
(29, NULL, 'paid', '2013-04-11 00:36:49', NULL, NULL, 'arga'),
(30, NULL, 'paid', '2013-04-11 00:37:09', NULL, NULL, 'arga'),
(31, NULL, 'paid', '2013-04-11 00:37:14', NULL, NULL, 'arga'),
(25, NULL, 'paid', '2013-04-11 00:38:24', NULL, NULL, 'arga'),
(32, NULL, 'paid', '2013-04-11 00:54:47', NULL, NULL, 'arga-dsp'),
(33, NULL, 'paid', '2013-04-11 01:01:51', NULL, NULL, 'arga'),
(34, NULL, 'paid', '2013-05-14 10:41:41', NULL, NULL, 'arga'),
(32, 'paid', 'cancelled', '2013-06-18 06:35:16', NULL, 'arga-dsp', 'arga'),
(32, 'cancelled', 'paid', '2013-06-18 06:46:02', NULL, 'arga', 'arga'),
(32, 'paid', 'cancelled', '2013-06-18 07:40:16', NULL, 'arga', 'arga'),
(35, NULL, 'paid', '2013-06-19 07:52:58', NULL, NULL, 'arga'),
(35, NULL, 'paid', '2013-06-19 07:59:50', NULL, NULL, 'arga'),
(35, NULL, 'paid', '2013-06-19 07:59:51', NULL, NULL, 'arga'),
(35, NULL, 'paid', '2013-06-19 08:00:10', NULL, NULL, 'arga'),
(35, NULL, 'paid', '2013-06-19 08:04:52', NULL, NULL, 'arga'),
(34, NULL, 'paid', '2013-09-25 02:41:11', NULL, NULL, 'arga'),
(36, NULL, 'paid', '2013-09-25 02:43:05', NULL, NULL, 'arga'),
(37, NULL, 'paid', '2013-09-25 02:43:44', NULL, NULL, 'arga'),
(39, NULL, 'paid', '2013-09-25 02:43:56', NULL, NULL, 'arga'),
(40, NULL, 'paid', '2013-09-25 02:50:17', NULL, NULL, 'arga'),
(40, NULL, 'paid', '2013-09-25 02:52:15', NULL, NULL, 'arga'),
(41, NULL, 'paid', '2013-09-25 03:05:36', NULL, NULL, 'arga'),
(42, NULL, 'paid', '2013-09-25 03:19:17', NULL, NULL, 'arga'),
(42, NULL, 'paid', '2013-09-25 03:20:30', NULL, NULL, 'arga'),
(43, NULL, 'paid', '2013-09-25 03:20:37', NULL, NULL, 'arga'),
(44, NULL, 'paid', '2013-09-25 05:24:42', NULL, NULL, 'arga'),
(44, NULL, 'paid', '2013-09-25 07:08:56', NULL, NULL, 'arga'),
(42, NULL, 'paid', '2013-09-25 07:10:01', NULL, NULL, 'arga'),
(45, NULL, 'paid', '2013-09-25 08:49:06', NULL, NULL, 'arga'),
(46, NULL, 'paid', '2013-09-25 08:53:53', NULL, NULL, 'arga'),
(47, NULL, 'paid', '2013-09-25 09:08:38', NULL, NULL, 'arga'),
(48, NULL, 'paid', '2013-09-25 09:12:35', NULL, NULL, 'arga'),
(49, NULL, 'paid', '2013-09-25 09:17:05', NULL, NULL, 'arga'),
(50, NULL, 'paid', '2013-09-25 09:17:42', NULL, NULL, 'arga'),
(51, NULL, 'paid', '2013-09-25 09:18:06', NULL, NULL, 'arga'),
(52, NULL, 'paid', '2013-09-26 02:31:07', NULL, NULL, 'arga'),
(53, NULL, 'paid', '2013-09-26 05:41:43', NULL, NULL, 'arga'),
(54, NULL, 'paid', '2013-09-26 06:21:37', NULL, NULL, 'arga'),
(55, NULL, 'paid', '2013-09-26 08:12:18', NULL, NULL, 'arga'),
(58, NULL, 'paid', '2013-09-27 05:50:28', NULL, NULL, 'arga'),
(56, NULL, 'paid', '2013-09-27 07:45:45', NULL, NULL, 'arga'),
(57, NULL, 'paid', '2013-09-30 06:46:01', NULL, NULL, 'arga'),
(61, NULL, 'paid', '2013-10-02 08:00:19', NULL, NULL, 'arga'),
(62, NULL, 'paid', '2013-10-02 08:00:23', NULL, NULL, 'arga'),
(63, NULL, 'paid', '2013-10-02 08:00:27', NULL, NULL, 'arga'),
(64, NULL, 'paid', '2013-10-02 08:00:32', NULL, NULL, 'arga'),
(65, NULL, 'paid', '2013-10-02 08:00:36', NULL, NULL, 'arga'),
(66, NULL, 'paid', '2013-10-02 08:00:41', NULL, NULL, 'arga'),
(67, NULL, 'paid', '2013-10-02 08:00:44', NULL, NULL, 'arga'),
(72, NULL, 'paid', '2013-10-02 08:00:49', NULL, NULL, 'arga'),
(75, NULL, 'paid', '2013-10-02 08:00:52', NULL, NULL, 'arga');

-- --------------------------------------------------------

--
-- Table structure for table `credit_transfer_logs`
--

CREATE TABLE IF NOT EXISTS `credit_transfer_logs` (
  `transferlogid` int(11) NOT NULL AUTO_INCREMENT,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `transferdate` timestamp NULL DEFAULT NULL,
  `credit_id` int(10) NOT NULL,
  `retailersim` varchar(50) DEFAULT NULL,
  `OpenSIM` varchar(20) DEFAULT NULL,
  `end_balance` float(10,2) DEFAULT '0.00',
  `balance_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `amount` int(10) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `transfer_status` enum('initial','inprocess','completed','failed') DEFAULT NULL,
  `transferby` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`transferlogid`),
  KEY `transfer_logs_idx` (`balance_time`,`transfer_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=82 ;

--
-- Dumping data for table `credit_transfer_logs`
--

INSERT INTO `credit_transfer_logs` (`transferlogid`, `createddate`, `transferdate`, `credit_id`, `retailersim`, `OpenSIM`, `end_balance`, `balance_time`, `amount`, `status`, `transfer_status`, `transferby`) VALUES
(1, '2013-03-07 02:50:55', NULL, 2, NULL, NULL, 0.00, '0000-00-00 00:00:00', 1000, NULL, 'initial', 'arga-ret'),
(2, '2013-03-07 02:52:09', NULL, 3, NULL, NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(3, '2013-03-07 02:52:32', NULL, 4, NULL, NULL, 0.00, '0000-00-00 00:00:00', 200, NULL, 'initial', 'arga-ret'),
(4, '2013-03-07 03:02:20', NULL, 5, NULL, NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(5, '2013-03-07 03:02:26', NULL, 6, NULL, NULL, 0.00, '0000-00-00 00:00:00', 1000, NULL, 'initial', 'arga-ret'),
(6, '2013-03-07 03:15:43', NULL, 7, NULL, NULL, 0.00, '0000-00-00 00:00:00', 2000, NULL, 'initial', 'arga-ret'),
(7, '2013-03-07 03:16:49', NULL, 8, NULL, NULL, 0.00, '0000-00-00 00:00:00', 250, NULL, 'initial', 'arga-ret'),
(8, '2013-03-07 03:18:09', NULL, 9, NULL, NULL, 0.00, '0000-00-00 00:00:00', 250, NULL, 'initial', 'arga-ret'),
(9, '2013-03-07 03:34:53', NULL, 10, NULL, NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(10, '2013-03-07 04:14:25', NULL, 11, NULL, NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(11, '2013-03-07 04:15:57', NULL, 12, '09191234561', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(12, '2013-03-13 05:33:26', NULL, 2, '09191234561', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(13, '2013-03-14 07:07:39', NULL, 2, '09191234561', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(14, '2013-03-14 09:26:30', NULL, 2, '09191234561', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(15, '2013-04-03 02:14:34', '2013-04-03 02:14:34', 8, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(16, '2013-04-03 02:24:38', '2013-04-03 02:24:38', 9, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(17, '2013-04-03 07:44:47', '2013-04-03 07:44:47', 11, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(18, '2013-04-03 07:46:32', '2013-04-03 07:46:32', 12, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(19, '2013-04-04 07:50:49', '2013-04-04 07:50:49', 13, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1000, NULL, 'initial', 'arga-ret'),
(20, '2013-04-04 09:05:23', '2013-04-04 09:05:23', 14, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(21, '2013-04-04 10:41:20', '2013-04-04 10:41:20', 16, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1000, NULL, 'initial', 'arga-ret'),
(22, '2013-04-10 01:14:58', '2013-04-10 01:14:58', 17, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'initial', 'arga-ret'),
(23, '2013-04-09 20:24:50', '2013-04-09 20:24:50', 18, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(24, '2013-04-09 20:33:58', '2013-04-09 20:33:58', 19, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(25, '2013-04-09 20:35:35', '2013-04-09 20:35:35', 20, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(26, '2013-04-10 11:36:20', '2013-04-10 11:36:20', 21, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(27, '2013-04-10 11:42:14', '2013-04-10 11:42:14', 22, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(28, '2013-04-10 02:47:28', '2013-04-10 02:47:28', 23, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(29, '2013-04-10 02:48:41', '2013-04-10 02:48:41', 24, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(30, '2013-04-10 14:51:34', '2013-04-10 14:51:34', 25, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(31, '2013-04-10 02:56:05', '2013-04-10 02:56:05', 26, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(32, '2013-04-09 23:57:54', '2013-04-09 23:57:54', 27, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(33, '2013-04-10 08:00:49', '2013-04-10 08:00:49', 28, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(34, '2013-04-12 08:09:09', '2013-04-12 08:09:09', 29, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(35, '2013-04-12 08:09:57', '2013-04-12 08:09:57', 30, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(36, '2013-04-12 08:16:20', '2013-04-12 08:16:20', 31, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(37, '2013-04-11 00:41:15', '2013-04-11 00:41:15', 32, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'initial', 'arga-ret'),
(38, '2013-04-11 00:44:28', '2013-04-11 00:44:28', 33, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'initial', 'arga-ret'),
(39, '2013-04-19 06:02:07', '2013-04-19 06:02:07', 34, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(40, '2013-05-09 09:49:12', '2013-05-09 09:49:12', 35, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 5, NULL, 'initial', 'arga-ret'),
(41, '2013-05-12 10:07:29', '2013-05-12 10:07:29', 36, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 5, NULL, 'initial', 'arga-ret'),
(42, '2013-05-09 10:37:02', '2013-05-09 10:37:02', 37, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 5, NULL, 'initial', 'arga-ret'),
(43, '2013-05-14 11:21:53', '2013-05-14 11:21:53', 38, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 35, NULL, 'initial', 'arga-ret'),
(44, '2013-09-24 06:15:03', '2013-09-24 06:15:03', 39, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'initial', 'arga-ret'),
(45, '2013-09-25 02:47:37', '2013-09-25 02:47:37', 40, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(46, '2013-09-25 03:04:16', '2013-09-25 03:04:16', 41, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 150, NULL, 'initial', 'arga-ret'),
(47, '2013-09-25 03:06:11', '2013-09-25 03:06:11', 42, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 237, NULL, 'initial', 'arga-ret'),
(48, '2013-09-25 03:08:02', '2013-09-25 03:08:02', 43, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 150, NULL, 'initial', 'arga-ret'),
(49, '2013-09-25 03:08:17', '2013-09-25 03:08:17', 44, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 68, NULL, 'initial', 'arga-ret'),
(50, '2013-09-25 08:48:49', '2013-09-25 03:08:35', 45, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 231, NULL, 'completed', 'arga-ret'),
(51, '2013-09-25 08:53:44', '2013-09-25 03:08:43', 46, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'completed', 'arga-ret'),
(52, '2013-09-25 09:08:30', '2013-09-25 03:08:51', 47, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 243, NULL, 'completed', 'arga-ret'),
(53, '2013-09-25 09:12:31', '2013-09-25 03:09:09', 48, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'completed', 'arga-ret'),
(54, '2013-09-25 09:17:01', '2013-09-25 03:11:15', 49, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 25, NULL, 'completed', 'arga-ret'),
(55, '2013-09-25 09:17:38', '2013-09-25 03:11:22', 50, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'completed', 'arga-ret'),
(56, '2013-09-25 09:17:57', '2013-09-25 07:10:44', 51, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 33, NULL, 'completed', 'arga-ret'),
(57, '2013-09-26 02:30:45', '2013-09-26 02:05:03', 52, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1000, NULL, 'completed', 'arga-ret'),
(58, '2013-09-26 05:19:46', '2013-09-26 05:19:16', 53, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1000, NULL, 'completed', 'arga-ret'),
(59, '2013-09-26 06:21:27', '2013-09-26 06:20:55', 54, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1200, NULL, 'completed', 'arga-ret'),
(60, '2013-09-26 06:56:46', '2013-09-26 06:25:31', 55, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 500, NULL, 'completed', 'arga-ret'),
(61, '2013-09-27 07:45:38', '2013-09-26 06:36:23', 56, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'completed', 'arga-ret'),
(62, '2013-09-30 06:45:45', '2013-09-27 03:11:45', 57, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 70, NULL, 'completed', 'arga-ret'),
(63, '2013-09-27 05:50:19', '2013-09-27 03:28:33', 58, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 2000, NULL, 'completed', 'arga-ret'),
(64, '2013-09-27 03:29:09', '2013-09-27 03:29:09', 59, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 200, NULL, 'initial', 'arga-ret'),
(65, '2013-09-27 03:29:31', '2013-09-27 03:29:31', 60, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 129, NULL, 'initial', 'arga-ret'),
(66, '2013-10-02 07:59:23', '2013-09-27 08:43:02', 61, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(67, '2013-10-02 07:59:30', '2013-09-27 10:44:01', 62, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(68, '2013-10-02 07:59:37', '2013-09-27 08:47:09', 63, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(69, '2013-10-02 07:59:47', '2013-09-27 08:47:42', 64, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(70, '2013-10-02 07:59:52', '2013-09-27 08:52:40', 65, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(71, '2013-10-02 07:59:56', '2013-09-30 14:05:15', 66, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(72, '2013-10-02 08:00:01', '2013-09-30 14:05:32', 67, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(73, '2013-09-30 04:32:10', '2013-09-30 04:32:10', 68, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 2, NULL, 'initial', 'arga-ret'),
(74, '2013-10-03 10:34:39', '2013-10-03 10:34:39', 69, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 3, NULL, 'initial', 'arga-ret'),
(75, '2013-09-30 05:04:48', '2013-09-30 05:04:48', 70, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 100, NULL, 'initial', 'arga-ret'),
(76, '2013-09-30 11:05:36', '2013-09-30 11:05:36', 71, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 50, NULL, 'initial', 'arga-ret'),
(77, '2013-10-02 08:00:08', '2013-09-30 06:31:48', 72, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 1, NULL, 'completed', 'arga-ret'),
(78, '2013-09-30 06:42:56', '2013-09-30 06:42:56', 73, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 2, NULL, 'initial', 'arga-ret'),
(79, '2013-10-01 00:59:05', '2013-10-01 00:59:05', 74, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 4, NULL, 'initial', 'arga-ret'),
(80, '2013-10-02 08:00:13', '2013-10-02 07:52:22', 75, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 4, NULL, 'completed', 'arga-ret'),
(81, '2013-10-04 10:00:09', '2013-10-04 10:00:09', 76, '09191234562', NULL, 0.00, '0000-00-00 00:00:00', 7, NULL, 'initial', 'arga-ret');

--
-- Triggers `credit_transfer_logs`
--
DROP TRIGGER IF EXISTS `credit_transfer_update`;
DELIMITER //
CREATE TRIGGER `credit_transfer_update` AFTER UPDATE ON `credit_transfer_logs`
 FOR EACH ROW begin
	if NEW.amount > 0 THEN
		if NEW.transfer_status = 'completed' then
			update credit_logs set delivered_amount = delivered_amount + NEW.amount,delivery_status = case when delivered_amount + NEW.amount >= amount then 'completed' when delivered_amount + NEW.amount > 0 then 'partial' else delivery_status end where credit_id = NEW.credit_id and delivered_amount  + NEW.amount <= amount;  					
		end if;
	end if;
end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `daily_transfer_report`
--

CREATE TABLE IF NOT EXISTS `daily_transfer_report` (
  `transferreport_id` int(11) NOT NULL AUTO_INCREMENT,
  `transferfrom_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `transferto_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OPENSIM` varchar(20) DEFAULT NULL,
  `inprogress_amount` float(10,2) DEFAULT NULL,
  `amount_transfered` float(10,2) DEFAULT NULL,
  `companyid` int(10) DEFAULT NULL,
  `creditlimitid` int(10) DEFAULT NULL,
  `outstanding_balance` float(10,2) DEFAULT NULL,
  `payment_status` varchar(50) DEFAULT 'unpaid',
  `payment_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `amount_paid` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`transferreport_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `daily_transfer_report`
--

INSERT INTO `daily_transfer_report` (`transferreport_id`, `transferfrom_time`, `transferto_time`, `OPENSIM`, `inprogress_amount`, `amount_transfered`, `companyid`, `creditlimitid`, `outstanding_balance`, `payment_status`, `payment_date`, `amount_paid`) VALUES
(1, '0000-00-00 00:00:00', '2013-09-26 06:55:28', '123456', 500.00, 300.00, 2, 5, 0.00, 'paid', '2013-09-26 06:55:28', 3800.00);

-- --------------------------------------------------------

--
-- Table structure for table `holiday_tbl`
--

CREATE TABLE IF NOT EXISTS `holiday_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `holiday_from` datetime NOT NULL,
  `holiday_to` datetime NOT NULL,
  `active` varchar(1) NOT NULL DEFAULT 'y',
  PRIMARY KEY (`id`),
  UNIQUE KEY `holiday_from` (`holiday_from`),
  UNIQUE KEY `holiday_to` (`holiday_to`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `holiday_tbl`
--

INSERT INTO `holiday_tbl` (`id`, `holiday_from`, `holiday_to`, `active`) VALUES
(1, '2013-04-10 00:00:00', '2013-04-10 00:00:00', 'N'),
(2, '2013-05-08 12:00:00', '2013-05-08 12:00:00', 'y'),
(3, '2013-05-13 12:00:00', '2013-05-13 12:00:00', 'y'),
(4, '2013-09-06 00:00:00', '2013-09-30 00:00:00', 'y'),
(5, '2013-12-25 00:00:00', '2013-12-25 00:00:00', 'N'),
(6, '2014-01-01 00:00:00', '2014-01-01 00:00:00', 'y'),
(8, '2013-11-01 00:00:00', '2013-11-02 00:00:00', 'y'),
(10, '2013-11-13 00:00:00', '2013-11-13 00:00:00', 'y'),
(11, '2013-09-30 00:00:00', '2013-10-02 00:00:00', 'y');

-- --------------------------------------------------------

--
-- Table structure for table `level_tbl`
--

CREATE TABLE IF NOT EXISTS `level_tbl` (
  `levelid` int(11) NOT NULL AUTO_INCREMENT,
  `levelname` varchar(30) NOT NULL,
  PRIMARY KEY (`levelid`),
  UNIQUE KEY `levelname` (`levelname`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `level_tbl`
--

INSERT INTO `level_tbl` (`levelid`, `levelname`) VALUES
(3, 'DSP'),
(2, 'PD'),
(1, 'RD'),
(4, 'Retailer');

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE IF NOT EXISTS `modules` (
  `moduleid` int(11) NOT NULL AUTO_INCREMENT,
  `modulename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`moduleid`),
  UNIQUE KEY `modulename` (`modulename`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`moduleid`, `modulename`) VALUES
(6, 'Credit Limit Management'),
(4, 'Emergency Load management'),
(5, 'Network Management'),
(3, 'privileges management'),
(2, 'roles management'),
(1, 'user management');

-- --------------------------------------------------------

--
-- Table structure for table `opensim_balance_history`
--

CREATE TABLE IF NOT EXISTS `opensim_balance_history` (
  `balance_date` datetime DEFAULT NULL,
  `opensim` varchar(20) DEFAULT NULL,
  `balance` float(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `opensim_balance_history`
--

INSERT INTO `opensim_balance_history` (`balance_date`, `opensim`, `balance`) VALUES
('2013-09-30 12:35:31', '09178558927', 500.00),
('2013-09-30 18:35:31', '09152746780', 200.00),
('2013-09-30 15:17:52', '09276677556', 150.00),
('2013-09-30 15:44:01', '09275448875', 50.00),
('2013-09-30 15:44:05', '09162234124', 70.00),
('2013-09-30 15:44:09', '09062771282', 230.00),
('2013-09-30 15:44:11', '09062543675', 90.00),
('2013-09-30 15:44:11', '09273445234', 20.00),
('2013-09-30 15:46:37', '09172456743', 80.00),
('2013-09-30 15:46:40', '09172456445', 44.00);

-- --------------------------------------------------------

--
-- Table structure for table `opensim_tbl`
--

CREATE TABLE IF NOT EXISTS `opensim_tbl` (
  `opensim` varchar(50) NOT NULL,
  `modemname` varchar(50) NOT NULL,
  `appname` varchar(50) NOT NULL,
  `pin` varchar(50) NOT NULL,
  UNIQUE KEY `modemname` (`modemname`),
  UNIQUE KEY `opensim` (`opensim`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `opensim_tbl`
--

INSERT INTO `opensim_tbl` (`opensim`, `modemname`, `appname`, `pin`) VALUES
('09178558927', 'Test', 'load', '1234'),
('09172456445', 'Test10', '', ''),
('09152746780', 'Test2', '', ''),
('09276677556', 'Test3', '', ''),
('09275448875', 'Test4', '', ''),
('09162234124', 'Test5', '', ''),
('09062771282', 'Test6', '', ''),
('09062543675', 'Test7', '', ''),
('09273445234', 'Test8', '', ''),
('09172456743', 'Test9', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `privileges`
--

CREATE TABLE IF NOT EXISTS `privileges` (
  `privilegeid` int(11) NOT NULL,
  `privilege` varchar(50) DEFAULT NULL,
  `active` varchar(1) DEFAULT 'Y',
  UNIQUE KEY `privilege` (`privilege`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `privileges`
--

INSERT INTO `privileges` (`privilegeid`, `privilege`, `active`) VALUES
(1, 'add', 'Y'),
(2, 'edit', 'Y'),
(3, 'verify', 'Y'),
(4, 'disable', 'Y'),
(5, 'delete', 'Y'),
(6, 'view', 'Y'),
(7, 'search', 'Y'),
(8, 'process transfers', 'Y'),
(9, 'deactivate', 'Y'),
(10, 'update payment status', 'Y'),
(11, 'apply credit limit', 'Y'),
(12, 'apply emergency load', 'Y'),
(13, 'insert payment', 'Y'),
(14, 'access all', 'N');

-- --------------------------------------------------------

--
-- Table structure for table `retailer_sim_transfer_logs`
--

CREATE TABLE IF NOT EXISTS `retailer_sim_transfer_logs` (
  `transctionid` int(11) DEFAULT NULL,
  `partnerid` varchar(25) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `msisdn` varchar(25) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `trace` varchar(25) DEFAULT NULL,
  `transferdate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) DEFAULT NULL,
  `active` varchar(1) DEFAULT 'Y',
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `rolename` (`rolename`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`roleid`, `rolename`, `active`) VALUES
(1, 'Super Administrator', 'Y'),
(2, 'RD Managers', 'Y'),
(3, 'RD Operations', 'Y'),
(4, 'PD Managers', 'Y'),
(5, 'PD Operations', 'Y'),
(6, 'DSP Operations', 'Y'),
(7, 'Retailer Operations', 'Y'),
(8, 'Super', 'Y'),
(9, 'test', 'Y'),
(10, 'test2', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `role_level_permissions`
--

CREATE TABLE IF NOT EXISTS `role_level_permissions` (
  `levelid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  UNIQUE KEY `levelid` (`levelid`,`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_level_permissions`
--

INSERT INTO `role_level_permissions` (`levelid`, `roleid`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 8),
(2, 4),
(2, 5),
(3, 6),
(4, 7);

-- --------------------------------------------------------

--
-- Table structure for table `role_privileges`
--

CREATE TABLE IF NOT EXISTS `role_privileges` (
  `roleid` int(11) DEFAULT NULL,
  `moduleid` int(11) DEFAULT NULL,
  `privilege_mode` varchar(50) DEFAULT NULL,
  `levelid` int(11) DEFAULT NULL,
  UNIQUE KEY `roleid` (`roleid`,`moduleid`,`levelid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_privileges`
--

INSERT INTO `role_privileges` (`roleid`, `moduleid`, `privilege_mode`, `levelid`) VALUES
(1, 1, '11110000000001', 1),
(1, 2, '11110000000001', 1),
(1, 3, '11110000000001', 1),
(2, 4, '11110000000001', 1),
(2, 5, '11110000000001', 1),
(3, 4, '11110000000001', 1),
(4, 4, '11110000000001', 2),
(4, 5, '11110000000001', 2),
(5, 4, '11110000000001', 2),
(6, 4, '11110000000001', 3),
(7, 4, '11110000000001', 4),
(1, 6, '11110000000001', 1),
(2, 6, '11110000000001', 2);

-- --------------------------------------------------------

--
-- Table structure for table `settings_tbl`
--

CREATE TABLE IF NOT EXISTS `settings_tbl` (
  `setting_name` varchar(50) NOT NULL,
  `setting_value` varchar(50) NOT NULL,
  UNIQUE KEY `setting_name` (`setting_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `settings_tbl`
--

INSERT INTO `settings_tbl` (`setting_name`, `setting_value`) VALUES
('close_begintime', '18:00'),
('close_endtime', '06:00'),
('outgoing_modem', 'modemout');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `status` enum('inactive','active') DEFAULT NULL,
  `email_address` varchar(30) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `roleid` int(11) DEFAULT NULL,
  `CompanyID` int(11) DEFAULT NULL,
  `createdby` varchar(30) DEFAULT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email_address` (`email_address`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `name`, `username`, `password`, `status`, `email_address`, `mobile`, `roleid`, `CompanyID`, `createdby`, `createddate`) VALUES
(1, 'CWI', 'cwi', 'cwi', 'active', 'admin@payexchangeinc.com', '', 1, 1, '0', '2013-04-11 00:31:34'),
(2, 'RD', 'cwi1', 'cwi1', 'active', 'runjel1@payexchangeinc.com', '', 2, 1, '0', '2013-03-07 01:52:42'),
(3, 'PD', 'arga', 'arga', 'active', 'runjel2@payexchangeinc.com', '', 4, 2, '0', '2013-03-07 01:52:50'),
(4, 'DSP', 'arga-dsp', 'arga-dsp', 'active', 'runjel3@payexchangeinc.com', '', 6, 12, '0', '2013-03-07 01:52:59'),
(5, 'Retailer', 'arga-ret', 'arga-ret', 'active', 'runjel4@payexchangeinc.com', '', 7, 22, '0', '2013-03-07 01:53:08'),
(6, 'CWI', 'runjel', 'runjel', 'active', 'runjel5@payexchangeinc.com', '', 8, 1, '0', '2013-03-07 14:50:59'),
(8, 'Angel', 'Angel', 'Angel', 'active', 'Angel@yahoo.com', '', 4, 3, 'runjel5', '2013-04-12 08:24:50'),
(9, 'haha', 'haha', '123456', 'inactive', 'haha@yahoo.com', '5555555', 5, 3, 'arga', '2013-09-27 01:54:16'),
(11, 'hehe', 'hehe', '123456', 'active', 'hehe@yahoo.com', '1234545677', 7, 23, 'cwi', '2013-09-27 03:07:41');

-- --------------------------------------------------------

--
-- Table structure for table `user_transactions_logs`
--

CREATE TABLE IF NOT EXISTS `user_transactions_logs` (
  `transactionid` int(11) NOT NULL,
  `txdate` datetime DEFAULT NULL,
  `tracenumber` varchar(25) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `mobilenumber` varchar(25) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
