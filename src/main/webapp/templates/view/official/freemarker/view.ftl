<#noparse><#include "/menu.html" />
<@layout>
</#noparse>
<h1>${(entity.comment)?default("${entity.name}")}管理 ---&gt; 查看${(entity.comment)?default("${entity.name}")}
</h1>
<div class="form_box">
<fieldset class="solid">
	<legend>查看${(entity.comment)?default("${entity.name}")}</legend>
	<#list entity.fields as field> 
	<#if field.isPrimaryKey==1>
	<input type="hidden" name="${entity.name}.${field.name}" id="${field.name}" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>">
	<#else>
	<div class="control-group">
		<div class="controls" >
		<label>${(field.comment)?default("${field.name}")}</label>
		<input type="text" readonly="readonly" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>" />
		</div>
	</div>
	</#if>
	</#list>
</fieldset>
</div>
<#noparse>
</@layout>
</#noparse>