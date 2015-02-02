package com.jfinal.codeonline.ext;

import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.core.IProjectInitializer;
import com.jfinal.codeonline.ui.dwz.project.Project;

import java.io.File;

import static com.jfinal.codeonline.core.Constants.FS;

public class GradleInitializer implements IProjectInitializer {
    @Override
    public void init(Project project) {
        File baseDir = new File(Config.targetPath() + FS + project.get("name"));
        baseDir.mkdirs();
        new File(baseDir, FS + "src").mkdirs();
        new File(baseDir, FS + "src" + FS + "main").mkdirs();
        new File(baseDir, FS + "src" + FS + "test").mkdirs();
        new File(baseDir, FS + "src" + FS + "test" + FS + "java").mkdirs();
        new File(baseDir, FS + "src" + FS + "test" + FS + "resources").mkdirs();
        new File(baseDir, FS + "src" + FS + "main" + FS + "java").mkdirs();
        new File(baseDir, FS + "src" + FS + "main" + FS + "resources").mkdirs();
        new File(baseDir, FS + "src" + FS + "main" + FS + "webapp").mkdirs();
        new File(baseDir, FS + "src" + FS + "main" + FS + "webapp" + FS + "WEB-INF").mkdirs();
    }
}
