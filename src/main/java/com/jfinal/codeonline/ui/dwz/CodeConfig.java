package com.jfinal.codeonline.ui.dwz;

import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.metadata.db.DbConfigDataProvider;
import com.jfinal.codeonline.script.DefaultScriptHelper;
import com.jfinal.codeonline.template.FreeMarkerHelper;
import com.jfinal.codeonline.ui.dwz.common.BaseController;
import com.jfinal.codeonline.ui.dwz.entity.Entity;
import com.jfinal.codeonline.ui.dwz.field.Field;
import com.jfinal.codeonline.ui.dwz.group.Groups;
import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.codeonline.ui.dwz.task.TaskParam;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

import java.io.IOException;

import static com.jfinal.codeonline.core.Constants.FS;

public class CodeConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        loadPropertyFile("app.txt");
        me.setDevMode(getPropertyToBoolean("devMode", false));
        me.setViewType(ViewType.FREE_MARKER);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add(new AutoBindRoutes().addExcludeClasses(BaseController.class));
    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"), getProperty("username"), getProperty(
                "password").trim(), getProperty("driver"));
        AutoTableBindPlugin atbp = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
        atbp.setContainerFactory(new CaseInsensitiveContainerFactory(true)).setShowSql(true);
//        atbp.addScanPackages("com.jfinal.codeonline.ui.dwz");
        me.add(druidPlugin);
        atbp.autoScan(false);
        atbp.addMapping("project", Project.class);
        atbp.addMapping("entity",Entity.class);
        atbp.addMapping("field",Field.class);
        atbp.addMapping("groups",Groups.class);
        atbp.addMapping("task",Task.class);
        atbp.addMapping("task_param",TaskParam.class);
        me.add(atbp);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new TxByRegex("/.*[save|delete|update].*"));
    }

    @Override
    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("PATH"));
    }

    public static void main(String[] args) throws IOException {
        JFinal.start("src/main/webapp", 8888, "/", 5);
    }

    @Override
    public void afterJFinalStart() {

        Config.configDataProvider(new DbConfigDataProvider());

        Config.templatePath(PathKit.getRootClassPath() + FS + "templates");

        Config.targetPath(PathKit.getWebRootPath() + FS + "target");

        Config.scriptHelper(new DefaultScriptHelper("groovy"));

        Config.templateEngine(new FreeMarkerHelper(Config.templatePath()));
    }
}
