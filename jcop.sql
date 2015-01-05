-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: jcop
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.14.04.1

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
-- Table structure for table `data_type`
--

DROP TABLE IF EXISTS `data_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dbType` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `fieldType` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `columnType` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `javaType` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_type`
--

LOCK TABLES `data_type` WRITE;
/*!40000 ALTER TABLE `data_type` DISABLE KEYS */;
INSERT INTO `data_type` VALUES (1,'mysql','Integer','int','Integer'),(2,'mysql','String','varchar','String'),(3,'mysql','Float','float','Float'),(4,'mysql','Datetime','timestamp','Timestamp'),(5,'mysql','Richtext','longtext','String'),(6,'mysql','Date','date','Date');
/*!40000 ALTER TABLE `data_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `db_info`
--

DROP TABLE IF EXISTS `db_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `db_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `driverClass` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `db_info`
--

LOCK TABLES `db_info` WRITE;
/*!40000 ALTER TABLE `db_info` DISABLE KEYS */;
INSERT INTO `db_info` VALUES (1,'mysql','com.mysql.jdbc.Driver');
/*!40000 ALTER TABLE `db_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entity`
--

DROP TABLE IF EXISTS `entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entity` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `label` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `projectId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entity`
--

LOCK TABLES `entity` WRITE;
/*!40000 ALTER TABLE `entity` DISABLE KEYS */;
INSERT INTO `entity` VALUES (1,'blog','博客',1);
/*!40000 ALTER TABLE `entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `field`
--

DROP TABLE IF EXISTS `field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `field` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `isPrimaryKey` tinyint(4) DEFAULT '0',
  `isSearchable` tinyint(4) DEFAULT '1',
  `longness` int(10) DEFAULT '32',
  `scale` int(11) DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `entityId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` VALUES (1,'id','id',1,0,0,NULL,'Integer',1),(2,'title','标题',0,1,32,NULL,'String',1),(3,'publishTime','发布时间',0,1,0,NULL,'Datetime',1),(4,'score','评分',0,1,32,NULL,'Float',1),(5,'content','内容',0,0,0,NULL,'Richtext',1);
/*!40000 ALTER TABLE `field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'jfinalweb','基于jfinal的web项目');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups_task_relation`
--

DROP TABLE IF EXISTS `groups_task_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_task_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupsId` int(11) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_task_relation`
--

LOCK TABLES `groups_task_relation` WRITE;
/*!40000 ALTER TABLE `groups_task_relation` DISABLE KEYS */;
INSERT INTO `groups_task_relation` VALUES (19,1,20),(20,1,18),(21,1,17),(22,1,16),(23,1,15),(24,1,14),(25,1,13),(26,1,11),(27,1,10),(28,1,9),(29,1,8),(30,1,7),(31,1,6),(32,1,5),(33,1,4),(34,1,3),(35,1,2),(36,1,1);
/*!40000 ALTER TABLE `groups_task_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `viewFramework` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci DEFAULT 'dwz',
  `ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '127.0.0.1',
  `port` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '8080',
  `dbType` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'mysql',
  `jdbcurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'root',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'root',
  `packageName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `viewEngine` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'freemarker',
  `management` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'gradle',
  `groupsId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'demo','生成','dwz','127.0.0.1','8080','mysql','jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8','root','root','com.leonzhou.test','freemarker','gradle',1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskname` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `taskType` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `folder` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `filename` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `templatePath` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `valid` tinyint(1) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'model','multiple','\"src/main/java/\"+project.packageName.replaceAll(\"\\\\.\",\"/\")+\"/\"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase())+\".java\"','\"java/model.ftl\"',1),(2,'ddl','single','\"sql\"','project.name + \".sql\"','\"db/\" + project.dbType + \"/ddl.ftl\"',1),(3,'appconfig','single','\"src/main/java/\" + project.packageName.replaceAll(\"\\\\.\", \"/\")','TaskKit.capitalize(project.name) + \"Config.java\"','\"java/config.ftl\"',1),(4,'config','single','\"src/main/resources\"','\"config.txt\"','\"config/txt.ftl\"',1),(5,'webxml','single','\"src/main/webapp/WEB-INF\"','\"web.xml\"','\"config/web.ftl\"',1),(6,'indexController','single','\"src/main/java/\"+project.packageName.replaceAll(\"\\\\.\", \"/\")','\"IndexController.java\"','\"java/index.ftl\"',1),(7,'validate','multiple','\"src/main/java/\"+project.packageName.replaceAll(\"\\\\.\", \"/\") + \"/\"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase()) + \"Validator.java\"','\"java/validator.ftl\"',1),(8,'controller','multiple','\"src/main/java/\"+project.packageName.replaceAll(\"\\\\.\", \"/\") + \"/\"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase()) + \"Controller.java\"','\"java/controller.ftl\"',1),(9,'add','multiple','\"src/main/webapp/\"+entity.name.toLowerCase()','\"add.html\"','\"view/\" + project.viewFramework + \"/\"+project.viewEngine+ \"/add.ftl\"',1),(10,'log4j','single','\"src/main/resources\"','\"log4j.properties\"','\"config/log4j.ftl\"',1),(11,'edit','multiple','\"src/main/webapp/\"+entity.name.toLowerCase()','\"edit.html\"','\"view/\" + project.viewFramework + \"/\" + project.viewEngine + \"/edit.ftl\"',1),(13,'index','single','\"src/main/webapp\"','\"index.html\"','\"view/\" + project.viewFramework + \"/\" + project.viewEngine + \"/index.ftl\"',1),(14,'menu','single','\"src/main/webapp\"','\"menu.html\"','\"view/\" + project.viewFramework + \"/\" +project.viewEngine+ \"/menu.ftl\"',1),(15,'form','multiple','\"src/main/webapp/\"+entity.name.toLowerCase()','\"_form.html\"','\"view/\" + project.viewFramework + \"/\" +project.viewEngine+ \"/_form.ftl\"',1),(16,'page','multiple','\"src/main/webapp/\"+entity.name.toLowerCase()','\"index.html\"','\"view/\" + project.viewFramework + \"/\" + project.viewEngine + \"/page.ftl\"',1),(17,'view','multiple','\"src/main/webapp/\"+entity.name.toLowerCase()','\"view.html\"','\"view/\" + project.viewFramework + \"/\" + project.viewEngine + \"/view.ftl\"',1),(18,'searcher','multiple','\"src/main/java/\" + project.packageName.replaceAll(\"\\\\.\", \"/\") + \"/\"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase()) + \"Searcher.java\"','\"java/searcher.ftl\"',1),(20,'build','single','\"\"','TaskKit.buildFileName(project.management)','\"build/\"+project.management+\".ftl\"',1);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_param`
--

DROP TABLE IF EXISTS `task_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `expression` varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `taskId` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_param`
--

LOCK TABLES `task_param` WRITE;
/*!40000 ALTER TABLE `task_param` DISABLE KEYS */;
INSERT INTO `task_param` VALUES (4,'_accessor','0','1'),(5,'_conversionPattern','\"[%-5p] %d{yyyy-MM-dd HH:mm:ss} method:%l%n%m%n\"','10');
/*!40000 ALTER TABLE `task_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_engine`
--

DROP TABLE IF EXISTS `view_engine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view_engine` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_engine`
--

LOCK TABLES `view_engine` WRITE;
/*!40000 ALTER TABLE `view_engine` DISABLE KEYS */;
INSERT INTO `view_engine` VALUES (1,'freemarker');
/*!40000 ALTER TABLE `view_engine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_framework`
--

DROP TABLE IF EXISTS `view_framework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view_framework` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_framework`
--

LOCK TABLES `view_framework` WRITE;
/*!40000 ALTER TABLE `view_framework` DISABLE KEYS */;
INSERT INTO `view_framework` VALUES (1,'dwz');
/*!40000 ALTER TABLE `view_framework` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-05 22:17:57
