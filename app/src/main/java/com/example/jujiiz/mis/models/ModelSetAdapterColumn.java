package com.example.jujiiz.mis.models;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.controllers.HouseholdActivity;

import java.util.ArrayList;


/**
 * Created by JuJiiz on 20/7/2560.
 */

public class ModelSetAdapterColumn {

    public static void setHouseholdAdapter(Context context, ArrayList LIST, String FIRST_COLUMN, String SECOND_COLUMN, String THIRD_COLUMN, String FOURTH_COLUMN, String HIDDEN_COLUMN, ListView listView) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, LIST, R.layout.view_household_column,
                new String[]{FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, FOURTH_COLUMN, HIDDEN_COLUMN},
                new int[]{R.id.tvColumn1, R.id.tvColumn2, R.id.tvColumn3, R.id.tvColumn4, R.id.tvHiddenColumn}
        );
        listView.setAdapter(simpleAdapter);

    }

    /*public static void setHeadAdapter(Context context, ArrayList LIST, String COLUMN_NUMBER, String OWNER_NAME_COLUMN, String STATUS_COLUMN, ListView listView) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, LIST, R.layout.view_head_column,
                new String[]{COLUMN_NUMBER, OWNER_NAME_COLUMN, STATUS_COLUMN},
                new int[]{R.id.tvNumberHead, R.id.tvOwnerNameHead, R.id.tvProgressHead}
        );
        listView.setAdapter(simpleAdapter);
    }

    public static void setChildAdapter(Context context, ArrayList LIST, String TASK, String STATUS, String taskID, ListView listView) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, LIST, R.layout.view_child_column,
                new String.[]{TASK, STATUS, taskID},
                new int[]{R.id.tvTaskColumn, R.id.tvStatusColumn, R.id.tvTaskID}
        );
        listView.setAdapter(simpleAdapter);
    }*/
}
