package com.jfinal.codeonline.metadata.db.model;

import com.google.common.collect.Lists;
import com.jfinal.ext.kit.ModelExt;

import java.util.List;

public class UtilityClass extends ModelExt<UtilityClass> {
    public static final UtilityClass DAO = new UtilityClass();

    public List<String> findAllNames() {
        List<UtilityClass> utilityClasses = UtilityClass.DAO.findAll();
        List<String> classNames = Lists.newArrayList();
        for (UtilityClass utilityClass : utilityClasses) {
            classNames.add(utilityClass.getStr("className"));
        }
        return classNames;
    }

}
