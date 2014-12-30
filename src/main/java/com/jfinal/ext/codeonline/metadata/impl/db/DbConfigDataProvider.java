package com.jfinal.ext.codeonline.metadata.impl.db;

import com.jfinal.ext.codeonline.metadata.*;

import java.util.List;

public class DbConfigDataProvider implements IConfigDataProvider {

    private IViewFrameworkProvider viewFrameworkProvider = new DbViewFrameworkProvider();

    private IViewEngineProvider viewEngineProvider = new DbViewEngineProvider();

    private IDbInfoProvider dbInfoProvider = new DbDbInfoProvider();

    private IDataInfoProvider dataTypeProvider = new DbSqlDataTypeProvider();

    @Override
    public List<String> viewFrameworks() {
        return viewFrameworkProvider.viewFrameWorks();
    }

    @Override
    public List<String> viewEngines() {
        return viewEngineProvider.viewEngines();
    }

    @Override
    public String findDriver(String dbType) {
        return dbInfoProvider.findDriver(dbType);
    }

    @Override
    public List<String> dbTypes() {
        return dbInfoProvider.dbTypes();
    }

    @Override
    public List<String> fieldTypes(String dbType) {
        return dataTypeProvider.fieldTypes(dbType);
    }

    @Override
    public String dbColumnType(String dbType, String fieldType) {
        return dataTypeProvider.dbColumnType(dbType, fieldType);
    }

}
