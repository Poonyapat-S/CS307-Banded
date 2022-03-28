CREATE DATABASE  IF NOT EXISTS `cs307group27` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cs307group27`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: cs307group27
-- ------------------------------------------------------
-- Server version	8.0.28

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
  `postID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `postTitle` varchar(255) NOT NULL DEFAULT "Default Title",
  `postText` varchar(255) DEFAULT NULL,
  `parentPostID` int DEFAULT NULL,
  `timePosted` datetime DEFAULT NULL,
  `topicID` int DEFAULT NULL,
  `isAnon` bit(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`postID`),
  KEY `FK_post_userID_user_userID_idx` (`userID`),
  KEY `FK_post_topicID_topic_topicID_idx` (`topicID`),
  KEY `FK_post_postParentID_post_postID_idx` (`parentPostID`),
  CONSTRAINT `FK_post_parentPostID_post_postID` FOREIGN KEY (`parentPostID`) REFERENCES `post` (`postID`),
  CONSTRAINT `FK_post_topicID_topic_topicID` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`),
  CONSTRAINT `FK_post_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reaction`
--

DROP TABLE IF EXISTS `reaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reaction` (
  `reactionID` int NOT NULL AUTO_INCREMENT,
  `postID` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`reactionID`),
  KEY `FK_reaction_userID_user_userID_idx` (`userID`),
  KEY `FK_reaction_postID_post_postID_idx` (`postID`),
  CONSTRAINT `FK_reaction_postID_post_postID` FOREIGN KEY (`postID`) REFERENCES `post` (`postID`),
  CONSTRAINT `FK_reaction_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reaction`
--

LOCK TABLES `reaction` WRITE;
/*!40000 ALTER TABLE `reaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `reaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savedpost`
--

DROP TABLE IF EXISTS `savedpost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savedpost` (
  `postID` int NOT NULL,
  `userID` int NOT NULL,
  KEY `FK_savedpost_userID_user_userID_idx` (`userID`),
  KEY `FK_savedpost_postID_post_postID_idx` (`postID`),
  CONSTRAINT `FK_savedpost_postID_post_postID` FOREIGN KEY (`postID`) REFERENCES `post` (`postID`),
  CONSTRAINT `FK_savedpost_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savedpost`
--

LOCK TABLES `savedpost` WRITE;
/*!40000 ALTER TABLE `savedpost` DISABLE KEYS */;
/*!40000 ALTER TABLE `savedpost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `topicID` int NOT NULL AUTO_INCREMENT,
  `topicName` varchar(45) NOT NULL,
  PRIMARY KEY (`topicID`),
  UNIQUE KEY `topicName_UNIQUE` (`topicName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Rock');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topicfollower`
--

DROP TABLE IF EXISTS `topicfollower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topicfollower` (
  `userID` int NOT NULL,
  `topicID` int NOT NULL,
  KEY `FK_topicfollower_followerID_user_userID_idx` (`userID`),
  KEY `FK_topicfollower_topicID_topic_topicID_idx` (`topicID`),
  CONSTRAINT `FK_topicfollower_followerID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `FK_topicfollower_topicID_topic_topicID` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topicfollower`
--

LOCK TABLES `topicfollower` WRITE;
/*!40000 ALTER TABLE `topicfollower` DISABLE KEYS */;
/*!40000 ALTER TABLE `topicfollower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `bio` varchar(100) DEFAULT NULL,
  `fav_band` varchar(45) DEFAULT NULL,
  `fav_song` varchar(45) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `user_authorities` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'john','password','john@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'person0.75208950147623','password','0.75208950147623@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'person71','password','71@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'person51','password','51@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'person98','password','98@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'person3771','password','3771@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'person2653','password','2653@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'person9322','password','9322@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'person4150','password','4150@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'person7539','password','7539@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'person2318','password','2318@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'person1237','password','1237@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'person7880','password','7880@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'person3719','password','3719@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'person1788','password','1788@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'person7298','password','7298@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'person5319','password','5319@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,'person1606','password','1606@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'otherperson2481','newpassword','2481@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,'otherperson3369','newpassword','3369@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userfollower`
--

DROP TABLE IF EXISTS `userfollower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userfollower` (
  `userID` int NOT NULL,
  `followerID` int NOT NULL,
  KEY `FK_userID_idx` (`userID`),
  KEY `FK_followerID_idx` (`followerID`),
  CONSTRAINT `FK_userfollower_followerID_user_userID` FOREIGN KEY (`followerID`) REFERENCES `user` (`userID`),
  CONSTRAINT `FK_userfollower_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfollower`
--

LOCK TABLES `userfollower` WRITE;
/*!40000 ALTER TABLE `userfollower` DISABLE KEYS */;
/*!40000 ALTER TABLE `userfollower` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-22 17:47:03
