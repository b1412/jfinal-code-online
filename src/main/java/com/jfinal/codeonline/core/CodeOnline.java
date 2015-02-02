package com.jfinal.codeonline.core;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.ext.kit.Reflect;
import com.jfinal.log.Logger;

import java.util.List;

public class CodeOnline {

    private static final Logger LOG = Logger.getLogger(CodeOnline.class);

    private Project project;


    private CodeOnline(Project project) {
        this.project = project;
    }

    public static CodeOnline on(Project project) {
        return new CodeOnline(project);
    }

    public List<String> run(Group group) {
        Config.projectInitializer().init(project);
        Object initializer = group.get("initializer");
        if (initializer != null) {
            IGroupInitializer groupInitializer = Reflect.on(initializer.toString()).create().get();
            groupInitializer.init(project, group);
        }
        List<String> paths = Lists.newArrayList();
        for (Task task : group.tasks()) {
            paths.addAll(task.processTask(project));
        }
        return paths;
    }


}
