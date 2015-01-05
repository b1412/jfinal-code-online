package com.jfinal.codeonline.ui.dwz.common;

import com.google.common.collect.Lists;
import com.jfinal.core.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BaseController<T> extends Controller {

    /**
     * 获取前端传来的数组对象并响应成Model列表
     *
     * @param modelClass
     * @param modelName
     * @return
     * @author yongtree
     * @date 2013-9-26上午10:22:38
     */
    @SuppressWarnings("unchecked")
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
