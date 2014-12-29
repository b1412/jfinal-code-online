package com.jfinal.ext.codeonline.designer.task;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.common.BaseController;
import com.jfinal.ext.codeonline.designer.project.Project;
import com.jfinal.ext.kit.ModelKit;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class TaskController extends BaseController {

    public void index() {
        Page<Task> page = Task.DAO.page(getParaToInt(0, 1), 20);
        List<String> taskIds = Lists.newArrayList(getPara("taskIds","").split(","));
        keepPara("lookup");
        setAttr("taskIds",taskIds);
        setAttr("page", page);
    }

    public void save() {
        Task task = getModel(Task.class);
        List<TaskParam> taskParams = getModels(TaskParam.class,"taskParams");
        if (task.getInt("id") == null) {
            task.save();
        } else {
            task.update();
        }
        for (TaskParam taskParam : taskParams) {
            taskParam.set("task_id",task.get("id"));
        }
        task.deleteTaskParams();
        if(!taskParams.isEmpty()) {
            ModelKit.batchSave(taskParams);
        }
        render(DwzRender.closeCurrentAndRefresh("task"));
    }

    public void edit() {
        Task task = Task.DAO.findById(getPara(0));
        if (task == null) {
            task = new Task();
        }
        setAttr("task", task);
    }


    public void delete() {
        Task.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }


    public void eval() {
        Project project = Project.DAO.findFirst("select * from project limit 1");
        List<Record> records = Lists.newArrayList();
        Task task = new Task();
        if (project != null) {
            task = Task.DAO.findById(getPara(0));
            records = project.eval(task);
        }
        setAttr("project", project);
        setAttr("records", records);
        setAttr("task", task);
    }


    public static void main(String[] args) {

    }
}
