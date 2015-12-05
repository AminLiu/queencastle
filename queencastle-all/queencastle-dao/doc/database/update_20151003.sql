DROP TABLE IF EXISTS `queen_user_relation`;
CREATE TABLE `queen_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) DEFAULT NULL,
   `parent_id` bigint(20) DEFAULT NULL,
   `r_type` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `queen_user_checkin`;
CREATE TABLE `queen_user_checkin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `queen_user_group`;
CREATE TABLE `queen_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `cname` varchar(60) DEFAULT NULL,
   `code` varchar(60) DEFAULT NULL,
   `img` varchar(60) DEFAULT NULL,
   `profile` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `queen_user_member`;
CREATE TABLE `queen_user_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL,
     `group_id` bigint(20) DEFAULT NULL,
   `code` varchar(60) DEFAULT NULL,
   `m_type` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `queen_area_group`;
CREATE TABLE `queen_area_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `area_id` bigint(20) DEFAULT NULL,
   `code` varchar(60) DEFAULT NULL,
   `area_type` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `queen_praise`;
CREATE TABLE `queen_praise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `info_id` bigint(20) DEFAULT NULL,
   `user_id` bigint(20) DEFAULT NULL,
   `r_type` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
   `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `queen_qrcode`;
CREATE TABLE `queen_qrcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) DEFAULT NULL,
   `ticket` varchar(60) DEFAULT NULL,
   `img` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 

DROP TABLE IF EXISTS `queen_sale_data`;
CREATE TABLE `queen_sale_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `order_no` varchar(120) DEFAULT NULL,
   `user_name` varchar(120) DEFAULT NULL,
   `address` varchar(120) DEFAULT NULL,
   `province` varchar(120) DEFAULT NULL,
   `city` varchar(120) DEFAULT NULL,
   `area` varchar(120) DEFAULT NULL,
   `phone` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `queen_property_domain`;
CREATE TABLE `queen_property_domain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `cname` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `queen_property_dict`;
CREATE TABLE `queen_property_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `domain_id` bigint(20) DEFAULT NULL,
   `cname` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `queen_user_intention`;
CREATE TABLE `queen_user_intention` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) DEFAULT NULL,
   `timework_id` bigint(20) DEFAULT NULL,
   `teamcnt_id` bigint(20) DEFAULT NULL,
   `sale_info` varchar(60) DEFAULT NULL,
   `saleamount_id` bigint(20) DEFAULT NULL,
   `plan_info` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `queen_user_audit`;
CREATE TABLE `queen_user_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `apply_user` bigint(20) DEFAULT NULL,
   `audit_user` bigint(20) DEFAULT NULL,
   `audit_status` varchar(60) DEFAULT NULL,
   `reason` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
   `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




