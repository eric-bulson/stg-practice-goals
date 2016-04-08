-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stg_career_stats
-- ------------------------------------------------------
-- Server version	5.7.10-log

--
-- Table structure for table training_goals
--

DROP TABLE training_goals IF EXISTS;

CREATE TABLE training_goals (
  id bigint identity primary key,
  type integer NOT NULL,
  goal integer DEFAULT 0,
);

ALTER TABLE training_goals ADD CONSTRAINT fk1 FOREIGN KEY (id) REFERENCES training_type(id);

INSERT INTO training_goals VALUES (1,1,100),(2,2,200),(3,3,200);

