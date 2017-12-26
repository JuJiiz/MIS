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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelGetData;
import com.example.jujiiz.mis.models.ModelGetJson;
import com.example.jujiiz.mis.models.ModelNavClick;
import com.example.jujiiz.mis.models.ModelToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class HouseholdActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemClickListener {

    boolean searchVisibility = false;
    LinearLayout pageLayout;
    ListView lvHousehold;
    Button btnSearch, btnPrevious, btnNext;
    Spinner spVName;
    EditText etSearch;

    ArrayList<String> LIST;

    int PAGE_NUMBER = 1;
    String apiURL = "https://api.cinfo.co.th/v2/getTaskList_F01_01?";
    String whatUWant = "Houses";
    JSONArray JARRAY_JSONDATA;
    String spSearch = "", strSearch = "";
    Boolean SearchStatus = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        ModelToken.checkToken(this);

        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getJson();
        if (JARRAY_JSONDATA.length() > 10)
            pageLayout.setVisibility(View.VISIBLE);
    }

    private void init() {
        pageLayout = (LinearLayout) findViewById(R.id.pageLayout);
        lvHousehold = (ListView) findViewById(R.id.lvHousehold);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);
        spVName = (Spinner) findViewById(R.id.spVName);
        etSearch = (EditText) findViewById(R.id.etSearch);

        lvHousehold.setOnItemClickListener(this);

        btnSearch.setOnClickListener(this);
        /*btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);*/
    }

    private void getJson() {
        JARRAY_JSONDATA = ModelGetData.getJsonArray(this, apiURL, whatUWant);
        Log.d("MYLOG", "JARRAY_JSONDATA: " + JARRAY_JSONDATA.length());

        //PAGE_NUMBER = ModelGetJson.getHouseholdHeadJson(this, STRING_JSONDATA, 1, lvHousehold);
        LIST = ModelGetJson.getHouseholdListJson(this, JARRAY_JSONDATA, spSearch, strSearch, lvHousehold); //set json to listview

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, LIST);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVName.setAdapter(arrayAdapter);
        spVName.setSelection(0); //set spinner on search bar

        //SearchStatus = false;
    }

    private void changePage(int pPage) {
        /*if(SearchStatus = false){
            PAGE_NUMBER = ModelGetJson.getHouseholdHeadJson(this, STRING_JSONDATA, pPage, lvHousehold);
        }else {
            PAGE_NUMBER = ModelGetJson.getSearchHouseholdHead(this, STRING_JSONDATA, strSearch, pPage, lvHousehold);
        }*/
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
        if (view == btnPrevious) {
            //changePage(PAGE_NUMBER - 1);
        }
        if (view == btnNext) {
            //changePage(PAGE_NUMBER + 1);
        }
        if (view == btnSearch) {
            spSearch = spVName.getSelectedItem().toString();
            strSearch = etSearch.getText().toString();

            Log.d("MYLOG", "spSearch: "+ spSearch + " strSearch: "+ strSearch);
            ModelGetJson.getHouseholdListJson(this, JARRAY_JSONDATA, spSearch, strSearch, lvHousehold);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> Item = (HashMap<String, String>) lvHousehold.getItemAtPosition(i);
        String SelectedTaskItem = Item.get("task").toString();
        String SelectedStatusItem = Item.get("status").toString();
        Toast.makeText(getApplicationContext(), SelectedTaskItem, Toast.LENGTH_SHORT).show();
        /*if (SelectedStatusItem.equals("success")) {
            intent = new Intent(getApplicationContext(), TaskgroupF0101Activity.class);
            intent.putExtra("TaskID", SelectedTaskItem);
            startActivity(intent);
        }else if(SelectedStatusItem.equals("surveying")) {
            Toast.makeText(getApplicationContext(), "เข้าไปแบบฟอร์ม F01_10", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "Dialog เด้งขึ้น", Toast.LENGTH_SHORT).show();
        }*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action) {
            LinearLayout searchLayout = (LinearLayout) findViewById(R.id.searchLayout);
            if (searchVisibility == false) {
                searchLayout.setVisibility(View.VISIBLE);
                Log.d("xxxxxxxxxxxxxx", "1");
                searchVisibility = true;

            } else if (searchVisibility == true) {
                searchLayout.setVisibility(View.GONE);
                Log.d("xxxxxxxxxxxxxx", "2");
                searchVisibility = false;

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
