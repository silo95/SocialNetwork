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
  `idPost` int(11) NOT NULL AUTO_INCREMENT,
  `strPost` char(50) NOT NULL,
  `person` int(11) NOT NULL,
  `postDate` timestamp NOT NULL,
  PRIMARY KEY (`idPost`),
  KEY `person` (`person`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`person`) REFERENCES `person` (`idPerson`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'Today I\'m going to study',3,'2018-06-12 06:31:38'),(4,'yesterday I was free and I went to work',1,'2018-03-04 23:19:04'),(5,'Two days ago i went to the country',8,'2018-06-27 07:25:42'),(8,'If it doesn\'t rain, I\'ll go to walk on the beach',7,'2018-09-19 01:10:06'),(9,'Tomorrow I\'ll go to walk on the beach',5,'2018-11-12 06:05:12'),(10,'In three days i will go to the restaurant',4,'2018-02-17 07:25:37'),(11,'Today I\'m going to the restaurant',3,'2018-04-17 20:27:48'),(12,'In two days i will go to walk in the mountains',9,'2018-07-16 07:04:34'),(14,'Today I\'m going to swim in the sea',2,'2018-03-22 22:21:05'),(15,'Tomorrow I\'ll go to the country',7,'2018-04-11 02:10:12'),(16,'yesterday I was free and I went to the country',6,'2018-02-01 03:19:36'),(17,'In two days i will go to walk in the mountains',5,'2018-03-03 04:31:49'),(18,'Three days age i went to work',1,'2018-10-05 23:35:10'),(20,'Today I\'m going to work',9,'2018-11-23 09:32:34'),(21,'Tomorrow I\'ll go to swim in the pool',8,'2018-07-14 00:27:48'),(22,'In three days i will go to swim in the sea',8,'2018-11-05 04:25:05'),(23,'Today I\'m going to study',8,'2018-12-11 06:23:15'),(24,'Today I\'m going to visit my parents',9,'2018-05-02 21:34:23'),(25,'In three days i will go to the country',9,'2018-01-13 08:29:21'),(26,'If it doesn\'t rain, I\'ll go to the country',2,'2018-11-07 22:23:33'),(27,'If it doesn\'t rain, I\'ll go to swim in the sea',7,'2018-02-05 07:31:45'),(28,'Two days ago i went to study',9,'2018-05-14 05:12:41'),(29,'If I\'m free tomorrow, I\'ll go to swim in the pool',9,'2018-03-13 03:09:35'),(30,'if tomorrow is a good day to swim in the sea',2,'2018-02-20 00:11:40'),(31,'Two days ago i went to study',7,'2018-12-05 04:20:46'),(32,'Two days ago i went to visit my parents',8,'2018-07-18 20:21:20'),(33,'Three days age i went to study',8,'2018-07-24 22:27:23'),(34,'Yesterday I went to the restaurant',6,'2018-10-01 03:32:39'),(35,'In three days i will go to visit my parents',3,'2018-06-02 05:24:41'),(36,'If it doesn\'t rain, I\'ll go to work',6,'2018-03-01 08:06:09'),(37,'Three days age i went to work',6,'2018-02-22 22:11:25'),(38,'Two days ago i went to the restaurant',5,'2018-10-12 21:01:29'),(39,'If I\'m free tomorrow, I\'ll go to swim in the sea',3,'2018-12-11 09:12:26'),(41,'Two days ago i went to the restaurant',2,'2018-02-24 05:09:01'),(42,'If it doesn\'t rain, I\'ll go to walk on the beach',2,'2018-08-24 20:31:20'),(43,'Three days age i went to walk in the mountains',6,'2018-02-11 04:28:05'),(44,'if tomorrow is a good day to study',2,'2018-03-13 09:11:36'),(45,'Three days age i went to swim in the sea',5,'2018-02-08 06:13:09'),(46,'If I\'m free tomorrow, I\'ll go to the restaurant',4,'2018-02-19 04:05:45'),(47,'if tomorrow is a good day to work',4,'2018-10-07 03:19:06'),(48,'In two days i will go to walk on the beach',1,'2018-12-13 06:05:24'),(50,'If it doesn\'t rain, I\'ll go to visit my parents',6,'2018-03-27 05:06:01'),(51,'if tomorrow is a good day to work',9,'2018-01-20 06:02:01'),(52,'If it doesn\'t rain, I\'ll go to swim in the sea',3,'2018-10-19 02:16:11'),(53,'Two days ago i went to visit my parents',9,'2018-02-06 06:24:06'),(54,'Three days age i went to the country',8,'2018-07-22 01:33:03'),(55,'If I\'m free tomorrow, I\'ll go to swim in the sea',6,'2018-07-26 23:22:03'),(56,'In three days i will go to walk in the mountains',1,'2018-10-01 06:08:03'),(58,'Two days ago i went to swim in the pool',6,'2018-04-10 07:07:46'),(59,'If it doesn\'t rain, I\'ll go to the country',2,'2018-04-07 04:01:47'),(60,'Yesterday I went to study',6,'2018-03-26 23:05:50'),(61,'Two days ago i went to visit my parents',3,'2018-07-11 01:22:07'),(63,'In three days i will go to work',6,'2018-04-15 21:13:38'),(64,'Yesterday I went to swim in the pool',4,'2018-07-21 03:21:30'),(65,'Tomorrow I\'ll go to walk in the mountains',9,'2018-11-12 09:26:45'),(66,'yesterday I was free and I went to the country',4,'2018-06-01 20:27:07'),(67,'If it doesn\'t rain, I\'ll go to visit my parents',3,'2018-03-05 22:35:50'),(68,'If it doesn\'t rain, I\'ll go to study',1,'2018-09-21 05:32:29'),(69,'In two days i will go to walk in the mountains',7,'2018-11-06 00:06:11'),(70,'In two days i will go to study',9,'2018-05-27 07:13:29'),(71,'Two days ago i went to the restaurant',7,'2018-08-13 00:12:17'),(72,'If I\'m free tomorrow, I\'ll go to study',3,'2018-05-15 01:24:37'),(73,'Two days ago i went to walk in the mountains',7,'2018-09-07 20:31:47'),(74,'If I\'m free tomorrow, I\'ll go to the restaurant',9,'2018-09-15 01:23:49'),(76,'if tomorrow is a good day to the restaurant',7,'2018-04-03 00:02:38'),(77,'If I\'m free tomorrow, I\'ll go to the restaurant',2,'2018-07-08 00:21:44'),(78,'Yesterday I went to the restaurant',9,'2018-12-05 22:13:25'),(79,'yesterday I was free and I went to work',4,'2018-06-07 21:09:43'),(80,'Three days age i went to study',7,'2018-07-16 02:30:34'),(81,'In two days i will go to study',7,'2018-06-03 22:14:12'),(82,'if tomorrow is a good day to study',6,'2018-02-18 07:14:19'),(84,'In three days i will go to swim in the pool',8,'2018-10-14 07:32:45'),(85,'If it doesn\'t rain, I\'ll go to walk on the beach',5,'2018-03-18 03:11:18'),(86,'Yesterday I went to walk in the mountains',6,'2018-11-27 01:18:44'),(87,'Today I\'m going to swim in the pool',7,'2018-10-10 07:11:08'),(88,'If I\'m free tomorrow, I\'ll go to visit my parents',3,'2018-02-19 23:33:07'),(89,'Tomorrow I\'ll go to walk on the beach',5,'2018-04-06 21:08:50'),(90,'Yesterday I went to swim in the sea',6,'2018-06-19 07:09:40'),(91,'If it doesn\'t rain, I\'ll go to visit my parents',7,'2018-07-23 00:28:34'),(92,'Yesterday I went to work',1,'2018-04-24 03:06:34'),(93,'Yesterday I went to walk on the beach',4,'2018-07-07 21:03:32'),(94,'if tomorrow is a good day to swim in the pool',3,'2018-03-07 09:19:33'),(95,'Yesterday I went to the country',1,'2018-04-27 04:35:36'),(96,'In two days i will go to the country',1,'2018-05-23 00:28:17'),(97,'Tomorrow I\'ll go to walk in the mountains',4,'2018-03-15 04:10:37'),(98,'If I\'m free tomorrow, I\'ll go to swim in the pool',1,'2018-01-10 06:06:39'),(99,'Three days age i went to swim in the pool',4,'2018-08-22 06:12:39');
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

-- Dump completed on 2019-10-22 16:54:27
