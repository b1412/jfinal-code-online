package com.jfinal.codeonline.core;

import com.jfinal.codeonline.ui.dwz.group.Group;
import com.jfinal.plugin.activerecord.Record;

public interface IGroupInitializer {
    void init(Record project,Group groups);
}
