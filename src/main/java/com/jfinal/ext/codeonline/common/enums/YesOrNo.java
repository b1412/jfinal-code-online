package com.jfinal.ext.codeonline.common.enums;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * Created by kid on 14-12-26.
 */
public class YesOrNo {
    public  static  List<Record> RECORDS = Lists.newArrayList();

    static {
        Record yes = new Record();
        yes.set("display","是");
        yes.set("value","1");
        RECORDS.add(yes);
        Record no = new Record();
        no.set("display","否");
        no.set("value","0");
        RECORDS.add(no);
    }
}
