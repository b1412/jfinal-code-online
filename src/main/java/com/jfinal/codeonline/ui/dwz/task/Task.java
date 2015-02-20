package com.jfinal.codeonline.ui.dwz.task;

import com.google.common.collect.Maps;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.task.processor.ITaskProcessor;
import com.jfinal.ext.kit.ModelExt;
import com.jfinal.ext.kit.Reflect;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.jfinal.codeonline.core.Constants.FS;

public class Task extends ModelExt<Task> {
    private static final Logger LOG = Logger.getLogger(ModelExt.class);

    public static final Task DAO = new Task();

    public Page<Task> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from task order by id desc");
    }

    public List<TaskParam> getTaskParams() {
        return TaskParam.DAO.find("select * from task_param where taskId = ?", getInt("id"));
    }

    public void deleteTaskParams() {
        Db.update("delete from task_param where taskId = ?", getInt("id"));
    }

    @Override
    public List<Task> findAll() {
        return DAO.find("select * from task where valid = 1");
    }

    @Override
    public boolean deleteById(Object id) {
        Db.update("delete from task_param where taskId =?",id);
        return super.deleteById(id);
    }


    public List<String> processTask(Record project) {
        List<Record> entities = Config.modelProvider().getEntities(project);
        System.out.println(entities);
        Map<String, Object> scope = Maps.newHashMap();
        scope.put("project", project);
        scope.put("entities", entities);
        scope.put("configDataProvider", Config.configDataProvider());
        List<String> utilClassNames = Config.configDataProvider().utilityClasses();
        for (String utilClassName : utilClassNames) {
            Class clazz = Reflect.on(utilClassName).get();
            scope.put(clazz.getSimpleName(), clazz);
        }
        for (TaskParam taskParam : getTaskParams()) {
            scope.put(taskParam.getStr("key"), Config.scriptHelper().exec(taskParam.getStr("expression"), scope));
        }
        Config.templateEngine().putAll(scope);
        return run(project, scope);
    }

    public String processTemplate(Map<String, Object> root) {
        Record project = (Record) root.get("project");
        List<TaskParam> params = getTaskParams();
        for (TaskParam param : params) {
            if (StrKit.isBlank(param.getStr("name"))) continue;
            String value = param.getStr("expression");
            Config.templateEngine().put(param.getStr("name"), Config.scriptHelper().exec(value, root));
        }
        String templateFilename = Config.modelProvider().getGroup(project).getStr("name") + FS
                + Config.scriptHelper().exec(getStr("templatePath"), root).toString();
        String folder = Config.scriptHelper().exec(getStr("folder"), root).toString();
        folder = Config.targetPath() + FS + project.getStr("name") + File.separator + folder;
        File folderDir = new File(folder);
        if (!folderDir.exists()) {
            folderDir.mkdirs();
        }
        String filename = Config.scriptHelper().exec(getStr("filename"), root).toString();
        String outputFilename = folder + FS + filename;
        LOG.debug(outputFilename);
        Config.templateEngine().exec(templateFilename, outputFilename);
        return outputFilename;

    }
    public List<String> run( Record project, Map<String, Object> root) {
        LOG.debug("task " + getStr("taskname") + " run");
        List<String> paths = taskProcessor(this.getStr("type")).run(project, this, root);
        LOG.debug("task " + getStr("taskname") + " end");
        return paths;
    }

    private ITaskProcessor taskProcessor(String taskType) {
        return Reflect.on(ITaskProcessor.class.getPackage().getName()+"."
                + StrKit.firstCharToUpperCase(taskType) +
                "TaskProcessor").create().get();
    }
}
