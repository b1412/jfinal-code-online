<#noparse><%layout("/menu.html"){%></#noparse>

<h1>${(table.comment)?default("${table.name}")}管理&nbsp;&nbsp;
<a href="<#noparse>${PATH}</#noparse>/${table.name}/add">创建${(table.comment)?default("${table.name}")}</a>
</h1>
<div class="table_box">
	<table class="list">
		<tbody>
			<tr>
				<#list table.columns as column>
				<#if !column.primaryKey>
				<th>${(column.comment)?default("${column.name}")}</th>
				</#if>
				</#list>
				<th width="12%">操作</th>
			</tr>
			
			<#noparse><%for(item in page.list){ %></#noparse>
			<tr>
				<#list table.columns as column>
				<#if !column.primaryKey>
				<td style="text-align:left;">
					<#noparse>${</#noparse>item.${column.name}<#noparse>!}</#noparse>
				</td>
				</#if>
				</#list>
				<td style="text-align:left;">
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${table.name}/view/<#noparse>${item.id}</#noparse>">查看</a>
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${table.name}/edit/<#noparse>${item.id}</#noparse>">编辑</a>
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${table.name}/delete/<#noparse>${item.id}</#noparse>">删除</a>
				</td>
			</tr>
			<#noparse><%}%></#noparse>
		</tbody>
	</table>
<#noparse>
	<%includeFileTemplate("/common/_paginate.html",
		{"currentPage":page.pageNumber,"totalPage":page.totalPage,"actionUrl":PATH+"/</#noparse>${table.name}<#noparse>/"}) {}</#noparse>
	 %>
</div>
<#noparse>
<%}%>
</#noparse>