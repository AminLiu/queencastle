--产品

DROP TABLE IF EXISTS `queen_shop_product`;
CREATE TABLE `queen_shop_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `brand_id` bigint(20) NOT NULL, 
  `amount` bigint(20) NOT NULL,
  `images` varchar(300) NOT NULL,
  `standard_ids` varchar(120) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--商家
DROP TABLE IF EXISTS `queen_shop_customer`;
CREATE TABLE `queen_shop_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) NOT NULL, 
  `customer_tel` varchar(20) NOT NULL,
  `customer_sex` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--订单
DROP TABLE IF EXISTS `queen_shoporder`;
CREATE TABLE `queen_shoporder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--产品规格
DROP TABLE IF EXISTS `queen_shop_productstandard`;
CREATE TABLE `queen_shop_productstandard` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `colour`  varchar(20) NOT NULL,
  `size`  varchar(20) NOT NULL,
  `img`  varchar(20) NOT NULL,
  `price` float(10) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--收货地址
DROP TABLE IF EXISTS `queen_shop_address`;
CREATE TABLE `queen_shop_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `province_id`  bigint(20) NOT NULL,
  `city_id`  bigint(20) NOT NULL,
  `detail`  varchar(20) NOT NULL,
  `user_id`  bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--产品品牌
DROP TABLE IF EXISTS `queen_shopbrand`;
CREATE TABLE `queen_shopbrand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname`  varchar(20) NOT NULL,
  `img`  varchar(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `queen_shop_orderitem`;
CREATE TABLE `queen_shop_orderitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shopProduct_id`  bigint(20) NOT NULL,
  `amount`  bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `queen_shop_orderitem`;
CREATE TABLE `queen_shop_orderitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shopProduct_id`  bigint(20) NOT NULL,
  `amount`  bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;