package com.jfinal.ext.codeonline.core;

import com.jfinal.ext.codeonline.metadata.ConfigDataProvider;
import com.jfinal.ext.codeonline.metadata.impl.db.DbConfigDataProvider;
import com.jfinal.kit.PathKit;

import static com.jfinal.ext.codeonline.core.Constants.FS;

public class Config {

    static private ConfigDataProvider configDataProvider = new DbConfigDataProvider();
    static private String templatePath = PathKit.getWebRootPath() + FS + "template";
    static private String targetPath = PathKit.getWebRootPath() + FS + "target";

    public static ConfigDataProvider getConfigDataProvider() {
        return configDataProvider;
    }

    public static void setConfigDataProvider(ConfigDataProvider configDataProvider) {
        Config.configDataProvider = configDataProvider;
    }

    public static String getTemplatePath() {
        return templatePath;
    }

    public static void setTemplatePath(String templatePath) {
        Config.templatePath = templatePath;
    }

    public static String getTargetPath() {
        return targetPath;
    }

    public static void setTargetPath(String targetPath) {
        Config.targetPath = targetPath;
    }
}