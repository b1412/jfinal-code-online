package com.jfinal.codeonline.core.enums;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class MetadataType {
    public static final List<Record> RECORDS = Lists.newArrayList();

    static {
        RECORDS.add(new Record().set("display", "string").set("value", "string"));
        RECORDS.add(new Record().set("display", "int").set("value", "int"));
        RECORDS.add(new Record().set("display", "boolean").set("value", "boolean"));
        RECORDS.add(new Record().set("display", "enum").set("value", "enum"));
    }
}
