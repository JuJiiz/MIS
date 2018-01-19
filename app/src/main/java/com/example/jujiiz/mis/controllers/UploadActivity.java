package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelParseJson;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class UploadActivity extends AppCompatActivity {
    ListView listHousehold, listPopulation;
    Button btnUploadData;

    Intent intent;
    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> TestList, HouseList, HouseActive,PopulationList,PopulationActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        init();
        setHouseListView();
        setPopulationListView();
    }

    private void init() {
        listHousehold = (ListView) findViewById(R.id.listHousehold);
        listPopulation = (ListView) findViewById(R.id.listPopulation);

        btnUploadData = (Button) findViewById(R.id.btnUploadData);
    }

    private void setHouseListView() {
        String strVilleNo = "VNumber";
        String strHouseNo = "HNumber";
        String strVilleName = "VilleName";
        String strHID = "ID";
        HouseActive = new ArrayList<HashMap<String, String>>();
        HouseList = db.SelectData("house");
        if (!HouseList.isEmpty()) {
            for (int i = 0; i < HouseList.size(); i++) {
                if (HouseList.get(i).get("survey_status").equals("1")) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    TestList = db.SelectWhereData("tr14", "house_id", HouseList.get(i).get("house_id"));
                    temp.put(strVilleNo, TestList.get(0).get("vilage_no"));
                    temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                    temp.put(strVilleName, " ");
                    temp.put(strHID, HouseList.get(i).get("house_id"));
                    HouseActive.add(temp);
                }
            }
        }
        ModelParseJson.ArraylistToJsonlist(HouseActive); // check on log
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_upload_column,
                new String[]{strVilleNo, strHouseNo, strVilleName, strHID},
                new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvHiddenColumn}
        );
        listHousehold.setAdapter(simpleAdapter);
    }

    private void setPopulationListView() {
        String strHouseNo = "HNumber";
        String strFName = "FName";
        //String strLName = "LName";
        String strPID = "ID";
        PopulationActive = new ArrayList<HashMap<String, String>>();
        PopulationList = db.SelectData("population");
        if (!PopulationList.isEmpty()) {
            for (int i = 0; i < PopulationList.size(); i++) {
                if (PopulationList.get(i).get("survey_status").equals("1")) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    TestList = db.SelectWhereData("house", "house_id", PopulationList.get(i).get("house_id"));
                    temp.put(strHouseNo, TestList.get(0).get("house_no"));
                    temp.put(strFName, PopulationList.get(i).get("firstname"));
                    //temp.put(strLName, PopulationList.get(i).get("lastname"));
                    temp.put(strPID, PopulationList.get(i).get("population_idcard"));
                    PopulationActive.add(temp);
                }
            }
        }
        ModelParseJson.ArraylistToJsonlist(PopulationActive); // check on log
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, PopulationActive, R.layout.view_upload_column,
                new String[]{strHouseNo, strFName, strPID},
                new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3}
        );
        listPopulation.setAdapter(simpleAdapter);
    }
}
