package com.jfinal.codeonline.metadata.db;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.core.GenException;
import com.jfinal.codeonline.core.IConfigDataProvider;
import com.jfinal.codeonline.metadata.db.model.DataType;
import com.jfinal.codeonline.metadata.db.model.DbInfo;
import com.jfinal.codeonline.metadata.db.model.ViewEngine;
import com.jfinal.codeonline.metadata.db.model.ViewFramework;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.druid.DruidPlugin;

import java.util.List;

public class DbConfigDataProvider implements IConfigDataProvider {

    public DbConfigDataProvider() {
        Prop prop = PropKit.use("conf.txt");
        DruidPlugin dp = new DruidPlugin(prop.get("jdbcUrl"), prop.get("username"), prop.get(
                "password").trim(), prop.get("driver"));
        AutoTableBindPlugin atbp = new AutoTableBindPlugin("metadata", dp, SimpleNameStyles.LOWER_UNDERLINE);
        atbp.autoScan(false);
        atbp.addMapping("data_type", DataType.class);
        atbp.addMapping("db_info",DbInfo.class);
        atbp.addMapping("view_engine",ViewEngine.class);
        atbp.addMapping("view_framework",ViewFramework.class);
//        atbp.addScanPackages("com.jfinal.codeonline.metadata.db");
        atbp.setContainerFactory(new CaseInsensitiveContainerFactory(true)).setShowSql(true);
        dp.start();
        atbp.start();
    }

    @Override
    public List<String> viewFrameworks() {
        List<String> result = Lists.newArrayList();
        List<ViewFramework> viewFrameworks = ViewFramework.DAO.findAll();
        for (ViewFramework viewFramework : viewFrameworks) {
            result.add(viewFramework.getStr("name"));
        }
        return result;
    }

    @Override
    public List<String> viewEngines() {
        List<String> result = Lists.newArrayList();
        List<ViewEngine> viewEngines = ViewEngine.DAO.findAll();
        for (ViewEngine viewEngine : viewEngines) {
            result.add(viewEngine.getStr("name"));
        }
        return result;
    }

    @Override
    public String driver(String dbType) {
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

    @Override
    public List<String> fieldTypes(String dbType) {
        List<String> result = Lists.newArrayList();
        List<DataType> dataTypes = DataType.DAO.findByColumn("dbType", dbType);
        for (DataType dataType : dataTypes) {
            result.add(dataType.getStr("fieldType"));
        }
        return result;
    }

    @Override
    public String dbColumnType(String dbType, String fieldType) {
        String result = Db.queryStr("select columnType from data_type where dbType = ? and fieldType =?", dbType, fieldType);
        if (StrKit.isBlank(result)) {
            throw new GenException("fieldType(" + fieldType + ") can match any columnType in database(" + dbType + ")");
        }
        return result;
    }

}
