-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stg_career_stats
-- ------------------------------------------------------
-- Server version	5.7.10-log



--
-- Table structure for table training_type
--

DROP TABLE training_type IF EXISTS;

CREATE TABLE training_type (
  id bigint identity primary key,
  type varchar(45) NOT NULL,
  UNIQUE (type)
);


--
-- Dumping data for table training_type
--

INSERT INTO training_type VALUES (2,'Blog Post'),(1,'Brown Bag'),(3,'Carrer Development');
