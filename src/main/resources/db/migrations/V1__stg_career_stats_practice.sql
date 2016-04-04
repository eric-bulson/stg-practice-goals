-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stg_career_stats
-- ------------------------------------------------------
-- Server version	5.7.10-log

--
-- Table structure for table practice
--

DROP TABLE practice IF EXISTS;

CREATE TABLE practice (
  id bigint identity primary key,
  name varchar(255) NOT NULL,
  UNIQUE (name)
);


INSERT INTO practice VALUES (1,'Java'),(2,'Scripted');

