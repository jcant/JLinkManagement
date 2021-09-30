
DROP TABLE IF EXISTS `hibernate_sequence`;
DROP TABLE IF EXISTS `jadvertising`;
DROP TABLE IF EXISTS `jroot_link`;


DROP TABLE IF EXISTS `jlink_click`;
DROP TABLE IF EXISTS `jarticle`;
DROP TABLE IF EXISTS `juser_log`;
DROP TABLE IF EXISTS `juser_payments_log`;


DROP TABLE IF EXISTS `jlink`;
DROP TABLE IF EXISTS `juser`;



CREATE TABLE `juser` (
  `id` bigint NOT NULL,
  `blocked` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reset_password` bit(1) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `juser` WRITE;
/*!40000 ALTER TABLE `juser` DISABLE KEYS */;
INSERT INTO `juser` VALUES (1,_binary '\0',NULL,'admin',NULL,'$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.',_binary '\0','ADMIN'),(2,_binary '\0',NULL,'user',NULL,'$2a$10$mvuMNa9iOkNJK1LyWLPj9uh.xaICWGjC78iRZkkdF9auHDjZLbjx.',_binary '\0','USER');
/*!40000 ALTER TABLE `juser` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `jlink` (
  `id` bigint NOT NULL,
  `enabled` bit(1) NOT NULL,
  `finish_date` datetime(6) NOT NULL,
  `free` bit(1) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `target` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc86ugt1e9pswxq2jigk6j8onk` (`user_id`),
  CONSTRAINT `FKc86ugt1e9pswxq2jigk6j8onk` FOREIGN KEY (`user_id`) REFERENCES `juser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `jlink` WRITE;
/*!40000 ALTER TABLE `jlink` DISABLE KEYS */;
INSERT INTO `jlink` VALUES 
(11,_binary '','2021-11-30 00:00:01.707000',_binary '','2021-09-29 00:00:01.698000','google.com/search?q=java+spring','short1.jca/qqwwee',1),
(12,_binary '','2021-11-30 00:00:01.707000',_binary '\0','2021-09-29 00:00:01.698000','www.linkedin.com/in/anton-isaev','short1.jca/adminlink',1),
(13,_binary '','2021-11-30 00:00:01.707000',_binary '\0','2021-09-29 00:00:01.698000','github.com/jcant','owesome.short1.jca',1),
(14,_binary '','2021-11-30 00:00:01.707000',_binary '','2021-09-29 00:00:01.698000','google.com/search?q=oop+principles','short2.jca/aassdd',2),
(15,_binary '','2021-11-30 00:00:01.707000',_binary '\0','2021-09-29 00:00:01.698000','github.com/jcant','short2.jca/userlink',2),
(16,_binary '','2021-11-30 00:00:01.707000',_binary '\0','2021-09-29 00:00:01.698000','www.linkedin.com/in/anton-isaev','super.short2.jca',2);
/*!40000 ALTER TABLE `jlink` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (27);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `jadvertising` (
  `id` bigint NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `pub_finish` datetime(6) DEFAULT NULL,
  `pub_start` datetime(6) DEFAULT NULL,
  `text` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `jadvertising` WRITE;
/*!40000 ALTER TABLE `jadvertising` DISABLE KEYS */;
INSERT INTO `jadvertising` VALUES 
  (7,'jCant Graduate Project','Link Management',NULL,NULL,'Project sources!!!: <a target=\"_blank\" href=\"https://github.com/jcant/JLinkManagement\"> GitHub</a>'),
  (8,'prog.kiev.ua','Java Courses',NULL,NULL,'excellent Java Academy<br><a target=\"_blank\" href=\"https://prog.kiev.ua/\">https://prog.kiev.ua</a>');
/*!40000 ALTER TABLE `jadvertising` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `jroot_link` (
  `id` bigint NOT NULL,
  `enabled` bit(1) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2gx5qobeqp6xicupnkemfet9g` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `jroot_link` WRITE;
/*!40000 ALTER TABLE `jroot_link` DISABLE KEYS */;
INSERT INTO `jroot_link` VALUES 
(9,_binary '','short1.jca'),
(10,_binary '\0','short2.jca');
/*!40000 ALTER TABLE `jroot_link` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `jarticle` (
  `id` bigint NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `pub_finish` datetime(6) DEFAULT NULL,
  `pub_start` datetime(6) DEFAULT NULL,
  `text` varchar(10000) DEFAULT NULL,
  `author` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKleyl972uhq02ow57vhccecr9h` (`author`),
  CONSTRAINT `FKleyl972uhq02ow57vhccecr9h` FOREIGN KEY (`author`) REFERENCES `juser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `jarticle` WRITE;
/*!40000 ALTER TABLE `jarticle` DISABLE KEYS */;
INSERT INTO `jarticle` VALUES 
  (3,'2021-09-30 10:33:30.447000','Weclome!',NULL,NULL,'It\'s good to see you here!<br>This is a test project of the redirect platform. You can try the user and admin functionality, as well as the basic <strong>redirect functionality.</strong><br></br>Registration of a new user is disabled<br>Creation of a new links is disabled<br>You can log in either as administrator or as ordinaly user',1),
  (4,'2021-09-30 10:33:30.447000','Admin',NULL,NULL,'to Auth as an ADMIN, please input:<br><br>login: <strong>admin</strong><br>password: <strong>password</strong>',1),
  (5,'2021-09-30 10:33:30.447000','User',NULL,NULL,'to Auth as an USER, please input:<br><br>login: <strong>user</strong><br>password: <strong>password</strong>',1),
  (6,'2021-09-30 10:33:30.447000','Redirecting',NULL,NULL,'FreeLink (owner admin) <a target=\"_blank\" href=\"short1.jca:10000/qqwwee\"><strong>https://short1.jca/qqwwee</strong></a> => google.com/search?q=java+spring<br>PaidLink (owner admin) <a target=\"_blank\" href=\"short1.jca:10000/adminlink\"><strong>https://short1.jca/adminlink</strong></a> => www.linkedin.com/in/anton-isaev<br>PaidLink (owner admin) <a target=\"_blank\" href=\"owesome.short1.jca:10000\"><strong>https://owesome.short1.jca</strong></a> => github.com/jcant<br>FreeLink (owner user) <a target=\"_blank\" href=\"short2.jca:10000/aassdd\"><strong>https://short2.jca/aassdd</strong></a> => google.com/search?q=oop+principles<br>PaidLink (owner user) <a target=\"_blank\" href=\"short2.jca:10000/userlink\"><strong>https://short2.jca/userlink</strong></a> => github.com/jcant<br>PaidLink (owner user) <a target=\"_blank\" href=\"super.short2.jca:10000\"><strong>https://super.short2.jca</strong></a> => www.linkedin.com/in/anton-isaev<br>',1);
/*!40000 ALTER TABLE `jarticle` ENABLE KEYS */;
UNLOCK TABLES;


CREATE TABLE `jlink_click` (
  `id` bigint NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `link_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4af468aeeph3dk7xw96veh30p` (`link_id`),
  CONSTRAINT `FK4af468aeeph3dk7xw96veh30p` FOREIGN KEY (`link_id`) REFERENCES `jlink` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `juser_log` (
  `id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9kyxgrow3pd40vpuf7pbe87nb` (`user_id`),
  CONSTRAINT `FK9kyxgrow3pd40vpuf7pbe87nb` FOREIGN KEY (`user_id`) REFERENCES `juser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `juser_payments_log` (
  `id` bigint NOT NULL,
  `amount` double NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `pay_system` varchar(255) DEFAULT NULL,
  `link_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2kbmeth2wq25cxdadg322omk7` (`link_id`),
  KEY `FKfgkg2dtv1xvp4x1a923xxorth` (`user_id`),
  CONSTRAINT `FK2kbmeth2wq25cxdadg322omk7` FOREIGN KEY (`link_id`) REFERENCES `jlink` (`id`),
  CONSTRAINT `FKfgkg2dtv1xvp4x1a923xxorth` FOREIGN KEY (`user_id`) REFERENCES `juser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
