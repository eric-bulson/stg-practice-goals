-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stg_career_stats
-- ------------------------------------------------------
-- Server version	5.7.10-log

--
-- Table structure for table training_goals
--

DROP TABLE IF EXISTS training_goals;

CREATE TABLE training_goals (
  id int(10) unsigned NOT NULL,
  type int(10) unsigned NOT NULL,
  goal int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  CONSTRAINT id FOREIGN KEY (id) REFERENCES training_type (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO training_goals VALUES (1,1,100),(2,2,200),(3,3,200);

