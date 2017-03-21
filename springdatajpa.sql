-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.51 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 aidou.i_dou_change 结构
CREATE TABLE IF NOT EXISTS `i_dou_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `change_type` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `i_dou_count` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_giqq7yns6cxf6ovmpdohhjnkw` (`user_id`),
  CONSTRAINT `FK_giqq7yns6cxf6ovmpdohhjnkw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  aidou.i_dou_change 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `i_dou_change` DISABLE KEYS */;
INSERT INTO `i_dou_change` (`id`, `change_type`, `create_time`, `i_dou_count`, `user_id`) VALUES
	(1, '注册成功', '2017-03-21 07:57:57', 100, 1),
	(2, '吃吃', '2017-03-21 08:27:15', 1000, NULL);
/*!40000 ALTER TABLE `i_dou_change` ENABLE KEYS */;

-- 导出  表 aidou.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `charge_money` bigint(20) NOT NULL DEFAULT '0',
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `i_dou_count` bigint(20) NOT NULL DEFAULT '0',
  `latitude` double DEFAULT NULL,
  `log_out` tinyint(1) DEFAULT NULL,
  `log_out_time` datetime DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  aidou.user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `account`, `avatar`, `charge_money`, `city`, `country`, `create_time`, `i_dou_count`, `latitude`, `log_out`, `log_out_time`, `longitude`, `name`, `province`, `sex`, `update_time`) VALUES
	(1, '89351', NULL, 0, NULL, NULL, '2017-03-21 07:57:57', 100, NULL, 0, NULL, NULL, 'af12', NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
