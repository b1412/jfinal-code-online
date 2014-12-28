package com.jfinal.ext.codeonline.common;

import com.google.common.collect.Maps;
import com.jfinal.ext.codeonline.core.Config;
import com.jfinal.ext.codeonline.webdesigner.entity.Entity;
import com.jfinal.ext.codeonline.webdesigner.project.Project;
import com.jfinal.ext.codeonline.webdesigner.task.Task;
import com.jfinal.ext.codeonline.webdesigner.task.TaskParam;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.jfinal.ext.codeonline.core.Constants.FS;

public class TaskKit {
    private static Logger LOG = Logger.getLogger(TaskKit.class);
    public static TemplateHelper templateHelp;

    public static List<String> processTask(Project project, Task task) {
        List<String> paths;
        List<Entity> entities = project.getEntities();
        Map<String, Object> root = Maps.newHashMap();
        root.put("project", project);
        root.put("entities", entities);
        root.put("configDataProvider", Config.getConfigDataProvider());
        root.put("TaskKit", TaskKit.class);
        for (TaskParam taskParam : task.getTaskParams()) {
            root.put(taskParam.getStr("key"), ScriptKit.exec(taskParam.getStr("expression"), root));
        }
        initTemplateHelp(root);
        paths = task.run(project, root);
        return paths;
    }

    /**
     * @param root
     * @param task
     * @return 生成文件的路径
     */
    public static String processTemplate(Map<String, Object> root, Task task) {

        List<TaskParam> params = task.getTaskParams();
        for (TaskParam param : params) {
            if (StrKit.isBlank(param.getStr("name"))) continue;
            String value = param.getStr("expression");
            templateHelp.put(param.getStr("name"), ScriptKit.exec(value, root));
        }
        String templetFilename = ScriptKit.exec(task.getStr("templatepath"), root).toString();
        String folder = ScriptKit.exec(task.getStr("folder"), root).toString();
        folder = Config.getTargetPath() + FS + ((Project) root.get("project")).getStr("name") + File.separator + folder;
        File floderDir = new File(folder);
        if (!floderDir.exists()) {
            floderDir.mkdirs();
        }
        String filename = ScriptKit.exec(task.getStr("filename"), root).toString();
        String outputFilename = folder + FS + filename;
        LOG.debug(outputFilename);
        templateHelp.process(templetFilename, outputFilename);
        return outputFilename;

    }

    private static void initTemplateHelp(Map<String, Object> root) {
        templateHelp = new TemplateHelper(Config.getTemplatePath());
        templateHelp.putAll(root);

    }

    public static String capitalize(String str) {
        return StrKit.firstCharToUpperCase(str);
    }


}
