package com.jfinal.codeonline.core;

import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.codeonline.ui.dwz.project.Project;

public interface IGroupInitializer {
    void init(Project project,Group groups);
}
