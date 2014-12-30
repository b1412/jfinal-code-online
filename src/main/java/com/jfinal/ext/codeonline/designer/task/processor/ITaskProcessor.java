package com.jfinal.ext.codeonline.designer.task.processor;

import com.jfinal.ext.codeonline.designer.project.Project;
import com.jfinal.ext.codeonline.designer.task.Task;

import java.util.List;
import java.util.Map;

public interface ITaskProcessor {
    List<String> run(Project project, Task task, Map<String, Object> root);
}
