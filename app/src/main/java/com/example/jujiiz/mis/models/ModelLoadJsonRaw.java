package com.example.jujiiz.mis.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by JuJiiz on 5/2/2561.
 */

public class ModelLoadJsonRaw {
    public static JSONArray loadJsonFile(InputStream inputStream, String First, String Second){
        JSONArray jsonArray = new JSONArray();

        try {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                int n;

                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }

            } finally {
                inputStream.close();
            }
            String jsonString = writer.toString();
            JSONObject jsonObjAll = new JSONObject(jsonString);
            String jsonTH = jsonObjAll.getString(First);
            JSONObject jsonObjTH = new JSONObject(jsonTH);
            String jsonProvince = jsonObjTH.getString(Second);
            jsonArray = ModelSort.sortJsonArray(jsonProvince);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
