package com.jfinal.ext.codeonline.common;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;

import java.io.*;
import java.util.Map;

public class TemplateHelper {

    protected Configuration freeMarkerEngine;
    protected SimpleHash context;

    public TemplateHelper(String templateDir) {
        freeMarkerEngine = new Configuration(Configuration.VERSION_2_3_21);
        context = new SimpleHash();
        TemplateLoader loader;
        try {
            loader = new FileTemplateLoader(new File(templateDir));
        } catch (IOException e) {
            throw new GenException(e);
        }
        freeMarkerEngine.setTemplateLoader(loader);
    }

    public void process(String templateFilename, Writer writer) {
        try {
            Template template = freeMarkerEngine.getTemplate(templateFilename, "UTF-8");
            template.process(context, writer);
            writer.close();
        } catch (TemplateException e) {
            throw new GenException("Problem pasre template file :" + templateFilename + " " + e.getMessage(), e);
        } catch (IOException e) {
            throw new GenException("not found template file :" + templateFilename, e);
        }
    }

    public void process(String templateFilename, String outputFilename) {
        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new GenException("Problem pasre template file :" + templateFilename + " " + e.getMessage(), e);
        } catch (FileNotFoundException e) {
            throw new GenException("not found output file :" + templateFilename, e);
        }
        process(templateFilename, out);
    }

    public void put(String key, Object value) {
        if (value instanceof Map)
            value = new SimpleHash((Map) value);
        context.put(key, value);
    }

    public void putAll(Map<String, Object> map) {
        for (String key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    public TemplateHashModel userJavaStaticMethod(String className) {
        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel fileStatics;
        try {
            fileStatics = (TemplateHashModel) staticModels.get(className);
        } catch (TemplateModelException e) {
            throw new RuntimeException(e);
        }
        return fileStatics;
    }

}
