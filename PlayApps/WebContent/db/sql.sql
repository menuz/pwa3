CREATE TABLE `version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '只有两种值，a代表android，i代表ios',
  `version` varchar(255) DEFAULT NULL COMMENT '格式如下：x.x',
  `info` varchar(255) DEFAULT NULL COMMENT '升级内容',
  `path` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `size` double DEFAULT NULL COMMENT '大小，单位为MB',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


insert into version  values(null, 'i', '1.0', 'init project', '', 1.0);
insert into version  values(null, 'i', '1.1', 'test project', '', 1.1);