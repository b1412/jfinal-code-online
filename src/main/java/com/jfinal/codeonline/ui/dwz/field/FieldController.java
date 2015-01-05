package com.jfinal.codeonline.ui.dwz.field;

import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.core.Controller;
import com.jfinal.codeonline.core.enums.YesOrNo;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.entity.Entity;
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
        Entity entity = Entity.DAO.findById(getPara("entity.id"));
        Project project = entity.getProject();
        Field field = Field.DAO.findById(getPara(0));
        if (field == null) {
            field = new Field();
            field.set("entityId",entity.getInt("id"));
        }
        List<String> types = Config.configDataProvider().fieldTypes(project.getStr("dbType"));
        setAttr("yesOrNo", YesOrNo.RECORDS);
        setAttr("types", types);
        setAttr("field", field);
    }

    public void delete() {
        Field.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }
}
