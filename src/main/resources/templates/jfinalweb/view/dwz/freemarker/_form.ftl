<div class="pageFormContent" layoutH="56">
    <#list entity.fields as field>
        <#if field.isPrimaryKey==1>
            <input type="hidden" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}"
                   value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>">
            <#elseif field.type="Date">
                <p>
                    <label>${(field.comment)?default("${field.name}")}：</label>
                    <input type="text" name="${entity.name}.${field.name}"
                           value="<#noparse>${</#noparse>${entity.name}.${field.name}<#noparse>}</#noparse>"
                           class="date" readonly="true"/>
                    <a class="inputDateButton" href="javascript:;">选择</a>
                </p>
                <#elseif field.type="Datetime">
                    <p>
                        <label>${(field.comment)?default("${field.name}")}：</label>
                        <input type="text" name="${entity.name}.${field.name}"
                               value="<#noparse>${</#noparse>${entity.name}.${field.name}<#noparse>}</#noparse>"
                               dateFmt="yyyy-MM-dd HH:mm:ss"
                               class="date" readonly="true"/>
                        <a class="inputDateButton" href="javascript:;">选择</a>
                    </p>
                    <#elseif field.type="Richtext">
                        <p>
                            <label>${(field.comment)?default("${field.name}")}：</label>
                            <textarea class="editor" name="${entity.name}.${field.name}"
                                      id="${entity.name}.${field.name}"
                                      rows="10" cols="50"
                                      tools="mfull"><#noparse>${</#noparse>${entity.name}.${field.name}<#noparse>}</#noparse>
                            </textarea>
                        </p>
                        <#else>
                            <p>
                                <label>${(field.comment)?default("${field.name}")}：</label>
                                <input type="text" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}"
                                       class="textInput" size="30"
                                       value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>"/>
                            </p>
        </#if>
    </#list>
</div>
<div class="formBar">
    <ul>
        <li>
            <div class="buttonActive">
                <div class="buttonContent">
                    <button type="submit">保存</button>
                </div>
            </div>
        </li>
        <li>
            <div class="button">
                <div class="buttonContent">
                    <button type="button" class="close">取消</button>
                </div>
            </div>
        </li>
    </ul>
</div>
