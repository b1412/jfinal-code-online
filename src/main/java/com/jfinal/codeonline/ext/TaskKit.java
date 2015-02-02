package com.jfinal.codeonline.ext;

import com.jfinal.kit.StrKit;

public class TaskKit {

    public static String capitalize(String str) {
        return StrKit.firstCharToUpperCase(str);
    }

    public static String buildFileName(String str) {
        if ("gradle".equals(str)) {
            return "build.gradle";
        } else if ("maven".equals(str)) {
            return "pom.xml";
        } else {
            throw new IllegalArgumentException("Not support " + str);
        }
    }

}
