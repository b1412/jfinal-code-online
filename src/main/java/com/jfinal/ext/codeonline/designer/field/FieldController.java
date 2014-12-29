package com.jfinal.ext.codeonline.designer.field;

import com.jfinal.core.Controller;
import com.jfinal.ext.codeonline.common.enums.YesOrNo;
import com.jfinal.ext.codeonline.core.Config;
import com.jfinal.ext.codeonline.designer.entity.Entity;
import com.jfinal.ext.render.DwzRender;

import java.util.List;

import static com.jfinal.ext.render.DwzRender.closeCurrentAndRefresh;

public class FieldController extends Controller {
    public void index() {
        Integer entityId = getParaToInt("entity.id");
        keepModel(Entity.class);
        setAttr("page", Field.DAO.page(entityId, getParaToInt(0, 1), 20));
    }

    public void save() {
        Field field = getModel(Field.class);
        if (field.getInt("id") == null) {
            field.save();
        } else {
            field.update();
        }
        render(closeCurrentAndRefresh("field"));
    }

    public void edit() {
        Entity entity = getModel(Entity.class);
        Field field = Field.DAO.findById(getPara(0));
        if (field == null) {
            field = new Field();
            field.set("entity_id",entity.getInt("id"));
        }
        List<String> fieldTypes = Config.getConfigDataProvider().fieldTypes("mysql");
        setAttr("yesOrNo", YesOrNo.RECORDS);
        setAttr("fieldTypes", fieldTypes);
        setAttr("field", field);
    }

    public void delete() {
        Field.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }
}
