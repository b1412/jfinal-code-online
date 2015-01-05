package com.jfinal.codeonline.ui.dwz.task.processor;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.ext.TaskKit;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.entity.Entity;
import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.Task;

import java.util.List;
import java.util.Map;

public class MultipleTaskProcessor implements ITaskProcessor {
    @Override
    public List<String> run( Project project, Task task, Map<String, Object> context) {
        List<String> paths = Lists.newArrayList();
        for (Entity entity : project.getEntities()) {
            context.put("entity", entity);
            Config.templateEngine().put("entity", entity);
            paths.add(TaskKit.processTemplate(context, task));
        }
        return paths;

    }
}
