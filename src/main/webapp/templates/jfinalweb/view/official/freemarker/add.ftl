<#noparse><#include "/menu.html" />
<@layout>
</#noparse>
<h1>${(entity.comment)?default("${entity.name}")}管理 ---&gt; 创建${(entity.comment)?default("${entity.name}")}
</h1>
<div class="form_box">
	<form action="<#noparse>${PATH}</#noparse>/${entity.name}/save" method="post">
<#noparse>
		<#include "_form.html" />
	</form>
</div>
</@layout>
</#noparse>