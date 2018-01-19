package com.example.jujiiz.mis.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class ModelSendApi {
    String URL_FORM_EDIT = "https://api.cinfo.co.th/v2/form_edit";
    String URL_FORM_INSERT = "https://api.cinfo.co.th/v2/form_insert";
    String STRING_JSON_OBJECT = "";
    JSONArray JS_ARRAY_DATA;
    Context context = null;

    public ModelSendApi(Context pContext, String formName) {
        this.context = pContext;
        JS_ARRAY_DATA = new JSONArray();
        add("formName", formName);
        SharedPreferences sp = context.getSharedPreferences("myStorage", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        String districtCode = ModelToken.getByName(token, "cus_data.DISTRICT_CODE");
        String uID = ModelToken.getByName(token, "uID");
        Log.d("PPPPPPPPPPPP", "uID: "+uID);
        add("surveyer",uID);
        add("token", token);
        add("DISTRICT_CODE", districtCode);
    }

    public String get() {
        return STRING_JSON_OBJECT;
    }

    public void set() {
        STRING_JSON_OBJECT =
                "{\"name\":\"F01-12-01\", \"value\":\"0\"}," +
                        "{\"name\":\"F01-12-01-01-01\", \"value\":\"ทดสอบ ส่งจาก android\"}," +
                        "{\"name\":\"F01-12-01-01-02\", \"value\":\"หน่อยนะ อีกครั้ง\"}," +
                        "{\"name\":\"F01-12-01-01-03\", \"value\":\"1234567890123\"}," +
                        "{\"name\":\"F01-12-01-02-01\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-01-03-01\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-01-04-01\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-02\", \"value\":\"โรงหมู\"}," +
                        "{\"name\":\"F01-12-04\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-05\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-06\", \"value\":\"โรงฆ่าสุกร\"}," +
                        "{\"name\":\"F01-12-06-01\", \"value\":\"\"}," +
                        "{\"name\":\"E\", \"value\":\"\"}," +
                        "{\"name\":\"N\", \"value\":\"\"}," +
                        "{\"name\":\"F12_village_select\", \"value\":\"90000101\"}," +
                        "{\"name\":\"F01-12-11\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-13\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-14\", \"value\":\"5\"}," +
                        "{\"name\":\"F01-12-15\", \"value\":\"11\"}," +
                        "{\"name\":\"F01-12-18\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-19\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-L-01\", \"value\":\"default\"}," +
                        "{\"name\":\"F01-12-phone\", \"value\":\"\"}," +
                        "{\"name\":\"imgPath\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-S2-01\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-S2-02\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-S2-03\", \"value\":\"\"}," +
                        "{\"name\":\"F01-12-S2-04\", \"value\":\"\"}," +
                        "{\"name\":\"successForm\", \"value\":\"100\"}," +
                        "{\"name\":\"surveyer\", \"value\":\"9000010128\"}," +
                        "{\"name\":\"recordDate\", \"value\":\"2017-07-24T09:09:30.161Z\"}," +
                        "{\"name\":\"token\", \"value\":\"900001-7fac2cf9d14549bcf718364024c6bb22df909860\"}," +
                        "{\"name\":\"DISTRICT_CODE\", \"value\":\"900001\"}," +
                        "{\"name\":\"formName\", \"value\":\"F01_12\"}," +
                        "{\"name\":\"task_id\", \"value\":\"5975b93ac9d8ea000828f5e6\"}";
    }

    public void add(String name, String value) {
        try {
            boolean found = false;
            for (int i = 0; i < JS_ARRAY_DATA.length(); i++) {
                if (JS_ARRAY_DATA.getJSONObject(i).getString("name").equals(name)) {
                    JS_ARRAY_DATA.remove(i);
                    break;
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("value", value);
            JS_ARRAY_DATA.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String send() {
        String year = (Calendar.getInstance().get(Calendar.YEAR) + 543) + "";
        String timeStamp = year + new SimpleDateFormat("-MM-dd'T'HH:mm:ss.SSS'Z'").format(Calendar.getInstance().getTime());
        //Log.d("=================", "send: "+timeStamp);
        add("recordDate", timeStamp);
        String strSend = "[" + STRING_JSON_OBJECT + "]";
        String result = null;
        try {
            result = new CallApiPost().execute("https://api.cinfo.co.th/v2/form_edit", strSend).get();
            Log.i("1111111111111111", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sendInsert() {

        String year = (Calendar.getInstance().get(Calendar.YEAR) + 543) + "";
        String timeStamp = year + new SimpleDateFormat("-MM-dd'T'HH:mm:ss.SSS'Z'").format(Calendar.getInstance().getTime());
        add("recordDate", timeStamp);
        add("task_id","new");
        String strSend = JS_ARRAY_DATA.toString();
        String result = "";
        try {
            Log.e("***********", "jsonArray: "+strSend);
            result = new CallApiPost().execute(URL_FORM_INSERT, strSend).get();
            Log.i("1111111111111111", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sendEdit(String ptask_id) {
        String year = (Calendar.getInstance().get(Calendar.YEAR) + 543) + "";
        String timeStamp = year + new SimpleDateFormat("-MM-dd'T'HH:mm:ss.SSS'Z'").format(Calendar.getInstance().getTime());

        add("recordDate", timeStamp);
        add("task_id",ptask_id);
        String strSend = JS_ARRAY_DATA.toString();
        String result = "";
        try {
            result = new CallApiPost().execute(URL_FORM_EDIT, strSend).get();
            Log.i("1111111111111111", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void clear() {
        STRING_JSON_OBJECT = "";
    }

}
