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
        calendar.set(calendar.get(Calendar.YEAR) + 543, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        editText.setText(mdformat.format(calendar.getTime()));
    }

    public static int getCurrentYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR) + 543;
        return year;
    }
}
