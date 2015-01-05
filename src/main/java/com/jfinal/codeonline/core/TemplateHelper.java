package com.jfinal.codeonline.core;

import java.util.Map;

public abstract class TemplateHelper {

    public abstract void put(String key, Object value);

    public abstract void putAll(Map<String, Object> map);

    public abstract void exec(String templateFilename, String targetFilename);

}
