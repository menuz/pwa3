CREATE TABLE `version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '只有两种值，a代表android，i代表ios',
  `version` varchar(255) DEFAULT NULL COMMENT '格式如下：x.x',
  `info` varchar(255) DEFAULT NULL COMMENT '升级内容',
  `path` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `size` double DEFAULT NULL COMMENT '大小，单位为MB',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

ALTER TABLE try ADD delete_ct timestamp NULL DEFAULT NULL COMMENT '卸载时间';

----------------------------------------------------------------------------------------------------

insert into version  values(null, 'i', '1.0', 'init project', '', 1.0);
insert into version  values(null, 'i', '1.1', 'test project', '', 1.1);

-- 插入要监测的应用
insert into app values(null, 'appname1', 'i', '', '', 4, 2, 100, 0, null, 'test1', 1, 'com.nake', '','');

-- 插入监测任务
insert into try values(null, 29, 9, 0, 300, '2014-11-29 14:49:55', null, null,null);


-- status = 0表示初始状态应用状态，1表示正在玩，2表示已完成
-- io.vov.vitamio.demo
insert into try values(null, 984, 10, 0, 300, null, null, null);
update try set status = 0 where try_id = 19;

-- org.achartengine.chartdemo.demo
insert into try values(null, 984, 11, 0, 300, null, null, null);
update try set status = 0 where try_id = 20;

user_id = 984
app_id = 10 && 11



