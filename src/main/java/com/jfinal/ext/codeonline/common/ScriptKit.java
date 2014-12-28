package com.jfinal.ext.codeonline.common;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.Set;

public class ScriptKit {

    public static <T> T exec(String express, Map<String, Object> root) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine scriptEngine = factory.getEngineByName("groovy");
        Set<Map.Entry<String, Object>> set = root.entrySet();
        Object result = null;
        for (Map.Entry<String, Object> item : set) {
            String key = item.getKey();
            Object val = item.getValue();
            scriptEngine.put(key, val);
        }
        try {
            result = scriptEngine.eval(express);
        } catch (ScriptException e) {
            throw new GenException("exec expression[" + express + "]fail", e);
        }
        return (T) result;
    }

}