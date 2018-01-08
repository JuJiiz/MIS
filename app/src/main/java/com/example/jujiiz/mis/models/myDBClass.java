package com.example.jujiiz.mis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JuJiiz on 4/1/2561.
 */

public class myDBClass extends SQLiteOpenHelper {

    private static final String DB_NAME = "MIS";
    private static final int DB_VERSION = 1;

    public myDBClass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house` (`house_id`\tINTEGER NOT NULL,`house_no`\tINTEGER NOT NULL,`vilage_id`\tINTEGER NOT NULL,`house_location_lat`\tTEXT NOT NULL,`house_location_lng`\tTEXT NOT NULL,`house_in_registry`\tTEXT NOT NULL,`house_status`\tTEXT NOT NULL,`house_family_type`\tTEXT NOT NULL,`distributor_img`\tTEXT NOT NULL,`distributor`\tTEXT NOT NULL,`survey_status`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT NOT NULL,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_disaster` (`disaster_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`disaster_type`\tTEXT NOT NULL,`dis1`\tTEXT NOT NULL,`dis2`\tTEXT NOT NULL,`dis3`\tTEXT NOT NULL,`dis4`\tTEXT NOT NULL,`dis5`\tTEXT NOT NULL,`dis6`\tTEXT NOT NULL,`dis7`\tTEXT NOT NULL,`dis8`\tTEXT NOT NULL,`dis9`\tTEXT NOT NULL,`dis10`\tTEXT NOT NULL,`dis11`\tTEXT NOT NULL,`dis12`\tTEXT NOT NULL,`dis13`\tTEXT NOT NULL,`dis14`\tTEXT NOT NULL,`house_id`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_evyprob` (`evyprob_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`evyprob_type`\tTEXT NOT NULL,`ep1`\tTEXT NOT NULL,`ep2`\tTEXT NOT NULL,`ep3`\tTEXT NOT NULL,`ep4`\tTEXT,`ep5`\tTEXT NOT NULL,`ep6`\tTEXT NOT NULL,`ep7`\tTEXT NOT NULL,`house_id`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_problem` (`problem_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`prob1`\tTEXT NOT NULL,`prob2`\tTEXT NOT NULL,`prob3`\tTEXT NOT NULL,`prob4`\tTEXT NOT NULL,`prob5`\tTEXT NOT NULL,`prob6`\tTEXT NOT NULL,`prob7`\tTEXT NOT NULL,`prob8`\tTEXT NOT NULL,`prob9`\tTEXT NOT NULL,`prob10`\tTEXT NOT NULL,`problem_another`\tTEXT NOT NULL,`house_id`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tINTEGER,`ACTIVE`\tINTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `members` (`members_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`members_un`\tTEXT NOT NULL,`members_pw`\tTEXT NOT NULL,`members_status`\tTEXT NOT NULL,`vilage_id`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `nationality` (`nationality_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`nationality_detail`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `opt` (`opt_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`opt_name`\tTEXT NOT NULL,`opt_type_id`\tINTEGER NOT NULL,`opt_location_lat`\tTEXT NOT NULL,`opt_location_lng`\tTEXT NOT NULL,`opt_address_no`\tINTEGER NOT NULL,`opt_vilage_no`\tINTEGER NOT NULL,`opt_alley`\tTEXT NOT NULL,`opt_road`\tTEXT NOT NULL,`opt_province`\tTEXT NOT NULL,`opt_district`\tTEXT NOT NULL,`opt_sub_district`\tTEXT NOT NULL,`opt_postal_code`\tINTEGER NOT NULL,`opt_tel`\tINTEGER NOT NULL,`opt_fax`\tINTEGER NOT NULL,`opt_vision`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `opt_type` (`opt_type_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`opt_type_name`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population` (`population_idcard`\tINTEGER NOT NULL,`prename_id`\tINTEGER NOT NULL,`firstname`\tTEXT NOT NULL,`lastname`\tTEXT NOT NULL,`birthdate`\tTEXT NOT NULL,`height`\tINTEGER NOT NULL,`weight`\tINTEGER NOT NULL,`sex`\tTEXT NOT NULL,`bloodgroup`\tTEXT NOT NULL,`living`\tTEXT NOT NULL,`maritalstatus`\tTEXT NOT NULL,`tel`\tINTEGER NOT NULL,`nationality_id`\tINTEGER NOT NULL,`house_id`\tINTEGER NOT NULL,`currentaddr`\tTEXT NOT NULL,`currentaddr_province`\tTEXT NOT NULL,`currentaddr_country`\tTEXT NOT NULL,`dwellerstatus`\tTEXT NOT NULL,`income`\tTEXT NOT NULL,`income_money`\tINTEGER NOT NULL,`dept`\tTEXT NOT NULL,`saving`\tTEXT NOT NULL,`allergichis`\tTEXT NOT NULL,`allergichis_detail`\tTEXT NOT NULL,`disadvantage`\tTEXT NOT NULL,`sub_al`\tTEXT NOT NULL,`education`\tTEXT NOT NULL,`education_class`\tTEXT NOT NULL,`literacy`\tTEXT NOT NULL,`technology`\tTEXT NOT NULL,`expertise`\tTEXT NOT NULL,`expertise_name`\tTEXT NOT NULL,`expertise_detail`\tTEXT NOT NULL,`religion`\tTEXT NOT NULL,`religion_another`\tTEXT NOT NULL,`participation`\tTEXT NOT NULL,`election`\tTEXT NOT NULL,`residence_status`\tTEXT NOT NULL,`latentpop_province`\tTEXT NOT NULL,`latentpop_country`\tTEXT NOT NULL,`distributor`\tTEXT NOT NULL,`survey_status`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_congenitalhis` (`congh_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`congh_type`\tTEXT NOT NULL,`congh1`\tTEXT NOT NULL,`congh2`\tTEXT NOT NULL,`congh3`\tTEXT NOT NULL,`congh4`\tTEXT NOT NULL,`congh5`\tTEXT NOT NULL,`congh_another`\tTEXT NOT NULL,`population_idcard`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_contagioushis` (`conth_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`conth_type`\tTEXT NOT NULL,`conth1`\tTEXT NOT NULL,`conth2`\tTEXT NOT NULL,`conth3`\tTEXT NOT NULL,`conth4`\tTEXT NOT NULL,`conth5`\tTEXT NOT NULL,`conth6`\tTEXT NOT NULL,`conth7`\tTEXT NOT NULL,`conth8`\tTEXT NOT NULL,`conth9`\tTEXT NOT NULL,`conth10`\tTEXT NOT NULL,`conth11`\tTEXT NOT NULL,`conth_another`\tTEXT NOT NULL,`population_idcard`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_disabled` (`disabled_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`disabled_type`\tTEXT NOT NULL,`disabled1`\tTEXT NOT NULL,`disabled2`\tTEXT NOT NULL,`disabled3`\tTEXT NOT NULL,`disabled4`\tTEXT NOT NULL,`disabled5`\tTEXT NOT NULL,`disabled6`\tTEXT NOT NULL,`population_idcard`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_agriculture` (`agri_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`works_id`\tINTEGER NOT NULL,`agri1`\tTEXT NOT NULL,`agri2`\tTEXT NOT NULL,`agri3`\tTEXT NOT NULL,`agri4`\tTEXT NOT NULL,`agri5`\tTEXT NOT NULL,`agri6`\tTEXT NOT NULL,`agri7`\tTEXT NOT NULL,`agri8`\tTEXT NOT NULL,`agri_another`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_animal` (`animal_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`works_id`\tINTEGER NOT NULL,`animal1`\tTEXT NOT NULL,`animal2`\tTEXT NOT NULL,`animal3`\tTEXT NOT NULL,`animal4`\tTEXT NOT NULL,`animal5`\tTEXT NOT NULL,`animal6`\tTEXT NOT NULL,`animal7`\tTEXT NOT NULL,`animal8`\tTEXT NOT NULL,`animal9`\tTEXT NOT NULL,`animal_another`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_govern` (`govern_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`works_id`\tINTEGER NOT NULL,`govern1`\tTEXT NOT NULL,`govern2`\tTEXT NOT NULL,`govern3`\tTEXT NOT NULL,`govern4`\tTEXT NOT NULL,`govern_another`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_private` (`private_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`works_id`\tINTEGER NOT NULL,`private1`\tTEXT NOT NULL,`private2`\tTEXT NOT NULL,`private3`\tTEXT NOT NULL,`private4`\tTEXT NOT NULL,`private5`\tTEXT NOT NULL,`private6`\tTEXT NOT NULL,`private7`\tTEXT NOT NULL,`private_another`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_land` (`land_id`\tINTEGER NOT NULL,`population_idcard`\tINTEGER NOT NULL,`dimen1`\tINTEGER NOT NULL,`dimen2`\tINTEGER NOT NULL,`dimen3`\tINTEGER NOT NULL,`land_benefit`\tTEXT NOT NULL,`land_location`\tTEXT NOT NULL,`land_tax`\tTEXT NOT NULL,`land_rent`\tTEXT NOT NULL,`distributor`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_transport` (`transport_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`transport_type`\tTEXT NOT NULL,`population_idcard`\tINTEGER NOT NULL,`trans1`\tTEXT NOT NULL,`trans2`\tTEXT NOT NULL,`trans3`\tTEXT NOT NULL,`trans4`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_vehicle` (`vehicle_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`regisdate`\tTEXT NOT NULL,`vehicle_type`\tTEXT NOT NULL,`vehicle_detail`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_works` (`works_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`works_type`\tTEXT NOT NULL,`population_idcard`\tINTEGER NOT NULL,`cr_by`\tINTEGER NOT NULL,`cr_date`\tINTEGER NOT NULL,`upd_by`\tINTEGER,`upd_date`\tINTEGER,`ACTIVE`\tINTEGER NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `prename` (`prename_id`\tINTEGER NOT NULL,`prename_detail`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `vilage` (`vilage_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`opt_id`\tINTEGER NOT NULL,`vilage_name`\tTEXT NOT NULL,`vilage_location_lat`\tTEXT NOT NULL,`vilage_location_lng`\tTEXT NOT NULL,`vilage_aor`\tINTEGER NOT NULL,`vilage_liveable`\tTEXT NOT NULL,`vilage_start`\tTEXT NOT NULL,`vilage_history`\tTEXT NOT NULL,`vilage_problem`\tTEXT NOT NULL,`vilage_sup_firstname`\tTEXT NOT NULL,`vilage_sup_lastname`\tTEXT NOT NULL,`vilage_sup_startdate`\tTEXT NOT NULL,`vilage_address_no`\tINTEGER NOT NULL,`vilage_no`\tINTEGER NOT NULL,`vilage_alley`\tTEXT NOT NULL,`vilage_road`\tTEXT NOT NULL,`vilage_province`\tTEXT NOT NULL,`vilage_district`\tTEXT NOT NULL,`vilage_sub_district`\tTEXT NOT NULL,`vilage_postal_code`\tINTEGER NOT NULL,`vilage_tel`\tINTEGER NOT NULL,`vilage_img`\tTEXT NOT NULL,`vilage_informant_firstname`\tTEXT NOT NULL,`vilage_informant_lastname`\tTEXT NOT NULL,`vilage_informant_tel`\tINTEGER NOT NULL,`survey_status`\tTEXT NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `vilage_area` (`area_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`area_river`\tTEXT NOT NULL,`area_plateau`\tTEXT NOT NULL,`area_mountain`\tTEXT NOT NULL,`area_coastal`\tTEXT NOT NULL,`vilage_id`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `vilage_slum` (`slum_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`slum_status`\tTEXT NOT NULL,`slum_address`\tTEXT NOT NULL,`vilage_id`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `vilage_soil` (`soil_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`soil_bog`\tTEXT NOT NULL,`soil_don`\tTEXT NOT NULL,`soil_clay`\tTEXT NOT NULL,`soil_mold`\tTEXT NOT NULL,`soil_sandy`\tTEXT NOT NULL,`vilage_id`\tINTEGER NOT NULL,`cr_by`\tTEXT NOT NULL,`cr_date`\tTEXT NOT NULL,`upd_by`\tTEXT,`upd_date`\tTEXT,`ACTIVE`\tTEXT NOT NULL);");
        Log.d("MYLOG", "Create Table Successfully.");

        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (1,'อบจ.','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (2,'กรุงเทพมหานครฯ','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (3,'เมืองพัทยา','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (4,'เทศบาลนคร','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (5,'เทศบาลเมือง','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (6,'เทศบาลตำบล','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (7,'อบต. ขนาดใหญ่','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (8,'อบต. ขนาดกลาง','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `opt_type` VALUES (9,'อบต. ขนาดเล็ก','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `nationality` VALUES (1,'ไทย','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `members` VALUES (1,'admin','1234','administrator','1','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    // Select Data
    public ArrayList<HashMap<String, String>> SelectData(String tableName) {
        // TODO Auto-generated method stub

        try {
            //String arrData[] = null;
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            /*Cursor cursor = db.query("opt_type", new String[]{"*"}, null,
                    new String[]{String.valueOf(strMemberID)}, null, null, null, null);*/
            String strSQL = "SELECT * FROM " + tableName;
            Cursor cursor = db.rawQuery(strSQL, null);
            //Log.d("MYLOG", "cursor: " + cursor);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        //arrData = new String[cursor.getColumnCount()];
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            //arrData[i] = cursor.getString(i);
                            map.put(cursor.getColumnName(i), cursor.getString(i));
                        }
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            Log.d("MYLOG", "Selected List: " + MyArrList);
            return MyArrList;

        } catch (Exception e) {
            return null;
        }
    }

    // Insert Data
    public long InsertData(String TableName, ContentValues Val) {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data
            Log.d("MYLOG", "Val Insert: " + Val);

            long rows = db.insert(TableName, null, Val);

            db.close();
            return rows; // return rows inserted.

        } catch (Exception e) {
            return -1;
        }
    }

    // Update Data
    public long UpdateData(String TableName, ContentValues Val, String strPK, String strPKValue) {
        // TODO Auto-generated method stub
        try {
            SQLiteDatabase db;
            db = this.getWritableDatabase(); // Write Data
            Log.d("MYLOG", "Val Update: " + Val);
            String where = strPK + " = ?";
            String[] whereArgs = new String[]{String.valueOf(strPKValue)};
            long rows = db.update(TableName, Val, where, whereArgs);
            db.close();
            return rows; // return rows inserted.
        } catch (Exception e) {
            return -1;
        }
    }

    // Select Where Data
    public ArrayList<HashMap<String, String>> SelectWhereData(String tableName, String strKey, String strValue) {
        // TODO Auto-generated method stub

        try {
            //String arrData[] = null;
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            /*Cursor cursor = db.query("opt_type", new String[]{"*"}, null,
                    new String[]{String.valueOf(strMemberID)}, null, null, null, null);*/
            String strSQL = "SELECT * FROM " + tableName + " WHERE " + strKey + " = " + strValue;
            Cursor cursor = db.rawQuery(strSQL, null);
            //Log.d("MYLOG", "cursor: " + cursor);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        //arrData = new String[cursor.getColumnCount()];
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            //arrData[i] = cursor.getString(i);
                            map.put(cursor.getColumnName(i), cursor.getString(i));
                        }
                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;

        } catch (Exception e) {
            return null;
        }
    }

    public List<String> SelectOPTType() {
        try {
            List<String> MyArrList = new ArrayList<String>();

            SQLiteDatabase db;
            db = this.getReadableDatabase();

            String strSQL = "SELECT * FROM opt_type";

            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        MyArrList.add(cursor.getString(1));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> SelectOPT() {
        try {
            ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            SQLiteDatabase db;
            db = this.getReadableDatabase();

            String strSQL = "SELECT * FROM opt";
            Cursor cursor = db.rawQuery(strSQL, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("opt_id", cursor.getString(0));
                        map.put("opt_name", cursor.getString(1));
                        map.put("opt_type_id", cursor.getString(2));
                        map.put("opt_location_lat", cursor.getString(3));
                        map.put("opt_location_lng", cursor.getString(4));
                        map.put("opt_address_no", cursor.getString(5));
                        map.put("opt_vilage_no", cursor.getString(6));
                        map.put("opt_alley", cursor.getString(7));
                        map.put("opt_road", cursor.getString(8));
                        map.put("opt_province", cursor.getString(9));
                        map.put("opt_district", cursor.getString(10));
                        map.put("opt_sub_district", cursor.getString(11));
                        map.put("opt_postal_code", cursor.getString(12));
                        map.put("opt_tel", cursor.getString(13));
                        map.put("opt_fax", cursor.getString(14));
                        map.put("opt_vision", cursor.getString(15));
                        map.put("cr_by", cursor.getString(16));
                        map.put("cr_date", cursor.getString(17));

                        MyArrList.add(map);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return MyArrList;
        } catch (Exception e) {
            return null;
        }
    }

}
