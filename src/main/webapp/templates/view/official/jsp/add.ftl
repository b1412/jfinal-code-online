<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<#noparse><%@ include file="/menu.jsp"%>
</#noparse>
<h1>${(table.comment)?default("${table.name}")}管理 ---&gt; 创建${(table.comment)?default("${table.name}")}
</h1>
<div class="form_box">
	<form action="<#noparse>${PATH}</#noparse>/${table.name}/save" method="post">
<#noparse>
		<%@ include file="_form.jsp"%>
	</form>
</div>
</#noparse>