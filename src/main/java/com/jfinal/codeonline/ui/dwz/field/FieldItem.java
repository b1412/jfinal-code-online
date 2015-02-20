package com.jfinal.codeonline.ui.dwz.field;

import com.jfinal.codeonline.ui.dwz.entity.EntityItem;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class FieldItem extends ModelExt<FieldItem> {

    public static final FieldItem DAO = new FieldItem();

    public Page<FieldItem> page(int entityId, int pageNumber, int pageSize) {

        return paginate(pageNumber, pageSize, "select *", "from field where entityId = ? order by id desc", entityId);
    }

    public EntityItem getEntity() {
        return EntityItem.DAO.findById(getInt("entityId"));
    }

}
