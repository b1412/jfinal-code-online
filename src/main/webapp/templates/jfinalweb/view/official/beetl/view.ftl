<#noparse><%layout("/menu.html"){%></#noparse>

<h1>${table.comment?default("${table.name}")}管理 ---&gt; 查看${(table.comment)?default("${table.name}")}
</h1>
<div class="form_box">
<fieldset class="solid">
	<legend>查看${(table.comment)?default("${table.name}")}</legend>
	<#list table.columns as column> 
	<#if column.primaryKey>
	<input type="hidden" name="${table.name}.${column.name}" id="${column.name}" value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!}</#noparse>">
	<#else>
	<div class="control-group">
		<div class="controls" >
		<label>${column.name}</label>
		<input type="text" readonly="readonly" name="${table.name}.${column.name}" id="${table.name}.${column.name}" value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!}</#noparse>" />
		</div>
	</div>
	</#if>
	</#list>
</fieldset>
</div>
<#noparse><%}%></#noparse>