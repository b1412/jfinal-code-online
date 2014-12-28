package com.jfinal.ext.codeonline.metadata.impl.db;

import com.jfinal.ext.codeonline.metadata.*;
import lombok.Data;

import java.util.List;

/**
 * Created by kid on 14-12-21.
 */
@Data
public class DbConfigDataProvider implements ConfigDataProvider {

    private ViewFrameworkProvider viewFrameworkProvider = new DbViewFrameworkProvider();

    private ViewEngineProvider viewEngineProvider = new DbViewEngineProvider();

    private DbInfoProvider dbInfoProvider = new DbDbInfoProvider();

    private DataInfoProvider dataTypeProvider = new DbSqlDataTypeProvider();

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
