package com.example.jujiiz.mis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by JuJiiz on 4/1/2561.
 */

public class myDBClass extends SQLiteOpenHelper {

    private static final String DB_NAME = "MIS";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "HouseholdForm";
    public static final String COL_1 = "InRegistry";
    public static final String COL_2 = "HouseStatus";
    public static final String COL_3 = "HouseNo";
    public static final String COL_4 = "HouseID";
    public static final String COL_5 = "VillName";
    public static final String COL_6 = "LocationLat";
    public static final String COL_7 = "LocationLng";
    public static final String COL_8 = "FamilyType";

    public static final String COL_9 = "Prob1";
    public static final String COL_10 = "Prob2";
    public static final String COL_11 = "Prob3";
    public static final String COL_12 = "Prob4";
    public static final String COL_13 = "Prob5";
    public static final String COL_14 = "Prob6";
    public static final String COL_15 = "Prob7";
    public static final String COL_16 = "Prob8";
    public static final String COL_17 = "Prob9";
    public static final String COL_18 = "Prob10";
    public static final String COL_19 = "AnotherProb";

    public static final String COL_H1 = "HaveEnProb";
    public static final String COL_20 = "EP1";
    public static final String COL_21 = "EP2";
    public static final String COL_22 = "EP3";
    public static final String COL_23 = "EP4";
    public static final String COL_24 = "EP5";
    public static final String COL_25 = "EP6";
    public static final String COL_26 = "EP7";

    public static final String COL_H2 = "HaveDisas";
    public static final String COL_27 = "Dis1";
    public static final String COL_28 = "Dis2";
    public static final String COL_29 = "Dis3";
    public static final String COL_30 = "Dis4";
    public static final String COL_31 = "Dis5";
    public static final String COL_32 = "Dis6";
    public static final String COL_33 = "Dis7";
    public static final String COL_34 = "Dis8";
    public static final String COL_35 = "Dis9";
    public static final String COL_36 = "Dis10";
    public static final String COL_37 = "Dis11";
    public static final String COL_38 = "Dis12";
    public static final String COL_39 = "Dis13";
    public static final String COL_40 = "Dis14";

    public static final String COL_41 = "Image";
    public static final String COL_42 = "Distributor";
    public static final String COL_43 = "Date";

    public myDBClass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +" (HouseholdID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_1 + " INTEGER, "
                + COL_2 + " INTEGER, "
                + COL_3 + " INTEGER, "
                + COL_4 + " INTEGER, "
                + COL_5 + " TEXT, "
                + COL_6 + " DOUBLE, "
                + COL_7 + " DOUBLE, "
                + COL_8 + " INTEGER, "
                + COL_9 + " INTEGER, "
                + COL_10 + " INTEGER, "
                + COL_11 + " INTEGER, "
                + COL_12 + " INTEGER, "
                + COL_13 + " INTEGER, "
                + COL_14 + " INTEGER, "
                + COL_15 + " INTEGER, "
                + COL_16 + " INTEGER, "
                + COL_17 + " INTEGER, "
                + COL_18 + " INTEGER, "
                + COL_19 + " TEXT, "
                + COL_H1 + " INTEGER, "
                + COL_20 + " INTEGER, "
                + COL_21 + " INTEGER, "
                + COL_22 + " INTEGER, "
                + COL_23 + " INTEGER, "
                + COL_24 + " INTEGER, "
                + COL_25 + " INTEGER, "
                + COL_26 + " INTEGER, "
                + COL_H2 + " INTEGER, "
                + COL_27 + " INTEGER, "
                + COL_28 + " INTEGER, "
                + COL_29 + " INTEGER, "
                + COL_30 + " INTEGER, "
                + COL_31 + " INTEGER, "
                + COL_32 + " INTEGER, "
                + COL_33 + " INTEGER, "
                + COL_34 + " INTEGER, "
                + COL_36 + " INTEGER, "
                + COL_37 + " INTEGER, "
                + COL_38 + " INTEGER, "
                + COL_39 + " INTEGER, "
                + COL_40 + " INTEGER, "
                + COL_41 + " BLOB, "
                + COL_42 + " TEXT, "
                + COL_43 + " TEXT);");
        Log.d("MYLOG","Create Table Successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    // Insert Data
    /*public long InsertData(String strMemberID,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strName,
                           String strTel) {
        // TODO Auto-generated method stub
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data

            *//**
             *  for API 11 and above
             SQLiteStatement insertCmd;
             String strSQL = "INSERT INTO " + TABLE_MEMBER
             + "(MemberID,Name,Tel) VALUES (?,?,?)";

             insertCmd = db.compileStatement(strSQL);
             insertCmd.bindString(1, strMemberID);
             insertCmd.bindString(2, strName);
             insertCmd.bindString(3, strTel);
             return insertCmd.executeInsert();
             *//*

            ContentValues Val = new ContentValues();
            Val.put("MemberID", strMemberID);
            Val.put("Name", strName);
            Val.put("Tel", strTel);

            long rows = db.insert(TABLE_NAME, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }
    }*/
}
