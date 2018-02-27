package com.example.jujiiz.mis.models;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.controllers.VehicalFormActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by JuJiiz on 23/2/2561.
 */

public class CustomDialog {

    public static void DatePickerDialog(Context context, final EditText editText) {
        final String[] date28 = new String[28],
                date29 = new String[29],
                date31 = new String[31],
                date30 = new String[30],
                month = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"},
                year = new String[262];
        int indexYear = 0;

        final Dialog d = new Dialog(context);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.custom_datepicker);
        Button btnSetDate = (Button) d.findViewById(R.id.btnSetDate);
        final NumberPicker Day_picker = (NumberPicker) d.findViewById(R.id.Day_picker);
        final NumberPicker Month_picker = (NumberPicker) d.findViewById(R.id.Month_picker);
        final NumberPicker Year_picker = (NumberPicker) d.findViewById(R.id.Year_picker);

        for (int i = 0; i < 31; i++) {
            date31[i] = (i + 1) + "";
        }

        for (int i = 0; i < 30; i++) {
            date30[i] = (i + 1) + "";
        }

        for (int i = 0; i < 29; i++) {
            date29[i] = (i + 1) + "";
        }

        for (int i = 0; i < 28; i++) {
            date28[i] = (i + 1) + "";
        }

        for (int i = ModelCurrentCalendar.getCurrentYear() - 261; i <= ModelCurrentCalendar.getCurrentYear(); i++) {
            year[indexYear] = i + "";
            indexYear++;
        }

        Year_picker.setMinValue(0);
        Year_picker.setMaxValue(year.length - 1);
        Year_picker.setDisplayedValues(year);

        Month_picker.setMinValue(0);
        Month_picker.setMaxValue(month.length - 1);
        Month_picker.setDisplayedValues(month);

        Day_picker.setMinValue(0);
        Day_picker.setMaxValue(date31.length - 1);
        Day_picker.setDisplayedValues(date31);

        Day_picker.setValue(0);
        Month_picker.setValue(0);
        Year_picker.setValue(year.length - 1);

        Month_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int strYear = Integer.parseInt(Year_picker.getDisplayedValues()[Year_picker.getValue()]) - 543;
                Log.d("MYLOG", "onValueChange1: " + strYear);

                if (Month_picker.getValue() == 0 ||
                        Month_picker.getValue() == 2 ||
                        Month_picker.getValue() == 4 ||
                        Month_picker.getValue() == 6 ||
                        Month_picker.getValue() == 7 ||
                        Month_picker.getValue() == 9 ||
                        Month_picker.getValue() == 11) {

                    Day_picker.setDisplayedValues(null);
                    Day_picker.setValue(0);
                    Day_picker.setMinValue(0);
                    Day_picker.setMaxValue(date31.length - 1);
                    Day_picker.setDisplayedValues(date31);
                }

                if (Month_picker.getValue() == 3 ||
                        Month_picker.getValue() == 5 ||
                        Month_picker.getValue() == 8 ||
                        Month_picker.getValue() == 10) {
                    Day_picker.setDisplayedValues(null);
                    Day_picker.setValue(0);
                    Day_picker.setMinValue(0);
                    Day_picker.setMaxValue(date30.length - 1);
                    Day_picker.setDisplayedValues(date30);
                }

                if (Month_picker.getValue() == 1) {
                    if ((strYear % 400 == 0) || ((strYear % 4 == 0) && (strYear % 100 != 0))) {
                        Day_picker.setDisplayedValues(null);
                        Day_picker.setValue(0);
                        Day_picker.setMinValue(0);
                        Day_picker.setMaxValue(date29.length - 1);
                        Day_picker.setDisplayedValues(date29);
                    } else {
                        Day_picker.setDisplayedValues(null);
                        Day_picker.setValue(0);
                        Day_picker.setMinValue(0);
                        Day_picker.setMaxValue(date28.length - 1);
                        Day_picker.setDisplayedValues(date28);
                    }
                }
            }
        });

        Year_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int strYear = Integer.parseInt(Year_picker.getDisplayedValues()[Year_picker.getValue()]) - 543;
                Log.d("MYLOG", "onValueChange2: " + strYear);

                if ((strYear % 400 == 0) || ((strYear % 4 == 0) && (strYear % 100 != 0))) {
                    if (Month_picker.getValue() == 1) {
                        Day_picker.setDisplayedValues(null);
                        Day_picker.setValue(0);
                        Day_picker.setMinValue(0);
                        Day_picker.setMaxValue(date29.length - 1);
                        Day_picker.setDisplayedValues(date29);
                    }
                }
            }
        });

        btnSetDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DecimalFormat decimalFormat = new DecimalFormat("00");
                String strDate = decimalFormat.format(Day_picker.getValue() + 1);
                String strMonth = decimalFormat.format(Month_picker.getValue() + 1);
                String strYear = Year_picker.getDisplayedValues()[Year_picker.getValue()];
                //Log.d("MYLOG", strDate + "/" + strMonth + "/" + strYear);
                editText.setText(String.valueOf(strDate + "/" + strMonth + "/" + strYear));
                d.dismiss();
            }
        });
        d.show();
    }

    public static void ListViewDialog(final Context context, Spinner spinner, String[] strings, ArrayAdapter<String> arrayAdapter) {

        final Dialog d = new Dialog(context);
        d.setTitle("Search");
        d.setContentView(R.layout.spinner_search_dialog);
        EditText etListSearch = d.findViewById(R.id.etListSearch);
        ListView lvList = d.findViewById(R.id.lvList);
        ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(context, R.layout.spinner_search_dialog, R.id.lvList, strings);
        lvList.setAdapter(adapter);

        etListSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
            }
        });

        d.show();
    }
}
