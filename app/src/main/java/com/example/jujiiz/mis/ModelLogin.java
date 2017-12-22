package com.example.jujiiz.mis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ModelLogin {
    public static String getByName(String pusername, String pkey, String pname){
        String resultValue = "";
        try {
            String url = "https://api.cinfo.co.th/v2/authen?username="+pusername+"&key="+pkey;
            String strGetJson = new CallApi().execute(url).get();

            JSONObject jsonObject = new JSONObject(strGetJson);
            resultValue = jsonObject.getString(pname);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

}

