package com.jfinal.codeonline.metadata.db;

import com.jfinal.codeonline.core.IModelProvider;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.project.ProjectItem;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by kid on 15-2-8.
 */
public class DbModelProvider implements IModelProvider {
    @Override
    public Record getProject(Object id) {
        List<ProjectItem> projectItems = ProjectItem.DAO.findByColumn("itemId", id);
        Record project = ProjectItem.DAO.toProject(projectItems);
        project.set("entities", getEntities(project));
        return project;
    }

    @Override
    public Group getGroup(Record project) {
        return Group.DAO.findFirst("select * from `group` where valid = ?", 1);
    }

    @Override
    public List<Record> getEntities(Record project) {
        Group group = getGroup(project);
        List<Record> entities = Db.find("select itemId from entity_item where `groupId` = ?  and projectId =?" +
                " group by itemId order by itemId asc", group.get("id"), project.get("itemId"));
        for (Record entity : entities) {
            List<Record> data = Db.find("select `column`,`value` from entity_item where itemId = ?", entity.getInt("itemId"));
            for (Record item : data) {
                entity.set(item.getStr("column"), item.getStr("value"));
            }
            entity.set("fields", getFields(entity));
        }
        return entities;
    }

    @Override
    public List<Record> getFields(Record entity) {
        Group group = getGroup(null);
        List<Record> fields = Db.find("select itemId from field_item where `groupId` = ?  and entityId =?" +
                " group by itemId order by itemId asc", group.get("id"), entity.get("itemId"));
        for (Record field : fields) {
            List<Record> data = Db.find("select `column`,`value`  from field_item where itemId = ?", field.getInt("itemId"));
            for (Record item : data) {
                String column = item.getStr("column");
                String value = item.getStr("value");
                String type = Db.queryStr("select type from group_metadata where name=? and scope='field'", column);
                if ("int".equals(type) || "boolean".equals(type)) {
                    if (value != null) {
                        field.set(column, Integer.parseInt(value));
                    } else {
                        field.set(column, 0);
                    }
                } else {
                    field.set(column, value);
                }
            }
        }
        return fields;
    }
}
