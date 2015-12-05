DROP TABLE IF EXISTS `queen_article`;
CREATE TABLE `queen_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `board_id` bigint(20) DEFAULT NULL ,
  `author` varchar(60) DEFAULT NULL,
  `title` varchar(60) DEFAULT NULL,
  `img` varchar(60) DEFAULT NULL,
  `content` varchar(20) DEFAULT NULL,
  `praise_cnt` bigint(20) DEFAULT NULL,
  `page_view` bigint(20) DEFAULT NULL,
  `attention_cnt` bigint(60) DEFAULT NULL,
  `comment_cnt` bigint(60) DEFAULT NULL,
  `top`  int(1) NOT NULL DEFAULT '0',
  `type` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--  comment  文章评论
DROP TABLE IF EXISTS `queen_comment`;
CREATE TABLE `queen_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` BIGINT(20) DEFAULT NULL ,
  `content` varchar(60) DEFAULT NULL,
  `user_id` BIGINT(20) DEFAULT NULL,
    `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- board 板块
DROP TABLE IF EXISTS `queen_board`;
CREATE TABLE `queen_board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL ,
  `img` varchar(60) DEFAULT NULL,
    `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



