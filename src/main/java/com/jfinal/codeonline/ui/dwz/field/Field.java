package com.jfinal.codeonline.ui.dwz.field;

import com.jfinal.codeonline.ui.dwz.entity.Entity;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Field extends Model<Field> {

    public static final Field DAO = new Field();

    public Page<Field> page(int entityId, int pageNumber, int pageSize) {

        return paginate(pageNumber, pageSize, "select *", "from field where entityId = ? order by id desc", entityId);
    }

    public Entity getEntity() {
        return Entity.DAO.findById(getInt("entityId"));
    }

}
