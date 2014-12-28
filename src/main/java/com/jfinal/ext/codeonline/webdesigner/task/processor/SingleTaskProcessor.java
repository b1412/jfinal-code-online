package com.jfinal.ext.codeonline.webdesigner.task.processor;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.common.TaskKit;
import com.jfinal.ext.codeonline.webdesigner.project.Project;
import com.jfinal.ext.codeonline.webdesigner.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by kid on 14-12-21.
 */
public class SingleTaskProcessor implements ITaskProcessor {
    @Override
    public List<String> run(Project project, Task task, Map<String, Object> root) {
        return Lists.newArrayList(TaskKit.processTemplate(root, task));

    }
}
