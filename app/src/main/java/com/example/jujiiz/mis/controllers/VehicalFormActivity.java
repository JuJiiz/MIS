package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class VehicalFormActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    EditText etOwnerName, etOwnerPersonalID, etRegisterDate, etDate;
    RadioGroup rdVehicalType, rdRent;
    RadioButton rbVehicalType1, rbVehicalType2, rbVehicalType3, rbVehicalType4, rbVehicalType5, rbRentNo, rbRentYes;
    LinearLayout loVehicalType1, loVehicalType2, loVehicalType3, loVehicalType4, loVehicalType5;
    Spinner spVehicalType1, spVehicalType2, spVehicalType3, spVehicalType4, spVehicalType5, spContributor;
    Button btnSavingData;

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> DwellerList, OwnerList, VehicleList, VTypeList, TestList;
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayList<String> Type1 = new ArrayList<String>();
    ArrayList<String> Type2 = new ArrayList<String>();
    ArrayList<String> Type3 = new ArrayList<String>();
    ArrayList<String> Type4 = new ArrayList<String>();
    ArrayList<String> Type5 = new ArrayList<String>();
    ArrayAdapter<String> dwellerArrayAdapter, AdapterType1, AdapterType2, AdapterType3, AdapterType4, AdapterType5;
    String PersonID, HouseID, VehicleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_form);
        PersonID = getIntent().getExtras().getString("PersonID");
        HouseID = getIntent().getExtras().getString("HouseID");
        VehicleID = getIntent().getExtras().getString("VehicleID");

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        setSpinner();
        setField();
    }

    private void init() {
        etOwnerName = (EditText) findViewById(R.id.etOwnerName);
        etOwnerPersonalID = (EditText) findViewById(R.id.etOwnerPersonalID);
        etRegisterDate = (EditText) findViewById(R.id.etRegisterDate);
        etDate = (EditText) findViewById(R.id.etDate);

        rdVehicalType = (RadioGroup) findViewById(R.id.rdVehicalType);
        rdRent = (RadioGroup) findViewById(R.id.rdRent);

        rbVehicalType1 = (RadioButton) findViewById(R.id.rbVehicalType1);
        rbVehicalType2 = (RadioButton) findViewById(R.id.rbVehicalType2);
        rbVehicalType3 = (RadioButton) findViewById(R.id.rbVehicalType3);
        rbVehicalType4 = (RadioButton) findViewById(R.id.rbVehicalType4);
        rbVehicalType5 = (RadioButton) findViewById(R.id.rbVehicalType5);
        rbRentNo = (RadioButton) findViewById(R.id.rbRentNo);
        rbRentYes = (RadioButton) findViewById(R.id.rbRentYes);
        rbVehicalType1.setOnCheckedChangeListener(this);
        rbVehicalType2.setOnCheckedChangeListener(this);
        rbVehicalType3.setOnCheckedChangeListener(this);
        rbVehicalType4.setOnCheckedChangeListener(this);
        rbVehicalType5.setOnCheckedChangeListener(this);
        rbRentYes.setOnCheckedChangeListener(this);

        loVehicalType1 = (LinearLayout) findViewById(R.id.loVehicalType1);
        loVehicalType2 = (LinearLayout) findViewById(R.id.loVehicalType2);
        loVehicalType3 = (LinearLayout) findViewById(R.id.loVehicalType3);
        loVehicalType4 = (LinearLayout) findViewById(R.id.loVehicalType4);
        loVehicalType5 = (LinearLayout) findViewById(R.id.loVehicalType5);

        spVehicalType1 = (Spinner) findViewById(R.id.spVehicalType1);
        spVehicalType2 = (Spinner) findViewById(R.id.spVehicalType2);
        spVehicalType3 = (Spinner) findViewById(R.id.spVehicalType3);
        spVehicalType4 = (Spinner) findViewById(R.id.spVehicalType4);
        spVehicalType5 = (Spinner) findViewById(R.id.spVehicalType5);
        spContributor = (Spinner) findViewById(R.id.spContributor);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
    }

    private void setSpinner() {
        DwellerList = db.SelectWhereData("population", "house_id", HouseID);
        if (!DwellerList.isEmpty()) {
            for (int i = 0; i < DwellerList.size(); i++) {
                String strDweller = DwellerList.get(i).get("firstname") + " " + DwellerList.get(i).get("lastname");
                Dweller.add(strDweller);
            }
            String[] spDwellerArray = Dweller.toArray(new String[0]);
            dwellerArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spDwellerArray, spContributor);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะทั่วไป" + "\"");
        if (!VTypeList.isEmpty()) {
            for (int i = 0; i < VTypeList.size(); i++) {
                Type1.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType1 = Type1.toArray(new String[0]);
            AdapterType1 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType1, spVehicalType1);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน" + "\"");
        if (!VTypeList.isEmpty()) {
            for (int i = 0; i < VTypeList.size(); i++) {
                Type2.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType2 = Type2.toArray(new String[0]);
            AdapterType2 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType2, spVehicalType2);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านบรรเทาสาธารณภัย" + "\"");
        if (!VTypeList.isEmpty()) {
            for (int i = 0; i < VTypeList.size(); i++) {
                Type3.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType3 = Type3.toArray(new String[0]);
            AdapterType3 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType3, spVehicalType3);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านสิ่งแวดล้อม" + "\"");
        if (!VTypeList.isEmpty()) {
            for (int i = 0; i < VTypeList.size(); i++) {
                Type4.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType4 = Type4.toArray(new String[0]);
            AdapterType4 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType4, spVehicalType4);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านการศึกษา" + "\"");
        if (!VTypeList.isEmpty()) {
            for (int i = 0; i < VTypeList.size(); i++) {
                Type5.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType5 = Type5.toArray(new String[0]);
            AdapterType5 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType5, spVehicalType5);
        }
    }

    private void setField() {
        OwnerList = db.SelectWhereData("population", "population_idcard", PersonID);
        etOwnerName.setText(OwnerList.get(0).get("firstname") + " " + OwnerList.get(0).get("lastname"));
        etOwnerPersonalID.setText(OwnerList.get(0).get("population_idcard"));
        if (!VehicleID.equals("Nope")) {
            VehicleList = db.SelectWhereData("population_asset_vehicle", "vehicle_running", VehicleID);
            etRegisterDate.setText(VehicleList.get(0).get("regisdate"));

            VTypeList = db.SelectWhereData("asset_vehicle", "vtype_id", VehicleList.get(0).get("vtype_id"));
            if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะทั่วไป")){
                rbVehicalType1.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType1.setSelection(spinnerPositionType);
            }else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน")){
                rbVehicalType2.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType2.setSelection(spinnerPositionType);
            }else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านบรรเทาสาธารณภัย")){
                rbVehicalType3.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType3.setSelection(spinnerPositionType);
            }else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านสิ่งแวดล้อม")){
                rbVehicalType4.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType4.setSelection(spinnerPositionType);
            }else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านการศึกษา")){
                rbVehicalType5.setChecked(true);
                int spinnerPositionType = AdapterType5.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType5.setSelection(spinnerPositionType);
            }

            if (VehicleList.get(0).get("vehical_rent").equals("0")) {
                rbRentNo.setChecked(true);
            } else if (VehicleList.get(0).get("vehical_rent").equals("1")) {
                rbRentYes.setChecked(true);
            }
            int spinnerPositionContri = dwellerArrayAdapter.getPosition(VehicleList.get(0).get("distributor"));
            spContributor.setSelection(spinnerPositionContri);
        }
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());
        Val = new ContentValues();
        Val.put("population_idcard", PersonID);
        Val.put("regisdate", etRegisterDate.getText().toString());
        if (rbVehicalType1.isChecked()) {
            TestList = db.SelectWhereData("asset_vehicle", "vtype_detail", "\"" + spVehicalType1.getSelectedItem() + "\"");
            Val.put("vtype_id", TestList.get(0).get("vtype_id"));
        } else if (rbVehicalType2.isChecked()) {
            TestList = db.SelectWhereData("asset_vehicle", "vtype_detail", "\"" + spVehicalType2.getSelectedItem() + "\"");
            Val.put("vtype_id", TestList.get(0).get("vtype_id"));
        } else if (rbVehicalType3.isChecked()) {
            TestList = db.SelectWhereData("asset_vehicle", "vtype_detail", "\"" + spVehicalType3.getSelectedItem() + "\"");
            Val.put("vtype_id", TestList.get(0).get("vtype_id"));
        } else if (rbVehicalType4.isChecked()) {
            TestList = db.SelectWhereData("asset_vehicle", "vtype_detail", "\"" + spVehicalType4.getSelectedItem() + "\"");
            Val.put("vtype_id", TestList.get(0).get("vtype_id"));
        } else if (rbVehicalType5.isChecked()) {
            TestList = db.SelectWhereData("asset_vehicle", "vtype_detail", "\"" + spVehicalType5.getSelectedItem() + "\"");
            Val.put("vtype_id", TestList.get(0).get("vtype_id"));
        } else {
            Val.put("vtype_id", "");
        }

        if (rbRentYes.isChecked()) {
            Val.put("vehical_rent", "1");
        } else if (rbRentNo.isChecked()) {
            Val.put("vehical_rent", "0");
        } else {
            Val.put("vehical_rent", "");
        }
        Val.put("distributor", spContributor.getSelectedItem().toString());
        Val.put("upd_by", "");
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        if (VehicleID.equals("Nope")) {
            Val.put("cr_by", "JuJiiz");
            Val.put("cr_date", date);
            db.InsertData("population_asset_vehicle", Val);
        } else {
            VehicleList = db.SelectWhereData("population_asset_vehicle", "vehicle_running", VehicleID);
            if (VehicleList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_asset_vehicle", Val);
            } else {
                db.UpdateData("population_asset_vehicle", Val, "vehicle_running", VehicleID);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSavingData) {
            updateData();
            Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == rbVehicalType1)
            ModelShowHideLayout.radiobuttonShowHide(rbVehicalType1, loVehicalType1);
        if (buttonView == rbVehicalType2)
            ModelShowHideLayout.radiobuttonShowHide(rbVehicalType2, loVehicalType2);
        if (buttonView == rbVehicalType3)
            ModelShowHideLayout.radiobuttonShowHide(rbVehicalType3, loVehicalType3);
        if (buttonView == rbVehicalType4)
            ModelShowHideLayout.radiobuttonShowHide(rbVehicalType4, loVehicalType4);
        if (buttonView == rbVehicalType5)
            ModelShowHideLayout.radiobuttonShowHide(rbVehicalType5, loVehicalType5);
    }
}
