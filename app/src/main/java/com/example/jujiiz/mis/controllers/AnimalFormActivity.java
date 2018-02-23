package com.example.jujiiz.mis.controllers;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import com.example.jujiiz.mis.models.ModelCheckForm;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AnimalFormActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    EditText etAnimalOwner, etAnimalAmount, etAnimalType, etAnimalInfection, etDate;
    RadioGroup rdAnimalRegister, rdAnimalInfection, rdAnimalShelter, rdInfectionCtrl, rdCtrlBy, rdDiseaseFree, rdAnimalMarket;
    RadioButton rbAnimalRegisterNo, rbAnimalRegisterYes, rbAnimalInfectionNo, rbAnimalInfectionYes, rbAnimalShelterIn, rbAnimalShelterOut, rbInfectionCtrlNo, rbInfectionCtrlYes, rbCtrlBySelf, rbCtrlByGovern, rbDiseaseFreeNo, rbDiseaseFreeYes, rbAnimalMarketNo, rbAnimalMarketYes;
    LinearLayout loAnimalType, loAnimalInfection, loInfectionCtrlYes,loAnimalMarket;
    Spinner spAnimalType, spAnimalMarket, spContributor;
    Button btnSavingData;

    String[] spMarketArray = {"กรุณาเลือก","ตลาดในหมู่บ้าน", "ตลาดนอกหมู่บ้าน", "พ่อค้าคนกลาง"};

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> DwellerList, OwnerList, AnimalList, TypeList;
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayList<String> Type = new ArrayList<String>();
    ArrayAdapter<String> dwellerArrayAdapter, marketArrayAdapter, typeArrayAdapter;
    String PersonID, HouseID, AnimalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_form);
        PersonID = getIntent().getExtras().getString("PersonID");
        HouseID = getIntent().getExtras().getString("HouseID");
        AnimalID = getIntent().getExtras().getString("AnimalID");

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        setSpinner();
        setField();
    }

    private void init() {
        etAnimalOwner = (EditText) findViewById(R.id.etAnimalOwner);
        etAnimalAmount = (EditText) findViewById(R.id.etAnimalAmount);
        etAnimalType = (EditText) findViewById(R.id.etAnimalType);
        etAnimalInfection = (EditText) findViewById(R.id.etAnimalInfection);
        etDate = (EditText) findViewById(R.id.etDate);

        rdAnimalRegister = (RadioGroup) findViewById(R.id.rdAnimalRegister);
        rdAnimalInfection = (RadioGroup) findViewById(R.id.rdAnimalInfection);
        rdAnimalShelter = (RadioGroup) findViewById(R.id.rdAnimalShelter);
        rdInfectionCtrl = (RadioGroup) findViewById(R.id.rdInfectionCtrl);
        rdCtrlBy = (RadioGroup) findViewById(R.id.rdCtrlBy);
        rdDiseaseFree = (RadioGroup) findViewById(R.id.rdDiseaseFree);
        rdAnimalMarket = (RadioGroup) findViewById(R.id.rdAnimalMarket);

        rbAnimalRegisterNo = (RadioButton) findViewById(R.id.rbAnimalRegisterNo);
        rbAnimalRegisterYes = (RadioButton) findViewById(R.id.rbAnimalRegisterYes);
        rbAnimalInfectionNo = (RadioButton) findViewById(R.id.rbAnimalInfectionNo);
        rbAnimalInfectionYes = (RadioButton) findViewById(R.id.rbAnimalInfectionYes);
        rbAnimalShelterIn = (RadioButton) findViewById(R.id.rbAnimalShelterIn);
        rbAnimalShelterOut = (RadioButton) findViewById(R.id.rbAnimalShelterOut);
        rbInfectionCtrlNo = (RadioButton) findViewById(R.id.rbInfectionCtrlNo);
        rbInfectionCtrlYes = (RadioButton) findViewById(R.id.rbInfectionCtrlYes);
        rbCtrlBySelf = (RadioButton) findViewById(R.id.rbCtrlBySelf);
        rbCtrlByGovern = (RadioButton) findViewById(R.id.rbCtrlByGovern);
        rbDiseaseFreeNo = (RadioButton) findViewById(R.id.rbDiseaseFreeNo);
        rbDiseaseFreeYes = (RadioButton) findViewById(R.id.rbDiseaseFreeYes);
        rbAnimalMarketNo = (RadioButton) findViewById(R.id.rbAnimalMarketNo);
        rbAnimalMarketYes = (RadioButton) findViewById(R.id.rbAnimalMarketYes);
        rbAnimalInfectionYes.setOnCheckedChangeListener(this);
        rbInfectionCtrlYes.setOnCheckedChangeListener(this);
        rbAnimalMarketYes.setOnCheckedChangeListener(this);

        loAnimalType = (LinearLayout) findViewById(R.id.loAnimalType);
        loAnimalInfection = (LinearLayout) findViewById(R.id.loAnimalInfection);
        loInfectionCtrlYes = (LinearLayout) findViewById(R.id.loInfectionCtrlYes);
        loAnimalMarket = (LinearLayout) findViewById(R.id.loAnimalMarket);

        spAnimalType = (Spinner) findViewById(R.id.spAnimalType);
        spAnimalMarket = (Spinner) findViewById(R.id.spAnimalMarket);
        spContributor = (Spinner) findViewById(R.id.spContributor);
        spAnimalType.setOnItemSelectedListener(this);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
    }

    private void setSpinner() {
        TypeList = db.SelectData("asset_animal");
        if (!TypeList.isEmpty()) {
            Type.add("กรุณาเลือก");
            for (int i = 0; i < TypeList.size(); i++) {
                if (TypeList.get(i).get("ACTIVE").equals("Y")) {
                    String strNat = TypeList.get(i).get("atype_name");
                    if (!Type.contains(strNat)) {
                        Type.add(strNat);
                    }
                }
            }
            Type.add("อื่นๆ");
            String[] spTypeArray = Type.toArray(new String[0]);
            typeArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spTypeArray, spAnimalType);
        }

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

        marketArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spMarketArray, spAnimalMarket);
    }

    private void setField() {
        OwnerList = db.SelectWhereData("population", "population_idcard", PersonID);
        etAnimalOwner.setText(OwnerList.get(0).get("firstname") + " " + OwnerList.get(0).get("lastname"));
        if (!AnimalID.equals("Nope")) {
            AnimalList = db.SelectWhereData("population_asset_animal", "animal_running", AnimalID);
            if (AnimalList.get(0).get("animal_regis").equals("0")) {
                rbAnimalRegisterNo.setChecked(true);
            } else if (AnimalList.get(0).get("animal_regis").equals("1")) {
                rbAnimalRegisterYes.setChecked(true);
            }

            etAnimalAmount.setText(AnimalList.get(0).get("animal_amount"));

            TypeList = db.SelectWhereData("asset_animal", "atype_id", AnimalList.get(0).get("atype_id"));
            int spinnerPositionType = typeArrayAdapter.getPosition(TypeList.get(0).get("atype_name"));
            spAnimalType.setSelection(spinnerPositionType);

            if (AnimalList.get(0).get("infection").equals("0")) {
                rbAnimalInfectionNo.setChecked(true);
            } else if (AnimalList.get(0).get("infection").equals("1")) {
                rbAnimalInfectionYes.setChecked(true);
                etAnimalInfection.setText(TypeList.get(0).get("infection_detail"));
            }

            if (AnimalList.get(0).get("shelter").equals("0")) {
                rbAnimalShelterIn.setChecked(true);
            } else if (AnimalList.get(0).get("shelter").equals("1")) {
                rbAnimalShelterOut.setChecked(true);
            }

            if (AnimalList.get(0).get("diseasecontrol").equals("0")) {
                rbInfectionCtrlNo.setChecked(true);
            } else if (AnimalList.get(0).get("diseasecontrol").equals("1")) {
                rbInfectionCtrlYes.setChecked(true);
                if (AnimalList.get(0).get("diseasecontrol_by").equals("1")) {
                    rbCtrlBySelf.setChecked(true);
                } else if (AnimalList.get(0).get("diseasecontrol_by").equals("2")) {
                    rbCtrlByGovern.setChecked(true);
                }
            }

            if (AnimalList.get(0).get("disease_shelter").equals("0")) {
                rbDiseaseFreeNo.setChecked(true);
            } else if (AnimalList.get(0).get("disease_shelter").equals("1")) {
                rbDiseaseFreeYes.setChecked(true);
            }

            if (AnimalList.get(0).get("market").equals("0")) {
                rbAnimalMarketNo.setChecked(true);
            } else if (AnimalList.get(0).get("market").equals("1")) {
                rbAnimalMarketYes.setChecked(true);
                int spinnerPositionMarket = marketArrayAdapter.getPosition(AnimalList.get(0).get("market_place"));
                spAnimalMarket.setSelection(spinnerPositionMarket);
            }

            int spinnerPositionContri = dwellerArrayAdapter.getPosition(AnimalList.get(0).get("distributor"));
            spContributor.setSelection(spinnerPositionContri);
        }
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());
        Boolean TypePass = true;
        TypeList = db.SelectData("asset_animal");
        if (!TypeList.isEmpty()) {
            if (spAnimalType.getSelectedItem().toString().equals("อื่นๆ")) {
                TypePass = false;
                String strAnotherType = etAnimalType.getText().toString();
                if (!strAnotherType.equals("")) {
                    if (!Type.contains(strAnotherType)) {
                        Val = new ContentValues();
                        Val.put("atype_name", strAnotherType);
                        Val.put("upd_by", "JuJiiz");
                        Val.put("upd_date", date);
                        Val.put("ACTIVE", "Y");
                        TypeList = db.SelectWhereData("asset_animal", "atype_name", "\"" + strAnotherType + "\"");
                        if (TypeList.isEmpty()) {
                            Val.put("cr_by", "JuJiiz");
                            Val.put("cr_date", date);
                            db.InsertData("asset_animal", Val);
                            TypePass = true;
                        } else {
                            db.UpdateData("asset_animal", Val, "atype_name", "\"" + strAnotherType + "\"");
                            TypePass = true;
                        }
                    } else {
                        Toast.makeText(this, "ประเภทซ้ำ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุประเภท", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //////////////////////////////////// Type Insert/Update ////////////////////////////////////

        if (TypePass == true){
            Val = new ContentValues();
            Val.put("population_idcard", PersonID);

            if (rbAnimalRegisterNo.isChecked()) {
                Val.put("animal_regis", "0");
            } else if (rbAnimalRegisterYes.isChecked()) {
                Val.put("animal_regis", "1");
            }else {
                Val.put("animal_regis", "0");
            }

            if (!etAnimalAmount.getText().toString().equals("")){
                Val.put("animal_amount", etAnimalAmount.getText().toString());
            }else {
                Val.put("animal_amount", "0");
            }


            if (spAnimalType.getSelectedItem().toString().equals("อื่นๆ")) {
                TypeList = db.SelectWhereData("asset_animal","atype_name", "\"" + etAnimalType.getText().toString() + "\"");
                Val.put("atype_id", TypeList.get(0).get("atype_id"));
            } else {
                TypeList = db.SelectWhereData("asset_animal","atype_name", "\"" + spAnimalType.getSelectedItem().toString() + "\"");
                Val.put("atype_id", TypeList.get(0).get("atype_id"));
            }

            if (rbAnimalInfectionNo.isChecked()) {
                Val.put("infection", "0");
                Val.put("infection_detail", "");
            } else if (rbAnimalInfectionYes.isChecked()) {
                Val.put("infection", "1");
                Val.put("infection_detail", etAnimalInfection.getText().toString());
            }else {
                Val.put("infection", "0");
                Val.put("infection_detail", "");
            }

            if (rbAnimalShelterIn.isChecked()) {
                Val.put("shelter", "0");
            } else if (rbAnimalShelterOut.isChecked()) {
                Val.put("shelter", "1");
            }else {
                Val.put("shelter", "0");
            }

            if (rbInfectionCtrlNo.isChecked()) {
                Val.put("diseasecontrol", "0");
                Val.put("diseasecontrol_by", "0");
            } else if (rbInfectionCtrlYes.isChecked()) {
                Val.put("diseasecontrol", "1");
                if (rbCtrlBySelf.isChecked()) {
                    Val.put("diseasecontrol_by", "1");
                } else if (rbCtrlByGovern.isChecked()) {
                    Val.put("diseasecontrol_by", "2");
                }else {
                    Val.put("diseasecontrol_by", "0");
                }
            }else {
                Val.put("diseasecontrol", "0");
                Val.put("diseasecontrol_by", "0");
            }

            if (rbDiseaseFreeNo.isChecked()) {
                Val.put("disease_shelter", "0");
            } else if (rbDiseaseFreeYes.isChecked()) {
                Val.put("disease_shelter", "1");
            }else {
                Val.put("disease_shelter", "0");
            }

            if (rbAnimalMarketNo.isChecked()) {
                Val.put("market", "0");
                Val.put("market_place", "0");
            } else if (rbAnimalMarketYes.isChecked()) {
                Val.put("market", "1");
                Val.put("market_place", spAnimalMarket.getSelectedItemPosition());
            }else {
                Val.put("market", "0");
                Val.put("market_place", "0");
            }

            Val.put("distributor", spContributor.getSelectedItem().toString());
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            if (AnimalID.equals("Nope")) {
                Log.d("MYLOG", "AnimalID: "+AnimalID);
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_asset_animal", Val);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population",Val,"population_idcard",PersonID);
            } else {
                AnimalList = db.SelectWhereData("population_asset_animal", "animal_running", AnimalID);
                if (AnimalList.isEmpty()) {
                    Log.d("MYLOG", "AnimalList: "+AnimalList);
                    Val.put("cr_by", "JuJiiz");
                    Val.put("cr_date", date);
                    db.InsertData("population_asset_animal", Val);
                    Val = new ContentValues();
                    Val.put("upload_status", "1");
                    db.UpdateData("population",Val,"population_idcard",PersonID);
                } else {
                    db.UpdateData("population_asset_animal", Val, "animal_running", AnimalID);
                    Val = new ContentValues();
                    Val.put("upload_status", "1");
                    db.UpdateData("population",Val,"population_idcard",PersonID);
                }
            }
            Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSavingData){
            if (fieldCheck() == 0){
                updateData();
                Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                this.finish();
            } else if (fieldCheck() == 1){
                Toast.makeText(this, "กรุณาระบุ \"ชนิดสัตว์เลี้ยง\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 2){
                Toast.makeText(this, "กรุณาระบุ \"การควบคุมโรค\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 3){
                Toast.makeText(this, "กรุณาระบุ \"การติดโรค\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 4){
                Toast.makeText(this, "กรุณาระบุ \"ตลาดในการซื้อขาย\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 5){
                Toast.makeText(this, "กรุณาระบุ \"ชื่อผู้ให้ข้อมูล\"", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int fieldCheck(){
        int formPass = 0;
        Boolean typePass = true,//1
                infectctrlPass = true,//2
                infectPass = true,//3
                marketPass = true,//4
                conPass = true;//5

        typePass = ModelCheckForm.checkSpinner(spAnimalType);
        conPass = ModelCheckForm.checkSpinner(spContributor);

        if(rbInfectionCtrlYes.isChecked()){
            if (!rbCtrlBySelf.isChecked() && !rbCtrlByGovern.isChecked()){
                infectctrlPass = false;
            }
        }

        if (rbAnimalInfectionYes.isChecked()){
            infectPass = ModelCheckForm.checkEditText(etAnimalInfection);
        }

        if (rbAnimalMarketYes.isChecked()){
            marketPass = ModelCheckForm.checkSpinner(spAnimalMarket);
        }

        if (typePass == true ){
            if (infectctrlPass == true ){
                if (infectPass == true ){
                    if (marketPass == true ){
                        if (conPass == true ){
                            formPass = 0;
                        }else{
                            formPass = 5;
                        }
                    }else{
                        formPass = 4;
                    }
                }else{
                    formPass = 3;
                }
            }else{
                formPass = 2;
            }
        }else{
            formPass = 1;
        }

        return formPass;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == rbAnimalInfectionYes)
            ModelShowHideLayout.radiobuttonShowHide(rbAnimalInfectionYes, loAnimalInfection);
        if (buttonView == rbInfectionCtrlYes)
            ModelShowHideLayout.radiobuttonShowHide(rbInfectionCtrlYes, loInfectionCtrlYes);
        if (buttonView == rbAnimalMarketYes)
            ModelShowHideLayout.radiobuttonShowHide(rbAnimalMarketYes, loAnimalMarket);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == spAnimalType) {
            if (spAnimalType.getSelectedItem().toString().equals("อื่นๆ")) {
                loAnimalType.setVisibility(View.VISIBLE);
            } else {
                loAnimalType.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
