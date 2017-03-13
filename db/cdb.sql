-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: computer-database-db
-- ------------------------------------------------------
-- Server version	5.5.54-0+deb8u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Apple Inc.'),(2,'Thinking Machines'),(3,'RCA'),(4,'Netronics'),(5,'Tandy Corporation'),(6,'Commodore International'),(7,'MOS Technology'),(8,'Micro Instrumentation and Telemetry Systems'),(9,'IMS Associates, Inc.'),(10,'Digital Equipment Corporation'),(11,'Lincoln Laboratory'),(12,'Moore School of Electrical Engineering'),(13,'IBM'),(14,'Amiga Corporation'),(15,'Canon'),(16,'Nokia'),(17,'Sony'),(18,'OQO'),(19,'NeXT'),(20,'Atari'),(22,'Acorn computer'),(23,'Timex Sinclair'),(24,'Nintendo'),(25,'Sinclair Research Ltd'),(26,'Xerox'),(27,'Hewlett-Packard'),(28,'Zemmix'),(29,'ACVS'),(30,'Sanyo'),(31,'Cray'),(32,'Evans & Sutherland'),(33,'E.S.R. Inc.'),(34,'OMRON'),(35,'BBN Technologies'),(36,'Lenovo Group'),(37,'ASUS'),(38,'Amstrad'),(39,'Sun Microsystems'),(40,'Texas Instruments'),(41,'HTC Corporation'),(42,'Research In Motion'),(43,'Samsung Electronics');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `introduced` timestamp NULL DEFAULT NULL,
  `discontinued` timestamp NULL DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_computer_company_1` (`company_id`),
  CONSTRAINT `fk_computer_company_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (1,'MacBook Pro 15.4 inch',NULL,NULL,1),(2,'CM-2a',NULL,NULL,2),(3,'CM-200',NULL,NULL,2),(4,'CM-5e',NULL,NULL,2),(5,'CM-5','1990-12-31 23:00:00',NULL,2),(6,'MacBook Pro','2006-01-09 23:00:00',NULL,1),(7,'Apple IIe',NULL,NULL,NULL),(8,'Apple IIc',NULL,NULL,NULL),(9,'Apple IIGS',NULL,NULL,NULL),(10,'Apple IIc Plus',NULL,NULL,NULL),(11,'Apple II Plus',NULL,NULL,NULL),(12,'Apple III','1980-04-30 22:00:00','1984-03-31 22:00:00',1),(13,'Apple Lisa',NULL,NULL,1),(14,'CM-2',NULL,NULL,2),(15,'Connection Machine','1986-12-31 23:00:00',NULL,2),(16,'Apple II','1977-03-31 23:00:00','1993-09-30 23:00:00',1),(17,'Apple III Plus','1983-11-30 23:00:00','1984-03-31 22:00:00',1),(18,'COSMAC ELF',NULL,NULL,3),(19,'COSMAC VIP','1976-12-31 23:00:00',NULL,3),(20,'ELF II','1976-12-31 23:00:00',NULL,4),(21,'Macintosh','1984-01-23 23:00:00',NULL,1),(22,'Macintosh II',NULL,NULL,NULL),(23,'Macintosh Plus','1986-01-15 23:00:00','1990-10-14 23:00:00',1),(24,'Macintosh IIfx',NULL,NULL,NULL),(25,'iMac','1997-12-31 23:00:00',NULL,1),(26,'Mac Mini','2005-01-21 23:00:00',NULL,1),(27,'Mac Pro','2006-08-06 22:00:00',NULL,1),(28,'Power Macintosh','1994-02-28 23:00:00','2006-07-31 22:00:00',1),(29,'PowerBook','1990-12-31 23:00:00','2005-12-31 23:00:00',1),(30,'Xserve',NULL,NULL,NULL),(31,'Powerbook 100',NULL,NULL,NULL),(32,'Powerbook 140',NULL,NULL,NULL),(33,'Powerbook 170',NULL,NULL,NULL),(34,'PowerBook Duo',NULL,NULL,NULL),(35,'PowerBook 190',NULL,NULL,NULL),(36,'Macintosh Quadra','1990-12-31 23:00:00',NULL,1),(37,'Macintosh Quadra 900',NULL,NULL,NULL),(38,'Macintosh Quadra 700',NULL,NULL,NULL),(39,'Macintosh LC','1989-12-31 23:00:00',NULL,1),(40,'Macintosh LC II','1989-12-31 23:00:00',NULL,1),(41,'Macintosh LC III','1992-12-31 23:00:00',NULL,1),(42,'Macintosh LC III+',NULL,NULL,NULL),(43,'Macintosh Quadra 605','1993-10-20 23:00:00',NULL,1),(44,'Macintosh LC 500 series',NULL,NULL,NULL),(45,'TRS-80 Color Computer','1979-12-31 23:00:00',NULL,5),(46,'Acorn System 2',NULL,NULL,NULL),(47,'Dragon 32/64',NULL,NULL,NULL),(48,'MEK6800D2',NULL,NULL,NULL),(49,'Newbear 77/68',NULL,NULL,NULL),(50,'Commodore PET',NULL,NULL,6),(51,'Commodore 64','1982-07-31 22:00:00','1993-12-31 23:00:00',6),(52,'Commodore 64C',NULL,NULL,NULL),(53,'Commodore SX-64',NULL,NULL,6),(54,'Commodore 128',NULL,NULL,6),(55,'Apple I','1976-03-31 22:00:00','1977-09-30 23:00:00',1),(56,'KIM-1','1974-12-31 23:00:00',NULL,7),(57,'Altair 8800','1974-12-18 23:00:00',NULL,8),(58,'IMSAI 8080','1975-07-31 23:00:00',NULL,9),(59,'IMSAI Series Two',NULL,NULL,NULL),(60,'VAX','1977-10-24 23:00:00',NULL,10),(61,'VAX 11/780','1977-10-24 23:00:00',NULL,10),(62,'VAX 11/750','1980-09-30 23:00:00',NULL,10),(63,'TX-2','0000-00-00 00:00:00',NULL,11),(64,'TX-0','0000-00-00 00:00:00',NULL,11),(65,'Whirlwind','0000-00-00 00:00:00',NULL,11),(66,'ENIAC','0000-00-00 00:00:00','0000-00-00 00:00:00',12),(67,'IBM PC','1981-08-11 22:00:00',NULL,13),(68,'Macintosh Classic',NULL,NULL,NULL),(69,'Macintosh Classic II','1990-12-31 23:00:00',NULL,1),(70,'Amiga','1984-12-31 23:00:00',NULL,14),(71,'Amiga 1000',NULL,NULL,6),(72,'Amiga 500','1986-12-31 23:00:00',NULL,6),(73,'Amiga 500+',NULL,NULL,NULL),(74,'Amiga 2000','1985-12-31 23:00:00','1989-12-31 23:00:00',6),(75,'Amiga 3000',NULL,NULL,6),(76,'Amiga 600','1992-02-29 23:00:00',NULL,6),(77,'Macintosh 128K','1983-12-31 23:00:00',NULL,1),(78,'Macintosh 512K','1984-09-09 22:00:00','1986-04-13 22:00:00',1),(79,'Macintosh SE','1987-03-01 23:00:00','1989-07-31 22:00:00',1),(80,'Macintosh SE/30','1989-01-18 23:00:00','1991-10-20 23:00:00',1),(81,'Canon Cat','1986-12-31 23:00:00',NULL,15),(82,'Nokia 770',NULL,NULL,16),(83,'Nokia N800','2006-12-31 23:00:00',NULL,16),(84,'Mylo','2006-09-20 22:00:00',NULL,17),(85,'OQO 02','2006-12-31 23:00:00',NULL,18),(86,'OQO 01+',NULL,NULL,NULL),(87,'Pinwheel calculator',NULL,NULL,NULL),(88,'iBook',NULL,NULL,1),(89,'MacBook','2006-05-15 22:00:00',NULL,1),(90,'NeXTstation','1989-12-31 23:00:00','1992-12-31 23:00:00',19),(91,'NeXTcube','1987-12-31 23:00:00','1992-12-31 23:00:00',19),(92,'NeXTstation Color Turbo',NULL,NULL,NULL),(93,'NeXTstation Color',NULL,NULL,NULL),(94,'NeXTstation Turbo',NULL,NULL,NULL),(95,'NeXTcube Turbo',NULL,NULL,19),(96,'NeXTcube 040',NULL,NULL,19),(97,'NeXTcube 030',NULL,NULL,19),(98,'Tinkertoy Tic-Tac-Toe Computer',NULL,NULL,NULL),(99,'Z3',NULL,NULL,NULL),(100,'Z4',NULL,NULL,NULL),(101,'Z1',NULL,NULL,NULL),(102,'Z2',NULL,NULL,NULL),(103,'Wang 2200','1973-04-30 23:00:00',NULL,NULL),(104,'Wang VS',NULL,NULL,NULL),(105,'Wang OIS',NULL,NULL,NULL),(106,'BBC Micro',NULL,NULL,22),(107,'IBM 650','0000-00-00 00:00:00','0000-00-00 00:00:00',13),(108,'Cray-1',NULL,NULL,NULL),(109,'Cray-3',NULL,NULL,NULL),(110,'Cray-2',NULL,NULL,NULL),(111,'Cray-4',NULL,NULL,NULL),(112,'Cray X1',NULL,NULL,NULL),(113,'Cray XD1',NULL,NULL,NULL),(114,'Cray T3D','1992-12-31 23:00:00',NULL,NULL),(115,'Cray T3E','1994-12-31 23:00:00',NULL,NULL),(116,'Cray C90',NULL,NULL,NULL),(117,'Cray T90',NULL,NULL,NULL),(118,'Cray SV1',NULL,NULL,NULL),(119,'Cray J90',NULL,NULL,NULL),(120,'Cray XT3',NULL,NULL,NULL),(121,'Cray CS6400',NULL,NULL,NULL),(122,'Atari ST','1984-12-31 23:00:00','1992-12-31 23:00:00',20),(123,'Amiga 2500',NULL,NULL,NULL),(124,'Amiga 2500',NULL,NULL,6),(125,'Amiga 4000',NULL,NULL,6),(126,'Amiga 3000UX',NULL,NULL,6),(127,'Amiga 3000T',NULL,NULL,6),(128,'Amiga 4000T',NULL,NULL,6),(129,'Amiga 1200','1992-09-30 23:00:00','1995-12-31 23:00:00',6),(130,'Atari 1040 STf','1985-12-31 23:00:00',NULL,NULL),(131,'Atari 520 ST','1984-12-31 23:00:00',NULL,NULL),(132,'Atari 520 STfm','1985-12-31 23:00:00',NULL,NULL),(133,'Atari 1040 STe','1988-12-31 23:00:00',NULL,NULL),(134,'Atari MEGA STe','1990-12-31 23:00:00',NULL,NULL),(135,'Atari 520 ST+','1984-12-31 23:00:00',NULL,NULL),(136,'Atari 520 STm','1984-12-31 23:00:00',NULL,NULL),(137,'Atari 130 ST','1984-12-31 23:00:00',NULL,NULL),(138,'Atari 260 ST','1984-12-31 23:00:00',NULL,NULL),(139,'Atari MEGA ST','1986-12-31 23:00:00',NULL,NULL),(140,'Atari 520 STf','1985-12-31 23:00:00',NULL,NULL),(141,'Atari 1040 STfm','1985-12-31 23:00:00',NULL,NULL),(142,'Atari 2080 ST','1985-12-31 23:00:00',NULL,NULL),(143,'Atari 260 ST+','1984-12-31 23:00:00',NULL,NULL),(144,'Atari 4160 STe','1987-12-31 23:00:00',NULL,NULL),(145,'TRS-80 Color Computer 2',NULL,NULL,NULL),(146,'TRS-80 Color Computer 3',NULL,NULL,NULL),(147,'TRS-80 Model 1','1976-12-31 23:00:00',NULL,5),(148,'Timex Sinclair 2068','1983-10-31 23:00:00','1984-03-31 22:00:00',23),(149,'ZX Spectrum','1981-12-31 23:00:00',NULL,25),(150,'Xerox Star','1980-12-31 23:00:00',NULL,26),(151,'Xerox Alto',NULL,NULL,NULL),(152,'Acorn Archimedes',NULL,NULL,22),(153,'Nintendo Entertainment System',NULL,NULL,24),(154,'Super Nintendo Entertainment System','1991-07-31 22:00:00','1998-12-31 23:00:00',24),(155,'Super Famicom',NULL,NULL,NULL),(156,'Nintendo GameCube',NULL,NULL,24),(157,'Game Boy line',NULL,NULL,NULL),(158,'PlayStation','1994-12-02 23:00:00',NULL,17),(159,'PlayStation 2','2000-03-23 23:00:00',NULL,17),(160,'Game & Watch',NULL,NULL,24),(161,'EDSAC',NULL,NULL,NULL),(162,'IBM System/4 Pi',NULL,NULL,NULL),(163,'IBM AP-101',NULL,NULL,NULL),(164,'IBM TC-1',NULL,NULL,NULL),(165,'IBM AP-101B',NULL,NULL,NULL),(166,'IBM AP-101S',NULL,NULL,13),(167,'ProLiant',NULL,NULL,27),(168,'Http://nepomuk.semanticdesktop.org/xwiki/',NULL,NULL,NULL),(169,'Sinclair QL','1983-12-31 23:00:00','1985-12-31 23:00:00',25),(170,'Sinclair ZX81','1980-12-31 23:00:00',NULL,25),(171,'Sinclair ZX80',NULL,NULL,25),(172,'Atari 65XE',NULL,NULL,20),(173,'Deep Blue',NULL,NULL,NULL),(174,'Macintosh Quadra 650',NULL,NULL,NULL),(175,'Macintosh Quadra 610',NULL,NULL,NULL),(176,'Macintosh Quadra 800',NULL,NULL,NULL),(177,'Macintosh Quadra 950',NULL,NULL,NULL),(178,'PowerBook 160',NULL,NULL,NULL),(179,'PowerBook 145B',NULL,NULL,NULL),(180,'PowerBook 170',NULL,NULL,NULL),(181,'PowerBook 145',NULL,NULL,NULL),(182,'PowerBook G3',NULL,NULL,NULL),(183,'PowerBook 140',NULL,NULL,NULL),(184,'Macintosh IIcx',NULL,NULL,NULL),(185,'Powerbook 180',NULL,NULL,NULL),(186,'PowerBook G4',NULL,NULL,NULL),(187,'Macintosh XL',NULL,NULL,NULL),(188,'PowerBook 100',NULL,NULL,NULL),(189,'PowerBook 2400c',NULL,NULL,NULL),(190,'PowerBook 1400',NULL,NULL,NULL),(191,'Macintosh Quadra 630',NULL,NULL,NULL),(192,'Macintosh Quadra 660AV',NULL,NULL,NULL),(193,'Macintosh Quadra 840AV',NULL,NULL,NULL),(194,'PowerBook 5300',NULL,NULL,NULL),(195,'PowerBook 3400c',NULL,NULL,NULL),(196,'Macintosh Color Classic',NULL,NULL,NULL),(197,'Macintosh 512Ke',NULL,NULL,NULL),(198,'Macintosh IIsi',NULL,NULL,NULL),(199,'Macintosh IIx',NULL,NULL,NULL),(200,'PowerBook 500 series',NULL,NULL,NULL),(201,'Power Macintosh G3',NULL,NULL,NULL),(202,'Macintosh IIci',NULL,NULL,NULL),(203,'iMac G5','2004-08-30 22:00:00',NULL,1),(204,'Power Mac G4',NULL,NULL,NULL),(205,'Power Macintosh 7100',NULL,NULL,NULL),(206,'Power Macintosh 9600',NULL,NULL,NULL),(207,'Power Macintosh 7200',NULL,NULL,NULL),(208,'Power Macintosh 7300',NULL,NULL,NULL),(209,'Power Macintosh 8600',NULL,NULL,NULL),(210,'Power Macintosh 6200',NULL,NULL,NULL),(211,'Power Macintosh 8100',NULL,NULL,NULL),(212,'Compact Macintosh',NULL,NULL,NULL),(213,'Power Macintosh 4400',NULL,NULL,NULL),(214,'Power Macintosh 9500',NULL,NULL,NULL),(215,'Macintosh Portable',NULL,NULL,NULL),(216,'EMac',NULL,NULL,NULL),(217,'Power Macintosh 7600',NULL,NULL,NULL),(218,'Power Mac G5',NULL,NULL,NULL),(219,'Power Macintosh 7500',NULL,NULL,NULL),(220,'Power Macintosh 6100',NULL,NULL,NULL),(221,'Power Macintosh 8500',NULL,NULL,NULL),(222,'Macintosh IIvi',NULL,NULL,NULL),(223,'Macintosh IIvx',NULL,NULL,NULL),(224,'IMac G3',NULL,NULL,NULL),(225,'IMac G4',NULL,NULL,NULL),(226,'Power Mac G4 Cube',NULL,NULL,1),(227,'Intel iMac',NULL,NULL,NULL),(228,'Deep Thought',NULL,NULL,13),(229,'Wii','2006-11-18 23:00:00',NULL,24),(230,'IBM System x',NULL,NULL,NULL),(231,'IBM System i','2005-12-31 23:00:00',NULL,13),(232,'IBM System z','2005-12-31 23:00:00',NULL,13),(233,'IBM System p','1999-12-31 23:00:00',NULL,13),(234,'LC 575',NULL,NULL,NULL),(235,'Macintosh TV',NULL,NULL,NULL),(236,'Macintosh Performa',NULL,NULL,NULL),(237,'Macintosh II series',NULL,NULL,NULL),(238,'Power Macintosh 6400',NULL,NULL,NULL),(239,'Power Macintosh 6500',NULL,NULL,NULL),(240,'Apple PenLite',NULL,NULL,NULL),(241,'Wallstreet',NULL,NULL,NULL),(242,'Twentieth Anniversary Macintosh',NULL,NULL,NULL),(243,'Power Macintosh 5500',NULL,NULL,NULL),(244,'iBook G3',NULL,NULL,1),(245,'Power Macintosh 5200 LC',NULL,NULL,NULL),(246,'Power Macintosh 5400',NULL,NULL,NULL),(247,'CM-1',NULL,NULL,NULL),(248,'MSX','1982-12-31 23:00:00','1994-12-31 23:00:00',28),(249,'PlayStation 3',NULL,NULL,17),(250,'MSX2','1985-12-31 23:00:00',NULL,29),(251,'MSX2+','1987-12-31 23:00:00',NULL,30),(252,'MSX turbo R','1989-12-31 23:00:00',NULL,NULL),(253,'Panasonic FS A1GT',NULL,NULL,NULL),(254,'Panasonic FS A1ST',NULL,NULL,NULL),(255,'PDP-11',NULL,NULL,10),(256,'PDP-1',NULL,NULL,10),(257,'PDP-10',NULL,NULL,10),(258,'PDP-8',NULL,NULL,10),(259,'PDP-6',NULL,NULL,10),(260,'DECSYSTEM-20',NULL,NULL,10),(261,'PDP-7',NULL,NULL,10),(262,'PDP-5',NULL,NULL,10),(263,'PDP-12',NULL,NULL,10),(264,'LINC',NULL,NULL,10),(265,'PDP-14',NULL,NULL,10),(266,'PDP-15',NULL,NULL,10),(267,'PDP-16',NULL,NULL,10),(268,'Cray X2','2006-12-31 23:00:00',NULL,31),(269,'Cray X-MP','1981-12-31 23:00:00',NULL,31),(270,'Evans & Sutherland ES-1',NULL,NULL,32),(271,'Commodore VIC-20','1979-12-31 23:00:00',NULL,6),(272,'PowerBook 150',NULL,NULL,NULL),(273,'MacBook Air','2008-01-14 23:00:00',NULL,1),(274,'Digi-Comp I','0000-00-00 00:00:00',NULL,33),(275,'Digi-Comp',NULL,NULL,NULL),(276,'Digi-Comp II',NULL,NULL,33),(277,'Manchester Mark I','0000-00-00 00:00:00',NULL,NULL),(278,'Small-Scale Experimental Machine','0000-00-00 00:00:00',NULL,NULL),(279,'Nintendo 64',NULL,NULL,24),(280,'Game Boy Advance',NULL,NULL,24),(281,'Game Boy',NULL,NULL,24),(282,'Nintendo DS Lite',NULL,NULL,24),(283,'Nintendo DS','2003-12-31 23:00:00',NULL,24),(284,'Game Boy Color',NULL,NULL,24),(285,'Game Boy Advance SP',NULL,NULL,24),(286,'Virtual Boy',NULL,NULL,24),(287,'Game Boy Micro',NULL,NULL,24),(288,'Roadrunner',NULL,NULL,13),(289,'HP 9000',NULL,NULL,NULL),(290,'OMRON Luna-88K2',NULL,NULL,NULL),(291,'OMRON Luna-88K',NULL,NULL,34),(292,'Motorola series 900',NULL,NULL,NULL),(293,'Motorola M8120',NULL,NULL,NULL),(294,'Triton Dolphin System 100',NULL,NULL,NULL),(295,'BBN TC2000','1989-07-31 22:00:00',NULL,35),(296,'WRT54G',NULL,NULL,NULL),(297,'ThinkPad','1991-12-31 23:00:00',NULL,36),(298,'Apple Newton','1992-12-31 23:00:00','1997-12-31 23:00:00',1),(299,'Atanasoff-Berry Computer','0000-00-00 00:00:00',NULL,NULL),(300,'Atlas Computer','0000-00-00 00:00:00','1973-12-31 23:00:00',NULL),(301,'ASUS Eee PC 901',NULL,NULL,37),(302,'ASUS Eee PC 701',NULL,NULL,NULL),(303,'IBM 7030','0000-00-00 00:00:00',NULL,13),(304,'System/38','1978-12-31 23:00:00',NULL,13),(305,'System/36','1982-12-31 23:00:00','1999-12-31 23:00:00',13),(306,'IBM 7090','0000-00-00 00:00:00',NULL,13),(307,'IBM RT',NULL,NULL,13),(308,'System/360','0000-00-00 00:00:00',NULL,13),(309,'IBM 801','1979-12-31 23:00:00',NULL,13),(310,'IBM 1401','0000-00-00 00:00:00',NULL,13),(311,'ASCI White','2000-12-31 23:00:00','2005-12-31 23:00:00',13),(312,'Blue Gene',NULL,NULL,13),(313,'ASCI Blue Pacific','1997-12-31 23:00:00',NULL,13),(314,'iPhone','2007-05-31 22:00:00',NULL,1),(315,'Nokia N810','2007-10-16 22:00:00',NULL,16),(316,'EDSAC 2',NULL,NULL,NULL),(317,'Titan',NULL,NULL,NULL),(318,'Pilot ACE',NULL,NULL,NULL),(319,'HP Mini 1000','2008-10-28 23:00:00',NULL,27),(320,'HP 2133 Mini-Note PC','2008-04-14 22:00:00',NULL,27),(321,'Kogan Agora Pro','2008-12-03 23:00:00',NULL,NULL),(322,'D-Series Machines',NULL,NULL,NULL),(323,'ZX Spectrum 48K','1981-12-31 23:00:00',NULL,25),(324,'ZX Spectrum 16K','1981-12-31 23:00:00',NULL,25),(325,'ZX Spectrum 128','1985-08-31 22:00:00',NULL,25),(326,'ZX Spectrum +3',NULL,NULL,38),(327,'ZX Spectrum +2','1985-12-31 23:00:00',NULL,38),(328,'ZX Spectrum +2A','1986-12-31 23:00:00',NULL,38),(329,'ZX Spectrum +','1984-05-31 22:00:00',NULL,25),(330,'Acer Extensa',NULL,NULL,NULL),(331,'Acer Extensa 5220',NULL,NULL,NULL),(332,'Dell Latitude',NULL,NULL,NULL),(333,'Toshiba Satellite',NULL,NULL,NULL),(334,'Timex Sinclair 2048',NULL,NULL,23),(335,'Sprinter',NULL,NULL,NULL),(336,'Timex Computer 2048',NULL,NULL,NULL),(337,'Pentagon',NULL,NULL,NULL),(338,'Belle',NULL,NULL,NULL),(339,'Loki',NULL,NULL,25),(340,'Hobbit',NULL,NULL,NULL),(341,'NeXT Computer',NULL,NULL,19),(342,'TRS-80',NULL,NULL,NULL),(343,'TRS-80 Model 2','1979-12-31 23:00:00',NULL,5),(344,'TRS-80 Model 3',NULL,NULL,5),(345,'STacy','1988-12-31 23:00:00',NULL,NULL),(346,'ST BOOK','1989-12-31 23:00:00',NULL,NULL),(347,'Atari 520 STE','1988-12-31 23:00:00',NULL,NULL),(348,'Amiga 2000 Model A',NULL,NULL,NULL),(349,'Amiga 2000 Model B',NULL,NULL,NULL),(350,'Amiga 2000 Model C',NULL,NULL,NULL),(351,'IBM 3270',NULL,NULL,NULL),(352,'CALDIC',NULL,NULL,NULL),(353,'Modbook',NULL,NULL,NULL),(354,'Compaq SystemPro',NULL,NULL,NULL),(355,'ARRA',NULL,NULL,NULL),(356,'IBM System Cluster 1350',NULL,NULL,NULL),(357,'Finite element machine',NULL,NULL,NULL),(358,'ES7000',NULL,NULL,NULL),(359,'HP MediaSmart Server',NULL,NULL,NULL),(360,'HP Superdome',NULL,NULL,NULL),(361,'IBM Power Systems','2007-12-31 23:00:00',NULL,13),(362,'Oslo Analyzer',NULL,NULL,NULL),(363,'Microsoft Softcard',NULL,NULL,NULL),(364,'WITCH',NULL,NULL,NULL),(365,'Analytical engine',NULL,NULL,NULL),(366,'EDVAC',NULL,NULL,NULL),(367,'BINAC',NULL,NULL,NULL),(368,'Earth Simulator',NULL,NULL,NULL),(369,'BARK',NULL,NULL,NULL),(370,'Harvard Mark I','0000-00-00 00:00:00',NULL,13),(371,'ILLIAC IV',NULL,NULL,NULL),(372,'ILLIAC II',NULL,NULL,NULL),(373,'ILLIAC III',NULL,NULL,NULL),(374,'Water integrator',NULL,NULL,NULL),(375,'CSIRAC',NULL,NULL,NULL),(376,'System X',NULL,NULL,NULL),(377,'Harvest',NULL,NULL,NULL),(378,'ChipTest',NULL,NULL,NULL),(379,'HiTech',NULL,NULL,NULL),(380,'Bomba',NULL,NULL,NULL),(381,'ACE',NULL,NULL,NULL),(382,'ASCI Red',NULL,NULL,NULL),(383,'ASCI Thors Hammer',NULL,NULL,NULL),(384,'ASCI Purple','2004-12-31 23:00:00',NULL,13),(385,'ASCI Blue Mountain',NULL,NULL,NULL),(386,'Columbia',NULL,NULL,NULL),(387,'HP Integrity',NULL,NULL,NULL),(388,'APEXC',NULL,NULL,NULL),(389,'Datasaab D2',NULL,NULL,NULL),(390,'BRLESC',NULL,NULL,NULL),(391,'DYSEAC',NULL,NULL,NULL),(392,'SSEC','0000-00-00 00:00:00',NULL,13),(393,'Hydra',NULL,NULL,NULL),(394,'FUJIC',NULL,NULL,NULL),(395,'RAYDAC',NULL,NULL,NULL),(396,'Harvard Mark III',NULL,NULL,NULL),(397,'DATAR',NULL,NULL,NULL),(398,'ReserVec',NULL,NULL,NULL),(399,'DASK',NULL,NULL,NULL),(400,'UTEC',NULL,NULL,NULL),(401,'DRTE Computer',NULL,NULL,NULL),(402,'PowerEdge',NULL,NULL,NULL),(403,'Apple Network Server',NULL,NULL,NULL),(404,'Goodyear MPP',NULL,NULL,NULL),(405,'Macintosh 128K technical details',NULL,NULL,NULL),(406,'Power Macintosh G3',NULL,NULL,NULL),(407,'CER-10',NULL,NULL,NULL),(408,'CER-20',NULL,NULL,NULL),(409,'IBM BladeCenter','2001-12-31 23:00:00',NULL,13),(410,'Wisconsin Integrally Synchronized Computer',NULL,NULL,NULL),(411,'Amstrad CPC',NULL,NULL,38),(412,'Amstrad CPC 6128',NULL,NULL,38),(413,'Amstrad CPC 664',NULL,NULL,38),(414,'Amstrad CPC 464',NULL,NULL,38),(415,'Intergraph',NULL,NULL,NULL),(416,'Enterprise',NULL,NULL,NULL),(417,'MTX500',NULL,NULL,NULL),(418,'Acorn Electron',NULL,NULL,NULL),(419,'Sony Vaio P','2009-01-31 23:00:00',NULL,17),(420,'VAIO',NULL,NULL,17),(421,'Sony Vaio P VGN-P588E/Q',NULL,NULL,NULL),(422,'Sony Vaio P VGN-P530H/G',NULL,NULL,NULL),(423,'Sony Vaio P VGN-P530H/W',NULL,NULL,NULL),(424,'Sony Vaio P VGN-P530H/Q',NULL,NULL,NULL),(425,'Sony Vaio P VGN-P530H/R',NULL,NULL,NULL),(426,'Sony Vaio P VGN-P588E/R',NULL,NULL,NULL),(427,'Sony Vaio P VGN-P598E/Q',NULL,NULL,NULL),(428,'Timex Sinclair 1000','1982-06-30 22:00:00',NULL,23),(429,'Komputer 2086',NULL,NULL,NULL),(430,'Galaksija',NULL,NULL,NULL),(431,'Vector-06C',NULL,NULL,NULL),(432,'Elektronika BK',NULL,NULL,NULL),(433,'Sun386i',NULL,NULL,39),(434,'Xerox Daybreak','1984-12-31 23:00:00','1988-12-31 23:00:00',NULL),(435,'Xerox NoteTaker',NULL,NULL,26),(436,'D4a','0000-00-00 00:00:00',NULL,NULL),(437,'LGP-30',NULL,NULL,NULL),(438,'LGP-21',NULL,NULL,NULL),(439,'ASUS Eee PC 900','2008-04-30 22:00:00',NULL,37),(440,'Atari TT030',NULL,NULL,NULL),(441,'Bi Am ZX-Spectrum 48/64',NULL,NULL,NULL),(442,'Bi Am ZX-Spectrum 128',NULL,NULL,NULL),(443,'PlayStation Portable',NULL,NULL,NULL),(444,'MSI Wind Netbook',NULL,NULL,NULL),(445,'Sharp Mebius NJ70A','2009-04-20 22:00:00',NULL,NULL),(446,'HTC Snap',NULL,NULL,41),(447,'Commodore Educator 64',NULL,NULL,6),(448,'Amiga 1500',NULL,NULL,6),(449,'Commodore 65',NULL,NULL,6),(450,'Commodore 16',NULL,NULL,6),(451,'Commodore CBM-II',NULL,NULL,6),(452,'Commodore Plus/4',NULL,NULL,6),(453,'Commodore LCD',NULL,NULL,6),(454,'Commodore MAX Machine',NULL,NULL,6),(455,'Aster CT-80',NULL,NULL,NULL),(456,'Test','2008-12-31 23:00:00','2008-12-31 23:00:00',NULL),(457,'MSI GX723',NULL,NULL,NULL),(458,'Eee PC 1000HV','2009-05-21 22:00:00',NULL,NULL),(459,'VTech Laser 200','1982-12-31 23:00:00',NULL,NULL),(460,'CrunchPad',NULL,NULL,NULL),(461,'Neo Geo','1989-12-31 23:00:00',NULL,NULL),(462,'Sega Mega Drive',NULL,NULL,NULL),(463,'Sega Master System',NULL,NULL,NULL),(464,'TurboGrafx-16',NULL,NULL,NULL),(465,'Sun-3',NULL,NULL,39),(466,'Pleiades',NULL,NULL,NULL),(467,'IBM Sequoia',NULL,NULL,NULL),(468,'Inves Spectrum 48k plus',NULL,NULL,NULL),(469,'iPhone 3G',NULL,NULL,NULL),(470,'iPhone 3GS',NULL,NULL,NULL),(471,'Beagle Board',NULL,NULL,40),(472,'HP nPar',NULL,NULL,NULL),(473,'MacBook Family',NULL,NULL,NULL),(474,'Reservisor',NULL,NULL,NULL),(475,'BladeSystem',NULL,NULL,NULL),(476,'lenovo thinkpad t60p',NULL,NULL,NULL),(477,'lenovo thinkpad x200',NULL,NULL,36),(478,'lenovo thinkpad t60',NULL,NULL,NULL),(479,'lenovo thinkpad w700',NULL,NULL,NULL),(480,'lenovo thinkpad t41',NULL,NULL,NULL),(481,'lenovo thinkpad z61p',NULL,NULL,NULL),(482,'lenovo thinkpad x61s',NULL,NULL,NULL),(483,'lenovo thinkpad t43',NULL,NULL,NULL),(484,'lenovo thinkpad r400',NULL,NULL,NULL),(485,'lenovo thinkpad x60s',NULL,NULL,NULL),(486,'lenovo thinkpad x301',NULL,NULL,NULL),(487,'lenovo thinkpad t42',NULL,NULL,NULL),(488,'lenovo thinkpad r61',NULL,NULL,NULL),(489,'lenovo thinkpad w500',NULL,NULL,NULL),(490,'lenovo thinkpad sl400',NULL,NULL,NULL),(491,'lenovo thinkpad x40',NULL,NULL,NULL),(492,'lenovo thinkpad x200 tablet',NULL,NULL,36),(493,'lenovo thinkpad t400s',NULL,NULL,NULL),(494,'Nokia N900','2009-09-30 22:00:00',NULL,16),(495,'Internet Tablet',NULL,NULL,NULL),(496,'Meiko Computing Surface','1985-12-31 23:00:00','1992-12-31 23:00:00',NULL),(497,'CS-2',NULL,NULL,NULL),(498,'IBM 701','0000-00-00 00:00:00',NULL,13),(499,'IBM 5100','1974-12-31 23:00:00',NULL,13),(500,'AN/FSQ-7','0000-00-00 00:00:00',NULL,13),(501,'AN/FSQ-32','0000-00-00 00:00:00',NULL,13),(502,'IBM CPC','0000-00-00 00:00:00',NULL,13),(503,'System/34','1977-12-31 23:00:00','1982-12-31 23:00:00',13),(504,'System/32','1974-12-31 23:00:00',NULL,13),(505,'System/3','0000-00-00 00:00:00','1984-12-31 23:00:00',13),(506,'IBM 305','0000-00-00 00:00:00',NULL,13),(507,'English Electric DEUCE',NULL,NULL,NULL),(508,'CER-203',NULL,NULL,NULL),(509,'CER-22',NULL,NULL,NULL),(510,'Kentucky Linux Athlon Testbed',NULL,NULL,NULL),(511,'QNAP TS-101',NULL,NULL,NULL),(512,'iPad','2009-12-31 23:00:00','2011-03-01 23:00:00',1),(513,'iPhone 2G',NULL,NULL,NULL),(514,'Inslaw',NULL,NULL,NULL),(515,'WePad','2010-06-30 22:00:00',NULL,NULL),(516,'MacBook Parts',NULL,NULL,1),(517,'MacBook 13-inch Core 2 Duo 2.13GHz (MC240LL/A) DDR2 Model',NULL,NULL,1),(518,'MacBook 13-inch Core 2 Duo 2.13GHz (MC240T/A) DDR2 Model',NULL,NULL,NULL),(519,'MacBook 13-inch Core 2 Duo 2.13GHz (MC240X/A) DDR2 Model',NULL,NULL,NULL),(520,'MacBook 13-inch Core 2 Duo 2.26GHz (Unibody MC207LL/A) DDR3 Model',NULL,NULL,NULL),(521,'MC240LL/A',NULL,NULL,NULL),(522,'D.K.COMMUNICATION',NULL,NULL,NULL),(523,'iPhone 4',NULL,NULL,1),(524,'Nintendo 3DS','2010-03-22 23:00:00',NULL,24),(525,'ASUS Eee PC 1005PE','2009-12-31 23:00:00',NULL,37),(526,'National Law Enforcement System',NULL,NULL,NULL),(527,'BlackBerry PlayBook',NULL,NULL,42),(528,'Barnes & Noble nook','2009-10-19 22:00:00',NULL,NULL),(529,'SAM Coupé',NULL,NULL,NULL),(530,'HTC Dream','2008-10-21 22:00:00',NULL,41),(531,'Samsung Galaxy Tab','2010-09-01 22:00:00',NULL,43),(532,'BlackBerry PlayBook','2010-09-26 22:00:00',NULL,42),(533,'Tianhe-I',NULL,NULL,NULL),(534,'Kno',NULL,NULL,NULL),(535,'ThinkPad 701 C',NULL,NULL,NULL),(536,'ThinkPad 340 CSE',NULL,NULL,NULL),(537,'ThinkPad 755 CX',NULL,NULL,NULL),(538,'ThinkPad 755 CE',NULL,NULL,NULL),(539,'ThinkPad 370 C',NULL,NULL,NULL),(540,'Coleco Adam','1982-12-31 23:00:00',NULL,NULL),(541,'Nebulae',NULL,NULL,NULL),(542,'Alex eReader',NULL,NULL,NULL),(543,'Acer Iconia',NULL,NULL,NULL),(544,'Archos 101',NULL,NULL,NULL),(545,'Fujitsu Lifebook T900',NULL,NULL,NULL),(546,'Motorola Xoom',NULL,NULL,NULL),(547,'ViewSonic G Tablet',NULL,NULL,NULL),(548,'DEC Professional','1981-12-31 23:00:00',NULL,10),(549,'DEC Multia','1994-11-06 23:00:00',NULL,10),(550,'DEC Firefly',NULL,NULL,10),(551,'DEC 3000 AXP',NULL,NULL,10),(552,'DEC 2000 AXP','1993-05-24 22:00:00',NULL,10),(553,'DEC 4000 AXP','1992-11-09 23:00:00',NULL,10),(554,'DEC 7000/10000 AXP','1992-11-09 23:00:00',NULL,10),(555,'DEC Professional 350',NULL,NULL,NULL),(556,'DEC Rainbow 100',NULL,NULL,NULL),(557,'DEC Professional 325',NULL,NULL,NULL),(558,'DECmate II',NULL,NULL,10),(559,'DECmate',NULL,NULL,10),(560,'DECsystem',NULL,NULL,10),(561,'NetApp Filer',NULL,NULL,NULL),(562,'DEC GT40',NULL,NULL,10),(563,'ecoATM',NULL,NULL,NULL),(564,'MindWave BrainCubed Education Bundle',NULL,NULL,NULL),(565,'PalmPilot',NULL,NULL,NULL),(566,'Upcoming iPhone 5',NULL,NULL,1),(567,'Dell Inspiron 560 Desktop Computer ',NULL,NULL,NULL),(568,'IPad 2',NULL,NULL,1),(569,'HP TouchPad','2011-02-08 23:00:00',NULL,27),(570,'HP Veer','2011-02-08 23:00:00',NULL,27),(571,'Lenovo Thinkpad Edge 11',NULL,NULL,36),(572,'Dell Vostro',NULL,NULL,NULL),(573,'Gateway LT3103U','2007-12-31 23:00:00',NULL,NULL),(574,'iPhone 4S','2011-10-13 22:00:00',NULL,1);
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-13 10:37:51
