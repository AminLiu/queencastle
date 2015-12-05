DROP TABLE IF EXISTS `queen_feedback`;
CREATE TABLE `queen_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `info_id` bigint(20) DEFAULT NULL ,
  `user_id` bigint(20) DEFAULT NULL,
  `content` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;