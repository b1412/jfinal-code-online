<#noparse><%layout("/menu.html"){%></#noparse>

<h1>${(table.comment)?default("${table.name}")}管理 ---&gt; 创建${(table.comment)?default("${table.name}")}
</h1>
<div class="form_box">
	<form action="<#noparse>${PATH}</#noparse>/${table.name}/save" method="post">
	<#noparse><%includeFileTemplate</#noparse>("/${table.name}/_form.html")<#noparse>{}%></#noparse>
		
	</form>
</div>
<#noparse><%}%></#noparse>