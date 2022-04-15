package com.gs.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class DataUtil {

    public static <T> T mapToObject(Map<String, ?> map, Type type) {

        Gson gson = new Gson();
        String json = null;
        T obj;

        json = objectToJsonString(map);

        obj = gson.fromJson(json, type);

        return obj;
    }

    public static <T> T mapToObject(Map<String, ?> map, Class<T> classOfT) {

        Gson gson = new Gson();
        String json = null;
        T obj;

        json = objectToJsonString(map);

        obj = gson.fromJson(json, classOfT);

        return obj;
    }

    public static String objectToJsonString(Object args) {

        String json = null;
        Gson gson = new Gson();

        json = gson.toJson(args);

        return json;
    }
}
