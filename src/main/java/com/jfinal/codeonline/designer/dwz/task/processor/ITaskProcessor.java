package com.jfinal.codeonline.designer.dwz.task.processor;

import com.jfinal.codeonline.designer.dwz.project.Project;
import com.jfinal.codeonline.designer.dwz.task.Task;

import java.util.List;
import java.util.Map;

public interface ITaskProcessor {
    List<String> run(Project project, Task task, Map<String, Object> context);
}
