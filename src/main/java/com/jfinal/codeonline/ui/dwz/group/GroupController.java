package com.jfinal.codeonline.ui.dwz.group;

import com.jfinal.codeonline.core.enums.MetadataType;
import com.jfinal.codeonline.ui.dwz.common.BaseController;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class GroupController extends BaseController {

    public void index() {
        Page<Group> page = Group.DAO.page(getParaToInt(0, 1), 20);
        setAttr("page", page);
    }

    public void save() {
        Group model = getModel(Group.class);
        Integer groupId = model.getInt("id");
        if (groupId == null) {
            model.save();
        } else {
            model.update();
        }
        groupId = model.getInt("id");

        model.deleteTasks();
        model.deleteMetadata();
        String[] taskIds = getPara("tasks.ids").split(",");

        for (String taskId : taskIds) {
            Record record = new Record().set("groupId", groupId).set("taskId", Integer.parseInt(taskId));
            Db.save("group_task_relation", record);
        }

        List<GroupMetadata> projectMetadata = getModels(GroupMetadata.class, "projectMetadata");
        for (GroupMetadata groupMetadata : projectMetadata) {
            groupMetadata.set("groupId", groupId);
            groupMetadata.set("scope", "project");
            groupMetadata.save();
        }

        List<GroupMetadata> entityMetadata = getModels(GroupMetadata.class, "entityMetadata");
        for (GroupMetadata groupMetadata : entityMetadata) {
            groupMetadata.set("groupId", groupId);
            groupMetadata.set("scope", "entity");
            groupMetadata.save();
        }

        List<GroupMetadata> fieldMetadata = getModels(GroupMetadata.class, "fieldMetadata");
        for (GroupMetadata groupMetadata : fieldMetadata) {
            groupMetadata.set("groupId", groupId);
            groupMetadata.set("scope", "field");
            groupMetadata.save();
        }
        render(DwzRender.closeCurrentAndRefresh("group"));
    }

    public void edit() {
        Group group = Group.DAO.findById(getPara(0));
        if (group == null) {
            group = new Group();
        }
        setAttr("taskIds",group.taskIds());
        setAttr("taskNames",group.taskNames());
        setAttr("group", group);
    }

    public void delete() {
        Group.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }

    public void metadataType() {
        setAttr("metadataTypes", MetadataType.RECORDS);
    }
}
