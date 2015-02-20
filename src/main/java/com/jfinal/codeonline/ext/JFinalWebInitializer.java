package com.jfinal.codeonline.ext;

import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.core.GenException;
import com.jfinal.codeonline.core.IGroupInitializer;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static com.jfinal.codeonline.core.Constants.FS;

public class JFinalWebInitializer implements IGroupInitializer {
    @Override
    public void init(Record project, Group groups) {
        File baseDir = new File(Config.targetPath() + FS + project.get("name"));
        File view = new File(Config.templatePath() + FS + Config.modelProvider().getGroup(project).get("name")
                + FS + "common" + FS + "view" + FS + project.get("viewFramework") + FS + project.get("viewEngine"));
        try {
            FileUtils.copyDirectory(view, new File(baseDir + FS + "src" + FS + "main" + FS + "webapp"));
        } catch (IOException e) {
            throw new GenException(e.getMessage(), e);
        }
    }
}
