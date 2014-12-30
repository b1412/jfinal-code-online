package ${project.packageName}.${entity.name?lower_case};

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import java.util.Date;
import com.jfinal.log.Logger;

@SuppressWarnings("serial")
<#assign entityName= entity.name?lower_case?cap_first/>
public class ${entityName} extends Model<${entityName}> {

    private static final Logger LOG = Logger.getLogger(${entityName}.class);

	public static final ${entityName} DAO = new ${entityName}();
	
	<#if _accessor == 1>
	<#list entity.fields as field>
	public ${field.fieldType?lower_case?cap_first} get${field.name?cap_first}(){
		return get("${field.name}");
	}
	public void set${field.name?cap_first}(${field.fieldType?lower_case?cap_first} ${field.name}){
		set("${field.name}",${field.name});
	}
	</#list>
	</#if>
	
	public Page<${entityName}> page(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *","from ${entityName?lower_case} order by id asc");
	}
}