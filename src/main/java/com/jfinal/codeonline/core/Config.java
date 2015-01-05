package com.jfinal.codeonline.core;

public class Config {

    private static IConfigDataProvider configDataProvider;

    private static String templatePath;

    private static String targetPath;

    private static  ScriptHelper scriptHelper;

    private static  TemplateHelper templateEngine;

    private Config(){

    }

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