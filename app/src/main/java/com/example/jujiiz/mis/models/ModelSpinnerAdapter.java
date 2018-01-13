package com.example.jujiiz.mis.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jujiiz.mis.R;

/**
 * Created by JuJiiz on 2/1/2561.
 */

public class ModelSpinnerAdapter{

    public static ArrayAdapter<String> setSpinnerItem(Context context, String[] stringArray, Spinner spinner){
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (context, android.R.layout.simple_spinner_item,
                        stringArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        return spinnerArrayAdapter;
    }
}
