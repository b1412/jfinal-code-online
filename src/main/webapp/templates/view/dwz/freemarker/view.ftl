<#noparse>
<@layout>
</#noparse>
<h1>${(entity.comment)?default("${entity.name}")}管理 ---&gt; 查看${(entity.comment)?default("${entity.name}")}
</h1>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
	<#list entity.fields as field> 
	<#if field.isPrimaryKey==1>
		<input type="hidden" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}"  value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>">
	<#else>
		<p>
			<label>${(field.comment)?default("${field.name}")}：</label>
			<input type="text" readonly="readonly" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}" class="textInput" size="30" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>"/>
		</p>
	</#if>
	</#list>
	</div>
</div>
<#noparse>
</@layout>
</#noparse>