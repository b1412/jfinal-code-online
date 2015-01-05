package com.jfinal.codeonline.ui.dwz.project;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.ext.TaskKit;
import com.jfinal.codeonline.ui.dwz.entity.Entity;
import com.jfinal.codeonline.ui.dwz.group.Groups;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

@SuppressWarnings("serial")
public class Project extends ModelExt<Project> {

    private static final Logger LOG = Logger.getLogger(Project.class);

    public static final Project DAO = new Project();

    public List<Project> findAll() {
        return find("select * from project");
    }

    public List<Entity> getEntities() {
        return Entity.DAO.find("select * from entity where projectId = ?", get("id"));
    }
    @Override
    public boolean deleteById(Object id) {
        Entity entity = Entity.DAO.findFirstByColumn("projectId",id);
        Entity.DAO.deleteById(entity.getInt("id"));
        return super.deleteById(id);
    }
    public Page<Project> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from project order by id asc");
    }

    public List<String> runGroup(Groups group) {
        List<String> paths = Lists.newArrayList();
        for (Task task : group.tasks()) {
            paths.addAll(TaskKit.processTask(this, task));
        }
        return paths;
    }

    public Groups getGroups() {
        return Groups.DAO.findById(getInt("groupsId"));
    }
}
