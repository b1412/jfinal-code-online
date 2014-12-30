package com.jfinal.ext.codeonline.common;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

import java.io.File;

public class AntKit {

    public static void copy(String descDir, String configDir) {
        File destDir = new File(descDir);
        destDir.mkdirs();

        Project project = new Project();
        Copy copy = new Copy();
        copy.setTodir(destDir);

        FileSet configFileSet = new FileSet();
        configFileSet.setDir(new File(configDir));
        copy.addFileset(configFileSet);
        copy.setProject(project);
        copy.execute();
    }


    public static void javac(String srcdir, String descDir, String classpath) {
        Javac javac = new Javac();
        Project project = new Project();
        Path srcDir = new Path(project, srcdir);
        javac.setSrcdir(srcDir);
        File destDir = new File(descDir);
        destDir.mkdirs();
        javac.setDestdir(destDir);
        javac.setDebug(true);
        Path path = new Path(project);
        FileSet fileSet = new FileSet();
        fileSet.setDir(new File(classpath));

        path.addFileset(fileSet);
        javac.setClasspath(path);
        javac.setProject(project);
        javac.setTarget("1.6");
        javac.setSource("1.6");
        javac.execute();
    }

    public static void sqlScript(String driver, String url, String user, String passwd, String sqlFilePath) {
        SQLExec sqlExec = new SQLExec();
        sqlExec.setDriver(driver);
        sqlExec.setUrl(url);
        sqlExec.setUserid(user);
        sqlExec.setPassword(passwd);
        sqlExec.setSrc(new File(sqlFilePath));
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project());
        sqlExec.execute();
    }

}
