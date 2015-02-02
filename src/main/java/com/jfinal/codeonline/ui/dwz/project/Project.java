package com.jfinal.codeonline.ui.dwz.project;

import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.entity.Entity;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Page;

import java.io.File;
import java.util.List;

import static com.jfinal.codeonline.core.Constants.FS;

@SuppressWarnings("serial")
public class Project extends ModelExt<Project> {

    private static final Logger LOG = Logger.getLogger(Project.class);

    public static final Project DAO = new Project();

    public List<Project> findAll() {
        return find("select * from project");
    }

    public List<Entity> getEntities() {
        return Entity.DAO.find("select * from entity where projectId = ?", getInt("id"));
    }
    @Override
    public boolean deleteById(Object id) {
        List<Entity> entities = Entity.DAO.findByColumn("projectId", id);
        for (Entity entity : entities) {
            Entity.DAO.deleteById(entity.getInt("id"));
        }
        return super.deleteById(id);
    }
    public Page<Project> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from project order by id asc");
    }

    public Group getGroup() {
        return Group.DAO.findById(getInt("groupId"));
    }

    public File rootFile(){
        return new File(Config.targetPath() + FS + get("name"));
    }
}
