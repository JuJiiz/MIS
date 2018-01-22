package com.example.jujiiz.mis.controllers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelGetData;
import com.example.jujiiz.mis.models.ModelGetJson;
import com.example.jujiiz.mis.models.ModelSpinnerAdapter;
import com.example.jujiiz.mis.models.myDBClass;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class HouseholdActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView lvHousehold;
    Button btnSearch;
    Spinner spVName;
    EditText etSearch;

    myDBClass db = new myDBClass(this);
    ArrayList<HashMap<String, String>> HouseActive, TR14List, VilleList, TestList, List, HouseList, DwellerList, PrenameList, NatList;
    ArrayList<String> Village = new ArrayList<String>();
    ArrayAdapter<String> villeArrayAdapter;
    HashMap<String, String> temp;
    ContentValues Val;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String strVilleNo = "VNumber";
    String strHouseNo = "HNumber";
    String strVilleName = "VilleName";
    String strStatus = "Status";
    String strHID = "ID";
    String survey = " survey";
    String SelectedIDItem;
    JSONArray STRING_JSONDATA;
    String apiURL = "http://203.154.54.229/qry_tr14byall";
    String JKey = "data";
    boolean RESUME = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household);

        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); //Important!! (Form)

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        TR14List = db.SelectData("tr14");
        if (TR14List.isEmpty()) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo netInfo = manager.getActiveNetworkInfo();
                            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                                new AsynTaskDownload().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                RESUME = true;
                            } else {
                                Toast.makeText(getApplicationContext(), "การเชื่อมต่อขัดข้อง", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ไม่มีข้อมูล ทร.14\nท่านต้องการดาวน์โหลดข้อมูลหรือไม่? (ใช้Internet)").setPositiveButton("ใช่", dialogClickListener)
                    .setNegativeButton("ไม่ใช่", dialogClickListener).show();
        } else {
            setListView();
            RESUME = true;
        }
    }

    public boolean isConnectedToServer(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (RESUME == true){
            setListView();
        }
    }

    private void init() {
        lvHousehold = (ListView) findViewById(R.id.lvHousehold);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        spVName = (Spinner) findViewById(R.id.spVName);
        etSearch = (EditText) findViewById(R.id.etSearch);

        lvHousehold.setOnItemClickListener(this);

        btnSearch.setOnClickListener(this);
        /*btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);*/
    }

    private void setSpinner() {

        VilleList = db.SelectData("vilage");
        if (!VilleList.isEmpty()) {
            Village.add("ทั้งหมด");
            for (int i = 0; i < VilleList.size(); i++) {
                String strActive = VilleList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    String strVillage = VilleList.get(i).get("vilage_name");
                    if (!Village.contains(strVillage)) {
                        Village.add(strVillage);
                    }

                }
            }
            String[] spVillageArray = Village.toArray(new String[0]);
            villeArrayAdapter = ModelSpinnerAdapter.setSpinnerItem(this, spVillageArray, spVName);
        }
    }

    private void setListView() {
        HouseActive = new ArrayList<HashMap<String, String>>();
        HouseList = db.SelectData("house");
        TR14List = db.SelectData("tr14");
        if (!HouseList.isEmpty()) {
            for (int i = 0; i < HouseList.size(); i++) {
                HashMap<String, String> temp = new HashMap<String, String>();
                //TR14List = db.SelectWhereData("tr14", "house_id", HouseList.get(i).get("house_id"));
                for (int j = 0; j < TR14List.size() ; j++) {
                    if (TR14List.get(j).get("house_id").contains(HouseList.get(i).get("house_id"))) {
                        temp.put(strVilleNo, TR14List.get(0).get("vilage_no"));
                    }
                }
                temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                temp.put(strVilleName, " ");
                if (HouseList.get(i).get("survey_status").equals("0")) {
                    survey = "";
                }
                if (HouseList.get(i).get("survey_status").equals("1")) {
                    survey = "*";
                }
                temp.put(strStatus, survey);
                temp.put(strHID, HouseList.get(i).get("house_id"));
                HouseActive.add(temp);
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_household_column,
                new String[]{strVilleNo, strHouseNo, strVilleName, strStatus, strHID},
                new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
        );
        lvHousehold.setAdapter(simpleAdapter);
    }

    class AsynTaskDownload extends AsyncTask<Void, Void, Void> {
        private final ProgressDialog dialog = new ProgressDialog(HouseholdActivity.this);
        private String HNoDialogMessage = "";

        // can use UI thread here
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("กำลังดาวน์โหลด...");
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String date = df.format(Calendar.getInstance().getTime());
            String formattedDate;
            DateFormat originalFormat;
            DateFormat targetFormat;
            Date odate = null;
            STRING_JSONDATA = ModelGetData.getJsonArray(HouseholdActivity.this, apiURL, JKey);
            if (STRING_JSONDATA != null) {
                List = ModelGetJson.JsonArraytoArrayList(STRING_JSONDATA);
                if (!List.isEmpty()) {
                    for (int i = 0; i < List.size(); i++) {
                        Val = new ContentValues();
                        Val.put("tr14_running", List.get(i).get("tr14_running"));
                        Val.put("house_id", List.get(i).get("house_id"));
                        Val.put("house_no", List.get(i).get("house_no"));
                        HNoDialogMessage = List.get(i).get("house_no");
                        Val.put("p_order", List.get(i).get("p_order"));
                        Val.put("idcard", List.get(i).get("idcard"));
                        Val.put("prename", List.get(i).get("prename"));
                        Val.put("firstname", List.get(i).get("firstname"));
                        Val.put("lastname", List.get(i).get("lastname"));
                        Val.put("sex", List.get(i).get("sex"));
                        Val.put("dweller", List.get(i).get("dweller"));

                        originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                        targetFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            odate = originalFormat.parse(List.get(i).get("birthdate"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (odate != null) {
                            formattedDate = targetFormat.format(odate);
                        } else {
                            formattedDate = "";
                        }

                        Val.put("birthdate", formattedDate);
                        Val.put("nationality", List.get(i).get("nationality"));
                        Val.put("vilage_no", List.get(i).get("vilage_no"));
                        db.InsertData("tr14", Val);
                        this.dialog.setMessage("กำลังดาวน์โหลด... " + HNoDialogMessage);

                        HouseList = new ArrayList<HashMap<String, String>>();
                        HouseList = db.SelectWhereData("house", "house_id", List.get(i).get("house_id"));
                        if (HouseList.isEmpty()) {
                            Val = new ContentValues();
                            Val.put("house_id", List.get(i).get("house_id"));
                            Val.put("house_no", List.get(i).get("house_no"));
                            Val.put("vilage_id", "");
                            Val.put("house_location_lat", "");
                            Val.put("house_location_lng", "");
                            Val.put("house_in_registry", "");
                            Val.put("house_status", "");
                            Val.put("house_family_type", "");
                            Val.put("distributor", "");
                            Val.put("survey_status", "0");
                            Val.put("cr_by", "ADMIN");
                            Val.put("cr_date", date);
                            Val.put("upd_by", "");
                            Val.put("upd_date", date);
                            Val.put("ACTIVE", "Y");
                            db.InsertData("house", Val);
                        }

                        DwellerList = new ArrayList<HashMap<String, String>>();
                        DwellerList = db.SelectWhereData("population", "population_idcard", List.get(i).get("idcard"));
                        if (DwellerList.isEmpty()) {
                            Val = new ContentValues();
                            Val.put("population_idcard", List.get(i).get("idcard"));
                            Val.put("prename", List.get(i).get("prename"));
                            Val.put("firstname", List.get(i).get("firstname"));
                            Val.put("lastname", List.get(i).get("lastname"));
                            Val.put("birthdate", formattedDate);
                            Val.put("height", "");
                            Val.put("weight", "");
                            if (List.get(i).get("sex").equals("ชาย")) {
                                Val.put("sex", "M");
                            } else if (List.get(i).get("sex").equals("หญิง")) {
                                Val.put("sex", "F");
                            } else {
                                Val.put("sex", "");
                            }
                            Val.put("bloodgroup", "");
                            Val.put("living", "");
                            Val.put("maritalstatus", "");
                            Val.put("tel", "");
                            Val.put("nationality", List.get(i).get("nationality"));
                            Val.put("house_id", List.get(i).get("house_id"));
                            Val.put("currentaddr", "");
                            Val.put("currentaddr_province", "");
                            Val.put("currentaddr_country", "");
                            if (List.get(i).get("dweller").equals("เจ้าบ้าน")) {
                                Val.put("dwellerstatus", "0");
                            } else if (List.get(i).get("dweller").equals("ผู้อาศัย")) {
                                Val.put("dwellerstatus", "1");
                            }
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
                            Val.put("cr_by", "ADMIN");
                            Val.put("cr_date", date);
                            Val.put("upd_by", "");
                            Val.put("upd_date", date);
                            Val.put("ACTIVE", "Y");
                            db.InsertData("population", Val);
                        }
                    }
                }
            } else {
                Toast.makeText(HouseholdActivity.this, "ไม่มีข้อมูล", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            setListView();
            Toast.makeText(getApplicationContext(), "ดาวน์โหลดสำเร็จแล้ว", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchEvent() {
        String strEditText = etSearch.getText().toString();
        HouseList = db.SelectData("house");

        HouseActive = new ArrayList<HashMap<String, String>>();
        if (!HouseList.isEmpty()) {
            for (int i = 0; i < HouseList.size(); i++) {
                String strActive = HouseList.get(i).get("ACTIVE");
                if (strActive.equals("Y")) {
                    if (HouseList.get(i).get("house_no").equals(strEditText)) {
                        HashMap<String, String> temp = new HashMap<String, String>();
                        TR14List = db.SelectWhereData("tr14", "house_id", HouseList.get(i).get("house_id"));
                        temp.put(strVilleNo, TR14List.get(0).get("vilage_no"));
                        temp.put(strHouseNo, HouseList.get(i).get("house_no"));
                        temp.put(strVilleName, " ");
                        if (HouseList.get(i).get("survey_status").equals("0")) {
                            survey = "";
                        }
                        if (HouseList.get(i).get("survey_status").equals("1")) {
                            survey = "*";
                        }
                        temp.put(strStatus, survey);
                        temp.put(strHID, HouseList.get(i).get("house_id"));
                        HouseActive.add(temp);
                    }
                }
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, HouseActive, R.layout.view_household_column,
                    new String[]{strVilleNo, strHouseNo, strVilleName, strStatus, strHID},
                    new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
            );
            lvHousehold.setAdapter(simpleAdapter);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnSearch) {
            if (etSearch.getText().toString().equals("")) {
                setListView();
            } else {
                lvHousehold.setAdapter(null);
                searchEvent();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String, String> Item = (HashMap<String, String>) lvHousehold.getItemAtPosition(i);
        SelectedIDItem = Item.get("ID").toString();
        Intent intent = new Intent(getApplicationContext(), HouseholdFormActivity.class);
        intent.putExtra("HouseID", SelectedIDItem);
        startActivity(intent);
    }
}
