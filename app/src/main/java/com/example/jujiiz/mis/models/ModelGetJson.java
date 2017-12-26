package com.example.jujiiz.mis.models;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JuJiiz on 20/7/2560.
 */

public class ModelGetJson {

    public static ArrayList getHouseholdListJson(Context context, JSONArray jsonArray, String spSearch, String strSearch, ListView listView) {
        String FIRST_COLUMN = "ville_no";
        String SECOND_COLUMN = "house_no";
        String THIRD_COLUMN = "ville_name";
        String FOURTH_COLUMN = "status";
        String HIDDEN_COLUMN = "task";

        ArrayList<String> VILLAGE_LIST;
        VILLAGE_LIST = new ArrayList<String>();
        String villeItem;

        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        /*int pPage = 1;
        int pageIndex = 0;
        int maxItem = 10;*/
        try {
            //String strJsonObj = ModelGetData.getJsonArray(context, apiURL, pKey);
            Log.d("MYLOG", "jsonArray.length(): " + jsonArray.length());
            /*if (pPage < 1) {
                pageIndex = 0;
            } else if (pPage > Math.ceil(jsonArray.length() / (1.0 * maxItem))) {
                pageIndex = (int) Math.ceil(jsonArray.length() / (1.0 * maxItem)) - 1;
            } else {
                pageIndex = pPage - 1;
            }*/
            //for (int i = pageIndex * maxItem; i < jsonArray.length() && i < pageIndex * maxItem + maxItem; i++) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();

                String CO1 = "X";
                String CO2 = jsonObj.getString("H_NO");
                JSONObject tmpJsonObj = new JSONObject(jsonObj.getString("zone"));
                String CO3 = tmpJsonObj.getString("VIL_NAME");
                villeItem = tmpJsonObj.getString("VIL_NAME");
                tmpJsonObj = new JSONObject(jsonObj.getString("SURV_Status"));
                String CO4 = tmpJsonObj.getString("status");
                String COH = tmpJsonObj.getString("task_Id");

                if (spSearch.equals("") && strSearch.equals("")) {
                    temp.put(FIRST_COLUMN, CO1);
                    temp.put(SECOND_COLUMN, CO2);
                    temp.put(THIRD_COLUMN, CO3);
                    temp.put(FOURTH_COLUMN, CO4);
                    temp.put(HIDDEN_COLUMN, COH);
                } if (!spSearch.equals("") && strSearch.equals("")) {
                    if (CO3.contains(spSearch)) {
                        temp.put(FIRST_COLUMN, CO1);
                        temp.put(SECOND_COLUMN, CO2);
                        temp.put(THIRD_COLUMN, CO3);
                        temp.put(FOURTH_COLUMN, CO4);
                        temp.put(HIDDEN_COLUMN, COH);
                    }
                } if (spSearch.equals("") && !strSearch.equals("")) {
                    if (CO2.contains(strSearch)) {
                        temp.put(FIRST_COLUMN, CO1);
                        temp.put(SECOND_COLUMN, CO2);
                        temp.put(THIRD_COLUMN, CO3);
                        temp.put(FOURTH_COLUMN, CO4);
                        temp.put(HIDDEN_COLUMN, COH);
                    }
                } if (!spSearch.equals("") && !strSearch.equals("")) {
                    if (CO3.contains(spSearch) && CO2.contains(strSearch)) {
                        temp.put(FIRST_COLUMN, CO1);
                        temp.put(SECOND_COLUMN, CO2);
                        temp.put(THIRD_COLUMN, CO3);
                        temp.put(FOURTH_COLUMN, CO4);
                        temp.put(HIDDEN_COLUMN, COH);
                    }
                }

                /*if (tmpJsonObj.has("task_Id")) {
                    temp.put(HIDDEN_COLUMN, tmpJsonObj.getString("task_Id"));
                } else {
                    temp.put(HIDDEN_COLUMN, "");
                }*/

                Log.d("MYLOG", "task_Id: " + tmpJsonObj.getString("task_Id"));
                LIST.add(temp);

                //Spinner
                if (!VILLAGE_LIST.contains(villeItem))
                    VILLAGE_LIST.add(villeItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setHouseholdAdapter(context, LIST, FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, FOURTH_COLUMN, HIDDEN_COLUMN, listView);
        return VILLAGE_LIST;
    }

    public static void pagination() {

    }

    /*public static void getHeadJson(Context context, String apiURL, String pKey, ListView listView) {
        String HEAD_COLUMN_NUMBER = "number";
        String HEAD_OWNER_NAME_COLUMN = "name";
        String HEAD_STATUS_COLUMN = "status";
        String status_count = "count";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getJsonArray(context, apiURL, pKey);
            JSONArray jsonArray = new JSONArray(strJsonObj);
            for (int i = 0; i < jsonArray.length(); i++) {
                int statusCount = 0, progress = 0;
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(HEAD_COLUMN_NUMBER, jsonObj.getString("ID"));
                temp.put(HEAD_OWNER_NAME_COLUMN, jsonObj.getString("owner"));
                temp.put(HEAD_STATUS_COLUMN, jsonObj.getString("status"));
                JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
                if (tmpJsonArray.length() != 0) {
                    for (int j = 0; j < tmpJsonArray.length(); j++) {
                        JSONObject jsonObjcheck = tmpJsonArray.getJSONObject(j);
                        status_count = jsonObjcheck.getString("status");
                        if (!status_count.equals("wait")) {
                            statusCount += 1;
                        }
                    }
                    progress = (statusCount * 100) / tmpJsonArray.length();
                }
                temp.put(HEAD_STATUS_COLUMN, progress + "%");
                LIST.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setHeadAdapter(context, LIST, HEAD_COLUMN_NUMBER, HEAD_OWNER_NAME_COLUMN, HEAD_STATUS_COLUMN, listView);
    }

    public static void getDialogJson(Context context, String apiURL, String pKey, int Position, ListView listView) {
        String TASK = "task";
        String STATUS = "status";
        String ID = "id";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getJsonArray(context, apiURL, pKey);
            JSONArray jsonArray = new JSONArray(strJsonObj);
            JSONObject jsonObj = jsonArray.getJSONObject(Position);
            temp.put(TASK, jsonObj.getString("task"));
            temp.put(STATUS, jsonObj.getString("status"));
            temp.put(ID, jsonObj.getString("_id"));
            LIST.add(temp);

            JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
            if (tmpJsonArray.length() != 0) {
                for (int j = 0; j < tmpJsonArray.length(); j++) {
                    temp = new HashMap<String, String>();
                    JSONObject jsonObjChild = tmpJsonArray.getJSONObject(j);
                    temp.put(TASK, jsonObjChild.getString("task"));
                    temp.put(STATUS, jsonObjChild.getString("status"));
                    temp.put(ID, jsonObjChild.getString("_id"));
                    Log.d("xxxxxxxxxxxxxxxxxx", temp.get(ID));
                    LIST.add(temp);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setChildAdapter(context, LIST, TASK, STATUS, ID, listView);
    }*/

    /*public static int getHouseholdChildJson(Context context, String strJsonObj, int pPage, ListView listView) {
        String HEAD_COLUMN_IDNUMBER = "idnumber";
        String HEAD_OWNER_NAME_COLUMN = "name";
        String HEAD_STATUS_COLUMN = "status";
        String status_count = "count";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        int pageIndex = 0;
        int maxItem = 10;
        try {
            //String strJsonObj = ModelGetData.getHouseholdJsonArray(context, apiURL, pKey, taskID);
            JSONArray jsonArray = new JSONArray(strJsonObj);
            if (pPage < 1) {
                pageIndex = 0;
            } else if (pPage > Math.ceil(jsonArray.length() / (1.0 * maxItem))) {
                pageIndex = (int) Math.ceil(jsonArray.length() / (1.0 * maxItem)) - 1;
            } else {
                pageIndex = pPage - 1;
            }
            for (int i = pageIndex * maxItem; i < jsonArray.length() && i < pageIndex * maxItem + maxItem; i++) {
                int statusCount = 0, progress = 0;
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put(HEAD_COLUMN_IDNUMBER, jsonObj.getString("ID"));
                temp.put(HEAD_OWNER_NAME_COLUMN, jsonObj.getString("Owner"));
                temp.put(HEAD_STATUS_COLUMN, jsonObj.getString("status"));

                JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
                if (tmpJsonArray.length() != 0) {
                    for (int j = 0; j < tmpJsonArray.length(); j++) {
                        JSONObject jsonObjcheck = tmpJsonArray.getJSONObject(j);
                        status_count = jsonObjcheck.getString("status");
                        if (!status_count.equals("wait")) {
                            statusCount += 1;
                        }
                    }
                    progress = (statusCount * 100) / tmpJsonArray.length();
                }
                temp.put(HEAD_STATUS_COLUMN, progress + "%");
                LIST.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setHeadAdapter(context, LIST, HEAD_COLUMN_IDNUMBER, HEAD_OWNER_NAME_COLUMN, HEAD_STATUS_COLUMN, listView);
        return pageIndex;
    }

    public static void getHouseholdChildDialogJson(Context context, String apiURL, String pKey, String taskID, int Position, ListView listView) {
        String TASK = "task";
        String STATUS = "status";
        String transTASK, transSTATUS;
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> temp = new HashMap<String, String>();
        JSONObject jsonObject = null;
        try {
            String strJsonObj = ModelGetData.getHouseholdJsonArray(context, apiURL, pKey, taskID);
            JSONArray jsonArray = new JSONArray(strJsonObj);
            JSONObject jsonObj = jsonArray.getJSONObject(Position);
            temp.put(TASK, jsonObj.getString("task"));
            temp.put(STATUS, jsonObj.getString("status"));

            LIST.add(temp);

            JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
            if (tmpJsonArray.length() != 0) {
                for (int j = 0; j < tmpJsonArray.length(); j++) {
                    temp = new HashMap<String, String>();
                    JSONObject jsonObjChild = tmpJsonArray.getJSONObject(j);
                    temp.put(TASK, jsonObjChild.getString("task"));
                    temp.put(STATUS, jsonObjChild.getString("status"));
                    LIST.add(temp);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setChildAdapter(context, LIST, TASK, STATUS, taskID, listView);
    }*/

    /*public static int getSearchHouseholdHead(Context context, String strJsonObj, String strSearch, int pPage, ListView listView) {
        String CHILD_COLUMN_NUMBER = "number";
        String CHILD_OWNER_NAME_COLUMN = "name";
        String CHILD_STATUS_COLUMN = "status";
        String CHILD_TASKID_COLUMN = "taskid";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        int pageIndex = 0;
        int maxItem = 10;
        try {
            JSONArray jsonArray = new JSONArray(strJsonObj);
            if (pPage < 1) {
                pageIndex = 0;
            } else if (pPage > Math.ceil(jsonArray.length() / (1.0 * maxItem))) {
                pageIndex = (int) Math.ceil(jsonArray.length() / (1.0 * maxItem)) - 1;
            } else {
                pageIndex = pPage - 1;
            }
            for (int i = pageIndex * maxItem; i < jsonArray.length() && i < pageIndex * maxItem + maxItem; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String HNo = jsonObj.getString("H_NO");
                JSONObject tmpJsonObj = new JSONObject(jsonObj.getString("zone"));
                String VilName = tmpJsonObj.getString("VIL_NAME");
                tmpJsonObj = new JSONObject(jsonObj.getString("SURV_Status"));
                String Status = tmpJsonObj.getString("status");

                if (HNo.indexOf(strSearch) >= 0 || VilName.indexOf(strSearch) >= 0) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put(CHILD_COLUMN_NUMBER, HNo);
                    temp.put(CHILD_OWNER_NAME_COLUMN, VilName);
                    temp.put(CHILD_STATUS_COLUMN, Status);
                    if (tmpJsonObj.has("task_Id")) {
                        temp.put(CHILD_TASKID_COLUMN, tmpJsonObj.getString("task_Id"));
                    } else {
                        temp.put(CHILD_TASKID_COLUMN, "");
                    }
                    LIST.add(temp);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setHouseholdAdapter(context, LIST, CHILD_COLUMN_NUMBER, CHILD_OWNER_NAME_COLUMN, CHILD_STATUS_COLUMN, CHILD_TASKID_COLUMN, listView);
        return pageIndex;
    }

    public static int getSearchHouseholdChild(Context context, String strJsonObj, String strSearch, int pPage, ListView listView) {
        String CHILD_COLUMN_NUMBER = "number";
        String CHILD_OWNER_NAME_COLUMN = "name";
        String CHILD_STATUS_COLUMN = "status";
        String CHILD_TASKID_COLUMN = "taskid";
        String status_count = "count";
        ArrayList<HashMap<String, String>> LIST;
        LIST = new ArrayList<HashMap<String, String>>();
        int pageIndex = 0;
        int maxItem = 10;
        try {
            JSONArray jsonArray = new JSONArray(strJsonObj);
            if (pPage < 1) {
                pageIndex = 0;
            } else if (pPage > Math.ceil(jsonArray.length() / (1.0 * maxItem))) {
                pageIndex = (int) Math.ceil(jsonArray.length() / (1.0 * maxItem)) - 1;
            } else {
                pageIndex = pPage - 1;
            }
            for (int i = pageIndex * maxItem; i < jsonArray.length() && i < pageIndex * maxItem + maxItem; i++) {
                int statusCount = 0, progress = 0;
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String Pid = jsonObj.getString("ID");
                String Oname = jsonObj.getString("Owner");
                String Status = jsonObj.getString("status");

                JSONArray tmpJsonArray = new JSONArray(jsonObj.getString("asset"));
                if (Pid.indexOf(strSearch) >= 0 || Oname.indexOf(strSearch) >= 0) {
                    HashMap<String, String> temp = new HashMap<String, String>();
                    temp.put(CHILD_COLUMN_NUMBER, Pid);
                    temp.put(CHILD_OWNER_NAME_COLUMN, Oname);
                    temp.put(CHILD_STATUS_COLUMN, Status);
                    for (int j = 0; j < tmpJsonArray.length(); j++) {
                        JSONObject jsonObjcheck = tmpJsonArray.getJSONObject(j);
                        status_count = jsonObjcheck.getString("status");
                        if (!status_count.equals("wait")) {
                            statusCount += 1;
                        }
                    }
                    temp.put(CHILD_TASKID_COLUMN, progress + "%");
                    LIST.add(temp);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ModelSetAdapterColumn.setHouseholdAdapter(context, LIST, CHILD_OWNER_NAME_COLUMN, CHILD_COLUMN_NUMBER, CHILD_STATUS_COLUMN, CHILD_TASKID_COLUMN, listView);
        return pageIndex;
    }*/


}
