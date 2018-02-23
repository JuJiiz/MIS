package com.example.jujiiz.mis.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    Button btnSavingData, btnImagePick;
    ImageView ivImage;
    ImageButton btnCameraPick;

    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 1;

    String[] spMonthArray = {"กรุณาเลือก","มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
    String[] spSterileArray = {"กรุณาเลือก","ทำหมันแล้ว", "ยังไม่ทำหมัน", "ไม่ทราบ", "ฉีดยาคุม"};

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> DwellerList, OwnerList, PetList;
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayAdapter<String> dwellerArrayAdapter, monthArrayAdapter, sterileArrayAdapter;
    String PersonID, HouseID, PetID;
    Bitmap imgBitmap;
    byte[] imgByteArray = null;

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
        rbPetSexMale.setOnCheckedChangeListener(this);
        rbPetSexFemale.setOnCheckedChangeListener(this);

        loVaccineContinue = (LinearLayout) findViewById(R.id.loVaccineContinue);
        loLastVaccine = (LinearLayout) findViewById(R.id.loLastVaccine);
        loPetBorn = (LinearLayout) findViewById(R.id.loPetBorn);

        spSterile = (Spinner) findViewById(R.id.spSterile);
        spLastVaccine = (Spinner) findViewById(R.id.spLastVaccine);
        spContributor = (Spinner) findViewById(R.id.spContributor);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
        btnImagePick = (Button) findViewById(R.id.btnImagePick);
        btnImagePick.setOnClickListener(this);
        btnCameraPick = (ImageButton) findViewById(R.id.btnCameraPick);
        btnCameraPick.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.ivImage);
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

            if (!PetList.get(0).get("pet_img").equals("")) {
                imgByteArray = Base64.decode(PetList.get(0).get("pet_img"), Base64.DEFAULT);
                imgBitmap = BitmapFactory.decodeByteArray(imgByteArray, 0, imgByteArray.length);
                ivImage.setImageBitmap(imgBitmap);
                ivImage.setVisibility(View.VISIBLE);
            }

            etPetAmount.setText(PetList.get(0).get("pet_amount"));

            if (PetList.get(0).get("pet_type").equals("1")) {
                rbPetTypeDog.setChecked(true);
            } else if (PetList.get(0).get("pet_type").equals("2")) {
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
                if (PetList.get(0).get("vaccine_during").equals("1")) {
                    rbVaccineContinueNo.setChecked(true);
                } else if (PetList.get(0).get("vaccine_during").equals("2")) {
                    rbVaccineContinueYes.setChecked(true);
                }
                int spinnerPositionMonth = monthArrayAdapter.getPosition(PetList.get(0).get("vaccine_lastest"));
                spLastVaccine.setSelection(spinnerPositionMonth);
            }

            int spinnerPositionSterile = sterileArrayAdapter.getPosition(PetList.get(0).get("sterile"));
            spSterile.setSelection(spinnerPositionSterile);

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
            Val.put("pet_regis", "0");
        }

        if (ivImage.getDrawable() != null) {
            String imgString = Base64.encodeToString(imgByteArray, Base64.NO_WRAP);
            Val.put("pet_img", imgString);
        } else {
            Val.put("pet_img", "");
        }

        Val.put("pet_amount", etPetAmount.getText().toString());

        if (rbPetTypeDog.isChecked()) {
            Val.put("pet_type", "1");
        } else if (rbPetTypeCat.isChecked()) {
            Val.put("pet_type", "2");
        } else {
            Val.put("pet_type", "1");
        }

        if (rbPetSexMale.isChecked()) {
            Val.put("pet_sex", "M");
        } else if (rbPetSexFemale.isChecked()) {
            Val.put("pet_sex", "F");
        } else {
            Val.put("pet_sex", "M");
        }

        if (rbPetVaccineNo.isChecked()) {
            Val.put("vaccine", "0");
            Val.put("vaccine_during", "0");
            Val.put("vaccine_lastest", "");
        } else if (rbPetVaccineYes.isChecked()) {
            Val.put("vaccine", "1");
            if (rbVaccineContinueNo.isChecked()) {
                Val.put("vaccine_during", "1");
            } else if (rbVaccineContinueYes.isChecked()) {
                Val.put("vaccine_during", "2");
            } else {
                Val.put("vaccine_during", "0");
            }
            Val.put("vaccine_lastest", spLastVaccine.getSelectedItem().toString());
        } else {
            Val.put("vaccine", "0");
            Val.put("vaccine_during", "0");
            Val.put("vaccine_lastest", "");
        }

        Val.put("sterile", spSterile.getSelectedItem().toString());

        if (rbPetBornNo.isChecked()) {
            Val.put("pet_newborn", "0");
            Val.put("pet_newborn_number", "0");
        } else if (rbPetBornYes.isChecked()) {
            Val.put("pet_newborn", "1");
            Val.put("pet_newborn_number", etPetBorn.getText().toString());
        } else {
            Val.put("pet_newborn", "0");
            Val.put("pet_newborn_number", "0");
        }

        Val.put("distributor", spContributor.getSelectedItem().toString());
        Val.put("upd_by", "");
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        if (PetID.equals("Nope")) {
            Val.put("cr_by", "JuJiiz");
            Val.put("cr_date", date);
            db.InsertData("population_asset_pet", Val);
            Val = new ContentValues();
            Val.put("upload_status", "1");
            db.UpdateData("population",Val,"population_idcard",PersonID);
        } else {
            PetList = db.SelectWhereData("population_asset_pet", "pet_running", PetID);
            if (PetList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_asset_pet", Val);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population",Val,"population_idcard",PersonID);
            } else {
                db.UpdateData("population_asset_pet", Val, "pet_running", PetID);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population",Val,"population_idcard",PersonID);
            }
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                imgBitmap = BitmapFactory.decodeStream(imageStream);
                ivImage.setImageBitmap(imgBitmap);
                ivImage.setVisibility(View.VISIBLE);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                imgBitmap.compress(Bitmap.CompressFormat.JPEG, 10, outputStream);
                imgByteArray = outputStream.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }
        if (reqCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivImage.setImageBitmap(photo);
            ivImage.setVisibility(View.VISIBLE);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 10, outputStream);
            imgByteArray = outputStream.toByteArray();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSavingData) {
            if (fieldCheck() == 0){
                updateData();
                Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                this.finish();
            } else if (fieldCheck() == 1){
                Toast.makeText(this, "กรุณาระบุ \"ประเภทสัตว์เลี้ยงในครัวเรือน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 2){
                Toast.makeText(this, "กรุณาระบุ \"เพศ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 3){
                Toast.makeText(this, "กรุณาระบุ \"การฉีดวัคซีน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 4){
                Toast.makeText(this, "กรุณาระบุ \"จำนวนสัตว์เกิดใหม่ต่อปี\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 5){
                Toast.makeText(this, "กรุณาระบุ \"การทำหมัน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 6){
                Toast.makeText(this, "กรุณาระบุ \"ชื่อผู้ให้ข้อมูล\"", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == btnImagePick) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        }
        if (v == btnCameraPick) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private int fieldCheck(){
        int formPass = 0;
        Boolean typePass = true,//1
                sexPass = true,//2
                vacconPass = true,//3
                bornPass = true,//4
                sterilePass = true,//5
                conPass = true;//6

        if (!rbPetTypeDog.isChecked() && !rbPetTypeCat.isChecked()){
            typePass = false;
        }

        if (!rbPetSexMale.isChecked() && !rbPetSexFemale.isChecked()){
            sexPass = false;
        }

        if(rbPetVaccineYes.isChecked()){
            if (!rbVaccineContinueNo.isChecked() && !rbVaccineContinueYes.isChecked()){
                vacconPass = false;
            }
        }

        if(rbPetVaccineYes.isChecked()){
            if (ModelCheckForm.checkSpinner(spLastVaccine) != true){
                vacconPass = false;
            }
        }

        if(rbPetBornYes.isChecked()){
            if (ModelCheckForm.checkEditText(etPetBorn) != true){
                bornPass = false;
            }
        }

        sterilePass = ModelCheckForm.checkSpinner(spSterile);
        conPass = ModelCheckForm.checkSpinner(spContributor);

        if (typePass == true){
            if (sexPass == true){
                if (vacconPass == true){
                    if (bornPass == true){
                        if (sterilePass == true){
                            if (conPass == true){
                                formPass = 0;
                            }else{
                                formPass = 6;
                            }
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
        if (buttonView == rbPetVaccineYes){
            ModelShowHideLayout.radiobuttonShowHide(rbPetVaccineYes, loVaccineContinue);
            ModelShowHideLayout.radiobuttonShowHide(rbPetVaccineYes, loLastVaccine);
        }
        if (buttonView == rbPetBornYes)
            ModelShowHideLayout.radiobuttonShowHide(rbPetBornYes, loPetBorn);
        if (buttonView == rbPetSexMale){
            rbPetBornYes.setEnabled(false);
            rbPetBornNo.setEnabled(false);
        }
        if (buttonView == rbPetSexFemale){
            rbPetBornYes.setEnabled(true);
            rbPetBornNo.setEnabled(true);
        }
    }
}
