package com.jfinal.codeonline.core.enums;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class TaskType {
    public static final List<Record> RECORDS = Lists.newArrayList();

    static {
        RECORDS.add(new Record().set("display", "project").set("value", "project"));
        RECORDS.add(new Record().set("display", "entity").set("value", "entity"));
    }
}
