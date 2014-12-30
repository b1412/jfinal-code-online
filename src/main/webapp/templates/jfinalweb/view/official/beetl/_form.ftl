<fieldset class="solid">
	<legend>创建${(table.comment)?default("${table.name}")}</legend>
	<#list table.columns as column> 
	<#if column.primaryKey>
	<input type="hidden" name="${table.name}.${column.name}" id="${column.name}" value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!''}</#noparse>">
	<#else>
	<div class="control-group">
		<div class="controls" >
		<label>${column.name}</label>
		<input type="text" name="${table.name}.${column.name}" id="${table.name}.${column.name}" value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!''}</#noparse>" />
		</div>
	</div>
	</#if>
	</#list>
	<div>
		<label>&nbsp;</label>
		<input value="提交" type="submit">
	</div>
</fieldset>