package com.jfinal.ext.codeonline.designer.field;

import com.jfinal.ext.codeonline.designer.entity.Entity;
import com.jfinal.ext.codeonline.designer.project.Project;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Field extends Model<Field> {

    public static final Field DAO = new Field();

    public Page<Field> page(int entityId, int pageNumber, int pageSize) {

        return paginate(pageNumber, pageSize, "select *", "from field where entity_id = ? order by id desc", entityId);
    }

    public Entity getEntity() {
        return Entity.DAO.findById(getInt("entity_id"));
    }

    public String getDbColumnType(String database) {
        String fieldType = getStr("fieldType");
        String result = Db.queryStr("select column_type from data_type where database_name = ? and field_type =?", database, fieldType);
        if (StrKit.isBlank(result)) {
            throw new RuntimeException("field_type(" + fieldType + ") can match any column_type in database(" + database + ")");
        }
        return result;
    }

    public String getDbColumnType(Project project) {
        return getDbColumnType(project.getDbType());
    }
}
