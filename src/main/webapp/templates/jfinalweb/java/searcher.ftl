package ${project.packageName}.${entity.name?lower_case};

import com.jfinal.ext.interceptor.pageinfo.PageInfoInterceptor;

public class ${entity.name?cap_first}Searcher extends PageInfoInterceptor {
	public void config() {
		setModel(${entity.name?cap_first}.class);
	}
}
