package com.jfinal.codeonline.ext;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jfinal.ext.kit.Reflect;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;

import java.util.List;

public class SqlKit {

    public static void batchDelete(String tableName, List<Integer> ids) {
        int idsize = ids.size();
        int per = 1000;
        int length = idsize / per;
        if (idsize % per != 0) {
            length++;
        }
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                String sql = "DELETE FROM " + tableName + " WHERE id IN ("
                        + Joiner.on(",").join(ids.subList(i * per, ids.size())) + ")";
                Db.update(sql);
            } else {
                String sql = "DELETE FROM " + tableName + " WHERE id IN ("
                        + Joiner.on(",").join(ids.subList(i * per, i * per + per)) + ")";
                Db.update(sql);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static List<? extends Model> batchQuery(Class<? extends Model> model, List<?> ids) {
        Table table = TableMapping.me().getTable(model);
        String idName = table.getPrimaryKey();
        return batchQuery(model, ids, idName);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<? extends Model> batchQuery(Class<? extends Model> model, List<?> ids, String column) {
        List<? extends Model> result = Lists.newArrayList();
        if (ids.isEmpty()) {
            return result;
        }
        Table table = TableMapping.me().getTable(model);
        String tableName = table.getName();
        int idSize = ids.size();
        int batchSize = 1024;
        int length = idSize / batchSize;
        if (idSize % batchSize != 0) {
            length++;
        }
        for (int i = 0; i < length; i++) {
            Model dao = Reflect.on(model).create().get();
            if (i == length - 1) {
                List item = dao.find("select * from " + tableName + " where " + column + " in (" + Joiner.on(" ,").join(ids.subList(i * batchSize, ids.size())) + ")");
                result.addAll(item);
            } else {
                List item = dao.find("select * from " + tableName + " where " + column + " in (" + Joiner.on(" ,").join(ids.subList(i * batchSize, i * batchSize + batchSize)) + ")");
                result.addAll(item);
            }
        }

        return result;
    }
}