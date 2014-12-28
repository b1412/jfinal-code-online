package ${project.packageName};

<#list entities as entity>
import ${project.packageName}.${entity.name?lower_case}.*;
</#list>

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.activerecord.dialect.${project.dbType?cap_first}Dialect;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.render.ViewType;
public class ${project.name?cap_first}Config extends JFinalConfig {
	
	public void configConstant(Constants me) {
		loadPropertyFile("config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	    <#if project.viewEngine=="freemarker">
		me.setViewType(ViewType.FREE_MARKER);
		</#if>
	}
	
	public void configRoute(Routes me) {
		me.add("/",IndexController.class);
		<#list entities as entity>
		me.add("/${entity.name}", ${entity.name?capitalize}Controller.class);
		</#list>
	}
	
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcurl"),getProperty("username"),getProperty("password").trim(),getProperty("driver"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setDialect(new ${project.dbType?cap_first}Dialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory()).setShowSql(true);
	<#list entities as entity>
		<#list entity.fields as field>
		<#if field.isPrimaryKey==1 && field.name !="id">
		arp.addMapping("${entity.name?lower_case}","${field.name}", ${entity.name?cap_first}.class);	
		<#elseif field.isPrimaryKey==1>
		arp.addMapping("${entity.name?lower_case}", ${entity.name?cap_first}.class);
		</#if>
		</#list>
	</#list>
	
		me.add(druidPlugin);
		me.add(arp);
	}
	
	public void configInterceptor(Interceptors me) {
        me.add(new TxByRegex("/.*[save|delete|update].*"));
    }
	
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("PATH"));
	}
	
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", ${project.port}, "/${project.name}", 5);
	}
}
