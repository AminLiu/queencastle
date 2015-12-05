DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `menu_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(200) DEFAULT NULL ,
  `cname` varchar(120) DEFAULT NULL,
  `ename` varchar(60) DEFAULT NULL,
  `rank` bigint(200) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;