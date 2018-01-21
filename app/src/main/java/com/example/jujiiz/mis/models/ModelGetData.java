package com.example.jujiiz.mis.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

/**
 * Created by JuJiiz on 17/7/2560.
 */

public class ModelGetData {
    public static JSONArray getJsonArray(Context context, String apiURL, String pname) {
        JSONArray jsonArray = null;
        String result = "";
        try {
            String url = apiURL;
            String strGetJson = new CallApi().execute(url).get();
            if (strGetJson!=null){
                JSONObject jsonObject = new JSONObject(strGetJson);
                result = new String(jsonObject.getString(pname).getBytes("ISO-8859-1"), "UTF-8");
                //result = jsonObject.getString(pname);
                jsonArray = new JSONArray(result);
            }else {
                jsonArray = null;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

}
