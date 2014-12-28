<#noparse><%layout("/menu.html"){%></#noparse>

<h1>${(table.comment)?default("${table.name}")}管理 ---&gt; 修改${(table.comment)?default("${table.name}")}
</h1>
<div class="form_box">
	<form action="<#noparse>${PATH}</#noparse>/${table.name}/update" method="post">
	<#noparse><%</#noparse>includeFileTemplate("/${table.name}/_form.html"){}<#noparse>%></#noparse>
		
	</form>
</div>
<#noparse><%}%></#noparse>