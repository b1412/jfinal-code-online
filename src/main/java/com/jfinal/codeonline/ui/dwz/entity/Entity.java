package com.jfinal.codeonline.ui.dwz.entity;

import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.field.Field;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

@SuppressWarnings("serial")
public class Entity extends ModelExt<Entity> {

    public static final Entity DAO = new Entity();

    public List<Entity> findAll() {
        return find("select * from entity");
    }

    @Override
    public boolean deleteById(Object id) {
        Db.update("delete from field where entityId =?", id);
        return super.deleteById(id);
    }

    public Project getProject() {
        return Project.DAO.findById(getInt("projectId"));
    }

    public List<Field> getFields() {
        return Field.DAO.find("select * from field where entityId = ?", get("id"));
    }

    public Page<Entity> page(int projectId, int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from entity where projectId = ? order by id desc", projectId);
    }

    public void createPrimaryKey() {
        Field field = new Field();
        field.set("name", "id").set("label", "id")
                .set("isPrimaryKey", "1")
                .set("type", "Integer")
                .set("entityId", getInt("id")).save();
    }
}
