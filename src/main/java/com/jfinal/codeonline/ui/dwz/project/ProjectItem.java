package com.jfinal.codeonline.ui.dwz.project;

import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.entity.EntityItem;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.plugin.activerecord.Record;

import java.io.File;
import java.util.List;

import static com.jfinal.codeonline.core.Constants.FS;

@SuppressWarnings("serial")
public class ProjectItem extends ModelExt<ProjectItem> {

    public static final ProjectItem DAO = new ProjectItem();

    public List<ProjectItem> findAll() {
        return find("select * from project");
    }

    public Record toProject(List<ProjectItem> projectItems) {
        Record project = new Record();
        for (ProjectItem item : projectItems) {
            project.set(item.getStr("column"), item.getStr("value"));
            project.set("itemId", item.get("itemId"));
        }
        return project;
    }


    @Override
    public boolean deleteById(Object id) {
        List<EntityItem> entities = EntityItem.DAO.findByColumn("projectId", id);
        for (EntityItem entity : entities) {
            EntityItem.DAO.deleteById(entity.getInt("id"));
        }
        return super.deleteById(id);
    }

    public Group getGroup() {
        return Group.DAO.findById(getInt("groupId"));
    }

    public File rootFile() {
        return new File(Config.targetPath() + FS + get("name"));
    }


}
