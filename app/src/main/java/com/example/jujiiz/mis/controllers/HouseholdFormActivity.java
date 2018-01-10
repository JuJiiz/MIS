package com.example.jujiiz.mis.controllers;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCurrentCalendar;
import com.example.jujiiz.mis.models.ModelShowHideLayout;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HouseholdFormActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    MarkerOptions markerOptions;
    LatLng latLng;

    final myDBClass myDb = new myDBClass(this);

    String COL5, COL19, COL42, COL43;
    double COL6, COL7;
    int COL1, COL2, COL3, COL4, COL8, COL9, COL10, COL11, COL12, COL13, COL14, COL15, COL16, COL17, COL18, COLH1, COL20, COL21, COL22, COL23, COL24, COL25, COLH2, COL26, COL27, COL28, COL29, COL30, COL31, COL32, COL33, COL34, COL35, COL36, COL37, COL38, COL39, COL40, COL41;

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

    LinearLayout loAnotherProblem;

    RadioButton rbProbEnvyNo, rbProbEnvyYes;
    CheckBox cbSound, cbShock, cbDust, cbSmell, cbAir, cbWater, cbGarbage;
    LinearLayout loEnvyProblem, loSound, loShock, loDust, loSmell, loAir, loWater, loGarbage;
    //RadioButton rbSoundHigh,rbSoundMid,rbSoundLow,rbShockHigh,rbShockMid,rbShockLow,rbDustHigh,rbDustMid,rbDustLow,rbSmellHigh,rbSmellMid,rbSmellLow,rbAirHigh,rbAirMid,rbAirLow,rbWaterHigh,rbWaterMid,rbWaterLow,rbGarbageHigh,rbGarbageMid,rbGarbageLow;

    RadioButton rbDisasterNo, rbDisasterYes;
    CheckBox cbStorm, cbFlood, cbMud, cbEarthquake, cbBuilding, cbDrought, cbCold, cbRoad, cbFire, cbFireForest, cbSmoke, cbChemical, cbPlague, cbWeed;
    LinearLayout loDisaster, loStorm, loFlood, loMud, loEarthquake, loBuilding, loDrought, loCold, loRoad, loFire, loFireForest, loSmoke, loChemical, loPlague, loWeed;
    //RadioButton rbStormHigh,rbStormMid,rbStormLow,rbFloodHigh,rbFloodMid,rbFloodLow,rbMudHigh,rbMudMid,rbMudLow,rbEarthquakeHigh,rbEarthquakeMid,rbEarthquakeLow,rbBuildingHigh,rbBuildingMid,rbBuildingLow,rbDroughtHigh,rbDroughtMid,rbDroughtLow,rbColdHigh,rbColdMid,rbColdLow,rbRoadHigh,rbRoadMid,rbRoadLow,rbFireHigh,rbFireMid,rbFireLow,rbFireForestHigh,rbFireForestMid,rbFireForestLow,rbSmokeHigh,rbSmokeMid,rbSmokeLow,rbChemicalHigh,rbChemicalMid,rbChemicalLow,rbPlagueHigh,rbPlagueMid,rbPlagueLow,rbWeedHigh,rbWeedMid,rbWeedLow;

    Intent intent;
    Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_form);

        init();

        myDBClass myDb = new myDBClass(this); // เริ่มต้นการเรียกใช้งาน Class
        myDb.getWritableDatabase(); // เริ่มการเขียน Database (เป็นการสร้าง Database และ Table)

        ModelCurrentCalendar.edittextCurrentCalendar(this, etDate);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)
    }

    private void init() {
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFrag.getMapAsync(this);
        btnCurrentLocation = (Button) findViewById(R.id.btnCurrentLocation);
        btnCurrentLocation.setOnClickListener(this);
        etLat = (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);

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
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
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

        //Place current location marker
        latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

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
                        mGoogleMap.setMyLocationEnabled(true);
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
                selectedImage = BitmapFactory.decodeStream(imageStream);
                ivImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnCurrentLocation) {

            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

            etLat.setText(mLastLocation.convert(mLastLocation.getLatitude(), mLastLocation.FORMAT_DEGREES));
            etLong.setText(mLastLocation.convert(mLastLocation.getLongitude(), mLastLocation.FORMAT_DEGREES));
        }
        if (view == btnAddDweller) {
            intent = new Intent(this, PeopleFormActivity.class);
            this.startActivity(intent);
        }
        if (view == btnImagePick) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        }
        if (view == btnSavingData) {
            ContentValues Val = new ContentValues();
            /*Val.put("house_id", etHouseID.getText().toString());
            Val.put("house_no", etHouseNumber.getText().toString());
            Val.put("vilage_id", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);
            Val.put("Tel", strTel);*/
            myDb.InsertData("house",Val);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup == registerRadioGroup) {
            int register = registerRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = registerRadioGroup.indexOfChild(radioButton);
            COL1 = idx;
        }
        if (radioGroup == housestatusRadioGroup) {
            int register = housestatusRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = housestatusRadioGroup.indexOfChild(radioButton);
            COL2 = idx;
        }
        if (radioGroup == familyRadioGroup) {
            int register = familyRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = familyRadioGroup.indexOfChild(radioButton);
            COL3 = idx;
        }
        if (radioGroup == probenvyRadioGroup) {
            int register = probenvyRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = probenvyRadioGroup.indexOfChild(radioButton);
            COLH1 = idx;
        }
        if (radioGroup == soundRadioGroup) {
            int register = soundRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = soundRadioGroup.indexOfChild(radioButton);
            COL20 = idx;
        }
        if (radioGroup == shockRadioGroup) {
            int register = shockRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = shockRadioGroup.indexOfChild(radioButton);
            COL21 = idx;
        }
        if (radioGroup == dustRadioGroup) {
            int register = dustRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = dustRadioGroup.indexOfChild(radioButton);
            COL22 = idx;
        }
        if (radioGroup == smellRadioGroup) {
            int register = smellRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = smellRadioGroup.indexOfChild(radioButton);
            COL23 = idx;
        }
        if (radioGroup == airRadioGroup) {
            int register = airRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = airRadioGroup.indexOfChild(radioButton);
            COL24 = idx;
        }
        if (radioGroup == waterRadioGroup) {
            int register = waterRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = waterRadioGroup.indexOfChild(radioButton);
            COL25 = idx;
        }
        if (radioGroup == garbageRadioGroup) {
            int register = garbageRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = garbageRadioGroup.indexOfChild(radioButton);
            COL26 = idx;
        }
        if (radioGroup == disasterRadioGroup) {
            int register = disasterRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = disasterRadioGroup.indexOfChild(radioButton);
            COLH2 = idx;
        }
        if (radioGroup == stormRadioGroup) {
            int register = stormRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = stormRadioGroup.indexOfChild(radioButton);
            COL27 = idx;
        }
        if (radioGroup == floodRadioGroup) {
            int register = floodRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = floodRadioGroup.indexOfChild(radioButton);
            COL28 = idx;
        }
        if (radioGroup == mudRadioGroup) {
            int register = mudRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = mudRadioGroup.indexOfChild(radioButton);
            COL29 = idx;
        }
        if (radioGroup == earthquakeRadioGroup) {
            int register = earthquakeRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = earthquakeRadioGroup.indexOfChild(radioButton);
            COL30 = idx;
        }
        if (radioGroup == buildingRadioGroup) {
            int register = buildingRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = buildingRadioGroup.indexOfChild(radioButton);
            COL31 = idx;
        }
        if (radioGroup == droughtRadioGroup) {
            int register = droughtRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = droughtRadioGroup.indexOfChild(radioButton);
            COL32 = idx;
        }
        if (radioGroup == coldRadioGroup) {
            int register = coldRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = coldRadioGroup.indexOfChild(radioButton);
            COL33 = idx;
        }
        if (radioGroup == roadRadioGroup) {
            int register = roadRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = roadRadioGroup.indexOfChild(radioButton);
            COL34 = idx;
        }
        if (radioGroup == fireRadioGroup) {
            int register = fireRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = fireRadioGroup.indexOfChild(radioButton);
            COL35 = idx;
        }
        if (radioGroup == fireforestRadioGroup) {
            int register = fireforestRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = fireforestRadioGroup.indexOfChild(radioButton);
            COL36 = idx;
        }
        if (radioGroup == smokeRadioGroup) {
            int register = smokeRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = smokeRadioGroup.indexOfChild(radioButton);
            COL37 = idx;
        }
        if (radioGroup == chemicalRadioGroup) {
            int register = chemicalRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = chemicalRadioGroup.indexOfChild(radioButton);
            COL38 = idx;
        }
        if (radioGroup == plagueRadioGroup) {
            int register = plagueRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = plagueRadioGroup.indexOfChild(radioButton);
            COL39 = idx;
        }
        if (radioGroup == weedRadioGroup) {
            int register = weedRadioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(register);
            int idx = weedRadioGroup.indexOfChild(radioButton);
            COL40 = idx;
        }
    }
}
