package com.jfinal.codeonline.core;

public class Config {

    private static IProjectInitializer projectInitializer;

    private static IConfigDataProvider configDataProvider;

    private static String templatePath;

    private static String targetPath;

    private static IScriptHelper scriptHelper;

    private static ITemplateHelper templateEngine;

    private Config() {

    }

    public static IProjectInitializer projectInitializer() {
        return projectInitializer;
    }

    static void projectInitializer(IProjectInitializer projectInitializer) {
        Config.projectInitializer = projectInitializer;
    }

    public static IConfigDataProvider configDataProvider() {
        return configDataProvider;
    }

    static void configDataProvider(IConfigDataProvider configDataProvider) {
        Config.configDataProvider = configDataProvider;
    }

    public static String templatePath() {
        return templatePath;
    }

    static void templatePath(String templatePath) {
        Config.templatePath = templatePath;
    }

    public static String targetPath() {
        return targetPath;
    }

    static void targetPath(String targetPath) {
        Config.targetPath = targetPath;
    }

    public static IScriptHelper scriptHelper() {
        return scriptHelper;
    }

    static void scriptHelper(IScriptHelper scriptHelper) {
        Config.scriptHelper = scriptHelper;
    }

    public static ITemplateHelper templateEngine() {
        return templateEngine;
    }

    static void templateEngine(ITemplateHelper templateEngine) {
        Config.templateEngine = templateEngine;
    }
}