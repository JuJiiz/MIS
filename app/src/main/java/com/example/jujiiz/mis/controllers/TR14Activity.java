package com.example.jujiiz.mis.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelNavClick;
import com.example.jujiiz.mis.models.myDBClass;

import java.util.ArrayList;
import java.util.HashMap;

public class TR14Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    EditText etAddHouseNumber, etAddVillageName;
    Button btnSearchVillage, btnAddVillage;
    ListView listHousehold;

    myDBClass db = new myDBClass(this);
    ArrayList<HashMap<String, String>> HouseList, HouseActive, VilleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
        setListView();
    }

    private void init() {
        etAddHouseNumber = (EditText) findViewById(R.id.etAddHouseNumber);
        etAddVillageName = (EditText) findViewById(R.id.etAddVillageName);

        listHousehold = (ListView) findViewById(R.id.listHousehold);

        btnSearchVillage = (Button) findViewById(R.id.btnSearchVillage);
        btnSearchVillage.setOnClickListener(this);
        btnAddVillage = (Button) findViewById(R.id.btnSearchVillage);
        btnAddVillage.setOnClickListener(this);
    }

    private void setListView() {
        String strVilleName = "Name";
        String strHouseNo = "Number";
        String strHouseID = "ID";
        HouseActive = new ArrayList<HashMap<String, String>>();
        HouseList = db.SelectData("house");
        if (!HouseList.isEmpty()) {
            for (int i = 0; i < HouseList.size(); i++) {
                String strActive = HouseList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    VilleList = db.SelectWhereData("vilage", "vilage_id", HouseList.get(i).get("vilage_id"));
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put(strVilleName, VilleList.get(0).get("vilage_name"));
                    temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                    temp.put(strHouseID, HouseList.get(i).get("house_id"));
                    HouseActive.add(temp);
                }
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_tr14_house_column,
                    new String[]{strVilleName, strHouseNo, strHouseID,},
                    new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3}
            );
            listHousehold.setAdapter(simpleAdapter);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tr14, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

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
        if (view == btnAddVillage){
            Intent intent = new Intent(getApplicationContext(), TR14HouseFormActivity.class);
            this.finish();
            startActivity(intent);
        }
    }
}
