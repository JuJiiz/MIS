package com.example.jujiiz.mis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelShowHideLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HouseholdFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    Button btnSavingData;
    EditText etDate;

    CheckBox cbProb10;
    LinearLayout loAnotherProblem;

    RadioButton rbProbEnvyYes;
    CheckBox cbSound, cbShock, cbDust, cbSmell, cbAir, cbWater, cbGarbage;
    LinearLayout loEnvyProblem, loSound, loShock, loDust, loSmell, loAir, loWater, loGarbage;

    RadioButton rbDisasterYes;
    CheckBox cbStorm, cbFlood, cbMud, cbEarthquake, cbBuilding, cbDrought, cbCold, cbRoad, cbFire, cbFireForest, cbSmoke, cbChemical, cbPlague, cbWeed;
    LinearLayout loDisaster, loStorm, loFlood, loMud, loEarthquake, loBuilding, loDrought, loCold, loRoad, loFire, loFireForest, loSmoke, loChemical, loPlague, loWeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_form);
        init();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/YYYY ");
        etDate.setText(mdformat.format(calendar.getTime()));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void init() {
        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);

        etDate = (EditText) findViewById(R.id.etDate);


        rbProbEnvyYes = (RadioButton) findViewById(R.id.rbProbEnvyYes);
        rbDisasterYes = (RadioButton) findViewById(R.id.rbDisasterYes);

        rbProbEnvyYes.setOnCheckedChangeListener(this);
        rbDisasterYes.setOnCheckedChangeListener(this);

        cbProb10 = (CheckBox) findViewById(R.id.cbProb10);
        cbSound = (CheckBox) findViewById(R.id.cbSound);
        cbShock = (CheckBox) findViewById(R.id.cbShock);
        cbDust = (CheckBox) findViewById(R.id.cbDust);
        cbSmell = (CheckBox) findViewById(R.id.cbSmell);
        cbAir = (CheckBox) findViewById(R.id.cbAir);
        cbWater = (CheckBox) findViewById(R.id.cbWater);
        cbGarbage = (CheckBox) findViewById(R.id.cbGarbage);
        cbStorm = (CheckBox) findViewById(R.id.cbStorm);
        cbFlood = (CheckBox) findViewById(R.id.cbFlood);
        cbMud = (CheckBox) findViewById(R.id.cbMud);
        cbEarthquake = (CheckBox) findViewById(R.id.cbEarthquake);
        cbBuilding = (CheckBox) findViewById(R.id.cbBuilding);
        cbDrought = (CheckBox) findViewById(R.id.cbDrought);
        cbCold = (CheckBox) findViewById(R.id.cbCold);
        cbRoad = (CheckBox) findViewById(R.id.cbRoad);
        cbFire = (CheckBox) findViewById(R.id.cbFire);
        cbFireForest = (CheckBox) findViewById(R.id.cbFireForest);
        cbSmoke = (CheckBox) findViewById(R.id.cbSmoke);
        cbChemical = (CheckBox) findViewById(R.id.cbChemical);
        cbPlague = (CheckBox) findViewById(R.id.cbPlague);
        cbWeed = (CheckBox) findViewById(R.id.cbWeed);

        cbProb10.setOnCheckedChangeListener(this);
        cbSound.setOnCheckedChangeListener(this);
        cbShock.setOnCheckedChangeListener(this);
        cbDust.setOnCheckedChangeListener(this);
        cbSmell.setOnCheckedChangeListener(this);
        cbAir.setOnCheckedChangeListener(this);
        cbWater.setOnCheckedChangeListener(this);
        cbGarbage.setOnCheckedChangeListener(this);
        cbStorm.setOnCheckedChangeListener(this);
        cbFlood.setOnCheckedChangeListener(this);
        cbMud.setOnCheckedChangeListener(this);
        cbEarthquake.setOnCheckedChangeListener(this);
        cbBuilding.setOnCheckedChangeListener(this);
        cbDrought.setOnCheckedChangeListener(this);
        cbCold.setOnCheckedChangeListener(this);
        cbRoad.setOnCheckedChangeListener(this);
        cbFire.setOnCheckedChangeListener(this);
        cbFireForest.setOnCheckedChangeListener(this);
        cbSmoke.setOnCheckedChangeListener(this);
        cbChemical.setOnCheckedChangeListener(this);
        cbPlague.setOnCheckedChangeListener(this);
        cbWeed.setOnCheckedChangeListener(this);

        loAnotherProblem = (LinearLayout) findViewById(R.id.loAnotherProblem);
        loEnvyProblem = (LinearLayout) findViewById(R.id.loEnvyProblem); //Radio
        loSound = (LinearLayout) findViewById(R.id.loSound);
        loShock = (LinearLayout) findViewById(R.id.loShock);
        loDust = (LinearLayout) findViewById(R.id.loDust);
        loSmell = (LinearLayout) findViewById(R.id.loSmell);
        loAir = (LinearLayout) findViewById(R.id.loAir);
        loWater = (LinearLayout) findViewById(R.id.loWater);
        loGarbage = (LinearLayout) findViewById(R.id.loGarbage);
        loDisaster = (LinearLayout) findViewById(R.id.loDisaster); //Radio
        loStorm = (LinearLayout) findViewById(R.id.loStorm);
        loFlood = (LinearLayout) findViewById(R.id.loFlood);
        loMud = (LinearLayout) findViewById(R.id.loMud);
        loEarthquake = (LinearLayout) findViewById(R.id.loEarthquake);
        loBuilding = (LinearLayout) findViewById(R.id.loBuilding);
        loDrought = (LinearLayout) findViewById(R.id.loDrought);
        loCold = (LinearLayout) findViewById(R.id.loCold);
        loRoad = (LinearLayout) findViewById(R.id.loRoad);
        loFire = (LinearLayout) findViewById(R.id.loFire);
        loFireForest = (LinearLayout) findViewById(R.id.loFireForest);
        loSmoke = (LinearLayout) findViewById(R.id.loSmoke);
        loChemical = (LinearLayout) findViewById(R.id.loChemical);
        loPlague = (LinearLayout) findViewById(R.id.loPlague);
        loWeed = (LinearLayout) findViewById(R.id.loWeed);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //RadioButton
        if (compoundButton == rbProbEnvyYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbProbEnvyYes, loEnvyProblem);
        }
        if (compoundButton == rbDisasterYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbDisasterYes, loDisaster);
        }

        //Checkbox
        if (compoundButton == cbProb10) {
            ModelShowHideLayout.checkboxShowHide(cbProb10, loAnotherProblem);
        }
        if (compoundButton == cbSound) {
            ModelShowHideLayout.checkboxShowHide(cbSound, loSound);
        }
        if (compoundButton == cbShock) {
            ModelShowHideLayout.checkboxShowHide(cbShock, loShock);
        }
        if (compoundButton == cbDust) {
            ModelShowHideLayout.checkboxShowHide(cbDust, loDust);
        }
        if (compoundButton == cbSmell) {
            ModelShowHideLayout.checkboxShowHide(cbSmell, loSmell);
        }
        if (compoundButton == cbAir) {
            ModelShowHideLayout.checkboxShowHide(cbAir, loAir);
        }
        if (compoundButton == cbWater) {
            ModelShowHideLayout.checkboxShowHide(cbWater, loWater);
        }
        if (compoundButton == cbGarbage) {
            ModelShowHideLayout.checkboxShowHide(cbGarbage, loGarbage);
        }
        if (compoundButton == cbStorm) {
            ModelShowHideLayout.checkboxShowHide(cbStorm, loStorm);
        }
        if (compoundButton == cbFlood) {
            ModelShowHideLayout.checkboxShowHide(cbFlood, loFlood);
        }
        if (compoundButton == cbMud) {
            ModelShowHideLayout.checkboxShowHide(cbMud, loMud);
        }
        if (compoundButton == cbEarthquake) {
            ModelShowHideLayout.checkboxShowHide(cbEarthquake, loEarthquake);
        }
        if (compoundButton == cbBuilding) {
            ModelShowHideLayout.checkboxShowHide(cbBuilding, loBuilding);
        }
        if (compoundButton == cbDrought) {
            ModelShowHideLayout.checkboxShowHide(cbDrought, loDrought);
        }
        if (compoundButton == cbCold) {
            ModelShowHideLayout.checkboxShowHide(cbCold, loCold);
        }
        if (compoundButton == cbRoad) {
            ModelShowHideLayout.checkboxShowHide(cbRoad, loRoad);
        }
        if (compoundButton == cbFire) {
            ModelShowHideLayout.checkboxShowHide(cbFire, loFire);
        }
        if (compoundButton == cbFireForest) {
            ModelShowHideLayout.checkboxShowHide(cbFireForest, loFireForest);
        }
        if (compoundButton == cbSmoke) {
            ModelShowHideLayout.checkboxShowHide(cbSmoke, loSmoke);
        }
        if (compoundButton == cbChemical) {
            ModelShowHideLayout.checkboxShowHide(cbChemical, loChemical);
        }
        if (compoundButton == cbPlague) {
            ModelShowHideLayout.checkboxShowHide(cbPlague, loPlague);
        }
        if (compoundButton == cbWeed) {
            ModelShowHideLayout.checkboxShowHide(cbWeed, loWeed);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}
