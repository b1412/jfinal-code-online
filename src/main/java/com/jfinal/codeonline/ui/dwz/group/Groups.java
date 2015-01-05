package com.jfinal.codeonline.ui.dwz.group;

import com.google.common.base.Joiner;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class Groups extends ModelExt<Groups> {
    public static final Groups DAO = new Groups();

    public List<Task> tasks() {
        return Task.DAO.find("select t.* from task t " +
                "left join groups_task_relation  r on(t.id = r.taskId) " +
                "left join groups g on(r.groupsId = g.id) where g.id = ?", getInt("id"));
    }

    public String taskIds() {
        List<Integer> ids = Db.query("select t.id from task t " +
                "left join groups_task_relation  r on(t.id = r.taskId) " +
                "left join groups g on(r.groupsId = g.id) where g.id = ?", getInt("id"));
        return Joiner.on(",").join(ids);
    }

    public String taskNames() {
        List<Integer> ids = Db.query("select t.taskname from task t " +
                "left join groups_task_relation  r on(t.id = r.taskId) " +
                "left join groups g on(r.groupsId = g.id) where g.id = ?", getInt("id"));
        return Joiner.on(",").join(ids);
    }

    public Page<Groups> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from groups order by id desc");
    }

    @Override
    public boolean deleteById(Object id) {
        deleteTasks(id);
        return super.deleteById(id);
    }

    @Override

    public List<Groups> findAll() {
        return DAO.find("select * from task where valid = 1");
    }

    public void deleteTasks(Object id) {
        Db.update("delete from groups_task_relation where groupsId = ?", id);
    }

    public void deleteTasks() {
        deleteTasks(get("id"));
    }


}
