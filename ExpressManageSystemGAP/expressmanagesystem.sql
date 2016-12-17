-- MySQL dump 10.13  Distrib 5.7.14, for osx10.11 (x86_64)
--
-- Host: localhost    Database: expressmanagesystem
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `name` varchar(45) NOT NULL,
  `balance` double DEFAULT NULL,
  `income` double DEFAULT NULL,
  `pay` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arrivedorder`
--

DROP TABLE IF EXISTS `arrivedorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrivedorder` (
  `order_id` varchar(20) DEFAULT NULL,
  `des_ins_id` varchar(20) DEFAULT NULL,
  `from_ins_id` varchar(20) DEFAULT NULL,
  `comment` varchar(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL,
  `load_order_id` varchar(20) DEFAULT NULL,
  `is_stockin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrivedorder`
--

LOCK TABLES `arrivedorder` WRITE;
/*!40000 ALTER TABLE `arrivedorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `arrivedorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arrivedorderitem`
--

DROP TABLE IF EXISTS `arrivedorderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrivedorderitem` (
  `expressorder_id` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `arrivedstate` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arrivedorderitem`
--

LOCK TABLES `arrivedorderitem` WRITE;
/*!40000 ALTER TABLE `arrivedorderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `arrivedorderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billorder`
--

DROP TABLE IF EXISTS `billorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billorder` (
  `order_id` varchar(20) DEFAULT NULL,
  `total_money` double DEFAULT NULL,
  `time` date DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billorder`
--

LOCK TABLES `billorder` WRITE;
/*!40000 ALTER TABLE `billorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `billorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billorderitem`
--

DROP TABLE IF EXISTS `billorderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billorderitem` (
  `delivery_id` varchar(20) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `order_id` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billorderitem`
--

LOCK TABLES `billorderitem` WRITE;
/*!40000 ALTER TABLE `billorderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `billorderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `car_id` varchar(10) NOT NULL,
  `car_num` varchar(5) DEFAULT NULL,
  `institution_id` varchar(7) DEFAULT NULL,
  `server_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_info`
--

DROP TABLE IF EXISTS `cargo_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_info` (
  `cargo_name` varchar(20) DEFAULT NULL,
  `numbers` int(5) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `volume` float DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_info`
--

LOCK TABLES `cargo_info` WRITE;
/*!40000 ALTER TABLE `cargo_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `province_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliveryorder`
--

DROP TABLE IF EXISTS `deliveryorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliveryorder` (
  `order_id` varchar(20) DEFAULT NULL,
  `comment` text,
  `time` date DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliveryorder`
--

LOCK TABLES `deliveryorder` WRITE;
/*!40000 ALTER TABLE `deliveryorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliveryorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliveryorderitem`
--

DROP TABLE IF EXISTS `deliveryorderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliveryorderitem` (
  `expressorder_id` varchar(20) DEFAULT NULL,
  `order_id` varchar(20) DEFAULT NULL,
  `delivery_id` varchar(20) DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliveryorderitem`
--

LOCK TABLES `deliveryorderitem` WRITE;
/*!40000 ALTER TABLE `deliveryorderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliveryorderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliverytime`
--

DROP TABLE IF EXISTS `deliverytime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliverytime` (
  `departure_city_id` varchar(255) DEFAULT NULL,
  `target_city_id` varchar(255) DEFAULT NULL,
  `cost_time` varchar(255) DEFAULT NULL,
  `order_num` varchar(255) DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliverytime`
--

LOCK TABLES `deliverytime` WRITE;
/*!40000 ALTER TABLE `deliverytime` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliverytime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `id` varchar(10) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `institution` varchar(7) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `driving_license_due` date DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `identity_num` varchar(18) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expressorder`
--

DROP TABLE IF EXISTS `expressorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expressorder` (
  `order_id` varchar(10) DEFAULT NULL,
  `currentIns_id` varchar(7) DEFAULT NULL,
  `targetIns_id` varchar(7) DEFAULT NULL,
  `received` tinyint(1) DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL,
  `isTransferred` tinyint(1) DEFAULT NULL,
  `sender_info` int(10) DEFAULT NULL,
  `receiver_info` int(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `delivery_id` varchar(9) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `order_type` enum('ECONOMIC','STANDARD','EXPRESS') DEFAULT NULL,
  `cargo_info` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expressorder`
--

LOCK TABLES `expressorder` WRITE;
/*!40000 ALTER TABLE `expressorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `expressorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expressorderstate`
--

DROP TABLE IF EXISTS `expressorderstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expressorderstate` (
  `order_id` varchar(10) DEFAULT NULL,
  `state_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expressorderstate`
--

LOCK TABLES `expressorderstate` WRITE;
/*!40000 ALTER TABLE `expressorderstate` DISABLE KEYS */;
/*!40000 ALTER TABLE `expressorderstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `initialaccountinfo`
--

DROP TABLE IF EXISTS `initialaccountinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initialaccountinfo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Balance` int(11) DEFAULT NULL,
  `initialHistory` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `initialaccountinfo`
--

LOCK TABLES `initialaccountinfo` WRITE;
/*!40000 ALTER TABLE `initialaccountinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `initialaccountinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `initialhistory`
--

DROP TABLE IF EXISTS `initialhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initialhistory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `totalBusinessHall` int(10) DEFAULT NULL,
  `totalCenter` int(11) DEFAULT NULL,
  `totalCourier` int(11) DEFAULT NULL,
  `totalBusinessClerk` int(11) DEFAULT NULL,
  `totalCenterClerk` int(11) DEFAULT NULL,
  `totalStockman` int(11) DEFAULT NULL,
  `totalWarehouse` int(11) DEFAULT NULL,
  `totalStock` int(11) DEFAULT NULL,
  `totalAccount` int(11) DEFAULT NULL,
  `totalBalance` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `initialhistory`
--

LOCK TABLES `initialhistory` WRITE;
/*!40000 ALTER TABLE `initialhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `initialhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `initialpeople_info`
--

DROP TABLE IF EXISTS `initialpeople_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initialpeople_info` (
  `ID` int(11) DEFAULT NULL,
  `City` varchar(10) DEFAULT NULL,
  `BusinessHall` int(10) DEFAULT NULL,
  `Center` int(11) DEFAULT NULL,
  `Courier` int(11) DEFAULT NULL,
  `BusinessClerk` int(11) DEFAULT NULL,
  `CenterClerk` int(11) DEFAULT NULL,
  `StockMan` int(11) DEFAULT NULL,
  `initialHistory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `initialpeople_info`
--

LOCK TABLES `initialpeople_info` WRITE;
/*!40000 ALTER TABLE `initialpeople_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `initialpeople_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `initialstockin_info`
--

DROP TABLE IF EXISTS `initialstockin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initialstockin_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `GoodsNumber` int(11) DEFAULT NULL,
  `OccupiedRate` double DEFAULT NULL,
  `initialHistory` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `initialstockin_info`
--

LOCK TABLES `initialstockin_info` WRITE;
/*!40000 ALTER TABLE `initialstockin_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `initialstockin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institution`
--

DROP TABLE IF EXISTS `institution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institution` (
  `ins_id` varchar(7) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `member_num` int(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `ins_type` enum('BUSINESS','CENTER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institution`
--

LOCK TABLES `institution` WRITE;
/*!40000 ALTER TABLE `institution` DISABLE KEYS */;
/*!40000 ALTER TABLE `institution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loadorder`
--

DROP TABLE IF EXISTS `loadorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loadorder` (
  `order_id` varchar(20) DEFAULT NULL,
  `driver_id` varchar(20) DEFAULT NULL,
  `guard_id` varchar(20) DEFAULT NULL,
  `car_num` varchar(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL,
  `target_ind_id` varchar(7) DEFAULT NULL,
  `departure_ins_id` varchar(7) DEFAULT NULL,
  `comment` text,
  `isSetArrived` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loadorder`
--

LOCK TABLES `loadorder` WRITE;
/*!40000 ALTER TABLE `loadorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `loadorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loadorderitem`
--

DROP TABLE IF EXISTS `loadorderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loadorderitem` (
  `expressorder_id` varchar(20) DEFAULT NULL,
  `order_id` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loadorderitem`
--

LOCK TABLES `loadorderitem` WRITE;
/*!40000 ALTER TABLE `loadorderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `loadorderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` varchar(9) DEFAULT NULL,
  `operate` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payee`
--

DROP TABLE IF EXISTS `payee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `account` varchar(45) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `type` enum('DELIVERY','BUSSINESSCLERK','CENTERCLERK','INVENTORY','ACCOUNTER','MANAGER','ADMINISTRATOR') DEFAULT NULL,
  `comment` text,
  `payment_list` varchar(20) DEFAULT NULL,
  `express_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payee`
--

LOCK TABLES `payee` WRITE;
/*!40000 ALTER TABLE `payee` DISABLE KEYS */;
/*!40000 ALTER TABLE `payee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentlist`
--

DROP TABLE IF EXISTS `paymentlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentlist` (
  `id` varchar(20) NOT NULL,
  `total` double DEFAULT NULL,
  `payer` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentlist`
--

LOCK TABLES `paymentlist` WRITE;
/*!40000 ALTER TABLE `paymentlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) DEFAULT NULL,
  `express` int(11) DEFAULT NULL,
  `standard` int(11) DEFAULT NULL,
  `economic` int(11) DEFAULT NULL,
  `base` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `province` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `received_info`
--

DROP TABLE IF EXISTS `received_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `received_info` (
  `time` date DEFAULT NULL,
  `order_id` varchar(10) DEFAULT NULL,
  `delivery_id` varchar(10) DEFAULT NULL,
  `receiver_name` varchar(20) DEFAULT NULL,
  `comment` text,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `received_info`
--

LOCK TABLES `received_info` WRITE;
/*!40000 ALTER TABLE `received_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `received_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiver_info`
--

DROP TABLE IF EXISTS `receiver_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receiver_info` (
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `address` int(10) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiver_info`
--

LOCK TABLES `receiver_info` WRITE;
/*!40000 ALTER TABLE `receiver_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `receiver_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rent` (
  `money` double DEFAULT NULL,
  `lastPaid` date DEFAULT NULL,
  `institution_id` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_type` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `ins_id` varchar(20) DEFAULT NULL,
  `alarmValue` double DEFAULT NULL,
  `sector_id` varchar(20) DEFAULT NULL,
  `type` enum('CAR','TRAIN','PLANE') DEFAULT NULL,
  `column_5` int(11) DEFAULT NULL,
  `rows` int(11) DEFAULT NULL,
  `shelves` int(11) DEFAULT NULL,
  `units` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector_flex`
--

DROP TABLE IF EXISTS `sector_flex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector_flex` (
  `sector_id` varchar(20) DEFAULT NULL,
  `begin_shelf` varchar(20) DEFAULT NULL,
  `end_shelf` varchar(20) DEFAULT NULL,
  `belong_sec_id` varchar(20) DEFAULT NULL,
  `used` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector_flex`
--

LOCK TABLES `sector_flex` WRITE;
/*!40000 ALTER TABLE `sector_flex` DISABLE KEYS */;
/*!40000 ALTER TABLE `sector_flex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector_item`
--

DROP TABLE IF EXISTS `sector_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector_item` (
  `location` varchar(20) DEFAULT NULL,
  `sector_id` varchar(20) DEFAULT NULL,
  `expressorder_id` varchar(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `belong_sec` varchar(20) DEFAULT NULL,
  `destination` varchar(20) DEFAULT NULL,
  `existed` tinyint(1) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector_item`
--

LOCK TABLES `sector_item` WRITE;
/*!40000 ALTER TABLE `sector_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sector_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sender_info`
--

DROP TABLE IF EXISTS `sender_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sender_info` (
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `address` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sender_info`
--

LOCK TABLES `sender_info` WRITE;
/*!40000 ALTER TABLE `sender_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `sender_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockinitem`
--

DROP TABLE IF EXISTS `stockinitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockinitem` (
  `expressorder_id` varchar(20) DEFAULT NULL,
  `order_id` varchar(20) DEFAULT NULL,
  `destination` varchar(20) DEFAULT NULL,
  `sector_id` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockinitem`
--

LOCK TABLES `stockinitem` WRITE;
/*!40000 ALTER TABLE `stockinitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockinitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockinorder`
--

DROP TABLE IF EXISTS `stockinorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockinorder` (
  `order_id` varchar(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `ins_id` varchar(20) DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockinorder`
--

LOCK TABLES `stockinorder` WRITE;
/*!40000 ALTER TABLE `stockinorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockinorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockoutitem`
--

DROP TABLE IF EXISTS `stockoutitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockoutitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expressorder_id` varchar(20) DEFAULT NULL,
  `order_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockoutitem`
--

LOCK TABLES `stockoutitem` WRITE;
/*!40000 ALTER TABLE `stockoutitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockoutitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockoutorder`
--

DROP TABLE IF EXISTS `stockoutorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockoutorder` (
  `order_id` varchar(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `transport` enum('CAR','PLANE','TRAIN') DEFAULT NULL,
  `target_ins` varchar(20) DEFAULT NULL,
  `ins_id` varchar(20) DEFAULT NULL,
  `passed` tinyint(1) DEFAULT NULL,
  `loaded` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockoutorder`
--

LOCK TABLES `stockoutorder` WRITE;
/*!40000 ALTER TABLE `stockoutorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockoutorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfare`
--

DROP TABLE IF EXISTS `transfare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) DEFAULT NULL,
  `driver_id` varchar(20) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfare`
--

LOCK TABLES `transfare` WRITE;
/*!40000 ALTER TABLE `transfare` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(9) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `institution` varchar(7) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `user_type` enum('DELIVERY','BUSSINESSCLERK','CENTERCLERK','INVENTORY','ACCOUNTER','MANAGER','ADMINISTRATOR') DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `last_paid` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-17 18:44:56
