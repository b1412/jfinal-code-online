package ${project.packageName}.${entity.name?lower_case};

import com.jfinal.core.Controller;
import com.jfinal.ext.render.excel.PoiRender;
import com.jfinal.aop.Before;
import java.util.List;

<#if project.viewFramework="dwz">
import com.jfinal.ext.render.DwzRender;
</#if>

public class ${entity.name?cap_first}Controller extends Controller {
    @Before(value = ${entity.name?cap_first}Searcher.class)
	public void index() {
    //setAttr("page", ${entity.name?cap_first}.DAO.page(getParaToInt("pageNum",1),getParaToInt("numPerPage",10)));
    }
	
	public void add() {
	}

	@Before(value = ${entity.name?cap_first}Validator.class)
	public void save() {
		${entity.name?cap_first} ${entity.name?lower_case} = getModel(${entity.name?cap_first}.class);
		// oracle ${entity.name?lower_case}.set("ID", UUID.randomUUID().toString());
		${entity.name?lower_case}.save();
		<#if project.viewFramework="dwz">
        render(DwzRender.closeCurrentAndRefresh("rel_${entity.name}"));
		<#else>
        forwardAction("/${entity.name}");
        </#if>
	}
	
	public void edit() {
		setAttr("${entity.name}", ${entity.name?cap_first}.DAO.findById(getPara(0)));
	}
	public void view() {
		setAttr("${entity.name}", ${entity.name?cap_first}.DAO.findById(getPara(0)));
	}
	
	public void update() {
		getModel(${entity.name?cap_first}.class).update();
        <#if project.viewFramework="dwz">
        render(DwzRender.closeCurrentAndRefresh("rel_${entity.name}"));
		<#else>
        forwardAction("/${entity.name}");
        </#if>
	}
	
	public void delete() {
		${entity.name?cap_first}.DAO.deleteById(getPara(0));
        <#if project.viewFramework="dwz">
			render(DwzRender.success());
		<#else>
        forwardAction("/${entity.name}");
        </#if>
	}
	public void excel() {
		List<${entity.name?cap_first}> data = ${entity.name?cap_first}.DAO.page(getParaToInt("pageNum",1),getParaToInt("numPerPage",10)).getList();
		String[] headers = new String[]{<#list entity.fields as field><#if field.isPrimaryKey!=1>"${field.name}"<#if field_has_next>,</#if></#if></#list>};
		String[] columns = new String[]{<#list entity.fields as field><#if field.isPrimaryKey!=1>"${field.name}"<#if field_has_next>,</#if></#if></#list>};
	    render(PoiRender.me(data).headers(headers).columns(columns).fileName("${entity.name}.xls"));
	}
}


