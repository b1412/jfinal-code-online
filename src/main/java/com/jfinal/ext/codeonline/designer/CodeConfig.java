package com.jfinal.ext.codeonline.designer;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.codeonline.common.AntKit;
import com.jfinal.ext.codeonline.core.Config;
import com.jfinal.ext.codeonline.metadata.impl.db.DbConfigDataProvider;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.SqlReporter;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

import java.io.IOException;

import static com.jfinal.ext.codeonline.core.Constants.FS;

public class CodeConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        loadPropertyFile("config.txt");
        me.setDevMode(getPropertyToBoolean("devMode", false));
        me.setViewType(ViewType.FREE_MARKER);
        afterConfigConstant();
    }

    private void afterConfigConstant() {
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"), getProperty("username"), getProperty(
                "password").trim(), getProperty("driver"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin("init", druidPlugin);
        druidPlugin.start();
        arp.start();
        String ddl = PathKit.getRootClassPath() + FS + "jfinal_code_ddl.sql";
        String metadata = PathKit.getRootClassPath() + FS + "jfinal_code_metadata.sql";
        AntKit.sqlScript(getProperty("driver"), getProperty("jdbcUrl"), getProperty("username"), getProperty(
                "password").trim(), ddl);
        AntKit.sqlScript(getProperty("driver"), getProperty("jdbcUrl"), getProperty("username"), getProperty(
                "password").trim(), metadata);
        druidPlugin.stop();
        arp.stop();


    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
        me.add(new AutoBindRoutes());
    }

    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"), getProperty("username"), getProperty(
                "password").trim(), getProperty("driver"));
        AutoTableBindPlugin arp = new AutoTableBindPlugin(druidPlugin, SimpleNameStyles.LOWER_UNDERLINE);
        arp.setContainerFactory(new CaseInsensitiveContainerFactory(true)).setShowSql(true);
        SqlReporter.setLogger(true);
        me.add(druidPlugin);
        me.add(arp);
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
        Config.setConfigDataProvider(new DbConfigDataProvider());
        Config.setTargetPath(PathKit.getWebRootPath() + FS + "target");
        Config.setTemplatePath(PathKit.getWebRootPath() + FS + "templates");
    }
}
