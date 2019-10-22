-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: socialnetwork
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `IdPost` int(11) NOT NULL AUTO_INCREMENT,
  `strPost` char(50) NOT NULL,
  `User` int(11) NOT NULL,
  `Date` timestamp NOT NULL,
  PRIMARY KEY (`IdPost`),
  KEY `User` (`User`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`User`) REFERENCES `user` (`IdUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (2,'Two days ago i went to swim in the pool',7,'2018-03-15 02:08:11'),(5,'Three days age i went to swim in the pool',6,'2018-11-25 09:17:22'),(6,'Three days age i went to study',8,'2018-04-16 06:29:40'),(7,'Today I\'m going to the restaurant',6,'2018-07-03 20:28:15'),(10,'yesterday I was free and I went to the restaurant',1,'2018-07-10 04:24:13'),(11,'Two days ago i went to swim in the sea',1,'2018-07-19 01:03:22'),(12,'In three days i will go to visit my parents',5,'2018-03-11 23:28:18'),(13,'Today I\'m going to study',2,'2018-10-21 21:23:29'),(14,'yesterday I was free and I went to the country',9,'2018-03-13 09:26:13'),(15,'Yesterday I went to study',2,'2018-06-25 23:16:25'),(16,'Today I\'m going to swim in the pool',5,'2018-08-25 04:12:06'),(17,'Three days age i went to study',7,'2018-09-25 06:02:20'),(18,'Yesterday I went to the restaurant',8,'2018-04-09 23:24:47'),(19,'If it doesn\'t rain, I\'ll go to the country',6,'2018-05-24 01:06:03'),(20,'In three days i will go to study',2,'2018-07-21 05:28:48'),(21,'If it doesn\'t rain, I\'ll go to swim in the pool',8,'2018-07-05 00:01:01'),(22,'if tomorrow is a good day to swim in the sea',2,'2018-10-16 22:05:30'),(24,'if tomorrow is a good day to walk in the mountains',2,'2018-03-26 21:33:36'),(25,'In three days i will go to walk on the beach',6,'2018-06-20 21:26:50'),(26,'Today I\'m going to walk on the beach',9,'2018-02-17 09:07:19'),(27,'In three days i will go to walk in the mountains',1,'2018-05-01 05:18:01'),(28,'Yesterday I went to walk on the beach',2,'2018-09-10 00:09:37'),(29,'In two days i will go to work',9,'2018-10-05 05:30:44'),(30,'Today I\'m going to swim in the sea',3,'2018-10-20 21:30:36'),(32,'Two days ago i went to work',6,'2018-09-08 04:22:13'),(33,'Today I\'m going to walk in the mountains',8,'2018-11-05 04:11:44'),(34,'Today I\'m going to the restaurant',8,'2018-07-21 06:06:40'),(35,'Three days age i went to study',8,'2018-05-22 03:19:38'),(36,'In two days i will go to study',3,'2018-08-07 01:23:35'),(38,'If it doesn\'t rain, I\'ll go to swim in the sea',3,'2018-12-11 05:13:10'),(39,'In two days i will go to study',4,'2018-03-17 05:24:38'),(41,'Three days age i went to the restaurant',7,'2018-03-10 08:27:02'),(43,'yesterday I was free and I went to study',1,'2018-11-07 01:14:02'),(44,'If I\'m free tomorrow, I\'ll go to swim in the sea',3,'2018-11-23 03:16:07'),(45,'Today I\'m going to walk on the beach',7,'2018-09-23 20:31:23'),(46,'Two days ago i went to work',8,'2018-02-24 04:12:24'),(47,'if tomorrow is a good day to the country',4,'2018-11-23 04:23:09'),(48,'If I\'m free tomorrow, I\'ll go to the restaurant',8,'2018-01-14 22:05:34'),(49,'Tomorrow I\'ll go to work',2,'2018-09-01 06:28:13'),(50,'Three days age i went to study',6,'2018-07-25 04:01:17'),(51,'if tomorrow is a good day to swim in the sea',9,'2018-09-04 05:01:25'),(53,'Two days ago i went to the restaurant',3,'2018-06-04 20:24:07'),(54,'Three days age i went to swim in the sea',9,'2018-06-22 20:18:21'),(55,'Two days ago i went to visit my parents',8,'2018-10-16 03:10:06'),(56,'Three days age i went to walk in the mountains',9,'2018-02-02 02:06:49'),(57,'In three days i will go to swim in the sea',3,'2018-03-26 00:16:50'),(58,'If it doesn\'t rain, I\'ll go to swim in the sea',6,'2018-02-06 23:26:05'),(59,'Today I\'m going to walk on the beach',4,'2018-02-03 08:16:07'),(60,'If I\'m free tomorrow, I\'ll go to the restaurant',3,'2018-02-15 02:23:32'),(61,'In three days i will go to walk on the beach',4,'2018-04-20 22:30:23'),(62,'If I\'m free tomorrow, I\'ll go to swim in the sea',3,'2018-03-27 04:11:30'),(63,'Yesterday I went to the restaurant',1,'2018-05-03 23:35:50'),(64,'Two days ago i went to visit my parents',9,'2018-09-21 07:23:49'),(65,'If it doesn\'t rain, I\'ll go to the country',2,'2018-04-07 22:34:37'),(66,'Today I\'m going to visit my parents',1,'2018-10-26 02:13:03'),(67,'Tomorrow I\'ll go to study',2,'2018-10-13 05:05:31'),(68,'Three days age i went to the restaurant',4,'2018-10-10 21:23:49'),(69,'In two days i will go to the restaurant',9,'2018-09-18 02:22:15'),(72,'Yesterday I went to swim in the pool',2,'2018-06-23 01:35:30'),(73,'If I\'m free tomorrow, I\'ll go to swim in the pool',7,'2018-05-23 02:16:10'),(75,'Today I\'m going to work',7,'2018-07-15 03:15:37'),(76,'if tomorrow is a good day to walk in the mountains',5,'2018-01-13 02:23:42'),(77,'Today I\'m going to the country',7,'2018-11-25 03:19:31'),(78,'Two days ago i went to work',6,'2018-06-03 03:15:17'),(79,'Yesterday I went to study',1,'2018-01-17 08:16:27'),(80,'If I\'m free tomorrow, I\'ll go to study',5,'2018-10-09 05:11:06'),(81,'Two days ago i went to study',3,'2018-07-25 07:01:41'),(82,'Today I\'m going to work',3,'2018-02-27 03:24:06'),(83,'Three days age i went to swim in the pool',9,'2018-10-22 07:11:04'),(84,'Tomorrow I\'ll go to swim in the pool',2,'2018-07-07 07:14:10'),(85,'if tomorrow is a good day to the restaurant',5,'2018-04-12 05:31:02'),(86,'Today I\'m going to the country',4,'2018-05-26 01:25:40'),(87,'Today I\'m going to the restaurant',7,'2018-02-16 03:03:10'),(88,'In two days i will go to walk in the mountains',9,'2018-09-17 00:05:45'),(89,'Three days age i went to visit my parents',8,'2018-12-03 03:08:22'),(90,'If it doesn\'t rain, I\'ll go to the country',2,'2018-04-26 21:13:02'),(91,'Tomorrow I\'ll go to swim in the sea',9,'2018-07-04 01:31:46'),(92,'In three days i will go to walk on the beach',7,'2018-07-21 00:33:47'),(94,'If it doesn\'t rain, I\'ll go to walk on the beach',4,'2018-07-23 07:24:21'),(96,'Yesterday I went to study',1,'2018-10-21 01:33:05'),(97,'Today I\'m going to study',4,'2018-01-09 05:28:47'),(98,'Today I\'m going to swim in the pool',2,'2018-07-13 02:16:44'),(99,'In two days i will go to work',2,'2018-04-17 05:17:11');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-21 18:20:22
