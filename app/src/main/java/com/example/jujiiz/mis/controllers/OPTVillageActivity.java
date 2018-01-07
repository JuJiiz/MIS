package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OPTVillageActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etAddVillageNumber, etAddVillageName;
    Button btnAddVillage;
    ListView listOPTVille;
    String VillageNumber = "", VillageName = "";

    myDBClass db = new myDBClass(this);

    ArrayList<HashMap<String, String>> OPTList, VilleList, VilleActive;
    String OPTid;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_village);

        init();

        setListView();
    }

    private void init() {
        etAddVillageNumber = (EditText) findViewById(R.id.etAddVillageNumber);
        etAddVillageName = (EditText) findViewById(R.id.etAddVillageName);

        btnAddVillage = (Button) findViewById(R.id.btnAddVillage);
        btnAddVillage.setOnClickListener(this);

        listOPTVille = (ListView) findViewById(R.id.listOPTVille);
    }

    private void setListView() {
        String strVilleName = "Name";
        String strVilleNo = "Number";
        String strSurStatus = "Status";
        VilleActive = new ArrayList<HashMap<String, String>>();
        VilleList = db.SelectData("vilage");
        if (!VilleList.isEmpty()) {
            for (int i = 0; i < VilleList.size(); i++) {
                String strAvtive = VilleList.get(i).get("ACTIVE");
                //Log.d("MYLOG", "strAvtive: " +strAvtive);
                if (strAvtive.equals("Y")) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put(strVilleName, VilleList.get(i).get("vilage_name"));
                    temp.put(strVilleNo, VilleList.get(i).get("vilage_no"));
                    temp.put(strSurStatus, VilleList.get(i).get("survey_status"));
                    VilleActive.add(temp);
                }
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, VilleActive, R.layout.view_village_column,
                new String[]{strVilleName, strVilleNo, strSurStatus},
                new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3}
        );
        listOPTVille.setAdapter(simpleAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == btnAddVillage) {
            VillageNumber = etAddVillageNumber.getText().toString();
            VillageName = etAddVillageName.getText().toString();
            OPTList = db.SelectData("opt");
            String date = df.format(Calendar.getInstance().getTime());
            if (!OPTList.isEmpty()) {
                OPTid = OPTList.get(0).get("opt_id");
                if (!VillageNumber.equals("") && !VillageName.equals("")) {
                    ContentValues Val = new ContentValues();
                    Val.put("opt_id", OPTid);
                    Val.put("vilage_name", VillageName);
                    Val.put("vilage_location_lat", "");
                    Val.put("vilage_location_lng", "");
                    Val.put("vilage_aor", "");
                    Val.put("vilage_liveable", "");
                    Val.put("vilage_start", "");
                    Val.put("vilage_history", "");
                    Val.put("vilage_problem", "");
                    Val.put("vilage_sup_firstname", "");
                    Val.put("vilage_sup_lastname", "");
                    Val.put("vilage_sup_startdate", "");
                    Val.put("vilage_address_no", "");
                    Val.put("vilage_no", VillageNumber);
                    Val.put("vilage_alley", "");
                    Val.put("vilage_road", "");
                    Val.put("vilage_province", "");
                    Val.put("vilage_district", "");
                    Val.put("vilage_sub_district", "");
                    Val.put("vilage_postal_code", "");
                    Val.put("vilage_tel", "");
                    Val.put("vilage_img", "");
                    Val.put("vilage_informant_firstname", "");
                    Val.put("vilage_informant_lastname", "");
                    Val.put("vilage_informant_tel", "");
                    Val.put("survey_status", "ยังไม่สำรวจ");
                    Val.put("cr_by", "JuJiiz");
                    Val.put("cr_date", date);
                    Val.put("upd_by", "JuJiiz");
                    Val.put("upd_date", date);
                    Val.put("ACTIVE", "Y");
                    db.InsertData("vilage", Val);
                    Toast.makeText(this, "เพิ่มข้อมูลสำเร็จแล้ว", Toast.LENGTH_SHORT).show();
                    setListView();
                    etAddVillageNumber.setText("");
                    etAddVillageName.setText("");
                } else {
                    Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "ไม่มีข้อมูล อปท.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
