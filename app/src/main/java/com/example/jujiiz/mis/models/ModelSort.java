package com.example.jujiiz.mis.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JuJiiz on 4/2/2561.
 */

public class ModelSort {
    public static JSONArray sortJsonArray(String strJSON) {
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        try {
            JSONArray array = new JSONArray(strJSON);
            for (int i = 0; i < array.length(); i++) {
                jsons.add(array.getJSONObject(i));
            }
            Collections.sort(jsons, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject lhs, JSONObject rhs) {
                    String lid = "", rid = "";
                    try {
                        lid = lhs.getString("name");
                        rid = rhs.getString("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return lid.compareTo(rid);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray(jsons);
    }
}
