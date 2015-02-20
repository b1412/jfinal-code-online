package com.jfinal.codeonline.ui.dwz.task.processor;

import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;
import java.util.Map;

public interface ITaskProcessor {
    List<String> run(Record project, Task task, Map<String, Object> context);
}
