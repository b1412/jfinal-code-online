package com.jfinal.codeonline.ui.dwz.group;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class GroupsController extends Controller {

    public void index() {
        Page<Groups> page = Groups.DAO.page(getParaToInt(0, 1), 20);
        setAttr("page", page);
    }

    public void save() {
        Groups model = getModel(Groups.class);
        Integer groupId = model.getInt("id");
        if (groupId == null) {
            model.save();
        } else {
            model.update();
        }
        groupId = model.getInt("id");
        model.deleteTasks();
        String[] taskIds = getPara("tasks.ids").split(",");
        for (String taskId : taskIds) {
            Record record = new Record().set("groupsId", groupId).set("taskId", Integer.parseInt(taskId));
            Db.save("groups_task_relation", record);
        }
        render(DwzRender.closeCurrentAndRefresh("group"));
    }

    public void edit() {
        Groups groups = Groups.DAO.findById(getPara(0));
        if (groups == null) {
            groups = new Groups();
        }
        setAttr("taskIds",groups.taskIds());
        setAttr("taskNames",groups.taskNames());
        setAttr("groups", groups);
    }

    public void delete() {
        Groups.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }


}
