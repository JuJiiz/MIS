package com.example.jujiiz.mis.models;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Created by JuJiiz on 9/2/2561.
 */

public class ModelCheckForm {
    public static Boolean checkRadioGroup(RadioGroup radioGroup){
        Boolean pass = true;
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            pass = false;
        }
        return pass;
    }

    public static Boolean checkEditText(EditText editText){
        Boolean pass = true;
        if (editText.getText().toString().equals("")) {
            pass = false;
        }
        return pass;
    }

    public static Boolean checkSpinner(Spinner spinner){
        Boolean pass = true;
        if (spinner.getSelectedItem().toString().equals("กรุณาเลือก")) {
            pass = false;
        }
        return pass;
    }
}
