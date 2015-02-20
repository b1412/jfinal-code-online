package com.jfinal.codeonline.ui.dwz;

import com.jfinal.codeonline.core.CodeOnlinePlugin;
import com.jfinal.codeonline.ext.MyCodeOnlineConfig;
import com.jfinal.codeonline.ui.dwz.common.BaseController;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.druid.DruidPlugin;

import java.io.IOException;

public class CodeConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        loadPropertyFile("app.txt");
        me.setDevMode(getPropertyToBoolean("devMode", false));
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
        atbp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
        atbp.addScanPackages("com.jfinal.codeonline.ui.dwz");
//        atbp.setDialect(new MysqlDialect());
        SqlReporter.setLogger(true);
        me.add(druidPlugin);
        me.add(atbp);
        me.add(new CodeOnlinePlugin(new MyCodeOnlineConfig()));
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

}
