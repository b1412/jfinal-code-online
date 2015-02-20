package com.jfinal.codeonline.ui.dwz.common;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author xwalker
 */
public class RecordInjector {
    public static <T> T inject(Class<?> recordClass, HttpServletRequest request, boolean skipConvertError) {
        String recordName = recordClass.getSimpleName();
        return inject(recordClass, StrKit.firstCharToLowerCase(recordName), request, skipConvertError);
    }

    @SuppressWarnings({"unchecked"})
    public static final <T> T inject(Class<?> recordClass, String recordName, HttpServletRequest request, boolean skipConvertError) {
        Record record = null;
        try {
            record = (Record) recordClass.newInstance();
        } catch (Exception e) {
        }

        injectRecord(record, recordName, request, recordClass, skipConvertError);

        return (T) record;
    }

    private static final void injectRecord(Record record, String recordName, HttpServletRequest request, Class<?> recordClass, boolean skipConvertError) {
        Map<String, String[]> paramMap = request.getParameterMap();
        String start = recordName + ".";
        String[] value;
        for (Entry<String, String[]> param : paramMap.entrySet()) {
            if (!param.getKey().startsWith(start)) {
                continue;
            }
            value = param.getValue();
            if (value.length != 0 && StrKit.notBlank(value[0])) {
                if (value.length == 1) {
                    record.set(removeStart(param.getKey(), start), value[0]);
                } else {
                    record.set(removeStart(param.getKey(), start), value);
                }

            } else {
                record.set(removeStart(param.getKey(), start), null);
            }
        }
    }

    private static String removeStart(String key, String start) {
        return key.substring(start.length());
    }

}