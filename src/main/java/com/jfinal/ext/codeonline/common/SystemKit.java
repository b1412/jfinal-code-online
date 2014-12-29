
package com.jfinal.ext.codeonline.common;

import java.io.IOException;

public class SystemKit {
    public static String invoke(String cmd) {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            p = rt.exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException("cmd exec faild", e);
        } finally {
            if (p != null) {
                try {
                    p.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                p.destroy();
            }
        }
        return cmd;
    }
}
