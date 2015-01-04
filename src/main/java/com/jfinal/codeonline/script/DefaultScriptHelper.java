package com.jfinal.codeonline.script;

import com.jfinal.codeonline.core.ScriptHelper;
import com.jfinal.codeonline.core.GenException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.Set;

public class DefaultScriptHelper implements ScriptHelper {
    private String language;

    public DefaultScriptHelper(String language) {
        this.language = language;
    }

    @Override
    public <T> T exec(String express, Map<String, Object> context) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine scriptEngine = factory.getEngineByName(language);
        Set<Map.Entry<String, Object>> set = context.entrySet();
        Object result;
        for (Map.Entry<String, Object> item : set) {
            String key = item.getKey();
            Object val = item.getValue();
            scriptEngine.put(key, val);
        }
        try {
            result = scriptEngine.eval(express);
        } catch (ScriptException e) {
            throw new GenException("exec expression[" + express + "]fail,context [" + context + "]", e);
        }
        return (T) result;
    }
}
