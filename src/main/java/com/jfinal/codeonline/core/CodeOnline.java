package com.jfinal.codeonline.core;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.ext.kit.Reflect;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class CodeOnline {

    private static final Logger LOG = Logger.getLogger(CodeOnline.class);

    private Record project;

    private List<Record> entities = Lists.newArrayList();

    private CodeOnline(Record project) {
        this.project = project;
        entities = Config.modelProvider().getEntities(project);
    }

    public static CodeOnline on(Record project) {
        return new CodeOnline(project);
    }


    public List<String> run(Group group) {
        return run(group, group.tasks());
    }

    public List<String> run(Group group, List<Task> tasks) {
        Config.projectInitializer().init(project);
        Object initializer = group.get("initializer");
        if (initializer != null) {
            IGroupInitializer groupInitializer = Reflect.on(initializer.toString()).create().get();
            groupInitializer.init(project, group);
        }
        List<String> paths = Lists.newArrayList();
        for (Task task : tasks) {
            paths.addAll(task.processTask(project));
        }
        return paths;
    }

}
