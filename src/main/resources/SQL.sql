create database deal_datail;


CREATE TABLE `stock_deal_json` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `stock_id` varchar(256) DEFAULT NULL,
  `deal_day` datetime DEFAULT NULL,
  `content` longtext COMMENT 'jsonContent',
  `add_time` int DEFAULT NULL,
  `price_a_d` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stock_id_add_time_idx` (`stock_id`,`deal_day`),
  KEY `stock_id_idx` (`stock_id`),
  KEY `add_time` (`deal_day`)
) ENGINE=InnoDB AUTO_INCREMENT=381588 DEFAULT CHARSET=utf8;