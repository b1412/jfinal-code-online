package com.jfinal.codeonline.core.enums;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class YesOrNo {
    public static final List<Record> RECORDS = Lists.newArrayList();

    static {
        RECORDS.add(new Record().set("display", "是").set("value", "1"));
        RECORDS.add(new Record().set("display", "否").set("value", "0"));
    }
    public String display(String value){
        for (Record record : RECORDS) {
            if(record.getStr("value").equals(value)){
                return  record.getStr("display");
            }
        }
        throw  new RuntimeException("Not support value "+value);
    }
}
