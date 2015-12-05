DROP TABLE IF EXISTS `queen_address`;
CREATE TABLE `queen_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `provinceCode` varchar(6) NOT NULL, 
  `cityCode` varchar(6) NOT NULL,
  `areaCode` varchar(6) NOT NULL,
  `address` varchar(30) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;