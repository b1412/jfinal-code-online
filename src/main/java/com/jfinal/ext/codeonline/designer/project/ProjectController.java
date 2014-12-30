package com.jfinal.ext.codeonline.designer.project;

import com.jfinal.core.Controller;
import com.jfinal.ext.codeonline.core.Config;
import com.jfinal.ext.codeonline.core.JFinalCodeOnline;
import com.jfinal.ext.codeonline.designer.group.Groups;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.plugin.activerecord.Page;
import de.java2html.Java2Html;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;

import java.io.File;
import java.io.IOException;

public class ProjectController extends Controller {

    public void index() {
        Page<Project> page = Project.DAO.page(getParaToInt(0, 1), 20);
        setAttr("page", page);
    }

    public void save() {
        Project model = getModel(Project.class);
        model.set("groups_id", 1);
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
        setAttr("dbInfos", Config.getConfigDataProvider().dbTypes());
        setAttr("viewFrameworks", Config.getConfigDataProvider().viewFrameworks());
        setAttr("viewEngines", Config.getConfigDataProvider().viewEngines());
    }


    public void delete() {
        Project.DAO.deleteById(getPara(0));
        render(DwzRender.success());
    }

    public void create() {
        Project project = Project.DAO.findById(getParaToInt(0));
        Groups groups = project.getGroups();
        setAttr("project", project);
        new JFinalCodeOnline(project) .run(groups);
        setAttr("root", new File(Config.getTargetPath() + File.separator + project.getStr("name")));
    }

    public void viewFile() throws IOException {
        String fileName = getPara("path");
        JavaSource javaSource = new JavaSourceParser().parse(new File(fileName));
        renderHtml(Java2Html.convertToHtmlPage(javaSource.getCode()));

    }
}
