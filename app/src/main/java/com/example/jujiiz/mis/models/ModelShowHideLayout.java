package com.example.jujiiz.mis.models;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * Created by JuJiiz on 28/12/2560.
 */

public class ModelShowHideLayout {

    public static void checkboxShowHide(CheckBox checkBox, LinearLayout linearLayout){
        if(checkBox.isChecked()==true){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.GONE);
        }
    }

    public static void radiobuttonShowHide(RadioButton radioButton, LinearLayout linearLayout){
        if(radioButton.isChecked()==true){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.GONE);
        }
    }

    public static void radiobuttonEnable(RadioButton radioButton, Spinner spinner1, Spinner spinner2){
        if(radioButton.isChecked()==true){
            spinner1.setEnabled(true);
            spinner2.setEnabled(false);
        }else{
            spinner1.setEnabled(false);
            spinner2.setEnabled(true);
        }
    }
}
