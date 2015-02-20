package com.jfinal.codeonline.ui.dwz.field;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.core.enums.YesOrNo;
import com.jfinal.codeonline.ui.dwz.common.BaseController;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.group.GroupMetadata;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.jfinal.ext.render.DwzRender.closeCurrentAndRefresh;

public class FieldController extends BaseController {
    public void index() {
        String entityId = getPara();
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<GroupMetadata> metadataList = group.getFieldMetadata();
        Page<Record> page = Db.paginate(getParaToInt("pagNum", 1), getParaToInt("numPerPage", 20), "select itemId",
                "from field_item where `groupId` = ?  and entityId =? group by itemId order by itemId asc", group.get("id"), entityId);
        List<Record> list = page.getList();
        for (Record record : list) {
            List<Record> data = Db.find("select `column`,`value` from field_item where itemId = ?", record.getInt("itemId"));
            for (Record item : data) {
                record.set(item.getStr("column"), item.getStr("value"));
            }
        }
        setAttr("entityId", entityId);
        setAttr("page", page);
        setAttr("metadataList", metadataList);
        setAttr("YesOrNo", new YesOrNo());
        render("index.html");
    }

    public void save() {
        Integer entityId = getParaToInt("entityId");
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        Record model = (Record) getRecord("record");
        Set<Map.Entry<String, Object>> entries = model.getColumns().entrySet();
        int itemId = getParaToInt("itemId", 0);
        if (itemId > 0) {
            FieldItem.DAO.deleteByColumn("itemId", itemId);
        }
        List<FieldItem> items = Lists.newArrayList();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            FieldItem item = new FieldItem();
            item.set("groupId", group.get("id"));
            item.set("column", key);
            item.set("entityId", entityId);
            if (value != null) {
                item.set("value", value);
            }
            items.add(item);
        }
        if (!items.isEmpty()) {
            for (FieldItem item : items) {
                item.save();
            }
            if (itemId == 0) {
                itemId = items.get(0).get("id");
            }
            for (FieldItem item : items) {
                item.set("itemId", itemId);
                item.update();
            }
        }
        render(closeCurrentAndRefresh("field"));
    }

    public void edit() {
        keepPara("entityId");
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<GroupMetadata> metadataList = group.getFieldMetadata();

        Record record = new Record();
        List<FieldItem> fieldItems = FieldItem.DAO.findByColumn("itemId", getPara());
        for (FieldItem item : fieldItems) {
            record.set(item.getStr("column"), item.getStr("value"));
            setAttr("itemId", item.get("itemId"));
        }
        setAttr("record", record);
        setAttr("metadataList", metadataList);
        Map<String, Object> map = Maps.newHashMap();
        map.put("type", Config.configDataProvider().fieldTypes());
        setAttr("yesOrNo", YesOrNo.RECORDS);
        setAttr("map", map);
    }

    public void delete() {
        FieldItem.DAO.deleteByColumn("itemId", getPara(0));
        render(DwzRender.success());
    }
}
