package com.example.jujiiz.mis.models;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.jujiiz.mis.controllers.HouseholdActivity;
import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.controllers.OPTActivity;
import com.example.jujiiz.mis.controllers.OPTFormActivity;
import com.example.jujiiz.mis.controllers.TR14Activity;
import com.example.jujiiz.mis.controllers.UploadActivity;

/**
 * Created by JuJiiz on 21/12/2560.
 */

public class ModelNavClick {

    public static void displaySelectedScreen(Context context, int id) {
        Intent intent = null;
        switch (id) {
            case R.id.nav_Household:
                if (context instanceof HouseholdActivity) {
                } else {
                    intent = new Intent(context, HouseholdActivity.class);
                    context.startActivity(intent);
                }

                break;

            case R.id.nav_Upload:
                if (context instanceof UploadActivity) {
                } else {
                    //Toast.makeText(context,"ปิดปรับปรุง",Toast.LENGTH_SHORT).show();
                    intent = new Intent(context, UploadActivity.class);
                    context.startActivity(intent);
                }

                break;

            /*case R.id.nav_OPT:
                Toast.makeText(context,"ปิดปรับปรุง",Toast.LENGTH_SHORT).show();
                *//*if (context instanceof HouseholdActivity) {
                    Toast.makeText(context, context.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(context, OPTActivity.class);
                    context.startActivity(intent);
                }*//*
                break;

            case R.id.nav_TR14:
                Toast.makeText(context,"ปิดปรับปรุง",Toast.LENGTH_SHORT).show();
                *//*if (context instanceof TR14Activity) {
                    //Toast.makeText(context,context.toString(),Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(context, TR14Activity.class);
                    context.startActivity(intent);
                }*//*
                break;*/
        }
    }
}
