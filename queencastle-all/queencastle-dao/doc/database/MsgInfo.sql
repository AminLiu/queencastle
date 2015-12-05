DROP TABLE IF EXISTS `msg_info`;
CREATE TABLE `msg_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL ,
  `title` varchar(120) DEFAULT NULL,
  `content` varchar(600) DEFAULT NULL,
  `images` varchar(600) DEFAULT null,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;