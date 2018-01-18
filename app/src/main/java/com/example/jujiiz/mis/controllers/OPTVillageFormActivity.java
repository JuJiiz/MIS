package com.example.jujiiz.mis.controllers;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelCheckboxCheck;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class OPTVillageFormActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, OnMapReadyCallback,
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
    LatLng latLng, NewLatlng;

    String VilleID;
    myDBClass db = new myDBClass(this);
    ArrayList<HashMap<String, String>> VilleList, SoilList, SlumList, AreaList;

    EditText etLat, etLong, etArea, etSlum, etEstablished, etHistory, etProblem, etConAdFName, etConAdLName, etConAdAppoint, etLocationNumber, etHno, etAlley, etStreet, etZipCode, etTel;
    EditText etDistributorF, etDistributorL, etDistributorTel, etDate;
    Button btnCurrentLocation, btnImagePick, btnSavingData;
    TextView tvVillageName;
    RadioGroup rgLovelyCommunity, rgSlum;
    RadioButton radioButton, rbSlumYes;
    LinearLayout loSlum;
    CheckBox cbSC1, cbSC2, cbSC3, cbSC4, cbSoil1, cbSoil2, cbSoil3, cbSoil4, cbSoil5;
    Spinner spProvince, spDistrict, spSubDistrict;
    ImageView ivImage;

    Boolean onDataReady = false;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat etd = new SimpleDateFormat("DD/MM/YYYY");
    ContentValues values, Val;
    Bitmap selectedImage;
    //String

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_village_form);
        VilleID = getIntent().getExtras().getString("VillageID");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        init();

        setField();
    }

    private void init() {
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFrag.getMapAsync(this);

        loSlum = (LinearLayout) findViewById(R.id.loSlum);

        ivImage = (ImageView) findViewById(R.id.ivImage);

        tvVillageName = (TextView) findViewById(R.id.tvVillageName);

        etLat = (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);
        etArea = (EditText) findViewById(R.id.etArea);
        etSlum = (EditText) findViewById(R.id.etSlum);
        etEstablished = (EditText) findViewById(R.id.etEstablished);
        etHistory = (EditText) findViewById(R.id.etHistory);
        etProblem = (EditText) findViewById(R.id.etProblem);
        etConAdFName = (EditText) findViewById(R.id.etConAdFName);
        etConAdLName = (EditText) findViewById(R.id.etConAdLName);
        etConAdAppoint = (EditText) findViewById(R.id.etConAdAppoint);
        etLocationNumber = (EditText) findViewById(R.id.etLocationNumber);
        etHno = (EditText) findViewById(R.id.etHno);
        etAlley = (EditText) findViewById(R.id.etAlley);
        etStreet = (EditText) findViewById(R.id.etStreet);
        etZipCode = (EditText) findViewById(R.id.etZipCode);
        etTel = (EditText) findViewById(R.id.etTel);
        etDistributorF = (EditText) findViewById(R.id.etDistributorF);
        etDistributorL = (EditText) findViewById(R.id.etDistributorL);
        etDistributorTel = (EditText) findViewById(R.id.etDistributorTel);
        etDate = (EditText) findViewById(R.id.etDate);

        btnCurrentLocation = (Button) findViewById(R.id.btnCurrentLocation);
        btnCurrentLocation.setOnClickListener(this);
        btnImagePick = (Button) findViewById(R.id.btnImagePick);
        btnImagePick.setOnClickListener(this);
        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);

        rgLovelyCommunity = (RadioGroup) findViewById(R.id.rgLovelyCommunity);
        rgSlum = (RadioGroup) findViewById(R.id.rgSlum);

        rbSlumYes = (RadioButton) findViewById(R.id.rbSlumYes);
        rbSlumYes.setOnCheckedChangeListener(this);

        cbSC1 = (CheckBox) findViewById(R.id.cbSC1);
        cbSC2 = (CheckBox) findViewById(R.id.cbSC2);
        cbSC3 = (CheckBox) findViewById(R.id.cbSC3);
        cbSC4 = (CheckBox) findViewById(R.id.cbSC4);
        cbSoil1 = (CheckBox) findViewById(R.id.cbSoil1);
        cbSoil2 = (CheckBox) findViewById(R.id.cbSoil2);
        cbSoil3 = (CheckBox) findViewById(R.id.cbSoil3);
        cbSoil4 = (CheckBox) findViewById(R.id.cbSoil4);
        cbSoil5 = (CheckBox) findViewById(R.id.cbSoil5);
        cbSC1.setOnCheckedChangeListener(this);
        cbSC2.setOnCheckedChangeListener(this);
        cbSC3.setOnCheckedChangeListener(this);
        cbSC4.setOnCheckedChangeListener(this);
        cbSoil1.setOnCheckedChangeListener(this);
        cbSoil2.setOnCheckedChangeListener(this);
        cbSoil3.setOnCheckedChangeListener(this);
        cbSoil4.setOnCheckedChangeListener(this);
        cbSoil5.setOnCheckedChangeListener(this);

        spProvince = (Spinner) findViewById(R.id.spProvince);
        spDistrict = (Spinner) findViewById(R.id.spDistrict);
        spSubDistrict = (Spinner) findViewById(R.id.spSubDistrict);
    }

    private void setField() {
        VilleList = db.SelectWhereData("vilage", "vilage_id", VilleID);
        if (!VilleList.isEmpty()) {
            String strLat = VilleList.get(0).get("vilage_location_lat");
            String strLng = VilleList.get(0).get("vilage_location_lng");
            String strLive = VilleList.get(0).get("vilage_liveable");
            if (!strLat.equals("") && !strLng.equals("")) {
                onDataReady = true;
            }
            tvVillageName.setText(VilleList.get(0).get("vilage_name"));
            etLat.append(VilleList.get(0).get("vilage_location_lat"));
            etLong.append(VilleList.get(0).get("vilage_location_lng"));
            etArea.append(VilleList.get(0).get("vilage_aor"));
            //etSlum.append(VilleList.get(0).get("vilage_name"));
            etEstablished.append(VilleList.get(0).get("vilage_start"));
            etHistory.append(VilleList.get(0).get("vilage_history"));
            etProblem.append(VilleList.get(0).get("vilage_problem"));
            etConAdFName.append(VilleList.get(0).get("vilage_sup_firstname"));
            etConAdLName.append(VilleList.get(0).get("vilage_sup_lastname"));
            etConAdAppoint.append(VilleList.get(0).get("vilage_sup_startdate"));
            etLocationNumber.append(VilleList.get(0).get("vilage_address_no"));
            etHno.append(VilleList.get(0).get("vilage_no"));
            etAlley.append(VilleList.get(0).get("vilage_alley"));
            etStreet.append(VilleList.get(0).get("vilage_road"));
            etZipCode.append(VilleList.get(0).get("vilage_postal_code"));
            etTel.append(VilleList.get(0).get("vilage_tel"));

            etDistributorF.append(VilleList.get(0).get("vilage_informant_firstname"));
            etDistributorL.append(VilleList.get(0).get("vilage_informant_lastname"));
            etDistributorTel.append(VilleList.get(0).get("vilage_informant_tel"));
            etDate.setText(etd.format(Calendar.getInstance().getTime()));

            if (!strLive.equals("")) {
                ((RadioButton) rgLovelyCommunity.getChildAt(Integer.parseInt(strLive))).setChecked(true);
            }
        }

        SlumList = db.SelectWhereData("vilage_slum", "vilage_id", VilleID);
        AreaList = db.SelectWhereData("vilage_area", "vilage_id", VilleID);
        SoilList = db.SelectWhereData("vilage_soil", "vilage_id", VilleID);
        Log.d("MYLOG", "SlumList: " + SlumList);
        Log.d("MYLOG", "AreaList: " + AreaList);
        Log.d("MYLOG", "SoilList: " + SoilList);
        if (!AreaList.isEmpty() && !SlumList.isEmpty() && !SoilList.isEmpty()) {
            String strSlum = SlumList.get(0).get("slum_status");
            if (!strSlum.equals("")) {
                if (strSlum.equals("0")) {
                    ((RadioButton) rgSlum.getChildAt(0)).setChecked(true);
                }
                if (strSlum.equals("1")) {
                    rbSlumYes.setChecked(true);
                }
            }
            etSlum.append(SlumList.get(0).get("slum_address"));

            ModelCheckboxCheck.checkboxSetCheck(cbSC1, AreaList.get(0).get("area_river"));
            ModelCheckboxCheck.checkboxSetCheck(cbSC2, AreaList.get(0).get("area_plateau"));
            ModelCheckboxCheck.checkboxSetCheck(cbSC3, AreaList.get(0).get("area_mountain"));
            ModelCheckboxCheck.checkboxSetCheck(cbSC4, AreaList.get(0).get("area_coastal"));

            ModelCheckboxCheck.checkboxSetCheck(cbSoil1, SoilList.get(0).get("soil_bog"));
            ModelCheckboxCheck.checkboxSetCheck(cbSoil2, SoilList.get(0).get("soil_don"));
            ModelCheckboxCheck.checkboxSetCheck(cbSoil3, SoilList.get(0).get("soil_clay"));
            ModelCheckboxCheck.checkboxSetCheck(cbSoil4, SoilList.get(0).get("soil_mold"));
            ModelCheckboxCheck.checkboxSetCheck(cbSoil5, SoilList.get(0).get("soil_sandy"));
        } else {
            insertAreaSoilSlum();
        }
    }

    private void insertAreaSoilSlum() {
        String date = df.format(Calendar.getInstance().getTime());
        values = new ContentValues();
        values.put("area_river", "0");
        values.put("area_plateau", "0");
        values.put("area_mountain", "0");
        values.put("area_coastal", "0");
        values.put("vilage_id", VilleID);
        values.put("cr_by", "JuJiiz");
        values.put("cr_date", date);
        values.put("upd_by", "JuJiiz");
        values.put("upd_date", date);
        values.put("ACTIVE", "Y");
        db.InsertData("vilage_area", values);

        values = new ContentValues();
        values.put("slum_status", "");
        values.put("slum_address", "");
        values.put("vilage_id", VilleID);
        values.put("cr_by", "JuJiiz");
        values.put("cr_date", date);
        values.put("upd_by", "JuJiiz");
        values.put("upd_date", date);
        values.put("ACTIVE", "Y");
        db.InsertData("vilage_slum", values);

        values = new ContentValues();
        values.put("soil_bog", "0");
        values.put("soil_don", "0");
        values.put("soil_clay", "0");
        values.put("soil_mold", "0");
        values.put("soil_sandy", "0");
        values.put("vilage_id", VilleID);
        values.put("cr_by", "JuJiiz");
        values.put("cr_date", date);
        values.put("upd_by", "JuJiiz");
        values.put("upd_date", date);
        values.put("ACTIVE", "Y");
        db.InsertData("vilage_soil", values);
        Log.d("MYLOG", "INSERT COMPLETE");
    }

    private void updateData() {
        String date = df.format(Calendar.getInstance().getTime());

        Val = new ContentValues();
        Val.put("vilage_location_lat", etLat.getText().toString());
        Val.put("vilage_location_lng", etLong.getText().toString());
        Val.put("vilage_aor", etArea.getText().toString());
        int live = rgLovelyCommunity.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(live);
        int idx = rgLovelyCommunity.indexOfChild(radioButton);
        if (idx < 0) {
            idx = 0;
        }
        Val.put("vilage_liveable", idx);
        Val.put("vilage_start", etEstablished.getText().toString());
        Val.put("vilage_history", etHistory.getText().toString());
        Val.put("vilage_problem", etProblem.getText().toString());
        Val.put("vilage_sup_firstname", etConAdFName.getText().toString());
        Val.put("vilage_sup_lastname", etConAdLName.getText().toString());
        Val.put("vilage_sup_startdate", etConAdAppoint.getText().toString());
        Val.put("vilage_address_no", etLocationNumber.getText().toString());
        Val.put("vilage_alley", etAlley.getText().toString());
        Val.put("vilage_road", etStreet.getText().toString());
        Val.put("vilage_province", "");
        Val.put("vilage_district", "");
        Val.put("vilage_sub_district", "");
        Val.put("vilage_postal_code", etZipCode.getText().toString());
        Val.put("vilage_tel", etTel.getText().toString());
        Val.put("vilage_img", "");
        Val.put("vilage_informant_firstname", etDistributorF.getText().toString());
        Val.put("vilage_informant_lastname", etDistributorL.getText().toString());
        Val.put("vilage_informant_tel", etDistributorTel.getText().toString());
        Val.put("survey_status", "1");
        Val.put("upd_by", "JuJiiz");
        Val.put("upd_date", date);
        db.UpdateData("vilage", Val, "vilage_id", VilleID);

        Val = new ContentValues();
        int slum = rgSlum.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(slum);
        int idxSlum = rgLovelyCommunity.indexOfChild(radioButton);
        if (idxSlum < 0) {
            idxSlum = 0;
        }
        Val.put("slum_status", idxSlum);
        Val.put("slum_address", etSlum.getText().toString());
        Val.put("upd_by", "JuJiiz");
        Val.put("upd_date", date);
        db.UpdateData("vilage_slum", Val, "vilage_id", VilleID);

        Val = new ContentValues();
        Val.put("area_river", ModelCheckboxCheck.checkboxReturnCheck(cbSC1));
        Val.put("area_plateau", ModelCheckboxCheck.checkboxReturnCheck(cbSC2));
        Val.put("area_mountain", ModelCheckboxCheck.checkboxReturnCheck(cbSC3));
        Val.put("area_coastal", ModelCheckboxCheck.checkboxReturnCheck(cbSC4));
        Val.put("upd_by", "JuJiiz");
        Val.put("upd_date", date);
        db.UpdateData("vilage_area", Val, "vilage_id", VilleID);

        Val = new ContentValues();
        Val.put("soil_bog", ModelCheckboxCheck.checkboxReturnCheck(cbSoil1));
        Val.put("soil_don", ModelCheckboxCheck.checkboxReturnCheck(cbSoil2));
        Val.put("soil_clay", ModelCheckboxCheck.checkboxReturnCheck(cbSoil3));
        Val.put("soil_mold", ModelCheckboxCheck.checkboxReturnCheck(cbSoil4));
        Val.put("soil_sandy", ModelCheckboxCheck.checkboxReturnCheck(cbSoil5));
        Val.put("upd_by", "JuJiiz");
        Val.put("upd_date", date);
        db.UpdateData("vilage_soil", Val, "vilage_id", VilleID);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton == rbSlumYes) {
            ModelShowHideLayout.radiobuttonShowHide(rbSlumYes, loSlum);
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

        if (onDataReady == true) {
            NewLatlng = new LatLng(Double.parseDouble(VilleList.get(0).get("vilage_location_lat")), Double.parseDouble(VilleList.get(0).get("vilage_location_lng")));
            markerOptions = new MarkerOptions();
            markerOptions.position(NewLatlng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            if (mCurrLocationMarker != null)
                mCurrLocationMarker.remove();
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NewLatlng, 16));
            onDataReady = false;
        }
        if (onDataReady == false) {
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
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
                                ActivityCompat.requestPermissions(OPTVillageFormActivity.this,
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
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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

            // other 'case' lines to check for other
            // permissions this app might request
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
            if (mCurrLocationMarker != null)
                mCurrLocationMarker.remove();
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

            etLat.setText(mLastLocation.convert(mLastLocation.getLatitude(), mLastLocation.FORMAT_DEGREES));
            etLong.setText(mLastLocation.convert(mLastLocation.getLongitude(), mLastLocation.FORMAT_DEGREES));
        }
        if (view == btnSavingData) {
            updateData();
            Toast.makeText(this, "แก้ไขข้อมูลสำเร็จแล้ว", Toast.LENGTH_SHORT).show();

            this.finish();
            //Intent intent = new Intent(getApplicationContext(), OPTActivity.class);
            //startActivity(intent);
        }
        if (view == btnImagePick) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        }
    }
}
