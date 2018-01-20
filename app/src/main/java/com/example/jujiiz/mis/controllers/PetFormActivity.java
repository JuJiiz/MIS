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

public class PetFormActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    EditText etPetOwner, etPetAmount, etPetBorn, etDate;
    RadioGroup rdPetRegister, rdPetType, rdPetSex, rdPetVaccine, rdVaccineContinue, rdPetBorn;
    RadioButton rbPetRegisterNo, rbPetRegisterYes, rbPetTypeDog, rbPetTypeCat, rbPetSexMale, rbPetSexFemale, rbPetVaccineNo, rbPetVaccineYes, rbVaccineContinueNo, rbVaccineContinueYes, rbPetBornNo, rbPetBornYes;
    LinearLayout loVaccineContinue, loLastVaccine, loPetBorn;
    Spinner spSterile, spLastVaccine, spContributor;
    Button btnSavingData;

    String[] spMonthArray = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
    String[] spSterileArray = {"ทำหมันแล้ว", "ทำหมันแล้ว", "ไม่ทราบ", "ฉีดยาคุม"};

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> DwellerList, OwnerList, PetList;
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayAdapter<String> dwellerArrayAdapter, monthArrayAdapter, sterileArrayAdapter;
    String PersonID, HouseID, PetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_form);
        PersonID = getIntent().getExtras().getString("PersonID");
        HouseID = getIntent().getExtras().getString("HouseID");
        PetID = getIntent().getExtras().getString("PetID");

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        setSpinner();
        setField();
    }

    private void init() {
        etPetOwner = (EditText) findViewById(R.id.etPetOwner);
        etPetAmount = (EditText) findViewById(R.id.etPetAmount);
        etPetBorn = (EditText) findViewById(R.id.etPetBorn);
        etDate = (EditText) findViewById(R.id.etDate);

        rdPetRegister = (RadioGroup) findViewById(R.id.rdPetRegister);
        rdPetType = (RadioGroup) findViewById(R.id.rdPetType);
        rdPetSex = (RadioGroup) findViewById(R.id.rdPetSex);
        rdPetVaccine = (RadioGroup) findViewById(R.id.rdPetVaccine);
        rdVaccineContinue = (RadioGroup) findViewById(R.id.rdVaccineContinue);
        rdPetBorn = (RadioGroup) findViewById(R.id.rdPetBorn);

        rbPetRegisterNo = (RadioButton) findViewById(R.id.rbPetRegisterNo);
        rbPetRegisterYes = (RadioButton) findViewById(R.id.rbPetRegisterYes);
        rbPetTypeDog = (RadioButton) findViewById(R.id.rbPetTypeDog);
        rbPetTypeCat = (RadioButton) findViewById(R.id.rbPetTypeCat);
        rbPetSexMale = (RadioButton) findViewById(R.id.rbPetSexMale);
        rbPetSexFemale = (RadioButton) findViewById(R.id.rbPetSexFemale);
        rbPetVaccineNo = (RadioButton) findViewById(R.id.rbPetVaccineNo);
        rbPetVaccineYes = (RadioButton) findViewById(R.id.rbPetVaccineYes);
        rbVaccineContinueNo = (RadioButton) findViewById(R.id.rbVaccineContinueNo);
        rbVaccineContinueYes = (RadioButton) findViewById(R.id.rbVaccineContinueYes);
        rbPetBornNo = (RadioButton) findViewById(R.id.rbPetBornNo);
        rbPetBornYes = (RadioButton) findViewById(R.id.rbPetBornYes);
        rbPetVaccineYes.setOnCheckedChangeListener(this);
        rbPetBornYes.setOnCheckedChangeListener(this);

        loVaccineContinue = (LinearLayout) findViewById(R.id.loVaccineContinue);
        loLastVaccine = (LinearLayout) findViewById(R.id.loLastVaccine);
        loPetBorn = (LinearLayout) findViewById(R.id.loPetBorn);

        spSterile = (Spinner) findViewById(R.id.spSterile);
        spLastVaccine = (Spinner) findViewById(R.id.spLastVaccine);
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

        monthArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spMonthArray, spLastVaccine);
        sterileArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spSterileArray, spSterile);
    }

    private void setField() {
        OwnerList = db.SelectWhereData("population", "population_idcard", PersonID);
        etPetOwner.setText(OwnerList.get(0).get("firstname") + " " + OwnerList.get(0).get("lastname"));
        if (!PetID.equals("Nope")) {
            PetList = db.SelectWhereData("population_asset_pet", "pet_running", PetID);
            if (PetList.get(0).get("pet_regis").equals("0")) {
                rbPetRegisterNo.setChecked(true);
            } else if (PetList.get(0).get("pet_regis").equals("1")) {
                rbPetRegisterYes.setChecked(true);
            }

            etPetAmount.setText(PetList.get(0).get("pet_amount"));

            if (PetList.get(0).get("pet_type").equals("0")) {
                rbPetTypeDog.setChecked(true);
            } else if (PetList.get(0).get("pet_type").equals("1")) {
                rbPetTypeCat.setChecked(true);
            }

            if (PetList.get(0).get("pet_sex").equals("M")) {
                rbPetSexMale.setChecked(true);
            } else if (PetList.get(0).get("pet_sex").equals("F")) {
                rbPetSexFemale.setChecked(true);
            }

            if (PetList.get(0).get("vaccine").equals("0")) {
                rbPetVaccineNo.setChecked(true);
            } else if (PetList.get(0).get("vaccine").equals("1")) {
                rbPetVaccineYes.setChecked(true);
                if (PetList.get(0).get("vaccine_during").equals("0")) {
                    rbVaccineContinueNo.setChecked(true);
                } else if (PetList.get(0).get("vaccine_during").equals("1")) {
                    rbVaccineContinueYes.setChecked(true);
                }
                int spinnerPositionMonth = monthArrayAdapter.getPosition(PetList.get(0).get("vaccine_lastest"));
                spLastVaccine.setSelection(spinnerPositionMonth);
            }

            if (PetList.get(0).get("pet_newborn").equals("0")) {
                rbPetBornNo.setChecked(true);
            } else if (PetList.get(0).get("pet_newborn").equals("1")) {
                rbPetBornYes.setChecked(true);
                etPetBorn.setText(PetList.get(0).get("pet_newborn_number"));
            }

            int spinnerPositionContri = dwellerArrayAdapter.getPosition(PetList.get(0).get("distributor"));
            spContributor.setSelection(spinnerPositionContri);
        }
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());
        Val = new ContentValues();
        Val.put("population_idcard", PersonID);
        if (rbPetRegisterNo.isChecked()) {
            Val.put("pet_regis", "0");
        } else if (rbPetRegisterYes.isChecked()) {
            Val.put("pet_regis", "1");
        } else {
            Val.put("pet_regis", "");
        }
        Val.put("pet_amount", etPetAmount.getText().toString());

        if (rbPetTypeDog.isChecked()) {
            Val.put("pet_type", "0");
        } else if (rbPetTypeCat.isChecked()) {
            Val.put("pet_type", "1");
        } else {
            Val.put("pet_type", "");
        }

        if (rbPetSexMale.isChecked()) {
            Val.put("pet_sex", "M");
        } else if (rbPetSexFemale.isChecked()) {
            Val.put("pet_sex", "F");
        } else {
            Val.put("pet_sex", "");
        }

        if (rbPetVaccineNo.isChecked()) {
            Val.put("vaccine", "0");
            Val.put("vaccine_during", "");
            Val.put("vaccine_lastest", "");
        } else if (rbPetVaccineYes.isChecked()) {
            Val.put("vaccine", "1");
            if (rbVaccineContinueNo.isChecked()) {
                Val.put("vaccine_during", "0");
            } else if (rbVaccineContinueYes.isChecked()) {
                Val.put("vaccine_during", "1");
            } else {
                Val.put("vaccine_during", "");
            }
            Val.put("vaccine_lastest", spLastVaccine.getSelectedItem().toString());
        } else {
            Val.put("vaccine", "");
            Val.put("vaccine_during", "");
            Val.put("vaccine_lastest", "");
        }

        if (rbPetBornNo.isChecked()) {
            Val.put("pet_newborn", "0");
            Val.put("pet_newborn_number", "");
        } else if (rbPetBornYes.isChecked()) {
            Val.put("pet_newborn", "1");
            Val.put("pet_newborn_number", etPetBorn.getText().toString());
        } else {
            Val.put("pet_newborn", "");
            Val.put("pet_newborn_number", "");
        }

        Val.put("distributor", spContributor.getSelectedItem().toString());
        Val.put("upd_by", "");
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        if (PetID.equals("Nope")) {
            Val.put("cr_by", "JuJiiz");
            Val.put("cr_date", date);
            db.InsertData("population_asset_pet", Val);
        } else {
            PetList = db.SelectWhereData("population_asset_pet", "pet_running", PetID);
            if (PetList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_asset_pet", Val);
            } else {
                db.UpdateData("population_asset_pet", Val, "pet_running", PetID);
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
        if (buttonView == rbPetVaccineYes)
            ModelShowHideLayout.radiobuttonShowHide(rbPetVaccineYes, loVaccineContinue);
        ModelShowHideLayout.radiobuttonShowHide(rbPetVaccineYes, loLastVaccine);
        if (buttonView == rbPetBornYes)
            ModelShowHideLayout.radiobuttonShowHide(rbPetBornYes, loPetBorn);
    }
}
