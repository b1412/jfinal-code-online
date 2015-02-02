package com.jfinal.codeonline.core;

import java.util.Map;

public interface IScriptHelper {
    @SuppressWarnings("unchecked")
    <T> T exec(String express, Map<String, Object> context);
}