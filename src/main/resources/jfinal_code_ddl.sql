DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '项目名',
  `description` varchar(200) DEFAULT NULL COMMENT '项目描述',
  `viewFramework` varchar(20) DEFAULT 'dwz' COMMENT '试图风格',
  `ip` varchar(32) DEFAULT '127.0.0.1' COMMENT 'IP',
  `port` varchar(10) DEFAULT '8080' COMMENT '端口',
  `dbType` varchar(20) DEFAULT 'mysql' COMMENT '数据库类型',
  `jdbcurl` varchar(100) DEFAULT NULL COMMENT '数据库连接',
  `username` varchar(20) DEFAULT 'root' COMMENT '数据库用户名',
  `password` varchar(20) DEFAULT 'root' COMMENT '数据库密码',
  `packageName` varchar(50) DEFAULT NULL COMMENT '包名',
  `viewEngine` varchar(20) DEFAULT 'freemarker' COMMENT '模版引擎类型',
  `management` varchar(20) DEFAULT 'gradle' COMMENT '构建方式',
  `groups_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)   DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '表名',
  `label` varchar(200) DEFAULT NULL COMMENT '描述',
  `project_id` int(10) DEFAULT NULL COMMENT '所属业务',
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8 ;



DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '字段名',
  `label` varchar(50) DEFAULT NULL COMMENT '描述',
  `isDisplayable` tinyint(4) DEFAULT NULL COMMENT '是否显示',
  `isPrimaryKey` tinyint(4) DEFAULT '0' COMMENT '是否为主键',
  `isSearchable` tinyint(4) DEFAULT '1' COMMENT '查询类型',
  `isReadOnly` tinyint(4) DEFAULT '0' COMMENT '是否只读',
  `isHidden` tinyint(4) DEFAULT '0' COMMENT '是否隐藏',
  `defaultValue` varchar(20) DEFAULT NULL COMMENT '默认值',
  `validator` int(10) DEFAULT NULL COMMENT '校验类型',
  `longness` int(10) DEFAULT '0' COMMENT '长度',
  `scale` varchar(10) DEFAULT NULL COMMENT '精度',
  `fieldType` varchar(10) DEFAULT NULL COMMENT '数据库字段类型',
  `entity_id` int(10) DEFAULT NULL COMMENT '所属实体',
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务组',
  `name` varchar(45)  NOT NULL COMMENT '任务组名称',
  `remark` varchar(45) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `groups_task_relation`;
CREATE TABLE `groups_task_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groups_id` int(11) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskname` varchar(32) DEFAULT NULL,
  `taskType` varchar(45) DEFAULT NULL,
  `folder` varchar(512) DEFAULT NULL,
  `filename` varchar(512) DEFAULT NULL,
  `templatepath` varchar(512) DEFAULT NULL,
  `valid` tinyint(1) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `task_param`;
CREATE TABLE `task_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(250) DEFAULT NULL,
  `expression` varchar(250) DEFAULT NULL,
  `task_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `view_engine`;
CREATE TABLE `view_engine` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `view_framework`;
CREATE TABLE `view_framework` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `data_type`;
CREATE TABLE `data_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT 'id',
  `db_type` varchar(45) DEFAULT NULL,
  `field_type` varchar(45) DEFAULT NULL,
  `column_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `db_info`;
CREATE TABLE `db_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(20) DEFAULT NULL COMMENT '数据库名',
  `driverClass` varchar(128) DEFAULT NULL COMMENT '驱动名',
  PRIMARY KEY (`id`)
)   DEFAULT CHARSET=utf8;

