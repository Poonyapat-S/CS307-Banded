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
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block` (
  `blockedUserID` int NOT NULL,
  `blockerUserID` int NOT NULL,
  `blockID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`blockID`),
  KEY `FK_block_blockerUserID_user_userID_idx` (`blockerUserID`),
  KEY `FK_block_blockedUserID_user_userID` (`blockedUserID`),
  CONSTRAINT `FK_block_blockedUserID_user_userID` FOREIGN KEY (`blockedUserID`) REFERENCES `user` (`userID`),
  CONSTRAINT `FK_block_blockerUserID_user_userID` FOREIGN KEY (`blockerUserID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dm`
--

DROP TABLE IF EXISTS `dm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dm` (
  `dmID` int NOT NULL AUTO_INCREMENT,
  `senderID` int NOT NULL,
  `recipientID` int NOT NULL,
  `messageText` varchar(255) DEFAULT NULL,
  `timeSent` datetime NOT NULL,
  PRIMARY KEY (`dmID`),
  KEY `FK_dm_recipientID_user_userID_idx` (`recipientID`),
  KEY `FK_dm_senderID_user_userID` (`senderID`),
  CONSTRAINT `FK_dm_recipientID_user_userID` FOREIGN KEY (`recipientID`) REFERENCES `user` (`userID`),
  CONSTRAINT `FK_dm_senderID_user_userID` FOREIGN KEY (`senderID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `postID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `postText` varchar(255) DEFAULT NULL,
  `parentPostID` int DEFAULT NULL,
  `timePosted` datetime NOT NULL,
  `topicID` int DEFAULT NULL,
  `isAnon` bit(1) NOT NULL,
  `postTitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`postID`),
  KEY `FK_post_userID_user_userID_idx` (`userID`),
  KEY `FK_post_topicID_topic_topicID_idx` (`topicID`),
  KEY `FK_post_postParentID_post_postID_idx` (`parentPostID`),
  CONSTRAINT `FK_post_parentPostID_post_postID` FOREIGN KEY (`parentPostID`) REFERENCES `post` (`postID`),
  CONSTRAINT `FK_post_topicID_topic_topicID` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`),
  CONSTRAINT `FK_post_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `reactionTime` datetime NOT NULL,
  PRIMARY KEY (`reactionID`),
  KEY `FK_reaction_userID_user_userID_idx` (`userID`),
  KEY `FK_reaction_postID_post_postID_idx` (`postID`),
  CONSTRAINT `FK_reaction_postID_post_postID` FOREIGN KEY (`postID`) REFERENCES `post` (`postID`),
  CONSTRAINT `FK_reaction_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `savedpost`
--

DROP TABLE IF EXISTS `savedpost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savedpost` (
  `postID` int NOT NULL,
  `userID` int NOT NULL,
  `savedPostID` int NOT NULL AUTO_INCREMENT,
  `savedTime` datetime NOT NULL,
  PRIMARY KEY (`savedPostID`),
  KEY `FK_savedpost_userID_user_userID_idx` (`userID`),
  KEY `FK_savedpost_postID_post_postID_idx` (`postID`),
  CONSTRAINT `FK_savedpost_postID_post_postID` FOREIGN KEY (`postID`) REFERENCES `post` (`postID`),
  CONSTRAINT `FK_savedpost_userID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topicfollower`
--

DROP TABLE IF EXISTS `topicfollower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topicfollower` (
  `userID` int NOT NULL,
  `topicID` int NOT NULL,
  `topicfollowerID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`topicfollowerID`),
  KEY `FK_topicfollower_followerID_user_userID_idx` (`userID`),
  KEY `FK_topicfollower_topicID_topic_topicID_idx` (`topicID`),
  CONSTRAINT `FK_topicfollower_followerID_user_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
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
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `bio` varchar(100) DEFAULT NULL,
  `fav_band` varchar(100) DEFAULT NULL,
  `fav_song` varchar(100) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `user_authorities` varchar(45) DEFAULT NULL,
  `profilePic` int DEFAULT '1',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `userFollowerID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`userFollowerID`),
  KEY `FK_userID_idx` (`userID`),
  KEY `FK_followerID_idx` (`followerID`),
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

-- Dump completed on 2022-04-28  9:58:26
