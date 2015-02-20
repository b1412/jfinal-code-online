package com.jfinal.codeonline.ui.dwz.entity;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.ui.dwz.common.BaseController;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.group.GroupMetadata;
import com.jfinal.codeonline.ui.dwz.project.ProjectItem;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityController extends BaseController {

    public void index() {
        String projectId = getPara();
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<GroupMetadata> metadataList = group.getEntityMetadata();
        Page<Record> page = Db.paginate(getParaToInt("pagNum", 1), getParaToInt("numPerPage", 20), "select itemId",
                "from entity_item where `groupId` = ?  and projectId =? group by itemId order by itemId asc", group.get("id"), projectId);
        List<Record> list = page.getList();
        for (Record record : list) {
            List<Record> data = Db.find("select `column`,`value` from entity_item where itemId = ?", record.getInt("itemId"));
            for (Record item : data) {
                record.set(item.getStr("column"), item.getStr("value"));
            }
        }
        setAttr("projectId", projectId);
        setAttr("page", page);
        setAttr("metadataList", metadataList);
        render("index.html");
    }

    public void save() {
        Integer projectId = getParaToInt("projectId");
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        Record model = (Record) getRecord("record");
        Set<Map.Entry<String, Object>> entries = model.getColumns().entrySet();
        int itemId = getParaToInt("itemId", 0);
        if (itemId > 0) {
            System.out.println("itemId " + itemId);
            EntityItem.DAO.deleteByColumn("itemId", itemId);
        }
        List<EntityItem> items = Lists.newArrayList();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            EntityItem item = new EntityItem();
            item.set("groupId", group.get("id"));
            item.set("column", key);
            item.set("projectId", projectId);
            if (value != null) {
                item.set("value", value);
            }
            items.add(item);
        }
        if (!items.isEmpty()) {
            for (EntityItem item : items) {
                item.save();
            }
            if (itemId == 0) {
                itemId = items.get(0).get("id");
            }
            for (EntityItem item : items) {
                item.set("itemId", itemId);
                item.update();
            }
        }
        render(DwzRender.closeCurrentAndRefresh("entity"));
    }

    public void edit() {
        keepPara("projectId");
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<GroupMetadata> metadataList = group.getEntityMetadata();

        Record record = new Record();
        List<EntityItem> entityItems = EntityItem.DAO.findByColumn("itemId", getPara());
        for (EntityItem item : entityItems) {
            record.set(item.getStr("column"), item.getStr("value"));
            setAttr("itemId", item.get("itemId"));
        }
        setAttr("record", record);
        setAttr("metadataList", metadataList);
    }

    public void delete() {
        //TODO 级联删除
        EntityItem.DAO.deleteByColumn("itemId", getPara(0));
        render(DwzRender.success());
    }
//
//    public void create() {
//        EntityItem entity = EntityItem.DAO.findById(getParaToInt(0));
//        Group group = entity.getProject().getGroup();
//        CodeOnline.on(entity).run(group);
//        setAttr("root", entity.getProject().rootFile());
//        render("/project/tree.html");
//    }

    public void tree() {
        ProjectItem project = ProjectItem.DAO.findById(getParaToInt(0));
        File root = project.rootFile();
        if (root.exists()) {
            setAttr("root", root);
        } else {
//            create();
        }
    }
}
