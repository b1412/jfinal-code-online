package com.jfinal.codeonline.core;

import java.util.Map;

public interface ITemplateHelper {

    void put(String key, Object value);

    void putAll(Map<String, Object> map);

    void exec(String templateFilename, String targetFilename);

}
