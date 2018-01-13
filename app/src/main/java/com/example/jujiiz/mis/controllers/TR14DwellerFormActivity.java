package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class TR14DwellerFormActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    myDBClass db = new myDBClass(this);
    Spinner spPrefix, spNationality;
    EditText etDwellerPersonalID, etDwellerFirstName, etDwellerLastName, etDwellerBirtDate, etNationality;
    LinearLayout loNationality;
    Button btnSavingData;

    ArrayList<String> Prefix = new ArrayList<String>();
    ArrayList<String> Nationality = new ArrayList<String>();
    ArrayList<HashMap<String, String>> PrefixList, NationalityList, TestList, DwellerList;
    ArrayAdapter<String> prefixArrayAdapter, nationalityArrayAdapter;
    ContentValues Val;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String HouseID, DwellerID, dwellerstatus = "1";
    boolean booSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14_dweller_form);
        HouseID = getIntent().getExtras().getString("HouseID");
        DwellerID = getIntent().getExtras().getString("DwellerID");
        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
        setSpinner();
        setField();
    }

    private void init() {
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
        spNationality = (Spinner) findViewById(R.id.spNationality);
        spNationality.setOnItemSelectedListener(this);

        loNationality = (LinearLayout) findViewById(R.id.loNationality);

        etDwellerPersonalID = (EditText) findViewById(R.id.etDwellerPersonalID);
        etDwellerFirstName = (EditText) findViewById(R.id.etDwellerFirstName);
        etDwellerLastName = (EditText) findViewById(R.id.etDwellerLastName);
        etDwellerBirtDate = (EditText) findViewById(R.id.etDwellerBirtDate);
        etNationality = (EditText) findViewById(R.id.etNationality);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
    }

    private void setSpinner() {
        PrefixList = db.SelectData("prename");
        for (int i = 0; i < PrefixList.size(); i++) {
            String strPrefix = PrefixList.get(i).get("prename_detail");
            Prefix.add(strPrefix);
        }
        String[] spPrefixArray = Prefix.toArray(new String[0]);
        prefixArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spPrefixArray, spPrefix);

        NationalityList = db.SelectData("nationality");
        if (!NationalityList.isEmpty()) {
            for (int i = 0; i < NationalityList.size(); i++) {
                String strActive = NationalityList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    String strNat = NationalityList.get(i).get("nationality_detail");
                    if (!Nationality.contains(strNat)) {
                        Nationality.add(strNat);
                    }
                }
            }
            Nationality.add("อื่นๆ");
            String[] spNatArray = Nationality.toArray(new String[0]);
            nationalityArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spNatArray, spNationality);
        }
    }

    private void setField() {
        if (!DwellerID.equals("Nope")) {
            DwellerList = db.SelectWhereData("population", "population_id", DwellerID);
            if (!DwellerList.isEmpty()) {
                etDwellerPersonalID.setText(DwellerList.get(0).get("population_idcard"));
                TestList = db.SelectWhereData("prename", "prename_id", DwellerList.get(0).get("prename_id"));
                int spinnerPositionPrefix = prefixArrayAdapter.getPosition(TestList.get(0).get("prename_detail"));
                spPrefix.setSelection(spinnerPositionPrefix);
                etDwellerFirstName.setText(DwellerList.get(0).get("firstname"));
                etDwellerLastName.setText(DwellerList.get(0).get("lastname"));
                etDwellerBirtDate.setText(DwellerList.get(0).get("birthdate"));
                TestList = db.SelectWhereData("nationality", "nationality_id", DwellerList.get(0).get("nationality_id"));
                int spinnerPositionNat = nationalityArrayAdapter.getPosition(TestList.get(0).get("nationality_detail"));
                spNationality.setSelection(spinnerPositionNat);
            }
        }
    }

    private void insertDweller() {
        String date = df.format(Calendar.getInstance().getTime());
        if (!HouseID.equals("Nope")) {
            NationalityList = db.SelectData("nationality");
            if (!NationalityList.isEmpty()) {

                if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                    String strAnotherNat = etNationality.getText().toString();
                    if (!strAnotherNat.equals("")) {
                        if (!Nationality.contains(strAnotherNat)) {
                            ContentValues natVal = new ContentValues();
                            natVal.put("nationality_detail", strAnotherNat);
                            natVal.put("upd_by", "JuJiiz");
                            natVal.put("upd_date", date);
                            natVal.put("ACTIVE", "Y");
                            NationalityList = db.SelectWhereData("nationality", "nationality_detail", strAnotherNat);
                            if (NationalityList == null) {
                                natVal.put("cr_by", "JuJiiz");
                                natVal.put("cr_date", date);
                                db.InsertData("nationality", natVal);
                            } else {
                                db.UpdateData("nationality", natVal, "nationality_detail", strAnotherNat);
                            }
                            String keyvalue = "\"" + strAnotherNat + "\"";
                            NationalityList = db.SelectWhereData("nationality", "nationality_detail", keyvalue);
                            if (!NationalityList.isEmpty()) {
                                Val = new ContentValues();
                                Val.put("population_idcard", etDwellerPersonalID.getText().toString());
                                TestList = db.SelectWhereData("prename", "prename_detail", "\"" + spPrefix.getSelectedItem().toString() + "\"");
                                Val.put("prename_id", TestList.get(0).get("prename_id"));
                                Val.put("firstname", etDwellerFirstName.getText().toString());
                                Val.put("lastname", etDwellerLastName.getText().toString());
                                Val.put("birthdate", etDwellerBirtDate.getText().toString());
                                Val.put("height", "");
                                Val.put("weight", "");
                                Val.put("sex", "");
                                Val.put("bloodgroup", "");
                                Val.put("living", "");
                                Val.put("maritalstatus", "");
                                Val.put("tel", "");
                                Val.put("nationality_id", NationalityList.get(0).get("nationality_id"));
                                Val.put("house_id", HouseID);
                                Val.put("currentaddr", "");
                                Val.put("currentaddr_province", "");
                                Val.put("currentaddr_country", "");
                                Val.put("dwellerstatus", dwellerstatus);
                                Val.put("income", "");
                                Val.put("income_money", "");
                                Val.put("dept", "");
                                Val.put("saving", "");
                                Val.put("allergichis", "");
                                Val.put("allergichis_detail", "");
                                Val.put("disadvantage", "");
                                Val.put("sub_al", "");
                                Val.put("education", "");
                                Val.put("education_class", "");
                                Val.put("literacy", "");
                                Val.put("technology", "");
                                Val.put("expertise", "");
                                Val.put("expertise_name", "");
                                Val.put("expertise_detail", "");
                                Val.put("religion", "");
                                Val.put("religion_another", "");
                                Val.put("participation", "");
                                Val.put("election", "");
                                Val.put("residence_status", "1");
                                Val.put("latentpop_province", "");
                                Val.put("latentpop_country", "");
                                Val.put("distributor", "");
                                Val.put("survey_status", "0");
                                Val.put("upd_by", "JuJiiz");
                                Val.put("upd_date", date);
                                Val.put("ACTIVE", "Y");
                                DwellerList = db.SelectWhereData("population", "population_idcard", etDwellerPersonalID.getText().toString());
                                if (DwellerList.isEmpty()) {
                                    Val.put("cr_by", "JuJiiz");
                                    Val.put("cr_date", date);
                                    db.InsertData("population", Val);
                                    booSuccess = true;
                                } else {
                                    db.UpdateData("population", Val, "population_idcard", etDwellerPersonalID.getText().toString());
                                    booSuccess = true;
                                }
                            }
                        } else {
                            Toast.makeText(this, "สัญชาติซ้ำ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "กรุณาระบุสัญชาติ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String strNat = "\"" + spNationality.getSelectedItem().toString() + "\"";
                    NationalityList = db.SelectWhereData("nationality", "nationality_detail", strNat);
                    if (!NationalityList.isEmpty()) {
                        Val = new ContentValues();
                        Val.put("population_idcard", etDwellerPersonalID.getText().toString());
                        TestList = db.SelectWhereData("prename", "prename_detail", "\"" + spPrefix.getSelectedItem().toString() + "\"");
                        Val.put("prename_id", TestList.get(0).get("prename_id"));
                        Val.put("firstname", etDwellerFirstName.getText().toString());
                        Val.put("lastname", etDwellerLastName.getText().toString());
                        Val.put("birthdate", etDwellerBirtDate.getText().toString());
                        Val.put("height", "");
                        Val.put("weight", "");
                        Val.put("sex", "");
                        Val.put("bloodgroup", "");
                        Val.put("living", "");
                        Val.put("maritalstatus", "");
                        Val.put("tel", "");
                        Val.put("nationality_id", NationalityList.get(0).get("nationality_id"));
                        Val.put("house_id", HouseID);
                        Val.put("currentaddr", "");
                        Val.put("currentaddr_province", "");
                        Val.put("currentaddr_country", "");
                        Val.put("dwellerstatus", dwellerstatus);
                        Val.put("income", "");
                        Val.put("income_money", "");
                        Val.put("dept", "");
                        Val.put("saving", "");
                        Val.put("allergichis", "");
                        Val.put("allergichis_detail", "");
                        Val.put("disadvantage", "");
                        Val.put("sub_al", "");
                        Val.put("education", "");
                        Val.put("education_class", "");
                        Val.put("literacy", "");
                        Val.put("technology", "");
                        Val.put("expertise", "");
                        Val.put("expertise_name", "");
                        Val.put("expertise_detail", "");
                        Val.put("religion", "");
                        Val.put("religion_another", "");
                        Val.put("participation", "");
                        Val.put("election", "");
                        Val.put("residence_status", "1");
                        Val.put("latentpop_province", "");
                        Val.put("latentpop_country", "");
                        Val.put("distributor", "");
                        Val.put("survey_status", "0");
                        Val.put("upd_by", "JuJiiz");
                        Val.put("upd_date", date);
                        Val.put("ACTIVE", "Y");
                        DwellerList = db.SelectWhereData("population", "population_idcard", etDwellerPersonalID.getText().toString());
                        if (DwellerList.isEmpty()) {
                            Val.put("cr_by", "JuJiiz");
                            Val.put("cr_date", date);
                            db.InsertData("population", Val);
                            booSuccess = true;
                        } else {
                            db.UpdateData("population", Val, "population_idcard", etDwellerPersonalID.getText().toString());
                            booSuccess = true;
                        }
                    }
                }
            } else {
                Toast.makeText(this, "เกิดกความิดพลาดบางอย่าง", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSavingData) {
            if (!etDwellerPersonalID.getText().toString().equals("")) {
                insertDweller();
            } else {
                Toast.makeText(this, "กรุณากรอกข้อมูลผู้อาศัย", Toast.LENGTH_SHORT).show();
            }

            if (booSuccess == true) {
                //Intent intent = new Intent(this, TR14HouseFormActivity.class);
                this.finish();
                //this.startActivity(intent);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == spNationality) {
            if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                loNationality.setVisibility(View.VISIBLE);
            } else {
                loNationality.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
