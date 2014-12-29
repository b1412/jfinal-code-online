package com.jfinal.ext.codeonline.common;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BaseController extends Controller {
    /**
     * 获取以modelName开头的参数，并自动赋值给record对象
     *
     * @param modelName
     * @return
     * @author yongtree
     * @date 2013-9-25上午9:14:00
     */
    public Record getRecord(String modelName) {
        String modelNameAndDot = modelName + ".";
        Record model = new Record();
        boolean exist = false;
        Map<String, String[]> parasMap = getRequest().getParameterMap();
        for (Map.Entry<String, String[]> e : parasMap.entrySet()) {
            String paraKey = e.getKey();
            if (paraKey.startsWith(modelNameAndDot)) {
                String paraName = paraKey.substring(modelNameAndDot.length());
                String[] paraValue = e.getValue();
                Object value = paraValue[0] != null ? (paraValue.length == 1 ? paraValue[0]
                        : Joiner.on(",").join(paraValue, ","))
                        : null;
                model.set(paraName, value);
                exist = true;
            }
        }
        if (exist) {
            return model;
        } else {
            return null;
        }
    }

    /**
     * 获取前端传来的数组对象并响应成Record列表
     *
     * @param modelName
     * @return
     * @author yongtree
     * @date 2013-9-26上午10:21:38
     */
    public List<Record> getRecords(String modelName) {
        List<String> nos = getModelsNoList(modelName);
        List<Record> list = new ArrayList<Record>();
        for (String no : nos) {
            Record r = getRecord(modelName + "[" + no + "]");
            if (r != null) {
                list.add(r);
            }
        }
        return list;
    }

    /**
     * 获取前端传来的数组对象并响应成Model列表
     *
     * @param modelClass
     * @param modelName
     * @return
     * @author yongtree
     * @date 2013-9-26上午10:22:38
     */
    public <T> List<T> getModels(Class<T> modelClass, String modelName) {
        List<String> nos = getModelsNoList(modelName);
        List<T> list = Lists.newArrayList();
        for (String no : nos) {
            T m = getModel(modelClass, modelName + "[" + no + "]");
            if (m != null) {
                list.add(m);
            }
        }
        return list;
    }

    /**
     * 提取model对象数组的标号
     *
     * @param modelName
     * @return
     * @author yongtree
     * @date 2013-9-26上午10:17:14
     */
    protected List<String> getModelsNoList(String modelName) {
        // 提取标号
        List<String> list = Lists.newArrayList();
        String modelNameAndLeft = modelName + "[";
        Map<String, String[]> parasMap = getRequest().getParameterMap();
        for (Map.Entry<String, String[]> e : parasMap.entrySet()) {
            String paraKey = e.getKey();
            if (paraKey.startsWith(modelNameAndLeft)) {
                String no = paraKey.substring(paraKey.indexOf("[") + 1,
                        paraKey.indexOf("]"));
                if (!list.contains(no)) {
                    list.add(no);
                }
            }
        }
        Collections.sort(list);
        return list;
    }
}
