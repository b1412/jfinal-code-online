package com.jfinal.codeonline.ext;

import com.google.common.collect.Maps;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ui.dwz.entity.Entity;
import com.jfinal.codeonline.ui.dwz.project.Project;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.codeonline.ui.dwz.task.TaskParam;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.jfinal.codeonline.core.Constants.FS;

public class TaskKit {
    private static Logger LOG = Logger.getLogger(TaskKit.class);

    public static List<String> processTask(Project project, Task task) {
        List<String> paths;
        List<Entity> entities = project.getEntities();
        Map<String, Object> scope = Maps.newHashMap();
        scope.put("project", project);
        scope.put("entities", entities);
        scope.put("configDataProvider", Config.configDataProvider());
        scope.put("TaskKit", TaskKit.class);
        for (TaskParam taskParam : task.getTaskParams()) {
            scope.put(taskParam.getStr("key"), Config.scriptHelper().exec(taskParam.getStr("expression"), scope));
        }
        Config.templateEngine().putAll(scope);
        paths = task.run(project, scope);
        return paths;
    }

    /**
     * @param root
     * @param task
     * @return 生成文件的路径
     */
    public static String processTemplate(Map<String, Object> root, Task task) {
        Project project = (Project) root.get("project");
        List<TaskParam> params = task.getTaskParams();
        for (TaskParam param : params) {
            if (StrKit.isBlank(param.getStr("name"))) continue;
            String value = param.getStr("expression");
            Config.templateEngine().put(param.getStr("name"), Config.scriptHelper().exec(value, root));
        }
        String templateFilename = project.getGroups().getStr("name") + FS + Config.scriptHelper().exec(task.getStr("templatePath"), root).toString();
        String folder = Config.scriptHelper().exec(task.getStr("folder"), root).toString();
        folder = Config.targetPath() + FS + project.getStr("name") + File.separator + folder;
        File folderDir = new File(folder);
        if (!folderDir.exists()) {
            folderDir.mkdirs();
        }
        String filename = Config.scriptHelper().exec(task.getStr("filename"), root).toString();
        String outputFilename = folder + FS + filename;
        LOG.debug(outputFilename);
        Config.templateEngine().exec(templateFilename, outputFilename);
        return outputFilename;

    }

    public static String capitalize(String str) {
        return StrKit.firstCharToUpperCase(str);
    }

    public static String buildFileName(String str) {
        if ("gradle".equals(str)) {
            return "build.gradle";
        } else if ("maven".equals(str)) {
            return "pom.xml";
        } else {
            throw new IllegalArgumentException("Not support " + str);
        }
    }

}
