-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: teacher_files
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.19.04.1

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
-- Table structure for table `academic_paper`
--

DROP TABLE IF EXISTS `academic_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_paper` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(255) DEFAULT NULL,
  `paper_type` int(11) DEFAULT NULL,
  `paper_title` varchar(255) DEFAULT NULL,
  `teacher_type` int(11) DEFAULT NULL,
  `sign_unit` int(11) DEFAULT NULL,
  `periodical_name` varchar(255) DEFAULT NULL,
  `periodical_number` varchar(255) DEFAULT NULL,
  `publish_time` varchar(255) DEFAULT NULL,
  `member_code` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `paper_grade_id` int(11) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `item_member` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paper_id`),
  KEY `FK1sjhyo03rhqqnwrj9h9o29cvn` (`paper_grade_id`),
  KEY `FK5sqfeufl9788mchnf2owsuyf3` (`teacher_id`),
  CONSTRAINT `FK1sjhyo03rhqqnwrj9h9o29cvn` FOREIGN KEY (`paper_grade_id`) REFERENCES `academic_paper_grade` (`id`),
  CONSTRAINT `FK5sqfeufl9788mchnf2owsuyf3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='学术论文';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_paper`
--

LOCK TABLES `academic_paper` WRITE;
/*!40000 ALTER TABLE `academic_paper` DISABLE KEYS */;
INSERT INTO `academic_paper` VALUES (1,'工科“中职生本”人才培养模式探讨',1,'',1,1,'教育教学论坛','','2019-09-30','17041','莫智懿',1,11,'2019-10-30',NULL),(2,'指纹在高校考生身份识别中的应用研究',1,'',1,1,'数学技术与应用','','2017-10-30','17011','陈佳',43,11,'2019-10-30',NULL),(3,'TPACK带给高校教师教学改革的启发——以梧州学院“计算机文化基础”课程教学为例',1,'',1,1,'梧州学院学报','','2016-10-30','17011','陈佳',43,11,'2019-10-30',NULL),(4,'教育信息化发展带给高校教师的挑战及应对建议',1,'',1,1,'亚太教育','','2016-10-30','17011','陈佳',43,11,'2019-10-30',NULL),(5,'以培养独立思考能力的课程改革探索——以《计算机文化基础》课程为例',1,'',1,1,'教育教学论坛','','2016-10-30','17049','汪梅',41,11,'2019-10-30',NULL),(6,'注意力经济对高校课堂教学的启发',1,'',1,1,'梧州学院学报','','2018-10-30','17132','卿海军',52,11,'2019-10-30',NULL);
/*!40000 ALTER TABLE `academic_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `academic_paper_annex`
--

