package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelParseJson;
import com.example.jujiiz.mis.models.ModelSendApi;
import com.example.jujiiz.mis.models.myDBClass;

import org.json.JSONException;
import org.json.JSONObject;

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
            HouseList, HouseActive,
            CongList, ContList, DisList, TransList, WorkList, AgriList, AniList, GovernList, PrivateList,
            PopulationList, PopulationActive,
            LandList, LandActive,
            VehicleList, VehicleActive,
            PetList, PetActive,
            AnimalList, AnimalActive,
            DwellerList,
            AnimalName;
    String parseJson = "", jsonResult = "";

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
                    temp.put("vilage_id", "1");
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

    private void uploadPopulation() {
        PopulationActive = new ArrayList<HashMap<String, String>>();
        PopulationList = db.SelectData("population");
        if (!PopulationList.isEmpty()) {
            for (int i = 0; i < PopulationList.size(); i++) {
                if (PopulationList.get(i).get("survey_status").equals("1")) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put("population_idcard", PopulationList.get(i).get("population_idcard"));
                    temp.put("prename_id", PopulationList.get(i).get("prename"));
                    temp.put("firstname", PopulationList.get(i).get("firstname"));
                    temp.put("lastname", PopulationList.get(i).get("lastname"));
                    temp.put("birthdate", PopulationList.get(i).get("birthdate"));
                    temp.put("height", PopulationList.get(i).get("height"));
                    temp.put("weight", PopulationList.get(i).get("weight"));
                    temp.put("sex", PopulationList.get(i).get("sex"));
                    temp.put("bloodgroup", PopulationList.get(i).get("bloodgroup"));
                    temp.put("living", PopulationList.get(i).get("living"));
                    temp.put("maritalstatus", PopulationList.get(i).get("maritalstatus"));
                    temp.put("tel", PopulationList.get(i).get("tel"));
                    temp.put("nationality_id", PopulationList.get(i).get("nationality"));
                    temp.put("house_id", PopulationList.get(i).get("house_id"));
                    temp.put("currentaddr", PopulationList.get(i).get("currentaddr"));
                    temp.put("currentaddr_province", PopulationList.get(i).get("currentaddr_province"));
                    temp.put("currentaddr_country", PopulationList.get(i).get("currentaddr_country"));
                    temp.put("dwellerstatus", PopulationList.get(i).get("dwellerstatus"));
                    temp.put("income", PopulationList.get(i).get("income"));
                    temp.put("income_money", PopulationList.get(i).get("income_money"));
                    temp.put("dept", PopulationList.get(i).get("dept"));
                    temp.put("saving", PopulationList.get(i).get("saving"));
                    temp.put("allergichis", PopulationList.get(i).get("allergichis"));
                    temp.put("allergichis_detail", PopulationList.get(i).get("allergichis_detail"));
                    temp.put("disadvantage", PopulationList.get(i).get("disadvantage"));
                    temp.put("sub_al", PopulationList.get(i).get("sub_al"));
                    temp.put("education", PopulationList.get(i).get("education"));
                    temp.put("education_class", PopulationList.get(i).get("education_class"));
                    temp.put("literacy", PopulationList.get(i).get("literacy"));
                    temp.put("technology", PopulationList.get(i).get("technology"));
                    temp.put("expertise", PopulationList.get(i).get("expertise"));
                    temp.put("expertise_name", PopulationList.get(i).get("expertise_name"));
                    temp.put("expertise_detail", PopulationList.get(i).get("expertise_detail"));
                    temp.put("religion", PopulationList.get(i).get("religion"));
                    temp.put("religion_another", PopulationList.get(i).get("religion_another"));
                    temp.put("participation", PopulationList.get(i).get("participation"));
                    temp.put("election", PopulationList.get(i).get("election"));
                    temp.put("residence_status", PopulationList.get(i).get("residence_status"));
                    temp.put("latentpop_province", PopulationList.get(i).get("latentpop_province"));
                    temp.put("latentpop_country", PopulationList.get(i).get("latentpop_country"));
                    temp.put("distributor", PopulationList.get(i).get("distributor"));
                    temp.put("survey_status", PopulationList.get(i).get("survey_status"));

                    temp.put("userid", "1579");
                    temp.put("username", "JuJiiz");

                    CongList = db.SelectWhereData("population_congenitalhis", "population_idcard", PopulationList.get(i).get("population_idcard"));
                    temp.put("congh_type", CongList.get(0).get("congh_type"));
                    temp.put("congh1", CongList.get(0).get("congh1"));
                    temp.put("congh2", CongList.get(0).get("congh2"));
                    temp.put("congh3", CongList.get(0).get("congh3"));
                    temp.put("congh4", CongList.get(0).get("congh4"));
                    temp.put("congh5", CongList.get(0).get("congh5"));
                    temp.put("congh_another", CongList.get(0).get("congh_another"));

                    ContList = db.SelectWhereData("population_contagioushis", "population_idcard", PopulationList.get(i).get("population_idcard"));
                    temp.put("conth_type", ContList.get(0).get("conth_type"));
                    temp.put("conth1", ContList.get(0).get("conth1"));
                    temp.put("conth2", ContList.get(0).get("conth2"));
                    temp.put("conth3", ContList.get(0).get("conth3"));
                    temp.put("conth4", ContList.get(0).get("conth4"));
                    temp.put("conth5", ContList.get(0).get("conth5"));
                    temp.put("conth6", ContList.get(0).get("conth6"));
                    temp.put("conth7", ContList.get(0).get("conth7"));
                    temp.put("conth8", ContList.get(0).get("conth8"));
                    temp.put("conth9", ContList.get(0).get("conth9"));
                    temp.put("conth10", ContList.get(0).get("conth10"));
                    temp.put("conth11", ContList.get(0).get("conth11"));
                    temp.put("conth_another", ContList.get(0).get("conth_another"));

                    DisList = db.SelectWhereData("population_disabled", "population_idcard", PopulationList.get(i).get("population_idcard"));
                    temp.put("disabled_type", DisList.get(0).get("disabled_type"));
                    temp.put("disabled1", DisList.get(0).get("disabled1"));
                    temp.put("disabled2", DisList.get(0).get("disabled2"));
                    temp.put("disabled3", DisList.get(0).get("disabled3"));
                    temp.put("disabled4", DisList.get(0).get("disabled4"));
                    temp.put("disabled5", DisList.get(0).get("disabled5"));
                    temp.put("disabled6", DisList.get(0).get("disabled6"));

                    TransList = db.SelectWhereData("population_transport", "population_idcard", PopulationList.get(i).get("population_idcard"));
                    temp.put("transport_type", TransList.get(0).get("transport_type"));
                    temp.put("trans1", TransList.get(0).get("trans1"));
                    temp.put("trans2", TransList.get(0).get("trans2"));
                    temp.put("trans3", TransList.get(0).get("trans3"));
                    temp.put("trans4", TransList.get(0).get("trans4"));

                    WorkList = db.SelectWhereData("population_works", "population_idcard", PopulationList.get(i).get("population_idcard"));
                    temp.put("works_type", WorkList.get(0).get("works_type"));

                    AgriList = db.SelectWhereData("population_job_agriculture", "works_id", WorkList.get(0).get("works_id"));
                    if (AgriList.get(0).get("agri1").equals("1") ||
                            AgriList.get(0).get("agri2").equals("1") ||
                            AgriList.get(0).get("agri3").equals("1") ||
                            AgriList.get(0).get("agri4").equals("1") ||
                            AgriList.get(0).get("agri5").equals("1") ||
                            AgriList.get(0).get("agri6").equals("1") ||
                            AgriList.get(0).get("agri7").equals("1") ||
                            AgriList.get(0).get("agri8").equals("1")) {
                        temp.put("agricheck", "1");
                    } else {
                        temp.put("agricheck", "0");
                    }
                    temp.put("agri1", AgriList.get(0).get("agri1"));
                    temp.put("agri2", AgriList.get(0).get("agri2"));
                    temp.put("agri3", AgriList.get(0).get("agri3"));
                    temp.put("agri4", AgriList.get(0).get("agri4"));
                    temp.put("agri5", AgriList.get(0).get("agri5"));
                    temp.put("agri6", AgriList.get(0).get("agri6"));
                    temp.put("agri_another", AgriList.get(0).get("agri_another"));

                    AniList = db.SelectWhereData("population_job_animal", "works_id", WorkList.get(0).get("works_id"));
                    if (AniList.get(0).get("animal1").equals("1") ||
                            AniList.get(0).get("animal2").equals("1") ||
                            AniList.get(0).get("animal3").equals("1") ||
                            AniList.get(0).get("animal4").equals("1") ||
                            AniList.get(0).get("animal5").equals("1") ||
                            AniList.get(0).get("animal6").equals("1") ||
                            AniList.get(0).get("animal7").equals("1") ||
                            AniList.get(0).get("animal8").equals("1") ||
                            AniList.get(0).get("animal9").equals("1")) {
                        temp.put("animalcheck", "1");
                    } else {
                        temp.put("animalcheck", "0");
                    }
                    temp.put("animal1", AniList.get(0).get("animal1"));
                    temp.put("animal2", AniList.get(0).get("animal2"));
                    temp.put("animal3", AniList.get(0).get("animal3"));
                    temp.put("animal4", AniList.get(0).get("animal4"));
                    temp.put("animal5", AniList.get(0).get("animal5"));
                    temp.put("animal6", AniList.get(0).get("animal6"));
                    temp.put("animal7", AniList.get(0).get("animal7"));
                    temp.put("animal8", AniList.get(0).get("animal8"));
                    temp.put("animal9", AniList.get(0).get("animal9"));
                    temp.put("animal_another", AniList.get(0).get("animal_another"));

                    GovernList = db.SelectWhereData("population_job_govern", "works_id", WorkList.get(0).get("works_id"));
                    if (GovernList.get(0).get("govern1").equals("1") ||
                            GovernList.get(0).get("govern2").equals("1") ||
                            GovernList.get(0).get("govern3").equals("1") ||
                            GovernList.get(0).get("govern4").equals("1")) {
                        temp.put("governcheck", "1");
                    } else {
                        temp.put("governcheck", "0");
                    }
                    temp.put("govern1", GovernList.get(0).get("govern1"));
                    temp.put("govern2", GovernList.get(0).get("govern2"));
                    temp.put("govern3", GovernList.get(0).get("govern3"));
                    temp.put("govern4", GovernList.get(0).get("govern4"));
                    temp.put("govern_another", GovernList.get(0).get("govern_another"));

                    PrivateList = db.SelectWhereData("population_job_private", "works_id", WorkList.get(0).get("works_id"));
                    if (PrivateList.get(0).get("private1").equals("1") ||
                            PrivateList.get(0).get("private2").equals("1") ||
                            PrivateList.get(0).get("private3").equals("1") ||
                            PrivateList.get(0).get("private4").equals("1") ||
                            PrivateList.get(0).get("private5").equals("1") ||
                            PrivateList.get(0).get("private6").equals("1") ||
                            PrivateList.get(0).get("private7").equals("1")) {
                        temp.put("privatecheck", "1");
                    } else {
                        temp.put("privatecheck", "0");
                    }
                    temp.put("private1", PrivateList.get(0).get("private1"));
                    temp.put("private2", PrivateList.get(0).get("private2"));
                    temp.put("private3", PrivateList.get(0).get("private3"));
                    temp.put("private4", PrivateList.get(0).get("private4"));
                    temp.put("private5", PrivateList.get(0).get("private5"));
                    temp.put("private6", PrivateList.get(0).get("private6"));
                    temp.put("private7", PrivateList.get(0).get("private7"));
                    temp.put("private_another", PrivateList.get(0).get("private_another"));
                    PopulationActive.add(temp);
                }
            }
        }
    }

    private void uploadLand() {
        LandActive = new ArrayList<HashMap<String, String>>();
        //LandList = db.SelectData("population_asset_land");
        //if (!LandList.isEmpty()) {
        for (int i = 0; i < LandList.size(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("userid", "1579");
            temp.put("username", "JuJiiz");

            temp.put("population_idcard", LandList.get(i).get("population_idcard"));
            temp.put("system_id", LandList.get(i).get("system_id"));
            temp.put("dimen1", LandList.get(i).get("dimen1"));
            temp.put("dimen2", LandList.get(i).get("dimen2"));
            temp.put("dimen3", LandList.get(i).get("dimen3"));
            temp.put("land_benefit", LandList.get(i).get("land_benefit"));
            temp.put("land_location", LandList.get(i).get("land_location"));
            temp.put("land_tax", LandList.get(i).get("land_tax"));
            temp.put("land_rent", LandList.get(i).get("land_rent"));
            temp.put("distributor", LandList.get(i).get("distributor"));
            LandActive.add(temp);
        }
        //}
    }

    private void uploadVehicle() {
        VehicleActive = new ArrayList<HashMap<String, String>>();
        //VehicleList = db.SelectData("population_asset_vehicle");
        //if (!VehicleList.isEmpty()) {
        for (int i = 0; i < VehicleList.size(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("userid", "1579");
            temp.put("username", "JuJiiz");

            temp.put("population_idcard", VehicleList.get(i).get("population_idcard"));
            temp.put("regisdate", VehicleList.get(i).get("regisdate"));
            temp.put("vtype_id", VehicleList.get(i).get("vtype_id"));
            temp.put("vehicle_rent", VehicleList.get(i).get("vehical_rent"));
            temp.put("distributor", VehicleList.get(i).get("distributor"));
            VehicleActive.add(temp);
        }
        //}
    }

    private void uploadPet() {
        PetActive = new ArrayList<HashMap<String, String>>();
        //PetList = db.SelectData("population_asset_pet");
        //if (!PetList.isEmpty()) {
        for (int i = 0; i < PetList.size(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("userid", "1579");
            temp.put("username", "JuJiiz");

            temp.put("population_idcard", PetList.get(i).get("population_idcard"));
            temp.put("pet_regis", PetList.get(i).get("pet_regis"));
            temp.put("pet_amount", PetList.get(i).get("pet_amount"));
            temp.put("ptype_id", PetList.get(i).get("pet_type"));
            temp.put("pet_sex", PetList.get(i).get("pet_sex"));
            temp.put("vaccine", PetList.get(i).get("vaccine"));
            temp.put("vaccine_during", PetList.get(i).get("vaccine_during"));
            temp.put("vaccine_lastest", PetList.get(i).get("vaccine_lastest"));
            temp.put("sterile", PetList.get(i).get("sterile"));
            temp.put("pet_newborn", PetList.get(i).get("pet_newborn"));
            temp.put("pet_newborn_number", PetList.get(i).get("pet_newborn_number"));
            temp.put("distributor", PetList.get(i).get("distributor"));
            PetActive.add(temp);
        }
        //}
    }

    private void uploadAnimal() {
        AnimalActive = new ArrayList<HashMap<String, String>>();
        //AnimalList = db.SelectData("population_asset_animal");
        //if (!AnimalList.isEmpty()) {
        for (int i = 0; i < AnimalList.size(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("userid", "1579");
            temp.put("username", "JuJiiz");

            temp.put("population_idcard", AnimalList.get(i).get("population_idcard"));
            temp.put("animal_regis", AnimalList.get(i).get("animal_regis"));
            temp.put("animal_amount", AnimalList.get(i).get("animal_amount"));
            AnimalName = db.SelectWhereData("asset_animal", "atype_id", AnimalList.get(0).get("atype_id"));
            Log.d("MYLOG", "AnimalName: " + AnimalName.get(0).get("atype_name"));
            temp.put("atype_name", AnimalName.get(0).get("atype_name"));
            temp.put("infection", AnimalList.get(i).get("infection"));
            temp.put("infection_detail", AnimalList.get(i).get("infection_detail"));
            temp.put("shelter", AnimalList.get(i).get("shelter"));
            temp.put("diseasecontrol", AnimalList.get(i).get("diseasecontrol"));
            temp.put("diseasecontrol_by", AnimalList.get(i).get("diseasecontrol_by"));
            temp.put("disease_shelter", AnimalList.get(i).get("disease_shelter"));
            temp.put("market", AnimalList.get(i).get("market"));
            temp.put("market_place", AnimalList.get(i).get("market_place"));
            temp.put("distributor", AnimalList.get(i).get("distributor"));
            AnimalActive.add(temp);
        }
        //}
    }

    @Override
    public void onClick(View v) {
        if (v == btnUploadData) {
            Boolean AllInPopulation = false;

            uploadHouse();
            for (int i = 0; i < HouseActive.size(); i++) {
                parseJson = ModelParseJson.HashmapToJsonlist(HouseActive.get(i));
                jsonResult = ModelSendApi.send("http://203.154.54.229/inserthouse", parseJson);
                Log.d("MYLOG", "jsonResult (House): " + jsonResult);
                try {
                    if (jsonResult != null) {
                        JSONObject jsonObject = new JSONObject(jsonResult);
                        String returnStatus = jsonObject.getString("status");
                        if (returnStatus.equals("ok")) {
                            Val = new ContentValues();
                            Val.put("survey_status", "0");
                            db.UpdateData("house", Val, "house_id", HouseActive.get(i).get("house_id"));
                            Toast.makeText(this, "ส่งข้อมูล \"ครัวเรือน\" สำเร็จ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "ข้อมูลผิดพลาด", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "ข้อผิดพลาดในการเชื่อมต่อ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setHouseListView();
            }

            uploadPopulation();
            for (int i = 0; i < PopulationActive.size(); i++) {
                //for (int h = 0; h < PopulationActive.size(); h++) {
                parseJson = ModelParseJson.HashmapToJsonlist(PopulationActive.get(i));
                jsonResult = ModelSendApi.send("http://203.154.54.229/insertpopulation", parseJson);
                Log.d("MYLOG", "jsonResult (Population): " + jsonResult);
                try {
                    if (jsonResult != null) {
                        JSONObject jsonObject = new JSONObject(jsonResult);
                        String returnStatus = jsonObject.getString("status");
                        if (returnStatus.equals("ok")) {
                            AllInPopulation = true;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //}

                LandList = db.SelectWhereData("population_asset_land", "population_idcard", PopulationActive.get(i).get("population_idcard"));
                if (!LandList.isEmpty()) {
                    AllInPopulation = false;
                    uploadLand();
                    for (int j = 0; j < LandActive.size(); j++) {
                        parseJson = ModelParseJson.HashmapToJsonlist(LandActive.get(j));
                        jsonResult = ModelSendApi.send("http://203.154.54.229/insertassetland", parseJson);
                        Log.d("MYLOG", "jsonResult (Land): " + jsonResult);
                        try {
                            if (jsonResult != null) {
                                JSONObject jsonObject = new JSONObject(jsonResult);
                                String returnStatus = jsonObject.getString("status");
                                if (returnStatus.equals("ok")) {
                                    AllInPopulation = true;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d("MYLOG", "Land No Data");
                }

                VehicleList = db.SelectWhereData("population_asset_vehicle", "population_idcard", PopulationActive.get(i).get("population_idcard"));
                if (!VehicleList.isEmpty()) {
                    AllInPopulation = false;
                    uploadVehicle();
                    for (int j = 0; j < VehicleActive.size(); j++) {
                        parseJson = ModelParseJson.HashmapToJsonlist(VehicleActive.get(j));
                        jsonResult = ModelSendApi.send("http://203.154.54.229/insertassetvehicle", parseJson);
                        Log.d("MYLOG", "jsonResult (Vehicle): " + jsonResult);
                        try {
                            if (jsonResult != null) {
                                JSONObject jsonObject = new JSONObject(jsonResult);
                                String returnStatus = jsonObject.getString("status");
                                if (returnStatus.equals("ok")) {
                                    AllInPopulation = true;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d("MYLOG", "Vehicle No Data");
                }

                PetList = db.SelectWhereData("population_asset_pet", "population_idcard", PopulationActive.get(i).get("population_idcard"));
                if (!PetList.isEmpty()) {
                    AllInPopulation = false;
                    uploadPet();
                    for (int j = 0; j < PetActive.size(); j++) {
                        parseJson = ModelParseJson.HashmapToJsonlist(PetActive.get(j));
                        jsonResult = ModelSendApi.send("http://203.154.54.229/insertassetpet", parseJson);
                        Log.d("MYLOG", "jsonResult (Pet): " + jsonResult);
                        try {
                            if (jsonResult != null) {
                                JSONObject jsonObject = new JSONObject(jsonResult);
                                String returnStatus = jsonObject.getString("status");
                                if (returnStatus.equals("ok")) {
                                    AllInPopulation = true;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d("MYLOG", "Pet No Data");
                }

                AnimalList = db.SelectWhereData("population_asset_animal", "population_idcard", PopulationActive.get(i).get("population_idcard"));
                if (!AnimalList.isEmpty()) {
                    AllInPopulation = false;
                    uploadAnimal();
                    for (int j = 0; j < AnimalActive.size(); j++) {
                        parseJson = ModelParseJson.HashmapToJsonlist(AnimalActive.get(j));
                        jsonResult = ModelSendApi.send("http://203.154.54.229/insertassetanimal", parseJson);
                        Log.d("MYLOG", "jsonResult (Animal): " + jsonResult);
                        try {
                            if (jsonResult != null) {
                                JSONObject jsonObject = new JSONObject(jsonResult);
                                String returnStatus = jsonObject.getString("status");
                                if (returnStatus.equals("ok")) {
                                    AllInPopulation = true;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.d("MYLOG", "Animal No Data");
                }

                if (AllInPopulation == true) {
                    Val = new ContentValues();
                    Val.put("survey_status", "0");
                    db.UpdateData("population", Val, "population_idcard", PopulationActive.get(i).get("population_idcard"));
                    Toast.makeText(this, "ส่งข้อมูล \"ประชากร\" สำเร็จ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "อัพโหลดผิดพลาด", Toast.LENGTH_SHORT).show();
                }
                setPopulationListView();
            }
        }
    }
}

