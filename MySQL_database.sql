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
  `postText` varchar(100) DEFAULT NULL,
  `parentPostID` int DEFAULT NULL,
  `timePosted` datetime DEFAULT NULL,
  `topicID` int DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topicfollower`
--

DROP TABLE IF EXISTS `topicfollower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topicfollower` (
  `topicID` int NOT NULL,
  `followerID` int NOT NULL,
  PRIMARY KEY (`topicID`,`followerID`),
  KEY `FK_topicfollower_followerID_user_userID_idx` (`followerID`),
  CONSTRAINT `FK_topicfollower_followerID_user_userID` FOREIGN KEY (`followerID`) REFERENCES `user` (`userID`),
  CONSTRAINT `FK_topicfollower_topicID_topic_topicID` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `bio` varchar(100) DEFAULT NULL,
  `favBand` varchar(45) DEFAULT NULL,
  `favSong` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userfollower`
--

DROP TABLE IF EXISTS `userfollower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userfollower` (
  `userID` int NOT NULL,
  `followerID` int NOT NULL,
  PRIMARY KEY (`userID`,`followerID`),
  KEY `FK_userfollower_followerID_user_userID_idx` (`followerID`),
  CONSTRAINT `FK_userfollower_followerID_user_userID` FOREIGN KEY (`followerID`) REFERENCES `user` (`userID`),
  CONSTRAINT `FK_userfollower_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-14 20:14:33
