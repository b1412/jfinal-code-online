package com.jfinal.ext.codeonline.metadata.impl.db;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.metadata.IViewEngineProvider;
import com.jfinal.ext.codeonline.metadata.impl.db.model.ViewEngine;

import java.util.List;

public class DbViewEngineProvider implements IViewEngineProvider {
    @Override
    public List<String> viewEngines() {
        List<String> result = Lists.newArrayList();
        List<ViewEngine> viewEngines = ViewEngine.DAO.findAll();
        for (ViewEngine viewEngine : viewEngines) {
            result.add(viewEngine.getStr("name"));
        }
        return result;
    }
}
