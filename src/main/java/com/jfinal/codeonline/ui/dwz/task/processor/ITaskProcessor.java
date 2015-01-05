package com.jfinal.codeonline.ui.dwz.task.processor;

import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.Task;

import java.util.List;
import java.util.Map;

public interface ITaskProcessor {
    List<String> run(Project project, Task task, Map<String, Object> context);
}
