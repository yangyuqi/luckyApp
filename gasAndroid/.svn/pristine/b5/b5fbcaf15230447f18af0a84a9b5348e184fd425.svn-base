package com.yunqilai.delivery.request.base;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KK on 2017/2/17.
 */
public class GsonDataParser {
    private Gson mGson = new Gson();

    public String toDataStr(Object object) {
        return mGson.toJson(object);
    }
    public <T> T parseObject(String data, Class<T> clsOfT) {
        return mGson.fromJson(data, clsOfT);
    }

    public <T> List<T> parseList(String json, Class<T> clz) {
        try {
            Type type = new TypeToken<ArrayList<JsonObject>>()
            {}.getType();
            ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

            ArrayList<T> arrayList = new ArrayList<>();
            for (JsonObject jsonObject : jsonObjects)
            {
                arrayList.add(mGson.fromJson(jsonObject, clz));
            }
            return arrayList;
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public <T> T getValue(String data, String key, Class<T> clsOfT) {
        String value=null;
        try {
            JsonObject jsonData = new JsonObject();
            JsonObject result = jsonData.getAsJsonObject(key);
            return parseObject(mGson.toJson(result), clsOfT);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
