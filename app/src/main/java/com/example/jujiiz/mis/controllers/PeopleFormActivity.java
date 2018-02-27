package com.example.jujiiz.mis.controllers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.CustomDialog;
import com.example.jujiiz.mis.models.ModelCheckForm;
import com.example.jujiiz.mis.models.ModelCheckboxCheck;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelGetJson;
import com.example.jujiiz.mis.models.ModelLoadJsonRaw;
import com.example.jujiiz.mis.models.ModelSetting;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import org.json.JSONArray;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class PeopleFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
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
    RadioButton rbIHProvince, rbIRProvince, rbIRCountry, rbIHCountry;
    LinearLayout loNationality, loAnotherPrefix, loBloodType, loInRegister, loNotInRegister, loNotInHousehold, loCareer, loAgri, loAnotherAgri, loPet, loAnotherPet, loGovern, loAnotherGovern, loPrivate, loAnotherPrivate, loICMonth, loICYear, loCongenital, loContagious, loAllergic, loDisabled, loInStudy, loGraduated, loExpertise, loAnotherReligion, loTransportation, loExpertiseText, loAnotherCong, loAnotherCont, loProperty, loLiveHere;
    Spinner spNationality, spPrefix, spBloodType, spMaritalStatus, spVillageName, spInStudy, spGraduated, spExpertise, spContributor, spIRProvince, spIHProvince, spIHCountry, spIRCountry;
    EditText etNationality, etFirstName, etLastName, etAnotherPrefix, etPersonalID, etBirtDate, etHeight, etWeight, etBloodType, etTel, etHNo, etHID, etAnotherAgri, etAnotherPet, etAnotherGovern, etAnotherPrivate, etICMonth, etICYear, etAllergic, etAnotherReligion, etDate, etExpertise, etAnotherCong, etAnotherCont;
    CheckBox cbAgri, cbAgri1, cbAgri2, cbAgri3, cbAgri4, cbAgri5, cbAgri6, cbAgri7, cbAgri8;
    CheckBox cbPet, cbPet1, cbPet2, cbPet3, cbPet4, cbPet5, cbPet6, cbPet7, cbPet8, cbPet9;
    CheckBox cbGovern, cbGovern1, cbGovern2, cbGovern3, cbGovern4, cbGovern5;
    CheckBox cbPrivate, cbPrivate1, cbPrivate2, cbPrivate3, cbPrivate4, cbPrivate5, cbPrivate6, cbPrivate7;
    CheckBox cbCong1, cbCong2, cbCong3, cbCong4, cbCong5, cbCong6, cbCong7, cbCong8, cbCong9, cbCong10, cbCong11, cbCong12, cbCong13, cbCong14, cbCong15, cbCong16, cbCong17, cbCong18, cbCong19, cbCong20, cbCong21, cbCong22, cbCong23, cbCong24, cbCong25, cbCong26;
    CheckBox cbCont1, cbCont2, cbCont3, cbCont4, cbCont5, cbCont6, cbCont7, cbCont8, cbCont9, cbCont10, cbCont11, cbCont12, cbCont13, cbCont14, cbCont15, cbCont16, cbCont17, cbCont18, cbCont19, cbCont20, cbCont21, cbCont22, cbCont23, cbCont24, cbCont25, cbCont26, cbCont27, cbCont28, cbCont29, cbCont30, cbCont31, cbCont32, cbCont33, cbCont34, cbCont35, cbCont36, cbCont37, cbCont38, cbCont39, cbCont40, cbCont41, cbCont42, cbCont43, cbCont44, cbCont45, cbCont46, cbCont47, cbCont48, cbCont49, cbCont50, cbCont51;
    CheckBox cbDisabled1, cbDisabled2, cbDisabled3, cbDisabled4, cbDisabled5, cbDisabled6;
    CheckBox cbTrans1, cbTrans2, cbTrans3, cbTrans4;
    Button btnSavingData, btnAddProperty, btnDatePick;
    Button btnIRCSearch, btnIRPSearch, btnIHCSearch, btnIHPSearch;
    ListView lvProperty;
    ScrollView svPopulation;

    myDBClass db = new myDBClass(this);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    ContentValues Val;
    ArrayList<HashMap<String, String>> NationalityList, PrefixList, PersonList, TestList, DwellerList, WorkList, AgriList, PetList, GovernList, PrivateList, CongList, ContList, DisabledList, TransList;
    ArrayList<HashMap<String, String>> PropertyActive, PLandList, PVehicleList, PPetList, PAnimalList;
    ArrayList<HashMap<String, String>> ProvinceList, CountryList;
    ArrayList<String> Prefix = new ArrayList<String>();
    ArrayList<String> Nationality = new ArrayList<String>();
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayList<String> Province = new ArrayList<String>();
    ArrayList<String> Country = new ArrayList<String>();
    ArrayAdapter<String> prefixArrayAdapter, nationalityArrayAdapter, dwellerArrayAdapter, bloodArrayAdapter, maritalArrayAdapter, educationIArrayAdapter, educationGArrayAdapter, expertiseArrayAdapter, provinceArrayAdapter, countryArrayAdapter;
    String PersonID, HouseID;
    String SelectedIDItem, SelectedNameItem;

    private DatePickerDialog fromDatePickerDialog;

    String[] spBloodGroupArray = {"กรุณาเลือก", "ไม่ทราบ", "A", "B", "AB", "O", "A Rh+", "B Rh+", "B Rh-", "AB Rh+", "AB Rh-", "O Rh+", "O Rh+-"};
    String[] spMaritalStatusArray = {"กรุณาเลือก", "สมรส", "โสด", "หย่าร้าง", "หม้าย", "แยกกันอยู่"};
    String[] spEducationArray = {"กรุณาเลือก", "ระดับก่อนประถมศึกษา", "ระดับประถมศึกษา", "ระดับมัธยมศึกษาตอนต้น", "ระดับมัธยมศึกษาตอนปลาย", "ระดับอนุปริญญา", "ระดับปริญญาตรี", "ระดับปริญญาโท", "ระดับปริญญาเอก"};
    String[] spExpertiseArray = {"กรุณาเลือก", "สาขาการเกษตรและพัฒนาชนบท", "สาขาอุตสาหกรรมก่อสร้าง", "สาขาการศึกษา", "สาขาพลังงาน", "สาขาสิ่งแวดล้อม", "สาขาการเงิน", "สาขาสาธารณสุข", "สาขาอุตสาหกรรม", "สาขาเบ็ดเตล็ด", "สาขาประชากร", "สาขาเทคโนโลยีสารสนเทศและการสื่อสาร", "สาขาการท่องเที่ยว", "สาขาการขนส่ง", "สาขาการพัฒนาเมือง", "สาขาการประปาและสุขาภิบาล", "สาขาภาษาต่างประเทศ"};

    String[] spCountryArray, spProvinceArray;

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
        setDateTimeField();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListView();
    }

    private void init() {
        lvProperty = findViewById(R.id.lvProperty);
        lvProperty.setOnItemClickListener(this);
        lvProperty.setOnItemLongClickListener(this);
        /*lvProperty.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/

        svPopulation = findViewById(R.id.svPopulation);

        btnSavingData = findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
        btnAddProperty = findViewById(R.id.btnAddProperty);
        btnAddProperty.setOnClickListener(this);
        btnDatePick = findViewById(R.id.btnDatePick);
        btnDatePick.setOnClickListener(this);

        btnIRCSearch = findViewById(R.id.btnIRCSearch);
        btnIRCSearch.setOnClickListener(this);
        btnIRPSearch = findViewById(R.id.btnIRPSearch);
        btnIRPSearch.setOnClickListener(this);
        btnIHCSearch = findViewById(R.id.btnIHCSearch);
        btnIHCSearch.setOnClickListener(this);
        btnIHPSearch = findViewById(R.id.btnIHPSearch);
        btnIHPSearch.setOnClickListener(this);

        loNationality = findViewById(R.id.loNationality);
        loAnotherPrefix = findViewById(R.id.loAnotherPrefix);
        loBloodType = findViewById(R.id.loBloodType);
        loInRegister = findViewById(R.id.loInRegister);
        loNotInRegister = findViewById(R.id.loNotInRegister);
        loNotInHousehold = findViewById(R.id.loNotInHousehold);
        loCareer = findViewById(R.id.loCareer);
        loAgri = findViewById(R.id.loAgri);
        loAnotherAgri = findViewById(R.id.loAnotherAgri);
        loPet = findViewById(R.id.loPet);
        loAnotherPet = findViewById(R.id.loAnotherPet);
        loGovern = findViewById(R.id.loGovern);
        loAnotherGovern = findViewById(R.id.loAnotherGovern);
        loPrivate = findViewById(R.id.loPrivate);
        loAnotherPrivate = findViewById(R.id.loAnotherPrivate);
        loICMonth = findViewById(R.id.loICMonth);
        loICYear = findViewById(R.id.loICYear);
        loCongenital = findViewById(R.id.loCongenital);
        loContagious = findViewById(R.id.loContagious);
        loAllergic = findViewById(R.id.loAllergic);
        loDisabled = findViewById(R.id.loDisabled);
        loInStudy = findViewById(R.id.loInStudy);
        loGraduated = findViewById(R.id.loGraduated);
        loExpertise = findViewById(R.id.loExpertise);
        loExpertiseText = findViewById(R.id.loExpertiseText);
        loAnotherReligion = findViewById(R.id.loAnotherReligion);
        loTransportation = findViewById(R.id.loTransportation);
        loAnotherCong = findViewById(R.id.loAnotherCong);
        loAnotherCont = findViewById(R.id.loAnotherCont);
        loProperty = findViewById(R.id.loProperty);
        loLiveHere = findViewById(R.id.loLiveHere);

        spNationality = findViewById(R.id.spNationality);
        spNationality.setOnItemSelectedListener(this);
        spPrefix = findViewById(R.id.spPrefix);
        spBloodType = findViewById(R.id.spBloodType);
        spMaritalStatus = findViewById(R.id.spMaritalStatus);
        spVillageName = findViewById(R.id.spVillageName);
        spInStudy = findViewById(R.id.spInStudy);
        spGraduated = findViewById(R.id.spGraduated);
        spExpertise = findViewById(R.id.spExpertise);
        spContributor = findViewById(R.id.spContributor);

        etNationality = findViewById(R.id.etNationality);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAnotherPrefix = findViewById(R.id.etAnotherPrefix);
        etPersonalID = findViewById(R.id.etPersonalID);
        etBirtDate = findViewById(R.id.etBirtDate);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        etBloodType = findViewById(R.id.etBloodType);
        etTel = findViewById(R.id.etTel);
        etHNo = findViewById(R.id.etHNo);
        etHID = findViewById(R.id.etHID);
        etAnotherAgri = findViewById(R.id.etAnotherAgri);
        etAnotherPet = findViewById(R.id.etAnotherPet);
        etAnotherGovern = findViewById(R.id.etAnotherGovern);
        etAnotherPrivate = findViewById(R.id.etAnotherPrivate);
        etICMonth = findViewById(R.id.etICMonth);
        etICYear = findViewById(R.id.etICYear);
        etAllergic = findViewById(R.id.etAllergic);
        etAnotherReligion = findViewById(R.id.etAnotherReligion);
        etDate = findViewById(R.id.etDate);

        spIHCountry = findViewById(R.id.spIHCountry);
        spIHCountry.setEnabled(false);
        spIHProvince = findViewById(R.id.spIHProvince);
        spIHProvince.setEnabled(false);
        spIRCountry = findViewById(R.id.spIRCountry);
        spIRCountry.setEnabled(false);
        spIRProvince = findViewById(R.id.spIRProvince);
        spIRProvince.setEnabled(false);

        etExpertise = findViewById(R.id.etExpertise);
        etAnotherCong = findViewById(R.id.etAnotherCong);
        etAnotherCont = findViewById(R.id.etAnotherCont);

        cbAgri = findViewById(R.id.cbAgri);
        cbAgri1 = findViewById(R.id.cbAgri1);
        cbAgri2 = findViewById(R.id.cbAgri2);
        cbAgri3 = findViewById(R.id.cbAgri3);
        cbAgri4 = findViewById(R.id.cbAgri4);
        cbAgri5 = findViewById(R.id.cbAgri5);
        cbAgri6 = findViewById(R.id.cbAgri6);
        cbAgri7 = findViewById(R.id.cbAgri7);
        cbAgri8 = findViewById(R.id.cbAgri8);
        cbPet = findViewById(R.id.cbPet);
        cbPet1 = findViewById(R.id.cbPet1);
        cbPet2 = findViewById(R.id.cbPet2);
        cbPet3 = findViewById(R.id.cbPet3);
        cbPet4 = findViewById(R.id.cbPet4);
        cbPet5 = findViewById(R.id.cbPet5);
        cbPet6 = findViewById(R.id.cbPet6);
        cbPet7 = findViewById(R.id.cbPet7);
        cbPet8 = findViewById(R.id.cbPet8);
        cbPet9 = findViewById(R.id.cbPet9);
        cbGovern = findViewById(R.id.cbGovern);
        cbGovern1 = findViewById(R.id.cbGovern1);
        cbGovern2 = findViewById(R.id.cbGovern2);
        cbGovern3 = findViewById(R.id.cbGovern3);
        cbGovern4 = findViewById(R.id.cbGovern4);
        cbGovern5 = findViewById(R.id.cbGovern5);
        cbPrivate = findViewById(R.id.cbPrivate);
        cbPrivate1 = findViewById(R.id.cbPrivate1);
        cbPrivate2 = findViewById(R.id.cbPrivate2);
        cbPrivate3 = findViewById(R.id.cbPrivate3);
        cbPrivate4 = findViewById(R.id.cbPrivate4);
        cbPrivate5 = findViewById(R.id.cbPrivate5);
        cbPrivate6 = findViewById(R.id.cbPrivate6);
        cbPrivate7 = findViewById(R.id.cbPrivate7);
        cbCong1 = findViewById(R.id.cbCong1);
        cbCong2 = findViewById(R.id.cbCong2);
        cbCong3 = findViewById(R.id.cbCong3);
        cbCong4 = findViewById(R.id.cbCong4);
        cbCong5 = findViewById(R.id.cbCong5);
        cbCong6 = findViewById(R.id.cbCong6);
        cbCong7 = findViewById(R.id.cbCong7);
        cbCong8 = findViewById(R.id.cbCong8);
        cbCong9 = findViewById(R.id.cbCong9);
        cbCong10 = findViewById(R.id.cbCong10);
        cbCong11 = findViewById(R.id.cbCong11);
        cbCong12 = findViewById(R.id.cbCong12);
        cbCong13 = findViewById(R.id.cbCong13);
        cbCong14 = findViewById(R.id.cbCong14);
        cbCong15 = findViewById(R.id.cbCong15);
        cbCong16 = findViewById(R.id.cbCong17);
        cbCong17 = findViewById(R.id.cbCong18);
        cbCong18 = findViewById(R.id.cbCong18);
        cbCong19 = findViewById(R.id.cbCong19);
        cbCong20 = findViewById(R.id.cbCong20);
        cbCong21 = findViewById(R.id.cbCong21);
        cbCong22 = findViewById(R.id.cbCong22);
        cbCong23 = findViewById(R.id.cbCong23);
        cbCong24 = findViewById(R.id.cbCong24);
        cbCong25 = findViewById(R.id.cbCong25);
        cbCong26 = findViewById(R.id.cbCong26);
        cbCont1 = findViewById(R.id.cbCont1);
        cbCont2 = findViewById(R.id.cbCont2);
        cbCont3 = findViewById(R.id.cbCont3);
        cbCont4 = findViewById(R.id.cbCont4);
        cbCont5 = findViewById(R.id.cbCont5);
        cbCont6 = findViewById(R.id.cbCont6);
        cbCont7 = findViewById(R.id.cbCont7);
        cbCont8 = findViewById(R.id.cbCont8);
        cbCont9 = findViewById(R.id.cbCont9);
        cbCont10 = findViewById(R.id.cbCont10);
        cbCont11 = findViewById(R.id.cbCont11);
        cbCont12 = findViewById(R.id.cbCont12);
        cbCont13 = findViewById(R.id.cbCont13);
        cbCont14 = findViewById(R.id.cbCont14);
        cbCont15 = findViewById(R.id.cbCont15);
        cbCont16 = findViewById(R.id.cbCont16);
        cbCont17 = findViewById(R.id.cbCont17);
        cbCont18 = findViewById(R.id.cbCont18);
        cbCont19 = findViewById(R.id.cbCont19);
        cbCont20 = findViewById(R.id.cbCont20);
        cbCont21 = findViewById(R.id.cbCont21);
        cbCont22 = findViewById(R.id.cbCont22);
        cbCont23 = findViewById(R.id.cbCont23);
        cbCont24 = findViewById(R.id.cbCont24);
        cbCont25 = findViewById(R.id.cbCont25);
        cbCont26 = findViewById(R.id.cbCont26);
        cbCont27 = findViewById(R.id.cbCont27);
        cbCont28 = findViewById(R.id.cbCont28);
        cbCont29 = findViewById(R.id.cbCont29);
        cbCont30 = findViewById(R.id.cbCont30);
        cbCont31 = findViewById(R.id.cbCont31);
        cbCont32 = findViewById(R.id.cbCont32);
        cbCont33 = findViewById(R.id.cbCont33);
        cbCont34 = findViewById(R.id.cbCont34);
        cbCont35 = findViewById(R.id.cbCont35);
        cbCont36 = findViewById(R.id.cbCont36);
        cbCont37 = findViewById(R.id.cbCont37);
        cbCont38 = findViewById(R.id.cbCont38);
        cbCont39 = findViewById(R.id.cbCont39);
        cbCont40 = findViewById(R.id.cbCont40);
        cbCont41 = findViewById(R.id.cbCont41);
        cbCont42 = findViewById(R.id.cbCont42);
        cbCont43 = findViewById(R.id.cbCont43);
        cbCont44 = findViewById(R.id.cbCont44);
        cbCont45 = findViewById(R.id.cbCont45);
        cbCont46 = findViewById(R.id.cbCont46);
        cbCont47 = findViewById(R.id.cbCont47);
        cbCont48 = findViewById(R.id.cbCont48);
        cbCont49 = findViewById(R.id.cbCont49);
        cbCont50 = findViewById(R.id.cbCont50);
        cbCont51 = findViewById(R.id.cbCont51);
        cbDisabled1 = findViewById(R.id.cbDisabled1);
        cbDisabled2 = findViewById(R.id.cbDisabled2);
        cbDisabled3 = findViewById(R.id.cbDisabled3);
        cbDisabled4 = findViewById(R.id.cbDisabled4);
        cbDisabled5 = findViewById(R.id.cbDisabled5);
        cbDisabled6 = findViewById(R.id.cbDisabled6);
        cbTrans1 = findViewById(R.id.cbTrans1);
        cbTrans2 = findViewById(R.id.cbTrans2);
        cbTrans3 = findViewById(R.id.cbTrans3);
        cbTrans4 = findViewById(R.id.cbTrans4);

        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbAlive = findViewById(R.id.rbAlive);
        rbDead = findViewById(R.id.rbDead);
        rbInRegister = findViewById(R.id.rbInRegister);
        rbNotInRegister = findViewById(R.id.rbNotInRegister);
        rbInHousehold = findViewById(R.id.rbInHousehold);
        rbNotInHousehold = findViewById(R.id.rbNotInHousehold);
        rbStatusOwner = findViewById(R.id.rbStatusOwner);
        rbStatusDweller = findViewById(R.id.rbStatusDweller);
        rbJGStudent = findViewById(R.id.rbJGStudent);
        rbJGCareer = findViewById(R.id.rbJGCareer);
        rbJGNoJob = findViewById(R.id.rbJGNoJob);
        rbICMonth = findViewById(R.id.rbICMonth);
        rbICYear = findViewById(R.id.rbICYear);
        rbDebtNo = findViewById(R.id.rbDebtNo);
        rbDebtYes = findViewById(R.id.rbDebtYes);
        rbSavingNo = findViewById(R.id.rbSavingNo);
        rbSavingYes = findViewById(R.id.rbSavingYes);
        rbCongenitalNo = findViewById(R.id.rbCongenitalNo);
        rbCongenitalYes = findViewById(R.id.rbCongenitalYes);
        rbContagiousNo = findViewById(R.id.rbContagiousNo);
        rbContagiousYes = findViewById(R.id.rbContagiousYes);
        rbAllergicNo = findViewById(R.id.rbAllergicNo);
        rbAllergicYes = findViewById(R.id.rbAllergicYes);
        rbDisabledNo = findViewById(R.id.rbDisabledNo);
        rbDisabledYes = findViewById(R.id.rbDisabledYes);
        rbDisadvantageNo = findViewById(R.id.rbDisadvantageNo);
        rbDisadvantageYes = findViewById(R.id.rbDisadvantageYes);
        rbSubAlNo = findViewById(R.id.rbSubAlNo);
        rbSubAlYes = findViewById(R.id.rbSubAlYes);
        rbNoStudy = findViewById(R.id.rbNoStudy);
        rbInStudy = findViewById(R.id.rbInStudy);
        rbGraduated = findViewById(R.id.rbGraduated);
        rbLiteracyYes = findViewById(R.id.rbLiteracyYes);
        rbLiteracyNo = findViewById(R.id.rbLiteracyNo);
        rbTechnologyNo = findViewById(R.id.rbTechnologyNo);
        rbTechnologyYes = findViewById(R.id.rbTechnologyYes);
        rbExpertiseNo = findViewById(R.id.rbExpertiseNo);
        rbExpertiseYes = findViewById(R.id.rbExpertiseYes);
        rbBuddhismReligion = findViewById(R.id.rbBuddhismReligion);
        rbChristReligion = findViewById(R.id.rbChristReligion);
        rbIslamicReligion = findViewById(R.id.rbIslamicReligion);
        rbAnotherReligion = findViewById(R.id.rbAnotherReligion);
        rbParticipationNo = findViewById(R.id.rbParticipationNo);
        rbParticipationYes = findViewById(R.id.rbParticipationYes);
        rbElectionAlway = findViewById(R.id.rbElectionAlway);
        rbElectionSometime = findViewById(R.id.rbElectionSometime);
        rbElectionNever = findViewById(R.id.rbElectionNever);
        rbTransportationNo = findViewById(R.id.rbTransportationNo);
        rbTransportationYes = findViewById(R.id.rbTransportationYes);
        rbIHProvince = findViewById(R.id.rbIHProvince);
        rbIRProvince = findViewById(R.id.rbIRProvince);
        rbIHCountry = findViewById(R.id.rbIHCountry);
        rbIRCountry = findViewById(R.id.rbIRCountry);

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
        cbCong26.setOnCheckedChangeListener(this);
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
        cbCont51.setOnCheckedChangeListener(this);
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

        rbIHProvince.setOnCheckedChangeListener(this);
        rbIRProvince.setOnCheckedChangeListener(this);
        rbIHCountry.setOnCheckedChangeListener(this);
        rbIRCountry.setOnCheckedChangeListener(this);
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year + 543, monthOfYear, dayOfMonth);
                etBirtDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    private void setSpinner() {

        NationalityList = db.SelectData("nationality");
        if (!NationalityList.isEmpty()) {
            Nationality.add("กรุณาเลือก");
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
            Prefix.add("กรุณาเลือก");
            for (int i = 0; i < PrefixList.size(); i++) {
                String strPrefix = PrefixList.get(i).get("prename_detail");
                Prefix.add(strPrefix);
            }
            String[] spPrefixArray = Prefix.toArray(new String[0]);
            prefixArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spPrefixArray, spPrefix);
        }

        InputStream inputStreamCountry = getResources().openRawResource(R.raw.country_en);
        JSONArray countryArray = ModelLoadJsonRaw.loadJsonFile(inputStreamCountry, "countries", "country");
        CountryList = ModelGetJson.JsonArraytoArrayList(countryArray);
        if (!CountryList.isEmpty()) {
            Country.add("กรุณาเลือก");
            for (int i = 0; i < CountryList.size(); i++) {
                String strProvince = CountryList.get(i).get("countryName");
                Country.add(strProvince);
            }
            spCountryArray = Country.toArray(new String[0]);
            countryArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spCountryArray, spIHCountry);
            countryArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spCountryArray, spIRCountry);
        }

        InputStream inputStreamProvince = getResources().openRawResource(R.raw.province_th);
        JSONArray provinceArray = ModelLoadJsonRaw.loadJsonFile(inputStreamProvince, "th", "changwats");
        ProvinceList = ModelGetJson.JsonArraytoArrayList(provinceArray);
        if (!ProvinceList.isEmpty()) {
            Province.add("กรุณาเลือก");
            for (int i = 0; i < ProvinceList.size(); i++) {
                String strProvince = ProvinceList.get(i).get("name");
                Province.add(strProvince);
            }
            spProvinceArray = Province.toArray(new String[0]);
            provinceArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spProvinceArray, spIHProvince);
            provinceArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spProvinceArray, spIRProvince);
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

        bloodArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spBloodGroupArray, spBloodType);
        maritalArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spMaritalStatusArray, spMaritalStatus);
        educationIArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spEducationArray, spInStudy);
        educationGArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spEducationArray, spGraduated);
        expertiseArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spExpertiseArray, spExpertise);
    }

    private void setField() {
        DateFormat originalFormat;
        DateFormat targetFormat;
        Date odate = null;
        String formattedDate = "0001-01-01";
        if (!PersonID.equals("Nope")) {
            PersonList = db.SelectWhereData("population", "population_idcard", PersonID);
            if (!PersonList.isEmpty()) {
                for (int i = 0; i < PersonList.size(); i++) {
                    if (PersonList.get(i).get("house_id").equals(HouseID)) {
                        if (PersonList.get(i).get("survey_status").equals("1")) {
                            loProperty.setVisibility(View.VISIBLE);
                        } else {
                            loProperty.setVisibility(View.GONE);
                        }
                        int spinnerPositionNat = nationalityArrayAdapter.getPosition(PersonList.get(i).get("nationality"));
                        spNationality.setSelection(spinnerPositionNat);

                        etFirstName.setText(PersonList.get(i).get("firstname"));
                        etLastName.setText(PersonList.get(i).get("lastname"));

                        int spinnerPositionPrefix = prefixArrayAdapter.getPosition(PersonList.get(i).get("prename"));
                        spPrefix.setSelection(spinnerPositionPrefix);

                        if (PersonList.get(i).get("sex").equals("M")) {
                            rbMale.setChecked(true);
                        }
                        if (PersonList.get(i).get("sex").equals("F")) {
                            rbFemale.setChecked(true);
                        }
                        etPersonalID.setText(PersonList.get(i).get("population_idcard"));

                        originalFormat = new SimpleDateFormat("yyyy-MM-dd");
                        targetFormat = new SimpleDateFormat("dd/MM/yyy");
                        try {
                            odate = originalFormat.parse(PersonList.get(i).get("birthdate"));

                            if (odate != null) {
                                formattedDate = targetFormat.format(odate);
                            } else {
                                formattedDate = "01/01/0001";
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            formattedDate = PersonList.get(i).get("birthdate");
                        }

                        etBirtDate.setText(formattedDate);
                        etHeight.setText(PersonList.get(i).get("height"));
                        etWeight.setText(PersonList.get(i).get("weight"));


                        if (!PersonList.get(i).get("bloodgroup").equals("")) {
                            int spinnerPositionBlood = bloodArrayAdapter.getPosition(PersonList.get(i).get("bloodgroup"));
                            spBloodType.setSelection(spinnerPositionBlood);
                        }

                        if (PersonList.get(i).get("living").equals("0")) {
                            rbAlive.setChecked(true);
                        } else if (PersonList.get(i).get("living").equals("1")) {
                            rbDead.setChecked(true);
                        }

                        if (!PersonList.get(i).get("maritalstatus").equals("")) {
                            int spinnerPositionMari = maritalArrayAdapter.getPosition(PersonList.get(i).get("maritalstatus"));
                            spMaritalStatus.setSelection(spinnerPositionMari);
                        }

                        etTel.setText(PersonList.get(i).get("tel"));

                        if (PersonList.get(i).get("residence_status").equals("1")) {
                            rbInRegister.setChecked(true);
                        } else if (PersonList.get(i).get("residence_status").equals("0")) {
                            rbNotInRegister.setChecked(true);
                            int spinnerPositionIRProvince = provinceArrayAdapter.getPosition(PersonList.get(i).get("latentpop_province"));
                            spIRProvince.setSelection(spinnerPositionIRProvince);
                            int spinnerPositionIRCountry = countryArrayAdapter.getPosition(PersonList.get(i).get("latentpop_country"));
                            spIRCountry.setSelection(spinnerPositionIRCountry);
                        }

                        if (PersonList.get(i).get("currentaddr").equals("0")) {
                            rbInHousehold.setChecked(true);
                        } else if (PersonList.get(i).get("currentaddr").equals("1")) {
                            rbNotInHousehold.setChecked(true);
                            int spinnerPositionIHProvince = provinceArrayAdapter.getPosition(PersonList.get(i).get("currentaddr_province"));
                            spIHProvince.setSelection(spinnerPositionIHProvince);
                            int spinnerPositionIHCountry = countryArrayAdapter.getPosition(PersonList.get(i).get("currentaddr_country"));
                            spIHCountry.setSelection(spinnerPositionIHCountry);
                        }

                        if (PersonList.get(i).get("dwellerstatus").equals("0")) {
                            rbStatusOwner.setChecked(true);
                        } else if (PersonList.get(i).get("dwellerstatus").equals("1")) {
                            rbStatusDweller.setChecked(true);
                        }

                        if (PersonList.get(i).get("income").equals("1")) {
                            rbICMonth.setChecked(true);
                            etICMonth.setText(PersonList.get(i).get("income_money"));
                        } else if (PersonList.get(i).get("income").equals("2")) {
                            rbICYear.setChecked(true);
                            etICYear.setText(PersonList.get(i).get("income_money"));
                        }

                        if (PersonList.get(i).get("dept").equals("1")) {
                            rbDebtNo.setChecked(true);
                        } else if (PersonList.get(i).get("dept").equals("2")) {
                            rbDebtYes.setChecked(true);
                        }

                        if (PersonList.get(i).get("saving").equals("1")) {
                            rbSavingNo.setChecked(true);
                        } else if (PersonList.get(i).get("saving").equals("2")) {
                            rbSavingYes.setChecked(true);
                        }

                        if (PersonList.get(i).get("allergichis").equals("1")) {
                            rbAllergicNo.setChecked(true);
                        } else if (PersonList.get(i).get("allergichis").equals("2")) {
                            rbAllergicYes.setChecked(true);
                            etAllergic.setText(PersonList.get(i).get("allergichis_detail"));
                        }

                        if (PersonList.get(i).get("disadvantage").equals("1")) {
                            rbDisadvantageNo.setChecked(true);
                        } else if (PersonList.get(i).get("disadvantage").equals("2")) {
                            rbDisadvantageYes.setChecked(true);
                        }

                        if (PersonList.get(i).get("sub_al").equals("1")) {
                            rbSubAlNo.setChecked(true);
                        } else if (PersonList.get(i).get("sub_al").equals("2")) {
                            rbSubAlYes.setChecked(true);
                        }

                        if (PersonList.get(i).get("education").equals("1")) {
                            rbNoStudy.setChecked(true);
                        } else if (PersonList.get(i).get("education").equals("2")) {
                            rbInStudy.setChecked(true);
                            if (!PersonList.get(i).get("education_class").equals("")) {
                                int spinnerPositionEduI = educationIArrayAdapter.getPosition(PersonList.get(i).get("education_class"));
                                spInStudy.setSelection(spinnerPositionEduI);
                            }
                        } else if (PersonList.get(i).get("education").equals("3")) {
                            rbGraduated.setChecked(true);
                            if (!PersonList.get(i).get("education_class").equals("")) {
                                int spinnerPositionEduG = educationGArrayAdapter.getPosition(PersonList.get(i).get("education_class"));
                                spGraduated.setSelection(spinnerPositionEduG);
                            }
                        }

                        if (PersonList.get(i).get("literacy").equals("1")) {
                            rbLiteracyYes.setChecked(true);
                        } else if (PersonList.get(i).get("literacy").equals("2")) {
                            rbLiteracyNo.setChecked(true);
                        }

                        if (PersonList.get(i).get("technology").equals("1")) {
                            rbTechnologyNo.setChecked(true);
                        } else if (PersonList.get(i).get("technology").equals("2")) {
                            rbTechnologyYes.setChecked(true);
                        }

                        if (PersonList.get(i).get("expertise").equals("1")) {
                            rbExpertiseNo.setChecked(true);
                        } else if (PersonList.get(i).get("expertise").equals("2")) {
                            rbExpertiseYes.setChecked(true);
                            if (!PersonList.get(i).get("expertise_name").equals("")) {
                                int spinnerPositionExp = expertiseArrayAdapter.getPosition(PersonList.get(i).get("expertise_name"));
                                spExpertise.setSelection(spinnerPositionExp);
                            }
                            etExpertise.setText(PersonList.get(i).get("expertise_detail"));
                        }

                        if (PersonList.get(i).get("religion").equals("1")) {
                            rbBuddhismReligion.setChecked(true);
                        } else if (PersonList.get(i).get("religion").equals("2")) {
                            rbChristReligion.setChecked(true);
                        } else if (PersonList.get(i).get("religion").equals("3")) {
                            rbIslamicReligion.setChecked(true);
                        } else if (PersonList.get(i).get("religion").equals("4")) {
                            rbAnotherReligion.setChecked(true);
                            etAnotherReligion.setText(PersonList.get(i).get("religion_another"));
                        }

                        if (PersonList.get(i).get("participation").equals("1")) {
                            rbParticipationNo.setChecked(true);
                        } else if (PersonList.get(i).get("participation").equals("2")) {
                            rbParticipationYes.setChecked(true);
                        }

                        if (PersonList.get(i).get("election").equals("1")) {
                            rbElectionAlway.setChecked(true);
                        } else if (PersonList.get(i).get("election").equals("2")) {
                            rbElectionSometime.setChecked(true);
                        } else if (PersonList.get(i).get("election").equals("3")) {
                            rbElectionNever.setChecked(true);
                        }
                        if (!PersonList.get(0).get("distributor").equals("")) {
                            int spinnerPositionContri = dwellerArrayAdapter.getPosition(PersonList.get(i).get("distributor"));
                            spContributor.setSelection(spinnerPositionContri);
                        }
                        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);

                        WorkList = db.SelectWhereData("population_works", "population_idcard", PersonList.get(i).get("population_idcard"));
                        if (!WorkList.isEmpty()) {
                            if (WorkList.get(0).get("works_type").equals("1")) {
                                rbJGStudent.setChecked(true);
                            } else if (WorkList.get(0).get("works_type").equals("2")) {
                                rbJGCareer.setChecked(true);
                            } else if (WorkList.get(0).get("works_type").equals("3")) {
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

                        CongList = db.SelectWhereData("population_congenitalhis", "population_idcard", PersonList.get(i).get("population_idcard"));
                        if (!CongList.isEmpty()) {
                            if (CongList.get(0).get("congh_type").equals("2")) {
                                rbCongenitalYes.setChecked(true);
                                ModelCheckboxCheck.checkboxSetCheck(cbCong1, CongList.get(0).get("congh1"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong2, CongList.get(0).get("congh2"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong3, CongList.get(0).get("congh3"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong4, CongList.get(0).get("congh4"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong5, CongList.get(0).get("congh5"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong6, CongList.get(0).get("congh6"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong7, CongList.get(0).get("congh7"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong8, CongList.get(0).get("congh8"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong9, CongList.get(0).get("congh9"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong10, CongList.get(0).get("congh10"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong11, CongList.get(0).get("congh11"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong12, CongList.get(0).get("congh12"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong13, CongList.get(0).get("congh13"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong14, CongList.get(0).get("congh14"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong15, CongList.get(0).get("congh15"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong16, CongList.get(0).get("congh16"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong17, CongList.get(0).get("congh17"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong18, CongList.get(0).get("congh18"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong19, CongList.get(0).get("congh19"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong20, CongList.get(0).get("congh20"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong21, CongList.get(0).get("congh21"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong22, CongList.get(0).get("congh22"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong23, CongList.get(0).get("congh23"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong24, CongList.get(0).get("congh24"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong25, CongList.get(0).get("congh25"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCong26, CongList.get(0).get("congh26"));
                                if (CongList.get(0).get("congh26").equals("1")) {
                                    etAnotherCong.setText(CongList.get(0).get("congh_another"));
                                }
                            } else if (CongList.get(0).get("congh_type").equals("1")) {
                                rbCongenitalNo.setChecked(true);
                            }
                        }

                        ContList = db.SelectWhereData("population_contagioushis", "population_idcard", PersonList.get(i).get("population_idcard"));
                        if (!ContList.isEmpty()) {
                            if (ContList.get(0).get("conth_type").equals("2")) {
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
                                ModelCheckboxCheck.checkboxSetCheck(cbCont12, ContList.get(0).get("conth12"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont13, ContList.get(0).get("conth13"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont14, ContList.get(0).get("conth14"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont15, ContList.get(0).get("conth15"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont16, ContList.get(0).get("conth16"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont17, ContList.get(0).get("conth17"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont18, ContList.get(0).get("conth18"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont19, ContList.get(0).get("conth19"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont20, ContList.get(0).get("conth20"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont21, ContList.get(0).get("conth21"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont22, ContList.get(0).get("conth22"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont23, ContList.get(0).get("conth23"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont24, ContList.get(0).get("conth24"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont25, ContList.get(0).get("conth25"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont26, ContList.get(0).get("conth26"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont27, ContList.get(0).get("conth27"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont28, ContList.get(0).get("conth28"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont29, ContList.get(0).get("conth29"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont30, ContList.get(0).get("conth30"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont31, ContList.get(0).get("conth31"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont32, ContList.get(0).get("conth32"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont33, ContList.get(0).get("conth33"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont34, ContList.get(0).get("conth34"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont35, ContList.get(0).get("conth35"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont36, ContList.get(0).get("conth36"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont37, ContList.get(0).get("conth37"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont38, ContList.get(0).get("conth38"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont39, ContList.get(0).get("conth39"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont40, ContList.get(0).get("conth40"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont41, ContList.get(0).get("conth41"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont42, ContList.get(0).get("conth42"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont43, ContList.get(0).get("conth43"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont44, ContList.get(0).get("conth44"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont45, ContList.get(0).get("conth45"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont46, ContList.get(0).get("conth46"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont47, ContList.get(0).get("conth47"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont48, ContList.get(0).get("conth48"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont49, ContList.get(0).get("conth49"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont50, ContList.get(0).get("conth50"));
                                ModelCheckboxCheck.checkboxSetCheck(cbCont51, ContList.get(0).get("conth51"));
                                if (ContList.get(0).get("conth51").equals("1")) {
                                    etAnotherCont.setText(ContList.get(0).get("conth_another"));
                                }
                            } else if (ContList.get(0).get("conth_type").equals("1")) {
                                rbContagiousNo.setChecked(true);
                            }
                        }

                        DisabledList = db.SelectWhereData("population_disabled", "population_idcard", PersonList.get(i).get("population_idcard"));
                        if (!DisabledList.isEmpty()) {
                            if (DisabledList.get(0).get("disabled_type").equals("2")) {
                                rbDisabledYes.setChecked(true);
                                ModelCheckboxCheck.checkboxSetCheck(cbDisabled1, DisabledList.get(0).get("disabled1"));
                                ModelCheckboxCheck.checkboxSetCheck(cbDisabled2, DisabledList.get(0).get("disabled2"));
                                ModelCheckboxCheck.checkboxSetCheck(cbDisabled3, DisabledList.get(0).get("disabled3"));
                                ModelCheckboxCheck.checkboxSetCheck(cbDisabled4, DisabledList.get(0).get("disabled4"));
                                ModelCheckboxCheck.checkboxSetCheck(cbDisabled5, DisabledList.get(0).get("disabled5"));
                                ModelCheckboxCheck.checkboxSetCheck(cbDisabled6, DisabledList.get(0).get("disabled6"));
                            } else if (DisabledList.get(0).get("disabled_type").equals("1")) {
                                rbDisabledNo.setChecked(true);
                            }
                        }

                        TransList = db.SelectWhereData("population_transport", "population_idcard", PersonList.get(i).get("population_idcard"));
                        if (!TransList.isEmpty()) {
                            if (TransList.get(0).get("transport_type").equals("2")) {
                                rbTransportationYes.setChecked(true);
                                ModelCheckboxCheck.checkboxSetCheck(cbTrans1, TransList.get(0).get("trans1"));
                                ModelCheckboxCheck.checkboxSetCheck(cbTrans2, TransList.get(0).get("trans2"));
                                ModelCheckboxCheck.checkboxSetCheck(cbTrans3, TransList.get(0).get("trans3"));
                                ModelCheckboxCheck.checkboxSetCheck(cbTrans4, TransList.get(0).get("trans4"));
                            } else if (TransList.get(0).get("transport_type").equals("1")) {
                                rbTransportationNo.setChecked(true);
                            }
                        }
                    }
                }

            }
            etFirstName.setEnabled(false);
            etLastName.setEnabled(false);
            etPersonalID.setEnabled(false);
            rbMale.setEnabled(false);
            rbFemale.setEnabled(false);
            rbInRegister.setChecked(true);
            rbInRegister.setEnabled(false);
            rbNotInRegister.setEnabled(false);
        } else {
            loProperty.setVisibility(View.GONE);
            rbNotInRegister.setChecked(true);
            rbInRegister.setEnabled(false);
            rbNotInRegister.setEnabled(false);
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
                        } else if (PLandList.get(i).get("land_benefit").equals("4")) {
                            benefit = "พื้นที่ว่างเปล่า";
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
                        temp.put(strPropType, "สัตว์เลี้ยงในครัวเรือน");
                        if (!PPetList.get(0).get("pet_type").equals("")) {
                            if (PPetList.get(0).get("pet_type").equals("1")) {
                                temp.put(strBenefit, "หมา");
                            } else if (PPetList.get(0).get("pet_type").equals("2")) {
                                temp.put(strBenefit, "แมว");
                            }
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
                        temp.put(strPropType, "สัตว์เลี้ยงเพื่อการเกษตร");
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

            ModelSetting.listviewHeight(lvProperty);
        }
    }

    private void updateData() {
        DateFormat originalFormat;
        DateFormat targetFormat;
        Date odate = null;
        String formattedDate = "0001-01-01";
        String date = df.format(Calendar.getInstance().getTime());
        Boolean NatPass = true;
        NationalityList = db.SelectData("nationality");

        if (!NationalityList.isEmpty()) {
            if (spNationality.getSelectedItem().toString().equals("อื่นๆ")) {
                NatPass = false;
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
            originalFormat = new SimpleDateFormat("dd/MM/yyy");
            targetFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                odate = originalFormat.parse(etBirtDate.getText().toString());
                if (odate != null) {
                    formattedDate = targetFormat.format(odate);
                } else {
                    formattedDate = "0001-01-01";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Val.put("birthdate", formattedDate);

            if (!etHeight.getText().toString().equals("")) {
                Val.put("height", etHeight.getText().toString());
            } else {
                Val.put("height", "1");
            }

            if (!etWeight.getText().toString().equals("")) {
                Val.put("weight", etWeight.getText().toString());
            } else {
                Val.put("weight", "1");
            }

            if (rbMale.isChecked()) {
                Val.put("sex", "M");
            } else if (rbFemale.isChecked()) {
                Val.put("sex", "F");
            } else {
                Val.put("sex", "M");
            }

            Val.put("bloodgroup", spBloodType.getSelectedItem().toString());

            if (rbAlive.isChecked()) {
                Val.put("living", "0");
            } else if (rbDead.isChecked()) {
                Val.put("living", "1");
            } else {
                Val.put("living", "0");
            }

            Val.put("maritalstatus", spMaritalStatus.getSelectedItem().toString());

            if (!etTel.getText().toString().equals("")) {
                Val.put("tel", etTel.getText().toString());
            } else {
                Val.put("tel", "1");
            }
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
                Val.put("currentaddr_province", spIHProvince.getSelectedItem().toString());
                Val.put("currentaddr_country", spIHProvince.getSelectedItem().toString());
            } else {
                Val.put("currentaddr", "0");
                Val.put("currentaddr_province", "");
                Val.put("currentaddr_country", "");
            }

            if (PersonID.equals("Nope")) {
                Val.put("dwellerstatus", "1");
            } else {
                PersonList = db.SelectWhereData("population", "population_idcard", etPersonalID.getText().toString());
                Val.put("dwellerstatus", PersonList.get(0).get("dwellerstatus"));
            }

            if (PersonID.equals("Nope")) {
                Val.put("residence_status", "0");
                Val.put("latentpop_province", spIRProvince.getSelectedItem().toString());
                Val.put("latentpop_country", spIRCountry.getSelectedItem().toString());
            } else {
                if (rbInRegister.isChecked()) {
                    Val.put("residence_status", "1");
                    Val.put("latentpop_province", "");
                    Val.put("latentpop_country", "");
                } else if (rbInRegister.isChecked()) {
                    Val.put("residence_status", "0");
                    Val.put("latentpop_province", spIRProvince.getSelectedItem().toString());
                    Val.put("latentpop_country", spIRCountry.getSelectedItem().toString());
                }
            }

            if (rbInHousehold.isChecked()) {

                Val.put("income", "0");
                Val.put("income_money", "0");
                Val.put("dept", "0");
                Val.put("saving", "0");
                Val.put("allergichis", "0");
                Val.put("allergichis_detail", "");
                Val.put("disadvantage", "0");
                Val.put("sub_al", "0");
                Val.put("education", "0");
                Val.put("education_class", "");
                Val.put("literacy", "0");
                Val.put("technology", "0");
                Val.put("expertise", "0");
                Val.put("expertise_name", "");
                Val.put("expertise_detail", "");
                Val.put("religion", "0");
                Val.put("religion_another", "");
                Val.put("participation", "0");
                Val.put("election", "0");

            } else if (rbNotInHousehold.isChecked()) {
                if (rbICMonth.isChecked()) {
                    Val.put("income", "1");
                    if (!etICMonth.getText().toString().equals("")) {
                        Val.put("income_money", etICMonth.getText().toString());
                    } else {
                        Val.put("income_money", "0");
                    }
                } else if (rbICYear.isChecked()) {
                    Val.put("income", "2");
                    if (!etICYear.getText().toString().equals("")) {
                        Val.put("income_money", etICYear.getText().toString());
                    } else {
                        Val.put("income_money", "0");
                    }
                } else {
                    Val.put("income", "0");
                    Val.put("income_money", "0");
                }

                if (rbDebtNo.isChecked()) {
                    Val.put("dept", "1");
                } else if (rbDebtYes.isChecked()) {
                    Val.put("dept", "2");
                } else {
                    Val.put("dept", "0");
                }

                if (rbSavingNo.isChecked()) {
                    Val.put("saving", "1");
                } else if (rbSavingYes.isChecked()) {
                    Val.put("saving", "2");
                } else {
                    Val.put("saving", "0");
                }

                if (rbAllergicNo.isChecked()) {
                    Val.put("allergichis", "1");
                    Val.put("allergichis_detail", "");
                } else if (rbAllergicYes.isChecked()) {
                    Val.put("allergichis", "2");
                    Val.put("allergichis_detail", etAllergic.getText().toString());
                } else {
                    Val.put("allergichis", "0");
                    Val.put("allergichis_detail", "");
                }

                if (rbDisadvantageNo.isChecked()) {
                    Val.put("disadvantage", "1");
                } else if (rbDisadvantageYes.isChecked()) {
                    Val.put("disadvantage", "2");
                } else {
                    Val.put("disadvantage", "0");
                }

                if (rbSubAlNo.isChecked()) {
                    Val.put("sub_al", "1");
                } else if (rbSubAlYes.isChecked()) {
                    Val.put("sub_al", "2");
                } else {
                    Val.put("sub_al", "0");
                }

                if (rbNoStudy.isChecked()) {
                    Val.put("education", "1");
                    Val.put("education_class", "");
                } else if (rbInStudy.isChecked()) {
                    Val.put("education", "2");
                    Val.put("education_class", spInStudy.getSelectedItem().toString());
                } else if (rbGraduated.isChecked()) {
                    Val.put("education", "3");
                    Val.put("education_class", spGraduated.getSelectedItem().toString());
                } else {
                    Val.put("education", "0");
                    Val.put("education_class", "");
                }

                if (rbLiteracyNo.isChecked()) {
                    Val.put("literacy", "1");
                } else if (rbLiteracyYes.isChecked()) {
                    Val.put("literacy", "2");
                } else {
                    Val.put("literacy", "0");
                }

                if (rbTechnologyNo.isChecked()) {
                    Val.put("technology", "1");
                } else if (rbTechnologyYes.isChecked()) {
                    Val.put("technology", "2");
                } else {
                    Val.put("technology", "0");
                }

                if (rbExpertiseNo.isChecked()) {
                    Val.put("expertise", "1");
                    Val.put("expertise_name", "");
                    Val.put("expertise_detail", "");
                } else if (rbExpertiseYes.isChecked()) {
                    Val.put("expertise", "2");
                    Val.put("expertise_name", spExpertise.getSelectedItem().toString());
                    Val.put("expertise_detail", etExpertise.getText().toString());
                } else {
                    Val.put("expertise", "0");
                    Val.put("expertise_name", "");
                    Val.put("expertise_detail", "");
                }

                if (rbBuddhismReligion.isChecked()) {
                    Val.put("religion", "1");
                    Val.put("religion_another", "");
                } else if (rbChristReligion.isChecked()) {
                    Val.put("religion", "2");
                    Val.put("religion_another", "");
                } else if (rbIslamicReligion.isChecked()) {
                    Val.put("religion", "3");
                    Val.put("religion_another", "");
                } else if (rbAnotherReligion.isChecked()) {
                    Val.put("religion", "4");
                    Val.put("religion_another", etAnotherReligion.getText().toString());
                } else {
                    Val.put("religion", "0");
                    Val.put("religion_another", "");
                }

                if (rbParticipationNo.isChecked()) {
                    Val.put("participation", "1");
                } else if (rbParticipationYes.isChecked()) {
                    Val.put("participation", "2");
                } else {
                    Val.put("participation", "0");
                }

                if (rbElectionAlway.isChecked()) {
                    Val.put("election", "1");
                } else if (rbElectionSometime.isChecked()) {
                    Val.put("election", "2");
                } else if (rbElectionNever.isChecked()) {
                    Val.put("election", "3");
                } else {
                    Val.put("election", "0");
                }

            }//In house radio

            Val.put("distributor", spContributor.getSelectedItem().toString());
            Val.put("survey_status", "1");
            Val.put("upload_status", "1");
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

            if (rbInHousehold.isChecked()) {

                Val.put("congh_type", "0");
                Val.put("congh1", "0");
                Val.put("congh2", "0");
                Val.put("congh3", "0");
                Val.put("congh4", "0");
                Val.put("congh5", "0");
                Val.put("congh6", "0");
                Val.put("congh7", "0");
                Val.put("congh8", "0");
                Val.put("congh9", "0");
                Val.put("congh10", "0");
                Val.put("congh11", "0");
                Val.put("congh12", "0");
                Val.put("congh13", "0");
                Val.put("congh14", "0");
                Val.put("congh15", "0");
                Val.put("congh16", "0");
                Val.put("congh17", "0");
                Val.put("congh18", "0");
                Val.put("congh19", "0");
                Val.put("congh20", "0");
                Val.put("congh21", "0");
                Val.put("congh22", "0");
                Val.put("congh23", "0");
                Val.put("congh24", "0");
                Val.put("congh25", "0");
                Val.put("congh26", "0");
                Val.put("congh_another", "");

            } else if (rbNotInHousehold.isChecked()) {
                if (rbCongenitalNo.isChecked()) {
                    Val.put("congh_type", "1");
                    Val.put("congh1", "0");
                    Val.put("congh2", "0");
                    Val.put("congh3", "0");
                    Val.put("congh4", "0");
                    Val.put("congh5", "0");
                    Val.put("congh6", "0");
                    Val.put("congh7", "0");
                    Val.put("congh8", "0");
                    Val.put("congh9", "0");
                    Val.put("congh10", "0");
                    Val.put("congh11", "0");
                    Val.put("congh12", "0");
                    Val.put("congh13", "0");
                    Val.put("congh14", "0");
                    Val.put("congh15", "0");
                    Val.put("congh16", "0");
                    Val.put("congh17", "0");
                    Val.put("congh18", "0");
                    Val.put("congh19", "0");
                    Val.put("congh20", "0");
                    Val.put("congh21", "0");
                    Val.put("congh22", "0");
                    Val.put("congh23", "0");
                    Val.put("congh24", "0");
                    Val.put("congh25", "0");
                    Val.put("congh26", "0");
                    Val.put("congh_another", "");
                } else if (rbCongenitalYes.isChecked()) {
                    Val.put("congh_type", "2");
                    Val.put("congh1", ModelCheckboxCheck.checkboxReturnCheck(cbCong1));
                    Val.put("congh2", ModelCheckboxCheck.checkboxReturnCheck(cbCong2));
                    Val.put("congh3", ModelCheckboxCheck.checkboxReturnCheck(cbCong3));
                    Val.put("congh4", ModelCheckboxCheck.checkboxReturnCheck(cbCong4));
                    Val.put("congh5", ModelCheckboxCheck.checkboxReturnCheck(cbCong5));
                    Val.put("congh6", ModelCheckboxCheck.checkboxReturnCheck(cbCong6));
                    Val.put("congh7", ModelCheckboxCheck.checkboxReturnCheck(cbCong7));
                    Val.put("congh8", ModelCheckboxCheck.checkboxReturnCheck(cbCong8));
                    Val.put("congh9", ModelCheckboxCheck.checkboxReturnCheck(cbCong9));
                    Val.put("congh10", ModelCheckboxCheck.checkboxReturnCheck(cbCong10));
                    Val.put("congh11", ModelCheckboxCheck.checkboxReturnCheck(cbCong11));
                    Val.put("congh12", ModelCheckboxCheck.checkboxReturnCheck(cbCong12));
                    Val.put("congh13", ModelCheckboxCheck.checkboxReturnCheck(cbCong13));
                    Val.put("congh14", ModelCheckboxCheck.checkboxReturnCheck(cbCong14));
                    Val.put("congh15", ModelCheckboxCheck.checkboxReturnCheck(cbCong15));
                    Val.put("congh16", ModelCheckboxCheck.checkboxReturnCheck(cbCong16));
                    Val.put("congh17", ModelCheckboxCheck.checkboxReturnCheck(cbCong17));
                    Val.put("congh18", ModelCheckboxCheck.checkboxReturnCheck(cbCong18));
                    Val.put("congh19", ModelCheckboxCheck.checkboxReturnCheck(cbCong19));
                    Val.put("congh20", ModelCheckboxCheck.checkboxReturnCheck(cbCong20));
                    Val.put("congh21", ModelCheckboxCheck.checkboxReturnCheck(cbCong21));
                    Val.put("congh22", ModelCheckboxCheck.checkboxReturnCheck(cbCong22));
                    Val.put("congh23", ModelCheckboxCheck.checkboxReturnCheck(cbCong23));
                    Val.put("congh24", ModelCheckboxCheck.checkboxReturnCheck(cbCong24));
                    Val.put("congh25", ModelCheckboxCheck.checkboxReturnCheck(cbCong25));
                    Val.put("congh26", ModelCheckboxCheck.checkboxReturnCheck(cbCong26));
                    if (cbCong26.isChecked()) {
                        Val.put("congh_another", etAnotherCong.getText().toString());
                    } else if (!cbCong26.isChecked()) {
                        Val.put("congh_another", "");
                    }
                } else {
                    Val.put("congh_type", "0");
                    Val.put("congh1", "0");
                    Val.put("congh2", "0");
                    Val.put("congh3", "0");
                    Val.put("congh4", "0");
                    Val.put("congh5", "0");
                    Val.put("congh6", "0");
                    Val.put("congh7", "0");
                    Val.put("congh8", "0");
                    Val.put("congh9", "0");
                    Val.put("congh10", "0");
                    Val.put("congh11", "0");
                    Val.put("congh12", "0");
                    Val.put("congh13", "0");
                    Val.put("congh14", "0");
                    Val.put("congh15", "0");
                    Val.put("congh16", "0");
                    Val.put("congh17", "0");
                    Val.put("congh18", "0");
                    Val.put("congh19", "0");
                    Val.put("congh20", "0");
                    Val.put("congh21", "0");
                    Val.put("congh22", "0");
                    Val.put("congh23", "0");
                    Val.put("congh24", "0");
                    Val.put("congh25", "0");
                    Val.put("congh26", "0");
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

            if (rbInHousehold.isChecked()) {

                Val.put("conth_type", "0");
                Val.put("conth1", "0");
                Val.put("conth2", "0");
                Val.put("conth3", "0");
                Val.put("conth4", "0");
                Val.put("conth5", "0");
                Val.put("conth6", "0");
                Val.put("conth7", "0");
                Val.put("conth8", "0");
                Val.put("conth9", "0");
                Val.put("conth10", "0");
                Val.put("conth11", "0");
                Val.put("conth12", "0");
                Val.put("conth13", "0");
                Val.put("conth14", "0");
                Val.put("conth15", "0");
                Val.put("conth16", "0");
                Val.put("conth17", "0");
                Val.put("conth18", "0");
                Val.put("conth19", "0");
                Val.put("conth20", "0");
                Val.put("conth21", "0");
                Val.put("conth22", "0");
                Val.put("conth23", "0");
                Val.put("conth24", "0");
                Val.put("conth25", "0");
                Val.put("conth26", "0");
                Val.put("conth27", "0");
                Val.put("conth28", "0");
                Val.put("conth29", "0");
                Val.put("conth30", "0");
                Val.put("conth31", "0");
                Val.put("conth32", "0");
                Val.put("conth33", "0");
                Val.put("conth34", "0");
                Val.put("conth35", "0");
                Val.put("conth36", "0");
                Val.put("conth37", "0");
                Val.put("conth38", "0");
                Val.put("conth39", "0");
                Val.put("conth40", "0");
                Val.put("conth41", "0");
                Val.put("conth42", "0");
                Val.put("conth43", "0");
                Val.put("conth44", "0");
                Val.put("conth45", "0");
                Val.put("conth46", "0");
                Val.put("conth47", "0");
                Val.put("conth48", "0");
                Val.put("conth49", "0");
                Val.put("conth50", "0");
                Val.put("conth51", "0");
                Val.put("conth_another", "");

            } else if (rbNotInHousehold.isChecked()) {
                if (rbContagiousNo.isChecked()) {
                    Val.put("conth_type", "1");
                    Val.put("conth1", "0");
                    Val.put("conth2", "0");
                    Val.put("conth3", "0");
                    Val.put("conth4", "0");
                    Val.put("conth5", "0");
                    Val.put("conth6", "0");
                    Val.put("conth7", "0");
                    Val.put("conth8", "0");
                    Val.put("conth9", "0");
                    Val.put("conth10", "0");
                    Val.put("conth11", "0");
                    Val.put("conth12", "0");
                    Val.put("conth13", "0");
                    Val.put("conth14", "0");
                    Val.put("conth15", "0");
                    Val.put("conth16", "0");
                    Val.put("conth17", "0");
                    Val.put("conth18", "0");
                    Val.put("conth19", "0");
                    Val.put("conth20", "0");
                    Val.put("conth21", "0");
                    Val.put("conth22", "0");
                    Val.put("conth23", "0");
                    Val.put("conth24", "0");
                    Val.put("conth25", "0");
                    Val.put("conth26", "0");
                    Val.put("conth27", "0");
                    Val.put("conth28", "0");
                    Val.put("conth29", "0");
                    Val.put("conth30", "0");
                    Val.put("conth31", "0");
                    Val.put("conth32", "0");
                    Val.put("conth33", "0");
                    Val.put("conth34", "0");
                    Val.put("conth35", "0");
                    Val.put("conth36", "0");
                    Val.put("conth37", "0");
                    Val.put("conth38", "0");
                    Val.put("conth39", "0");
                    Val.put("conth40", "0");
                    Val.put("conth41", "0");
                    Val.put("conth42", "0");
                    Val.put("conth43", "0");
                    Val.put("conth44", "0");
                    Val.put("conth45", "0");
                    Val.put("conth46", "0");
                    Val.put("conth47", "0");
                    Val.put("conth48", "0");
                    Val.put("conth49", "0");
                    Val.put("conth50", "0");
                    Val.put("conth51", "0");
                    Val.put("conth_another", "");
                } else if (rbContagiousYes.isChecked()) {
                    Val.put("conth_type", "2");
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
                    Val.put("conth12", ModelCheckboxCheck.checkboxReturnCheck(cbCont12));
                    Val.put("conth13", ModelCheckboxCheck.checkboxReturnCheck(cbCont13));
                    Val.put("conth14", ModelCheckboxCheck.checkboxReturnCheck(cbCont14));
                    Val.put("conth15", ModelCheckboxCheck.checkboxReturnCheck(cbCont15));
                    Val.put("conth16", ModelCheckboxCheck.checkboxReturnCheck(cbCont16));
                    Val.put("conth17", ModelCheckboxCheck.checkboxReturnCheck(cbCont17));
                    Val.put("conth18", ModelCheckboxCheck.checkboxReturnCheck(cbCont18));
                    Val.put("conth19", ModelCheckboxCheck.checkboxReturnCheck(cbCont19));
                    Val.put("conth20", ModelCheckboxCheck.checkboxReturnCheck(cbCont20));
                    Val.put("conth21", ModelCheckboxCheck.checkboxReturnCheck(cbCont21));
                    Val.put("conth22", ModelCheckboxCheck.checkboxReturnCheck(cbCont22));
                    Val.put("conth23", ModelCheckboxCheck.checkboxReturnCheck(cbCont23));
                    Val.put("conth24", ModelCheckboxCheck.checkboxReturnCheck(cbCont24));
                    Val.put("conth25", ModelCheckboxCheck.checkboxReturnCheck(cbCont25));
                    Val.put("conth26", ModelCheckboxCheck.checkboxReturnCheck(cbCont26));
                    Val.put("conth27", ModelCheckboxCheck.checkboxReturnCheck(cbCont27));
                    Val.put("conth28", ModelCheckboxCheck.checkboxReturnCheck(cbCont28));
                    Val.put("conth29", ModelCheckboxCheck.checkboxReturnCheck(cbCont29));
                    Val.put("conth30", ModelCheckboxCheck.checkboxReturnCheck(cbCont30));
                    Val.put("conth31", ModelCheckboxCheck.checkboxReturnCheck(cbCont31));
                    Val.put("conth32", ModelCheckboxCheck.checkboxReturnCheck(cbCont32));
                    Val.put("conth33", ModelCheckboxCheck.checkboxReturnCheck(cbCont33));
                    Val.put("conth34", ModelCheckboxCheck.checkboxReturnCheck(cbCont34));
                    Val.put("conth35", ModelCheckboxCheck.checkboxReturnCheck(cbCont35));
                    Val.put("conth36", ModelCheckboxCheck.checkboxReturnCheck(cbCont36));
                    Val.put("conth37", ModelCheckboxCheck.checkboxReturnCheck(cbCont37));
                    Val.put("conth38", ModelCheckboxCheck.checkboxReturnCheck(cbCont38));
                    Val.put("conth39", ModelCheckboxCheck.checkboxReturnCheck(cbCont39));
                    Val.put("conth40", ModelCheckboxCheck.checkboxReturnCheck(cbCont40));
                    Val.put("conth41", ModelCheckboxCheck.checkboxReturnCheck(cbCont41));
                    Val.put("conth42", ModelCheckboxCheck.checkboxReturnCheck(cbCont42));
                    Val.put("conth43", ModelCheckboxCheck.checkboxReturnCheck(cbCont43));
                    Val.put("conth44", ModelCheckboxCheck.checkboxReturnCheck(cbCont44));
                    Val.put("conth45", ModelCheckboxCheck.checkboxReturnCheck(cbCont45));
                    Val.put("conth46", ModelCheckboxCheck.checkboxReturnCheck(cbCont46));
                    Val.put("conth47", ModelCheckboxCheck.checkboxReturnCheck(cbCont47));
                    Val.put("conth48", ModelCheckboxCheck.checkboxReturnCheck(cbCont48));
                    Val.put("conth49", ModelCheckboxCheck.checkboxReturnCheck(cbCont49));
                    Val.put("conth50", ModelCheckboxCheck.checkboxReturnCheck(cbCont50));
                    Val.put("conth51", ModelCheckboxCheck.checkboxReturnCheck(cbCont51));
                    if (cbCont51.isChecked()) {
                        Val.put("conth_another", etAnotherCont.getText().toString());
                    } else if (!cbCont51.isChecked()) {
                        Val.put("conth_another", "");
                    }
                } else {
                    Val.put("conth_type", "0");
                    Val.put("conth1", "0");
                    Val.put("conth2", "0");
                    Val.put("conth3", "0");
                    Val.put("conth4", "0");
                    Val.put("conth5", "0");
                    Val.put("conth6", "0");
                    Val.put("conth7", "0");
                    Val.put("conth8", "0");
                    Val.put("conth9", "0");
                    Val.put("conth10", "0");
                    Val.put("conth11", "0");
                    Val.put("conth12", "0");
                    Val.put("conth13", "0");
                    Val.put("conth14", "0");
                    Val.put("conth15", "0");
                    Val.put("conth16", "0");
                    Val.put("conth17", "0");
                    Val.put("conth18", "0");
                    Val.put("conth19", "0");
                    Val.put("conth20", "0");
                    Val.put("conth21", "0");
                    Val.put("conth22", "0");
                    Val.put("conth23", "0");
                    Val.put("conth24", "0");
                    Val.put("conth25", "0");
                    Val.put("conth26", "0");
                    Val.put("conth27", "0");
                    Val.put("conth28", "0");
                    Val.put("conth29", "0");
                    Val.put("conth30", "0");
                    Val.put("conth31", "0");
                    Val.put("conth32", "0");
                    Val.put("conth33", "0");
                    Val.put("conth34", "0");
                    Val.put("conth35", "0");
                    Val.put("conth36", "0");
                    Val.put("conth37", "0");
                    Val.put("conth38", "0");
                    Val.put("conth39", "0");
                    Val.put("conth40", "0");
                    Val.put("conth41", "0");
                    Val.put("conth42", "0");
                    Val.put("conth43", "0");
                    Val.put("conth44", "0");
                    Val.put("conth45", "0");
                    Val.put("conth46", "0");
                    Val.put("conth47", "0");
                    Val.put("conth48", "0");
                    Val.put("conth49", "0");
                    Val.put("conth50", "0");
                    Val.put("conth51", "0");
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

            if (rbInHousehold.isChecked()) {

                Val.put("disabled_type", "0");
                Val.put("disabled1", "0");
                Val.put("disabled2", "0");
                Val.put("disabled3", "0");
                Val.put("disabled4", "0");
                Val.put("disabled5", "0");
                Val.put("disabled6", "0");

            } else if (rbNotInHousehold.isChecked()) {
                if (rbDisabledNo.isChecked()) {
                    Val.put("disabled_type", "1");
                    Val.put("disabled1", "0");
                    Val.put("disabled2", "0");
                    Val.put("disabled3", "0");
                    Val.put("disabled4", "0");
                    Val.put("disabled5", "0");
                    Val.put("disabled6", "0");
                } else if (rbDisabledYes.isChecked()) {
                    Val.put("disabled_type", "2");
                    Val.put("disabled1", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled1));
                    Val.put("disabled2", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled2));
                    Val.put("disabled3", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled3));
                    Val.put("disabled4", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled4));
                    Val.put("disabled5", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled5));
                    Val.put("disabled6", ModelCheckboxCheck.checkboxReturnCheck(cbDisabled6));
                } else {
                    Val.put("disabled_type", "0");
                    Val.put("disabled1", "0");
                    Val.put("disabled2", "0");
                    Val.put("disabled3", "0");
                    Val.put("disabled4", "0");
                    Val.put("disabled5", "0");
                    Val.put("disabled6", "0");
                }
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

            if (rbInHousehold.isChecked()) {

                Val.put("transport_type", "0");
                Val.put("trans1", "0");
                Val.put("trans2", "0");
                Val.put("trans3", "0");
                Val.put("trans4", "0");

            } else if (rbNotInHousehold.isChecked()) {
                if (rbTransportationNo.isChecked()) {
                    Val.put("transport_type", "1");
                    Val.put("trans1", "0");
                    Val.put("trans2", "0");
                    Val.put("trans3", "0");
                    Val.put("trans4", "0");
                } else if (rbTransportationYes.isChecked()) {
                    Val.put("transport_type", "2");
                    Val.put("trans1", ModelCheckboxCheck.checkboxReturnCheck(cbTrans1));
                    Val.put("trans2", ModelCheckboxCheck.checkboxReturnCheck(cbTrans2));
                    Val.put("trans3", ModelCheckboxCheck.checkboxReturnCheck(cbTrans3));
                    Val.put("trans4", ModelCheckboxCheck.checkboxReturnCheck(cbTrans4));
                } else {
                    Val.put("transport_type", "0");
                    Val.put("trans1", "0");
                    Val.put("trans2", "0");
                    Val.put("trans3", "0");
                    Val.put("trans4", "0");
                }
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

            if (rbInHousehold.isChecked()) {

                Val.put("works_type", "0");

            } else if (rbNotInHousehold.isChecked()) {
                if (rbJGStudent.isChecked()) {
                    Val.put("works_type", "1");
                } else if (rbJGCareer.isChecked()) {
                    Val.put("works_type", "2");
                } else if (rbJGNoJob.isChecked()) {
                    Val.put("works_type", "3");
                } else {
                    Val.put("works_type", "0");
                }
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
            if (rbInHousehold.isChecked()) {

                Val.put("agri1", "0");
                Val.put("agri2", "0");
                Val.put("agri3", "0");
                Val.put("agri4", "0");
                Val.put("agri5", "0");
                Val.put("agri6", "0");
                Val.put("agri7", "0");
                Val.put("agri8", "0");
                Val.put("agri_another", "");

            } else if (rbNotInHousehold.isChecked()) {
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
                    Val.put("agri1", "0");
                    Val.put("agri2", "0");
                    Val.put("agri3", "0");
                    Val.put("agri4", "0");
                    Val.put("agri5", "0");
                    Val.put("agri6", "0");
                    Val.put("agri7", "0");
                    Val.put("agri8", "0");
                    Val.put("agri_another", "");
                }
            }

            Val.put("works_id", TestList.get(0).get("works_id"));
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
            if (rbInHousehold.isChecked()) {

                Val.put("animal1", "0");
                Val.put("animal2", "0");
                Val.put("animal3", "0");
                Val.put("animal4", "0");
                Val.put("animal5", "0");
                Val.put("animal6", "0");
                Val.put("animal7", "0");
                Val.put("animal8", "0");
                Val.put("animal9", "0");
                Val.put("animal_another", "");

            } else if (rbNotInHousehold.isChecked()) {
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
                    Val.put("animal1", "0");
                    Val.put("animal2", "0");
                    Val.put("animal3", "0");
                    Val.put("animal4", "0");
                    Val.put("animal5", "0");
                    Val.put("animal6", "0");
                    Val.put("animal7", "0");
                    Val.put("animal8", "0");
                    Val.put("animal9", "0");
                    Val.put("animal_another", "");
                }
            }

            Val.put("works_id", TestList.get(0).get("works_id"));
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
            if (rbInHousehold.isChecked()) {

                Val.put("govern1", "0");
                Val.put("govern2", "0");
                Val.put("govern3", "0");
                Val.put("govern4", "0");
                Val.put("govern_another", "");

            } else if (rbNotInHousehold.isChecked()) {
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
                    Val.put("govern1", "0");
                    Val.put("govern2", "0");
                    Val.put("govern3", "0");
                    Val.put("govern4", "0");
                    Val.put("govern_another", "");
                }
            }

            Val.put("works_id", TestList.get(0).get("works_id"));
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
            if (rbInHousehold.isChecked()) {

                Val.put("private1", "0");
                Val.put("private2", "0");
                Val.put("private3", "0");
                Val.put("private4", "0");
                Val.put("private5", "0");
                Val.put("private6", "0");
                Val.put("private7", "0");
                Val.put("private_another", "");

            } else if (rbNotInHousehold.isChecked()) {
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
                    Val.put("private1", "0");
                    Val.put("private2", "0");
                    Val.put("private3", "0");
                    Val.put("private4", "0");
                    Val.put("private5", "0");
                    Val.put("private6", "0");
                    Val.put("private7", "0");
                    Val.put("private_another", "");
                }
            }

            Val.put("works_id", TestList.get(0).get("works_id"));
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
            Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
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
        if (compoundButton == cbCong26)
            ModelShowHideLayout.checkboxShowHide(cbCong26, loAnotherCong);
        if (compoundButton == cbCont51)
            ModelShowHideLayout.checkboxShowHide(cbCont51, loAnotherCont);
        if (compoundButton == cbAgri8)
            ModelShowHideLayout.checkboxShowHide(cbAgri8, loAnotherAgri);
        if (compoundButton == cbPet9)
            ModelShowHideLayout.checkboxShowHide(cbPet9, loAnotherPet);
        if (compoundButton == cbGovern5)
            ModelShowHideLayout.checkboxShowHide(cbGovern5, loAnotherGovern);
        if (compoundButton == cbPrivate7)
            ModelShowHideLayout.checkboxShowHide(cbPrivate7, loAnotherPrivate);


        /*if (compoundButton == rbInRegister)
            ModelShowHideLayout.radiobuttonShowHide(rbInRegister, loInRegister);*/
        if (compoundButton == rbNotInRegister)
            ModelShowHideLayout.radiobuttonShowHide(rbNotInRegister, loNotInRegister);
        if (compoundButton == rbInHousehold)
            ModelShowHideLayout.radiobuttonShowHide(rbInHousehold, loLiveHere);
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
        if (compoundButton == rbExpertiseYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbExpertiseYes, loExpertise);
            ModelShowHideLayout.radiobuttonShowHide(rbExpertiseYes, loExpertiseText);
        }
        if (compoundButton == rbAnotherReligion)
            ModelShowHideLayout.radiobuttonShowHide(rbAnotherReligion, loAnotherReligion);
        if (compoundButton == rbTransportationYes)
            ModelShowHideLayout.radiobuttonShowHide(rbTransportationYes, loTransportation);
        if (compoundButton == rbIHCountry)
            ModelShowHideLayout.radiobuttonEnable(rbIHCountry, spIHCountry, spIHProvince);
        if (compoundButton == rbIRCountry)
            ModelShowHideLayout.radiobuttonEnable(rbIRCountry, spIRCountry, spIRProvince);
        if (compoundButton == rbIHProvince)
            ModelShowHideLayout.radiobuttonEnable(rbIHProvince, spIHProvince, spIHCountry);
        if (compoundButton == rbIRProvince)
            ModelShowHideLayout.radiobuttonEnable(rbIRProvince, spIRProvince, spIRCountry);
    }

    @Override
    public void onClick(View view) {
        //,btnIRPSearch,btnIHCSearch,btnIHPSearch;
        if (view == btnIRCSearch) {
            //CustomDialog.ListViewDialog(PeopleFormActivity.this, spIRCountry, spCountryArray, countryArrayAdapter);
        }

        if (view == btnDatePick) {
            //fromDatePickerDialog.show();
            CustomDialog.DatePickerDialog(PeopleFormActivity.this, etBirtDate);
        }
        if (view == btnSavingData) {
            if (fieldCheck() == 0) {
                updateData();
                Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                if (!PersonID.equals("Nope")) {
                    loProperty.setVisibility(View.VISIBLE);
                    svPopulation.post(new Runnable() {
                        public void run() {
                            svPopulation.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                } else {
                    this.finish();
                }
            } else if (fieldCheck() == 1) {
                Toast.makeText(this, "กรุณาระบุ \"สัญชาติ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 2) {
                Toast.makeText(this, "กรุณาระบุ \"ชื่อต้น\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 3) {
                Toast.makeText(this, "กรุณาระบุ \"นามสกุล\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 4) {
                Toast.makeText(this, "กรุณาระบุ \"คำนำหน้า\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 5) {
                Toast.makeText(this, "กรุณาระบุ \"เพศ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 6) {
                Toast.makeText(this, "กรุณาระบุ \"เลขประจำตัวประชาชน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 7) {
                Toast.makeText(this, "กรุณาระบุ \"วัน/เดือน/ปีเกิด\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 8) {
                Toast.makeText(this, "กรุณาระบุ \"กรุ๊ปเลือด\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 9) {
                Toast.makeText(this, "กรุณาระบุ \"การมีชีวิตอยู่\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 10) {
                Toast.makeText(this, "กรุณาระบุ \"สถานภาพการสมรส\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 11) {
                Toast.makeText(this, "กรุณาระบุ \"ที่อยู่ตามทะเบียนบ้าน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 12) {
                Toast.makeText(this, "กรุณาระบุ \"ที่อยู่ปัจจุบัน\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 13) {
                Toast.makeText(this, "กรุณาระบุ \"สถานภาพของการอาศัย\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 14) {
                Toast.makeText(this, "กรุณาระบุ \"ระดับการศึกษา\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 15) {
                Toast.makeText(this, "กรุณาระบุ \"สาขาความเชี่ยวชาญ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 16) {
                Toast.makeText(this, "กรุณาระบุ \"จังหวัด\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 17) {
                Toast.makeText(this, "กรุณาระบุ \"ประเทศ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 18) {
                Toast.makeText(this, "กรุณาระบุ \"จังหวัด\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 19) {
                Toast.makeText(this, "กรุณาระบุ \"ประเทศ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 20) {
                Toast.makeText(this, "กรุณาระบุ \"ประเภทอาชีพ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 21) {
                Toast.makeText(this, "กรุณาระบุ \"อาชีพ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 22) {
                Toast.makeText(this, "กรุณาระบุ \"อาชีพ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 23) {
                Toast.makeText(this, "กรุณาระบุ \"รายได้\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 24) {
                Toast.makeText(this, "กรุณาระบุ \"ประวัติโรคประจำตัว\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 25) {
                Toast.makeText(this, "กรุณาระบุ \"ประวัติโรคประจำตัว (อื่นๆ)\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 26) {
                Toast.makeText(this, "กรุณาระบุ \"ประวัติการเป็นโรค\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 27) {
                Toast.makeText(this, "กรุณาระบุ \"ประวัติการเป็นโรค (อื่นๆ)\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 28) {
                Toast.makeText(this, "กรุณาระบุ \"ประวัติการแพ้ยา\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 29) {
                Toast.makeText(this, "กรุณาระบุ \"ความพิการ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 30) {
                Toast.makeText(this, "กรุณาระบุ \"สัญชาติ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 31) {
                Toast.makeText(this, "กรุณาระบุ \"การใช้บริการขนส่งสาธารณะ\"", Toast.LENGTH_SHORT).show();
            } else if (fieldCheck() == 32) {
                Toast.makeText(this, "กรุณาระบุ \"ชื่อผู้ให้ข้อมูล\"", Toast.LENGTH_SHORT).show();
            }
        }
        if (view == btnAddProperty) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.my_alert_dialog, (ViewGroup) findViewById(R.id.root));

            Button btnLand = v.findViewById(R.id.btnLand);
            btnLand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LandFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("LandID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    PeopleFormActivity.this.startActivity(intent);
                }
            });
            Button btnVehicle = v.findViewById(R.id.btnVehicle);
            btnVehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), VehicalFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("VehicleID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    PeopleFormActivity.this.startActivity(intent);
                }
            });
            Button btnPet = v.findViewById(R.id.btnPet);
            btnPet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PetFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("PetID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    PeopleFormActivity.this.startActivity(intent);
                }
            });
            Button btnAnimal = v.findViewById(R.id.btnAnimal);
            btnAnimal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AnimalFormActivity.class);
                    intent.putExtra("PersonID", PersonID);
                    intent.putExtra("AnimalID", "Nope");
                    intent.putExtra("HouseID", HouseID);
                    PeopleFormActivity.this.startActivity(intent);
                }
            });
            alert.setView(v);
            alert.show();
        }
    }

    private int fieldCheck() {
        int formPass = 0;
        Boolean nationPass = true,//1
                fnamePass = true,//2
                lnamePass = true,//3
                prefixPass = true,//4
                sexPass = true,//5
                personidPass = true,//6
                birthPass = true,//7
                bloodPass = true,//8
                livingPass = true,//9
                maritalPass = true,//10
                inregisPass = true,//11
                inhousePass = true,//12
                dwellerPass = true,//13
                graduatePass = true,//14
                expertSPass = true,//15
                irPPass = true,//16
                irCPass = true,//17
                ihPPass = true,//18
                ihCPass = true,//19
                jobPass = true,//20
                job1Pass = true,//21
                job1APass = true,//22
                incomePass = true,//23
                conghPass = true,//24
                conghAPass = true,//25
                conthPass = true,//26
                conthAPass = true,//27
                allePass = true,//28
                disPass = true,//29
                regionPass = true,//30
                transPass = true,//31
                conPass = true;//32

        if (!rbMale.isChecked() && !rbFemale.isChecked()) {
            sexPass = false;
        }

        if (!rbAlive.isChecked() && !rbDead.isChecked()) {
            livingPass = false;
        }

        if (!rbInRegister.isChecked() && !rbNotInRegister.isChecked()) {
            inregisPass = false;
        }

        if (!rbInHousehold.isChecked() && !rbNotInHousehold.isChecked()) {
            inhousePass = false;
        }

        if (!rbStatusOwner.isChecked() && !rbStatusDweller.isChecked()) {
            dwellerPass = false;
        }

        if (rbInHousehold.isChecked()) {
            if (rbInStudy.isChecked()) {
                graduatePass = ModelCheckForm.checkSpinner(spInStudy);
            } else if (rbGraduated.isChecked()) {
                graduatePass = ModelCheckForm.checkSpinner(spGraduated);
            }

            if (rbExpertiseYes.isChecked()) {
                expertSPass = ModelCheckForm.checkSpinner(spExpertise);
            }
        }

        if (rbNotInRegister.isChecked()) {
            if (rbIRProvince.isChecked()) {
                irPPass = ModelCheckForm.checkSpinner(spIRProvince);
            }

            if (rbIRCountry.isChecked()) {
                irCPass = ModelCheckForm.checkSpinner(spIRCountry);
            }
        }

        if (rbNotInHousehold.isChecked()) {
            if (rbIHProvince.isChecked()) {
                ihPPass = ModelCheckForm.checkSpinner(spIHProvince);
            }

            if (rbIHCountry.isChecked()) {
                ihCPass = ModelCheckForm.checkSpinner(spIHCountry);
            }
        }

        if (rbJGCareer.isChecked()) {
            if (!cbAgri.isChecked() && !cbPet.isChecked() && !cbGovern.isChecked() && !cbPrivate.isChecked()) {
                jobPass = false;
            }

            if (cbAgri.isChecked()) {
                if (!cbAgri1.isChecked() &&
                        !cbAgri2.isChecked() &&
                        !cbAgri3.isChecked() &&
                        !cbAgri4.isChecked() &&
                        !cbAgri5.isChecked() &&
                        !cbAgri6.isChecked() &&
                        !cbAgri7.isChecked() &&
                        !cbAgri7.isChecked() &&
                        !cbAgri8.isChecked()) {
                    job1Pass = false;
                }

                if (cbAgri8.isChecked()) {
                    job1APass = ModelCheckForm.checkEditText(etAnotherAgri);
                }
            } else if (cbPet.isChecked()) {
                if (!cbPet1.isChecked() &&
                        !cbPet2.isChecked() &&
                        !cbPet3.isChecked() &&
                        !cbPet4.isChecked() &&
                        !cbPet5.isChecked() &&
                        !cbPet6.isChecked() &&
                        !cbPet7.isChecked() &&
                        !cbPet8.isChecked() &&
                        !cbPet9.isChecked()) {
                    job1Pass = false;
                }

                if (cbPet9.isChecked()) {
                    job1APass = ModelCheckForm.checkEditText(etAnotherPet);
                }
            } else if (cbGovern.isChecked()) {
                if (!cbGovern1.isChecked() &&
                        !cbGovern2.isChecked() &&
                        !cbGovern3.isChecked() &&
                        !cbGovern4.isChecked() &&
                        !cbGovern5.isChecked()) {
                    job1Pass = false;
                }

                if (cbGovern5.isChecked()) {
                    job1APass = ModelCheckForm.checkEditText(etAnotherGovern);
                }
            } else if (cbPrivate.isChecked()) {
                if (!cbPrivate1.isChecked() &&
                        !cbPrivate2.isChecked() &&
                        !cbPrivate3.isChecked() &&
                        !cbPrivate4.isChecked() &&
                        !cbPrivate5.isChecked() &&
                        !cbPrivate6.isChecked() &&
                        !cbPrivate7.isChecked()) {
                    job1Pass = false;
                }

                if (cbPrivate7.isChecked()) {
                    job1APass = ModelCheckForm.checkEditText(etAnotherPrivate);
                }
            }
        }

        if (rbICMonth.isChecked()) {
            incomePass = ModelCheckForm.checkEditText(etICMonth);
        } else if (rbICYear.isChecked()) {
            incomePass = ModelCheckForm.checkEditText(etICMonth);
        }

        if (rbCongenitalYes.isChecked()) {
            if (!cbCong1.isChecked() &&
                    !cbCong2.isChecked() &&
                    !cbCong3.isChecked() &&
                    !cbCong4.isChecked() &&
                    !cbCong5.isChecked() &&
                    !cbCong6.isChecked() &&
                    !cbCong7.isChecked() &&
                    !cbCong8.isChecked() &&
                    !cbCong9.isChecked() &&
                    !cbCong10.isChecked() &&
                    !cbCong11.isChecked() &&
                    !cbCong12.isChecked() &&
                    !cbCong13.isChecked() &&
                    !cbCong14.isChecked() &&
                    !cbCong15.isChecked() &&
                    !cbCong16.isChecked() &&
                    !cbCong17.isChecked() &&
                    !cbCong18.isChecked() &&
                    !cbCong19.isChecked() &&
                    !cbCong20.isChecked() &&
                    !cbCong21.isChecked() &&
                    !cbCong22.isChecked() &&
                    !cbCong23.isChecked() &&
                    !cbCong24.isChecked() &&
                    !cbCong25.isChecked() &&
                    !cbCong26.isChecked()) {
                conghPass = false;
            }

            if (cbCong26.isChecked()) {
                conghAPass = ModelCheckForm.checkEditText(etAnotherCong);
            }
        }

        if (rbContagiousYes.isChecked()) {
            if (!cbCont1.isChecked() &&
                    !cbCont2.isChecked() &&
                    !cbCont3.isChecked() &&
                    !cbCont4.isChecked() &&
                    !cbCont5.isChecked() &&
                    !cbCont6.isChecked() &&
                    !cbCont7.isChecked() &&
                    !cbCont8.isChecked() &&
                    !cbCont9.isChecked() &&
                    !cbCont10.isChecked() &&
                    !cbCont11.isChecked() &&
                    !cbCont12.isChecked() &&
                    !cbCont13.isChecked() &&
                    !cbCont14.isChecked() &&
                    !cbCont15.isChecked() &&
                    !cbCont16.isChecked() &&
                    !cbCont17.isChecked() &&
                    !cbCont18.isChecked() &&
                    !cbCont19.isChecked() &&
                    !cbCont20.isChecked() &&
                    !cbCont21.isChecked() &&
                    !cbCont22.isChecked() &&
                    !cbCont23.isChecked() &&
                    !cbCont24.isChecked() &&
                    !cbCont25.isChecked() &&
                    !cbCont26.isChecked() &&
                    !cbCont27.isChecked() &&
                    !cbCont28.isChecked() &&
                    !cbCont29.isChecked() &&
                    !cbCont30.isChecked() &&
                    !cbCont31.isChecked() &&
                    !cbCont32.isChecked() &&
                    !cbCont33.isChecked() &&
                    !cbCont34.isChecked() &&
                    !cbCont35.isChecked() &&
                    !cbCont36.isChecked() &&
                    !cbCont37.isChecked() &&
                    !cbCont38.isChecked() &&
                    !cbCont39.isChecked() &&
                    !cbCont40.isChecked() &&
                    !cbCont41.isChecked() &&
                    !cbCont42.isChecked() &&
                    !cbCont43.isChecked() &&
                    !cbCont44.isChecked() &&
                    !cbCont45.isChecked() &&
                    !cbCont46.isChecked() &&
                    !cbCont47.isChecked() &&
                    !cbCont48.isChecked() &&
                    !cbCont49.isChecked() &&
                    !cbCont50.isChecked() &&
                    !cbCont51.isChecked()) {
                conthPass = false;
            }

            if (cbCont51.isChecked()) {
                conthAPass = ModelCheckForm.checkEditText(etAnotherCong);
            }
        }

        if (rbAllergicYes.isChecked()) {
            allePass = ModelCheckForm.checkEditText(etAllergic);
        }

        if (rbDisabledYes.isChecked()) {
            if (!cbDisabled1.isChecked() &&
                    !cbDisabled2.isChecked() &&
                    !cbDisabled3.isChecked() &&
                    !cbDisabled4.isChecked() &&
                    !cbDisabled5.isChecked() &&
                    !cbDisabled6.isChecked()) {
                disPass = false;
            }
        }

        if (rbAnotherReligion.isChecked()) {
            regionPass = ModelCheckForm.checkEditText(etAnotherReligion);
        }

        if (rbTransportationYes.isChecked()) {
            if (!cbTrans1.isChecked() &&
                    !cbTrans2.isChecked() &&
                    !cbTrans3.isChecked() &&
                    !cbTrans4.isChecked()) {
                transPass = false;
            }
        }

        fnamePass = ModelCheckForm.checkEditText(etFirstName);
        lnamePass = ModelCheckForm.checkEditText(etLastName);
        personidPass = ModelCheckForm.checkEditText(etPersonalID);
        birthPass = ModelCheckForm.checkEditText(etBirtDate);

        nationPass = ModelCheckForm.checkSpinner(spNationality);
        prefixPass = ModelCheckForm.checkSpinner(spNationality);
        bloodPass = ModelCheckForm.checkSpinner(spBloodType);
        maritalPass = ModelCheckForm.checkSpinner(spMaritalStatus);
        conPass = ModelCheckForm.checkSpinner(spContributor);

        if (nationPass == true) {
            if (fnamePass == true) {
                if (lnamePass == true) {
                    if (prefixPass == true) {
                        if (sexPass == true) {
                            if (personidPass == true) {
                                if (birthPass == true) {
                                    if (bloodPass == true) {
                                        if (livingPass == true) {
                                            if (maritalPass == true) {
                                                if (inregisPass == true) {
                                                    if (inhousePass == true) {
                                                        if (dwellerPass == true) {
                                                            if (graduatePass == true) {
                                                                if (expertSPass == true) {
                                                                    if (irPPass == true) {
                                                                        if (irCPass == true) {
                                                                            if (ihPPass == true) {
                                                                                if (ihCPass == true) {
                                                                                    if (jobPass == true) {
                                                                                        if (job1Pass == true) {
                                                                                            if (job1APass == true) {
                                                                                                if (incomePass == true) {
                                                                                                    if (conghPass == true) {
                                                                                                        if (conghAPass == true) {
                                                                                                            if (conthPass == true) {
                                                                                                                if (conthAPass == true) {
                                                                                                                    if (allePass == true) {
                                                                                                                        if (disPass == true) {
                                                                                                                            if (regionPass == true) {
                                                                                                                                if (transPass == true) {
                                                                                                                                    if (conPass == true) {
                                                                                                                                        formPass = 0;
                                                                                                                                    } else {
                                                                                                                                        formPass = 32;
                                                                                                                                    }
                                                                                                                                } else {
                                                                                                                                    formPass = 31;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                formPass = 30;
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            formPass = 29;
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        formPass = 28;
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    formPass = 27;
                                                                                                                }
                                                                                                            } else {
                                                                                                                formPass = 26;
                                                                                                            }
                                                                                                        } else {
                                                                                                            formPass = 25;
                                                                                                        }
                                                                                                    } else {
                                                                                                        formPass = 24;
                                                                                                    }
                                                                                                } else {
                                                                                                    formPass = 23;
                                                                                                }
                                                                                            } else {
                                                                                                formPass = 22;
                                                                                            }
                                                                                        } else {
                                                                                            formPass = 21;
                                                                                        }
                                                                                    } else {
                                                                                        formPass = 20;
                                                                                    }
                                                                                } else {
                                                                                    formPass = 19;
                                                                                }
                                                                            } else {
                                                                                formPass = 18;
                                                                            }
                                                                        } else {
                                                                            formPass = 17;
                                                                        }
                                                                    } else {
                                                                        formPass = 16;
                                                                    }
                                                                } else {
                                                                    formPass = 15;
                                                                }
                                                            } else {
                                                                formPass = 14;
                                                            }
                                                        } else {
                                                            formPass = 13;
                                                        }
                                                    } else {
                                                        formPass = 12;
                                                    }
                                                } else {
                                                    formPass = 11;
                                                }
                                            } else {
                                                formPass = 10;
                                            }
                                        } else {
                                            formPass = 9;
                                        }
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
        } else if (Item.get("PropType").toString().equals("สัตว์เลี้ยงในครัวเรือน")) {
            intent = new Intent(getApplicationContext(), PetFormActivity.class);
            intent.putExtra("PetID", SelectedIDItem);
        } else if (Item.get("PropType").toString().equals("สัตว์เลี้ยงเพื่อการเกษตร")) {
            intent = new Intent(getApplicationContext(), AnimalFormActivity.class);
            intent.putExtra("AnimalID", SelectedIDItem);

        }
        intent.putExtra("PersonID", PersonID);
        intent.putExtra("HouseID", HouseID);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> Item = (HashMap<String, String>) lvProperty.getItemAtPosition(position);
        SelectedIDItem = Item.get("ID").toString();
        SelectedNameItem = Item.get("PropType").toString();
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        ContentValues Val = new ContentValues();
                        if (SelectedNameItem.equals("ที่ดิน")) {
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population_asset_land", Val, "land_running", SelectedIDItem);
                            Val = new ContentValues();
                            Val.put("upload_status", "1");
                            db.UpdateData("population", Val, "population_idcard", etPersonalID.getText().toString());
                            Toast.makeText(getApplicationContext(), "ลบข้อมูล " + SelectedNameItem + " เรียบร้อย", Toast.LENGTH_SHORT).show();
                        } else if (SelectedNameItem.equals("ยานพาหนะ")) {
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population_asset_vehicle", Val, "vehicle_running", SelectedIDItem);
                            Val = new ContentValues();
                            Val.put("upload_status", "1");
                            db.UpdateData("population", Val, "population_idcard", etPersonalID.getText().toString());
                            Toast.makeText(getApplicationContext(), "ลบข้อมูล " + SelectedNameItem + " เรียบร้อย", Toast.LENGTH_SHORT).show();
                        } else if (SelectedNameItem.equals("สัตว์เลี้ยงในครัวเรือน")) {
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population_asset_pet", Val, "pet_running", SelectedIDItem);
                            Val = new ContentValues();
                            Val.put("upload_status", "1");
                            db.UpdateData("population", Val, "population_idcard", etPersonalID.getText().toString());
                            Toast.makeText(getApplicationContext(), "ลบข้อมูล " + SelectedNameItem + " เรียบร้อย", Toast.LENGTH_SHORT).show();
                        } else if (SelectedNameItem.equals("สัตว์เลี้ยงเพื่อการเกษตร")) {
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population_asset_animal", Val, "animal_running", SelectedIDItem);
                            Val = new ContentValues();
                            Val.put("upload_status", "1");
                            db.UpdateData("population", Val, "population_idcard", etPersonalID.getText().toString());
                            Toast.makeText(getApplicationContext(), "ลบข้อมูล " + SelectedNameItem + " เรียบร้อย", Toast.LENGTH_SHORT).show();
                        }
                        setListView();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ท่านต้องการลบข้อมูล " + SelectedNameItem + " ?").setPositiveButton("ใช่", dialogClickListener)
                .setNegativeButton("ไม่ใช่", dialogClickListener).show();
        return true;
    }
}
