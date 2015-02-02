package com.jfinal.codeonline.ui.dwz.task.processor;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.Task;

import java.util.List;
import java.util.Map;

public class ProjectTaskProcessor implements ITaskProcessor {
    @Override
    public List<String> run(Project project, Task task, Map<String, Object> context) {
        return Lists.newArrayList(task.processTemplate(context));
    }
}
