package com.example.jujiiz.mis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ModelToken {

    public static String getByName(String ptoken, String pname){
        String result = "";
        try {
            String url = "https://api.cinfo.co.th/v2/userData?token=" + ptoken;
            String strGetJson = new CallApi().execute(url).get();

            String[] keyNames = pname.split("\\.");
            JSONObject jsonObject = new JSONObject(strGetJson);
            for (int i = 0; i < keyNames.length; i++){
                if(i == keyNames.length-1){
                    result = jsonObject.getString(keyNames[i]);
                }else{
                    jsonObject = jsonObject.getJSONObject(keyNames[i]);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkToken(final MainActivity context){
        SharedPreferences sp = context.getSharedPreferences("myStorage", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");
        if(ModelToken.getByName(token,"status").equals("OK")){
            return true;
        }else{
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setCancelable(false);
            alertDialog.setTitle("หมดเวลาเชื่อมต่อ");
            alertDialog.setMessage("หมดเวลาในการเชื่อมต่อ \nกรุณา\"เข้าสู่ระบบ\"ใหม่");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent();
                            intent.setClass(context,LoginActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    });
            alertDialog.show();
            return false;
        }
    }
}
