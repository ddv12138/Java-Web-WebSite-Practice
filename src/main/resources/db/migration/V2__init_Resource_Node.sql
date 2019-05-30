# Host: 188.131.157.4  (Version 5.7.26-0ubuntu0.18.04.1)
# Date: 2019-05-30 15:44:36
# Generator: MySQL-Front 6.1  (Build 1.26)


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
# Data for table "Resourcetable"
#

REPLACE INTO `Resourcetable`
VALUES (1, 'root', '节点列表', NULL, 1, 8, 0, 1, NULL, 1),
       (2, '1', '节点管理', 1, 4, 7, 1, 2, 'resourceManage/resourcetree.html', 1);
