package com.jfinal.codeonline.core;

import java.util.Map;

public abstract class TemplateHelper {

    public TemplateHelper() {

    }

    public TemplateHelper(String templatesBaseDir) {
        configBaseDir(templatesBaseDir);
    }

    public abstract void configBaseDir(String templatesBaseDir);

    public abstract void put(String key, Object value);

    public abstract void putAll(Map<String, Object> map);

    public abstract void exec(String templateFilename, String targetFilename);

}
