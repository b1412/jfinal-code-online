package com.jfinal.ext.codeonline.metadata;

import java.util.List;

public interface DbInfoProvider {
    String findDriver(String dbType);
    List<String> dbTypes();
}
