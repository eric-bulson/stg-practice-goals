-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stg_career_stats
-- ------------------------------------------------------
-- Server version	5.7.10-log

--
-- Table structure for table training_record
--

DROP TABLE IF EXISTS training_record;

CREATE TABLE training_record (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  type int(10) unsigned NOT NULL,
  practice int(10) unsigned NOT NULL,
  notes mediumtext,
  time_spent_in_seconds int(10) unsigned DEFAULT '0',
  date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  KEY type_idx (type),
  KEY practice_idx (practice),
  CONSTRAINT practice FOREIGN KEY (practice) REFERENCES practice (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT type FOREIGN KEY (type) REFERENCES training_type (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO training_record VALUES (1,1,1,'first test',0,'2016-01-22 17:45:38'),(2,2,1,'second test',0,'2016-01-22 17:45:38'),(3,3,1,'third test',3600,'2016-01-22 17:45:38'),(4,1,2,'fourth test',0,'2016-01-22 18:07:25'),(5,2,2,'fifth test',0,'2016-01-22 18:07:25'),(6,3,2,'sxth test',1800,'2016-01-22 18:07:25');

