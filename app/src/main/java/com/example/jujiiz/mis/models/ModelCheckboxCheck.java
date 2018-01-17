package com.example.jujiiz.mis.models;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    public static void enviProb(CheckBox checkBox, RadioGroup radioGroup,int ep){
        if (ep == 0){
            checkBox.setChecked(false);
        }
        if (ep == 1){
            checkBox.setChecked(true);
            ((RadioButton) radioGroup.getChildAt(2)).setChecked(true);
        }
        if (ep == 2){
            checkBox.setChecked(true);
            ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
        }
        if (ep == 3){
            checkBox.setChecked(true);
            ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        }
    }
}
