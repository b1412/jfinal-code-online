package com.jfinal.ext.codeonline.designer.task.processor;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.common.TaskKit;
import com.jfinal.ext.codeonline.designer.entity.Entity;
import com.jfinal.ext.codeonline.designer.project.Project;
import com.jfinal.ext.codeonline.designer.task.Task;

import java.util.List;
import java.util.Map;

public class MultipleTaskProcessor implements ITaskProcessor {
    @Override
    public List<String> run( Project project, Task task, Map<String, Object> root) {
        List<String> paths = Lists.newArrayList();
        for (Entity entity : project.getEntities()) {
            root.put("entity", entity);
            TaskKit.templateHelp.put("entity", entity);
            paths.add(TaskKit.processTemplate(root, task));
        }
        return paths;

    }
}
