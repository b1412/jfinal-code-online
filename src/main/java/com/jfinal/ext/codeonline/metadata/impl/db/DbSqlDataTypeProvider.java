package com.jfinal.ext.codeonline.metadata.impl.db;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.metadata.DataInfoProvider;
import com.jfinal.ext.codeonline.metadata.impl.db.model.DataType;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by kid on 14-12-21.
 */
public class DbSqlDataTypeProvider implements DataInfoProvider {

    @Override
    public List<String> fieldTypes(String dbType) {
        List<String> result = Lists.newArrayList();
        List<DataType> dataTypes = DataType.DAO.findByColumn("db_type", dbType);
        for (DataType dataType : dataTypes) {
            result.add(dataType.getStr("field_type"));
        }
        return result;
    }

    @Override
    public String dbColumnType(String dbType, String fieldType) {
        String result = Db.queryStr("select column_type from data_type where db_type = ? and field_type =?", dbType, fieldType);
        if (StrKit.isBlank(result)) {
            throw new RuntimeException("field_type(" + fieldType + ") can match any column_type in database(" + dbType + ")");
        }
        return result;
    }
}
