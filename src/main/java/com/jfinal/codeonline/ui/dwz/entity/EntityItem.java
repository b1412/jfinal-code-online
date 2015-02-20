package com.jfinal.codeonline.ui.dwz.entity;

import com.jfinal.ext.kit.ModelExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class EntityItem extends ModelExt<EntityItem> {

    public static final EntityItem DAO = new EntityItem();

    @Override
    public boolean deleteById(Object id) {
        Db.update("delete from field_item where entityId =?", id);
        return super.deleteById(id);
    }

    public Page<EntityItem> page(int projectId, int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from entity where projectId = ? order by id desc", projectId);
    }

}
