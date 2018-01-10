package com.example.jujiiz.mis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TR14HouseFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    myDBClass db = new myDBClass(this);
    Spinner spPrefix,spVillageName;
    RadioGroup rgHouseOwner;
    RadioButton rbHouseOwnerYes;
    LinearLayout loHouseOwner;

    ArrayList<String> Prefix = new ArrayList<String>();
    ArrayList<String> Village = new ArrayList<String>();
    ArrayList<HashMap<String, String>> PrefixList,VillageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14_house_form);

        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
        setSpinner();
    }

    private void setSpinner(){
        PrefixList = db.SelectData("prename");
        if (!VillageList.isEmpty()) {
            for (int i = 0; i < PrefixList.size(); i++) {
                String strPrefix = PrefixList.get(i).get("prename_detail");
                Prefix.add(strPrefix);
            }
            String[] spPrefixArray = Prefix.toArray(new String[0]);
            spPrefix.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spPrefixArray, "เลือก"));
        }

        VillageList = db.SelectData("vilage");
        if (!VillageList.isEmpty()){
            for (int i = 0; i < VillageList.size(); i++) {
                String strAvtive = VillageList.get(i).get("ACTIVE");
                if (strAvtive.equals("Y")) {
                    String strVillage = VillageList.get(i).get("vilage_name");
                    if(!Village.contains(strVillage)){
                        Village.add(strVillage);
                    }

                }
            }
            String[] spVillageArray = Village.toArray(new String[0]);
            spVillageName.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spVillageArray, "เลือก"));
        }
    }

    private void init() {
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
        spVillageName = (Spinner) findViewById(R.id.spVillageName);

        rgHouseOwner = (RadioGroup) findViewById(R.id.rgHouseOwner);

        rbHouseOwnerYes = (RadioButton) findViewById(R.id.rbHouseOwnerYes);
        rbHouseOwnerYes.setOnCheckedChangeListener(this);

        loHouseOwner = (LinearLayout) findViewById(R.id.loHouseOwner);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == rbHouseOwnerYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbHouseOwnerYes, loHouseOwner);
        }
    }
}
