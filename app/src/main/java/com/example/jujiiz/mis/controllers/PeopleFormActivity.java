package com.example.jujiiz.mis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;

public class PeopleFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    Button btnLandForm, btnVehicalForm, btnPetForm;
    RadioButton rbThaiNationality, rbAnotherNationality;
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
    LinearLayout loAnotherNationality, loAnotherPrefix, loBloodType, loInRegister, loNotInRegister, loNotInHousehold, loCareer, loAgri, loAnotherAgri, loPet, loAnotherPet, loGovern, loAnotherGovern, loPrivate, loAnotherPrivate, loICMonth, loICYear, loCongenital, loContagious, loAllergic, loDisabled, loInStudy, loGraduated, loExpertise, loAnotherReligion, loTransportation;
    Spinner spAnotherNationality, spPrefix, spBloodType, spMaritalStatus, spVillageName, spCountry, spProvince, spIHCountry, spIHProvince, spInStudy, spGraduated, spExpertise, spContributor;
    EditText etFirstName, etLastName, etAnotherPrefix, etPersonalID, etBirtDate, etHeight, etWeight, etBloodType, etTel, etHNo, etHID, etAnotherAgri, etAnotherPet, etAnotherGovern, etAnotherPrivate, etICMonth, etICYear, etAllergic, etAnotherReligion, etDate;
    CheckBox cbAgri, cbAgri1, cbAgri2, cbAgri3, cbAgri4, cbAgri5, cbAgri6, cbAgri7, cbAgri8;
    CheckBox cbPet, cbPet1, cbPet2, cbPet3, cbPet4, cbPet5, cbPet6, cbPet7, cbPet8, cbPet9;
    CheckBox cbGovern, cbGovern1, cbGovern2, cbGovern3, cbGovern4, cbGovern5;
    CheckBox cbPrivate, cbPrivate1, cbPrivate2, cbPrivate3, cbPrivate4, cbPrivate5, cbPrivate6, cbPrivate7;
    CheckBox cbCong1, cbCong2, cbCong3, cbCong4, cbCong5;
    CheckBox cbCont1, cbCont2, cbCont3, cbCont4, cbCont5, cbCont6, cbCont7, cbCont8, cbCont9, cbCont10, cbCont11;
    CheckBox cbDisabled1, cbDisabled2, cbDisabled3, cbDisabled4, cbDisabled5, cbDisabled6;
    CheckBox cbTrans1, cbTrans2, cbTrans3, cbTrans4;

    String[] spPrefixArray = {"นาย", "นาง", "นางสาว", "เด็กชาย", "เด็กหญิง", "อื่นๆ (ระบุ)"};
    String[] spBloodGroupArray = {"O", "A", "B", "AB", "อื่นๆ (ระบุ)"};
    String[] spMaritalStatusArray = {"สมรส", "โสด", "หย่าร้าง", "หม้าย", "แยกกันอยู่"};
    String[] spEducationArray = {"ระดับก่อนประถมศึกษา", "ระดับประถมศึกษา", "ระดับมัธยมศึกษาตอนต้น", "ระดับมัธยมศึกษาตอนปลาย", "ระดับอนุปริญญา", "ระดับปริญญาตรี", "ระดับปริญญาโท", "ระดับปริญญาเอก"};
    String[] spExpertiseArray = {"สาขาการเกษตรและพัฒนาชนบท", "สาขาอุตสาหกรรมก่อสร้าง", "สาขาการศึกษา", "สาขาพลังงาน", "สาขาสิ่งแวดล้อม", "สาขาการเงิน", "สาขาสาธารณสุข", "สาขาอุตสาหกรรม", "สาขาเบ็ดเตล็ด", "สาขาประชากร", "สาขาเทคโนโลยีสารสนเทศและการสื่อสาร", "สาขาการท่องเที่ยว", "สาขาการขนส่ง", "สาขาการพัฒนาเมือง", "สาขาการประปาและสุขาภิบาล", "สาขาภาษาต่างประเทศ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_form);

        init();

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        spPrefix.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spPrefixArray, "เลือก"));
        spBloodType.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spBloodGroupArray, "เลือก"));
        spMaritalStatus.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spMaritalStatusArray, "เลือก"));
        spInStudy.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spEducationArray, "เลือก"));
        spGraduated.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spEducationArray, "เลือก"));
        spExpertise.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spExpertiseArray, "เลือก"));
    }

    private void init() {
        btnLandForm = (Button) findViewById(R.id.btnLandForm);
        btnLandForm.setOnClickListener(this);
        btnVehicalForm = (Button) findViewById(R.id.btnVehicalForm);
        btnVehicalForm.setOnClickListener(this);
        btnPetForm = (Button) findViewById(R.id.btnPetForm);
        btnPetForm.setOnClickListener(this);

        loAnotherNationality = (LinearLayout) findViewById(R.id.loAnotherNationality);
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
        loAnotherReligion = (LinearLayout) findViewById(R.id.loAnotherReligion);
        loTransportation = (LinearLayout) findViewById(R.id.loTransportation);

        spAnotherNationality = (Spinner) findViewById(R.id.spAnotherNationality);
        spPrefix = (Spinner) findViewById(R.id.spPrefix);
        spBloodType = (Spinner) findViewById(R.id.spBloodType);
        spMaritalStatus = (Spinner) findViewById(R.id.spMaritalStatus);
        spVillageName = (Spinner) findViewById(R.id.spVillageName);
        spCountry = (Spinner) findViewById(R.id.spCountry);
        spProvince = (Spinner) findViewById(R.id.spProvince);
        spIHCountry = (Spinner) findViewById(R.id.spIHCountry);
        spIHProvince = (Spinner) findViewById(R.id.spIHProvince);
        spInStudy = (Spinner) findViewById(R.id.spInStudy);
        spGraduated = (Spinner) findViewById(R.id.spGraduated);
        spExpertise = (Spinner) findViewById(R.id.spExpertise);
        spContributor = (Spinner) findViewById(R.id.spContributor);

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

        rbThaiNationality = (RadioButton) findViewById(R.id.rbThaiNationality);
        rbAnotherNationality = (RadioButton) findViewById(R.id.rbAnotherNationality);
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

        rbThaiNationality.setOnCheckedChangeListener(this);
        rbAnotherNationality.setOnCheckedChangeListener(this);
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

        if (compoundButton == rbAnotherNationality)
            ModelShowHideLayout.radiobuttonShowHide(rbAnotherNationality, loAnotherNationality);
        if (compoundButton == rbInRegister)
            ModelShowHideLayout.radiobuttonShowHide(rbInRegister, loInRegister);
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
        if (compoundButton == rbAnotherReligion)
            ModelShowHideLayout.radiobuttonShowHide(rbAnotherReligion, loAnotherReligion);
        if (compoundButton == rbTransportationYes)
            ModelShowHideLayout.radiobuttonShowHide(rbTransportationYes, loTransportation);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLandForm){
            Intent intent = new Intent(getApplicationContext(), LandFormActivity.class);
            startActivity(intent);
        }
        if(view == btnVehicalForm){
            Intent intent = new Intent(getApplicationContext(), VehicalFormActivity.class);
            startActivity(intent);
        }
        if(view == btnPetForm){
            Intent intent = new Intent(getApplicationContext(), PetFormActivity.class);
            startActivity(intent);
        }
    }
}
