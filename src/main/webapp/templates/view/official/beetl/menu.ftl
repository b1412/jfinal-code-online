
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
			<#noparse>${layoutContent}</#noparse>
		</div>
	</div>
</body>
</html>
