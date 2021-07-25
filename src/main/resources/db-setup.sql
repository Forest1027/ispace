CREATE DATABASE  IF NOT EXISTS `ispace_dev`;
USE `ispace_dev`;

DROP TABLE IF EXISTS `isp_article_detail`;

--
-- Table structure for table `user_info`
--
DROP TABLE IF EXISTS `isp_user_info`;

CREATE TABLE `isp_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `signature` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) NOT NULL,
  `email_verified` boolean DEFAULT false,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `isp_article_category`
--
DROP TABLE IF EXISTS `isp_article_category`;

CREATE TABLE `isp_article_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `parent_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `isp_article_detail`
--

CREATE TABLE `isp_article_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `content` longtext NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `tag` varchar(60) DEFAULT NULL,
  `author_id` int(11) NOT NULL,
  `create_time` datetime Not Null DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime Not Null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`author_id`) REFERENCES isp_user_info(id),
  FOREIGN KEY (`category_id`) REFERENCES isp_article_category(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `isp_article_category` VALUES 
	(1,'Arts & Entertainment',null),
	(2,'Art',1),
	(3,'Books',1),
	(4,'Comics',1),
	(5,'Film',1),
    (6,'Gaming',1),
    (7,'Music',1),
    (8,'Podcasts',1),
    (9,'TV',1),
    (10,'Other',1),
    (11,'Culture',null),
    (12,'Food',11),
    (13,'Language',11),
    (14,'Pets',11),
    (15,'Sports',11),
    (16,'Philosophy',11),
    (17,'Style',11),
    (18,'Travel',11),
    (19,'Sports',11),
    (20,'Other',11),
    (21,'Health',null),
    (22,'Addiction',21),
    (23,'Cornavirus',21),
    (24,'Fitness',21),
    (25,'Mental Health',21),
    (26,'Other',21),
    (27,'Industry',null),
    (28,'Business',27),
    (29,'Design',27),
    (30,'Economy',27),
    (31,'Leadership',27),
    (32,'Marketing',27),
    (33,'Work',27),
    (34,'Other',27),
    (35,'Personal Development',null),
    (36,'Creativity',35),
    (37,'Mindfulness',35),
    (38,'Money',35),
    (39,'Productivity',35),
    (40,'Other',35),
    (41,'Society',null),
    (42,'History',41),
    (43,'Cities',41),
    (44,'Social Media',41),
    (45,'Religion',41),
    (46,'Education',41),
    (47,'Other',41),
    (48,'Other',null)
    ;

