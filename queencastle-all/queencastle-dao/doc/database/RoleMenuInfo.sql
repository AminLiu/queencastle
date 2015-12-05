DROP TABLE IF EXISTS `rolemenu_info`;
CREATE TABLE `rolemenu_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(200) DEFAULT NULL,
  `menu_id` bigint(120) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;