package com.example.jujiiz.mis.models;

import android.content.Context;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by JuJiiz on 2/1/2561.
 */

public class ModelCurrentCalendar {
    public static void edittextCurrentCalendar(Context context, EditText editText) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy ");
        editText.setText(mdformat.format(calendar.getTime()));
    }
}
