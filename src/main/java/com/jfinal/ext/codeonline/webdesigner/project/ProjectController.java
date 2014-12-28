package com.jfinal.ext.codeonline.webdesigner.project;

import com.jfinal.core.Controller;
import com.jfinal.ext.codeonline.common.ZipKit;
import com.jfinal.ext.codeonline.core.Config;
import com.jfinal.ext.codeonline.core.JFinalCodeOnline;
import com.jfinal.ext.codeonline.webdesigner.group.Groups;
import com.jfinal.ext.render.DwzRender;
import com.jfinal.kit.PathKit;
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
        setAttr("paths", new JFinalCodeOnline(project)
                .run(groups));
    }

    public void viewFile() throws IOException {
        String fileName = getPara("path");
        JavaSource javaSource = new JavaSourceParser().parse(new File(fileName));
        renderHtml(Java2Html.convertToHtmlPage(javaSource.getCode()));

    }

    public void download() {
        Project project = Project.DAO.findById(getPara());
        String projectName = project.getStr("name");
        String source_folder = PathKit.getWebRootPath() + "/target/" + projectName;
        String output_zip_file = source_folder + "/" + projectName + ".zip";
        new File(output_zip_file).delete();
        ZipKit appZip = new ZipKit();
        appZip.generateFileList(new File(source_folder));
        appZip.zipIt(output_zip_file);
        renderFile(new File(output_zip_file));
    }


    public static void main(String[] args) throws IOException {
        String egyik = Java2Html.convertToHtmlPage("if('egyik'.equals){System.out.println('masik');}");
        String javaFile = null;
        new JavaSourceParser().parse(new File(javaFile));
        System.out.println(egyik);
    }

}
