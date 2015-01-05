<form id="pagerForm" method="post" action="<#noparse>${PATH}</#noparse>/${entity.name}/">
    <input type="hidden" name="pageNum" value="<#noparse>${page.pageNumber}</#noparse>"/>
    <input type="hidden" name="numPerPage" value="<#noparse>${page.pageSize}</#noparse>"/>

</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="<#noparse>${PATH}</#noparse>/${entity.name}" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <#list entity.fields as field>
                        <#if field.isSearchable==1>
                            <td>
                                <#if field.type=="Integer">
                                    ${field.label}:<input type="text" name="${entity.name}.f_${field.name}" />
                                    <#elseif field.type="Datetime">
                                    <#elseif field.type="Date">
                                        ${field.label}:<input type="text" name="${entity.name}.f_${field.name}" class="date" readonly="true" />
                                        <#else>
                                            ${field.label}:<input type="text" name="${entity.name}.f_${field.name}" />
                                            <input type="hidden" name="${entity.name}.f_${field.name}_op" value="LIKE" />
                                </#if>
                            </td>
                        </#if>
                    </#list>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="<#noparse>${PATH}</#noparse>/${entity.name}/add" target="navTab"
                   rel="rel_${entity.name}_add"><span>添加</span></a></li>
            <li><a class="delete" href="<#noparse>${PATH}</#noparse>/${entity.name}/delete/{sid_user}"
                   rel="rel_${entity.name}_delete" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="edit" href="<#noparse>${PATH}</#noparse>/${entity.name}/edit/{sid_user}" target="navTab"
                   rel="rel_${entity.name}_edit"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="icon" href="<#noparse>${PATH}</#noparse>/${entity.name}/excel" target="dwzExport"
                   targetType="navTab" rel="rel_${entity.name}_dowmload" title="实要导出这些记录吗?"><span>导出EXCEL</span></a>
            </li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <#list entity.fields as field>
                <#if field.isPrimaryKey==0>
                    <th width="80" align="center">${(field.comment)?default("${field.name}")}</th>
                </#if>
            </#list>
        </tr>
        </thead>
        <tbody>
        <#noparse>
            <#list page.list as item>
        </#noparse>
        <tr target="sid_user" rel="<#noparse>${item.id}</#noparse>">
            <#list entity.fields as field>
                <#if field.isPrimaryKey!=1>
                    <td>
                        <#noparse>${</#noparse>item.${field.name}<#noparse>}</#noparse>
                    </td>
                </#if>
            </#list>
        </tr>
        <#noparse></#list></#noparse>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
            <span>条，共<#noparse>${page.totalRow}</#noparse>条</span>
        </div>

        <div class="pagination" targetType="navTab" totalCount="<#noparse>${page.totalRow}</#noparse>"
             numPerPage="<#noparse>${page.pageSize}</#noparse>" pageNumShown="<#noparse>${page.pageSize}</#noparse>"
             currentPage="<#noparse>${page.pageNumber}</#noparse>"></div>

    </div>
</div>


