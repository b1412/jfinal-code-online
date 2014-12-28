package com.jfinal.ext.codeonline.common;

import com.google.common.collect.Lists;
import com.jfinal.ext.codeonline.core.Config;
import com.jfinal.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.jfinal.ext.codeonline.core.Constants.FS;

public class ZipKit {

    private static Logger LOG = Logger.getLogger(ZipKit.class);

    private List<String> fileList = Lists.newArrayList();

    public void zipIt(String zipFile) {
        byte[] buffer = new byte[1024];
        FileOutputStream fos;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            LOG.debug("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file : this.fileList) {
                LOG.debug("File Added : " + file);
                ZipEntry ze = new ZipEntry(getAbsFileName(Config.getTargetPath(), file));
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(file);
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            }

            zos.closeEntry();
            LOG.debug("Folder successfully compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateFileList(File node) {

        if (node.isFile()) {
            fileList.add(node.toString());
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }

    private static String getAbsFileName(String baseDir, String realFileName) {
        File real = new File(realFileName);
        File base = new File(baseDir);
        String ret = real.getName();
        while (true) {
            real = real.getParentFile();
            if (real == null) {
                break;
            }
            if (real.equals(base)) {
                break;
            } else {
                ret = real.getName() + FS + ret;
            }
        }
        return ret;
    }
}    