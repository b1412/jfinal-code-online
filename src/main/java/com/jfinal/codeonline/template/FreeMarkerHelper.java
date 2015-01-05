package com.jfinal.codeonline.template;

import com.jfinal.codeonline.core.GenException;
import com.jfinal.codeonline.core.TemplateHelper;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

import static com.jfinal.core.Const.DEFAULT_ENCODING;

public class FreeMarkerHelper extends TemplateHelper {
    protected Configuration freeMarkerEngine;
    protected SimpleHash context;

    public FreeMarkerHelper(String templatesBaseDir) {
        freeMarkerEngine = new Configuration(Configuration.VERSION_2_3_21);
        context = new SimpleHash();
        TemplateLoader loader;
        try {
            loader = new FileTemplateLoader(new File(templatesBaseDir));
        } catch (IOException e) {
            throw new GenException(e);
        }
        freeMarkerEngine.setTemplateLoader(loader);
    }

    @Override
    public void put(String key, Object value) {
        if (value instanceof Map) {
            value = new SimpleHash((Map) value);
        }
        context.put(key, value);
    }

    @Override
    public void putAll(Map<String, Object> map) {
        for (String key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    @Override
    public void exec(String templateFilename, String targetFilename) {
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFilename), DEFAULT_ENCODING));
            Template template = freeMarkerEngine.getTemplate(templateFilename, DEFAULT_ENCODING);
            template.process(context, out);
        } catch (Exception e) {
            throw new GenException("parse template file [" + templateFilename + "] error", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new GenException(e);
                }
            }
        }
    }


}
