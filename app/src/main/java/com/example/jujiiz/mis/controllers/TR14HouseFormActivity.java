package com.example.jujiiz.mis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jujiiz.mis.R;

import java.util.ArrayList;

public class TR14HouseFormActivity extends AppCompatActivity {
    ArrayList<String> spPrefixList = new ArrayList<String>();
    Spinner spPrefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14_house_form);
        init();
    }

    private void init() {
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
    }

    private void spPrefixSetAdapter() {
        spPrefixList.add("นาย");
        spPrefixList.add("นาง");
        spPrefixList.add("นางสาว");
        spPrefixList.add("เด็กหญิง");
        spPrefixList.add("เด็็กชาย");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spPrefixList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPrefix.setAdapter(arrayAdapter);
        spPrefix.setSelection(0);
    }
}
