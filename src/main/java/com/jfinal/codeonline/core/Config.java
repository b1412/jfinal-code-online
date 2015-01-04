package com.jfinal.codeonline.core;

public class Config {

    static private IConfigDataProvider configDataProvider;

    static private String templatePath;

    static private String targetPath;

    static private ScriptHelper scriptHelper;

    static private TemplateHelper templateEngine;

    public static IConfigDataProvider configDataProvider() {
        return configDataProvider;
    }

    public static void configDataProvider(IConfigDataProvider configDataProvider) {
        Config.configDataProvider = configDataProvider;
    }

    public static String templatePath() {
        return templatePath;
    }

    public static void templatePath(String templatePath) {
        Config.templatePath = templatePath;
    }

    public static String targetPath() {
        return targetPath;
    }

    public static void targetPath(String targetPath) {
        Config.targetPath = targetPath;
    }

    public static ScriptHelper scriptHelper() {
        return scriptHelper;
    }

    public static void scriptHelper(ScriptHelper scriptHelper) {
        Config.scriptHelper = scriptHelper;
    }

    public static TemplateHelper templateEngine() {
        return templateEngine;
    }

    public static void templateEngine(TemplateHelper templateEngine) {
        Config.templateEngine = templateEngine;
    }
}