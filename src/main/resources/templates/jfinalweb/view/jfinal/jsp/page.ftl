<#noparse>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/menu.jsp"%>
</#noparse>
<h1>${(table.comment)?default("${table.name}")}管理&nbsp;&nbsp;
<a href="<#noparse>${PATH}</#noparse>/${table.name}/add">创建${(table.comment)?default("${table.name}")}</a>
</h1>
<div class="table_box">
	<table class="list">
		<tbody>
			<tr>
				<#list entity.fields as field>
				<#if !field.primaryKey>
				<th>${field.comment}</th>
				</#if>
				</#list>
				<th width="12%">操作</th>
			</tr>
			
			<#noparse><c:forEach items="${page}" var="item"></#noparse>
			<tr>
				<#list table.fields as field>
				<#if !field.primaryKey>
				<td style="text-align:left;">
					<#noparse>${</#noparse>item.${field.name}<#noparse>}</#noparse>
				</td>
				</#if>
				</#list>
				<td style="text-align:left;">
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${entity.name}/view/<#noparse>${item.id}</#noparse>">查看</a>
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${entity.name}/edit/<#noparse>${item.id}</#noparse>">编辑</a>
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${entity.name}/delete/<#noparse>${item.id}</#noparse>">删除</a>
				</td>
			</tr>
			<#noparse></c:forEach></#noparse>
		</tbody>
	</table>
</div>