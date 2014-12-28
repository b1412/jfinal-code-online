package com.jfinal.ext.codeonline.metadata;

import java.util.List;


public interface ConfigDataProvider {

    public List<String> viewFrameworks();

    public List<String> viewEngines();


    public String findDriver(String dbType);

    public List<String> dbTypes();

    public List<String> fieldTypes(String dbType);

    public String dbColumnType(String dbType, String fieldType);

}
