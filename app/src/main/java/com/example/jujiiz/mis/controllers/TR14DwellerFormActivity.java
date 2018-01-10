package com.example.jujiiz.mis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.util.ArrayList;
import java.util.HashMap;

public class TR14DwellerFormActivity extends AppCompatActivity {
    myDBClass db = new myDBClass(this);
    Spinner spPrefix;

    ArrayList<String> Prefix = new ArrayList<String>();
    ArrayList<HashMap<String, String>> PrefixList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14_dweller_form);

        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
        setSpinner();
    }

    private void setSpinner(){
        PrefixList = db.SelectData("prename");
        for (int i = 0; i < PrefixList.size(); i++) {
            String strPrefix = PrefixList.get(i).get("prename_detail");
            Prefix.add(strPrefix);
        }
        String[] spPrefixArray = Prefix.toArray(new String[0]);
        spPrefix.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spPrefixArray, "เลือก"));
    }

    private void init() {
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
    }
}
