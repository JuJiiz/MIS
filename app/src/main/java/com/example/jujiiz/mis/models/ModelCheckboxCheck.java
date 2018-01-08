package com.example.jujiiz.mis.models;

import android.content.Context;
import android.widget.CheckBox;

/**
 * Created by JuJiiz on 8/1/2561.
 */

public class ModelCheckboxCheck {
    public static void checkboxSetCheck(Context context, CheckBox checkBox, int value){
        if(value == 0){
            checkBox.setChecked(false);
        }if(value == 1){
            checkBox.setChecked(true);
        }
    }

    public static int checkboxReturnCheck(CheckBox checkBox){
        int value=0;
        if (checkBox.isChecked()){
            value = 1;
        }
        if (!checkBox.isChecked()){
            value = 0;
        }
        return value;
    }
}
