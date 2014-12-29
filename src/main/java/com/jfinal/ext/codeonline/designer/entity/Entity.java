package com.jfinal.ext.codeonline.designer.entity;

import com.jfinal.ext.codeonline.designer.field.Field;
import com.jfinal.ext.codeonline.designer.project.Project;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

@SuppressWarnings("serial")
public class Entity extends ModelExt<Entity> {

    public static final Entity DAO = new Entity();

    public List<Entity> findAll() {
        return find("select * from entity");
    }

    public String getName() {
        return get("name");
    }

    public String getComment() {
        return get("comment");
    }

    public Project getProject() {
        return Project.DAO.findById(getInt("project_id"));
    }

    public List<Field> getFields() {
        return Field.DAO.find("select * from field where entity_id = ?", get("id"));
    }

    public Page<Entity> page(int projectId,int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from entity where project_id = ? order by id desc",projectId);
    }

    public void createPrimaryKey() {
        Field field = new Field();
        field.set("name","id").set("label","id")
                .set("isDisplayable","0")
                .set("isPrimaryKey","1")
                .set("isSearchable","0")
                .set("isReadOnly","1")
                .set("isHidden","1")
                .set("fieldType","Integer")
                .set("entity_id",getInt("id")).save();
    }
}
