package com.example.jujiiz.mis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;

import java.util.ArrayList;

public class TR14DwellerFormActivity extends AppCompatActivity {
    String[] spPrefixArray = {"นาย", "นาง", "นางสาว", "เด็กชาย", "เด็กหญิง", "อื่นๆ (ระบุ)"};
    Spinner spPrefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14_dweller_form);

        init();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        spPrefix.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spPrefixArray, "เลือก"));
    }

    private void init() {
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
    }
}
