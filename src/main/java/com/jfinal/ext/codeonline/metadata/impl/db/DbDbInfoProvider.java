package com.jfinal.ext.codeonline.metadata.impl.db;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.metadata.DbInfoProvider;
import com.jfinal.ext.codeonline.metadata.impl.db.model.DbInfo;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by kid on 14-12-21.
 */
public class DbDbInfoProvider implements DbInfoProvider {

    @Override
    public String findDriver(String dbType) {
        return Db.queryStr("select driverClass from db_info where type =?", dbType);
    }

    @Override
    public List<String> dbTypes() {
        List<String> result = Lists.newArrayList();
        List<DbInfo> dbInfos = DbInfo.DAO.findAll();
        for (DbInfo dbInfo : dbInfos) {
            result.add(dbInfo.getStr("type"));
        }
        return result;
    }
}
