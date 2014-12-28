<#noparse><#include "/menu.html" />
<@layout>
</#noparse>
<h1>${(entity.comment)?default("${entity.name}")}管理 ---&gt; 修改${(entity.comment)?default("${entity.name}")}
</h1>
<div class="form_box">
	<form action="<#noparse>${PATH}</#noparse>/${entity.name}/update" method="post">
<#noparse>
		<#include "_form.html" />
	</form>
</div>
</@layout>
</#noparse>