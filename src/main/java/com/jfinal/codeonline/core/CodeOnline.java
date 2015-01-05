package com.jfinal.codeonline.core;

import com.jfinal.codeonline.ui.dwz.group.Groups;
import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.log.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.jfinal.codeonline.core.Constants.FS;

public class CodeOnline {

    private static final Logger LOG = Logger.getLogger(CodeOnline.class);

    private Project project;


    public CodeOnline(Project project) {
        this.project = project;
    }

    public List<String> run(Groups group) {
        File baseDir = new File(Config.targetPath() + FS + project.get("name"));
        try {
//            clean(baseDir);
            makeDirs(baseDir);
        } catch (IOException e) {
            throw new GenException(e);
        }
        copyViewFramework(baseDir);
        return project.runGroup(group);
    }

    private void makeDirs(File baseDir) throws IOException {
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

    private void clean(File baseDir) throws IOException {
        FileUtils.deleteDirectory(baseDir);
    }

    private void copyViewFramework(File baseDir) {
        File view = new File(Config.templatePath()+ FS + project.getGroups().getStr("name") + FS + "common" + FS + "view" + FS + project.get("viewFramework") + FS
                + project.get("viewEngine"));
        try {
            FileUtils.copyDirectory(view, new File(baseDir + FS + "src" + FS + "main" + FS + "webapp"));
        } catch (IOException e) {
            throw new GenException(e.getMessage(), e);
        }

    }

}
