package com.example.jujiiz.mis.controllers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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

public class OPTVillageActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    EditText etAddVillageNumber, etAddVillageName;
    Button btnAddVillage;
    ListView listOPTVille;
    String VillageNumber = "", VillageName = "";

    myDBClass db = new myDBClass(this);
    String SelectedIDItem,SelectedNameItem;

    ArrayList<HashMap<String, String>> OPTList, VilleList, VilleActive;
    String OPTid;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_village);

        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
        setListView();
    }

    private void init() {
        etAddVillageNumber = (EditText) findViewById(R.id.etAddVillageNumber);
        etAddVillageName = (EditText) findViewById(R.id.etAddVillageName);

        btnAddVillage = (Button) findViewById(R.id.btnAddVillage);
        btnAddVillage.setOnClickListener(this);

        listOPTVille = (ListView) findViewById(R.id.listOPTVille);
        listOPTVille.setOnItemClickListener(this);
        listOPTVille.setOnItemLongClickListener(this);
    }

    private void setListView() {
        String strVilleName = "Name";
        String strVilleNo = "Number";
        String strSurStatus = "Status";
        String strVilleID = "ID";
        String strSurvey = "Survey";
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
                    if (VilleList.get(i).get("survey_status").equals("0")){
                        strSurvey = "รอการสำรวจ";
                    }
                    if (VilleList.get(i).get("survey_status").equals("1")){
                        strSurvey = "กำลังสำรวจ";
                    }
                    if (VilleList.get(i).get("survey_status").equals("2")){
                        strSurvey = "สำรวจแล้ว";
                    }
                    temp.put(strSurStatus, strSurvey);
                    temp.put(strVilleID, VilleList.get(i).get("vilage_id"));
                    VilleActive.add(temp);
                }
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, VilleActive, R.layout.view_village_column,
                    new String[]{strVilleName, strVilleNo, strSurStatus, strVilleID},
                    new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvHiddenColumn}
            );
            listOPTVille.setAdapter(simpleAdapter);
            Log.d("MYLOG", "Set ListView");
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnAddVillage) {
            VillageNumber = etAddVillageNumber.getText().toString();
            VillageName = etAddVillageName.getText().toString();
            OPTList = db.SelectData("opt");
            String date = df.format(Calendar.getInstance().getTime());
            if (!OPTList.isEmpty()) {
                OPTid = OPTList.get(0).get("opt_id_ai");
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
                    Val.put("survey_status", "0");
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> Item = (HashMap<String, String>) listOPTVille.getItemAtPosition(i);
        SelectedIDItem = Item.get("ID").toString();
        //String SelectedStatusItem = Item.get("status").toString();
        //Toast.makeText(getApplicationContext(), SelectedIDItem, Toast.LENGTH_SHORT).show();
        intent = new Intent(getApplicationContext(), OPTVillageFormActivity.class);
        intent.putExtra("VillageID", SelectedIDItem);
        this.finish();
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> Item = (HashMap<String, String>) listOPTVille.getItemAtPosition(i);
        SelectedIDItem = Item.get("ID").toString();
        SelectedNameItem = Item.get("Name").toString();
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        ContentValues Val = new ContentValues();
                        Val.put("ACTIVE","N");
                        db.UpdateData("vilage",Val,"vilage_id",SelectedIDItem);
                        Toast.makeText(getApplicationContext(),"ลบข้อมูลเรียบร้อย",Toast.LENGTH_SHORT).show();
                        setListView();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ท่านต้องการลบข้อมูล "+ SelectedNameItem +" ?").setPositiveButton("ใช่", dialogClickListener)
                .setNegativeButton("ไม่ใช่", dialogClickListener).show();
        return false;
    }
}
