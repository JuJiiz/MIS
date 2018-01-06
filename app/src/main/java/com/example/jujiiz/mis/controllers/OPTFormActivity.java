package com.example.jujiiz.mis.controllers;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OPTFormActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback,
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
    EditText etLat, etLong, etOPTName, etOPT์ID, etLocationNumber, etVillageNumber, etAlley, etStreet, etZipCode, etTel, etFax, etVision;

    Button btnCurrentLocation, btnSavingData,btnOPTEdit;
    Spinner spOPTType, spProvince, spDistrict, spSubDistrict;

    myDBClass db = new myDBClass(this);
    List<String> OPTTypeList;
    //ArrayList<HashMap<String, String>> OPTList;
    ArrayList<HashMap<String, String>> OPTList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_form);

        db.getWritableDatabase();

        init();

        OPTTypeList = db.SelectOPTType();
        String[] spOPTTypeArray = OPTTypeList.toArray(new String[0]);
        spOPTType.setAdapter(new ModelSpinnerAdapter(this, R.layout.simple_spinner_item, spOPTTypeArray, "เลือก"));

        setField();
    }

    private void init() {
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        mapFrag.getMapAsync(this);

        btnCurrentLocation = (Button) findViewById(R.id.btnCurrentLocation);
        btnCurrentLocation.setOnClickListener(this);

        btnSavingData = (Button) findViewById(R.id.btnSavingData);
        btnSavingData.setOnClickListener(this);
        btnOPTEdit = (Button) findViewById(R.id.btnOPTEdit);
        btnOPTEdit.setOnClickListener(this);

        etLat = (EditText) findViewById(R.id.etLat);
        etLong = (EditText) findViewById(R.id.etLong);
        etOPTName = (EditText) findViewById(R.id.etOPTName);
        etOPT์ID = (EditText) findViewById(R.id.etOPT์ID);
        etLocationNumber = (EditText) findViewById(R.id.etLocationNumber);
        etVillageNumber = (EditText) findViewById(R.id.etVillageNumber);
        etAlley = (EditText) findViewById(R.id.etAlley);
        etStreet = (EditText) findViewById(R.id.etStreet);
        etZipCode = (EditText) findViewById(R.id.etZipCode);
        etTel = (EditText) findViewById(R.id.etTel);
        etFax = (EditText) findViewById(R.id.etFax);
        etVision = (EditText) findViewById(R.id.etVision);
        spOPTType = (Spinner) findViewById(R.id.spOPTType);
    }

    private void setField() {
        OPTList = db.SelectOPT();
        Log.d("MYLOG", "OPTList: "+OPTList);
        if (!OPTList.isEmpty()) {
            etOPTName.append(OPTList.get(0).get("opt_name"));
            etOPT์ID.append(OPTList.get(0).get("opt_id"));
            spOPTType.setSelection(Integer.parseInt(OPTList.get(0).get("opt_type_id"))-1);
            etLat.append(OPTList.get(0).get("opt_location_lat"));
            etLong.append(OPTList.get(0).get("opt_location_lng"));
            etLocationNumber.append(OPTList.get(0).get("opt_address_no"));
            etVillageNumber.append(OPTList.get(0).get("opt_vilage_no"));
            etAlley.append(OPTList.get(0).get("opt_alley"));
            etStreet.append(OPTList.get(0).get("opt_road"));
            etZipCode.append(OPTList.get(0).get("opt_postal_code"));
            etTel.append(OPTList.get(0).get("opt_tel"));
            etFax.append(OPTList.get(0).get("opt_fax"));
            etVision.append(OPTList.get(0).get("opt_vision"));

            etOPTName.setEnabled(false);
            etOPT์ID.setEnabled(false);
            etLat.setEnabled(false);
            etLong.setEnabled(false);
            etLocationNumber.setEnabled(false);
            etVillageNumber.setEnabled(false);
            etAlley.setEnabled(false);
            etStreet.setEnabled(false);
            etZipCode.setEnabled(false);
            etTel.setEnabled(false);
            etFax.setEnabled(false);
            etVision.setEnabled(false);
            spOPTType.setClickable(false);
        }else{
            btnOPTEdit.setVisibility(View.GONE);
            btnSavingData.setVisibility(View.VISIBLE);
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
        //Log.d("MYLOG", "markerOptions: "+markerOptions);

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
                                ActivityCompat.requestPermissions(OPTFormActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                Log.d("KUY", "Test ");
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
    public void onClick(View view) {
        if (view == btnCurrentLocation) {

            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

            etLat.setText(mLastLocation.convert(mLastLocation.getLatitude(), mLastLocation.FORMAT_DEGREES));
            etLong.setText(mLastLocation.convert(mLastLocation.getLongitude(), mLastLocation.FORMAT_DEGREES));

            Toast.makeText(getApplicationContext(), mLastLocation.getLatitude() + " and " + mLastLocation.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        if (view == btnSavingData) {
            ContentValues Val = new ContentValues();
            Val.put("opt_id", etOPT์ID.getText().toString());
            Val.put("opt_name", etOPTName.getText().toString());
            Val.put("opt_type_id", spOPTType.getSelectedItemPosition()+1);
            Val.put("opt_location_lat", etLat.getText().toString());
            Val.put("opt_location_lng", etLong.getText().toString());
            Val.put("opt_address_no", etLocationNumber.getText().toString());
            Val.put("opt_vilage_no", etVillageNumber.getText().toString());
            Val.put("opt_alley", etAlley.getText().toString());
            Val.put("opt_road", etStreet.getText().toString());
            Val.put("opt_province", "ยโสธร");
            Val.put("opt_district", "เมือง");
            Val.put("opt_sub_district", "ในเมือง");
            Val.put("opt_postal_code", etZipCode.getText().toString());
            Val.put("opt_tel", etTel.getText().toString());
            Val.put("opt_fax", etFax.getText().toString());
            Val.put("opt_vision", etVision.getText().toString());
            Val.put("cr_by", "JuJiiz");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Val.put("cr_date", df.format(Calendar.getInstance().getTime()));
            Val.put("upd_by", "");
            Val.put("upd_date", "");
            Val.put("ACTIVE", "Y");

            if(OPTList.isEmpty()) {
                db.InsertData("opt", Val);
                Log.d("MYLOG", "Insert");
            }else{
                db.UpdateData("opt", Val,"opt_id",etOPT์ID.getText().toString());
                Log.d("MYLOG", "Update");
            }
            etOPTName.setEnabled(false);
            etOPT์ID.setEnabled(false);
            etLat.setEnabled(false);
            etLong.setEnabled(false);
            etLocationNumber.setEnabled(false);
            etVillageNumber.setEnabled(false);
            etAlley.setEnabled(false);
            etStreet.setEnabled(false);
            etZipCode.setEnabled(false);
            etTel.setEnabled(false);
            etFax.setEnabled(false);
            etVision.setEnabled(false);
            spOPTType.setClickable(false);
            btnOPTEdit.setVisibility(View.VISIBLE);
            btnSavingData.setVisibility(View.GONE);
        }
        if(view == btnOPTEdit){
            btnOPTEdit.setVisibility(View.GONE);
            btnSavingData.setVisibility(View.VISIBLE);

            etOPTName.setEnabled(true);
            etOPT์ID.setEnabled(true);
            etLat.setEnabled(true);
            etLong.setEnabled(true);
            etLocationNumber.setEnabled(true);
            etVillageNumber.setEnabled(true);
            etAlley.setEnabled(true);
            etStreet.setEnabled(true);
            etZipCode.setEnabled(true);
            etTel.setEnabled(true);
            etFax.setEnabled(true);
            etVision.setEnabled(true);
            spOPTType.setClickable(true);
        }
    }

}
