package com.jfinal.codeonline.ui.dwz.project;

import com.jfinal.codeonline.core.CodeOnline;
import com.jfinal.codeonline.ui.dwz.group.Groups;
import com.jfinal.core.Controller;
import com.jfinal.codeonline.core.Config;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Page;
import de.java2html.Java2Html;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;

import java.io.File;
import java.io.IOException;

import static com.jfinal.codeonline.core.Constants.FS;

public class ProjectController extends Controller {

    public void index() {
        Page<Project> page = Project.DAO.page(getParaToInt(0, 1), 20);
        setAttr("page", page);
    }

    public void save() {
        Project model = getModel(Project.class);
        model.set("groupsId", 1);
        if (model.getInt("id") == null) {
            model.save();
        } else {
            model.update();
        }
        render(DwzRender.closeCurrentAndRefresh("project"));
    }

    public void edit() {
        Project project = Project.DAO.findById(getPara(0));
        if (project == null) {
            project = new Project();
        }
        setAttr("project", project);
        setAttr("dbInfos", Config.configDataProvider().dbTypes());
        setAttr("viewFrameworks", Config.configDataProvider().viewFrameworks());
        setAttr("viewEngines", Config.configDataProvider().viewEngines());
    }


    public void delete() {
        Project.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }

    public void create() {
        Project project = Project.DAO.findById(getParaToInt(0));
        Groups groups = project.getGroups();
        setAttr("project", project);
        new CodeOnline(project).run(groups);
        setAttr("root", new File(Config.targetPath() + FS + project.getStr("name")));
    }

    public void viewFile() throws IOException {
        String fileName = getPara("path");
        JavaSource javaSource = new JavaSourceParser().parse(new File(fileName));
        renderHtml(Java2Html.convertToHtmlPage(javaSource.getCode()));
    }
}
