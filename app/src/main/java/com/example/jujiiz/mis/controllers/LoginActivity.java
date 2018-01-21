package com.example.jujiiz.mis.controllers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelParseJson;
import com.example.jujiiz.mis.models.ModelSendApi;
import com.example.jujiiz.mis.models.ModelToken;
import com.example.jujiiz.mis.models.Sha1Hash;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText etUsername, etPassword;
    String pUsername, pPassword;
    Intent intent = null;
    String jsonResult = "";
    HashMap<String, String> loginTemp = new HashMap<String, String>();
    String parseJSON = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
    }

    private void init() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            /*ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = manager.getActiveNetworkInfo();*/
            //if (isConnectedToServer("http://203.154.54.229/chklogin", 10000) == true) {
                try {
                    pUsername = etUsername.getText().toString();
                    pPassword = etPassword.getText().toString();

                    loginTemp.put("username", pUsername);
                    loginTemp.put("password", pPassword);
                    parseJSON = ModelParseJson.HashmapToJsonlist(loginTemp);

                    jsonResult = ModelSendApi.send("http://203.154.54.229/chklogin", parseJSON);

                    JSONObject jsonObject = new JSONObject(jsonResult);
                    //Log.d("MYLOG", "jsonResult: " + jsonResult);

                    String loginStatus = jsonObject.getString("status");

                    if (loginStatus.equals("ok")) {
                        String loginUserName = jsonObject.getString("username");
                        String loginUserStatus = jsonObject.getString("userstatus");
                        String loginUserID = jsonObject.getString("userid");

                        SharedPreferences sp = getSharedPreferences("UserMemo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username", loginUserName);
                        editor.putString("userstatus", loginUserStatus);
                        editor.putString("userid", loginUserID);
                        editor.commit();

                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "ชื่อผู้ใช้หรือรหัสผ่าน ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            /*} else {
                Toast.makeText(this, "การเชื่อมต่อมีปัญหา", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    public boolean isConnectedToServer(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
