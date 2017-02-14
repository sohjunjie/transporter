CREATE DATABASE  IF NOT EXISTS `transporter` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `transporter`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: transporter
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `accident_cause`
--

DROP TABLE IF EXISTS `accident_cause`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accident_cause` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accident_cause`
--

LOCK TABLES `accident_cause` WRITE;
/*!40000 ALTER TABLE `accident_cause` DISABLE KEYS */;
INSERT INTO `accident_cause` VALUES (1,'Carrying Out Work on the Road without Proper Attire or Sufficient Warning Signs'),(2,'Causes Attributed to Road Conditions'),(3,'Causes Attributed to Vehicles'),(4,'Changing Lane without Due Care'),(5,'Crossing Heedless of Traffic'),(6,'Crossing In Front or Behind a Vehicle which Obstructs View'),(7,'Disobeying Traffic Light Signals Resulting in Accidents with Vehicle'),(8,'Driving under the Influence of Alcohol'),(9,'Failing to Give Way to Traffic with Right of Way'),(10,'Failing to Have Proper Control'),(11,'Failing to Keep a Proper Lookout'),(12,'Failing to Use Available Pedestrian Crossing'),(13,'Following Too Close to Vehicle In Front'),(14,'Other Causes'),(15,'Other causes attributed to drivers, riders or pedal cyclists'),(16,'Other Causes of Accidents Attributed to Pedestrians'),(17,'Overtaking without Due Care'),(18,'Playing on The Road or Carpark'),(19,'Turning Vehicle & Failing to Give Way to Pedestrian During Green Man'),(20,'Turning Without Due Care'),(21,'Under the Influence of Alcohol'),(22,'Under the Influence of Drugs/Intoxicated Substance');
/*!40000 ALTER TABLE `accident_cause` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accident_report`
--

DROP TABLE IF EXISTS `accident_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accident_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accidentDateTime` datetime NOT NULL,
  `numOfCasualties` int(5) DEFAULT NULL,
  `description` varchar(90) DEFAULT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `imageLink` varchar(255) NOT NULL,
  `approvedDateTime` datetime DEFAULT NULL,
  `approvedBy` int(11) DEFAULT NULL,
  `resolvedDateTime` datetime DEFAULT NULL,
  `officialCause` int(11) DEFAULT NULL,
  `resolvedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_accident_report_approvedBy_idx` (`approvedBy`),
  KEY `fk_accident_report_resolvedBy_idx` (`resolvedBy`),
  KEY `fk_accident_report_initialCause_idx` (`description`),
  KEY `fk_accident_report_officialCause_idx` (`officialCause`),
  CONSTRAINT `fk_accident_report_approvedBy` FOREIGN KEY (`approvedBy`) REFERENCES `lta_personnel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accident_report_officialCause` FOREIGN KEY (`officialCause`) REFERENCES `accident_cause` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accident_report_resolvedBy` FOREIGN KEY (`resolvedBy`) REFERENCES `lta_personnel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accident_report`
--

LOCK TABLES `accident_report` WRITE;
/*!40000 ALTER TABLE `accident_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `accident_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(254) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'yhchan','password1','yhchan@lta.gov.sg','Yi Hao','Chan');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS `camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camera` (
  `id` int(11) NOT NULL,
  `dateInstalled` datetime DEFAULT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `type` int(2) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lta_personnel`
--

DROP TABLE IF EXISTS `lta_personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lta_personnel` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `fk_lta_personnel_id` FOREIGN KEY (`id`) REFERENCES `auth_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lta_personnel`
--

LOCK TABLES `lta_personnel` WRITE;
/*!40000 ALTER TABLE `lta_personnel` DISABLE KEYS */;
INSERT INTO `lta_personnel` VALUES (1);
/*!40000 ALTER TABLE `lta_personnel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-12 21:27:46
