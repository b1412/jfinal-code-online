package com.jfinal.codeonline.core;

import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;


public interface IModelProvider {

    /**
     * Get "Project" by id
     *
     * @param id
     * @return
     */
    Record getProject(Object id);

    /**
     * Get "Group" from project
     *
     * @param project
     * @return
     */
    Group getGroup(Record project);

    /**
     * Get all "Entity" of a "Project"
     *
     * @param project
     * @return
     */
    List<Record> getEntities(Record project);

    /**
     * Get all "Field" of a "Entity"
     *
     * @param entity
     * @return
     */
    List<Record> getFields(Record entity);


}
