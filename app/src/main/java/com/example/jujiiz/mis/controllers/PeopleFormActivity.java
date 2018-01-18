package com.example.jujiiz.mis.controllers;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCheckboxCheck;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class PeopleFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    RadioButton rbMale, rbFemale;
    RadioButton rbAlive, rbDead;
    RadioButton rbInRegister, rbNotInRegister;
    RadioButton rbInHousehold, rbNotInHousehold;
    RadioButton rbStatusOwner, rbStatusDweller;
    RadioButton rbJGStudent, rbJGCareer, rbJGNoJob;
    RadioButton rbICMonth, rbICYear;
    RadioButton rbDebtNo, rbDebtYes;
    RadioButton rbSavingNo, rbSavingYes;
    RadioButton rbCongenitalNo, rbCongenitalYes;
    RadioButton rbContagiousNo, rbContagiousYes;
    RadioButton rbAllergicNo, rbAllergicYes;
    RadioButton rbDisabledNo, rbDisabledYes;
    RadioButton rbDisadvantageNo, rbDisadvantageYes;
    RadioButton rbSubAlNo, rbSubAlYes;
    RadioButton rbNoStudy, rbInStudy, rbGraduated;
    RadioButton rbLiteracyYes, rbLiteracyNo;
    RadioButton rbTechnologyNo, rbTechnologyYes;
    RadioButton rbExpertiseNo, rbExpertiseYes;
    RadioButton rbBuddhismReligion, rbChristReligion, rbIslamicReligion, rbAnotherReligion;
    RadioButton rbParticipationNo, rbParticipationYes;
    RadioButton rbElectionAlway, rbElectionSometime, rbElectionNever;
    RadioButton rbTransportationNo, rbTransportationYes;
    LinearLayout loNationality, loAnotherPrefix, loBloodType, loInRegister, loNotInRegister, loNotInHousehold, loCareer, loAgri, loAnotherAgri, loPet, loAnotherPet, loGovern, loAnotherGovern, loPrivate, loAnotherPrivate, loICMonth, loICYear, loCongenital, loContagious, loAllergic, loDisabled, loInStudy, loGraduated, loExpertise, loAnotherReligion, loTransportation, loExpertiseText, loAnotherCong, loAnotherCont, loProperty;
    Spinner spNationality, spPrefix, spBloodType, spMaritalStatus, spVillageName, spInStudy, spGraduated, spExpertise, spContributor;
    EditText etNationality, etFirstName, etLastName, etAnotherPrefix, etPersonalID, etBirtDate, etHeight, etWeight, etBloodType, etTel, etHNo, etHID, etAnotherAgri, etAnotherPet, etAnotherGovern, etAnotherPrivate, etICMonth, etICYear, etAllergic, etAnotherReligion, etDate, etIHCountry, etIHProvince, etIRCountry, etIRProvince, etExpertise, etAnotherCong, etAnotherCont;
    CheckBox cbAgri, cbAgri1, cbAgri2, cbAgri3, cbAgri4, cbAgri5, cbAgri6, cbAgri7, cbAgri8;
    CheckBox cbPet, cbPet1, cbPet2, cbPet3, cbPet4, cbPet5, cbPet6, cbPet7, cbPet8, cbPet9;
    CheckBox cbGovern, cbGovern1, cbGovern2, cbGovern3, cbGovern4, cbGovern5;
    CheckBox cbPrivate, cbPrivate1, cbPrivate2, cbPrivate3, cbPrivate4, cbPrivate5, cbPrivate6, cbPrivate7;
    CheckBox cbCong1, cbCong2, cbCong3, cbCong4, cbCong5;
    CheckBox cbCont1, cbCont2, cbCont3, cbCont4, cbCont5, cbCont6, cbCont7, cbCont8, cbCont9, cbCont10, cbCont11;
    CheckBox cbDisabled1, cbDisabled2, cbDisabled3, cbDisabled4, cbDisabled5, cbDisabled6;
    CheckBox cbTrans1, cbTrans2, cbTrans3, cbTrans4;
    Button btnSavingData, btnAddProperty;
    ListView lvProperty;

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> NationalityList, PrefixList, PersonList, TestList, DwellerList, WorkList, AgriList, PetList, GovernList, PrivateList, CongList, ContList, DisabledList, TransList;
    ArrayList<HashMap<String, String>> PropertyActive, PLandList, PVehicleList, PPetList, PAnimalList;
    ArrayList<String> Prefix = new ArrayList<String>();
    ArrayList<String> Nationality = new ArrayList<String>();
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayAdapter<String> prefixArrayAdapter, nationalityArrayAdapter, dwellerArrayAdapter, bloodArrayAdapter, maritalArrayAdapter, educationIArrayAdapter, educationGArrayAdapter, expertiseArrayAdapter;
    String PersonID, HouseID;
    String SelectedIDItem;

    String[] spBloodGroupArray = {"O", "A", "B", "AB", "อื่นๆ"};
    String[] spMaritalStatusArray = {"สมรส", "โสด", "หย่าร้าง", "หม้าย", "แยกกันอยู่"};
    String[] spEducationArray = {"ระดับก่อนประถมศึกษา", "ระดับประถมศึกษา", "ระดับมัธยมศึกษาตอนต้น", "ระดับมัธยมศึกษาตอนปลาย", "ระดับอนุปริญญา", "ระดับปริญญาตรี", "ระดับปริญญาโท", "ระดับปริญญาเอก"};
    String[] spExpertiseArray = {"สาขาการเกษตรและพัฒนาชนบท", "สาขาอุตสาหกรรมก่อสร้าง", "สาขาการศึกษา", "สาขาพลังงาน", "สาขาสิ่งแวดล้อม", "สาขาการเงิน", "สาขาสาธารณสุข", "สาขาอุตสาหกรรม", "สาขาเบ็ดเตล็ด", "สาขาประชากร", "สาขาเทคโนโลยีสารสนเทศและการสื่อสาร", "สาขาการท่องเที่ยว", "สาขาการขนส่ง", "สาขาการพัฒนาเมือง", "สาขาการประปาและสุขาภิบาล", "สาขาภาษาต่างประเทศ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_form);
        PersonID = getIntent().getExtras().getString("PersonID");
        HouseID = getIntent().getExtras().getString("HouseID");

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        setSpinner();
        setField();
        setListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }

    private void init() {
        lvProperty = (ListView) findViewById(R.id.lvProperty);
        lvProperty.setOnItemClickListener(this);
        lvProperty.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
        btnAddProperty = (Button) findViewById(R.id.btnAddProperty);
        btnAddProperty.setOnClickListener(this);

        loNationality = (LinearLayout) findViewById(R.id.loNationality);
        loAnotherPrefix = (LinearLayout) findViewById(R.id.loAnotherPrefix);
        loBloodType = (LinearLayout) findViewById(R.id.loBloodType);
        loInRegister = (LinearLayout) findViewById(R.id.loInRegister);
        loNotInRegister = (LinearLayout) findViewById(R.id.loNotInRegister);
        loNotInHousehold = (LinearLayout) findViewById(R.id.loNotInHousehold);
        loCareer = (LinearLayout) findViewById(R.id.loCareer);
        loAgri = (LinearLayout) findViewById(R.id.loAgri);
        loAnotherAgri = (LinearLayout) findViewById(R.id.loAnotherAgri);
        loPet = (LinearLayout) findViewById(R.id.loPet);
        loAnotherPet = (LinearLayout) findViewById(R.id.loAnotherPet);
        loGovern = (LinearLayout) findViewById(R.id.loGovern);
        loAnotherGovern = (LinearLayout) findViewById(R.id.loAnotherGovern);
        loPrivate = (LinearLayout) findViewById(R.id.loPrivate);
        loAnotherPrivate = (LinearLayout) findViewById(R.id.loAnotherPrivate);
        loICMonth = (LinearLayout) findViewById(R.id.loICMonth);
        loICYear = (LinearLayout) findViewById(R.id.loICYear);
        loCongenital = (LinearLayout) findViewById(R.id.loCongenital);
        loContagious = (LinearLayout) findViewById(R.id.loContagious);
        loAllergic = (LinearLayout) findViewById(R.id.loAllergic);
        loDisabled = (LinearLayout) findViewById(R.id.loDisabled);
        loInStudy = (LinearLayout) findViewById(R.id.loInStudy);
        loGraduated = (LinearLayout) findViewById(R.id.loGraduated);
        loExpertise = (LinearLayout) findViewById(R.id.loExpertise);
        loExpertiseText = (LinearLayout) findViewById(R.id.loExpertiseText);
        loAnotherReligion = (LinearLayout) findViewById(R.id.loAnotherReligion);
        loTransportation = (LinearLayout) findViewById(R.id.loTransportation);
        loAnotherCong = (LinearLayout) findViewById(R.id.loAnotherCong);
        loAnotherCont = (LinearLayout) findViewById(R.id.loAnotherCont);
        loProperty = (LinearLayout) findViewById(R.id.loProperty);

        spNationality = (Spinner) findViewById(R.id.spNationality);
        spNationality.setOnItemSelectedListener(this);
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
        spBloodType = (Spinner) findViewById(R.id.spBloodType);
        spMaritalStatus = (Spinner) findViewById(R.id.spMaritalStatus);
        spVillageName = (Spinner) findViewById(R.id.spVillageName);
        spInStudy = (Spinner) findViewById(R.id.spInStudy);
        spGraduated = (Spinner) findViewById(R.id.spGraduated);
        spExpertise = (Spinner) findViewById(R.id.spExpertise);
        spContributor = (Spinner) findViewById(R.id.spContributor);

        etNationality = (EditText) findViewById(R.id.etNationality);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etAnotherPrefix = (EditText) findViewById(R.id.etAnotherPrefix);
        etPersonalID = (EditText) findViewById(R.id.etPersonalID);
        etBirtDate = (EditText) findViewById(R.id.etBirtDate);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etBloodType = (EditText) findViewById(R.id.etBloodType);
        etTel = (EditText) findViewById(R.id.etTel);
        etHNo = (EditText) findViewById(R.id.etHNo);
        etHID = (EditText) findViewById(R.id.etHID);
        etAnotherAgri = (EditText) findViewById(R.id.etAnotherAgri);
        etAnotherPet = (EditText) findViewById(R.id.etAnotherPet);
        etAnotherGovern = (EditText) findViewById(R.id.etAnotherGovern);
        etAnotherPrivate = (EditText) findViewById(R.id.etAnotherPrivate);
        etICMonth = (EditText) findViewById(R.id.etICMonth);
        etICYear = (EditText) findViewById(R.id.etICYear);
        etAllergic = (EditText) findViewById(R.id.etAllergic);
        etAnotherReligion = (EditText) findViewById(R.id.etAnotherReligion);
        etDate = (EditText) findViewById(R.id.etDate);
        etIHCountry = (EditText) findViewById(R.id.etIHCountry);
        etIHProvince = (EditText) findViewById(R.id.etIHProvince);
        etIRCountry = (EditText) findViewById(R.id.etIRCountry);
        etIRProvince = (EditText) findViewById(R.id.etIRProvince);
        etExpertise = (EditText) findViewById(R.id.etExpertise);
        etAnotherCong = (EditText) findViewById(R.id.etAnotherCong);
        etAnotherCont = (EditText) findViewById(R.id.etAnotherCont);

        cbAgri = (CheckBox) findViewById(R.id.cbAgri);
        cbAgri1 = (CheckBox) findViewById(R.id.cbAgri1);
        cbAgri2 = (CheckBox) findViewById(R.id.cbAgri2);
        cbAgri3 = (CheckBox) findViewById(R.id.cbAgri3);
        cbAgri4 = (CheckBox) findViewById(R.id.cbAgri4);
        cbAgri5 = (CheckBox) findViewById(R.id.cbAgri5);
        cbAgri6 = (CheckBox) findViewById(R.id.cbAgri6);
        cbAgri7 = (CheckBox) findViewById(R.id.cbAgri7);
        cbAgri8 = (CheckBox) findViewById(R.id.cbAgri8);
        cbPet = (CheckBox) findViewById(R.id.cbPet);
        cbPet1 = (CheckBox) findViewById(R.id.cbPet1);
        cbPet2 = (CheckBox) findViewById(R.id.cbPet2);
        cbPet3 = (CheckBox) findViewById(R.id.cbPet3);
        cbPet4 = (CheckBox) findViewById(R.id.cbPet4);
        cbPet5 = (CheckBox) findViewById(R.id.cbPet5);
        cbPet6 = (CheckBox) findViewById(R.id.cbPet6);
        cbPet7 = (CheckBox) findViewById(R.id.cbPet7);
        cbPet8 = (CheckBox) findViewById(R.id.cbPet8);
        cbPet9 = (CheckBox) findViewById(R.id.cbPet9);
        cbGovern = (CheckBox) findViewById(R.id.cbGovern);
        cbGovern1 = (CheckBox) findViewById(R.id.cbGovern1);
        cbGovern2 = (CheckBox) findViewById(R.id.cbGovern2);
        cbGovern3 = (CheckBox) findViewById(R.id.cbGovern3);
        cbGovern4 = (CheckBox) findViewById(R.id.cbGovern4);
        cbGovern5 = (CheckBox) findViewById(R.id.cbGovern5);
        cbPrivate = (CheckBox) findViewById(R.id.cbPrivate);
        cbPrivate1 = (CheckBox) findViewById(R.id.cbPrivate1);
        cbPrivate2 = (CheckBox) findViewById(R.id.cbPrivate2);
        cbPrivate3 = (CheckBox) findViewById(R.id.cbPrivate3);
        cbPrivate4 = (CheckBox) findViewById(R.id.cbPrivate4);
        cbPrivate5 = (CheckBox) findViewById(R.id.cbPrivate5);
        cbPrivate6 = (CheckBox) findViewById(R.id.cbPrivate6);
        cbPrivate7 = (CheckBox) findViewById(R.id.cbPrivate7);
        cbCong1 = (CheckBox) findViewById(R.id.cbCong1);
        cbCong2 = (CheckBox) findViewById(R.id.cbCong2);
        cbCong3 = (CheckBox) findViewById(R.id.cbCong3);
        cbCong4 = (CheckBox) findViewById(R.id.cbCong4);
        cbCong5 = (CheckBox) findViewById(R.id.cbCong5);
        cbCont1 = (CheckBox) findViewById(R.id.cbCont1);
        cbCont2 = (CheckBox) findViewById(R.id.cbCont2);
        cbCont3 = (CheckBox) findViewById(R.id.cbCont3);
        cbCont4 = (CheckBox) findViewById(R.id.cbCont4);
        cbCont5 = (CheckBox) findViewById(R.id.cbCont5);
        cbCont6 = (CheckBox) findViewById(R.id.cbCont6);
        cbCont7 = (CheckBox) findViewById(R.id.cbCont7);
        cbCont8 = (CheckBox) findViewById(R.id.cbCont8);
        cbCont9 = (CheckBox) findViewById(R.id.cbCont9);
        cbCont10 = (CheckBox) findViewById(R.id.cbCont10);
        cbCont11 = (CheckBox) findViewById(R.id.cbCont11);
        cbDisabled1 = (CheckBox) findViewById(R.id.cbDisabled1);
        cbDisabled2 = (CheckBox) findViewById(R.id.cbDisabled2);
        cbDisabled3 = (CheckBox) findViewById(R.id.cbDisabled3);
        cbDisabled4 = (CheckBox) findViewById(R.id.cbDisabled4);
        cbDisabled5 = (CheckBox) findViewById(R.id.cbDisabled5);
        cbDisabled6 = (CheckBox) findViewById(R.id.cbDisabled6);
        cbTrans1 = (CheckBox) findViewById(R.id.cbTrans1);
        cbTrans2 = (CheckBox) findViewById(R.id.cbTrans2);
        cbTrans3 = (CheckBox) findViewById(R.id.cbTrans3);
        cbTrans4 = (CheckBox) findViewById(R.id.cbTrans4);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbAlive = (RadioButton) findViewById(R.id.rbAlive);
        rbDead = (RadioButton) findViewById(R.id.rbDead);
        rbInRegister = (RadioButton) findViewById(R.id.rbInRegister);
        rbNotInRegister = (RadioButton) findViewById(R.id.rbNotInRegister);
        rbInHousehold = (RadioButton) findViewById(R.id.rbInHousehold);
        rbNotInHousehold = (RadioButton) findViewById(R.id.rbNotInHousehold);
        rbStatusOwner = (RadioButton) findViewById(R.id.rbStatusOwner);
        rbStatusDweller = (RadioButton) findViewById(R.id.rbStatusDweller);
        rbJGStudent = (RadioButton) findViewById(R.id.rbJGStudent);
        rbJGCareer = (RadioButton) findViewById(R.id.rbJGCareer);
        rbJGNoJob = (RadioButton) findViewById(R.id.rbJGNoJob);
        rbICMonth = (RadioButton) findViewById(R.id.rbICMonth);
        rbICYear = (RadioButton) findViewById(R.id.rbICYear);
        rbDebtNo = (RadioButton) findViewById(R.id.rbDebtNo);
        rbDebtYes = (RadioButton) findViewById(R.id.rbDebtYes);
        rbSavingNo = (RadioButton) findViewById(R.id.rbSavingNo);
        rbSavingYes = (RadioButton) findViewById(R.id.rbSavingYes);
        rbCongenitalNo = (RadioButton) findViewById(R.id.rbCongenitalNo);
        rbCongenitalYes = (RadioButton) findViewById(R.id.rbCongenitalYes);
        rbContagiousNo = (RadioButton) findViewById(R.id.rbContagiousNo);
        rbContagiousYes = (RadioButton) findViewById(R.id.rbContagiousYes);
        rbAllergicNo = (RadioButton) findViewById(R.id.rbAllergicNo);
        rbAllergicYes = (RadioButton) findViewById(R.id.rbAllergicYes);
        rbDisabledNo = (RadioButton) findViewById(R.id.rbDisabledNo);
        rbDisabledYes = (RadioButton) findViewById(R.id.rbDisabledYes);
        rbDisadvantageNo = (RadioButton) findViewById(R.id.rbDisadvantageNo);
        rbDisadvantageYes = (RadioButton) findViewById(R.id.rbDisadvantageYes);
        rbSubAlNo = (RadioButton) findViewById(R.id.rbSubAlNo);
        rbSubAlYes = (RadioButton) findViewById(R.id.rbSubAlYes);
        rbNoStudy = (RadioButton) findViewById(R.id.rbNoStudy);
        rbInStudy = (RadioButton) findViewById(R.id.rbInStudy);
        rbGraduated = (RadioButton) findViewById(R.id.rbGraduated);
        rbLiteracyYes = (RadioButton) findViewById(R.id.rbLiteracyYes);
        rbLiteracyNo = (RadioButton) findViewById(R.id.rbLiteracyNo);
        rbTechnologyNo = (RadioButton) findViewById(R.id.rbTechnologyNo);
        rbTechnologyYes = (RadioButton) findViewById(R.id.rbTechnologyYes);
        rbExpertiseNo = (RadioButton) findViewById(R.id.rbExpertiseNo);
        rbExpertiseYes = (RadioButton) findViewById(R.id.rbExpertiseYes);
        rbBuddhismReligion = (RadioButton) findViewById(R.id.rbBuddhismReligion);
        rbChristReligion = (RadioButton) findViewById(R.id.rbChristReligion);
        rbIslamicReligion = (RadioButton) findViewById(R.id.rbIslamicReligion);
        rbAnotherReligion = (RadioButton) findViewById(R.id.rbAnotherReligion);
        rbParticipationNo = (RadioButton) findViewById(R.id.rbParticipationNo);
        rbParticipationYes = (RadioButton) findViewById(R.id.rbParticipationYes);
        rbElectionAlway = (RadioButton) findViewById(R.id.rbElectionAlway);
        rbElectionSometime = (RadioButton) findViewById(R.id.rbElectionSometime);
        rbElectionNever = (RadioButton) findViewById(R.id.rbElectionNever);
        rbTransportationNo = (RadioButton) findViewById(R.id.rbTransportationNo);
        rbTransportationYes = (RadioButton) findViewById(R.id.rbTransportationYes);

        cbAgri.setOnCheckedChangeListener(this);
        cbAgri1.setOnCheckedChangeListener(this);
        cbAgri2.setOnCheckedChangeListener(this);
        cbAgri3.setOnCheckedChangeListener(this);
        cbAgri4.setOnCheckedChangeListener(this);
        cbAgri5.setOnCheckedChangeListener(this);
        cbAgri6.setOnCheckedChangeListener(this);
        cbAgri7.setOnCheckedChangeListener(this);
        cbAgri8.setOnCheckedChangeListener(this);
        cbPet.setOnCheckedChangeListener(this);
        cbPet1.setOnCheckedChangeListener(this);
        cbPet2.setOnCheckedChangeListener(this);
        cbPet3.setOnCheckedChangeListener(this);
        cbPet4.setOnCheckedChangeListener(this);
        cbPet5.setOnCheckedChangeListener(this);
        cbPet6.setOnCheckedChangeListener(this);
        cbPet7.setOnCheckedChangeListener(this);
        cbPet8.setOnCheckedChangeListener(this);
        cbPet9.setOnCheckedChangeListener(this);
        cbGovern.setOnCheckedChangeListener(this);
        cbGovern1.setOnCheckedChangeListener(this);
        cbGovern2.setOnCheckedChangeListener(this);
        cbGovern3.setOnCheckedChangeListener(this);
        cbGovern4.setOnCheckedChangeListener(this);
        cbGovern5.setOnCheckedChangeListener(this);
        cbPrivate.setOnCheckedChangeListener(this);
        cbPrivate1.setOnCheckedChangeListener(this);
        cbPrivate2.setOnCheckedChangeListener(this);
        cbPrivate3.setOnCheckedChangeListener(this);
        cbPrivate4.setOnCheckedChangeListener(this);
        cbPrivate5.setOnCheckedChangeListener(this);
        cbPrivate6.setOnCheckedChangeListener(this);
        cbPrivate7.setOnCheckedChangeListener(this);
        cbCong1.setOnCheckedChangeListener(this);
        cbCong2.setOnCheckedChangeListener(this);
        cbCong3.setOnCheckedChangeListener(this);
        cbCong4.setOnCheckedChangeListener(this);
        cbCong5.setOnCheckedChangeListener(this);
        cbCont1.setOnCheckedChangeListener(this);
        cbCont2.setOnCheckedChangeListener(this);
        cbCont3.setOnCheckedChangeListener(this);
        cbCont4.setOnCheckedChangeListener(this);
        cbCont5.setOnCheckedChangeListener(this);
        cbCont6.setOnCheckedChangeListener(this);
        cbCont7.setOnCheckedChangeListener(this);
        cbCont8.setOnCheckedChangeListener(this);
        cbCont9.setOnCheckedChangeListener(this);
        cbCont10.setOnCheckedChangeListener(this);
        cbCont11.setOnCheckedChangeListener(this);
        cbDisabled1.setOnCheckedChangeListener(this);
        cbDisabled2.setOnCheckedChangeListener(this);
        cbDisabled3.setOnCheckedChangeListener(this);
        cbDisabled4.setOnCheckedChangeListener(this);
        cbDisabled5.setOnCheckedChangeListener(this);
        cbDisabled6.setOnCheckedChangeListener(this);
        cbTrans1.setOnCheckedChangeListener(this);
        cbTrans2.setOnCheckedChangeListener(this);
        cbTrans3.setOnCheckedChangeListener(this);
        cbTrans4.setOnCheckedChangeListener(this);

        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);
        rbAlive.setOnCheckedChangeListener(this);
        rbDead.setOnCheckedChangeListener(this);
        rbInRegister.setOnCheckedChangeListener(this);
        rbNotInRegister.setOnCheckedChangeListener(this);
        rbInHousehold.setOnCheckedChangeListener(this);
        rbNotInHousehold.setOnCheckedChangeListener(this);
        rbStatusOwner.setOnCheckedChangeListener(this);
        rbStatusDweller.setOnCheckedChangeListener(this);
        rbJGStudent.setOnCheckedChangeListener(this);
        rbJGCareer.setOnCheckedChangeListener(this);
        rbJGNoJob.setOnCheckedChangeListener(this);
        rbICMonth.setOnCheckedChangeListener(this);
        rbICYear.setOnCheckedChangeListener(this);
        rbDebtNo.setOnCheckedChangeListener(this);
        rbDebtYes.setOnCheckedChangeListener(this);
        rbSavingNo.setOnCheckedChangeListener(this);
        rbSavingYes.setOnCheckedChangeListener(this);
        rbCongenitalNo.setOnCheckedChangeListener(this);
        rbCongenitalYes.setOnCheckedChangeListener(this);
        rbContagiousNo.setOnCheckedChangeListener(this);
        rbContagiousYes.setOnCheckedChangeListener(this);
        rbAllergicNo.setOnCheckedChangeListener(this);
        rbAllergicYes.setOnCheckedChangeListener(this);
        rbDisabledNo.setOnCheckedChangeListener(this);
        rbDisabledYes.setOnCheckedChangeListener(this);
        rbDisadvantageNo.setOnCheckedChangeListener(this);
        rbDisadvantageYes.setOnCheckedChangeListener(this);
        rbSubAlNo.setOnCheckedChangeListener(this);
        rbSubAlYes.setOnCheckedChangeListener(this);
        rbNoStudy.setOnCheckedChangeListener(this);
        rbInStudy.setOnCheckedChangeListener(this);
        rbGraduated.setOnCheckedChangeListener(this);
        rbLiteracyYes.setOnCheckedChangeListener(this);
        rbLiteracyNo.setOnCheckedChangeListener(this);
        rbTechnologyNo.setOnCheckedChangeListener(this);
        rbTechnologyYes.setOnCheckedChangeListener(this);
        rbExpertiseNo.setOnCheckedChangeListener(this);
        rbExpertiseYes.setOnCheckedChangeListener(this);
        rbBuddhismReligion.setOnCheckedChangeListener(this);
        rbChristReligion.setOnCheckedChangeListener(this);
        rbIslamicReligion.setOnCheckedChangeListener(this);
        rbAnotherReligion.setOnCheckedChangeListener(this);
        rbParticipationNo.setOnCheckedChangeListener(this);
        rbParticipationYes.setOnCheckedChangeListener(this);
        rbElectionAlway.setOnCheckedChangeListener(this);
        rbElectionSometime.setOnCheckedChangeListener(this);
        rbElectionNever.setOnCheckedChangeListener(this);
        rbTransportationNo.setOnCheckedChangeListener(this);
        rbTransportationYes.setOnCheckedChangeListener(this);
    }

    private void setSpinner() {
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

        PrefixList = db.SelectData("prename");
        if (!PrefixList.isEmpty()) {
            for (int i = 0; i < PrefixList.size(); i++) {
                String strPrefix = PrefixList.get(i).get("prename_detail");
                Prefix.add(strPrefix);
            }
            String[] spPrefixArray = Prefix.toArray(new String[0]);
            prefixArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spPrefixArray, spPrefix);
        }

        DwellerList = db.SelectWhereData("population", "house_id", HouseID);
        if (!DwellerList.isEmpty()) {
            for (int i = 0; i < DwellerList.size(); i++) {
                String strDweller = DwellerList.get(i).get("firstname") + " " + DwellerList.get(i).get("lastname");
                Dweller.add(strDweller);
            }
            String[] spDwellerArray = Dweller.toArray(new String[0]);
            dwellerArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spDwellerArray, spContributor);
        }

        bloodArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spBloodGroupArray, spBloodType);
        maritalArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spMaritalStatusArray, spMaritalStatus);
        educationIArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spEducationArray, spInStudy);
        educationGArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spEducationArray, spGraduated);
        expertiseArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spExpertiseArray, spExpertise);
    }

    private void setField() {
        if (!PersonID.equals("Nope")) {
            loProperty.setVisibility(View.VISIBLE);
            PersonList = db.SelectWhereData("population", "population_idcard", PersonID);
            if (!PersonList.isEmpty()) {
                int spinnerPositionNat = nationalityArrayAdapter.getPosition(PersonList.get(0).get("nationality"));
                spNationality.setSelection(spinnerPositionNat);

                etFirstName.setText(PersonList.get(0).get("firstname"));
                etLastName.setText(PersonList.get(0).get("lastname"));

                int spinnerPositionPrefix = prefixArrayAdapter.getPosition(PersonList.get(0).get("prename"));
                spPrefix.setSelection(spinnerPositionPrefix);

                if (PersonList.get(0).get("sex").equals("M")) {
                    rbMale.setChecked(true);
                }
                if (PersonList.get(0).get("sex").equals("F")) {
                    rbFemale.setChecked(true);
                }
                etPersonalID.setText(PersonList.get(0).get("population_idcard"));
                etBirtDate.setText(PersonList.get(0).get("birthdate"));
                etHeight.setText(PersonList.get(0).get("height"));
                etWeight.setText(PersonList.get(0).get("weight"));


                if (!PersonList.get(0).get("bloodgroup").equals("")) {
                    int spinnerPositionBlood = bloodArrayAdapter.getPosition(PersonList.get(0).get("bloodgroup"));
                    spBloodType.setSelection(spinnerPositionBlood);
                }

                if (PersonList.get(0).get("living").equals("0")) {
                    rbAlive.setChecked(true);
                } else if (PersonList.get(0).get("living").equals("1")) {
                    rbDead.setChecked(true);
                }

                if (!PersonList.get(0).get("maritalstatus").equals("")) {
                    int spinnerPositionMari = maritalArrayAdapter.getPosition(PersonList.get(0).get("maritalstatus"));
                    spMaritalStatus.setSelection(spinnerPositionMari);
                }

                etTel.setText(PersonList.get(0).get("tel"));

                if (PersonList.get(0).get("residence_status").equals("1")) {
                    rbInRegister.setChecked(true);
                } else if (PersonList.get(0).get("residence_status").equals("0")) {
                    rbNotInRegister.setChecked(true);
                    etIRProvince.setText(PersonList.get(0).get("latentpop_province"));
                    etIRCountry.setText(PersonList.get(0).get("latentpop_country"));
                }

                if (PersonList.get(0).get("currentaddr").equals("0")) {
                    rbInHousehold.setChecked(true);
                } else if (PersonList.get(0).get("currentaddr").equals("1")) {
                    rbNotInHousehold.setChecked(true);
                    etIHProvince.setText(PersonList.get(0).get("currentaddr_province"));
                    etIHCountry.setText(PersonList.get(0).get("currentaddr_country"));
                }

                if (PersonList.get(0).get("dwellerstatus").equals("0")) {
                    rbStatusOwner.setChecked(true);
                } else if (PersonList.get(0).get("dwellerstatus").equals("1")) {
                    rbStatusDweller.setChecked(true);
                }

                if (PersonList.get(0).get("income").equals("0")) {
                    rbICMonth.setChecked(true);
                    etICMonth.setText(PersonList.get(0).get("income_money"));
                } else if (PersonList.get(0).get("income").equals("1")) {
                    rbICYear.setChecked(true);
                    etICYear.setText(PersonList.get(0).get("income_money"));
                }

                if (PersonList.get(0).get("dept").equals("0")) {
                    rbDebtNo.setChecked(true);
                } else if (PersonList.get(0).get("dept").equals("1")) {
                    rbDebtYes.setChecked(true);
                }

                if (PersonList.get(0).get("saving").equals("0")) {
                    rbSavingNo.setChecked(true);
                } else if (PersonList.get(0).get("saving").equals("1")) {
                    rbSavingYes.setChecked(true);
                }

                if (PersonList.get(0).get("allergichis").equals("0")) {
                    rbAllergicNo.setChecked(true);
                } else if (PersonList.get(0).get("allergichis").equals("1")) {
                    rbAllergicYes.setChecked(true);
                    etAllergic.setText(PersonList.get(0).get("allergichis_detail"));
                }

                if (PersonList.get(0).get("disadvantage").equals("0")) {
                    rbDisadvantageNo.setChecked(true);
                } else if (PersonList.get(0).get("disadvantage").equals("1")) {
                    rbDisadvantageYes.setChecked(true);
                }

                if (PersonList.get(0).get("sub_al").equals("0")) {
                    rbSubAlNo.setChecked(true);
                } else if (PersonList.get(0).get("sub_al").equals("1")) {
                    rbSubAlYes.setChecked(true);
                }

                if (PersonList.get(0).get("education").equals("0")) {
                    rbNoStudy.setChecked(true);
                } else if (PersonList.get(0).get("education").equals("1")) {
                    rbInStudy.setChecked(true);
                    if (!PersonList.get(0).get("education_class").equals("")) {
                        int spinnerPositionEduI = educationIArrayAdapter.getPosition(PersonList.get(0).get("education_class"));
                        spInStudy.setSelection(spinnerPositionEduI);
                    }
                } else if (PersonList.get(0).get("education").equals("2")) {
                    rbGraduated.setChecked(true);
                    if (!PersonList.get(0).get("education_class").equals("")) {
                        int spinnerPositionEduG = educationGArrayAdapter.getPosition(PersonList.get(0).get("education_class"));
                        spGraduated.setSelection(spinnerPositionEduG);
                    }
                }

                if (PersonList.get(0).get("literacy").equals("0")) {
                    rbLiteracyYes.setChecked(true);
                } else if (PersonList.get(0).get("literacy").equals("1")) {
                    rbLiteracyNo.setChecked(true);
                }

                if (PersonList.get(0).get("technology").equals("0")) {
                    rbTechnologyNo.setChecked(true);
                } else if (PersonList.get(0).get("technology").equals("1")) {
                    rbTechnologyYes.setChecked(true);
                }

                if (PersonList.get(0).get("expertise").equals("0")) {
                    rbExpertiseNo.setChecked(true);
                } else if (PersonList.get(0).get("expertise").equals("1")) {
                    rbExpertiseYes.setChecked(true);
                    if (!PersonList.get(0).get("expertise_name").equals("")) {
                        int spinnerPositionExp = expertiseArrayAdapter.getPosition(PersonList.get(0).get("expertise_name"));
                        spExpertise.setSelection(spinnerPositionExp);
                    }
                    etExpertise.setText(PersonList.get(0).get("expertise_detail"));
                }

                if (PersonList.get(0).get("religion").equals("0")) {
                    rbBuddhismReligion.setChecked(true);
                } else if (PersonList.get(0).get("religion").equals("1")) {
                    rbChristReligion.setChecked(true);
                } else if (PersonList.get(0).get("religion").equals("2")) {
                    rbIslamicReligion.setChecked(true);
                } else if (PersonList.get(0).get("religion").equals("3")) {
                    rbAnotherReligion.setChecked(true);
                    etAnotherReligion.setText(PersonList.get(0).get("religion_another"));
                }

                if (PersonList.get(0).get("participation").equals("0")) {
                    rbParticipationNo.setChecked(true);
                } else if (PersonList.get(0).get("participation").equals("1")) {
                    rbParticipationYes.setChecked(true);
                }

                if (PersonList.get(0).get("election").equals("0")) {
                    rbElectionAlway.setChecked(true);
                } else if (PersonList.get(0).get("election").equals("1")) {
                    rbElectionSometime.setChecked(true);
                } else if (PersonList.get(0).get("election").equals("2")) {
                    rbElectionNever.setChecked(true);
                }
                int spinnerPositionContri = dwellerArrayAdapter.getPosition(PersonList.get(0).get("distributor"));
                spContributor.setSelection(spinnerPositionContri);
                ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);

                WorkList = db.SelectWhereData("population_works", "population_idcard", PersonList.get(0).get("population_idcard"));
                if (!WorkList.isEmpty()) {
                    if (WorkList.get(0).get("works_type").equals("0")) {
                        rbJGStudent.setChecked(true);
                    } else if (WorkList.get(0).get("works_type").equals("1")) {
                        rbJGCareer.setChecked(true);
                    } else if (WorkList.get(0).get("works_type").equals("1")) {
                        rbJGNoJob.setChecked(true);
                    }
                    AgriList = db.SelectWhereData("population_job_agriculture", "works_id", WorkList.get(0).get("works_id"));
                    if (!AgriList.isEmpty()) {
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri1, AgriList.get(0).get("agri1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri2, AgriList.get(0).get("agri2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri3, AgriList.get(0).get("agri3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri4, AgriList.get(0).get("agri4"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri5, AgriList.get(0).get("agri5"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri6, AgriList.get(0).get("agri6"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri7, AgriList.get(0).get("agri7"));
                        ModelCheckboxCheck.checkboxSetCheck(cbAgri8, AgriList.get(0).get("agri8"));
                        if (AgriList.get(0).get("agri8").equals("1")) {
                            etAnotherAgri.setText(AgriList.get(0).get("agri_another"));
                        }
                        if (AgriList.get(0).get("agri1").equals("1") ||
                                AgriList.get(0).get("agri2").equals("1") ||
                                AgriList.get(0).get("agri3").equals("1") ||
                                AgriList.get(0).get("agri4").equals("1") ||
                                AgriList.get(0).get("agri5").equals("1") ||
                                AgriList.get(0).get("agri6").equals("1") ||
                                AgriList.get(0).get("agri7").equals("1") ||
                                AgriList.get(0).get("agri8").equals("1")) {
                            cbAgri.setChecked(true);
                        }
                    }

                    PetList = db.SelectWhereData("population_job_animal", "works_id", WorkList.get(0).get("works_id"));
                    if (!PetList.isEmpty()) {
                        ModelCheckboxCheck.checkboxSetCheck(cbPet1, PetList.get(0).get("animal1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet2, PetList.get(0).get("animal2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet3, PetList.get(0).get("animal3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet4, PetList.get(0).get("animal4"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet5, PetList.get(0).get("animal5"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet6, PetList.get(0).get("animal6"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet7, PetList.get(0).get("animal7"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet8, PetList.get(0).get("animal8"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPet9, PetList.get(0).get("animal9"));
                        if (PetList.get(0).get("animal9").equals("1")) {
                            etAnotherPet.setText(PetList.get(0).get("animal_another"));
                        }
                        if (PetList.get(0).get("animal1").equals("1") ||
                                PetList.get(0).get("animal2").equals("1") ||
                                PetList.get(0).get("animal3").equals("1") ||
                                PetList.get(0).get("animal4").equals("1") ||
                                PetList.get(0).get("animal5").equals("1") ||
                                PetList.get(0).get("animal6").equals("1") ||
                                PetList.get(0).get("animal7").equals("1") ||
                                PetList.get(0).get("animal8").equals("1") ||
                                PetList.get(0).get("animal9").equals("1")) {
                            cbPet.setChecked(true);
                        }
                    }

                    GovernList = db.SelectWhereData("population_job_govern", "works_id", WorkList.get(0).get("works_id"));
                    if (!GovernList.isEmpty()) {
                        ModelCheckboxCheck.checkboxSetCheck(cbGovern1, GovernList.get(0).get("govern1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbGovern2, GovernList.get(0).get("govern2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbGovern3, GovernList.get(0).get("govern3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbGovern4, GovernList.get(0).get("govern4"));
                        if (GovernList.get(0).get("govern4").equals("1")) {
                            etAnotherGovern.setText(GovernList.get(0).get("govern_another"));
                        }
                        if (GovernList.get(0).get("govern1").equals("1") ||
                                GovernList.get(0).get("govern2").equals("1") ||
                                GovernList.get(0).get("govern3").equals("1") ||
                                GovernList.get(0).get("govern4").equals("1")) {
                            cbGovern.setChecked(true);
                        }
                    }

                    PrivateList = db.SelectWhereData("population_job_private", "works_id", WorkList.get(0).get("works_id"));
                    if (!PrivateList.isEmpty()) {
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate1, PrivateList.get(0).get("private1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate2, PrivateList.get(0).get("private2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate3, PrivateList.get(0).get("private3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate4, PrivateList.get(0).get("private4"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate5, PrivateList.get(0).get("private5"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate6, PrivateList.get(0).get("private6"));
                        ModelCheckboxCheck.checkboxSetCheck(cbPrivate7, PrivateList.get(0).get("private7"));
                        if (PrivateList.get(0).get("private7").equals("1")) {
                            etAnotherPrivate.setText(PrivateList.get(0).get("private_another"));
                        }
                        if (PrivateList.get(0).get("private1").equals("1") ||
                                PrivateList.get(0).get("private2").equals("1") ||
                                PrivateList.get(0).get("private3").equals("1") ||
                                PrivateList.get(0).get("private4").equals("1") ||
                                PrivateList.get(0).get("private5").equals("1") ||
                                PrivateList.get(0).get("private6").equals("1") ||
                                PrivateList.get(0).get("private7").equals("1")) {
                            cbPrivate.setChecked(true);
                        }
                    }
                }

                CongList = db.SelectWhereData("population_congenitalhis", "population_idcard", PersonList.get(0).get("population_idcard"));
                if (!CongList.isEmpty()) {
                    if (CongList.get(0).get("congh_type").equals("1")) {
                        rbCongenitalYes.setChecked(true);
                        ModelCheckboxCheck.checkboxSetCheck(cbCong1, CongList.get(0).get("congh1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCong2, CongList.get(0).get("congh2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCong3, CongList.get(0).get("congh3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCong4, CongList.get(0).get("congh4"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCong5, CongList.get(0).get("congh5"));
                        if (CongList.get(0).get("congh5").equals("1")) {
                            etAnotherCong.setText(CongList.get(0).get("congh_another"));
                        }
                    } else if (CongList.get(0).get("congh_type").equals("0")) {
                        rbCongenitalNo.setChecked(true);
                    }
                }

                ContList = db.SelectWhereData("population_contagioushis", "population_idcard", PersonList.get(0).get("population_idcard"));
                if (!ContList.isEmpty()) {
                    if (ContList.get(0).get("conth_type").equals("1")) {
                        rbContagiousYes.setChecked(true);
                        ModelCheckboxCheck.checkboxSetCheck(cbCont1, ContList.get(0).get("conth1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont2, ContList.get(0).get("conth2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont3, ContList.get(0).get("conth3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont4, ContList.get(0).get("conth4"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont5, ContList.get(0).get("conth5"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont6, ContList.get(0).get("conth6"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont7, ContList.get(0).get("conth7"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont8, ContList.get(0).get("conth8"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont9, ContList.get(0).get("conth9"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont10, ContList.get(0).get("conth10"));
                        ModelCheckboxCheck.checkboxSetCheck(cbCont11, ContList.get(0).get("conth11"));
                        if (ContList.get(0).get("conth11").equals("1")) {
                            etAnotherCont.setText(ContList.get(0).get("conth_another"));
                        }
                    } else if (ContList.get(0).get("conth_type").equals("0")) {
                        rbContagiousNo.setChecked(true);
                    }
                }

                DisabledList = db.SelectWhereData("population_disabled", "population_idcard", PersonList.get(0).get("population_idcard"));
                if (!DisabledList.isEmpty()) {
                    if (DisabledList.get(0).get("disabled_type").equals("1")) {
                        rbDisabledYes.setChecked(true);
                        ModelCheckboxCheck.checkboxSetCheck(cbDisabled1, DisabledList.get(0).get("disabled1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbDisabled2, DisabledList.get(0).get("disabled2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbDisabled3, DisabledList.get(0).get("disabled3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbDisabled4, DisabledList.get(0).get("disabled4"));
                        ModelCheckboxCheck.checkboxSetCheck(cbDisabled5, DisabledList.get(0).get("disabled5"));
                        ModelCheckboxCheck.checkboxSetCheck(cbDisabled6, DisabledList.get(0).get("disabled6"));
                    } else if (DisabledList.get(0).get("disabled_type").equals("0")) {
                        rbDisabledNo.setChecked(true);
                    }
                }

                TransList = db.SelectWhereData("population_transport", "population_idcard", PersonList.get(0).get("population_idcard"));
                if (!TransList.isEmpty()) {
                    if (TransList.get(0).get("transport_type").equals("1")) {
                        rbTransportationYes.setChecked(true);
                        ModelCheckboxCheck.checkboxSetCheck(cbTrans1, TransList.get(0).get("trans1"));
                        ModelCheckboxCheck.checkboxSetCheck(cbTrans2, TransList.get(0).get("trans2"));
                        ModelCheckboxCheck.checkboxSetCheck(cbTrans3, TransList.get(0).get("trans3"));
                        ModelCheckboxCheck.checkboxSetCheck(cbTrans4, TransList.get(0).get("trans4"));
                    } else if (TransList.get(0).get("transport_type").equals("0")) {
                        rbTransportationNo.setChecked(true);
                    }
                }
            }
        } else {
            loProperty.setVisibility(View.GONE);
        }
    }

    private void setListView() {
        if (!PersonID.equals("Nope")) {
            String strOrder = "Order";
            String strPropType = "PropType";
            String strBenefit = "Benefit";
            String strPropertyID = "ID";
            String benefit = "benefit";
            int order = 0;
            PropertyActive = new ArrayList<HashMap<String, String>>();
            PLandList = db.SelectWhereData("population_asset_land", "population_idcard", PersonID);
            if (!PLandList.isEmpty()) {
                for (int i = 0; i < PLandList.size(); i++) {
                    if (PLandList.get(i).get("ACTIVE").equals("Y")) {
                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put(strOrder, String.valueOf(++order));
                        temp.put(strPropType, "ที่ดิน");
                        if (PLandList.get(i).get("land_benefit").equals("0")) {
                            benefit = "ที่พักอาศัย";
                        } else if (PLandList.get(i).get("land_benefit").equals("1")) {
                            benefit = "เพาะปลูก";
                        } else if (PLandList.get(i).get("land_benefit").equals("2")) {
                            benefit = "เลี้ยงสัตว์";
                        } else if (PLandList.get(i).get("land_benefit").equals("3")) {
                            benefit = "ประกอบกิจการ";
                        } else {
                            benefit = "ไม่ทราบ";
                        }
                        temp.put(strBenefit, benefit);
                        temp.put(strPropertyID, PLandList.get(i).get("land_running"));
                        PropertyActive.add(temp);
                    }
                }
            }

            PVehicleList = db.SelectWhereData("population_asset_vehicle", "population_idcard", PersonID);
            if (!PVehicleList.isEmpty()) {
                for (int i = 0; i < PVehicleList.size(); i++) {
                    if (PVehicleList.get(i).get("ACTIVE").equals("Y")) {
                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put(strOrder, String.valueOf(++order));
                        temp.put(strPropType, "ยานพาหนะ");
                        TestList = db.SelectWhereData("asset_vehicle", "vtype_id", PVehicleList.get(i).get("vtype_id"));
                        if (!TestList.isEmpty()) {
                            temp.put(strBenefit, TestList.get(0).get("vtype_detail"));
                        } else {
                            temp.put(strBenefit, "ไม่ทราบ");
                        }
                        temp.put(strPropertyID, PVehicleList.get(i).get("vehicle_running"));
                        PropertyActive.add(temp);
                    }
                }
            }

            PPetList = db.SelectWhereData("population_asset_pet", "population_idcard", PersonID);
            if (!PPetList.isEmpty()) {
                for (int i = 0; i < PPetList.size(); i++) {
                    if (PPetList.get(i).get("ACTIVE").equals("Y")) {
                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put(strOrder, String.valueOf(++order));
                        temp.put(strPropType, "สัตว์เลี้ยง");
                        if (!PPetList.get(0).get("pet_type").equals("")) {
                            temp.put(strBenefit, PPetList.get(0).get("pet_type"));
                        } else {
                            temp.put(strBenefit, "ไม่ทราบ");
                        }
                        temp.put(strPropertyID, PPetList.get(i).get("pet_running"));
                        PropertyActive.add(temp);
                    }
                }
            }

            PAnimalList = db.SelectWhereData("population_asset_animal", "population_idcard", PersonID);
            if (!PAnimalList.isEmpty()) {
                for (int i = 0; i < PAnimalList.size(); i++) {
                    if (PAnimalList.get(i).get("ACTIVE").equals("Y")) {
                        HashMap<String, String> temp = new HashMap<String, String>();
                        temp.put(strOrder, String.valueOf(++order));
                        temp.put(strPropType, "ปศุสัตว์");
                        TestList = db.SelectWhereData("asset_animal", "atype_id", PAnimalList.get(i).get("atype_id"));
                        if (!TestList.isEmpty()) {
                            temp.put(strBenefit, TestList.get(0).get("atype_name"));
                        } else {
                            temp.put(strBenefit, "ไม่ทราบ");
                        }
                        temp.put(strPropertyID, PAnimalList.get(i).get("animal_running"));
                        PropertyActive.add(temp);
                    }
                }
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, PropertyActive, R.layout.view_property_column,
                    new String[]{strOrder, strPropType, strBenefit, strPropertyID},
                    new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvHiddenColumn}
            );
            lvProperty.setAdapter(simpleAdapter);
        }
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());
        Boolean NatPass = false;
        NationalityList = db.SelectData("nationality");
        if (!NationalityList.isEmpty()) {
            if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                String strAnotherNat = etNationality.getText().toString();
                if (!strAnotherNat.equals("")) {
                    if (!Nationality.contains(strAnotherNat)) {
                        Val = new ContentValues();
                        Val.put("nationality_detail", strAnotherNat);
                        Val.put("upd_by", "JuJiiz");
                        Val.put("upd_date", date);
                        Val.put("ACTIVE", "Y");
                        NationalityList = db.SelectWhereData("nationality", "nationality_detail", "\"" + strAnotherNat + "\"");
                        if (NationalityList.isEmpty()) {
                            Val.put("cr_by", "JuJiiz");
                            Val.put("cr_date", date);
                            db.InsertData("nationality", Val);
                            NatPass = true;
                        } else {
                            db.UpdateData("nationality", Val, "nationality_detail", "\"" + strAnotherNat + "\"");
                            NatPass = true;
                        }
                    } else {
                        Toast.makeText(this, "สัญชาติซ้ำ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุสัญชาติ", Toast.LENGTH_SHORT).show();
                }
            }
        }
        //////////////////////////////////// Nationality Insert/Update ////////////////////////////////////

        if (NatPass == true) {
            Val = new ContentValues();
            Val.put("population_idcard", etPersonalID.getText().toString());
            Val.put("prename", spPrefix.getSelectedItem().toString());
            Val.put("firstname", etFirstName.getText().toString());
            Val.put("lastname", etLastName.getText().toString());
            Val.put("birthdate", etBirtDate.getText().toString());
            Val.put("height", etHeight.getText().toString());
            Val.put("weight", etWeight.getText().toString());
            if (rbMale.isChecked()) {
                Val.put("sex", "M");
            } else if (rbFemale.isChecked()) {
                Val.put("sex", "F");
            } else {
                Val.put("sex", "");
            }
            Val.put("bloodgroup", spBloodType.getSelectedItem().toString());
            if (rbAlive.isChecked()) {
                Val.put("living", "0");
            } else if (rbDead.isChecked()) {
                Val.put("living", "1");
            } else {
                Val.put("living", "");
            }
            Val.put("maritalstatus", spMaritalStatus.getSelectedItem().toString());
            Val.put("tel", etTel.getText().toString());
            if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                Val.put("nationality", etNationality.getText().toString());
            } else {
                Val.put("nationality", spNationality.getSelectedItem().toString());
            }

            Val.put("house_id", HouseID);
            if (rbInHousehold.isChecked()) {
                Val.put("currentaddr", "0");
                Val.put("currentaddr_province", "");
                Val.put("currentaddr_country", "");
            } else if (rbNotInHousehold.isChecked()) {
                Val.put("currentaddr", "1");
                Val.put("currentaddr_province", etIHProvince.getText().toString());
                Val.put("currentaddr_country", etIHCountry.getText().toString());
            } else {
                Val.put("currentaddr", "");
                Val.put("currentaddr_province", "");
                Val.put("currentaddr_country", "");
            }
            if (PersonID.equals("Nope")) {
                Val.put("dwellerstatus", "1");
            } else {
                PersonList = db.SelectWhereData("population", "population_idcard", etPersonalID.getText().toString());
                Val.put("dwellerstatus", PersonList.get(0).get("dwellerstatus"));
            }

            if (rbICMonth.isChecked()) {
                Val.put("income", "0");
                Val.put("income_money", etICMonth.getText().toString());
            } else if (rbICYear.isChecked()) {
                Val.put("income", "1");
                Val.put("income_money", etICYear.getText().toString());
            } else {
                Val.put("income", "");
                Val.put("income_money", "");
            }

            if (rbDebtNo.isChecked()) {
                Val.put("dept", "0");
            } else if (rbDebtYes.isChecked()) {
                Val.put("dept", "1");
            } else {
                Val.put("dept", "");
            }

            if (rbSavingNo.isChecked()) {
                Val.put("saving", "0");
            } else if (rbSavingYes.isChecked()) {
                Val.put("saving", "1");
            } else {
                Val.put("saving", "");
            }

            if (rbAllergicNo.isChecked()) {
                Val.put("allergichis", "0");
                Val.put("allergichis_detail", "");
            } else if (rbAllergicYes.isChecked()) {
                Val.put("allergichis", "1");
                Val.put("allergichis_detail", etAllergic.getText().toString());
            } else {
                Val.put("allergichis", "");
                Val.put("allergichis_detail", "");
            }

            if (rbDisadvantageNo.isChecked()) {
                Val.put("disadvantage", "0");
            } else if (rbDisadvantageYes.isChecked()) {
                Val.put("disadvantage", "1");
            } else {
                Val.put("disadvantage", "");
            }

            if (rbSubAlNo.isChecked()) {
                Val.put("sub_al", "0");
            } else if (rbSubAlYes.isChecked()) {
                Val.put("sub_al", "1");
            } else {
                Val.put("sub_al", "");
            }

            if (rbNoStudy.isChecked()) {
                Val.put("education", "0");
                Val.put("education_class", "");
            } else if (rbInStudy.isChecked()) {
                Val.put("education", "1");
                Val.put("education_class", spInStudy.getSelectedItem().toString());
            } else if (rbGraduated.isChecked()) {
                Val.put("education", "2");
                Val.put("education_class", spGraduated.getSelectedItem().toString());
            } else {
                Val.put("education", "");
                Val.put("education_class", "");
            }

            if (rbLiteracyNo.isChecked()) {
                Val.put("literacy", "0");
            } else if (rbLiteracyYes.isChecked()) {
                Val.put("literacy", "1");
            } else {
                Val.put("literacy", "");
            }

            if (rbTechnologyNo.isChecked()) {
                Val.put("technology", "0");
            } else if (rbTechnologyYes.isChecked()) {
                Val.put("technology", "1");
            } else {
                Val.put("technology", "");
            }

            if (rbExpertiseNo.isChecked()) {
                Val.put("expertise", "0");
                Val.put("expertise_name", "");
                Val.put("expertise_detail", "");
            } else if (rbExpertiseYes.isChecked()) {
                Val.put("expertise", "1");
                Val.put("expertise_name", spExpertise.getSelectedItem().toString());
                Val.put("expertise_detail", etExpertise.getText().toString());
            } else {
                Val.put("expertise", "");
                Val.put("expertise_name", "");
                Val.put("expertise_detail", "");
            }

            if (rbBuddhismReligion.isChecked()) {
                Val.put("religion", "0");
                Val.put("religion_another", "");
            } else if (rbChristReligion.isChecked()) {
                Val.put("religion", "1");
                Val.put("religion_another", "");
            } else if (rbIslamicReligion.isChecked()) {
                Val.put("religion", "2");
                Val.put("religion_another", "");
            } else if (rbAnotherReligion.isChecked()) {
                Val.put("religion", "3");
                Val.put("religion_another", etAnotherReligion.getText().toString());
            } else {
                Val.put("religion", "");
                Val.put("religion_another", "");
            }

            if (rbParticipationNo.isChecked()) {
                Val.put("participation", "0");
            } else if (rbParticipationYes.isChecked()) {
                Val.put("participation", "1");
            } else {
                Val.put("participation", "");
            }

            if (rbElectionAlway.isChecked()) {
                Val.put("election", "0");
            } else if (rbElectionSometime.isChecked()) {
                Val.put("election", "1");
            } else if (rbElectionNever.isChecked()) {
                Val.put("election", "2");
            } else {
                Val.put("election", "");
            }

            if (PersonID.equals("Nope")) {
                Val.put("residence_status", "0");
                Val.put("latentpop_province", etIRProvince.getText().toString());
                Val.put("latentpop_country", etIRCountry.getText().toString());
            } else {
                if (rbInRegister.isChecked()) {
                    Val.put("residence_status", "1");
                    Val.put("latentpop_province", "");
                    Val.put("latentpop_country", "");
                } else if (rbInRegister.isChecked()) {
                    Val.put("residence_status", "0");
                    Val.put("latentpop_province", etIRProvince.getText().toString());
                    Val.put("latentpop_country", etIRCountry.getText().toString());
                }
            }

            Val.put("distributor", spContributor.getSelectedItem().toString());
            Val.put("survey_status", "1");
            Val.put("upd_by", "JuJiiz");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population", "population_idcard", etPersonalID.getText().toString());
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population", Val);
            } else {
                db.UpdateData("population", Val, "population_idcard", etPersonalID.getText().toString());
            }
            //////////////////////////////////// Population Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            if (rbCongenitalNo.isChecked()) {
                Val.put("congh_type", "0");
                Val.put("congh1", "");
                Val.put("congh2", "");
                Val.put("congh3", "");
                Val.put("congh4", "");
                Val.put("congh5", "");
                Val.put("congh_another", "");
            } else if (rbCongenitalYes.isChecked()) {
                Val.put("congh_type", "1");
                Val.put("congh1", ModelCheckboxCheck.checkboxReturnCheck(cbCong1));
                Val.put("congh2", ModelCheckboxCheck.checkboxReturnCheck(cbCong2));
                Val.put("congh3", ModelCheckboxCheck.checkboxReturnCheck(cbCong3));
                Val.put("congh4", ModelCheckboxCheck.checkboxReturnCheck(cbCong4));
                Val.put("congh5", ModelCheckboxCheck.checkboxReturnCheck(cbCong5));
                if (cbCong5.isChecked()) {
                    Val.put("congh_another", etAnotherCong.getText().toString());
                } else if (!cbCong5.isChecked()) {
                    Val.put("congh_another", "");
                }
            }
            Val.put("population_idcard", etPersonalID.getText().toString());
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_congenitalhis", "population_idcard", etPersonalID.getText().toString());
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_congenitalhis", Val);
            } else {
                db.UpdateData("population_congenitalhis", Val, "population_idcard", etPersonalID.getText().toString());
            }
            //////////////////////////////////// Population Congenitalhis Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            if (rbContagiousNo.isChecked()) {
                Val.put("conth_type", "0");
                Val.put("conth1", "");
                Val.put("conth2", "");
                Val.put("conth3", "");
                Val.put("conth4", "");
                Val.put("conth5", "");
                Val.put("conth6", "");
                Val.put("conth7", "");
                Val.put("conth8", "");
                Val.put("conth9", "");
                Val.put("conth10", "");
                Val.put("conth11", "");
                Val.put("conth_another", "");
            } else if (rbContagiousYes.isChecked()) {
                Val.put("conth_type", "1");
                Val.put("conth1", ModelCheckboxCheck.checkboxReturnCheck(cbCont1));
                Val.put("conth2", ModelCheckboxCheck.checkboxReturnCheck(cbCont2));
                Val.put("conth3", ModelCheckboxCheck.checkboxReturnCheck(cbCont3));
                Val.put("conth4", ModelCheckboxCheck.checkboxReturnCheck(cbCont4));
                Val.put("conth5", ModelCheckboxCheck.checkboxReturnCheck(cbCont5));
                Val.put("conth6", ModelCheckboxCheck.checkboxReturnCheck(cbCont6));
                Val.put("conth7", ModelCheckboxCheck.checkboxReturnCheck(cbCont7));
                Val.put("conth8", ModelCheckboxCheck.checkboxReturnCheck(cbCont8));
                Val.put("conth9", ModelCheckboxCheck.checkboxReturnCheck(cbCont9));
                Val.put("conth10", ModelCheckboxCheck.checkboxReturnCheck(cbCont10));
                Val.put("conth11", ModelCheckboxCheck.checkboxReturnCheck(cbCont11));
                if (cbCont11.isChecked()) {
                    Val.put("conth_another", etAnotherCont.getText().toString());
                } else if (!cbCont11.isChecked()) {
                    Val.put("conth_another", "");
                }
            }
            Val.put("population_idcard", etPersonalID.getText().toString());
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_contagioushis", "population_idcard", etPersonalID.getText().toString());
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_contagioushis", Val);
            } else {
                db.UpdateData("population_contagioushis", Val, "population_idcard", etPersonalID.getText().toString());
            }
            //////////////////////////////////// Population Contagioushis Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            if (rbDisabledNo.isChecked()) {
                Val.put("disabled_type", "0");
                Val.put("disabled1", "");
                Val.put("disabled2", "");
                Val.put("disabled3", "");
                Val.put("disabled4", "");
                Val.put("disabled5", "");
                Val.put("disabled6", "");
            } else if (rbDisabledYes.isChecked()) {
                Val.put("disabled_type", "1");
                Val.put("disabled1", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled1));
                Val.put("disabled2", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled2));
                Val.put("disabled3", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled3));
                Val.put("disabled4", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled4));
                Val.put("disabled5", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled5));
                Val.put("disabled6", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled6));
            }
            Val.put("population_idcard", etPersonalID.getText().toString());
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_disabled", "population_idcard", etPersonalID.getText().toString());
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_disabled", Val);
            } else {
                db.UpdateData("population_disabled", Val, "population_idcard", etPersonalID.getText().toString());
            }
            //////////////////////////////////// Population Disabled Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            if (rbTransportationNo.isChecked()) {
                Val.put("transport_type", "0");
                Val.put("trans1", "");
                Val.put("trans2", "");
                Val.put("trans3", "");
                Val.put("trans4", "");
            } else if (rbTransportationYes.isChecked()) {
                Val.put("transport_type", "1");
                Val.put("trans1", ModelCheckboxCheck.checkboxReturnCheck(cbTrans1));
                Val.put("trans2", ModelCheckboxCheck.checkboxReturnCheck(cbTrans2));
                Val.put("trans3", ModelCheckboxCheck.checkboxReturnCheck(cbTrans3));
                Val.put("trans4", ModelCheckboxCheck.checkboxReturnCheck(cbTrans4));
            }
            Val.put("population_idcard", etPersonalID.getText().toString());
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_transport", "population_idcard", etPersonalID.getText().toString());
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_transport", Val);
            } else {
                db.UpdateData("population_transport", Val, "population_idcard", etPersonalID.getText().toString());
            }
            //////////////////////////////////// Population Transport Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            if (rbJGStudent.isChecked()) {
                Val.put("works_type", "0");
            } else if (rbJGCareer.isChecked()) {
                Val.put("works_type", "1");
            } else if (rbJGNoJob.isChecked()) {
                Val.put("works_type", "2");
            } else {
                Val.put("works_type", "");
            }
            Val.put("population_idcard", etPersonalID.getText().toString());
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_works", "population_idcard", etPersonalID.getText().toString());
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_works", Val);
            } else {
                db.UpdateData("population_works", Val, "population_idcard", etPersonalID.getText().toString());
            }
            //////////////////////////////////// Population Work Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            TestList = db.SelectWhereData("population_works", "population_idcard", etPersonalID.getText().toString());
            Val.put("works_id", TestList.get(0).get("works_id"));
            if (cbAgri.isChecked()) {
                Val.put("agri1", ModelCheckboxCheck.checkboxReturnCheck(cbAgri1));
                Val.put("agri2", ModelCheckboxCheck.checkboxReturnCheck(cbAgri2));
                Val.put("agri3", ModelCheckboxCheck.checkboxReturnCheck(cbAgri3));
                Val.put("agri4", ModelCheckboxCheck.checkboxReturnCheck(cbAgri4));
                Val.put("agri5", ModelCheckboxCheck.checkboxReturnCheck(cbAgri5));
                Val.put("agri6", ModelCheckboxCheck.checkboxReturnCheck(cbAgri6));
                Val.put("agri7", ModelCheckboxCheck.checkboxReturnCheck(cbAgri7));
                Val.put("agri8", ModelCheckboxCheck.checkboxReturnCheck(cbAgri8));
                if (cbAgri8.isChecked()) {
                    Val.put("agri_another", etAnotherAgri.getText().toString());
                } else {
                    Val.put("agri_another", "");
                }

            } else {
                Val.put("agri1", "");
                Val.put("agri2", "");
                Val.put("agri3", "");
                Val.put("agri4", "");
                Val.put("agri5", "");
                Val.put("agri6", "");
                Val.put("agri7", "");
                Val.put("agri8", "");
                Val.put("agri_another", "");
            }
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_job_agriculture", "works_id", TestList.get(0).get("works_id"));
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_job_agriculture", Val);
            } else {
                db.UpdateData("population_job_agriculture", Val, "works_id", TestList.get(0).get("works_id"));
            }
            //////////////////////////////////// Population Work Agri Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            TestList = db.SelectWhereData("population_works", "population_idcard", etPersonalID.getText().toString());
            Val.put("works_id", TestList.get(0).get("works_id"));
            if (cbPet.isChecked()) {
                Val.put("animal1", ModelCheckboxCheck.checkboxReturnCheck(cbPet1));
                Val.put("animal2", ModelCheckboxCheck.checkboxReturnCheck(cbPet2));
                Val.put("animal3", ModelCheckboxCheck.checkboxReturnCheck(cbPet3));
                Val.put("animal4", ModelCheckboxCheck.checkboxReturnCheck(cbPet4));
                Val.put("animal5", ModelCheckboxCheck.checkboxReturnCheck(cbPet5));
                Val.put("animal6", ModelCheckboxCheck.checkboxReturnCheck(cbPet6));
                Val.put("animal7", ModelCheckboxCheck.checkboxReturnCheck(cbPet7));
                Val.put("animal8", ModelCheckboxCheck.checkboxReturnCheck(cbPet8));
                Val.put("animal9", ModelCheckboxCheck.checkboxReturnCheck(cbPet9));
                if (cbPet9.isChecked()) {
                    Val.put("animal_another", etAnotherPet.getText().toString());
                } else {
                    Val.put("animal_another", "");
                }

            } else {
                Val.put("animal1", "");
                Val.put("animal2", "");
                Val.put("animal3", "");
                Val.put("animal4", "");
                Val.put("animal5", "");
                Val.put("animal6", "");
                Val.put("animal7", "");
                Val.put("animal8", "");
                Val.put("animal9", "");
                Val.put("animal_another", "");
            }
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_job_animal", "works_id", TestList.get(0).get("works_id"));
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_job_animal", Val);
            } else {
                db.UpdateData("population_job_animal", Val, "works_id", TestList.get(0).get("works_id"));
            }
            //////////////////////////////////// Population Work Pet Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            TestList = db.SelectWhereData("population_works", "population_idcard", etPersonalID.getText().toString());
            Val.put("works_id", TestList.get(0).get("works_id"));
            if (cbGovern.isChecked()) {
                Val.put("govern1", ModelCheckboxCheck.checkboxReturnCheck(cbGovern1));
                Val.put("govern2", ModelCheckboxCheck.checkboxReturnCheck(cbGovern2));
                Val.put("govern3", ModelCheckboxCheck.checkboxReturnCheck(cbGovern3));
                Val.put("govern4", ModelCheckboxCheck.checkboxReturnCheck(cbGovern4));
                if (cbGovern4.isChecked()) {
                    Val.put("govern_another", etAnotherGovern.getText().toString());
                } else {
                    Val.put("govern_another", "");
                }

            } else {
                Val.put("govern1", "");
                Val.put("govern2", "");
                Val.put("govern3", "");
                Val.put("govern4", "");
                Val.put("govern_another", "");
            }
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_job_govern", "works_id", TestList.get(0).get("works_id"));
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_job_govern", Val);
            } else {
                db.UpdateData("population_job_govern", Val, "works_id", TestList.get(0).get("works_id"));
            }
            //////////////////////////////////// Population Work Govern Insert/Update ////////////////////////////////////

            Val = new ContentValues();
            TestList = db.SelectWhereData("population_works", "population_idcard", etPersonalID.getText().toString());
            Val.put("works_id", TestList.get(0).get("works_id"));
            if (cbPrivate.isChecked()) {
                Val.put("private1", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate1));
                Val.put("private2", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate2));
                Val.put("private3", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate3));
                Val.put("private4", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate4));
                Val.put("private5", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate5));
                Val.put("private6", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate6));
                Val.put("private7", ModelCheckboxCheck.checkboxReturnCheck(cbPrivate7));
                if (cbPrivate7.isChecked()) {
                    Val.put("private_another", etAnotherPrivate.getText().toString());
                } else {
                    Val.put("private_another", "");
                }

            } else {
                Val.put("private1", "");
                Val.put("private2", "");
                Val.put("private3", "");
                Val.put("private4", "");
                Val.put("private5", "");
                Val.put("private6", "");
                Val.put("private7", "");
                Val.put("private_another", "");
            }
            Val.put("upd_by", "");
            Val.put("upd_date", date);
            Val.put("ACTIVE", "Y");
            DwellerList = db.SelectWhereData("population_job_private", "works_id", TestList.get(0).get("works_id"));
            if (DwellerList.isEmpty()) {
                Val.put("cr_by", "JuJiiz");
                Val.put("cr_date", date);
                db.InsertData("population_job_private", Val);
            } else {
                db.UpdateData("population_job_private", Val, "works_id", TestList.get(0).get("works_id"));
            }
            //////////////////////////////////// Population Work Private Insert/Update ////////////////////////////////////
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == cbAgri)
            ModelShowHideLayout.checkboxShowHide(cbAgri, loAgri);
        if (compoundButton == cbPet)
            ModelShowHideLayout.checkboxShowHide(cbPet, loPet);
        if (compoundButton == cbGovern)
            ModelShowHideLayout.checkboxShowHide(cbGovern, loGovern);
        if (compoundButton == cbPrivate)
            ModelShowHideLayout.checkboxShowHide(cbPrivate, loPrivate);
        if (compoundButton == cbCong5)
            ModelShowHideLayout.checkboxShowHide(cbCong5, loAnotherCong);
        if (compoundButton == cbCont11)
            ModelShowHideLayout.checkboxShowHide(cbCont11, loAnotherCont);
        if (compoundButton == cbAgri8)
            ModelShowHideLayout.checkboxShowHide(cbAgri8, loAnotherAgri);
        if (compoundButton == cbPet9)
            ModelShowHideLayout.checkboxShowHide(cbPet9, loAnotherAgri);
        if (compoundButton == cbGovern5)
            ModelShowHideLayout.checkboxShowHide(cbGovern5, loAnotherAgri);
        if (compoundButton == cbPrivate7)
            ModelShowHideLayout.checkboxShowHide(cbPrivate7, loAnotherAgri);


        /*if (compoundButton == rbInRegister)
            ModelShowHideLayout.radiobuttonShowHide(rbInRegister, loInRegister);*/
        if (compoundButton == rbNotInRegister)
            ModelShowHideLayout.radiobuttonShowHide(rbNotInRegister, loNotInRegister);
        if (compoundButton == rbNotInHousehold)
            ModelShowHideLayout.radiobuttonShowHide(rbNotInHousehold, loNotInHousehold);
        if (compoundButton == rbJGCareer)
            ModelShowHideLayout.radiobuttonShowHide(rbJGCareer, loCareer);
        if (compoundButton == rbICMonth)
            ModelShowHideLayout.radiobuttonShowHide(rbICMonth, loICMonth);
        if (compoundButton == rbICYear)
            ModelShowHideLayout.radiobuttonShowHide(rbICYear, loICYear);
        if (compoundButton == rbCongenitalYes)
            ModelShowHideLayout.radiobuttonShowHide(rbCongenitalYes, loCongenital);
        if (compoundButton == rbContagiousYes)
            ModelShowHideLayout.radiobuttonShowHide(rbContagiousYes, loContagious);
        if (compoundButton == rbAllergicYes)
            ModelShowHideLayout.radiobuttonShowHide(rbAllergicYes, loAllergic);
        if (compoundButton == rbDisabledYes)
            ModelShowHideLayout.radiobuttonShowHide(rbDisabledYes, loDisabled);
        if (compoundButton == rbInStudy)
            ModelShowHideLayout.radiobuttonShowHide(rbInStudy, loInStudy);
        if (compoundButton == rbGraduated)
            ModelShowHideLayout.radiobuttonShowHide(rbGraduated, loGraduated);
        if (compoundButton == rbExpertiseYes)
            ModelShowHideLayout.radiobuttonShowHide(rbExpertiseYes, loExpertise);
        ModelShowHideLayout.radiobuttonShowHide(rbExpertiseYes, loExpertiseText);
        if (compoundButton == rbAnotherReligion)
            ModelShowHideLayout.radiobuttonShowHide(rbAnotherReligion, loAnotherReligion);
        if (compoundButton == rbTransportationYes)
            ModelShowHideLayout.radiobuttonShowHide(rbTransportationYes, loTransportation);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSavingData) {
            if (!etPersonalID.getText().toString().equals("") && !etFirstName.getText().toString().equals("") && !etLastName.getText().toString().equals("")) {
                updateData();
                Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "กรุณากรอกข้อมูลประชากร", Toast.LENGTH_SHORT).show();
            }
        }
        if (view == btnAddProperty) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            //inflate view for alertdialog since we are using multiple views inside a viewgroup (root = Layout top-level) (linear, relative, framelayout etc..)
            View v = inflater.inflate(R.layout.my_alert_dialog, (ViewGroup) findViewById(R.id.root));

            Button btnLand = (Button) v.findViewById(R.id.btnLand);
            btnLand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LandFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("LandID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    getApplicationContext().startActivity(intent);
                }
            });
            Button btnVehicle = (Button) v.findViewById(R.id.btnVehicle);
            btnVehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), VehicalFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("VehicleID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    getApplicationContext().startActivity(intent);
                }
            });
            Button btnPet = (Button) v.findViewById(R.id.btnPet);
            btnPet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PetFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("PetID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    getApplicationContext().startActivity(intent);
                }
            });
            Button btnAnimal = (Button) v.findViewById(R.id.btnAnimal);
            btnAnimal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AnimalFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("AnimalID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    getApplicationContext().startActivity(intent);
                }
            });
            alert.setView(v);
            alert.show();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> Item = (HashMap<String, String>) lvProperty.getItemAtPosition(position);
        SelectedIDItem = Item.get("ID").toString();
        Intent intent = null;
        if (Item.get("PropType").toString().equals("ที่ดิน")) {
            intent = new Intent(getApplicationContext(), LandFormActivity.class);
            intent.putExtra("LandID", SelectedIDItem);
        } else if (Item.get("PropType").toString().equals("ยานพาหนะ")) {
            intent = new Intent(getApplicationContext(), VehicalFormActivity.class);
            intent.putExtra("VehicleID", SelectedIDItem);
        } else if (Item.get("PropType").toString().equals("สัตว์เลี้ยง")) {
            intent = new Intent(getApplicationContext(), PetFormActivity.class);
            intent.putExtra("PetID", SelectedIDItem);
        } else if (Item.get("PropType").toString().equals("ปศุสัตว์")) {
            intent = new Intent(getApplicationContext(), AnimalFormActivity.class);
            intent.putExtra("AnimalID", SelectedIDItem);
        }
        intent.putExtra("PersonID", PersonID);
        intent.putExtra("HouseID", HouseID);
        startActivity(intent);
    }
}
