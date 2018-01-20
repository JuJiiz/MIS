package com.example.jujiiz.mis.models;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JuJiiz on 19/1/2561.
 */

public class ModelParseJson {
    public static String ArraylistToJsonlist(ArrayList<HashMap<String, String>> arrayList) {
        String parseAJ = "parseAJ";
        JSONArray jsonArray = new JSONArray(arrayList);
        parseAJ = jsonArray.toString();
        Log.d("MYLOG", "parseAJ: " + parseAJ);
        return parseAJ;
    }

    public static String HashmapToJsonlist(HashMap<String, String> hashMap) {
        String parseHJ = "parseHJ";
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
        arrayList.add(hashMap);
        JSONArray jsonArray = new JSONArray(arrayList);
        String str1 = jsonArray.toString().replace("[","");
        parseHJ = str1.replace("]","");
        Log.d("MYLOG", "parseAJ: " + parseHJ);
        return parseHJ;
    }
}
