-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: stg_career_stats
-- ------------------------------------------------------
-- Server version	5.7.10-log

--
-- Table structure for table training_record
--

DROP TABLE training_record IF EXISTS;

CREATE TABLE training_record (
  id bigint identity primary key,
  type integer NOT NULL,
  practice integer NOT NULL,
  notes LONGVARCHAR,
  time_spent_in_seconds integer DEFAULT 0,
  date timestamp DEFAULT CURRENT_TIMESTAMP,
);

ALTER TABLE training_record ADD CONSTRAINT fktr1 FOREIGN KEY (type) REFERENCES training_type(id);
ALTER TABLE training_record ADD CONSTRAINT fktr2 FOREIGN KEY (practice) REFERENCES practice(id);

INSERT INTO training_record VALUES (1,1,1,'first test',0,'2016-01-22 17:45:38'),(2,2,1,'second test',0,'2016-01-22 17:45:38'),(3,3,1,'third test',3600,'2016-01-22 17:45:38'),(4,1,2,'fourth test',0,'2016-01-22 18:07:25'),(5,2,2,'fifth test',0,'2016-01-22 18:07:25'),(6,3,2,'sxth test',1800,'2016-01-22 18:07:25');

