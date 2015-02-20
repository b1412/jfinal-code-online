package com.jfinal.codeonline.ext;

import com.jfinal.codeonline.core.*;
import com.jfinal.codeonline.metadata.db.DbConfigDataProvider;
import com.jfinal.codeonline.metadata.db.DbModelProvider;
import com.jfinal.codeonline.script.DefaultScriptHelper;
import com.jfinal.codeonline.template.FreeMarkerHelper;
import com.jfinal.kit.PathKit;

import static com.jfinal.codeonline.core.Constants.FS;

public class MyCodeOnlineConfig extends CodeOnlineConfig {

    @Override
    public IProjectInitializer projectInitializer() {
        return new GradleInitializer();
    }

    @Override
    public IConfigDataProvider configConfigDataProvider() {
        return new DbConfigDataProvider();
    }

    @Override
    public IModelProvider modelProvider() {
        return new DbModelProvider();
    }

    @Override
    public String configTemplatePath() {
        return PathKit.getRootClassPath() + FS + "templates";
    }

    @Override
    public String configTargetPath() {
        return PathKit.getWebRootPath() + FS + "target";
    }

    @Override
    public IScriptHelper configScriptHelper() {
        return new DefaultScriptHelper("groovy");
    }

    @Override
    public ITemplateHelper configTemplateEngine() {
        return new FreeMarkerHelper(Config.templatePath());
    }
}
