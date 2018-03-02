package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCheckForm;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class LandFormActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etOwnertName, etOwnerPersonalID, etSystemID, etDimenA, etDimenB, etDimenC, etDate;
    RadioGroup rgLandBenefit, rgLandLocation, rgLandTax, rgLandRent;
    RadioButton rbLB1, rbLB2, rbLB3, rbLB4, rbLB5, rbLL1, rbLL2, rbLT1, rbLT2, rbLT3, rbLR1, rbLR2;
    Spinner spContributor;
    Button btnSavingData;

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> DwellerList, OwnerList, LandList;
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayAdapter<String> dwellerArrayAdapter;
    String PersonID, HouseID, LandID;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_form);
        PersonID = getIntent().getExtras().getString("PersonID");
        HouseID = getIntent().getExtras().getString("HouseID");
        LandID = getIntent().getExtras().getString("LandID");

        SharedPreferences sp = LandFormActivity.this.getSharedPreferences("UserMemo", Context.MODE_PRIVATE);
        username = sp.getString("username", "");

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        setSpinner();
        setField();
    }

    private void init() {
        etOwnertName = (EditText) findViewById(R.id.etOwnertName);
        etOwnerPersonalID = (EditText) findViewById(R.id.etOwnerPersonalID);
        etSystemID = (EditText) findViewById(R.id.etSystemID);
        etDimenA = (EditText) findViewById(R.id.etDimenA);
        etDimenB = (EditText) findViewById(R.id.etDimenB);
        etDimenC = (EditText) findViewById(R.id.etDimenC);
        etDate = (EditText) findViewById(R.id.etDate);

        etDimenB.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    if (Integer.parseInt(s.toString()) > 3) {
                        etDimenB.setText("");
                    }
                }
            }
        });

        etDimenC.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    if (Integer.parseInt(s.toString()) > 99) {
                        etDimenC.setText("");
                    }
                }
            }
        });


        rgLandBenefit = (RadioGroup) findViewById(R.id.rgLandBenefit);
        rgLandLocation = (RadioGroup) findViewById(R.id.rgLandLocation);
        rgLandTax = (RadioGroup) findViewById(R.id.rgLandTax);
        rgLandRent = (RadioGroup) findViewById(R.id.rgLandRent);

        rbLB1 = (RadioButton) findViewById(R.id.rbLB1);
        rbLB2 = (RadioButton) findViewById(R.id.rbLB2);
        rbLB3 = (RadioButton) findViewById(R.id.rbLB3);
        rbLB4 = (RadioButton) findViewById(R.id.rbLB4);
        rbLB5 = (RadioButton) findViewById(R.id.rbLB5);
        rbLL1 = (RadioButton) findViewById(R.id.rbLL1);
        rbLL2 = (RadioButton) findViewById(R.id.rbLL2);
        rbLT1 = (RadioButton) findViewById(R.id.rbLT1);
        rbLT2 = (RadioButton) findViewById(R.id.rbLT2);
        rbLT3 = (RadioButton) findViewById(R.id.rbLT3);
        rbLR1 = (RadioButton) findViewById(R.id.rbLR1);
        rbLR2 = (RadioButton) findViewById(R.id.rbLR2);

        spContributor = (Spinner) findViewById(R.id.spContributor);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
    }

    private void setSpinner() {
        DwellerList = db.SelectWhereData("population", "house_id", HouseID);
        if (!DwellerList.isEmpty()) {
            Dweller.add("กรุณาเลือก");
            for (int i = 0; i < DwellerList.size(); i++) {
                String strDweller = DwellerList.get(i).get("firstname") + " " + DwellerList.get(i).get("lastname");
                Dweller.add(strDweller);
            }
            String[] spDwellerArray = Dweller.toArray(new String[0]);
            dwellerArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spDwellerArray, spContributor);
        }
    }

    private void setField() {
        OwnerList = db.SelectWhereData("population", "population_idcard", PersonID);
        etOwnertName.setText(OwnerList.get(0).get("firstname") + " " + OwnerList.get(0).get("lastname"));
        etOwnerPersonalID.setText(OwnerList.get(0).get("population_idcard"));
        if (!LandID.equals("Nope")) {
            LandList = db.SelectWhereData("population_asset_land", "land_running", LandID);
            etSystemID.setText(LandList.get(0).get("system_id"));
            etDimenA.setText(LandList.get(0).get("dimen1"));
            etDimenB.setText(LandList.get(0).get("dimen2"));
            etDimenC.setText(LandList.get(0).get("dimen3"));
            if (LandList.get(0).get("land_benefit").equals("0")) {
                rbLB1.setChecked(true);
            } else if (LandList.get(0).get("land_benefit").equals("1")) {
                rbLB2.setChecked(true);
            } else if (LandList.get(0).get("land_benefit").equals("2")) {
                rbLB3.setChecked(true);
            } else if (LandList.get(0).get("land_benefit").equals("3")) {
                rbLB4.setChecked(true);
            } else if (LandList.get(0).get("land_benefit").equals("4")) {
                rbLB5.setChecked(true);
            }

            if (LandList.get(0).get("land_location").equals("0")) {
                rbLL1.setChecked(true);
            } else if (LandList.get(0).get("land_location").equals("1")) {
                rbLL2.setChecked(true);
            }

            if (LandList.get(0).get("land_tax").equals("0")) {
                rbLT1.setChecked(true);
            } else if (LandList.get(0).get("land_tax").equals("1")) {
                rbLT2.setChecked(true);
            } else if (LandList.get(0).get("land_tax").equals("2")) {
                rbLT3.setChecked(true);
            }

            if (LandList.get(0).get("land_rent").equals("0")) {
                rbLR1.setChecked(true);
            } else if (LandList.get(0).get("land_rent").equals("1")) {
                rbLR2.setChecked(true);
            }
            int spinnerPositionContri = dwellerArrayAdapter.getPosition(LandList.get(0).get("distributor"));
            spContributor.setSelection(spinnerPositionContri);
        }
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());
        Val = new ContentValues();
        Val.put("population_idcard", PersonID);
        if (!etSystemID.getText().toString().equals("")) {
            Val.put("system_id", etSystemID.getText().toString());
        } else {
            Val.put("system_id", "0");
        }
        if (!etDimenA.getText().toString().equals("")) {
            Val.put("dimen1", etDimenA.getText().toString());
        } else {
            Val.put("dimen1", "0");
        }
        if (!etDimenB.getText().toString().equals("")) {
            Val.put("dimen2", etDimenB.getText().toString());
        } else {
            Val.put("dimen2", "0");
        }
        if (!etDimenC.getText().toString().equals("")) {
            Val.put("dimen3", etDimenC.getText().toString());
        } else {
            Val.put("dimen3", "0");
        }
        if (rbLB1.isChecked()) {
            Val.put("land_benefit", "0");
        } else if (rbLB2.isChecked()) {
            Val.put("land_benefit", "1");
        } else if (rbLB3.isChecked()) {
            Val.put("land_benefit", "2");
        } else if (rbLB4.isChecked()) {
            Val.put("land_benefit", "3");
        } else if (rbLB5.isChecked()) {
            Val.put("land_benefit", "4");
        } else {
            Val.put("land_benefit", "0");
        }

        if (rbLL1.isChecked()) {
            Val.put("land_location", "0");
        } else if (rbLL2.isChecked()) {
            Val.put("land_location", "1");
        } else {
            Val.put("land_location", "0");
        }

        if (rbLT1.isChecked()) {
            Val.put("land_tax", "0");
        } else if (rbLT2.isChecked()) {
            Val.put("land_tax", "1");
        } else if (rbLT3.isChecked()) {
            Val.put("land_tax", "2");
        } else {
            Val.put("land_tax", "0");
        }

        if (rbLR1.isChecked()) {
            Val.put("land_rent", "0");
        } else if (rbLR2.isChecked()) {
            Val.put("land_rent", "1");
        } else {
            Val.put("land_rent", "0");
        }

        Val.put("distributor", spContributor.getSelectedItem().toString());
        Val.put("upd_by", username);
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        if (LandID.equals("Nope")) {
            Val.put("cr_by", username);
            Val.put("cr_date", date);
            db.InsertData("population_asset_land", Val);
            Val = new ContentValues();
            Val.put("upload_status", "1");
            db.UpdateData("population", Val, "population_idcard", PersonID);
        } else {
            LandList = db.SelectWhereData("population_asset_land", "land_running", LandID);
            if (LandList.isEmpty()) {
                Val.put("cr_by", username);
                Val.put("cr_date", date);
                db.InsertData("population_asset_land", Val);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population", Val, "population_idcard", PersonID);
            } else {
                db.UpdateData("population_asset_land", Val, "land_running", LandID);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population", Val, "population_idcard", PersonID);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSavingData) {
            if (fieldCheck() == 0) {
                updateData();
                Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                this.finish();
            } else if (fieldCheck() == 1) {
                Toast.makeText(this, "กรุณาระบุ \"จำนวนพื้นที่\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 2) {
                Toast.makeText(this, "กรุณาระบุ \"การใช้ประโยชน์ที่ดิน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 3) {
                Toast.makeText(this, "กรุณาระบุ \"ชื่อผู้ให้ข้อมูล\"", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int fieldCheck() {
        int formPass = 0;
        Boolean dimenPass = true,//1
                benefitPass = true,//2
                conPass = true;//3

        if (ModelCheckForm.checkEditText(etDimenA) != true && ModelCheckForm.checkEditText(etDimenB) != true && ModelCheckForm.checkEditText(etDimenC) != true) {
            dimenPass = false;
        }

        if (!rbLB1.isChecked() && !rbLB2.isChecked() && !rbLB3.isChecked() && !rbLB4.isChecked() && !rbLB5.isChecked()) {
            benefitPass = false;
        }

        conPass = ModelCheckForm.checkSpinner(spContributor);

        if (dimenPass == true) {
            if (benefitPass == true) {
                if (conPass == true) {
                    formPass = 0;
                } else {
                    formPass = 3;
                }
            } else {
                formPass = 2;
            }
        } else {
            formPass = 1;
        }

        return formPass;
    }
}
