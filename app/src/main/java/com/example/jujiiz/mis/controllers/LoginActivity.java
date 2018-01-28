package com.example.jujiiz.mis.controllers;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

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

    class AsynTaskLogin extends AsyncTask<Void, Void, Void> {
        private final ProgressDialog dialogLogin = new ProgressDialog(LoginActivity.this);

        // can use UI thread here
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialogLogin.setMessage("กำลังเชื่อมต่อ...");
            this.dialogLogin.setCancelable(false);
            this.dialogLogin.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = manager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                try {
                    pUsername = etUsername.getText().toString();
                    pPassword = etPassword.getText().toString();

                    loginTemp.put("username", pUsername);
                    loginTemp.put("password", pPassword);
                    parseJSON = ModelParseJson.HashmapToJsonlist(loginTemp);

                    jsonResult = ModelSendApi.send("https://bayclouds.com/chklogin", parseJSON);

                    if (jsonResult != null) {
                        JSONObject jsonObject = new JSONObject(jsonResult);
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
                            Toast.makeText(LoginActivity.this, "ชื่อผู้ใช้หรือรหัสผ่าน ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "ข้อผิดพลาดในการเชื่อมต่อ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(LoginActivity.this, "การเชื่อมต่อขัดข้อง", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {

            if (this.dialogLogin.isShowing()) {
                this.dialogLogin.dismiss();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            new AsynTaskLogin().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public boolean isConnectedToServer(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) myUrl.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
