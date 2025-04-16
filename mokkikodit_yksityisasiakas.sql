-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mokkikodit
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `yksityisasiakas`
--

DROP TABLE IF EXISTS `yksityisasiakas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yksityisasiakas` (
  `asiakasID` int NOT NULL,
  `nimi` varchar(100) DEFAULT NULL,
  `sahkoposti` varchar(100) DEFAULT NULL,
  `puhelinnumero` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`asiakasID`),
  CONSTRAINT `yksityisasiakas_ibfk_1` FOREIGN KEY (`asiakasID`) REFERENCES `asiakas` (`asiakasID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yksityisasiakas`
--

LOCK TABLES `yksityisasiakas` WRITE;
/*!40000 ALTER TABLE `yksityisasiakas` DISABLE KEYS */;
INSERT INTO `yksityisasiakas` VALUES (11,'Matti Meikäläinen','matti@example.com','0401234567'),(12,'Maija Virtanen','maasiakasasiakasIDasiakastyyppiija@example.com','0507654321'),(13,'Kalle Korhonen','kalle@example.com','0412345678'),(14,'Laura Lahtinen','laura@example.com','0459876543'),(15,'Antti Nieminen','antti@example.com','0461231234'),(16,'Elina Salonen','elina@example.com','0408765432'),(17,'Jari Heikkinen','jari@example.com','0506543210'),(18,'Tiina Lehtinen','tiina@example.com','0443214567'),(19,'Pekka Mäkelä','pekka@example.com','0432109876'),(20,'Sari Aalto','sari@example.com','0499999999');
/*!40000 ALTER TABLE `yksityisasiakas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-16 17:19:09
