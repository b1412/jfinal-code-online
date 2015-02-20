
package com.jfinal.codeonline.core;

public abstract class CodeOnlineConfig {

    public abstract IProjectInitializer projectInitializer();

    public abstract IConfigDataProvider configConfigDataProvider();

    public abstract IModelProvider modelProvider();

    public abstract String configTemplatePath();

    public abstract String configTargetPath();

    public abstract IScriptHelper configScriptHelper();

    public abstract ITemplateHelper configTemplateEngine();


}