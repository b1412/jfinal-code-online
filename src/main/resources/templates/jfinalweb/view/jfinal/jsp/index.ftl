<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="<#noparse>${PATH}</#noparse>/css/manage.css" media="screen" rel="stylesheet" type="text/css" />
<script src="<#noparse>${PATH}</#noparse>/js/jquery-1.4.4.min.js" type="text/javascript" ></script>
</head>
<body>
	<div class="manage_container">
		<div class="manage_head">
			<div class="manage_logo"><a href="http://code.google.com/p/jfinal" target="_blank">JFinal web framework</a></div>
			<div id="nav">
				<ul>
					<li><a href="<#noparse>${PATH}</#noparse>"><b>首页</b></a></li>
					<#list tables as table>
					<li><a href="<#noparse>${PATH}</#noparse>/${table.name}"><b>${(table.comment)?default("${table.name}")}管理</b></a></li>
					</#list>
				</ul>
			</div>
		</div>
		<div class="main">
<h1>JFinal Demo 项目首页</h1>
<div class="table_box">
	<p>欢迎来到 JFinal极速开发世界！</p>
	<br><br><br>
	本Demo采用JSP 作为视图文件，您还可以使用FreeMarker、Velocity或自定义类型视图。
	点击<a href="/blog"><b>此处</b></a>开始试用Demo。
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</div>
		</div>
	</div>
</body>
</html>
