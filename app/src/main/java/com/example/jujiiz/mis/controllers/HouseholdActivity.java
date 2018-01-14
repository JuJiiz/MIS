package com.example.jujiiz.mis.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelGetData;
import com.example.jujiiz.mis.models.ModelGetJson;
import com.example.jujiiz.mis.models.ModelNavClick;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.ModelToken;
import com.example.jujiiz.mis.models.myDBClass;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class HouseholdActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    ListView lvHousehold;
    Button btnSearch;
    Spinner spVName;
    EditText etSearch;

    myDBClass db = new myDBClass(this);
    ArrayList<HashMap<String, String>> HouseActive, HouseList, VilleList, TestList;
    ArrayList<String> Village = new ArrayList<String>();
    ArrayAdapter<String> villeArrayAdapter;
    HashMap<String, String> temp;

    String strVilleNo = "VNumber";
    String strHouseNo = "HNumber";
    String strVilleName = "VilleName";
    String strStatus = "Status";
    String strHID = "ID";
    String survey = " survey";
    String SelectedIDItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setSpinner();
        setListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }

    private void init() {
        lvHousehold = (ListView) findViewById(R.id.lvHousehold);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        spVName = (Spinner) findViewById(R.id.spVName);
        etSearch = (EditText) findViewById(R.id.etSearch);

        lvHousehold.setOnItemClickListener(this);

        btnSearch.setOnClickListener(this);
        /*btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);*/
    }

    private void setSpinner() {

        VilleList = db.SelectData("vilage");
        if (!VilleList.isEmpty()) {
            Village.add("ทั้งหมด");
            for (int i = 0; i < VilleList.size(); i++) {
                String strActive = VilleList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    String strVillage = VilleList.get(i).get("vilage_name");
                    if (!Village.contains(strVillage)) {
                        Village.add(strVillage);
                    }

                }
            }
            String[] spVillageArray = Village.toArray(new String[0]);
            villeArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spVillageArray, spVName);
        }
    }

    private void setListView() {
        HouseActive = new ArrayList<HashMap<String, String>>();
        HouseList = db.SelectData("house");
        if (!HouseList.isEmpty()) {
            for (int i = 0; i < HouseList.size(); i++) {
                String strActive = HouseList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    VilleList = db.SelectWhereData("vilage", "vilage_id", HouseList.get(i).get("vilage_id"));
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put(strVilleNo, HouseList.get(i).get("vilage_id"));
                    temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                    temp.put(strVilleName, VilleList.get(0).get("vilage_name"));
                    if (HouseList.get(i).get("survey_status").equals("0")) {
                        survey = "รอการสำรวจ";
                    }
                    if (HouseList.get(i).get("survey_status").equals("1")) {
                        survey = "กำลังสำรวจ";
                    }
                    if (HouseList.get(i).get("survey_status").equals("2")) {
                        survey = "สำรวจแล้ว";
                    }
                    temp.put(strStatus, survey);
                    temp.put(strHID, HouseList.get(i).get("house_id"));
                    HouseActive.add(temp);
                }
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_household_column,
                    new String[]{strVilleNo, strHouseNo, strVilleName, strStatus, strHID},
                    new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
            );
            lvHousehold.setAdapter(simpleAdapter);
        }
    }

    private void searchEvent() {
        String strSpinnerItem = spVName.getSelectedItem().toString();
        String strEditText = etSearch.getText().toString();
        HouseList = db.SelectData("house");
        if (!strSpinnerItem.equals("ทั้งหมด") && !strEditText.equals("")) {
            TestList = db.SelectWhereData("vilage", "vilage_name", "\"" + strSpinnerItem + "\"");
            HouseActive = new ArrayList<HashMap<String, String>>();
            if (!HouseList.isEmpty()) {
                for (int i = 0; i < HouseList.size(); i++) {
                    String strActive = HouseList.get(i).get("ACTIVE");
                    if (strActive.equals("Y")) {
                        if (HouseList.get(i).get("vilage_id").equals(TestList.get(0).get("vilage_id")) && HouseList.get(i).get("house_no").equals(strEditText)) {
                            VilleList = db.SelectWhereData("vilage", "vilage_id", HouseList.get(i).get("vilage_id"));
                            temp = new HashMap<String, String>();
                            temp.put(strVilleNo, HouseList.get(i).get("vilage_id"));
                            temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                            temp.put(strVilleName, VilleList.get(0).get("vilage_name"));
                            if (HouseList.get(i).get("survey_status").equals("0")) {
                                survey = "รอการสำรวจ";
                            }
                            if (HouseList.get(i).get("survey_status").equals("1")) {
                                survey = "กำลังสำรวจ";
                            }
                            if (HouseList.get(i).get("survey_status").equals("2")) {
                                survey = "สำรวจแล้ว";
                            }
                            temp.put(strStatus, survey);
                            temp.put(strHID, HouseList.get(i).get("house_id"));
                            HouseActive.add(temp);
                        }
                    }
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_household_column,
                        new String[]{strVilleNo, strHouseNo, strVilleName, strStatus, strHID},
                        new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
                );
                lvHousehold.setAdapter(simpleAdapter);
            }
        } else if (strSpinnerItem.equals("ทั้งหมด") && !strEditText.equals("")) {
            HouseActive = new ArrayList<HashMap<String, String>>();
            if (!HouseList.isEmpty()) {
                for (int i = 0; i < HouseList.size(); i++) {
                    String strActive = HouseList.get(i).get("ACTIVE");
                    if (strActive.equals("Y")) {
                        if (HouseList.get(i).get("house_no").equals(strEditText)) {
                            VilleList = db.SelectWhereData("vilage", "vilage_id", HouseList.get(i).get("vilage_id"));
                            temp = new HashMap<String, String>();
                            temp.put(strVilleNo, HouseList.get(i).get("vilage_id"));
                            temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                            temp.put(strVilleName, VilleList.get(0).get("vilage_name"));
                            if (HouseList.get(i).get("survey_status").equals("0")) {
                                survey = "รอการสำรวจ";
                            }
                            if (HouseList.get(i).get("survey_status").equals("1")) {
                                survey = "กำลังสำรวจ";
                            }
                            if (HouseList.get(i).get("survey_status").equals("2")) {
                                survey = "สำรวจแล้ว";
                            }
                            temp.put(strStatus, survey);
                            temp.put(strHID, HouseList.get(i).get("house_id"));
                            HouseActive.add(temp);
                        }
                    }
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_household_column,
                        new String[]{strVilleNo, strHouseNo, strVilleName, strStatus, strHID},
                        new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
                );
                lvHousehold.setAdapter(simpleAdapter);
            }
        } else if (!strSpinnerItem.equals("ทั้งหมด") && strEditText.equals("")) {
            TestList = db.SelectWhereData("vilage", "vilage_name", "\"" + strSpinnerItem + "\"");
            HouseActive = new ArrayList<HashMap<String, String>>();
            if (!HouseList.isEmpty()) {
                for (int i = 0; i < HouseList.size(); i++) {
                    String strActive = HouseList.get(i).get("ACTIVE");
                    if (strActive.equals("Y")) {
                        if (HouseList.get(i).get("vilage_id").equals(TestList.get(0).get("vilage_id"))) {
                            VilleList = db.SelectWhereData("vilage", "vilage_id", HouseList.get(i).get("vilage_id"));
                            temp = new HashMap<String, String>();
                            temp.put(strVilleNo, HouseList.get(i).get("vilage_id"));
                            temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                            temp.put(strVilleName, VilleList.get(0).get("vilage_name"));
                            if (HouseList.get(i).get("survey_status").equals("0")) {
                                survey = "รอการสำรวจ";
                            }
                            if (HouseList.get(i).get("survey_status").equals("1")) {
                                survey = "กำลังสำรวจ";
                            }
                            if (HouseList.get(i).get("survey_status").equals("2")) {
                                survey = "สำรวจแล้ว";
                            }
                            temp.put(strStatus, survey);
                            temp.put(strHID, HouseList.get(i).get("house_id"));
                            HouseActive.add(temp);
                        }
                    }
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_household_column,
                        new String[]{strVilleNo, strHouseNo, strVilleName, strStatus, strHID},
                        new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
                );
                lvHousehold.setAdapter(simpleAdapter);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        ModelNavClick.displaySelectedScreen(this, id);

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == btnSearch) {
            if (spVName.getSelectedItem().toString().equals("ทั้งหมด") && etSearch.getText().toString().equals("")) {
                setListView();
            } else {
                lvHousehold.setAdapter(null);
                searchEvent();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> Item = (HashMap<String, String>) lvHousehold.getItemAtPosition(i);
        SelectedIDItem = Item.get("ID").toString();
        Intent intent = new Intent(getApplicationContext(), HouseholdFormActivity.class);
        intent.putExtra("PersonID", SelectedIDItem);
        startActivity(intent);
    }
}
