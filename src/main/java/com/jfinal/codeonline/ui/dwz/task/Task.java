package com.jfinal.codeonline.ui.dwz.task;

import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.processor.ITaskProcessor;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.ext.kit.Reflect;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;
import java.util.Map;

public class Task extends ModelExt<Task> {
    private static final Logger LOG = Logger.getLogger(ModelExt.class);

    public static final Task DAO = new Task();

    public Page<Task> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from task order by id desc");
    }

    public List<TaskParam> getTaskParams() {
        return TaskParam.DAO.find("select * from task_param where taskId = ?", getInt("id"));
    }

    public void deleteTaskParams() {
        Db.update("delete from task_param where taskId = ?", getInt("id"));
    }

    @Override
    public List<Task> findAll() {
        return DAO.find("select * from task where valid = 1");
    }

    @Override
    public boolean deleteById(Object id) {
        Db.update("delete from task_param where taskId =?",id);
        return super.deleteById(id);
    }

    public List<String> run( Project project, Map<String, Object> root) {
        LOG.debug("task " + this.getStr("taskname") + " run");
        List<String> paths = taskProcessor(this.getStr("taskType")).run(project, this, root);
        LOG.debug("task " + this.getStr("taskname") + " end");
        return paths;
    }

    private ITaskProcessor taskProcessor(String taskType) {
        return Reflect.on(ITaskProcessor.class.getPackage().getName()+"."
                + StrKit.firstCharToUpperCase(taskType) +
                "TaskProcessor").create().get();
    }
}
