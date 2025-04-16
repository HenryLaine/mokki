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
-- Table structure for table `yritysasiakas`
--

DROP TABLE IF EXISTS `yritysasiakas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yritysasiakas` (
  `asiakasID` int NOT NULL,
  `y_tunnus` varchar(20) DEFAULT NULL,
  `sahkoposti` varchar(100) DEFAULT NULL,
  `osoite` varchar(255) DEFAULT NULL,
  `nimi` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`asiakasID`),
  CONSTRAINT `yritysasiakas_ibfk_1` FOREIGN KEY (`asiakasID`) REFERENCES `asiakas` (`asiakasID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yritysasiakas`
--

LOCK TABLES `yritysasiakas` WRITE;
/*!40000 ALTER TABLE `yritysasiakas` DISABLE KEYS */;
INSERT INTO `yritysasiakas` VALUES (1,'1234567-1','yritys1@example.com','Yrityskatu 1, Helsinki','Yritys 1'),(2,'1234567-2','yritys2@example.com','Yrityskatu 2, Espoo','Yritys 2'),(3,'1234567-3','yritys3@example.com','Yrityskatu 3, Vantaa','Yritys 3'),(4,'1234567-4','yritys4@example.com','Yrityskatu 4, Turku','Yritys 4'),(5,'1234567-5','yritys5@example.com','Yrityskatu 5, Tampere','Yritys 5'),(6,'1234567-6','yritys6@example.com','Yrityskatu 6, Oulu','Yritys 6'),(7,'1234567-7','yritys7@example.com','Yrityskatu 7, Jyväskylä','Yritys 7'),(8,'1234567-8','yritys8@example.com','Yrityskatu 8, Kuopio','Yritys 8'),(9,'1234567-9','yritys9@example.com','Yrityskatu 9, Lahti','Yritys 9'),(10,'1234567-10','yritys10@example.com','Yrityskatu 10, Pori','Yritys 10');
/*!40000 ALTER TABLE `yritysasiakas` ENABLE KEYS */;
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
