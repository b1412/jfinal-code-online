package com.jfinal.codeonline.ui.dwz.task.processor;

import com.google.common.collect.Lists;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;
import java.util.Map;

public class EntityTaskProcessor implements ITaskProcessor {
    @Override
    public List<String> run(Record project, Task task, Map<String, Object> context) {
        List<String> paths = Lists.newArrayList();
        for (Record entity : Config.modelProvider().getEntities(project)) {
            context.put("entity", entity);
            Config.templateEngine().put("entity", entity);
            paths.add(task.processTemplate(context));
        }
        return paths;

    }
}
