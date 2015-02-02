package com.jfinal.codeonline.core;

import com.jfinal.plugin.IPlugin;

public class CodeOnlinePlugin implements IPlugin {
    private CodeOnlineConfig codeOnlineConfig;

    public CodeOnlinePlugin(CodeOnlineConfig codeOnlineConfig) {
        this.codeOnlineConfig = codeOnlineConfig;
    }

    @Override
    public boolean start() {
        Config.projectInitializer(codeOnlineConfig.projectInitializer());
        Config.configDataProvider(codeOnlineConfig.configConfigDataProvider());
        Config.targetPath(codeOnlineConfig.configTargetPath());
        Config.templatePath(codeOnlineConfig.configTemplatePath());
        Config.templateEngine(codeOnlineConfig.configTemplateEngine());
        Config.scriptHelper(codeOnlineConfig.configScriptHelper());
        return true;
    }

    @Override
    public boolean stop() {
        return false;
    }
}
