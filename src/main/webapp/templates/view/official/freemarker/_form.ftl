<fieldset class="solid">
	<legend>创建${(entity.comment)?default("${entity.name}")}</legend>
	<#list entity.fields as field> 
	<#if field.isPrimaryKey==1>
	<input type="hidden" name="${entity.name}.${field.name}" id="${field.name}" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>">
	<#else>
	<div class="control-group">
		<div class="controls" >
		<label>${(field.comment)?default("${field.name}")}</label>
		<input type="text" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>" />
		</div>
	</div>
	</#if>
	</#list>
	<div>
		<label>&nbsp;</label>
		<input value="提交" type="submit">
	</div>
</fieldset>