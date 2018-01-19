package com.example.jujiiz.mis.models;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CallApiPost extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {

        try {
            JSONArray jsonArrayToSend = new JSONArray(params[1]);
            URL url = new URL(params[0]);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);
            httpCon.setUseCaches(false);
            httpCon.setRequestProperty( "Content-Type", "application/json" );
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestMethod("POST");
            httpCon.connect();
            OutputStream os = httpCon.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(params[1]);
            osw.flush();
            osw.close();
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader( httpCon.getInputStream(),"utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            reader.close();
            return stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result)  {
        super.onPostExecute(result);
    }
}