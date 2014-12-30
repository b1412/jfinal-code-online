package com.jfinal.ext.codeonline.metadata;

import java.util.List;

public interface IDbInfoProvider {
    String findDriver(String dbType);
    List<String> dbTypes();
}
