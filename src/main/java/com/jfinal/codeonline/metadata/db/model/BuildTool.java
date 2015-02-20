package com.jfinal.codeonline.metadata.db.model;

import com.google.common.collect.Lists;
import com.jfinal.ext.kit.ModelExt;

import java.util.List;

public class BuildTool extends ModelExt<BuildTool> {
    public static final BuildTool DAO = new BuildTool();

    public List<String> findAllNames() {
        List<BuildTool> buildTools = BuildTool.DAO.findAll();
        List<String> names = Lists.newArrayList();
        for (BuildTool buildTool : buildTools) {
            names.add(buildTool.getStr("name"));
        }
        return names;
    }

}
