INSERT INTO `entity` VALUES (1,'student','学生',1),
(4,'organization','组织',1),
(5,'blog','博客',2);


INSERT INTO `field` VALUES (1,'id','id',0,1,0,0,1,NULL,NULL,11,'','Integer',1),
(2,'name','姓名',1,0,1,0,0,NULL,NULL,32,'','String',1),
(3,'age','年龄',1,0,1,0,0,NULL,NULL,11,NULL,'Integer',1),
(4,'birthday','生日',1,0,1,0,0,NULL,0,0,'','Date',1),
(5,'salary','工资',1,0,1,0,0,'1000',0,11,'2','Float',1),
(6,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',10),
(7,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',11),
(8,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',12),
(9,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',13),
(10,'id','id',NULL,0,0,0,0,NULL,NULL,0,NULL,NULL,3),
(11,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',4),
(12,'name','查询',NULL,0,1,0,0,NULL,NULL,0,NULL,'String',4),
(13,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',5),
(14,'title','标题',NULL,0,1,0,0,NULL,NULL,0,NULL,'String',5),
(18,'id','id',0,1,0,1,1,NULL,NULL,0,NULL,'Integer',6);



INSERT INTO `groups` VALUES (1,'jfinalweb','基于jfinal的web项目');




INSERT INTO `groups_task_relation` VALUES (19,1,20),
(20,1,18),
(21,1,17),
(22,1,16),
(23,1,15),
(24,1,14),
(25,1,13),
(26,1,11),
(27,1,10),
(28,1,9),
(29,1,8),
(30,1,7),
(31,1,6),
(32,1,5),
(33,1,4),
(34,1,3),
(35,1,2),
(36,1,1);




INSERT INTO `project` VALUES
(1,'demo','测试项目','dwz','127.0.0.1','8080','mysql','jdbc:mysql://localhost:3306/demo','root','root','com.kidzhou.demo','freemarker','gradle',1),
(2,'demo2','测试2','jfinal','127.0.0.1','8081','mysql','jdbc:mysql://localhost:3306/demo2','root','root','com.kidzhou.jfmk','freemarker','gradle',1);




INSERT INTO `task` VALUES
(1,'model','multiple','"src/main/java/"+project.packageName.replaceAll("\\\\.","/")+"/"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase())+".java"','"java/model.ftl"',1),
(2,'ddl','single','"sql"','project.name + ".sql"','"db/" + project.dbType + "/ddl.ftl"',1),
(3,'appconfig','single','"src/main/java/" + project.packageName.replaceAll("\\\\.", "/")','TaskKit.capitalize(project.name) + "Config.java"','"java/config.ftl"',1),
(4,'config','single','"src/main/resources"','"config.txt"','"config/txt.ftl"',1),
(5,'webxml','single','"src/main/webapp/WEB-INF"','"web.xml"','"config/web.ftl"',1),
(6,'indexController','single','"src/main/java/"+project.packageName.replaceAll("\\\\.", "/")','"IndexController.java"','"java/index.ftl"',1),
(7,'validate','multiple','"src/main/java/"+project.packageName.replaceAll("\\\\.", "/") + "/"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase()) + "Validator.java"','"java/validator.ftl"',1),
(8,'controller','multiple','"src/main/java/"+project.packageName.replaceAll("\\\\.", "/") + "/"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase()) + "Controller.java"','"java/controller.ftl"',1),
(9,'add','multiple','"src/main/webapp/"+entity.name.toLowerCase()','"add.html"','"view/" + project.viewFramework + "/"+project.viewEngine+ "/add.ftl"',1),
(10,'log4j','single','"src/main/resources"','"log4j.properties"','"config/log4j.ftl"',1),(11,'edit','multiple','"src/main/webapp/"+entity.name.toLowerCase()','"edit.html"','"view/" + project.viewFramework + "/" + project.viewEngine + "/edit.ftl"',1),
(13,'index','single','"src/main/webapp"','"index.html"','"view/" + project.viewFramework + "/" + project.viewEngine + "/index.ftl"',1),
(14,'menu','single','"src/main/webapp"','"menu.html"','"view/" + project.viewFramework + "/" +project.viewEngine+ "/menu.ftl"',1),
(15,'form','multiple','"src/main/webapp/"+entity.name.toLowerCase()','"_form.html"','"view/" + project.viewFramework + "/" +project.viewEngine+ "/_form.ftl"',1),
(16,'page','multiple','"src/main/webapp/"+entity.name.toLowerCase()','"index.html"','"view/" + project.viewFramework + "/" + project.viewEngine + "/page.ftl"',1),
(17,'view','multiple','"src/main/webapp/"+entity.name.toLowerCase()','"view.html"','"view/" + project.viewFramework + "/" + project.viewEngine + "/view.ftl"',1),
(18,'searcher','multiple','"src/main/java/" + project.packageName.replaceAll("\\\\.", "/") + "/"+entity.name.toLowerCase()','TaskKit.capitalize(entity.name.toLowerCase()) + "Searcher.java"','"java/searcher.ftl"',1),
(20,'build','single','""','TaskKit.buildFileName(project.management)','"build/"+project.management+".ftl"',1);



INSERT INTO `task_param` VALUES (4,'_accessor','0','1'),
(5,'_conversionPattern','"[%-5p] %d{yyyy-MM-dd HH:mm:ss} method:%l%n%m%n"','10');


INSERT INTO `data_type` VALUES (1,'mysql','Integer','int'),
(2,'mysql','String','varchar'),
(3,'mysql','Float','float'),
(4,'mysql','Datetime','timestamp'),
(5,'mysql','Richtext','longtext'),
(6,'mysql','Date','date');


INSERT INTO `db_info` VALUES (1,'h2','oracle.jdbc.driver.OracleDriver'),
(2,'mysql','com.mysql.jdbc.Driver');


INSERT INTO `view_engine` VALUES (1,'freemarker'),(2,'jsp');


DROP TABLE IF EXISTS `view_framework`;
CREATE TABLE `view_framework` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


INSERT INTO `view_framework` VALUES (1,'dwz'),(2,'jfinal');