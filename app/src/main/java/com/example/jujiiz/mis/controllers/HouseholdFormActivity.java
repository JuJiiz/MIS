package com.example.jujiiz.mis.controllers;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCheckForm;
import com.example.jujiiz.mis.models.ModelCheckboxCheck;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelSetting;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class HouseholdFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        /*GoogleMap.OnMarkerDragListener,*/
        LocationListener {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    MarkerOptions markerOptions;
    LatLng latLng, NewLatlng;

    //RadioButton rbRegisterYes,rbRegisterNo,rbNormalHouse,rbAbandonedHouse,rbDemolitionHouse,rbSingleFamily,rbExtendedFamily;
    RadioGroup registerRadioGroup, housestatusRadioGroup, familyRadioGroup;
    RadioGroup probenvyRadioGroup, soundRadioGroup, shockRadioGroup, dustRadioGroup, smellRadioGroup, airRadioGroup, waterRadioGroup, garbageRadioGroup;
    RadioGroup disasterRadioGroup, stormRadioGroup, floodRadioGroup, mudRadioGroup, earthquakeRadioGroup, buildingRadioGroup, droughtRadioGroup, coldRadioGroup, roadRadioGroup, fireRadioGroup, fireforestRadioGroup, smokeRadioGroup, chemicalRadioGroup, plagueRadioGroup, weedRadioGroup;
    RadioButton radioButton;
    EditText etHouseNumber, etHouseID, etLat, etLong, etAnotherProblem, etDate;
    Spinner spVillageName, spContributor;
    CheckBox cbProb1, cbProb2, cbProb3, cbProb4, cbProb5, cbProb6, cbProb7, cbProb8, cbProb9, cbProb10;

    Button btnSavingData, btnCurrentLocation, btnAddDweller, btnImagePick;
    ImageView ivImage;
    ImageButton btnCameraPick;

    LinearLayout loAnotherProblem;
    ListView listHousehold;

    RadioButton rbProbEnvyNo, rbProbEnvyYes;
    CheckBox cbSound, cbShock, cbDust, cbSmell, cbAir, cbWater, cbGarbage;
    LinearLayout loEnvyProblem, loSound, loShock, loDust, loSmell, loAir, loWater, loGarbage;
    //RadioButton rbSoundHigh,rbSoundMid,rbSoundLow,rbShockHigh,rbShockMid,rbShockLow,rbDustHigh,rbDustMid,rbDustLow,rbSmellHigh,rbSmellMid,rbSmellLow,rbAirHigh,rbAirMid,rbAirLow,rbWaterHigh,rbWaterMid,rbWaterLow,rbGarbageHigh,rbGarbageMid,rbGarbageLow;

    RadioButton rbDisasterNo, rbDisasterYes;
    CheckBox cbStorm, cbFlood, cbMud, cbEarthquake, cbBuilding, cbDrought, cbCold, cbRoad, cbFire, cbFireForest, cbSmoke, cbChemical, cbPlague, cbWeed;
    LinearLayout loDisaster, loStorm, loFlood, loMud, loEarthquake, loBuilding, loDrought, loCold, loRoad, loFire, loFireForest, loSmoke, loChemical, loPlague, loWeed;
    //RadioButton rbStormHigh,rbStormMid,rbStormLow,rbFloodHigh,rbFloodMid,rbFloodLow,rbMudHigh,rbMudMid,rbMudLow,rbEarthquakeHigh,rbEarthquakeMid,rbEarthquakeLow,rbBuildingHigh,rbBuildingMid,rbBuildingLow,rbDroughtHigh,rbDroughtMid,rbDroughtLow,rbColdHigh,rbColdMid,rbColdLow,rbRoadHigh,rbRoadMid,rbRoadLow,rbFireHigh,rbFireMid,rbFireLow,rbFireForestHigh,rbFireForestMid,rbFireForestLow,rbSmokeHigh,rbSmokeMid,rbSmokeLow,rbChemicalHigh,rbChemicalMid,rbChemicalLow,rbPlagueHigh,rbPlagueMid,rbPlagueLow,rbWeedHigh,rbWeedMid,rbWeedLow;

    Intent intent;
    myDBClass db = new myDBClass(this);
    String HouseID;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ContentValues Val;
    ArrayList<HashMap<String, String>> HouseList, DwellerList, DwellerActive, HProbList, HEnProbList, HDisasList, TestList;
    ArrayList<String> Dweller = new ArrayList<String>();
    ArrayAdapter<String> dwellerArrayAdapter;
    Boolean onDataReady = false;
    String SelectedIDItem, SelectedFNameItem, SelectedLNameItem;
    Bitmap imgBitmap;
    byte[] imgByteArray = null;

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_form);
        HouseID = getIntent().getExtras().getString("HouseID");

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

    private void init() {
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFrag.getMapAsync(this);
        btnCurrentLocation = (Button) findViewById(R.id.btnCurrentLocation);
        btnCurrentLocation.setOnClickListener(this);
        etLat = (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);

        spContributor = (Spinner) findViewById(R.id.spContributor);

        listHousehold = (ListView) findViewById(R.id.listHousehold);
        listHousehold.setOnItemClickListener(this);
        listHousehold.setOnItemLongClickListener(this);
        /*listHousehold.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/

        registerRadioGroup = (RadioGroup) findViewById(R.id.registerRadioGroup);
        registerRadioGroup.setOnCheckedChangeListener(this);
        housestatusRadioGroup = (RadioGroup) findViewById(R.id.housestatusRadioGroup);
        housestatusRadioGroup.setOnCheckedChangeListener(this);
        familyRadioGroup = (RadioGroup) findViewById(R.id.familyRadioGroup);
        familyRadioGroup.setOnCheckedChangeListener(this);

        probenvyRadioGroup = (RadioGroup) findViewById(R.id.probenvyRadioGroup);
        probenvyRadioGroup.setOnCheckedChangeListener(this);
        soundRadioGroup = (RadioGroup) findViewById(R.id.soundRadioGroup);
        soundRadioGroup.setOnCheckedChangeListener(this);
        shockRadioGroup = (RadioGroup) findViewById(R.id.shockRadioGroup);
        shockRadioGroup.setOnCheckedChangeListener(this);
        dustRadioGroup = (RadioGroup) findViewById(R.id.dustRadioGroup);
        dustRadioGroup.setOnCheckedChangeListener(this);
        smellRadioGroup = (RadioGroup) findViewById(R.id.smellRadioGroup);
        smellRadioGroup.setOnCheckedChangeListener(this);
        airRadioGroup = (RadioGroup) findViewById(R.id.airRadioGroup);
        airRadioGroup.setOnCheckedChangeListener(this);
        waterRadioGroup = (RadioGroup) findViewById(R.id.waterRadioGroup);
        waterRadioGroup.setOnCheckedChangeListener(this);
        garbageRadioGroup = (RadioGroup) findViewById(R.id.garbageRadioGroup);
        garbageRadioGroup.setOnCheckedChangeListener(this);

        disasterRadioGroup = (RadioGroup) findViewById(R.id.disasterRadioGroup);
        disasterRadioGroup.setOnCheckedChangeListener(this);
        stormRadioGroup = (RadioGroup) findViewById(R.id.stormRadioGroup);
        stormRadioGroup.setOnCheckedChangeListener(this);
        floodRadioGroup = (RadioGroup) findViewById(R.id.floodRadioGroup);
        floodRadioGroup.setOnCheckedChangeListener(this);
        mudRadioGroup = (RadioGroup) findViewById(R.id.mudRadioGroup);
        mudRadioGroup.setOnCheckedChangeListener(this);
        earthquakeRadioGroup = (RadioGroup) findViewById(R.id.earthquakeRadioGroup);
        earthquakeRadioGroup.setOnCheckedChangeListener(this);
        buildingRadioGroup = (RadioGroup) findViewById(R.id.buildingRadioGroup);
        buildingRadioGroup.setOnCheckedChangeListener(this);
        droughtRadioGroup = (RadioGroup) findViewById(R.id.droughtRadioGroup);
        droughtRadioGroup.setOnCheckedChangeListener(this);
        coldRadioGroup = (RadioGroup) findViewById(R.id.coldRadioGroup);
        coldRadioGroup.setOnCheckedChangeListener(this);
        roadRadioGroup = (RadioGroup) findViewById(R.id.roadRadioGroup);
        roadRadioGroup.setOnCheckedChangeListener(this);
        fireRadioGroup = (RadioGroup) findViewById(R.id.fireRadioGroup);
        fireRadioGroup.setOnCheckedChangeListener(this);
        fireforestRadioGroup = (RadioGroup) findViewById(R.id.fireforestRadioGroup);
        fireforestRadioGroup.setOnCheckedChangeListener(this);
        smokeRadioGroup = (RadioGroup) findViewById(R.id.smokeRadioGroup);
        smokeRadioGroup.setOnCheckedChangeListener(this);
        chemicalRadioGroup = (RadioGroup) findViewById(R.id.chemicalRadioGroup);
        chemicalRadioGroup.setOnCheckedChangeListener(this);
        plagueRadioGroup = (RadioGroup) findViewById(R.id.plagueRadioGroup);
        plagueRadioGroup.setOnCheckedChangeListener(this);
        weedRadioGroup = (RadioGroup) findViewById(R.id.weedRadioGroup);
        weedRadioGroup.setOnCheckedChangeListener(this);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);

        btnAddDweller = (Button) findViewById(R.id.btnAddDweller);
        btnAddDweller.setOnClickListener(this);

        btnImagePick = (Button) findViewById(R.id.btnImagePick);
        btnImagePick.setOnClickListener(this);
        btnCameraPick = (ImageButton) findViewById(R.id.btnCameraPick);
        btnCameraPick.setOnClickListener(this);

        etDate = (EditText) findViewById(R.id.etDate);
        etHouseID = (EditText) findViewById(R.id.etHouseID);
        etHouseNumber = (EditText) findViewById(R.id.etHouseNumber);
        etAnotherProblem = (EditText) findViewById(R.id.etAnotherProblem);

        ivImage = (ImageView) findViewById(R.id.ivImage);

        rbProbEnvyYes = (RadioButton) findViewById(R.id.rbProbEnvyYes);
        rbProbEnvyNo = (RadioButton) findViewById(R.id.rbProbEnvyNo);
        rbDisasterYes = (RadioButton) findViewById(R.id.rbDisasterYes);
        rbDisasterNo = (RadioButton) findViewById(R.id.rbDisasterNo);

        rbProbEnvyYes.setOnCheckedChangeListener(this);
        rbProbEnvyNo.setOnCheckedChangeListener(this);
        rbDisasterYes.setOnCheckedChangeListener(this);
        rbDisasterNo.setOnCheckedChangeListener(this);

        cbProb1 = (CheckBox) findViewById(R.id.cbProb1);
        cbProb2 = (CheckBox) findViewById(R.id.cbProb2);
        cbProb3 = (CheckBox) findViewById(R.id.cbProb3);
        cbProb4 = (CheckBox) findViewById(R.id.cbProb4);
        cbProb5 = (CheckBox) findViewById(R.id.cbProb5);
        cbProb6 = (CheckBox) findViewById(R.id.cbProb6);
        cbProb7 = (CheckBox) findViewById(R.id.cbProb7);
        cbProb8 = (CheckBox) findViewById(R.id.cbProb8);
        cbProb9 = (CheckBox) findViewById(R.id.cbProb9);

        cbProb10 = (CheckBox) findViewById(R.id.cbProb10);
        cbSound = (CheckBox) findViewById(R.id.cbSound);
        cbShock = (CheckBox) findViewById(R.id.cbShock);
        cbDust = (CheckBox) findViewById(R.id.cbDust);
        cbSmell = (CheckBox) findViewById(R.id.cbSmell);
        cbAir = (CheckBox) findViewById(R.id.cbAir);
        cbWater = (CheckBox) findViewById(R.id.cbWater);
        cbGarbage = (CheckBox) findViewById(R.id.cbGarbage);
        cbStorm = (CheckBox) findViewById(R.id.cbStorm);
        cbFlood = (CheckBox) findViewById(R.id.cbFlood);
        cbMud = (CheckBox) findViewById(R.id.cbMud);
        cbEarthquake = (CheckBox) findViewById(R.id.cbEarthquake);
        cbBuilding = (CheckBox) findViewById(R.id.cbBuilding);
        cbDrought = (CheckBox) findViewById(R.id.cbDrought);
        cbCold = (CheckBox) findViewById(R.id.cbCold);
        cbRoad = (CheckBox) findViewById(R.id.cbRoad);
        cbFire = (CheckBox) findViewById(R.id.cbFire);
        cbFireForest = (CheckBox) findViewById(R.id.cbFireForest);
        cbSmoke = (CheckBox) findViewById(R.id.cbSmoke);
        cbChemical = (CheckBox) findViewById(R.id.cbChemical);
        cbPlague = (CheckBox) findViewById(R.id.cbPlague);
        cbWeed = (CheckBox) findViewById(R.id.cbWeed);

        cbProb10.setOnCheckedChangeListener(this);
        cbSound.setOnCheckedChangeListener(this);
        cbShock.setOnCheckedChangeListener(this);
        cbDust.setOnCheckedChangeListener(this);
        cbSmell.setOnCheckedChangeListener(this);
        cbAir.setOnCheckedChangeListener(this);
        cbWater.setOnCheckedChangeListener(this);
        cbGarbage.setOnCheckedChangeListener(this);
        cbStorm.setOnCheckedChangeListener(this);
        cbFlood.setOnCheckedChangeListener(this);
        cbMud.setOnCheckedChangeListener(this);
        cbEarthquake.setOnCheckedChangeListener(this);
        cbBuilding.setOnCheckedChangeListener(this);
        cbDrought.setOnCheckedChangeListener(this);
        cbCold.setOnCheckedChangeListener(this);
        cbRoad.setOnCheckedChangeListener(this);
        cbFire.setOnCheckedChangeListener(this);
        cbFireForest.setOnCheckedChangeListener(this);
        cbSmoke.setOnCheckedChangeListener(this);
        cbChemical.setOnCheckedChangeListener(this);
        cbPlague.setOnCheckedChangeListener(this);
        cbWeed.setOnCheckedChangeListener(this);

        loAnotherProblem = (LinearLayout) findViewById(R.id.loAnotherProblem);
        loEnvyProblem = (LinearLayout) findViewById(R.id.loEnvyProblem); //Radio
        loSound = (LinearLayout) findViewById(R.id.loSound);
        loShock = (LinearLayout) findViewById(R.id.loShock);
        loDust = (LinearLayout) findViewById(R.id.loDust);
        loSmell = (LinearLayout) findViewById(R.id.loSmell);
        loAir = (LinearLayout) findViewById(R.id.loAir);
        loWater = (LinearLayout) findViewById(R.id.loWater);
        loGarbage = (LinearLayout) findViewById(R.id.loGarbage);
        loDisaster = (LinearLayout) findViewById(R.id.loDisaster); //Radio
        loStorm = (LinearLayout) findViewById(R.id.loStorm);
        loFlood = (LinearLayout) findViewById(R.id.loFlood);
        loMud = (LinearLayout) findViewById(R.id.loMud);
        loEarthquake = (LinearLayout) findViewById(R.id.loEarthquake);
        loBuilding = (LinearLayout) findViewById(R.id.loBuilding);
        loDrought = (LinearLayout) findViewById(R.id.loDrought);
        loCold = (LinearLayout) findViewById(R.id.loCold);
        loRoad = (LinearLayout) findViewById(R.id.loRoad);
        loFire = (LinearLayout) findViewById(R.id.loFire);
        loFireForest = (LinearLayout) findViewById(R.id.loFireForest);
        loSmoke = (LinearLayout) findViewById(R.id.loSmoke);
        loChemical = (LinearLayout) findViewById(R.id.loChemical);
        loPlague = (LinearLayout) findViewById(R.id.loPlague);
        loWeed = (LinearLayout) findViewById(R.id.loWeed);

    }

    private void setField() {
        HouseList = db.SelectWhereData("tr14", "house_id", HouseID);
        HouseList = db.SelectWhereData("house", "house_id", HouseID);
        if (!HouseList.isEmpty()) {
            ((RadioButton) registerRadioGroup.getChildAt(0)).setChecked(true);
            etHouseID.setText(HouseList.get(0).get("house_id"));
            etHouseNumber.setText(HouseList.get(0).get("house_no"));
            etLat.setText(HouseList.get(0).get("house_location_lat"));
            etLong.setText(HouseList.get(0).get("house_location_lng"));
            if (!HouseList.get(0).get("house_location_lat").equals("") && !HouseList.get(0).get("house_location_lng").equals("")) {
                onDataReady = true;
            }
            if (!HouseList.get(0).get("house_in_registry").equals("")) {
                ((RadioButton) registerRadioGroup.getChildAt(Integer.parseInt(HouseList.get(0).get("house_in_registry")))).setChecked(true);
            }
            if (!HouseList.get(0).get("house_status").equals("")) {
                ((RadioButton) housestatusRadioGroup.getChildAt(Integer.parseInt(HouseList.get(0).get("house_status")))).setChecked(true);
            }
            if (!HouseList.get(0).get("house_family_type").equals("")) {
                ((RadioButton) familyRadioGroup.getChildAt(Integer.parseInt(HouseList.get(0).get("house_family_type")))).setChecked(true);
            }

            if (!HouseList.get(0).get("distributor").equals("")) {
                int spinnerPositionContri = dwellerArrayAdapter.getPosition(HouseList.get(0).get("distributor"));
                spContributor.setSelection(spinnerPositionContri);
            }
            ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);

            if (!HouseList.get(0).get("distributor_img").equals("")) {
                imgByteArray = Base64.decode(HouseList.get(0).get("distributor_img"), Base64.DEFAULT);
                imgBitmap = BitmapFactory.decodeByteArray(imgByteArray, 0, imgByteArray.length);
                ivImage.setImageBitmap(imgBitmap);
                ivImage.setVisibility(View.VISIBLE);
            }

            HProbList = db.SelectWhereData("house_problem", "house_id", HouseID);
            if (!HProbList.isEmpty()) {
                ModelCheckboxCheck.checkboxSetCheck(cbProb1, HProbList.get(0).get("prob1"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb2, HProbList.get(0).get("prob2"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb3, HProbList.get(0).get("prob3"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb4, HProbList.get(0).get("prob4"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb5, HProbList.get(0).get("prob5"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb6, HProbList.get(0).get("prob6"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb7, HProbList.get(0).get("prob7"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb8, HProbList.get(0).get("prob8"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb9, HProbList.get(0).get("prob1"));
                ModelCheckboxCheck.checkboxSetCheck(cbProb10, HProbList.get(0).get("prob1"));
                etAnotherProblem.setText(HProbList.get(0).get("problem_another"));
            }

            HEnProbList = db.SelectWhereData("house_envyprob", "house_id", HouseID);
            if (!HEnProbList.isEmpty()) {
                ((RadioButton) probenvyRadioGroup.getChildAt(Integer.parseInt(HEnProbList.get(0).get("envyprob_type")))).setChecked(true);
                ModelCheckboxCheck.enviProb(cbSound, soundRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep1")));
                ModelCheckboxCheck.enviProb(cbShock, shockRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep2")));
                ModelCheckboxCheck.enviProb(cbDust, dustRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep3")));
                ModelCheckboxCheck.enviProb(cbSmell, smellRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep4")));
                ModelCheckboxCheck.enviProb(cbAir, airRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep5")));
                ModelCheckboxCheck.enviProb(cbWater, waterRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep6")));
                ModelCheckboxCheck.enviProb(cbGarbage, garbageRadioGroup, Integer.parseInt(HEnProbList.get(0).get("ep7")));
            }

            HDisasList = db.SelectWhereData("house_disaster", "house_id", HouseID);
            if (!HDisasList.isEmpty()) {
                ((RadioButton) disasterRadioGroup.getChildAt(Integer.parseInt(HDisasList.get(0).get("disaster_type")))).setChecked(true);
                ModelCheckboxCheck.enviProb(cbStorm, stormRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis1")));
                ModelCheckboxCheck.enviProb(cbFlood, floodRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis2")));
                ModelCheckboxCheck.enviProb(cbMud, mudRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis3")));
                ModelCheckboxCheck.enviProb(cbEarthquake, earthquakeRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis4")));
                ModelCheckboxCheck.enviProb(cbBuilding, buildingRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis5")));
                ModelCheckboxCheck.enviProb(cbDrought, droughtRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis6")));
                ModelCheckboxCheck.enviProb(cbCold, coldRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis7")));
                ModelCheckboxCheck.enviProb(cbRoad, roadRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis8")));
                ModelCheckboxCheck.enviProb(cbFire, fireRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis9")));
                ModelCheckboxCheck.enviProb(cbFireForest, fireforestRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis10")));
                ModelCheckboxCheck.enviProb(cbSmoke, smokeRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis11")));
                ModelCheckboxCheck.enviProb(cbChemical, chemicalRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis12")));
                ModelCheckboxCheck.enviProb(cbPlague, plagueRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis13")));
                ModelCheckboxCheck.enviProb(cbWeed, weedRadioGroup, Integer.parseInt(HDisasList.get(0).get("dis14")));
            }
        }
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());

        Val = new ContentValues();
        Val.put("house_id", etHouseID.getText().toString());
        Val.put("house_no", etHouseNumber.getText().toString());
        if (!etLat.getText().toString().equals("")) {
            Val.put("house_location_lat", etLat.getText().toString());
        } else {
            Val.put("house_location_lat", "0");
        }
        if (!etLong.getText().toString().equals("")) {
            Val.put("house_location_lng", etLong.getText().toString());
        } else {
            Val.put("house_location_lng", "0");
        }
        int register = registerRadioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(register);
        int idxregister = registerRadioGroup.indexOfChild(radioButton);
        if (idxregister <= 0) {
            Val.put("house_in_registry", idxregister);
        } else {
            Val.put("house_in_registry", "0");
        }

        int hStatus = housestatusRadioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(hStatus);
        int idxhStatus = housestatusRadioGroup.indexOfChild(radioButton);
        if (idxhStatus <= 0) {
            Val.put("house_status", idxhStatus);
        } else {
            Val.put("house_status", "0");
        }
        int famtype = familyRadioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(famtype);
        int idxfamtype = familyRadioGroup.indexOfChild(radioButton);
        if (idxfamtype <= 0) {
            Val.put("house_family_type", idxfamtype);
        } else {
            Val.put("house_family_type", "0");
        }
        Val.put("distributor", spContributor.getSelectedItem().toString());
        Val.put("survey_status", "1");
        Val.put("upload_status", "1");

        if (ivImage.getDrawable() != null) {
            String imgString = Base64.encodeToString(imgByteArray, Base64.NO_WRAP);
            Val.put("distributor_img", imgString);
        } else {
            Val.put("distributor_img", "");
        }

        Val.put("upd_by", "");
        Val.put("upd_date", date);
        db.UpdateData("house", Val, "house_id", HouseID);
        ///////////////////////////////////House///////////////////////////////////////////

        Val = new ContentValues();
        Val.put("prob1", ModelCheckboxCheck.checkboxReturnCheck(cbProb1));
        Val.put("prob2", ModelCheckboxCheck.checkboxReturnCheck(cbProb2));
        Val.put("prob3", ModelCheckboxCheck.checkboxReturnCheck(cbProb3));
        Val.put("prob4", ModelCheckboxCheck.checkboxReturnCheck(cbProb4));
        Val.put("prob5", ModelCheckboxCheck.checkboxReturnCheck(cbProb5));
        Val.put("prob6", ModelCheckboxCheck.checkboxReturnCheck(cbProb6));
        Val.put("prob7", ModelCheckboxCheck.checkboxReturnCheck(cbProb7));
        Val.put("prob8", ModelCheckboxCheck.checkboxReturnCheck(cbProb8));
        Val.put("prob9", ModelCheckboxCheck.checkboxReturnCheck(cbProb9));
        Val.put("prob10", ModelCheckboxCheck.checkboxReturnCheck(cbProb10));
        if (ModelCheckboxCheck.checkboxReturnCheck(cbProb10) == 0) {
            Val.put("problem_another", "");
        } else {
            Val.put("problem_another", etAnotherProblem.getText().toString());
        }
        Val.put("house_id", HouseID);
        Val.put("upd_by", "");
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        HProbList = db.SelectWhereData("house_problem", "house_id", HouseID);
        if (HProbList.isEmpty()) {
            Val.put("cr_by", "JuJiiz");
            Val.put("cr_date", date);
            db.InsertData("house_problem", Val);
        } else {
            db.UpdateData("house_problem", Val, "house_id", HouseID);
        }
        ///////////////////////////////////House Problem///////////////////////////////////////////

        Val = new ContentValues();
        if (rbProbEnvyNo.isChecked() == true) {
            Val.put("envyprob_type", "0");
            Val.put("ep1", "0");
            Val.put("ep2", "0");
            Val.put("ep3", "0");
            Val.put("ep4", "0");
            Val.put("ep5", "0");
            Val.put("ep6", "0");
            Val.put("ep7", "0");
        } else if (rbProbEnvyYes.isChecked() == true) {
            Val.put("envyprob_type", "1");
            if (cbSound.isChecked() == true) {
                if (soundRadioGroup.getCheckedRadioButtonId() != -1) {
                    int sound = soundRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(sound);
                    int idxsound = soundRadioGroup.indexOfChild(radioButton);
                    if (idxsound == 0) {
                        Val.put("ep1", "3");
                    }
                    if (idxsound == 1) {
                        Val.put("ep1", "2");
                    }
                    if (idxsound == 2) {
                        Val.put("ep1", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep1", "0");
            }
            if (cbShock.isChecked() == true) {
                if (shockRadioGroup.getCheckedRadioButtonId() != -1) {
                    int shock = shockRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(shock);
                    int idxshock = shockRadioGroup.indexOfChild(radioButton);
                    if (idxshock == 0) {
                        Val.put("ep2", "3");
                    }
                    if (idxshock == 1) {
                        Val.put("ep2", "2");
                    }
                    if (idxshock == 2) {
                        Val.put("ep2", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep2", "0");
            }
            if (cbDust.isChecked() == true) {
                if (dustRadioGroup.getCheckedRadioButtonId() != -1) {
                    int dust = dustRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(dust);
                    int idxdust = dustRadioGroup.indexOfChild(radioButton);
                    if (dust == 0) {
                        Val.put("ep3", "3");
                    }
                    if (dust == 1) {
                        Val.put("ep3", "2");
                    }
                    if (dust == 2) {
                        Val.put("ep3", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep3", "0");
            }
            if (cbSmell.isChecked() == true) {
                if (smellRadioGroup.getCheckedRadioButtonId() != -1) {
                    int smell = smellRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(smell);
                    int idxsmell = smellRadioGroup.indexOfChild(radioButton);
                    if (idxsmell == 0) {
                        Val.put("ep4", "3");
                    }
                    if (idxsmell == 1) {
                        Val.put("ep4", "2");
                    }
                    if (idxsmell == 2) {
                        Val.put("ep4", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep4", "0");
            }
            if (cbAir.isChecked() == true) {
                if (airRadioGroup.getCheckedRadioButtonId() != -1) {
                    int air = airRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(air);
                    int idxair = airRadioGroup.indexOfChild(radioButton);
                    if (idxair == 0) {
                        Val.put("ep5", "3");
                    }
                    if (idxair == 1) {
                        Val.put("ep5", "2");
                    }
                    if (idxair == 2) {
                        Val.put("ep5", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep5", "0");
            }
            if (cbWater.isChecked() == true) {
                if (waterRadioGroup.getCheckedRadioButtonId() != -1) {
                    int water = waterRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(water);
                    int idxwater = waterRadioGroup.indexOfChild(radioButton);
                    if (idxwater == 0) {
                        Val.put("ep6", "3");
                    }
                    if (idxwater == 1) {
                        Val.put("ep6", "2");
                    }
                    if (idxwater == 2) {
                        Val.put("ep6", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep6", "0");
            }
            if (cbGarbage.isChecked() == true) {
                if (garbageRadioGroup.getCheckedRadioButtonId() != -1) {
                    int garbage = garbageRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(garbage);
                    int idxgarbage = garbageRadioGroup.indexOfChild(radioButton);
                    if (idxgarbage == 0) {
                        Val.put("ep7", "3");
                    }
                    if (idxgarbage == 1) {
                        Val.put("ep7", "2");
                    }
                    if (idxgarbage == 2) {
                        Val.put("ep7", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("ep7", "0");
            }
        }
        Val.put("house_id", HouseID);
        Val.put("upd_by", "");
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        HEnProbList = db.SelectWhereData("house_envyprob", "house_id", HouseID);
        if (HEnProbList.isEmpty()) {
            Val.put("cr_by", "JuJiiz");
            Val.put("cr_date", date);
            db.InsertData("house_envyprob", Val);
        } else {
            db.UpdateData("house_envyprob", Val, "house_id", HouseID);
        }
        ///////////////////////////////////House Environment Problem///////////////////////////////////////////

        Val = new ContentValues();
        if (rbDisasterNo.isChecked() == true) {
            Val.put("disaster_type", "0");
            Val.put("dis1", "0");
            Val.put("dis2", "0");
            Val.put("dis3", "0");
            Val.put("dis4", "0");
            Val.put("dis5", "0");
            Val.put("dis6", "0");
            Val.put("dis7", "0");
            Val.put("dis8", "0");
            Val.put("dis9", "0");
            Val.put("dis10", "0");
            Val.put("dis11", "0");
            Val.put("dis12", "0");
            Val.put("dis13", "0");
            Val.put("dis14", "0");
        } else if (rbDisasterYes.isChecked() == true) {
            Val.put("disaster_type", "1");
            if (cbStorm.isChecked() == true) {
                if (stormRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = stormRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = stormRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis1", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis1", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis1", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis1", "0");
            }
            if (cbFlood.isChecked() == true) {
                if (floodRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = floodRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = floodRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis2", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis2", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis2", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis2", "0");
            }
            if (cbMud.isChecked() == true) {
                if (mudRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = mudRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = mudRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis3", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis3", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis3", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis3", "0");
            }
            if (cbEarthquake.isChecked() == true) {
                if (earthquakeRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = earthquakeRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = earthquakeRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis4", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis4", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis4", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis4", "0");
            }
            if (cbBuilding.isChecked() == true) {
                if (buildingRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = buildingRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = buildingRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis5", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis5", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis5", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis5", "0");
            }
            if (cbDrought.isChecked() == true) {
                if (droughtRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = droughtRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = droughtRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis6", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis6", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis6", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis6", "0");
            }
            if (cbCold.isChecked() == true) {
                if (coldRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = coldRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = coldRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis7", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis7", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis7", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis7", "0");
            }
            if (cbRoad.isChecked() == true) {
                if (roadRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = roadRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = roadRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis8", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis8", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis8", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis8", "0");
            }
            if (cbFire.isChecked() == true) {
                if (fireRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = fireRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = fireRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis9", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis9", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis9", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis9", "0");
            }
            if (cbFireForest.isChecked() == true) {
                if (fireforestRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = fireforestRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = fireforestRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis10", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis10", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis10", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis10", "0");
            }
            if (cbSmoke.isChecked() == true) {
                if (smokeRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = smokeRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = smokeRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis11", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis11", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis11", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis11", "0");
            }
            if (cbChemical.isChecked() == true) {
                if (chemicalRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = chemicalRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = chemicalRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis12", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis12", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis12", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis12", "0");
            }
            if (cbPlague.isChecked() == true) {
                if (plagueRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = plagueRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = plagueRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis13", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis13", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis13", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis13", "0");
            }
            if (cbWeed.isChecked() == true) {
                if (weedRadioGroup.getCheckedRadioButtonId() != -1) {
                    int rg = weedRadioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(rg);
                    int idx = weedRadioGroup.indexOfChild(radioButton);
                    if (idx == 0) {
                        Val.put("dis14", "3");
                    }
                    if (idx == 1) {
                        Val.put("dis14", "2");
                    }
                    if (idx == 2) {
                        Val.put("dis14", "1");
                    }
                } else {
                    Toast.makeText(this, "กรุณาระบุ ระดับ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Val.put("dis14", "0");
            }
        }
        Val.put("house_id", HouseID);
        Val.put("upd_by", "");
        Val.put("upd_date", date);
        Val.put("ACTIVE", "Y");
        HDisasList = db.SelectWhereData("house_disaster", "house_id", HouseID);
        if (HDisasList.isEmpty()) {
            Val.put("cr_by", "JuJiiz");
            Val.put("cr_date", date);
            db.InsertData("house_disaster", Val);
        } else {
            db.UpdateData("house_disaster", Val, "house_id", HouseID);
        }
        ///////////////////////////////////House Disaster///////////////////////////////////////////
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

    private void setListView() {
        String strLatent = "*";
        String strFName = "FName";
        String strLName = "LName";
        String strSStatus = "Status";
        String survey = "survey";
        String latent = "*";
        String strPopulationID = "ID";
        DwellerActive = new ArrayList<HashMap<String, String>>();
        DwellerList = db.SelectWhereData("population", "house_id", HouseID);
        if (!DwellerList.isEmpty()) {
            for (int i = 0; i < DwellerList.size(); i++) {
                String strActive = DwellerList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    if (DwellerList.get(i).get("residence_status").equals("0")) {
                        latent = "*";
                    }
                    if (DwellerList.get(i).get("residence_status").equals("1")) {
                        latent = "";
                    }
                    temp.put(strLatent, latent);
                    temp.put(strFName, DwellerList.get(i).get("firstname"));
                    temp.put(strLName, DwellerList.get(i).get("lastname"));
                    if (DwellerList.get(i).get("survey_status").equals("0")) {
                        survey = "ยังไม่สำรวจ";
                    }
                    if (DwellerList.get(i).get("survey_status").equals("1")) {
                        survey = "สำรวจแล้ว";
                    }
                    temp.put(strSStatus, survey);
                    temp.put(strPopulationID, DwellerList.get(i).get("population_idcard"));
                    DwellerActive.add(temp);
                }
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, DwellerActive, R.layout.view_dweller_household_column,
                    new String[]{strLatent, strFName, strLName, strSStatus, strPopulationID},
                    new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
            );
            listHousehold.setAdapter(simpleAdapter);

            ModelSetting.listviewHeight(listHousehold);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //RadioButton
        if (compoundButton == rbProbEnvyYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbProbEnvyYes, loEnvyProblem);
        }
        if (compoundButton == rbDisasterYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbDisasterYes, loDisaster);
        }

        //Checkbox
        if (compoundButton == cbProb10) {
            ModelShowHideLayout.checkboxShowHide(cbProb10, loAnotherProblem);
        }
        if (compoundButton == cbSound) {
            ModelShowHideLayout.checkboxShowHide(cbSound, loSound);
        }
        if (compoundButton == cbShock) {
            ModelShowHideLayout.checkboxShowHide(cbShock, loShock);
        }
        if (compoundButton == cbDust) {
            ModelShowHideLayout.checkboxShowHide(cbDust, loDust);
        }
        if (compoundButton == cbSmell) {
            ModelShowHideLayout.checkboxShowHide(cbSmell, loSmell);
        }
        if (compoundButton == cbAir) {
            ModelShowHideLayout.checkboxShowHide(cbAir, loAir);
        }
        if (compoundButton == cbWater) {
            ModelShowHideLayout.checkboxShowHide(cbWater, loWater);
        }
        if (compoundButton == cbGarbage) {
            ModelShowHideLayout.checkboxShowHide(cbGarbage, loGarbage);
        }
        if (compoundButton == cbStorm) {
            ModelShowHideLayout.checkboxShowHide(cbStorm, loStorm);
        }
        if (compoundButton == cbFlood) {
            ModelShowHideLayout.checkboxShowHide(cbFlood, loFlood);
        }
        if (compoundButton == cbMud) {
            ModelShowHideLayout.checkboxShowHide(cbMud, loMud);
        }
        if (compoundButton == cbEarthquake) {
            ModelShowHideLayout.checkboxShowHide(cbEarthquake, loEarthquake);
        }
        if (compoundButton == cbBuilding) {
            ModelShowHideLayout.checkboxShowHide(cbBuilding, loBuilding);
        }
        if (compoundButton == cbDrought) {
            ModelShowHideLayout.checkboxShowHide(cbDrought, loDrought);
        }
        if (compoundButton == cbCold) {
            ModelShowHideLayout.checkboxShowHide(cbCold, loCold);
        }
        if (compoundButton == cbRoad) {
            ModelShowHideLayout.checkboxShowHide(cbRoad, loRoad);
        }
        if (compoundButton == cbFire) {
            ModelShowHideLayout.checkboxShowHide(cbFire, loFire);
        }
        if (compoundButton == cbFireForest) {
            ModelShowHideLayout.checkboxShowHide(cbFireForest, loFireForest);
        }
        if (compoundButton == cbSmoke) {
            ModelShowHideLayout.checkboxShowHide(cbSmoke, loSmoke);
        }
        if (compoundButton == cbChemical) {
            ModelShowHideLayout.checkboxShowHide(cbChemical, loChemical);
        }
        if (compoundButton == cbPlague) {
            ModelShowHideLayout.checkboxShowHide(cbPlague, loPlague);
        }
        if (compoundButton == cbWeed) {
            ModelShowHideLayout.checkboxShowHide(cbWeed, loWeed);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                //mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            //mGoogleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        if (onDataReady == true) {
            NewLatlng = new LatLng(Double.parseDouble(HouseList.get(0).get("house_location_lat")), Double.parseDouble(HouseList.get(0).get("house_location_lng")));
            markerOptions = new MarkerOptions();
            markerOptions.position(NewLatlng);
            markerOptions.title("Current Position");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            if (mCurrLocationMarker != null)
                mCurrLocationMarker.remove();
            mGoogleMap.clear();
            mGoogleMap.addMarker(markerOptions).setDraggable(true);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NewLatlng, 16));
            onDataReady = false;
        }
        if (onDataReady == false) {
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        }

        btnCurrentLocation.setVisibility(View.VISIBLE);
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(HouseholdFormActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        //mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
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
    public void onClick(View view) {
        if (view == btnCurrentLocation) {

            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }
            mGoogleMap.clear();
            mGoogleMap.addMarker(markerOptions).setDraggable(true);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    mGoogleMap.clear();
                    mGoogleMap.addMarker(new MarkerOptions()
                            .position(latLng)/*.draggable(true)*/);
                    etLat.setText(String.format("%.5f", latLng.latitude));
                    etLong.setText(String.format("%.5f", latLng.longitude));
                }
            });
            etLat.setText(mLastLocation.convert(mLastLocation.getLatitude(), mLastLocation.FORMAT_DEGREES));
            etLong.setText(mLastLocation.convert(mLastLocation.getLongitude(), mLastLocation.FORMAT_DEGREES));
        }
        if (view == btnAddDweller) {
            intent = new Intent(this, PeopleFormActivity.class);
            intent.putExtra("PersonID", "Nope");
            intent.putExtra("HouseID", HouseID);
            this.startActivity(intent);
        }
        if (view == btnImagePick) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        }
        if (view == btnCameraPick) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        if (view == btnSavingData) {
            if (fieldCheck() == true) {
                updateData();
                Toast.makeText(this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "ข้อมูลไม่สมบูรณ์", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean fieldCheck() {
        Boolean formPass = false,
                regisPass = true,
                housePass = true,
                familyPass = true,
                probPass = true,
                enviPass = true,
                disasPass = true,
                conPass = true;

        regisPass = ModelCheckForm.checkRadioGroup(registerRadioGroup);
        housePass = ModelCheckForm.checkRadioGroup(housestatusRadioGroup);
        familyPass = ModelCheckForm.checkRadioGroup(familyRadioGroup);

        if (cbProb10.isChecked() == true) {
            probPass = ModelCheckForm.checkEditText(etAnotherProblem);
        }

        if (rbProbEnvyYes.isChecked() == true) {
            if (cbSound.isChecked() != true ||
                    cbShock.isChecked() != true ||
                    cbDust.isChecked() != true ||
                    cbSmell.isChecked() != true ||
                    cbAir.isChecked() != true ||
                    cbWater.isChecked() != true ||
                    cbGarbage.isChecked() != true) {
                enviPass = false;
            }

            if (cbSound.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(soundRadioGroup);
            }
            if (cbShock.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(shockRadioGroup);
            }
            if (cbDust.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(dustRadioGroup);
            }
            if (cbSmell.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(smellRadioGroup);
            }
            if (cbAir.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(airRadioGroup);
            }
            if (cbWater.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(waterRadioGroup);
            }
            if (cbGarbage.isChecked() == true) {
                enviPass = ModelCheckForm.checkRadioGroup(garbageRadioGroup);
            }
        }

        if (rbDisasterYes.isChecked() == true) {
            if (cbStorm.isChecked() != true ||
                    cbFlood.isChecked() != true ||
                    cbMud.isChecked() != true ||
                    cbEarthquake.isChecked() != true ||
                    cbBuilding.isChecked() != true ||
                    cbDrought.isChecked() != true ||
                    cbCold.isChecked() != true ||
                    cbRoad.isChecked() != true ||
                    cbFire.isChecked() != true ||
                    cbFireForest.isChecked() != true ||
                    cbSmoke.isChecked() != true ||
                    cbChemical.isChecked() != true ||
                    cbPlague.isChecked() != true ||
                    cbWeed.isChecked() != true) {
                disasPass = false;
            }

            if (cbStorm.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(stormRadioGroup);
            }
            if (cbFlood.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(floodRadioGroup);
            }
            if (cbMud.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(mudRadioGroup);
            }
            if (cbEarthquake.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(earthquakeRadioGroup);
            }
            if (cbBuilding.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(buildingRadioGroup);
            }
            if (cbDrought.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(droughtRadioGroup);
            }
            if (cbCold.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(coldRadioGroup);
            }
            if (cbRoad.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(roadRadioGroup);
            }
            if (cbFire.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(fireRadioGroup);
            }
            if (cbFireForest.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(fireforestRadioGroup);
            }
            if (cbSmoke.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(smokeRadioGroup);
            }
            if (cbChemical.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(chemicalRadioGroup);
            }
            if (cbPlague.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(plagueRadioGroup);
            }
            if (cbWeed.isChecked() == true) {
                disasPass = ModelCheckForm.checkRadioGroup(weedRadioGroup);
            }
        }

        conPass = ModelCheckForm.checkSpinner(spContributor);

        if (regisPass == true && housePass == true && familyPass == true && probPass == true && enviPass == true && disasPass == true && conPass == true) {
            formPass = true;
        } else {
            formPass = false;
        }

        return formPass;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> Item = (HashMap<String, String>) listHousehold.getItemAtPosition(position);
        SelectedIDItem = Item.get("ID").toString();
        Intent intent = new Intent(getApplicationContext(), PeopleFormActivity.class);
        intent.putExtra("PersonID", SelectedIDItem);
        intent.putExtra("HouseID", HouseID);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> Item = (HashMap<String, String>) listHousehold.getItemAtPosition(position);
        SelectedIDItem = Item.get("ID").toString();
        SelectedFNameItem = Item.get("FName").toString();
        SelectedLNameItem = Item.get("LName").toString();
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        DwellerList = db.SelectWhereData("population", "population_idcard", SelectedIDItem);
                        String residenceStatus = DwellerList.get(0).get("residence_status");

                        if (residenceStatus.equals("0")) { //ประชากรแฝง
                            Val = new ContentValues();
                            Val.put("upload_status", "1");
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population", Val, "population_idcard", SelectedIDItem);

                            Val = new ContentValues();
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population_asset_animal", Val, "population_idcard", SelectedIDItem);
                            db.UpdateData("population_asset_land", Val, "population_idcard", SelectedIDItem);
                            db.UpdateData("population_asset_pet", Val, "population_idcard", SelectedIDItem);
                            db.UpdateData("population_asset_vehicle", Val, "population_idcard", SelectedIDItem);
                            Toast.makeText(getApplicationContext(), "ลบข้อมูล " + SelectedFNameItem + " " + SelectedLNameItem + " เรียบร้อย", Toast.LENGTH_SHORT).show();

                        } else if (residenceStatus.equals("1")) { //มีชื่อในทร.14
                            Val = new ContentValues();
                            Val.put("height", "");
                            Val.put("weight", "");
                            Val.put("bloodgroup", "");
                            Val.put("living", "");
                            Val.put("maritalstatus", "");
                            Val.put("tel", "");
                            Val.put("currentaddr", "");
                            Val.put("currentaddr_province", "");
                            Val.put("currentaddr_country", "");
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
                            Val.put("upload_status", "1");
                            db.UpdateData("population", Val, "population_idcard", SelectedIDItem);

                            Val = new ContentValues();
                            Val.put("congh_type", "");
                            Val.put("congh1", "");
                            Val.put("congh2", "");
                            Val.put("congh3", "");
                            Val.put("congh4", "");
                            Val.put("congh5", "");
                            Val.put("congh_another", "");
                            db.UpdateData("population_congenitalhis", Val, "population_idcard", SelectedIDItem);

                            Val = new ContentValues();
                            Val.put("conth_type", "");
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
                            db.UpdateData("population_contagioushis", Val, "population_idcard", SelectedIDItem);

                            Val = new ContentValues();
                            Val.put("disabled_type", "");
                            Val.put("disabled1", "");
                            Val.put("disabled2", "");
                            Val.put("disabled3", "");
                            Val.put("disabled4", "");
                            Val.put("disabled5", "");
                            Val.put("disabled6", "");
                            db.UpdateData("population_disabled", Val, "population_idcard", SelectedIDItem);

                            Val = new ContentValues();
                            Val.put("transport_type", "");
                            Val.put("trans1", "");
                            Val.put("trans2", "");
                            Val.put("trans3", "");
                            Val.put("trans4", "");
                            db.UpdateData("population_transport", Val, "population_idcard", SelectedIDItem);

                            Val = new ContentValues();
                            Val.put("works_type", "");
                            db.UpdateData("population_works", Val, "population_idcard", SelectedIDItem);

                            TestList = db.SelectWhereData("population_works", "population_idcard", SelectedIDItem);

                            if (!TestList.isEmpty()) {
                                Val = new ContentValues();
                                Val.put("agri1", "");
                                Val.put("agri2", "");
                                Val.put("agri3", "");
                                Val.put("agri4", "");
                                Val.put("agri5", "");
                                Val.put("agri6", "");
                                Val.put("agri7", "");
                                Val.put("agri8", "");
                                Val.put("agri_another", "");
                                db.UpdateData("population_job_agriculture", Val, "works_id", TestList.get(0).get("works_id"));

                                Val = new ContentValues();
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
                                db.UpdateData("population_job_animal", Val, "works_id", TestList.get(0).get("works_id"));

                                Val = new ContentValues();
                                Val.put("govern1", "");
                                Val.put("govern2", "");
                                Val.put("govern3", "");
                                Val.put("govern4", "");
                                Val.put("govern_another", "");
                                db.UpdateData("population_job_govern", Val, "works_id", TestList.get(0).get("works_id"));

                                Val = new ContentValues();
                                Val.put("private1", "");
                                Val.put("private2", "");
                                Val.put("private3", "");
                                Val.put("private4", "");
                                Val.put("private5", "");
                                Val.put("private6", "");
                                Val.put("private7", "");
                                Val.put("private_another", "");
                                db.UpdateData("population_job_private", Val, "works_id", TestList.get(0).get("works_id"));
                            }

                            Val = new ContentValues();
                            Val.put("ACTIVE", "N");
                            db.UpdateData("population_asset_animal", Val, "population_idcard", SelectedIDItem);
                            db.UpdateData("population_asset_land", Val, "population_idcard", SelectedIDItem);
                            db.UpdateData("population_asset_pet", Val, "population_idcard", SelectedIDItem);
                            db.UpdateData("population_asset_vehicle", Val, "population_idcard", SelectedIDItem);

                            Toast.makeText(getApplicationContext(), "ลบข้อมูล " + SelectedFNameItem + " " + SelectedLNameItem + " เรียบร้อย", Toast.LENGTH_SHORT).show();
                        }
                        setListView();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("ท่านต้องการลบข้อมูล " + SelectedFNameItem + " " + SelectedLNameItem + " ?").setPositiveButton("ใช่", dialogClickListener)
                .setNegativeButton("ไม่ใช่", dialogClickListener).show();
        return true;
    }
}
