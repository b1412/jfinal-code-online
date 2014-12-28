<#noparse>
<#include "/menu.html"/>
<@layout>
</#noparse>
<h1>${entity.label}管理&nbsp;&nbsp;
<a href="<#noparse>${PATH}</#noparse>/${entity.name}/add">创建${entity.label}</a>
</h1>
<div class="table_box">
	<table class="list">
		<tbody>
			<tr>
				<#list entity.fields as field>
				<#if field.isPrimaryKey==0>
				<th>${field.label}</th>
				</#if>
				</#list>
				<th width="12%">操作</th>
			</tr>
			
			<#noparse><#list page.list as item></#noparse>
			<tr>
				<#list entity.fields as field>
				<#if field.isPrimaryKey==0>
				<td style="text-align:left;">
					<#noparse>${(</#noparse>item.${field.name}<#noparse>)!}</#noparse>
				</td>
				</#if>
				</#list>
				<td style="text-align:left;">
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${entity.name}/view/<#noparse>${item.id}</#noparse>">查看</a>
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${entity.name}/edit/<#noparse>${item.id}</#noparse>">编辑</a>
					&nbsp;&nbsp;<a href="<#noparse>${PATH}</#noparse>/${entity.name}/delete/<#noparse>${item.id}</#noparse>">删除</a>
				</td>
			</tr>
			<#noparse></#list></#noparse>
		</tbody>
	</table>
<#noparse>
	<#include "/common/_paginate.html" />
	<@paginate currentPage=page.pageNumber totalPage=page.totalPage actionUrl="${PATH}/</#noparse>${entity.name}<#noparse>/" /></#noparse>
</div>
<#noparse>
</@layout>
</#noparse>