DROP TABLE IF EXISTS `academic_paper_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_paper_annex` (
  `paper_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `paper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`paper_annex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='论文附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_paper_annex`
--

LOCK TABLES `academic_paper_annex` WRITE;
/*!40000 ALTER TABLE `academic_paper_annex` DISABLE KEYS */;
INSERT INTO `academic_paper_annex` VALUES (1,'D:/tomcat_annex/teacher/annex/54561ddc-57d0-46b5-bd29-5ad83f743937.docx','工科“中职生本”人才培养模式探讨.docx','.docx',1),(2,'D:/tomcat_annex/teacher/annex/6b7cba4e-c0db-453f-a0a4-1aaefe3e2a78.docx','指纹在高校考生身份识别中的应用研究.docx','.docx',2),(3,'D:/tomcat_annex/teacher/annex/dfaf3449-7d3c-4ce1-9f68-97f88d303bd3.docx','TPACK带给高校教师教学改革的启发.docx','.docx',3),(4,'D:/tomcat_annex/teacher/annex/2a97d2e0-a853-49aa-b2ae-95ce7a83bcd1.docx','教育信息化发展带给高校教师的挑战及应对建议.docx','.docx',4),(5,'D:/tomcat_annex/teacher/annex/e7103cd8-57a1-4a4f-9513-e9c74bd1fbea.docx','以培养独立思考能力的课程改革探索.docx','.docx',5),(6,'D:/tomcat_annex/teacher/annex/d79f3232-87ed-4326-a5b3-e59e9b851a4e.docx','注意力经济对高校课堂教学的启发.docx','.docx',6);
/*!40000 ALTER TABLE `academic_paper_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `academic_paper_grade`
--

DROP TABLE IF EXISTS `academic_paper_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_paper_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='论文等级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_paper_grade`
--

LOCK TABLES `academic_paper_grade` WRITE;
/*!40000 ALTER TABLE `academic_paper_grade` DISABLE KEYS */;
INSERT INTO `academic_paper_grade` VALUES (1,'国家级'),(2,'省级'),(3,'SCI2区'),(4,'SCI3区'),(5,'SCI4区'),(6,'EI检索期刊论文JA类型'),(7,'ISTP、EI检索会议论文CA类型'),(8,'梧州学院核心A级'),(9,'梧州学院核心B级'),(10,'梧州学院核心C级'),(11,'其它');
/*!40000 ALTER TABLE `academic_paper_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `prize_time` varchar(255) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `awardee` varchar(255) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `prize_level_id` int(11) DEFAULT NULL,
  `prize_grade_id` int(11) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `prize_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKn70xtf8crnb18k56rn59g309g` (`prize_grade_id`),
  KEY `FKi0a9r7x0qpmpxk820ewvord6r` (`prize_level_id`),
  KEY `FKne1gn9du4mvxnp7ckofdu965h` (`teacher_id`),
  KEY `FKn38nn82aimqbwwbkks5gunm6s` (`match_id`),
  CONSTRAINT `FKi0a9r7x0qpmpxk820ewvord6r` FOREIGN KEY (`prize_level_id`) REFERENCES `competition_prize_level` (`id`),
  CONSTRAINT `FKn38nn82aimqbwwbkks5gunm6s` FOREIGN KEY (`match_id`) REFERENCES `sys_match` (`match_id`),
  CONSTRAINT `FKn70xtf8crnb18k56rn59g309g` FOREIGN KEY (`prize_grade_id`) REFERENCES `competition_prize_grade` (`id`),
  CONSTRAINT `FKne1gn9du4mvxnp7ckofdu965h` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='学生竞赛项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,'Wondland/仙境','2018-01-01',157291900650416,'大数据与软件工程学院','陈树贤、谢盛云、张峻玮、苏心蕊、张雨红',7,24,1,3,'2019-10-30',NULL),(2,'异地协同式虚拟摄影工作间实训系统','2018-01-01',157291900650416,'大数据与软件工程学院','欧其健、吴桂静、庞日琳、张周恒、陈伟娴',7,24,1,3,'2019-10-30',NULL),(3,'第三届中国“互联网+”大学生创新创业大赛——警校训练场景规划系统的设计与实现','2017-01-01',157291900650416,'大数据与软件工程学院','卓艳玲、王紫燕、蔡英真、郭田田、梁家漫、梁林榕、宁凯、陈志斌、庞日琳',1,24,2,2,'2019-10-30',NULL),(4,'“创青春”全国大学生创新创业大赛：梧州市艾克森文化传播有限公司','2018-01-01',157291900650416,'大数据与软件工程学院','岑彩纯、刘涛、梁思琼、庞日琳、彭臣军、马东萍、袁鸣、刘东萍、王婷婷、叶舒怡',9,24,2,1,'2019-10-30',NULL),(5,'第13届信息安全与对抗技术竞赛','2016-01-01',157291900650416,'大数据与软件工程学院','陈冠宇',10,55,1,2,'2019-10-30',NULL),(6,'第13届信息安全与对抗技术竞赛','2016-01-01',157291900650416,'大数据与软件工程学院','李运通',10,55,1,2,'2019-10-30',NULL),(7,'第13届信息安全与对抗技术竞赛','2016-01-01',157291900650416,'大数据与软件工程学院','李昭',10,55,1,2,'2019-10-30',NULL),(8,'第二届中国“互联网+”大学生创新创业大赛——基于智能应答机器人的东盟语言客服中心','2016-01-01',157291900650416,'大数据与软件工程学院','覃锋锐、李旭乔、韦海宇、董子亮、黄腾、莫惠城、农苏武、陆广泉、覃海珍、张芝惠',11,55,2,1,'2019-10-30',NULL),(9,'文化上的丝路','2017-01-01',157291900650416,'大数据与软件工程学院','覃海、王紫燕、韦雪雪、罗淇、李艳芳',12,25,2,3,'2019-10-30',NULL),(10,'百越之西，不忘初心','2018-01-01',157291900650416,'大数据与软件工程学院','张施兰、韦敏婷、何业德、陆厚圣',12,25,2,2,'2019-10-30',NULL),(11,'万众一心，攻坚克难','2018-01-01',157291900650416,'大数据与软件工程学院','尧志欣、杨宏、曾令冉、黄其铸、祝培健',12,25,2,2,'2019-10-30',NULL),(12,'我的信仰不平凡','2018-01-01',157291900650416,'大数据与软件工程学院','凌子晴、尧志欣、王桂春、李思慧、赖绍辉',12,25,2,3,'2019-10-30',NULL),(13,'校园三维全景导航沙盘','2018-01-01',157291900650416,'大数据与软件工程学院','黄盈洁、张周恒、罗国能、闭锦滔、苏心蕊',7,48,1,2,'2019-10-30',NULL),(14,'第一届广西大学生程序设计大赛','2018-01-01',157291900650416,'大数据与软件工程学院','司晓硕、易苏阳、赖善豪',13,54,2,2,'2019-10-30',NULL),(15,'第一届广西大学生程序设计大赛','2018-01-01',157291900650416,'大数据与软件工程学院','林俊光、祝培健、刘海帆',13,54,2,2,'2019-10-30',NULL),(16,'中国软件杯大学生软件设计大赛','2016-01-01',157291900650416,'大数据与软件工程学院','徐利安',14,2,1,3,'2019-10-30',NULL),(17,'中国软件杯大学生软件设计大赛','2018-01-01',157291900650416,'大数据与软件工程学院','赖绍辉',1,2,1,3,'2019-10-30',NULL),(18,'2018年全国高校物联网应用创新大赛','2018-01-01',157291900650416,'大数据与软件工程学院','潘柱文、李雅兰、陈燕芳',15,53,1,3,'2019-10-30',NULL),(19,'2018年全国高校物联网应用创新大赛','2018-01-01',157291900650416,'大数据与软件工程学院','朱春龙、陈艳玲、张婷芬',1,53,1,3,'2019-10-30',NULL),(20,'冒泡排序算法','2017-01-01',157291900650416,'大数据与软件工程学院','陈聪、农建、何高明',2,53,1,3,'2019-10-30',NULL),(21,'项目建设过程监管服务平台','2018-01-01',157291900650416,'大数据与软件工程学院','唐泽、尧志欣、廖梦青、莫东林、邓彦松',8,1,3,4,'2019-10-30',NULL),(22,'“创青春”全国大学生创新创业大赛：项目建设过程监管云服务平台','2018-01-01',157291900650416,'大数据与软件工程学院','唐泽、尧志欣、黎兆祥、莫东林、廖梦青、徐丽梅、姚如玉',9,1,2,3,'2019-10-30',NULL),(23,'“创青春”全国大学生创新创业大赛：项目建设过程监管云服务平台','2018-01-01',157291900650416,'大数据与软件工程学院','唐泽、尧志欣、黎兆祥、莫东林、廖梦青、徐丽梅、姚如玉',9,1,3,1,'2019-10-30',NULL),(24,'全国大学生数学建模大赛广西区赛','2016-01-01',157291900650416,'大数据与软件工程学院','王焕明、唐赟科、林夏连',16,18,2,3,'2019-10-30',NULL),(25,'全国大学生数学建模大赛广西区赛','2018-01-01',157291900650416,'大数据与软件工程学院','周燕婷、邓丽君、赖坚凤',16,18,2,3,'2019-10-30',NULL),(26,'第二届中国“互联网+”大学生创新创业大赛——便携式“家庭医生”','2016-01-01',157291900650416,'大数据与软件工程学院','黄昌纯、陆勇锋、陈堔、黄谟升、韦日英、廖治欢、钟培锋、杨清',11,23,2,2,'2019-10-30',NULL),(27,'第二届中国“互联网+”大学生创新创业大赛——全景导航虚拟沙盘','2016-01-01',157291900650416,'大数据与软件工程学院','梁博秋、陶熊杰、严兵兵、覃宁、陈翠莹、梁万新、钟珊珊、蒋政',11,23,2,3,'2019-10-30',NULL),(28,'WAKE UP','2018-01-01',157291900650416,'大数据与软件工程学院','何业德、凌子晴、唐春芳、韦玉婷、卢杰平',7,27,1,2,'2019-10-30',NULL),(29,'森林巡防机器人','2018-01-01',157291900650416,'大数据与软件工程学院','吴桂静、李嘉欣、黄丽蓉、李兰',1,22,1,3,'2019-10-30',NULL),(30,'《多媒体技术》','2018-01-01',157291900650416,'大数据与软件工程学院','吴飞燕',5,22,2,3,'2019-10-30',NULL),(31,'2017年全国高校物联网应用创新大赛','2017-01-01',157291900650416,'大数据与软件工程学院','李广、王玲、孙银银',15,53,1,3,'2019-10-30',NULL),(32,'2017年全国高校物联网应用创新大赛','2017-01-01',157291900650416,'大数据与软件工程学院','翟其俊、黄修镇、吕文秀',15,31,1,3,'2019-10-30',NULL),(33,'2017年全国高校物联网应用创新大赛','2017-01-01',157291900650416,'大数据与软件工程学院','李曼、范伟新、黄叶萍',1,31,2,3,'2019-10-30',NULL),(34,'另一个视角','2018-01-01',157291900650416,'大数据与软件工程学院','韦玉婷、卢杰平、唐春芳、陈宝嫣',7,26,1,1,'2019-10-30',NULL),(35,'学无止“镜”','2018-01-01',157291900650416,'大数据与软件工程学院','彭臣军、黄晋杰、李曼曼、陈小娟、梁琼元',7,56,1,3,'2019-10-30',NULL),(36,'2017年全国高校物联网应用创新大赛','2017-01-01',157291900650416,'大数据与软件工程学院','樊江飞、潘海森、邓金富',15,29,2,3,'2019-10-30',NULL),(37,'2017年全国高校物联网应用创新大赛','2017-01-01',157291900650416,'大数据与软件工程学院','韦鑫、张宇、李天林 ',15,30,2,3,'2019-10-30',NULL),(38,'全国大学生数学建模大赛广西区赛','2016-01-01',157291900650416,'大数据与软件工程学院','李冯佳、梁孟活、黎冠志',16,7,2,2,'2019-10-30',NULL),(39,'全国大学生数学建模大赛广西区赛','2016-01-01',157291900650416,'大数据与软件工程学院','包永贤、刘阁森、王翠萍',16,7,2,3,'2019-10-30',NULL),(40,'全国大学生数学建模大赛广西区赛','2016-01-01',157291900650416,'大数据与软件工程学院','梁钟铭、陈燕萍、甘嘉婷',16,8,2,3,'2019-10-30',NULL),(41,'全国大学生数学建模大赛广西区赛','2018-01-01',157291900650416,'大数据与软件工程学院','刘阁森、张燕新、李娇华',1,9,2,3,'2019-10-30',NULL),(42,'全国大学生数学建模大赛广西区赛','2016-01-01',157291900650416,'大数据与软件工程学院','彭梦如、蒙家银、陈芝华',16,16,2,2,'2019-10-30',NULL),(43,'二维图形的旋转变化','2016-01-01',157291900650416,'大数据与软件工程学院','梁菁、龚平、贺杰',3,49,2,2,'2019-10-30',NULL),(44,'冒泡排序算法','2016-01-01',157291900650416,'大数据与软件工程学院','陈聪、冀肖榆、贺杰',3,53,2,2,'2019-10-30',NULL),(45,'如何让你的计算机变得强大？','2016-01-01',157291900650416,'大数据与软件工程学院','陈佳、万励、杨秋慧',3,43,2,3,'2019-10-30',NULL),(47,'《计算机图形学》','2017-01-01',157291900650416,'大数据与软件工程学院','梁菁',4,49,2,1,'2019-10-30',NULL),(48,'2018年全国高校物联网应用创新大赛','2018-01-01',157291900650416,'大数据与软件工程学院','钟昌升、李国庆、李娇玲',15,67,1,3,'2019-10-31',NULL),(49,'2017年全国高校物联网应用创新大赛','2017-01-01',157291900650416,'大数据与软件工程学院','潘柱文、潘海森、陈雁芠',15,67,1,3,'2019-10-31',NULL),(50,'艾克森—优质IP孵化平台','2018-01-01',157291900650416,'大数据与软件工程学院','蒋晶晶、刘东萍、梁思琼、岑彩纯、马东萍、袁鸣、王婷婷、庞日琳、叶舒怡、彭臣军',17,65,2,2,'2019-10-31',NULL),(51,'第一届广西大学生程序设计大赛','2018-01-01',157291900650416,'大数据与软件工程学院','李杰成、黄森南、赖雨思',13,69,2,2,'2019-10-31',NULL),(52,'新丝路•新未来','2017-01-01',157291900650416,'大数据与软件工程学院','赵岩、刘鑫、马明轩、潘桂华、李嘉欣',12,69,2,2,'2019-10-31',NULL),(53,'测试1','2019-01-01',157291900650416,'大数据与软件工程学院','策划i',2,1,1,1,'2019-11-07',NULL);
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_annex`
--

DROP TABLE IF EXISTS `competition_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_annex` (
  `competition_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `competition_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`competition_annex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='竞赛项目附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_annex`
--

LOCK TABLES `competition_annex` WRITE;
/*!40000 ALTER TABLE `competition_annex` DISABLE KEYS */;
INSERT INTO `competition_annex` VALUES (1,1,'Wondland 仙境.png','D:/tomcat_annex/teacher/annex/3c18d9e7-f62d-4c4c-a8cf-749518800cf6.png','.png'),(2,2,'异地协同式虚拟摄影工作间.png','D:/tomcat_annex/teacher/annex/634f6131-ce76-41fa-b690-b2e9453ef030.png','.png'),(3,3,'警校训练场景规划系统的设计与实现.png','D:/tomcat_annex/teacher/annex/132d834a-7672-4bf7-9101-796154e98baa.png','.png'),(4,4,'梧州市艾克森文化传播有限公司.png','D:/tomcat_annex/teacher/annex/44d7e9ff-ce36-4922-8835-c2bddaace35c.png','.png'),(5,5,'陈冠宇13届.png','D:/tomcat_annex/teacher/annex/d6e6f3a5-0c76-4501-bc5c-e916c731b0aa.png','.png'),(6,6,'李运通：第13届信息安全与对抗技术竞赛国家.png','D:/tomcat_annex/teacher/annex/39f4ac79-420c-4e94-bad0-21764d6e7f81.png','.png'),(7,7,'李昭：第13届信息安全与对抗技术竞赛国家级二等.png','D:/tomcat_annex/teacher/annex/9f1e7c0e-4d38-42c7-a6a4-9a88dcd1a6b6.png','.png'),(8,8,'第二届中国“互联网+”大学生创新创业大赛广西选拔赛区级金奖：基于智能应答机器人的东盟语言客服中心.png','D:/tomcat_annex/teacher/annex/8de5f497-c454-4493-b227-e5d1e758f9f4.png','.png'),(9,9,'文化上的丝绸之路.png','D:/tomcat_annex/teacher/annex/457668c6-5517-4f6d-a139-e6bd2762eb52.png','.png'),(10,10,'百越之西，不忘初心.png','D:/tomcat_annex/teacher/annex/2eaa0f48-431e-4fff-aa44-f5c2941a2858.png','.png'),(11,11,'万剑一心.png','D:/tomcat_annex/teacher/annex/ed54973c-bf3b-4f56-a49b-319dfcb5d0c1.png','.png'),(12,12,'我的信任不平凡.png','D:/tomcat_annex/teacher/annex/b6eaf43b-1a47-4719-965c-2846b8e73bc7.png','.png'),(13,13,'校园三维全景导航.png','D:/tomcat_annex/teacher/annex/9a1a87cb-9b9b-41bf-9f84-23d07bfe2f74.png','.png'),(14,14,'司晓硕.png','D:/tomcat_annex/teacher/annex/636ea659-91e2-4bb9-b7b0-5165c408e7cb.png','.png'),(15,15,'林俊光.png','D:/tomcat_annex/teacher/annex/e453a38c-fd40-4f9a-8e38-b1a18d9c2c46.png','.png'),(16,16,'徐利安-2016软件杯.png','D:/tomcat_annex/teacher/annex/72cadb9a-b20f-4fc0-b1a2-63a31e8bd3a0.png','.png'),(17,17,'赖绍辉：2018年中国软件杯大学生.png','D:/tomcat_annex/teacher/annex/82988f3e-f17c-4d79-b4d4-4d4d3ecdbb89.png','.png'),(19,18,'1.jpg','D:/tomcat_annex/teacher/annex/e8264143-a41e-495b-8668-5642cee8a592.jpg','.jpg'),(21,19,'2.jpg','D:/tomcat_annex/teacher/annex/8c5d6266-c6d7-4d66-bad9-76ee64b8ea2e.jpg','.jpg'),(22,20,'第二十一届全国教育教学信息化大赛：冒泡排序算法.png','D:/tomcat_annex/teacher/annex/1ab0cf7e-9850-448a-b109-3467e916f283.png','.png'),(23,21,'项目建设过程.png','D:/tomcat_annex/teacher/annex/3b61945e-295a-4135-8fbb-ca44eb2aea9e.png','.png'),(24,22,'“创青春”全国大学生创新创业大赛区级铜奖：项目建设过程监管云服务平台.png','D:/tomcat_annex/teacher/annex/e72b0ac6-d7cc-4937-9f0e-abea75503e53.png','.png'),(25,23,'校级创新春.png','D:/tomcat_annex/teacher/annex/57bf8a0e-8b7a-469e-984a-ba4e5e116034.png','.png'),(26,24,'广西区赛三等奖——王焕明、唐赟科、林夏连.png','D:/tomcat_annex/teacher/annex/30fa6447-14d5-47ce-a15e-f6001892f89c.png','.png'),(27,25,'建模大赛广西区赛三等奖——周燕婷、邓丽君、赖坚凤.png','D:/tomcat_annex/teacher/annex/3be51e42-5555-40d4-82c9-f606f919df89.png','.png'),(28,26,'第二届中国“互联网+”大学生创新创业大赛广西选拔赛区级银奖：便捷式“家庭医生”.png','D:/tomcat_annex/teacher/annex/d348f651-688c-4db7-9687-27b63e9d27b3.png','.png'),(29,27,'第二届中国“互联网+”大学生创新创业大赛广西选拔赛区级铜奖：全景导航虚拟沙盘制作平台.png','D:/tomcat_annex/teacher/annex/ad4ba443-c950-4044-8d80-1577f9267174.png','.png'),(30,28,'WAKE UP.png','D:/tomcat_annex/teacher/annex/360b8f05-d58b-46ba-8109-a4ad96394a1e.png','.png'),(31,29,'森林巡防机器人.png','D:/tomcat_annex/teacher/annex/d942b3eb-8abd-4ccd-9e60-cbf008566369.png','.png'),(32,30,'吴飞燕.jpg','D:/tomcat_annex/teacher/annex/fccb310d-544a-4b44-b10d-6728a81d5b38.jpg','.jpg'),(33,31,'8.jpg','D:/tomcat_annex/teacher/annex/309793bb-e418-4842-aafc-41cd880d37f3.jpg','.jpg'),(34,31,'9.jpg','D:/tomcat_annex/teacher/annex/2b18b420-eade-4a1d-8d8f-dca97c2d862c.jpg','.jpg'),(35,31,'10.jpg','D:/tomcat_annex/teacher/annex/0987344e-362d-45b2-b188-78970713367a.jpg','.jpg'),(36,32,'00.jpg','D:/tomcat_annex/teacher/annex/4842ff36-41c3-4682-b230-ac933eaec9fe.jpg','.jpg'),(37,32,'0.jpg','D:/tomcat_annex/teacher/annex/76cf99fd-d9a0-40bd-a064-c953fcee0b6f.jpg','.jpg'),(38,33,'创新大赛——李曼、范伟新、黄叶萍.png','D:/tomcat_annex/teacher/annex/f62fc8ef-7437-4196-ae21-31283b0514c9.png','.png'),(39,34,'另一个视角.png','D:/tomcat_annex/teacher/annex/428c5fb3-298b-458b-9563-574fe5ba85e9.png','.png'),(40,35,'学无止镜.png','D:/tomcat_annex/teacher/annex/9c112739-bdc0-4664-bb0f-48688506f89c.png','.png'),(41,36,'创新大赛——樊江飞、潘海森、邓金富.png','D:/tomcat_annex/teacher/annex/035e74f2-4a5f-40a5-8332-9676f01faac1.png','.png'),(42,37,'创新大赛韦鑫、张宇、李天林.png','D:/tomcat_annex/teacher/annex/6c8d4c91-ee54-4810-80f8-bbd53e41712b.png','.png'),(43,38,'广西区赛二等奖——李冯佳、梁孟活、黎冠志.png','D:/tomcat_annex/teacher/annex/5894c64d-9a20-4e85-a85b-91d320bc7b61.png','.png'),(44,39,'广西区赛三等奖——包永贤、刘阁森、王翠萍.png','D:/tomcat_annex/teacher/annex/38eb61bd-83ac-494d-9fb8-f81055958c32.png','.png'),(45,40,'广西区赛三等奖——梁钟铭、陈燕萍、甘嘉婷.png','D:/tomcat_annex/teacher/annex/8da9af62-69f7-4047-94b0-0b16389c1a58.png','.png'),(46,41,'模大赛广西区赛三等奖——刘阁森、张燕新、李娇华.png','D:/tomcat_annex/teacher/annex/3fe68011-cee2-43e3-9df7-0a6b40b5fc47.png','.png'),(47,42,'彭梦如、蒙家银、陈芝华.png','D:/tomcat_annex/teacher/annex/41916132-d828-438e-9f81-a43a55c7c381.png','.png'),(48,20,'第二十一届全国教育教学信息化大赛：冒泡排序算法.png','D:/tomcat_annex/teacher/annex/6392d3e3-15c0-4d9c-bab4-4b22987415b5.png','.png'),(49,30,'吴飞燕.jpg','D:/tomcat_annex/teacher/annex/85a20421-b621-44da-b4c3-2a242fa895b7.jpg','.jpg'),(50,43,'第十六届广西高等教育教学软件应用大赛：二维图形的旋转变化.jpg','D:/tomcat_annex/teacher/annex/750e344f-be62-4663-beae-9e4cf7950a8d.jpg','.jpg'),(51,20,'第二十一届全国教育教学信息化大赛：冒泡排序算法.png','D:/tomcat_annex/teacher/annex/4c18e0c6-969c-4c67-ae61-8acc337b0d2f.png','.png'),(52,44,'第十六届广西高等教育教学软件应用大赛：冒泡排序算法.jpg','D:/tomcat_annex/teacher/annex/d0943b04-faeb-42cc-b195-5c928847446f.jpg','.jpg'),(53,45,'第十六届广西高等教育教学软件应用大赛：如何让你的计算机变得强大？.png','D:/tomcat_annex/teacher/annex/780eaef7-98da-4328-8ab5-b9dcad9cca6b.png','.png'),(54,46,'第四届全区高校青年教师教学竞赛：《计算机图形学》.jpg','D:/tomcat_annex/teacher/annex/b558b3c8-faa2-4762-914d-80704d4c2663.jpg','.jpg'),(55,47,'第四届全区高校青年教师教学竞赛：《计算机图形学》.jpg','D:/tomcat_annex/teacher/annex/c9876592-88f1-4387-9222-76c1dde98a09.jpg','.jpg'),(56,48,'1.jpg','D:/tomcat_annex/teacher/annex/6abf8ae5-e1ee-41c4-9ebd-417135c2b0f2.jpg','.jpg'),(57,48,'2.jpg','D:/tomcat_annex/teacher/annex/5e417b5f-f018-4161-ac6c-6a53dbc04bb9.jpg','.jpg'),(58,48,'3.jpg','D:/tomcat_annex/teacher/annex/da3aaa7b-d122-4640-8b8d-8ac5a02c154f.jpg','.jpg'),(59,49,'22.jpg','D:/tomcat_annex/teacher/annex/b9a4770f-60a9-4247-8743-c6c6d3b5d10c.jpg','.jpg'),(60,49,'33.jpg','D:/tomcat_annex/teacher/annex/1c0999bb-2cf2-450d-b6c8-40383368e20a.jpg','.jpg'),(61,49,'44.jpg','D:/tomcat_annex/teacher/annex/7b34e706-87c4-4c82-a237-ae3ea7dfb9ef.jpg','.jpg'),(62,50,'艾克森—优质IP孵化平台.png','D:/tomcat_annex/teacher/annex/84135647-ee62-4b9e-916a-a101ac34bb35.png','.png'),(63,51,'龚平--李杰成.png','D:/tomcat_annex/teacher/annex/612275be-7cc3-434e-9184-99d9a8e62449.png','.png'),(64,52,'新思路，新未来.png','D:/tomcat_annex/teacher/annex/051f26d2-f122-40f0-91c0-51bb90f7336f.png','.png'),(65,53,'test.png','/home/soldier/SOLDIER/tomcat_annex/teacher/annex/162c2ba1-9729-439d-bea7-3d7e3cb06a5e.png','.png'),(66,53,'大数据老师.xlsx','/home/soldier/SOLDIER/tomcat_annex/teacher/annex/402d0b6e-2782-4331-a28a-43c0cefadf65.xlsx','.xlsx');
/*!40000 ALTER TABLE `competition_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_prize_grade`
--

DROP TABLE IF EXISTS `competition_prize_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_prize_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='竞赛获奖等次';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_prize_grade`
--

LOCK TABLES `competition_prize_grade` WRITE;
/*!40000 ALTER TABLE `competition_prize_grade` DISABLE KEYS */;
INSERT INTO `competition_prize_grade` VALUES (1,'一等奖'),(2,'二等奖'),(3,'三等奖'),(4,'特等奖');
/*!40000 ALTER TABLE `competition_prize_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_prize_level`
--

DROP TABLE IF EXISTS `competition_prize_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_prize_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='竞赛获奖级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_prize_level`
--

LOCK TABLES `competition_prize_level` WRITE;
/*!40000 ALTER TABLE `competition_prize_level` DISABLE KEYS */;
INSERT INTO `competition_prize_level` VALUES (1,'国家级'),(2,'区级'),(3,'校级');
/*!40000 ALTER TABLE `competition_prize_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `dept_id` bigint(20) NOT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `dept_person` varchar(255) DEFAULT NULL,
  `dept_phone` varchar(255) DEFAULT NULL,
  `parent_dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dept_id`),
  KEY `FKhbs8yfeciw8j7sabe90qh30hl` (`parent_dept_id`),
  CONSTRAINT `FKhbs8yfeciw8j7sabe90qh30hl` FOREIGN KEY (`parent_dept_id`) REFERENCES `dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (157291900650416,'大数据与软件工程学院','黄宏本','0774-5820972',NULL),(157291900653458,'计算机科学与技术','代丽娴','0774-5820972',157291900650416),(157291900653472,'计算机基础','汪梅','0774-5820972',157291900650416),(157291900653485,'行政','龚红梅','0774-5820972',157291900650416),(157291900653505,'软件工程','何希','0774-5820972',157291900650416),(157291900653510,'数学与应用数学','覃学文','0774-5820972',157291900650416),(157291900653609,'数字媒体技术','贺杰','0774-5820972',157291900650416),(157291900653636,'物联网工程','何高明','0774-5820972',157291900650416),(157291900653640,'信息与计算科学','苏芳','0774-5820972',157291900650416),(157304277097433,'测试1','测试1','18277404022',NULL),(157304278760076,'测试1。1','测试1','18277404022',157304277097433);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`),
  KEY `FKlmald6k02kwl9t0j6ngirf2hp` (`role_id`),
  KEY `FKa426m23no7pwaeeg5vfqg7tfy` (`menu_id`),
  CONSTRAINT `FKa426m23no7pwaeeg5vfqg7tfy` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`),
  CONSTRAINT `FKlmald6k02kwl9t0j6ngirf2hp` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1,6,1,'系统管理员'),(2,6,2,'系统管理员'),(3,6,3,'系统管理员'),(4,6,4,'系统管理员'),(5,6,5,'系统管理员'),(7,6,7,'系统管理员'),(8,6,8,'系统管理员'),(9,6,9,'系统管理员'),(10,6,10,'系统管理员'),(11,6,11,'系统管理员'),(12,6,12,'系统管理员'),(50,1,6,'公共的'),(51,2,6,'公共的'),(52,3,6,'公共的'),(53,4,6,'公共的'),(54,5,6,'公共的'),(55,6,6,'公共的'),(100,5,100,'部门管理员'),(101,5,101,'部门管理员'),(102,5,102,'部门管理员'),(103,5,103,'部门管理员'),(104,5,104,'部门管理员'),(105,5,105,'部门管理员'),(106,5,106,'部门管理员'),(107,5,107,'部门管理员'),(108,5,108,'部门管理员'),(200,4,200,'普通老师'),(201,4,201,'普通老师'),(202,4,202,'普通老师'),(203,4,203,'普通老师'),(204,4,204,'普通老师'),(205,4,205,'普通老师'),(206,4,206,'普通老师'),(300,1,300,'学院院长'),(301,1,301,'学院院长'),(302,1,302,'学院院长'),(303,1,303,'学院院长'),(304,1,304,'学院院长'),(305,1,305,'学院院长'),(306,1,306,'学院院长'),(307,1,307,'学院院长'),(308,1,308,'学院院长'),(333,2,300,'学院副院长'),(334,2,301,'学院副院长'),(335,2,302,'学院副院长'),(336,2,303,'学院副院长'),(337,2,304,'学院副院长'),(338,2,305,'学院副院长'),(339,2,306,'学院副院长'),(340,2,307,'学院副院长'),(341,2,308,'学院副院长'),(366,3,300,'学院书记'),(367,3,301,'学院书记'),(368,3,302,'学院书记'),(369,3,303,'学院书记'),(370,3,304,'学院书记'),(374,3,305,'学院书记'),(375,3,306,'学院书记'),(376,3,307,'学院书记'),(377,3,308,'学院书记');
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_code` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_phone` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `job_content` varchar(255) DEFAULT NULL COMMENT '项目分工',
  `is_person` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='竞赛学生信息表--弃用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'20160007261616','卢秋树','暂无','暂无','暂无','暂无','暂无',1,1,'“绿”动大学'),(2,'20160007261616','容传毅','暂无','暂无','暂无','暂无','暂无',0,1,'“绿”动大学'),(3,'20160007261616','黄伟印','暂无','暂无','暂无','暂无','暂无',0,1,'“绿”动大学'),(4,'20160007261616','吴枝晏','暂无','暂无','暂无','暂无','暂无',1,2,'5 GreenS'),(5,'20160007261616','廖治欢','暂无','暂无','暂无','暂无','暂无',1,3,'《丢》'),(6,'20160007261616','覃宁','暂无','暂无','暂无','暂无','暂无',1,4,'一念之差，一步之遥');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_match`
--

DROP TABLE IF EXISTS `sys_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_match` (
  `match_id` int(11) NOT NULL AUTO_INCREMENT,
  `match_name` varchar(255) DEFAULT NULL,
  `match_level` int(11) DEFAULT NULL,
  `match_type` int(11) DEFAULT NULL,
  `organizer` varchar(255) DEFAULT NULL,
  `contractor` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `match_attribute` int(11) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`match_id`),
  KEY `FK5clc6now9tplrvticej8wp42k` (`teacher_id`),
  CONSTRAINT `FK5clc6now9tplrvticej8wp42k` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='赛事管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_match`
--

LOCK TABLES `sys_match` WRITE;
/*!40000 ALTER TABLE `sys_match` DISABLE KEYS */;
INSERT INTO `sys_match` VALUES (1,'中国大学生计算机设计大赛',1,1,'中国大学生计算机设计大赛组织委员会','梧州学院',1,1,NULL),(2,'第二十一届全国教育教学信息化大赛',1,2,'暂无','暂无',1,2,NULL),(3,'第十六届广西高等教育教学软件应用大赛',2,2,'暂无','暂无',1,2,NULL),(4,'第四届全区高校青年教师教学竞赛',2,2,'暂无','暂无',1,2,NULL),(5,'第五届全区高校青年教师教学竞赛',2,2,'暂无','暂无',1,2,NULL),(6,'全国软件和信息技术专业人才大赛',1,1,'暂无','暂无',1,2,NULL),(7,'第六届全国大学生数字媒体科技作品及创意竞赛',1,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(8,'第三届中国“互联网+”大学生创新创业大赛',1,3,'梧州学院','梧州学院',1,1,'2019-10-30'),(9,'2018年“创青春”全国大学生创新创业大赛',1,3,'梧州学院','梧州学院',1,1,'2019-10-30'),(10,'全国信息安全与对抗技术竞赛',1,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(11,'第二届中国“互联网+”大学生创新创业大赛',1,3,'梧州学院','梧州学院',1,1,'2019-10-30'),(12,'广西高校计算机应用大赛',2,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(13,'广西大学生程序设计大赛',2,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(14,'中国软件杯大学生软件设计大赛',1,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(15,'全国高校物联网应用创新大赛',1,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(16,'全国大学生数学建模大赛',1,1,'梧州学院','梧州学院',1,1,'2019-10-30'),(17,'第四届中国“互联网+”大学生创新创业大赛',1,3,'梧州学院','梧州学院',1,1,'2019-10-31');
/*!40000 ALTER TABLE `sys_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_match_annex`
--

DROP TABLE IF EXISTS `sys_match_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_match_annex` (
  `match_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`match_annex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赛事附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_match_annex`
--

LOCK TABLES `sys_match_annex` WRITE;
/*!40000 ALTER TABLE `sys_match_annex` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_match_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` tinyint(4) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'竞赛管理','&#xe66c;','',0,'系统管理员'),(2,'教师项目管理','&#xe6b2;','',0,'系统管理员'),(3,'学术论文管理','&#xe705;','',0,'系统管理员'),(4,'教师档案管理','&#xe613;','',0,'系统管理员'),(5,'部门管理','&#xe631;','',0,'系统管理员'),(6,'基础数据管理','&#xe6b1;','',0,'公共的'),(7,'菜单管理','&#xe656;','',0,'系统管理员'),(8,'角色列表','&#xe66f;','',0,'系统管理员'),(9,'教师角色管理','&#xe672;','',0,'系统管理员'),(10,'教材管理','&#xe655;','',0,'系统管理员'),(11,'教学成果管理','&#xe62e;','',0,'系统管理员'),(12,'教师荣誉管理','&#xe604;','',0,'系统管理员'),(100,'竞赛管理','&#xe66c;','',0,'部门管理员'),(101,'教师项目管理','&#xe6b2;','',0,'部门管理员'),(102,'学术论文管理','&#xe705;','',0,'部门管理员'),(103,'教师档案管理','&#xe613;','',0,'部门管理员'),(104,'部门管理','&#xe631;','',0,'部门管理员'),(105,'教师角色管理','&#xe672;','',0,'部门管理员'),(106,'教材管理','&#xe655;','',0,'部门管理员'),(107,'教学成果管理','&#xe62e;','',0,'部门管理员'),(108,'教师荣誉管理','&#xe604;','',0,'部门管理员'),(200,'竞赛管理','&#xe66c;','',0,'普通老师'),(201,'教师项目管理','&#xe6b2;','',0,'普通老师'),(202,'学术论文管理','&#xe705;','',0,'普通老师'),(203,'教师档案管理','&#xe613;','',0,'普通老师'),(204,'教材管理','&#xe655;','',0,'普通老师'),(205,'教学成果管理','&#xe62e;','',0,'普通老师'),(206,'教师荣誉管理','&#xe604;','',0,'普通老师'),(300,'竞赛管理','&#xe66c;','',0,'领导'),(301,'教师项目管理','&#xe6b2;','',0,'领导'),(302,'学术论文管理','&#xe705;','',0,'领导'),(303,'教师档案管理','&#xe613;','',0,'领导'),(304,'部门管理','&#xe631;','',0,'领导'),(305,'教师角色管理','&#xe672;','',0,'领导'),(306,'教材管理','&#xe655;','',0,'领导'),(307,'教学成果管理','&#xe62e;','',0,'领导'),(308,'教师荣誉管理','&#xe604;','',0,'领导');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_children`
--

DROP TABLE IF EXISTS `sys_menu_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` tinyint(4) DEFAULT NULL,
  `menu_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3x9iahy8ma4rxeg4b7p9bpalg` (`menu_menu_id`),
  CONSTRAINT `FK3x9iahy8ma4rxeg4b7p9bpalg` FOREIGN KEY (`menu_menu_id`) REFERENCES `sys_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` VALUES (1,'竞赛获奖列表','&#xe602;','/teacher/page/system/competition/competition-list.html',0,1),(2,'教师项目列表','&#xe602;','/teacher/page/system/teacherItem/item-list.html',0,2),(3,'学术论文列表','&#xe602;','/teacher/page/system/academicPaper/paper-list.html',0,3),(4,'教师档案列表','&#xe602;','/teacher/page/system/teacher/teacher-list.html',0,4),(5,'部门列表','&#xe602;','/teacher/page/system/dept/dept-list.html',0,5),(6,'教师列表','&#xe602;','/teacher/page/system/teacherRole/teacher-list.html',0,9),(7,'角色列表','&#xe602;','/teacher/page/system/sysrole/role-list.html',0,8),(8,'系统菜单','&#xe602;','/teacher/page/system/sysMenu/menu-list.html',0,7),(9,'二级菜单','&#xe602;','/teacher/page/system/sysMenu/children/menu-children-list.html',0,7),(10,'论文等级管理','&#xe602;','/teacher/page/system/systemBasicData/academicPaperGrade/paperGrade-list.html',0,6),(11,'赛事管理','&#xe602;','/teacher/page/system/systemBasicData/match/match-list.html',0,6),(12,'教师项目级别管理','&#xe602;','/teacher/page/system/systemBasicData/teacherItemLevel/itemLevel-list.html',0,6),(13,'教师项目类别管理','&#xe602;','/teacher/page/system/systemBasicData/teacherItemCategory/itemCategory-list.html',0,6),(14,'竞赛获奖级别管理','&#xe602;','/teacher/page/system/systemBasicData/competitionPrizeLevel/prizeLevel-list.html',0,6),(15,'竞赛获奖等次管理','&#xe602;','/teacher/page/system/systemBasicData/competitionPrizeGrade/prizeGrade-list.html',0,6),(16,'管理员列表','&#xe602;','/teacher/page/system/teacherRole/deptadmin-list.html',0,9),(17,'教材信息列表','&#xe602;','/teacher/page/system/teachingMaterial/material-list.html',0,10),(18,'教学成果列表','&#xe602;','/teacher/page/system/teachingResult/result-list.html',0,11),(19,'教师荣誉列表','&#xe612;','/teacher/page/system/teacherHonor/honor-list.html',0,12),(100,'学生竞赛获奖列表','&#xe602;','/teacher/page/system/competition/DeptAdminAndLeader/competition-stu-list.html',0,100),(101,'教师竞赛获奖列表','&#xe602;','/teacher/page/system/competition/DeptAdminAndLeader/competition-tea-list.html',0,100),(102,'部门教师项目列表','&#xe602;','/teacher/page/system/teacherItem/DeptAdminAndLeader/item-list.html',0,101),(103,'部门学术论文列表','&#xe602;','/teacher/page/system/academicPaper/DeptAdminAndLeader/paper-list.html',0,102),(104,'部门教师档案列表','&#xe602;','/teacher/page/system/teacher/DeptAdminAndLeader/teacher-list.html',0,103),(105,'教研室列表','&#xe602;','/teacher/page/system/dept/DeptAdminAndLeader/dept-list.html',0,104),(106,'部门教师列表','&#xe602;','/teacher/page/system/teacherRole/DeptAdminAndLeader/teacher-list.html',0,105),(107,'部门教材信息列表','&#xe602;','/teacher/page/system/teachingMaterial/DeptAdminAndLeader/material-list.html',0,106),(108,'部门教学成果列表','&#xe602;','/teacher/page/system/teachingResult/DeptAdminAndLeader/result-list.html',0,107),(109,'部门教师荣誉列表','&#xe602;','/teacher/page/system/teacherHonor/DeptAdminAndLeader/honor-list.html',0,108),(200,'我主持的教师竞赛','&#xe612;','/teacher/page/system/competition/my-host-list.html',0,200),(201,'我参与的教师竞赛','&#xe602;','/teacher/page/system/competition/my-join-list.html',0,200),(202,'我指导的学生竞赛','&#xe612;','/teacher/page/system/competition/my-guide-list.html',0,200),(203,'我主持的项目','&#xe612;','/teacher/page/system/teacherItem/my-host-list.html',0,201),(204,'我参与的项目','&#xe602;','/teacher/page/system/teacherItem/my-join-list.html',0,201),(205,'我的学术论文','&#xe612;','/teacher/page/system/academicPaper/my-paper-list.html',0,202),(206,'我的档案信息','&#xe612;','/teacher/page/system/teacher/my-info.html',0,203),(207,'我主编的教材列表','&#xe612;','/teacher/page/system/teachingMaterial/my-editor-list.html',0,204),(208,'我主持的教学成果','&#xe612;','/teacher/page/system/teachingResult/my-host-list.html',0,205),(209,'我参与的教学成果','&#xe602;','/teacher/page/system/teachingResult/my-join-list.html',0,205),(210,'我的个人荣誉列表','&#xe612;','/teacher/page/system/teacherHonor/my-honor-list.html',0,206),(211,'我参与的教师荣誉','&#xe602;','/teacher/page/system/teacherHonor/my-join-list.html',0,206),(300,'学生竞赛获奖列表','&#xe602;','/teacher/page/system/competition/DeptAdminAndLeader/competition-stu-list.html',0,300),(301,'教师竞赛获奖列表','&#xe602;','/teacher/page/system/competition/DeptAdminAndLeader/competition-tea-list.html',0,300),(302,'我主持的教师竞赛','&#xe612;','/teacher/page/system/competition/my-host-list.html',0,300),(303,'我参与的教师竞赛','&#xe602;','/teacher/page/system/competition/my-join-list.html',0,300),(304,'我指导的学生竞赛','&#xe612;','/teacher/page/system/competition/my-guide-list.html',0,300),(305,'部门教师项目列表','&#xe602;','/teacher/page/system/teacherItem/DeptAdminAndLeader/item-list.html',0,301),(306,'我主持的项目','&#xe612;','/teacher/page/system/teacherItem/my-host-list.html',0,301),(307,'我参与的项目','&#xe602;','/teacher/page/system/teacherItem/my-join-list.html',0,301),(308,'部门学术论文列表','&#xe602;','/teacher/page/system/academicPaper/DeptAdminAndLeader/paper-list.html',0,302),(309,'我的学术论文','&#xe612;','/teacher/page/system/academicPaper/my-paper-list.html',0,302),(310,'部门教师档案列表','&#xe602;','/teacher/page/system/teacher/DeptAdminAndLeader/teacher-list.html',0,303),(311,'我的档案信息','&#xe612;','/teacher/page/system/teacher/my-info.html',0,303),(312,'教研室列表','&#xe602;','/teacher/page/system/dept/DeptAdminAndLeader/dept-list.html',0,304),(313,'部门教师列表','&#xe602;','/teacher/page/system/teacherRole/DeptAdminAndLeader/teacher-list.html',0,305),(314,'部门教材信息列表','&#xe602;','/teacher/page/system/teachingMaterial/DeptAdminAndLeader/material-list.html',0,306),(315,'我主编的教材列表','&#xe612;','/teacher/page/system/teachingMaterial/my-editor-list.html',0,306),(316,'部门教学成果列表','&#xe602;','/teacher/page/system/teachingResult/DeptAdminAndLeader/result-list.html',0,307),(317,'我主持的教学成果','&#xe612;','/teacher/page/system/teachingResult/my-host-list.html',0,307),(318,'我参与的教学成果','&#xe602;','/teacher/page/system/teachingResult/my-join-list.html',0,307),(319,'部门教师荣誉列表','&#xe602;','/teacher/page/system/teacherHonor/DeptAdminAndLeader/honor-list.html',0,308),(320,'我的个人荣誉列表','&#xe612;','/teacher/page/system/teacherHonor/my-honor-list.html',0,308),(321,'我参与的教师荣誉','&#xe602;','/teacher/page/system/teacherHonor/my-join-list.html',0,308);
/*!40000 ALTER TABLE `sys_menu_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `can_look` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'学院院长',1),(2,'学院副院长',1),(3,'学院书记',1),(4,'教师',1),(5,'学院管理员',0),(6,'系统管理员',0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_account` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK9kulrl1tou7sch3ym55guqnxp` (`teacher_id`),
  KEY `FK74A81DFD1B3921AC` (`teacher_id`),
  CONSTRAINT `FK74A81DFD1B3921AC` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `FK9kulrl1tou7sch3ym55guqnxp` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin','黄结\r',1,NULL),(2,'17041','123456','莫智懿\r',1,1),(3,'27105','123456','许传本',1,2),(4,'17224','123456','朱肖颖',1,3),(5,'17074','123456','庞光垚',1,4),(6,'17701','123456','蒲保兴',1,5),(7,'21016','123456','覃学文',1,6),(8,'30011','123456','赵春茹',1,7),(9,'21022','123456','覃桂茳',1,8),(10,'21011','123456','刘敏捷',1,9),(15,'21054','123456','杨甲山',1,13),(16,'21012','123456','黄劲',1,14),(17,'28009','123456','胡庆席',1,15),(18,'21005','123456','李连芬',1,16),(19,'21014','123456','石向东',1,17),(20,'30013','123456','涂井先',1,18),(21,'06003','123456','陈红',1,19),(22,'21018','123456','李志军',1,20),(23,'21017','123456','许成章',1,21),(24,'23098','123456','吴飞燕',1,22),(25,'17010','123456','贺杰',1,23),(28,'17044','123456','宫海晓',1,24),(29,'17062','123456','邸臻炜',1,25),(30,'17031','123456','李翊',1,26),(31,'17033','123456','黄筱佟',1,27),(32,'17214','123456','杨秋慧',1,28),(33,'17046','123456','何高明',1,29),(34,'17027','123456','蒋琳琼',1,30),(35,'17019','123456','李海英',1,31),(36,'17021','123456','李军',1,32),(37,'21010','123456','苏芳',1,33),(38,'21019','123456','赵贤',1,34),(39,'18074','123456','龚红梅',1,35),(40,'17005','123456','吴燕端',1,36),(41,'30008','123456','刘树先',1,37),(42,'17702','123456','邵晋芳',1,38),(43,'17703','123456','蔡敏仪',1,39),(44,'17704','123456','曾雨珊',1,40),(45,'17049','123456','汪梅',1,41),(46,'17020','123456','李健',1,42),(47,'17011','123456','陈佳',1,43),(48,'17022','123456','黄洁明',1,44),(49,'27013','123456','梁朝湘',1,45),(50,'17064','123456','吴芳',1,46),(51,'17018','123456','代丽娴',1,47),(52,'07003','123456','黄宏本',1,48),(53,'17037','123456','梁菁',1,49),(54,'31047','123456','李宗妮',1,50),(55,'17202','123456','农健',1,51),(56,'17132','123456','卿海军',1,52),(57,'17039','123456','陈聪',1,53),(58,'17038','123456','卢雪燕',1,54),(59,'17133','123456','黄寄洪',1,55),(60,'17213','123456','郑明',1,56),(61,'31046','123456','何希',1,57),(62,'17025','123456','吴炎桃',1,58),(63,'17221','123456','陈悦',1,59),(64,'17134','123456','冀肖榆',1,60),(65,'17007','123456','冀占江',1,61),(67,'dsjxy_admin','admin','大数据与软件工程学院管理员',1,63),(68,'sys_admin','admin','系统管理员',1,64),(69,'17001','123456','甘金明',1,65),(70,'17004','123456','陆科达',1,66),(71,'17006','123456','卢在盛',1,67),(72,'17042','123456','彭金虎',1,68),(73,'17003','123456','龚平',1,69),(74,'cs_admin','123456','测试学院管理员',1,70);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_code` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_sex` int(11) DEFAULT NULL,
  `teacher_birth` date DEFAULT NULL,
  `entry_time` date DEFAULT NULL,
  `teacher_img` varchar(255) DEFAULT NULL,
  `high_edu` varchar(255) DEFAULT NULL,
  `first_edu` varchar(255) DEFAULT NULL,
  `technical_post` varchar(255) DEFAULT NULL,
  `administ_post` varchar(255) DEFAULT NULL,
  `teacher_resume` varchar(2000) DEFAULT NULL,
  `other` varchar(2000) DEFAULT NULL COMMENT '主要从事工作及研究方向',
  `can_look` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `unit_ids` varchar(255) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `FKt9569nk9kidpx10a8lf3lq8ay` (`dept_id`),
  CONSTRAINT `FKt9569nk9kidpx10a8lf3lq8ay` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'17041','莫智懿',1,'1980-12-31','2019-07-23','/teacher/images/face.jpg','硕士','本科','无','高级工程师','在国内外学术刊物上发表科研论文2 篇，其中核心刊物1篇。\n在国内外学术刊物上发表教改论文2 篇，出版专著（译著等）0 部。\n获教学科研成果奖共0项；其中：国家级0项， 省部级0项，校级0项。\n目前承担科研项目共7项；其中：国家级0项，省部级1 项，市厅级0项，校级0项，横向6项。\n目前承担教学改革项目共1项；其中：国家级0项，省部级1项，市厅级0项，校级0项。\n近三年拥有教学科研经费共319.5万元， 年均106万元。\n近三年给本科生授课（理论教学）共1173学时；指导本科毕业设计共57人次。','软件工程、应用软件技术',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院','2019-11-05'),(2,'27105','许传本',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(3,'17224','朱肖颖',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(4,'17074','庞光垚',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(5,'17701','蒲保兴',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(6,'21016','覃学文',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(7,'30011','赵春茹',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(8,'21022','覃桂茳',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(9,'21011','刘敏捷',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(13,'21054','杨甲山',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(14,'21012','黄劲',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(15,'28009','胡庆席',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(16,'21005','李连芬',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(17,'21014','石向东',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(18,'30013','涂井先',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(19,'06003','陈红',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(20,'21018','李志军',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(21,'21017','许成章',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(22,'23098','吴飞燕',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(23,'17010','贺杰',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,2,'学院副院长','157291900653458',157291900650416,'大数据与软件工程学院',NULL),(24,'17044','宫海晓',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(25,'17062','邸臻炜',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(26,'17031','李翊',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(27,'17033','黄筱佟',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(28,'17214','杨秋慧',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(29,'17046','何高明',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(30,'17027','蒋琳琼',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(31,'17019','李海英',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(32,'17021','李军',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(33,'21010','苏芳',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(34,'21019','赵贤',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(35,'18074','龚红梅',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(36,'17005','吴燕端',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,3,'学院书记',NULL,157291900650416,'大数据与软件工程学院',NULL),(37,'30008','刘树先',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(38,'17702','邵晋芳',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(39,'17703','蔡敏仪',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(40,'17704','曾雨珊',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(41,'17049','汪梅',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(42,'17020','李健',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(43,'17011','陈佳',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(44,'17022','黄洁明',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(45,'27013','梁朝湘',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(46,'17064','吴芳',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(47,'17018','代丽娴',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(48,'07003','黄宏本',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,1,'学院院长',NULL,157291900650416,'大数据与软件工程学院',NULL),(49,'17037','梁菁',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(50,'31047','李宗妮',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(51,'17202','农健',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(52,'17132','卿海军',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(53,'17039','陈聪',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(54,'17038','卢雪燕',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(55,'17133','黄寄洪',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,2,'学院副院长',NULL,157291900650416,'大数据与软件工程学院',NULL),(56,'17213','郑明',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(57,'31046','何希',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(58,'17025','吴炎桃',0,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(59,'17221','陈悦',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(60,'17134','冀肖榆',1,'2019-07-24','2019-07-24','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(61,'17007','冀占江',1,'2019-07-25','2019-07-25','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',1,4,'教师',NULL,157291900650416,'大数据与软件工程学院',NULL),(63,'dsjxy_admin','大数据学院管理员',1,'2019-08-06','2019-08-06','/teacher/images/face.jpg','暂无','暂无','暂无','暂无','暂无','暂无',0,5,'学院管理员',NULL,157291900650416,'大数据与软件工程学院',NULL),(64,'保密','系统管理员',1,'2019-07-25','2019-07-25','/teacher/images/face.jpg','研究生','本科','暂无','暂无','暂无','暂无',0,6,'系统管理员',NULL,NULL,'大数据与软件工程学院',NULL),(65,'17001','甘金明',1,'2019-10-31','2019-10-31','/teacher/images/face.jpg','研究生','本科','1','1','1','1',1,4,'教师','',157291900650416,'大数据与软件工程学院','2019-10-31'),(66,'17004','陆科达',1,'2019-10-31','2019-10-31','/teacher/images/face.jpg','1','1','1','1','1','1',1,4,'教师','',157291900650416,'大数据与软件工程学院','2019-10-31'),(67,'17006','卢在盛',1,'2019-10-31','2019-10-31','/teacher/images/face.jpg','研究生','1','1','1','1','1',1,4,'教师','',157291900650416,'大数据与软件工程学院','2019-10-31'),(68,'17042','彭金虎',1,'2019-10-31','2019-10-31','/teacher/images/face.jpg','1','1','1','1','1','1',1,4,'教师','',157291900650416,'大数据与软件工程学院','2019-10-31'),(69,'17003','龚平',1,'2019-10-31','2019-10-31','/teacher/images/face.jpg','研究生','1','1','1','1','1',1,4,'教师','',157291900650416,'大数据与软件工程学院','2019-10-31'),(70,'cs_admin','测试学院管理员',1,'2019-11-07','2019-11-07','/teacher/images/face.jpg','暂无','暂无','暂无','暂无','暂无','暂无',0,5,'学院管理员',NULL,157304277097433,'测试1','2019-11-07');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_files_annex`
--

DROP TABLE IF EXISTS `teacher_files_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_files_annex` (
  `competition_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL COMMENT '对应项目的id，如competition、academic_paper',
  PRIMARY KEY (`competition_annex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师档案信息平台附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_files_annex`
--

LOCK TABLES `teacher_files_annex` WRITE;
/*!40000 ALTER TABLE `teacher_files_annex` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_files_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_honor`
--

DROP TABLE IF EXISTS `teacher_honor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_honor` (
  `honor_id` bigint(20) NOT NULL,
  `honor_name` varchar(255) DEFAULT NULL COMMENT '荣誉称号',
  `grant_unit` varchar(255) DEFAULT NULL COMMENT '授予单位',
  `member_code` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `honor_time` varchar(255) DEFAULT NULL COMMENT '获得时间',
  `dept_id` bigint(20) DEFAULT NULL,
  `item_person` int(11) DEFAULT NULL,
  `prize_grade_id` int(11) DEFAULT NULL,
  `prize_level_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`honor_id`),
  KEY `teacher_honor_competition_prize_grade_id_fk` (`prize_grade_id`),
  KEY `teacher_honor_competition_prize_level_id_fk` (`prize_level_id`),
  CONSTRAINT `teacher_honor_competition_prize_grade_id_fk` FOREIGN KEY (`prize_grade_id`) REFERENCES `competition_prize_grade` (`id`),
  CONSTRAINT `teacher_honor_competition_prize_level_id_fk` FOREIGN KEY (`prize_level_id`) REFERENCES `competition_prize_level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师荣誉';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_honor`
--

LOCK TABLES `teacher_honor` WRITE;
/*!40000 ALTER TABLE `teacher_honor` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_honor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_honor_annex`
--

DROP TABLE IF EXISTS `teacher_honor_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_honor_annex` (
  `annex_id` bigint(20) NOT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `honor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`annex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_honor_annex`
--

LOCK TABLES `teacher_honor_annex` WRITE;
/*!40000 ALTER TABLE `teacher_honor_annex` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_honor_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item`
--

DROP TABLE IF EXISTS `teacher_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `item_type` int(11) DEFAULT NULL COMMENT '项目类型',
  `contract_number` varchar(255) DEFAULT NULL,
  `item_money` double DEFAULT NULL,
  `member_code` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `item_person` int(11) DEFAULT NULL,
  `item_category_id` int(11) DEFAULT NULL,
  `item_level_id` int(11) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `item_member` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK90xtjsiqxj97k3ulv1gq9ykg7` (`item_category_id`),
  KEY `FKgmlj2mouc99otq5y0v33tu98u` (`item_level_id`),
  KEY `FKfxruiehw537yox37qls4k3hys` (`item_person`),
  CONSTRAINT `FK90xtjsiqxj97k3ulv1gq9ykg7` FOREIGN KEY (`item_category_id`) REFERENCES `teacher_item_category` (`id`),
  CONSTRAINT `FKfxruiehw537yox37qls4k3hys` FOREIGN KEY (`item_person`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `FKgmlj2mouc99otq5y0v33tu98u` FOREIGN KEY (`item_level_id`) REFERENCES `teacher_item_level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item`
--

LOCK TABLES `teacher_item` WRITE;
/*!40000 ALTER TABLE `teacher_item` DISABLE KEYS */;
INSERT INTO `teacher_item` VALUES (1,'以工程教育专业认证为导向的数字媒体技术专业人才培养模式与方法研究',1,'2019JGA307',2,'17044','宫海晓','2019-01-01','2019-01-01','2020-12-31',24,8,7,'2019-10-30',NULL),(2,'多层次信息安全技术联赛启发下的信息安全应用型人才培养模式探索',1,'Wyjg2016B007',0.3,'17133','黄寄洪','2016-01-01','2016-01-01','2017-12-31',55,9,9,'2019-10-30',NULL),(3,'中职升本人才培养模式及相关政策研究',1,'Wyjg2017B023',0.3,'27105','许传本','2017-01-01','2017-01-01','2018-12-31',2,9,9,'2019-10-30',NULL),(5,'新工科背景下面向应用型本科院校计算机类专业实验实训教学体系研究—以梧州学院为例',1,'2019JGA309',2,'17202','农健','2019-01-01','2019-01-01','2020-12-31',51,8,7,'2019-10-30',NULL),(6,'新工科背景下面向应用型本科院校计算机类专业实验实训教学体系研究—以梧州学院为例',1,'2019JGB355',1,'17039','陈聪','2019-01-01','2019-01-01','2019-12-31',53,8,8,'2019-10-30',NULL),(7,'测试1',1,'暂无',2,'17041','莫智懿','2012-01-01','2012-01-01','2012-12-31',1,1,1,'2019-10-30',NULL),(8,'面向创新性应用型人才培养的地方院校数字媒体技术专业转型发展研究',1,'2018JGB311',1,'17010','贺杰','2018-01-01','2018-01-01','2019-12-31',23,8,8,'2019-10-30',NULL),(9,'面向创新性应用型人才培养的数字媒体技术专业转型发展研究',1,'Wyjg2017B024',0.3,'17010','贺杰','2017-01-01','2017-01-01','2018-12-31',23,9,9,'2019-10-30',NULL),(10,'新工科背景下应用型本科院校立体化教材建设研究',1,'2018JGB309',1,'23098','吴飞燕','2018-01-01','2018-01-01','2019-12-31',22,8,8,'2019-10-30',NULL),(11,'基于应用型本科院校的立体化教材建设研究——以《多媒体技术》课程为例',1,'Wyjg2016B015',0.3,'23098','吴飞燕','2016-01-01','2016-01-01','2017-12-31',22,9,9,'2019-10-30',NULL),(12,'新工科背景下地方高校以学生为中心的教学模式的研究与实践',1,'2018JGA278',2,'17011','陈佳','2018-01-01','2018-01-01','2019-12-31',43,8,7,'2019-10-30',NULL),(13,'基于MOOC平台以学生为主体的教学模式的研究与实践——以《计算机应用基础》为例',1,'Wyjg2017B025',0.3,'17011','陈佳','2017-01-01','2017-01-01','2018-12-31',43,9,9,'2019-10-30',NULL),(14,'中职升本高等数学课程体系改革的研究',1,'Wyjg2016B006',0.3,'21017','许成章','2016-01-01','2016-01-01','2017-12-31',21,9,9,'2019-10-30',NULL),(15,'体验性教学在计算机专业课程中的应用',1,'Wyjg2017B026',0.3,'17214','杨秋慧','2017-01-01','2017-01-01','2018-12-31',28,9,9,'2019-10-30',NULL),(16,'真实职场环境下行业软件技术政校企协同育人平台建设及实训教学改革',1,'2017JGA303',2,'17004','陆科达','2017-01-01','2017-01-01','2018-12-31',66,8,7,'2019-10-31',NULL);
/*!40000 ALTER TABLE `teacher_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item_annex`
--

DROP TABLE IF EXISTS `teacher_item_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item_annex` (
  `item_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_annex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='教师项目附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item_annex`
--

LOCK TABLES `teacher_item_annex` WRITE;
/*!40000 ALTER TABLE `teacher_item_annex` DISABLE KEYS */;
INSERT INTO `teacher_item_annex` VALUES (1,'D:/tomcat_annex/teacher/annex/b0c02e43-ec46-4aed-835f-967bc2ede40b.docx','以工程教育专业认证为导向的数字媒体技术专业人才培养模式与方法研究.docx','.docx',1),(2,'D:/tomcat_annex/teacher/annex/b4586cae-b616-4352-8b6b-5d6721b092b9.docx','2016年梧州学院教学改革工程立项项目.docx','.docx',2),(3,'D:/tomcat_annex/teacher/annex/9cfc23f8-34f7-49b7-b4e8-34d60f372ed2.docx','2017年梧州学院教学改革工程立项项目.docx','.docx',3),(4,'D:/tomcat_annex/teacher/annex/9cfc23f8-34f7-49b7-b4e8-34d60f372ed2.docx','2017年梧州学院教学改革工程立项项目.docx','.docx',4),(5,'D:/tomcat_annex/teacher/annex/fff1bf11-3f38-4294-bfd4-009a107cdf52.docx','广西高等教育本科教学改革工程项.docx','.docx',5),(6,'D:/tomcat_annex/teacher/annex/69dc952a-96ff-4c0f-9013-0ca3b523eae8.docx','新工科背景下面向应用型本科院校计算机类专业实验实训教学体系研究—以梧州学院为例.docx','.docx',6),(7,'D:/tomcat_annex/teacher/annex/83ceaf0e-940c-4479-8783-94f6bb198154.docx','面向创新性应用型人才培养的地方院校数字媒体技术专业转型发展研究.docx','.docx',8),(8,'D:/tomcat_annex/teacher/annex/3f9d0d1f-ea67-43c2-a794-ac4846579483.docx','2017年梧州学院教学改革工程立项项目.docx','.docx',9),(9,'D:/tomcat_annex/teacher/annex/83de6b40-f996-46f7-aad4-cdf693330634.docx','新工科背景下应用型本科院校立体化教材建设研究.docx','.docx',10),(10,'D:/tomcat_annex/teacher/annex/1d980786-1211-4330-a5b7-0c6674ead529.docx','2016年梧州学院教学改革工程立项项目.docx','.docx',11),(11,'D:/tomcat_annex/teacher/annex/bed04b7b-a112-4538-87f0-844f4dff74c1.docx','新工科背景下地方高校以学生为中心的教学模式的研究与实践.docx','.docx',12),(12,'D:/tomcat_annex/teacher/annex/097cdaa0-ac05-43a3-b831-2b424113fc19.docx','2017年梧州学院教学改革工程立项项目.docx','.docx',13),(13,'D:/tomcat_annex/teacher/annex/306a9355-2a31-49ae-acfd-a99edc128c55.docx','2016年梧州学院教学改革工程立项项目.docx','.docx',14),(14,'D:/tomcat_annex/teacher/annex/18f225ca-6e88-4741-82fd-abd6c2c723f6.docx','2017年梧州学院教学改革工程立项项目.docx','.docx',15),(15,'D:/tomcat_annex/teacher/annex/7b53054f-c1da-429a-b0b2-d9c3f4cf69f8.docx','真实职场环境下行业软件技术政校企协同育人平台建设及实训教学改革.docx','.docx',16);
/*!40000 ALTER TABLE `teacher_item_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item_category`
--

DROP TABLE IF EXISTS `teacher_item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='教师项目类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item_category`
--

LOCK TABLES `teacher_item_category` WRITE;
/*!40000 ALTER TABLE `teacher_item_category` DISABLE KEYS */;
INSERT INTO `teacher_item_category` VALUES (1,'国家级地区项目'),(2,'省级地区项目'),(3,'广西科技厅科技开发项目'),(4,'广西教育厅科技开发项目'),(5,'广西中青年教师基础能力提升项目'),(6,'横向'),(7,'纵向'),(8,'广西高等教育本科教学改革工程项目'),(9,'梧州学院教育教学改革工程项目');
/*!40000 ALTER TABLE `teacher_item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item_level`
--

DROP TABLE IF EXISTS `teacher_item_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='教师项目级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item_level`
--

LOCK TABLES `teacher_item_level` WRITE;
/*!40000 ALTER TABLE `teacher_item_level` DISABLE KEYS */;
INSERT INTO `teacher_item_level` VALUES (1,'国家自然科学基金'),(2,'省级自然科学基金'),(3,'省级科技开发项目'),(4,'教育厅科技开发项目'),(5,'教育厅科研项目'),(6,'社会服务'),(7,'一般项目A类'),(8,'一般项目B类'),(9,'一般项目');
/*!40000 ALTER TABLE `teacher_item_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching_material`
--

DROP TABLE IF EXISTS `teaching_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_material` (
  `material_id` bigint(20) NOT NULL,
  `material_name` varchar(255) DEFAULT NULL COMMENT '教材名称',
  `press` varchar(255) DEFAULT NULL COMMENT '出版社',
  `publish_time` varchar(255) DEFAULT NULL COMMENT '出版时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL COMMENT '主编',
  PRIMARY KEY (`material_id`),
  KEY `FK588EBDD91B3921AC` (`teacher_id`),
  CONSTRAINT `FK588EBDD91B3921AC` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教材';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching_material`
--

LOCK TABLES `teaching_material` WRITE;
/*!40000 ALTER TABLE `teaching_material` DISABLE KEYS */;
INSERT INTO `teaching_material` VALUES (157300857621353,'2222222222','测试1','2016','...',157291900650416,1),(157300868237616,'测试1','测试1','2016','000',157291900650416,1);
/*!40000 ALTER TABLE `teaching_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching_material_annex`
--

DROP TABLE IF EXISTS `teaching_material_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_material_annex` (
  `annex_id` bigint(20) NOT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `material_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`annex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching_material_annex`
--

LOCK TABLES `teaching_material_annex` WRITE;
/*!40000 ALTER TABLE `teaching_material_annex` DISABLE KEYS */;
INSERT INTO `teaching_material_annex` VALUES (157300770257785,'/home/soldier/SOLDIER/tomcat_annex/teacher/annex/b2c1c43d-c310-4ea4-9e76-7fe4572c3741.docx','外调函(7).docx','.docx',157300770247444);
/*!40000 ALTER TABLE `teaching_material_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching_result`
--

DROP TABLE IF EXISTS `teaching_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_result` (
  `result_id` bigint(20) NOT NULL,
  `result_name` varchar(255) DEFAULT NULL,
  `member_code` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `result_time` varchar(255) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `item_person` int(11) DEFAULT NULL,
  `prize_grade_id` int(11) DEFAULT NULL,
  `prize_level_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`result_id`),
  KEY `FKC70BA54F45A2B115` (`item_person`),
  KEY `FKC70BA54F86F43586` (`prize_grade_id`),
  KEY `FKC70BA54F8716C4A6` (`prize_level_id`),
  CONSTRAINT `FKC70BA54F45A2B115` FOREIGN KEY (`item_person`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `FKC70BA54F86F43586` FOREIGN KEY (`prize_grade_id`) REFERENCES `competition_prize_grade` (`id`),
  CONSTRAINT `FKC70BA54F8716C4A6` FOREIGN KEY (`prize_level_id`) REFERENCES `competition_prize_level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教学成果';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching_result`
--

LOCK TABLES `teaching_result` WRITE;
/*!40000 ALTER TABLE `teaching_result` DISABLE KEYS */;
INSERT INTO `teaching_result` VALUES (157301642757813,'测试1.1','17041,17010','莫智懿,贺杰','2019',157291900650416,23,1,1);
/*!40000 ALTER TABLE `teaching_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teaching_result_annex`
--

DROP TABLE IF EXISTS `teaching_result_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_result_annex` (
  `annex_id` bigint(20) NOT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `result_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`annex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaching_result_annex`
--

LOCK TABLES `teaching_result_annex` WRITE;
/*!40000 ALTER TABLE `teaching_result_annex` DISABLE KEYS */;
INSERT INTO `teaching_result_annex` VALUES (157301642767662,'/home/soldier/SOLDIER/tomcat_annex/teacher/annex/6a0e47bb-3a64-4e3e-888a-45e6dc21922c.7z','档案材料.7z','.7z',157301642757813);
/*!40000 ALTER TABLE `teaching_result_annex` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-11 20:06:01
