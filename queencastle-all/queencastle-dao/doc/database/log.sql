DROP TABLE IF EXISTS `queen_log`;
CREATE TABLE `queen_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT, 
   `user_id` bigint(20) DEFAULT NULL,
    `object_id` bigint(20) DEFAULT NULL,
   `log_type` varchar(60) DEFAULT NULL,
   `content` varchar(60) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `queen_qrcode`;
CREATE TABLE `queen_qrcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT, 
  `user_id` bigint(20) DEFAULT NULL,
   `ticket` varchar(120) DEFAULT NULL,
   `img` varchar(120) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




