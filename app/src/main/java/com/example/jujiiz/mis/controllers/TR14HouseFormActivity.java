package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import junit.framework.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TR14HouseFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener,View.OnTouchListener {
    myDBClass db = new myDBClass(this);
    Spinner spPrefix, spVillageName, spNationality;
    RadioGroup rgHouseOwner, rgHouseOwnerNationality;
    RadioButton rbHouseOwnerNo,rbHouseOwnerYes, rbHouseOwnerTHNationality, rbHouseOwnerAnotherNationality, radioButton;
    LinearLayout loHouseOwner, loHouseOwnerNationality;
    RelativeLayout loListview;
    Button btnAddDweller, btnSavingData;
    ListView listDweller;
    EditText etHouseID, etHouseNo, etHouseOwnerPersonalID, etHouseOwnerFirstName, etHouseOwnerLastName, etHouseOwnerBirtDate, etHouseOwnerNationality;

    ArrayList<String> Prefix = new ArrayList<String>();
    ArrayList<String> Village = new ArrayList<String>();
    ArrayList<String> Nationality = new ArrayList<String>();
    ArrayList<HashMap<String, String>> PrefixList, VillageList, HouseList, VilleIDList, NationalList, NationalIDList, OwnerList, TestList,PopulationActive;
    ArrayAdapter<String> prefixArrayAdapter, villeArrayAdapter, natArrayAdapter;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    boolean booSuccess = false;

    String HouseID,dwellerstatus = "0",SelectedIDItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr14_house_form);
        HouseID = getIntent().getExtras().getString("HouseID");
        Log.d("MYLOG", "HouseID: "+HouseID);
        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
        setSpinner();
        setField();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }

    private void setSpinner() {
        PrefixList = db.SelectData("prename");
        if (!PrefixList.isEmpty()) {
            for (int i = 0; i < PrefixList.size(); i++) {
                String strPrefix = PrefixList.get(i).get("prename_detail");
                Prefix.add(strPrefix);
            }
            String[] spPrefixArray = Prefix.toArray(new String[0]);
            prefixArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spPrefixArray, spPrefix);
        }

        VillageList = db.SelectData("vilage");
        if (!VillageList.isEmpty()) {
            for (int i = 0; i < VillageList.size(); i++) {
                String strActive = VillageList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    String strVillage = VillageList.get(i).get("vilage_name");
                    if (!Village.contains(strVillage)) {
                        Village.add(strVillage);
                    }

                }
            }
            String[] spVillageArray = Village.toArray(new String[0]);
            villeArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spVillageArray, spVillageName);
        }

        NationalList = db.SelectData("nationality");
        if (!NationalList.isEmpty()) {
            for (int i = 0; i < NationalList.size(); i++) {
                String strActive = NationalList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    String strNat = NationalList.get(i).get("nationality_detail");
                    if (!Nationality.contains(strNat)) {
                        Nationality.add(strNat);
                    }
                }
            }
            Nationality.add("อื่นๆ");
            String[] spNatArray = Nationality.toArray(new String[0]);
            natArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spNatArray, spNationality);
        }
    }

    private void init() {
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
        spVillageName = (Spinner) findViewById(R.id.spVillageName);
        spNationality = (Spinner) findViewById(R.id.spNationality);
        spNationality.setOnItemSelectedListener(this);

        etHouseID = (EditText) findViewById(R.id.etHouseID);
        etHouseNo = (EditText) findViewById(R.id.etHouseNo);
        etHouseOwnerPersonalID = (EditText) findViewById(R.id.etHouseOwnerPersonalID);
        etHouseOwnerFirstName = (EditText) findViewById(R.id.etHouseOwnerFirstName);
        etHouseOwnerLastName = (EditText) findViewById(R.id.etHouseOwnerLastName);
        etHouseOwnerBirtDate = (EditText) findViewById(R.id.etHouseOwnerBirtDate);
        etHouseOwnerNationality = (EditText) findViewById(R.id.etHouseOwnerNationality);

        rgHouseOwner = (RadioGroup) findViewById(R.id.rgHouseOwner);
        //rgHouseOwnerNationality = (RadioGroup) findViewById(R.id.rgHouseOwnerNationality);

        rbHouseOwnerNo = (RadioButton) findViewById(R.id.rbHouseOwnerYes);
        rbHouseOwnerYes = (RadioButton) findViewById(R.id.rbHouseOwnerYes);
        rbHouseOwnerYes.setOnCheckedChangeListener(this);
        /*rbHouseOwnerTHNationality = (RadioButton) findViewById(R.id.rbHouseOwnerTHNationality);
        rbHouseOwnerTHNationality.setOnCheckedChangeListener(this);
        rbHouseOwnerAnotherNationality = (RadioButton) findViewById(R.id.rbHouseOwnerAnotherNationality);
        rbHouseOwnerAnotherNationality.setOnCheckedChangeListener(this);*/

        loHouseOwner = (LinearLayout) findViewById(R.id.loHouseOwner);
        loHouseOwnerNationality = (LinearLayout) findViewById(R.id.loHouseOwnerNationality);
        loListview = (RelativeLayout) findViewById(R.id.loListview);

        listDweller = (ListView) findViewById(R.id.listDweller);
        listDweller.setOnItemClickListener(this);
        listDweller.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        btnAddDweller = (Button) findViewById(R.id.btnAddDweller);
        btnAddDweller.setOnClickListener(this);
        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
    }

    private void setField() {
        if (!HouseID.equals("Nope")) {
            HouseList = db.SelectWhereData("house", "house_id", HouseID);
            if (!HouseList.isEmpty()) {
                loListview.setVisibility(View.VISIBLE);
                TestList = db.SelectWhereData("vilage", "vilage_id", HouseList.get(0).get("vilage_id"));
                int spinnerPositionVille = villeArrayAdapter.getPosition(TestList.get(0).get("vilage_name"));
                spVillageName.setSelection(spinnerPositionVille);
                etHouseID.setText(HouseList.get(0).get("house_id"));
                etHouseNo.setText(HouseList.get(0).get("house_no"));

                OwnerList = db.SelectWhereData("population", "house_id", HouseID);
                if (!OwnerList.isEmpty()) {
                    for (int i = 0; i < OwnerList.size(); i++) {
                        if (OwnerList.get(i).get("dwellerstatus").equals("0")) {
                            rbHouseOwnerYes.setChecked(true);
                            etHouseOwnerPersonalID.setText(OwnerList.get(i).get("population_idcard"));
                            TestList = db.SelectWhereData("prename", "prename_id", OwnerList.get(i).get("prename_id"));
                            int spinnerPositionPrefix = prefixArrayAdapter.getPosition(TestList.get(0).get("prename_detail"));
                            spPrefix.setSelection(spinnerPositionPrefix);
                            etHouseOwnerFirstName.setText(OwnerList.get(i).get("firstname"));
                            etHouseOwnerLastName.setText(OwnerList.get(i).get("lastname"));
                            etHouseOwnerBirtDate.setText(OwnerList.get(i).get("birthdate"));
                            TestList = db.SelectWhereData("nationality", "nationality_id", OwnerList.get(i).get("nationality_id"));
                            int spinnerPositionNat = natArrayAdapter.getPosition(TestList.get(0).get("nationality_detail"));
                            spNationality.setSelection(spinnerPositionNat);

                            setListView();
                        } else {
                            rbHouseOwnerYes.setChecked(true);
                        }
                    }
                } else {
                    rbHouseOwnerYes.setChecked(true);
                }
            } else {
                loListview.setVisibility(View.GONE);
            }
        }else {
            loListview.setVisibility(View.GONE);
        }
    }

    private void setListView() {
        if (!HouseID.equals("Nope")) {
            String strPersonalID = "PID";
            String strFName = "FName";
            String strLName = "LName";
            String strPopulationID = "ID";
            PopulationActive = new ArrayList<HashMap<String, String>>();
            OwnerList = db.SelectWhereData("population","house_id",HouseID);
            if (!OwnerList.isEmpty()) {
                for (int i = 0; i < OwnerList.size(); i++) {
                    String strActive = OwnerList.get(i).get("ACTIVE");
                    if (strActive.equals("Y")) {
                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put(strPersonalID, OwnerList.get(i).get("population_idcard"));
                        temp.put(strFName, OwnerList.get(i).get("firstname"));
                        temp.put(strLName, OwnerList.get(i).get("lastname"));
                        temp.put(strPopulationID, OwnerList.get(i).get("population_id"));
                        PopulationActive.add(temp);
                    }
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, PopulationActive, R.layout.view_population_column,
                        new String[]{strPersonalID, strFName, strLName, strPopulationID},
                        new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvHiddenColumn}
                );
                listDweller.setAdapter(simpleAdapter);
                Log.d("MYLOG", "Set ListView");
            }
        }
    }

    private void insertHouse() {
        String date = df.format(Calendar.getInstance().getTime());
        VillageList = db.SelectData("vilage");
        if (!VillageList.isEmpty()) {
            String strVille = "\"" + spVillageName.getSelectedItem().toString() + "\"";
            VilleIDList = db.SelectWhereData("vilage", "vilage_name", strVille);

            Val = new ContentValues();
            Val.put("house_id", etHouseID.getText().toString());
            Val.put("house_no", etHouseNo.getText().toString());
            Val.put("vilage_id", VilleIDList.get(0).get("vilage_id"));
            Val.put("house_location_lat", "");
            Val.put("house_location_lng", "");
            Val.put("house_in_registry", "");
            Val.put("house_status", "");
            Val.put("house_family_type", "");
            Val.put("distributor_img", "");
            Val.put("distributor", "");
            Val.put("survey_status", "0");
            Val.put("upd_by", "JuJiiz");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            HouseList = db.SelectWhereData("house", "house_id", etHouseID.getText().toString());
            if (HouseList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("house", Val);
                booSuccess = true;
            } else {
                db.UpdateData("house", Val, "house_id", etHouseID.getText().toString());
                booSuccess = true;
            }
        } else {
            Toast.makeText(this, "เกิดกความิดพลาดบางอย่าง", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertOwner() {
        String date = df.format(Calendar.getInstance().getTime());

        NationalList = db.SelectData("nationality");
        if (!NationalList.isEmpty()) {

            if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                String strAnotherNat = etHouseOwnerNationality.getText().toString();
                if (!strAnotherNat.equals("")) {
                    if (!Nationality.contains(strAnotherNat)) {
                        ContentValues natVal = new ContentValues();
                        natVal.put("nationality_detail", strAnotherNat);
                        natVal.put("upd_by", "JuJiiz");
                        natVal.put("upd_date", date);
                        natVal.put("ACTIVE", "Y");
                        NationalList = db.SelectWhereData("nationality", "nationality_detail", strAnotherNat);
                        if (NationalList == null) {
                            natVal.put("cr_by", "JuJiiz");
                            natVal.put("cr_date", date);
                            db.InsertData("nationality", natVal);
                        } else {
                            db.UpdateData("nationality", natVal, "nationality_detail", strAnotherNat);
                        }
                        String keyvalue = "\"" + strAnotherNat + "\"";
                        NationalIDList = db.SelectWhereData("nationality", "nationality_detail", keyvalue);
                        if (!NationalIDList.isEmpty()) {
                            Val = new ContentValues();
                            Val.put("population_idcard", etHouseOwnerPersonalID.getText().toString());
                            TestList = db.SelectWhereData("prename", "prename_detail", "\"" + spPrefix.getSelectedItem().toString() + "\"");
                            Val.put("prename_id", TestList.get(0).get("prename_id"));
                            Val.put("firstname", etHouseOwnerFirstName.getText().toString());
                            Val.put("lastname", etHouseOwnerLastName.getText().toString());
                            Val.put("birthdate", etHouseOwnerBirtDate.getText().toString());
                            Val.put("height", "");
                            Val.put("weight", "");
                            Val.put("sex", "");
                            Val.put("bloodgroup", "");
                            Val.put("living", "");
                            Val.put("maritalstatus", "");
                            Val.put("tel", "");
                            Val.put("nationality_id", NationalIDList.get(0).get("nationality_id"));
                            Val.put("house_id", etHouseID.getText().toString());
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
                            HouseList = db.SelectWhereData("population", "population_idcard", etHouseOwnerPersonalID.getText().toString());
                            if (HouseList.isEmpty()) {
                                Val.put("cr_by", "JuJiiz");
                                Val.put("cr_date", date);
                                db.InsertData("population", Val);
                                booSuccess = true;
                            } else {
                                db.UpdateData("population", Val, "population_idcard", etHouseOwnerPersonalID.getText().toString());
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
                NationalIDList = db.SelectWhereData("nationality", "nationality_detail", strNat);
                if (!NationalIDList.isEmpty()) {
                    Val = new ContentValues();
                    Val.put("population_idcard", etHouseOwnerPersonalID.getText().toString());
                    TestList = db.SelectWhereData("prename", "prename_detail", "\"" + spPrefix.getSelectedItem().toString() + "\"");
                    Val.put("prename_id", TestList.get(0).get("prename_id"));
                    Val.put("firstname", etHouseOwnerFirstName.getText().toString());
                    Val.put("lastname", etHouseOwnerLastName.getText().toString());
                    Val.put("birthdate", etHouseOwnerBirtDate.getText().toString());
                    Val.put("height", "");
                    Val.put("weight", "");
                    Val.put("sex", "");
                    Val.put("bloodgroup", "");
                    Val.put("living", "");
                    Val.put("maritalstatus", "");
                    Val.put("tel", "");
                    Val.put("nationality_id", NationalIDList.get(0).get("nationality_id"));
                    Val.put("house_id", etHouseID.getText().toString());
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
                    Val.put("survey_status", "0");
                    Val.put("distributor", "");
                    Val.put("cr_by", "JuJiiz");
                    Val.put("cr_date", date);
                    Val.put("upd_by", "JuJiiz");
                    Val.put("upd_date", date);
                    Val.put("ACTIVE", "Y");
                    db.InsertData("population", Val);
                    booSuccess = true;
                }
            }
        } else {
            Toast.makeText(this, "เกิดกความิดพลาดบางอย่าง", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == rbHouseOwnerYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbHouseOwnerYes, loHouseOwner);
        }
        /*if (compoundButton == rbHouseOwnerAnotherNationality) {
            ModelShowHideLayout.radiobuttonShowHide(rbHouseOwnerAnotherNationality, loHouseOwnerNationality);
        }*/
    }

    @Override
    public void onClick(View view) {
        if (view == btnAddDweller) {
            Intent intent = new Intent(this, TR14DwellerFormActivity.class);
            intent.putExtra("HouseID", etHouseID.getText().toString());
            intent.putExtra("DwellerID", "Nope");
            startActivity(intent);
        }
        if (view == btnSavingData) {
            if (!etHouseID.getText().toString().equals("") && !etHouseNo.getText().toString().equals("")) {
                insertHouse();
                if (rbHouseOwnerYes.isChecked()) {
                    booSuccess = false;
                    if (!etHouseID.getText().toString().equals("") && !etHouseNo.getText().toString().equals("")) {
                        if (!etHouseOwnerPersonalID.getText().toString().equals("")) {
                            insertOwner();
                            setListView();
                        } else {
                            Toast.makeText(this, "กรุณากรอกข้อมูลเจ้าของบ้าน", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "กรุณากรอกข้อมูลครัวเรือน", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "กรุณากรอกข้อมูลครัวเรือน", Toast.LENGTH_SHORT).show();
            }

            if (booSuccess == true) {
                //Intent intent = new Intent(this, TR14Activity.class);
                this.finish();
                //this.startActivity(intent);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == spNationality) {
            if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                loHouseOwnerNationality.setVisibility(View.VISIBLE);
            } else {
                loHouseOwnerNationality.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> Item = (HashMap<String, String>) listDweller.getItemAtPosition(position);
        SelectedIDItem = Item.get("ID").toString();

        ArrayList<HashMap<String, String>> Dweller = db.SelectWhereData("population", "population_id", SelectedIDItem);

        if (Dweller.get(0).get("dwellerstatus").equals("1")){
            Intent intent = new Intent(getApplicationContext(), TR14DwellerFormActivity.class);
            intent.putExtra("HouseID", etHouseID.getText().toString());
            intent.putExtra("DwellerID", SelectedIDItem);
            startActivity(intent);
        }else{
            Toast.makeText(this, "เจ้าของบ้าน", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
