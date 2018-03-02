package com.example.jujiiz.mis.controllers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.CustomDialog;
import com.example.jujiiz.mis.models.ModelCheckForm;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class VehicalFormActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    EditText etOwnerName, etOwnerPersonalID, etRegisterDate, etDate;
    RadioGroup rdVehicalType, rdRent;
    RadioButton rbVehicalType1, rbVehicalType2, rbVehicalType3, rbVehicalType4, rbVehicalType5, rbRentNo, rbRentYes;
    LinearLayout loVehicalType1, loVehicalType2, loVehicalType3, loVehicalType4, loVehicalType5;
    Spinner spVehicalType1, spVehicalType2, spVehicalType3, spVehicalType4, spVehicalType5, spContributor;
    Button btnSavingData, btnDatePick, btnImagePick;
    ImageView ivImage;
    ImageButton btnCameraPick;

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
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
    Bitmap imgBitmap;
    byte[] imgByteArray = null;

    private static final int CAMERA_REQUEST = 1888;
    private static final int GALLERY_REQUEST = 1;

    private DatePickerDialog fromDatePickerDialog;

    ArrayList<String> data;
    String[] date28 = new String[28],
            date29 = new String[29],
            date31 = new String[31],
            date30 = new String[30],
            month = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"},
            year = new String[300];

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_form);
        PersonID = getIntent().getExtras().getString("PersonID");
        HouseID = getIntent().getExtras().getString("HouseID");
        VehicleID = getIntent().getExtras().getString("VehicleID");

        SharedPreferences sp = VehicalFormActivity.this.getSharedPreferences("UserMemo", Context.MODE_PRIVATE);
        username = sp.getString("username", "");

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        setSpinner();
        setField();
        setDateTimeField();
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
        btnDatePick = (Button) findViewById(R.id.btnDatePick);
        btnDatePick.setOnClickListener(this);
        btnImagePick = (Button) findViewById(R.id.btnImagePick);
        btnImagePick.setOnClickListener(this);
        btnCameraPick = (ImageButton) findViewById(R.id.btnCameraPick);
        btnCameraPick.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.ivImage);
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year + 543, monthOfYear, dayOfMonth);
                etRegisterDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR) + 543, newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //Log.d("MYLOG", "setDateTimeField: " + Calendar.YEAR);

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

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะทั่วไป" + "\"");
        if (!VTypeList.isEmpty()) {
            Type1.add("กรุณาเลือก");
            for (int i = 0; i < VTypeList.size(); i++) {
                Type1.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType1 = Type1.toArray(new String[0]);
            AdapterType1 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType1, spVehicalType1);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน" + "\"");
        if (!VTypeList.isEmpty()) {
            Type2.add("กรุณาเลือก");
            for (int i = 0; i < VTypeList.size(); i++) {
                Type2.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType2 = Type2.toArray(new String[0]);
            AdapterType2 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType2, spVehicalType2);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านบรรเทาสาธารณภัย" + "\"");
        if (!VTypeList.isEmpty()) {
            Type3.add("กรุณาเลือก");
            for (int i = 0; i < VTypeList.size(); i++) {
                Type3.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType3 = Type3.toArray(new String[0]);
            AdapterType3 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType3, spVehicalType3);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านสิ่งแวดล้อม" + "\"");
        if (!VTypeList.isEmpty()) {
            Type4.add("กรุณาเลือก");
            for (int i = 0; i < VTypeList.size(); i++) {
                Type4.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType4 = Type4.toArray(new String[0]);
            AdapterType4 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType4, spVehicalType4);
        }

        VTypeList = db.SelectWhereData("asset_vehicle", "vtype_name", "\"" + "ประเภทยานพาหนะด้านการศึกษา" + "\"");
        if (!VTypeList.isEmpty()) {
            Type5.add("กรุณาเลือก");
            for (int i = 0; i < VTypeList.size(); i++) {
                Type5.add(VTypeList.get(i).get("vtype_detail"));
            }
            String[] spArrayType5 = Type5.toArray(new String[0]);
            AdapterType5 = ModelSpinnerAdapter.setSpinnerItem(this, spArrayType5, spVehicalType5);
        }
    }

    private void setField() {
        DateFormat originalFormat;
        DateFormat targetFormat;
        Date odate = null;
        String formattedDate = "0001-01-01";
        OwnerList = db.SelectWhereData("population", "population_idcard", PersonID);
        etOwnerName.setText(OwnerList.get(0).get("firstname") + " " + OwnerList.get(0).get("lastname"));
        etOwnerPersonalID.setText(OwnerList.get(0).get("population_idcard"));
        if (!VehicleID.equals("Nope")) {
            VehicleList = db.SelectWhereData("population_asset_vehicle", "vehicle_running", VehicleID);
            originalFormat = new SimpleDateFormat("yyyy-MM-dd");
            targetFormat = new SimpleDateFormat("dd/MM/yyy");
            try {
                odate = originalFormat.parse(VehicleList.get(0).get("regisdate"));
                if (odate != null) {
                    formattedDate = targetFormat.format(odate);
                } else {
                    formattedDate = "01/01/0001";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            etRegisterDate.setText(formattedDate);

            if (!VehicleList.get(0).get("vehical_img").equals("")) {
                imgByteArray = Base64.decode(VehicleList.get(0).get("vehical_img"), Base64.DEFAULT);
                imgBitmap = BitmapFactory.decodeByteArray(imgByteArray, 0, imgByteArray.length);
                ivImage.setImageBitmap(imgBitmap);
                ivImage.setVisibility(View.VISIBLE);
            }

            VTypeList = db.SelectWhereData("asset_vehicle", "vtype_id", VehicleList.get(0).get("vtype_id"));
            if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะทั่วไป")) {
                rbVehicalType1.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType1.setSelection(spinnerPositionType);
            } else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน")) {
                rbVehicalType2.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType2.setSelection(spinnerPositionType);
            } else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านบรรเทาสาธารณภัย")) {
                rbVehicalType3.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType3.setSelection(spinnerPositionType);
            } else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านสิ่งแวดล้อม")) {
                rbVehicalType4.setChecked(true);
                int spinnerPositionType = AdapterType1.getPosition(VTypeList.get(0).get("vtype_detail"));
                spVehicalType4.setSelection(spinnerPositionType);
            } else if (VTypeList.get(0).get("vtype_name").equals("ประเภทยานพาหนะด้านการศึกษา")) {
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
        DateFormat originalFormat;
        DateFormat targetFormat;
        Date odate = null;
        String formattedDate = "0001-01-01";
        String date = df.format(Calendar.getInstance().getTime());
        Val = new ContentValues();
        Val.put("population_idcard", PersonID);
        originalFormat = new SimpleDateFormat("dd/MM/yyy");
        targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            odate = originalFormat.parse(etRegisterDate.getText().toString());
            if (odate != null) {
                formattedDate = targetFormat.format(odate);
            } else {
                formattedDate = "0001-01-01";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Val.put("regisdate", formattedDate);

        if (ivImage.getDrawable() != null) {
            String imgString = Base64.encodeToString(imgByteArray, Base64.NO_WRAP);
            Val.put("vehical_img", imgString);
        } else {
            Val.put("vehical_img", "");
        }

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
            Val.put("vtype_id", "1");
        }

        if (rbRentYes.isChecked()) {
            Val.put("vehical_rent", "1");
        } else if (rbRentNo.isChecked()) {
            Val.put("vehical_rent", "0");
        } else {
            Val.put("vehical_rent", "0");
        }
        Val.put("distributor", spContributor.getSelectedItem().toString());
        Val.put("upd_by", username);
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        if (VehicleID.equals("Nope")) {
            Val.put("cr_by", username);
            Val.put("cr_date", date);
            db.InsertData("population_asset_vehicle", Val);
            Val = new ContentValues();
            Val.put("upload_status", "1");
            db.UpdateData("population", Val, "population_idcard", PersonID);
        } else {
            VehicleList = db.SelectWhereData("population_asset_vehicle", "vehicle_running", VehicleID);
            if (VehicleList.isEmpty()) {
                Val.put("cr_by", username);
                Val.put("cr_date", date);
                db.InsertData("population_asset_vehicle", Val);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population", Val, "population_idcard", PersonID);
            } else {
                db.UpdateData("population_asset_vehicle", Val, "vehicle_running", VehicleID);
                Val = new ContentValues();
                Val.put("upload_status", "1");
                db.UpdateData("population", Val, "population_idcard", PersonID);
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
                Toast.makeText(this, "กรุณาระบุ \"ประเภทยานพาหนะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 2) {
                Toast.makeText(this, "กรุณาระบุ \"ประเภทยานพาหนะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 3) {
                Toast.makeText(this, "กรุณาระบุ \"ประเภทยานพาหนะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 4) {
                Toast.makeText(this, "กรุณาระบุ \"ประเภทยานพาหนะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 5) {
                Toast.makeText(this, "กรุณาระบุ \"ประเภทยานพาหนะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 6) {
                Toast.makeText(this, "กรุณาระบุ \"ประเภทยานพาหนะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 7) {
                Toast.makeText(this, "กรุณาระบุ \"วันที่จดทะเบียน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 8) {
                Toast.makeText(this, "กรุณาระบุ \"ชื่อผู้ให้ข้อมูล\"", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == btnDatePick) {
            //fromDatePickerDialog.show();
            CustomDialog.DatePickerDialog(VehicalFormActivity.this, etRegisterDate);
        }
        if (v == btnImagePick) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        }
        if (v == btnCameraPick) {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private int fieldCheck() {
        int formPass = 0;
        Boolean typePass = true,//1
                type1Pass = true,//2
                type2Pass = true,//3
                type3Pass = true,//4
                type4Pass = true,//5
                type5Pass = true,//6
                datePass = true,//7
                conPass = true;//8

        if (!rbVehicalType1.isChecked() && !rbVehicalType2.isChecked() && !rbVehicalType3.isChecked() && !rbVehicalType4.isChecked() && !rbVehicalType5.isChecked()) {
            typePass = false;
        }

        if (rbVehicalType1.isChecked()) {
            type1Pass = ModelCheckForm.checkSpinner(spVehicalType1);
        }

        if (rbVehicalType2.isChecked()) {
            type2Pass = ModelCheckForm.checkSpinner(spVehicalType2);
        }

        if (rbVehicalType3.isChecked()) {
            type3Pass = ModelCheckForm.checkSpinner(spVehicalType3);
        }

        if (rbVehicalType4.isChecked()) {
            type4Pass = ModelCheckForm.checkSpinner(spVehicalType4);
        }

        if (rbVehicalType5.isChecked()) {
            type5Pass = ModelCheckForm.checkSpinner(spVehicalType5);
        }

        datePass = ModelCheckForm.checkEditText(etDate);

        conPass = ModelCheckForm.checkSpinner(spContributor);

        /*if ( &&  == true &&  == true &&  == true &&  == true &&  == true &&  == true &&  == true){
            formPass = true;
        }else{

        }*/

        if (typePass == true) {
            if (type1Pass == true) {
                if (type2Pass == true) {
                    if (type3Pass == true) {
                        if (type4Pass == true) {
                            if (type5Pass == true) {
                                if (datePass == true) {
                                    if (conPass == true) {
                                        formPass = 0;
                                    } else {
                                        formPass = 8;
                                    }
                                } else {
                                    formPass = 7;
                                }
                            } else {
                                formPass = 6;
                            }
                        } else {
                            formPass = 5;
                        }
                    } else {
                        formPass = 4;
                    }
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
