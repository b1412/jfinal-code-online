package com.jfinal.codeonline.core;

import java.util.List;


public interface IConfigDataProvider {

    List<String> viewFrameworks();

    List<String> viewEngines();

    String driver(String dbType);

    List<String> dbTypes();

    List<String> fieldTypes();

    String javaType(String dbType, String fieldType);

    String dbColumnType(String dbType, String fieldType);

    List<String> utilityClasses();

    List<String> buildTools();


}
