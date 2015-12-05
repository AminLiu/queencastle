
DROP TABLE IF EXISTS `queen_user`;
CREATE TABLE `queen_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(200) DEFAULT NULL,
  `username` varchar(120) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `out_source` varchar(60) DEFAULT NULL,
  `out_nick` varchar(120) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `queen_feedback`;
CREATE TABLE `queen_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `info_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `queen_category`;
CREATE TABLE `queen_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(60) DEFAULT NULL,
   `ename` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `queen_product`;
CREATE TABLE `queen_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `cname` varchar(60) DEFAULT NULL,
   `ename` varchar(60) DEFAULT NULL,
   `intro` varchar(300) DEFAULT NULL,
   `imgs` varchar(600) DEFAULT NULL,
  `created_at` datetime NOT NULL,
   `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `queen_resource`;
CREATE TABLE `queen_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `file_name` varchar(60) DEFAULT NULL,
   `origin_name` varchar(60) DEFAULT NULL,
   `file_ext` varchar(300) DEFAULT NULL,
   `file_key` varchar(600) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `zone_info`;
CREATE TABLE `zone_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `parent_id` bigint(20) DEFAULT NULL,
  `cname` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `queen_demendinfo`;
CREATE TABLE `queen_demendinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) DEFAULT NULL,
   `product_id` bigint(20) DEFAULT NULL,
   `amount` bigint(20) DEFAULT NULL,
   `price` bigint(20) DEFAULT NULL,
   `ds_Type` varchar(60) DEFAULT NULL,
   `memo` varchar(60) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






DROP TABLE IF EXISTS `queen_user_detail`;
CREATE TABLE `queen_user_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL ,
  `img` varchar(200) DEFAULT NULL,
  `country` varchar(200) DEFAULT NULL,
  `province` varchar(200) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `cityCode` varchar(20) DEFAULT NULL,
  `provinceCode` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





