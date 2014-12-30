package com.jfinal.ext.codeonline.metadata;

import java.util.List;


public interface IDataInfoProvider {
    List<String> fieldTypes(String dbType);

    String dbColumnType(String dbType, String fieldType);
}
