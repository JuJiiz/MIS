package com.example.jujiiz.mis.models;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by JuJiiz on 20/7/2560.
 */

public class ModelGetJson {

    public static ArrayList JsonArraytoArrayList(JSONArray jsonArray) {
        //ArrayList<String> VILLAGE_LIST = new ArrayList<String>();
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        try {

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                Iterator<String> iter = jsonObj.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    try {
                        Object value = jsonObj.get(key);
                        temp.put(key,value.toString());
                    } catch (JSONException e) {
                        // Something went wrong!
                    }
                }
                LIST.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return LIST;
    }
}
