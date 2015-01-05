package com.jfinal.codeonline.core;

import java.util.List;


public interface IConfigDataProvider {

     List<String> viewFrameworks();

     List<String> viewEngines();

     String driver(String dbType);

     List<String> dbTypes();

     List<String> fieldTypes(String dbType);

     String dbColumnType(String dbType, String fieldType);

}
