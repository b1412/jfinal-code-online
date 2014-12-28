package com.jfinal.ext.codeonline.metadata.impl.db;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.metadata.ViewEngineProvider;
import com.jfinal.ext.codeonline.metadata.impl.db.model.ViewEngine;

import java.util.List;

/**
 * Created by kid on 14-12-21.
 */
public class DbViewEngineProvider implements ViewEngineProvider {
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
