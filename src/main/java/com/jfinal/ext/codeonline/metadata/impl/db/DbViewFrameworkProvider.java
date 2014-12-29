package com.jfinal.ext.codeonline.metadata.impl.db;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.metadata.ViewFrameworkProvider;
import com.jfinal.ext.codeonline.metadata.impl.db.model.ViewFramework;

import java.util.List;

public class DbViewFrameworkProvider implements ViewFrameworkProvider {
    @Override
    public List<String> viewFrameWorks() {
        List<String> result = Lists.newArrayList();
        List<ViewFramework> viewFrameworks = ViewFramework.DAO.findAll();
        for (ViewFramework viewFramework : viewFrameworks) {
            result.add(viewFramework.getStr("name"));
        }
        return result;
    }
}
