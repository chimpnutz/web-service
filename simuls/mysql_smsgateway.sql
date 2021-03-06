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
-- Database: `mysql_smsgateway`
--
CREATE DATABASE IF NOT EXISTS `mysql_smsgateway` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `mysql_smsgateway`;

-- --------------------------------------------------------

--
-- Table structure for table `smsreceived`
--

CREATE TABLE IF NOT EXISTS `smsreceived` (
  `SMSID` int(11) NOT NULL AUTO_INCREMENT,
  `KEYWORD` varchar(160) DEFAULT NULL,
  `SMSDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SMSGID` varchar(6) DEFAULT NULL,
  `SENDER` varchar(20) DEFAULT NULL,
  `RECIPIENT` varchar(20) DEFAULT NULL,
  `SMSDATA` varchar(160) DEFAULT NULL,
  `SCTS` varchar(20) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  PRIMARY KEY (`SMSID`),
  KEY `SMRE_SMDA_IDX` (`SMSDATE`),
  KEY `SMRE_KEYW_IDX` (`KEYWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `smsreceived_history`
--

CREATE TABLE IF NOT EXISTS `smsreceived_history` (
  `SMSID` int(11) NOT NULL,
  `KEYWORD` varchar(160) DEFAULT NULL,
  `SMSDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SMSGID` varchar(6) DEFAULT NULL,
  `SENDER` varchar(20) DEFAULT NULL,
  `RECIPIENT` varchar(20) DEFAULT NULL,
  `SMSDATA` varchar(160) DEFAULT NULL,
  `SCTS` varchar(20) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  KEY `SMREH_SMDA_IDX` (`SMSDATE`),
  KEY `SMREH_KEYW_IDX` (`KEYWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
/*!50100 PARTITION BY RANGE ( unix_timestamp(smsdate))
(PARTITION wcl0 VALUES LESS THAN (1356969600) ENGINE = InnoDB,
 PARTITION wcl1 VALUES LESS THAN (1359648000) ENGINE = InnoDB,
 PARTITION wcl2 VALUES LESS THAN (1362067200) ENGINE = InnoDB,
 PARTITION wcl3 VALUES LESS THAN (1364745600) ENGINE = InnoDB,
 PARTITION wcl4 VALUES LESS THAN (1367337600) ENGINE = InnoDB,
 PARTITION wcl5 VALUES LESS THAN (1370016000) ENGINE = InnoDB,
 PARTITION wcl6 VALUES LESS THAN (1372608000) ENGINE = InnoDB,
 PARTITION wcl7 VALUES LESS THAN (1375286400) ENGINE = InnoDB,
 PARTITION wcl8 VALUES LESS THAN (1377964800) ENGINE = InnoDB,
 PARTITION wcl9 VALUES LESS THAN (1380556800) ENGINE = InnoDB,
 PARTITION wcl10 VALUES LESS THAN (1383235200) ENGINE = InnoDB,
 PARTITION wcl11 VALUES LESS THAN (1385827200) ENGINE = InnoDB,
 PARTITION wcl12 VALUES LESS THAN (1388505600) ENGINE = InnoDB,
 PARTITION wcl201401 VALUES LESS THAN (1391184000) ENGINE = InnoDB,
 PARTITION wcl201402 VALUES LESS THAN (1393603200) ENGINE = InnoDB,
 PARTITION wcl201403 VALUES LESS THAN (1396281600) ENGINE = InnoDB,
 PARTITION wcl201404 VALUES LESS THAN (1398873600) ENGINE = InnoDB,
 PARTITION wcl201405 VALUES LESS THAN (1401552000) ENGINE = InnoDB) */;

-- --------------------------------------------------------

--
-- Table structure for table `smssent`
--

CREATE TABLE IF NOT EXISTS `smssent` (
  `SMSID` int(11) NOT NULL AUTO_INCREMENT,
  `SMSDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SMSGID` varchar(6) DEFAULT NULL,
  `SENDER` varchar(20) DEFAULT NULL,
  `RECIPIENT` varchar(20) DEFAULT NULL,
  `SMSDATA` varchar(480) DEFAULT NULL,
  `TARIFF_CLASS` varchar(40) DEFAULT NULL,
  `NADC` varchar(20) DEFAULT NULL,
  `NT` varchar(1) DEFAULT NULL,
  `NPID` varchar(4) DEFAULT NULL,
  `SMSREF` int(11) DEFAULT NULL,
  `SERVICE_TYPE` varchar(20) DEFAULT NULL,
  `SCTS` varchar(12) DEFAULT NULL,
  `DCTS` varchar(12) DEFAULT NULL,
  `RSN` varchar(3) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `CONTENT_TYPE` varchar(10) DEFAULT NULL,
  `OTHER_DATA` varchar(4000) DEFAULT NULL,
  `LASTUPDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`SMSID`),
  KEY `SMSE_STAT_IDX` (`STATUS`),
  KEY `SMSE_RECIPIENT_IDX` (`SMSDATE`),
  KEY `SMSE_SMDA_IDX` (`SMSDATE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `smssent`
--

INSERT INTO `smssent` (`SMSID`, `SMSDATE`, `SMSGID`, `SENDER`, `RECIPIENT`, `SMSDATA`, `TARIFF_CLASS`, `NADC`, `NT`, `NPID`, `SMSREF`, `SERVICE_TYPE`, `SCTS`, `DCTS`, `RSN`, `STATUS`, `CONTENT_TYPE`, `OTHER_DATA`, `LASTUPDATE`) VALUES
(1, '2013-11-05 01:44:08', '1', 'haha', 'haha', 'haha', 'haha', NULL, NULL, NULL, NULL, 'haha', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(2, '2013-11-05 06:10:35', '', '2808', '09276598742', 'this is a message', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(3, '2013-11-05 07:37:03', '', '2808', '09276598742', 'this is a message', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(4, '2013-11-06 03:12:08', '', '2808', '09276598742', 'this is a message', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(5, '2013-11-06 03:42:02', '', '2808', '09276598742', 'this is a message', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(6, '2013-11-06 05:20:44', '', '2808', '09276598742', 'this is a message', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(7, '2013-11-06 05:22:06', '', '2808', '09276598742', 'this is a message', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(8, '2013-11-06 09:27:47', '', '2808', '09276598742', '4111240668', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(9, '2013-11-06 09:57:10', '', '2808', '09276598742', '', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(10, '2013-11-06 10:03:49', '', '2808', '09276598742', '     9120175251     761379     100     4111240668', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00'),
(11, '2013-11-07 03:02:35', '', '2808', '09276598742', '     9120175251     761379     100     4111240668', '', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 0, NULL, NULL, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `smssent_history`
--

CREATE TABLE IF NOT EXISTS `smssent_history` (
  `SMSID` int(11) NOT NULL,
  `SMSDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `SMSGID` varchar(6) DEFAULT NULL,
  `SENDER` varchar(20) DEFAULT NULL,
  `RECIPIENT` varchar(20) DEFAULT NULL,
  `SMSDATA` varchar(480) DEFAULT NULL,
  `TARIFF_CLASS` varchar(40) DEFAULT NULL,
  `NADC` varchar(20) DEFAULT NULL,
  `NT` varchar(1) DEFAULT NULL,
  `NPID` varchar(4) DEFAULT NULL,
  `SMSREF` int(11) DEFAULT NULL,
  `SERVICE_TYPE` varchar(20) DEFAULT NULL,
  `SCTS` varchar(12) DEFAULT NULL,
  `DCTS` varchar(12) DEFAULT NULL,
  `RSN` varchar(3) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `CONTENT_TYPE` varchar(10) DEFAULT NULL,
  `OTHER_DATA` varchar(4000) DEFAULT NULL,
  `LASTUPDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `SMSEH_STAT_IDX` (`STATUS`),
  KEY `SMSEH_RECIPIENT_IDX` (`SMSDATE`),
  KEY `SMSEH_SMDA_IDX` (`SMSDATE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
/*!50100 PARTITION BY RANGE ( unix_timestamp(smsdate))
(PARTITION th0 VALUES LESS THAN (1356969600) ENGINE = InnoDB,
 PARTITION th1 VALUES LESS THAN (1359648000) ENGINE = InnoDB,
 PARTITION th2 VALUES LESS THAN (1362067200) ENGINE = InnoDB,
 PARTITION th3 VALUES LESS THAN (1364745600) ENGINE = InnoDB,
 PARTITION th4 VALUES LESS THAN (1367337600) ENGINE = InnoDB,
 PARTITION th5 VALUES LESS THAN (1370016000) ENGINE = InnoDB,
 PARTITION th6 VALUES LESS THAN (1372608000) ENGINE = InnoDB,
 PARTITION th7 VALUES LESS THAN (1375286400) ENGINE = InnoDB,
 PARTITION th8 VALUES LESS THAN (1377964800) ENGINE = InnoDB,
 PARTITION th9 VALUES LESS THAN (1380556800) ENGINE = InnoDB,
 PARTITION th10 VALUES LESS THAN (1383235200) ENGINE = InnoDB,
 PARTITION th11 VALUES LESS THAN (1385827200) ENGINE = InnoDB,
 PARTITION th12 VALUES LESS THAN (1388505600) ENGINE = InnoDB,
 PARTITION th201401 VALUES LESS THAN (1391184000) ENGINE = InnoDB,
 PARTITION th201402 VALUES LESS THAN (1393603200) ENGINE = InnoDB,
 PARTITION th201403 VALUES LESS THAN (1396281600) ENGINE = InnoDB,
 PARTITION th201404 VALUES LESS THAN (1398873600) ENGINE = InnoDB,
 PARTITION th201405 VALUES LESS THAN (1401552000) ENGINE = InnoDB) */;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `USERID` varchar(20) NOT NULL,
  `USERPW` varchar(128) NOT NULL,
  `USERSTATUS` int(1) NOT NULL,
  `USER_FIRSTNAME` varchar(20) NOT NULL,
  `USER_LASTNAME` varchar(20) NOT NULL,
  `EXPIRATION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `USER_MOBILENO` varchar(11) DEFAULT NULL,
  `USER_EMAIL` varchar(100) DEFAULT NULL,
  `PW_CHANGE` int(1) DEFAULT NULL,
  `IS_LOGGED_ON` int(1) DEFAULT NULL,
  `LAST_LOGGED_ON` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_LOGGED_OFF` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_TERMINAL` varchar(20) DEFAULT NULL,
  `PASSWORD_HISTORY` varchar(128) DEFAULT NULL,
  UNIQUE KEY `USERID` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_memberships`
--

CREATE TABLE IF NOT EXISTS `user_memberships` (
  `USERID` varchar(20) NOT NULL,
  `GRP_ID` int(3) NOT NULL,
  UNIQUE KEY `GRP_ID` (`GRP_ID`,`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
