# Host: 188.131.157.4  (Version 5.7.26-0ubuntu0.18.04.1)
# Date: 2019-05-30 14:59:21
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "Category"
#

DROP TABLE IF EXISTS `Category`;
CREATE TABLE `Category`
(
    `id`   varchar(32) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

#
# Structure for table "mytable"
#

DROP TABLE IF EXISTS `mytable`;
CREATE TABLE `mytable`
(
    `Id`     int(11) NOT NULL AUTO_INCREMENT,
    `name`   varchar(255) DEFAULT NULL,
    `age`    int(11)      DEFAULT NULL,
    `gender` bigint(1)    DEFAULT NULL COMMENT '1为男，0为女',
    PRIMARY KEY (`Id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 703
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

#
# Structure for table "order"
#

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    `id`   varchar(32) NOT NULL,
    `code` varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

#
# Structure for table "order_item"
#

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`
(
    `id`     varchar(32) NOT NULL,
    `oid`    varchar(32) DEFAULT NULL,
    `pid`    varchar(32) DEFAULT NULL,
    `number` int(11)     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

#
# Structure for table "Product"
#

DROP TABLE IF EXISTS `Product`;
CREATE TABLE `Product`
(
    `id`    varchar(32) NOT NULL,
    `name`  varchar(255) DEFAULT NULL,
    `price` float        DEFAULT NULL,
    `cid`   varchar(32)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;

#
# Structure for table "Resourcetable"
#

DROP TABLE IF EXISTS `Resourcetable`;
CREATE TABLE `Resourcetable`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name`       varchar(200) COLLATE utf8_bin DEFAULT NULL,
    `cnname`     varchar(200) COLLATE utf8_bin DEFAULT NULL,
    `istop`      int(11)                       DEFAULT NULL,
    `leftvalue`  int(11)                       DEFAULT NULL,
    `rightvalue` int(11)                       DEFAULT NULL,
    `level`      int(11)                       DEFAULT NULL,
    `order`      int(11)                       DEFAULT NULL,
    `urlpath`    varchar(200) COLLATE utf8_bin DEFAULT NULL,
    `haschild`   tinyint(1)                    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 101
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

#
# Structure for table "usertable"
#

DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `name`     varchar(200) COLLATE utf8_bin DEFAULT NULL,
    `password` varchar(200) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `usertable_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
