package com.jfinal.codeonline.ui.dwz.project;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfinal.codeonline.core.CodeOnline;
import com.jfinal.codeonline.core.Config;
import com.jfinal.codeonline.ext.SqlKit;
import com.jfinal.codeonline.ui.dwz.common.BaseController;
import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.group.GroupMetadata;
import com.jfinal.codeonline.ui.dwz.task.Task;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import de.java2html.Java2Html;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.jfinal.codeonline.core.Constants.FS;

public class ProjectController extends BaseController {

    public void index() {
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<GroupMetadata> projectMetadata = group.getProjectMetadata();
        Page<Record> page = Db.paginate(getParaToInt(0, 1), 20, "select itemId",
                "from project_item where `groupId` = ? group by itemId order by itemId asc", group.get("id"));
        List<Record> list = page.getList();
        for (Record record : list) {
            List<Record> data = Db.find("select `column`,`value` from project_item where itemId = ?", record.getInt("itemId"));
            for (Record item : data) {
                record.set(item.getStr("column"), item.getStr("value"));
            }
        }
        setAttr("page", page);
        setAttr("metadataList", projectMetadata);
        render("index.html");
    }

    public void save() {
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        Record record = (Record) getRecord("record");
        Set<Map.Entry<String, Object>> entries = record.getColumns().entrySet();
        int itemId = getParaToInt("itemId", 0);
        if (itemId > 0) {
            ProjectItem.DAO.deleteByColumn("itemId", itemId);
        }
        List<ProjectItem> items = Lists.newArrayList();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            ProjectItem item = new ProjectItem();
            item.set("groupId", group.get("id"));
            item.set("column", key);
            if (value != null) {
                item.set("value", value);
            }
            items.add(item);
        }
        if (!items.isEmpty()) {
            for (ProjectItem item : items) {
                item.save();
            }
            if (itemId == 0) {
                itemId = items.get(0).get("id");
            }
            for (ProjectItem item : items) {
                item.set("itemId", itemId);
                item.update();
            }
        }
        render(DwzRender.closeCurrentAndRefresh("project"));
    }

    public void edit() {
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<GroupMetadata> metadataList = group.getProjectMetadata();
        List<ProjectItem> projectItems = ProjectItem.DAO.findByColumn("itemId", getPara());
        Record record = ProjectItem.DAO.toProject(projectItems);
        setAttr("itemId", record.get("itemId"));
        setAttr("record", record);
        setAttr("metadataList", metadataList);
        Map<String, Object> map = Maps.newHashMap();
        map.put("dbType", Config.configDataProvider().dbTypes());
        map.put("viewFramework", Config.configDataProvider().viewFrameworks());
        map.put("viewEngine", Config.configDataProvider().viewEngines());
        map.put("buildTool", Config.configDataProvider().buildTools());
        setAttr("map", map);
    }

    public void delete() {
        //TODO 级联删除
        ProjectItem.DAO.deleteByColumn("itemId", getPara(0));
        render(DwzRender.success());
    }

    public void selectTasks() {
        Record project = Config.modelProvider().getProject(getParaToInt(0));
        System.out.println(project);
        Group group = Group.DAO.findFirst("select * from `group` where valid = ?", 1);
        List<Task> tasks = group.tasks();
        Page<Task> page = new Page<Task>(tasks, 1, tasks.size(), 1, tasks.size());
        setAttr("project", project);
        setAttr("page", page);
    }

    public void create() {
        Record project = Config.modelProvider().getProject(getPara());
        Group group = Config.modelProvider().getGroup(project);
        List<String> taskIds = Lists.newArrayList(getParaValues("taskIds"));
        CodeOnline.on(project).run(group, ((List<Task>) SqlKit.batchQuery(Task.class, taskIds)));
        setAttr("root", new File(Config.targetPath() + FS + project.get("name")));
        render(DwzRender.success("Generated Successfully"));
    }

    public void tree() {
        Record project = Config.modelProvider().getProject(getParaToInt(0));
        File root = new File(Config.targetPath() + FS + project.get("name"));
        if (root.exists()) {
            setAttr("root", root);
        }
        render("tree.html");
    }

    public void clean() throws IOException {
        ProjectItem project = ProjectItem.DAO.findById(getParaToInt(0));
        FileUtils.deleteDirectory(project.rootFile());
        render(DwzRender.success());
    }


    public void viewFile() throws IOException {
        String fileName = getPara("path");
        JavaSource javaSource = new JavaSourceParser().parse(new File(fileName));
        String value = Java2Html.convertToHtmlPage(javaSource.getCode());
        setAttr("content", value);
    }
}
