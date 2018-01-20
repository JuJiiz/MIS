package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listHousehold, listPopulation;
    Button btnUploadData;

    Intent intent;
    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> TestList,
            IMGList,
            DisasterList, EnProbList, ProbList,
            HouseList,
            HouseActive,
            PopulationList,
            PopulationActive,
            DwellerList,
            EachHouse;

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
        listHousehold.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        listPopulation = (ListView) findViewById(R.id.listPopulation);
        listPopulation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        btnUploadData = (Button) findViewById(R.id.btnUploadData);
        btnUploadData.setOnClickListener(this);
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

                    //EachHouse = new ArrayList<HashMap<String, String>>();
                    //EachHouse.add(HouseList.get(i));
                    //ModelParseJson.ArraylistToJsonlist(EachHouse); // check on log

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

    private void uploadHouse() {
        Boolean houseHolder = false;
        HouseActive = new ArrayList<HashMap<String, String>>();
        HouseList = db.SelectData("house");
        if (!HouseList.isEmpty()) {
            for (int i = 0; i < HouseList.size(); i++) {
                if (HouseList.get(i).get("survey_status").equals("1")) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put("house_id", HouseList.get(i).get("house_id"));
                    temp.put("house_no", HouseList.get(i).get("house_no"));
                    temp.put("vilage_id", HouseList.get(i).get("vilage_id"));
                    DwellerList = db.SelectWhereData("population", "house_id", HouseList.get(i).get("house_id"));
                    if (!DwellerList.isEmpty()) {
                        for (int d = 0; d < DwellerList.size(); d++) {
                            if (DwellerList.get(d).get("dwellerstatus").equals("1")) {
                                houseHolder = true;
                            }
                        }
                    }
                    if (houseHolder == true) {
                        temp.put("house_holder", "1");
                    } else {
                        temp.put("house_holder", "0");
                    }
                    temp.put("house_location_lat", HouseList.get(i).get("house_location_lat"));
                    temp.put("house_location_lng", HouseList.get(i).get("house_location_lng"));
                    temp.put("house_in_registry", HouseList.get(i).get("house_in_registry"));
                    temp.put("house_status", HouseList.get(i).get("house_status"));
                    temp.put("house_family_type", HouseList.get(i).get("house_family_type"));
                    IMGList = db.SelectWhereData("house_img", "house_id", HouseList.get(i).get("house_id"));
                    if (IMGList != null) {
                        temp.put("distributor_img", IMGList.get(0).get("distributor_img"));
                    } else {
                        temp.put("distributor_img", "");
                    }
                    temp.put("distributor", HouseList.get(i).get("distributor"));
                    temp.put("survey_status", HouseList.get(i).get("survey_status"));

                    temp.put("userid", "1579");
                    temp.put("username", "JuJiiz");

                    DisasterList = db.SelectWhereData("house_disaster", "house_id", HouseList.get(i).get("house_id"));
                    temp.put("disaster_type", DisasterList.get(0).get("disaster_type"));
                    temp.put("dis1", DisasterList.get(0).get("dis1"));
                    temp.put("dis2", DisasterList.get(0).get("dis2"));
                    temp.put("dis3", DisasterList.get(0).get("dis3"));
                    temp.put("dis4", DisasterList.get(0).get("dis4"));
                    temp.put("dis5", DisasterList.get(0).get("dis5"));
                    temp.put("dis6", DisasterList.get(0).get("dis6"));
                    temp.put("dis7", DisasterList.get(0).get("dis7"));
                    temp.put("dis8", DisasterList.get(0).get("dis8"));
                    temp.put("dis9", DisasterList.get(0).get("dis9"));
                    temp.put("dis10", DisasterList.get(0).get("dis10"));
                    temp.put("dis11", DisasterList.get(0).get("dis11"));
                    temp.put("dis12", DisasterList.get(0).get("dis12"));
                    temp.put("dis13", DisasterList.get(0).get("dis13"));
                    temp.put("dis14", DisasterList.get(0).get("dis14"));

                    EnProbList = db.SelectWhereData("house_envyprob", "house_id", HouseList.get(i).get("house_id"));
                    temp.put("envyprob_type", EnProbList.get(0).get("envyprob_type"));
                    temp.put("ep1", EnProbList.get(0).get("ep1"));
                    temp.put("ep2", EnProbList.get(0).get("ep2"));
                    temp.put("ep3", EnProbList.get(0).get("ep3"));
                    temp.put("ep4", EnProbList.get(0).get("ep4"));
                    temp.put("ep5", EnProbList.get(0).get("ep5"));
                    temp.put("ep6", EnProbList.get(0).get("ep6"));
                    temp.put("ep7", EnProbList.get(0).get("ep7"));

                    ProbList = db.SelectWhereData("house_problem", "house_id", HouseList.get(i).get("house_id"));
                    temp.put("prob1", ProbList.get(0).get("prob1"));
                    temp.put("prob2", ProbList.get(0).get("prob2"));
                    temp.put("prob3", ProbList.get(0).get("prob3"));
                    temp.put("prob4", ProbList.get(0).get("prob4"));
                    temp.put("prob5", ProbList.get(0).get("prob5"));
                    temp.put("prob6", ProbList.get(0).get("prob6"));
                    temp.put("prob7", ProbList.get(0).get("prob7"));
                    temp.put("prob8", ProbList.get(0).get("prob8"));
                    temp.put("prob9", ProbList.get(0).get("prob9"));
                    temp.put("prob10", ProbList.get(0).get("prob10"));
                    temp.put("problem_another", ProbList.get(0).get("problem_another"));
                    HouseActive.add(temp);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnUploadData) {
            uploadHouse();
            ModelParseJson.ArraylistToJsonlist(HouseActive);
        }
    }
}
