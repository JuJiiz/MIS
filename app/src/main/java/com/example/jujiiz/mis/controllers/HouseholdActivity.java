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

    ListView lvHousehold;
    Button btnSearch,btnDumpPopulation;
    Spinner spVName;
    EditText etSearch;

    ArrayList<String> LIST;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        //ModelToken.checkToken(this);

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


    }

    private void init() {
        lvHousehold = (ListView) findViewById(R.id.lvHousehold);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnDumpPopulation = (Button) findViewById(R.id.btnDumpPopulation);
        spVName = (Spinner) findViewById(R.id.spVName);
        etSearch = (EditText) findViewById(R.id.etSearch);

        lvHousehold.setOnItemClickListener(this);

        btnSearch.setOnClickListener(this);
        btnDumpPopulation.setOnClickListener(this);
        /*btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);*/
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

        }
        if (view == btnDumpPopulation) {
            Intent intent = new Intent(getApplicationContext(), PeopleFormActivity.class);
            getApplicationContext().startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
