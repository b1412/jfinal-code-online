
<div class="pageContent">
	<form method="post" action="<#noparse>${PATH}</#noparse>/${entity.name}/update" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<#noparse>
		<#include "_form.html">
		</#noparse>
	</form>
</div>