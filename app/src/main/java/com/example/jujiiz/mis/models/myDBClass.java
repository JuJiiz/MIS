package com.example.jujiiz.mis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `tr14` (\n" +
                "\t`tr14_running`\tINTEGER NOT NULL,\n" +
                "\t`house_id`\tINTEGER NOT NULL,\n" +
                "\t`house_no`\tTEXT NOT NULL,\n" +
                "\t`p_order`\tINTEGER NOT NULL,\n" +
                "\t`idcard`\tINTEGER NOT NULL,\n" +
                "\t`prename`\tTEXT NOT NULL,\n" +
                "\t`firstname`\tTEXT NOT NULL,\n" +
                "\t`lastname`\tTEXT NOT NULL,\n" +
                "\t`sex`\tTEXT NOT NULL,\n" +
                "\t`dweller`\tTEXT NOT NULL,\n" +
                "\t`birthdate`\tTEXT NOT NULL,\n" +
                "\t`nationality`\tTEXT NOT NULL,\n" +
                "\t`vilage_no`\tINTEGER NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `prename` (\n" +
                "\t`prename_id`\tINTEGER NOT NULL,\n" +
                "\t`prename_detail`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_works` (\n" +
                "\t`works_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`works_type`\tTEXT NOT NULL,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tINTEGER NOT NULL,\n" +
                "\t`cr_date`\tINTEGER NOT NULL,\n" +
                "\t`upd_by`\tINTEGER,\n" +
                "\t`upd_date`\tINTEGER,\n" +
                "\t`ACTIVE`\tINTEGER NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_transport` (\n" +
                "\t`transport_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`transport_type`\tTEXT NOT NULL,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`trans1`\tTEXT NOT NULL,\n" +
                "\t`trans2`\tTEXT NOT NULL,\n" +
                "\t`trans3`\tTEXT NOT NULL,\n" +
                "\t`trans4`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_private` (\n" +
                "\t`private_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`works_id`\tINTEGER NOT NULL,\n" +
                "\t`private1`\tTEXT NOT NULL,\n" +
                "\t`private2`\tTEXT NOT NULL,\n" +
                "\t`private3`\tTEXT NOT NULL,\n" +
                "\t`private4`\tTEXT NOT NULL,\n" +
                "\t`private5`\tTEXT NOT NULL,\n" +
                "\t`private6`\tTEXT NOT NULL,\n" +
                "\t`private7`\tTEXT NOT NULL,\n" +
                "\t`private_another`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_govern` (\n" +
                "\t`govern_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`works_id`\tINTEGER NOT NULL,\n" +
                "\t`govern1`\tTEXT NOT NULL,\n" +
                "\t`govern2`\tTEXT NOT NULL,\n" +
                "\t`govern3`\tTEXT NOT NULL,\n" +
                "\t`govern4`\tTEXT NOT NULL,\n" +
                "\t`govern_another`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_animal` (\n" +
                "\t`animal_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`works_id`\tINTEGER NOT NULL,\n" +
                "\t`animal1`\tTEXT NOT NULL,\n" +
                "\t`animal2`\tTEXT NOT NULL,\n" +
                "\t`animal3`\tTEXT NOT NULL,\n" +
                "\t`animal4`\tTEXT NOT NULL,\n" +
                "\t`animal5`\tTEXT NOT NULL,\n" +
                "\t`animal6`\tTEXT NOT NULL,\n" +
                "\t`animal7`\tTEXT NOT NULL,\n" +
                "\t`animal8`\tTEXT NOT NULL,\n" +
                "\t`animal9`\tTEXT NOT NULL,\n" +
                "\t`animal_another`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_job_agriculture` (\n" +
                "\t`agri_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`works_id`\tINTEGER NOT NULL,\n" +
                "\t`agri1`\tTEXT NOT NULL,\n" +
                "\t`agri2`\tTEXT NOT NULL,\n" +
                "\t`agri3`\tTEXT NOT NULL,\n" +
                "\t`agri4`\tTEXT NOT NULL,\n" +
                "\t`agri5`\tTEXT NOT NULL,\n" +
                "\t`agri6`\tTEXT NOT NULL,\n" +
                "\t`agri7`\tTEXT NOT NULL,\n" +
                "\t`agri8`\tTEXT NOT NULL,\n" +
                "\t`agri_another`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_disabled` (\n" +
                "\t`disabled_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`disabled_type`\tTEXT NOT NULL,\n" +
                "\t`disabled1`\tTEXT NOT NULL,\n" +
                "\t`disabled2`\tTEXT NOT NULL,\n" +
                "\t`disabled3`\tTEXT NOT NULL,\n" +
                "\t`disabled4`\tTEXT NOT NULL,\n" +
                "\t`disabled5`\tTEXT NOT NULL,\n" +
                "\t`disabled6`\tTEXT NOT NULL,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_contagioushis` (\n" +
                "\t`conth_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`conth_type`\tTEXT NOT NULL,\n" +
                "\t`conth1`\tTEXT NOT NULL,\n" +
                "\t`conth2`\tTEXT NOT NULL,\n" +
                "\t`conth3`\tTEXT NOT NULL,\n" +
                "\t`conth4`\tTEXT NOT NULL,\n" +
                "\t`conth5`\tTEXT NOT NULL,\n" +
                "\t`conth6`\tTEXT NOT NULL,\n" +
                "\t`conth7`\tTEXT NOT NULL,\n" +
                "\t`conth8`\tTEXT NOT NULL,\n" +
                "\t`conth9`\tTEXT NOT NULL,\n" +
                "\t`conth10`\tTEXT NOT NULL,\n" +
                "\t`conth11`\tTEXT NOT NULL,\n" +
                "\t`conth12`\tTEXT NOT NULL,\n" +
                "\t`conth13`\tTEXT NOT NULL,\n" +
                "\t`conth14`\tTEXT NOT NULL,\n" +
                "\t`conth15`\tTEXT NOT NULL,\n" +
                "\t`conth16`\tTEXT NOT NULL,\n" +
                "\t`conth17`\tTEXT NOT NULL,\n" +
                "\t`conth18`\tTEXT NOT NULL,\n" +
                "\t`conth19`\tTEXT NOT NULL,\n" +
                "\t`conth20`\tTEXT NOT NULL,\n" +
                "\t`conth21`\tTEXT NOT NULL,\n" +
                "\t`conth22`\tTEXT NOT NULL,\n" +
                "\t`conth23`\tTEXT NOT NULL,\n" +
                "\t`conth24`\tTEXT NOT NULL,\n" +
                "\t`conth25`\tTEXT NOT NULL,\n" +
                "\t`conth26`\tTEXT NOT NULL,\n" +
                "\t`conth27`\tTEXT NOT NULL,\n" +
                "\t`conth28`\tTEXT NOT NULL,\n" +
                "\t`conth29`\tTEXT NOT NULL,\n" +
                "\t`conth30`\tTEXT NOT NULL,\n" +
                "\t`conth31`\tTEXT NOT NULL,\n" +
                "\t`conth32`\tTEXT NOT NULL,\n" +
                "\t`conth33`\tTEXT NOT NULL,\n" +
                "\t`conth34`\tTEXT NOT NULL,\n" +
                "\t`conth35`\tTEXT NOT NULL,\n" +
                "\t`conth36`\tTEXT NOT NULL,\n" +
                "\t`conth37`\tTEXT NOT NULL,\n" +
                "\t`conth38`\tTEXT NOT NULL,\n" +
                "\t`conth39`\tTEXT NOT NULL,\n" +
                "\t`conth40`\tTEXT NOT NULL,\n" +
                "\t`conth41`\tTEXT NOT NULL,\n" +
                "\t`conth42`\tTEXT NOT NULL,\n" +
                "\t`conth43`\tTEXT NOT NULL,\n" +
                "\t`conth44`\tTEXT NOT NULL,\n" +
                "\t`conth45`\tTEXT NOT NULL,\n" +
                "\t`conth46`\tTEXT NOT NULL,\n" +
                "\t`conth47`\tTEXT NOT NULL,\n" +
                "\t`conth48`\tTEXT NOT NULL,\n" +
                "\t`conth49`\tTEXT NOT NULL,\n" +
                "\t`conth50`\tTEXT NOT NULL,\n" +
                "\t`conth51`\tTEXT NOT NULL,\n" +
                "\t`conth_another`\tTEXT NOT NULL,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_congenitalhis` (\n" +
                "\t`congh_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`congh_type`\tTEXT NOT NULL,\n" +
                "\t`congh1`\tTEXT NOT NULL,\n" +
                "\t`congh2`\tTEXT NOT NULL,\n" +
                "\t`congh3`\tTEXT NOT NULL,\n" +
                "\t`congh4`\tTEXT NOT NULL,\n" +
                "\t`congh5`\tTEXT NOT NULL,\n" +
                "\t`congh6`\tTEXT NOT NULL,\n" +
                "\t`congh7`\tTEXT NOT NULL,\n" +
                "\t`congh8`\tTEXT NOT NULL,\n" +
                "\t`congh9`\tTEXT NOT NULL,\n" +
                "\t`congh10`\tTEXT NOT NULL,\n" +
                "\t`congh11`\tTEXT NOT NULL,\n" +
                "\t`congh12`\tTEXT NOT NULL,\n" +
                "\t`congh13`\tTEXT NOT NULL,\n" +
                "\t`congh14`\tTEXT NOT NULL,\n" +
                "\t`congh15`\tTEXT NOT NULL,\n" +
                "\t`congh16`\tTEXT NOT NULL,\n" +
                "\t`congh17`\tTEXT NOT NULL,\n" +
                "\t`congh18`\tTEXT NOT NULL,\n" +
                "\t`congh19`\tTEXT NOT NULL,\n" +
                "\t`congh20`\tTEXT NOT NULL,\n" +
                "\t`congh21`\tTEXT NOT NULL,\n" +
                "\t`congh22`\tTEXT NOT NULL,\n" +
                "\t`congh23`\tTEXT NOT NULL,\n" +
                "\t`congh24`\tTEXT NOT NULL,\n" +
                "\t`congh25`\tTEXT NOT NULL,\n" +
                "\t`congh26`\tTEXT NOT NULL,\n" +
                "\t`congh_another`\tTEXT NOT NULL,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_asset_vehicle` (\n" +
                "\t`vehicle_running`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`regisdate`\tTEXT NOT NULL,\n" +
                "\t`vehical_img`\tTEXT NOT NULL,\n" +
                "\t`vtype_id`\tINTEGER NOT NULL,\n" +
                "\t`vehical_rent`\tTEXT NOT NULL,\n" +
                "\t`distributor`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_asset_pet` (\n" +
                "\t`pet_running`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`pet_regis`\tTEXT NOT NULL,\n" +
                "\t`pet_img`\tTEXT NOT NULL,\n" +
                "\t`pet_amount`\tINTEGER NOT NULL,\n" +
                "\t`pet_type`\tTEXT NOT NULL,\n" +
                "\t`pet_sex`\tTEXT NOT NULL,\n" +
                "\t`vaccine`\tTEXT NOT NULL,\n" +
                "\t`vaccine_during`\tTEXT NOT NULL,\n" +
                "\t`vaccine_lastest`\tTEXT NOT NULL,\n" +
                "\t`sterile`\tTEXT NOT NULL,\n" +
                "\t`pet_newborn`\tTEXT NOT NULL,\n" +
                "\t`pet_newborn_number`\tINTEGER NOT NULL,\n" +
                "\t`distributor`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_asset_land` (\n" +
                "\t`land_running`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`system_id`\tINTEGER NOT NULL,\n" +
                "\t`dimen1`\tINTEGER NOT NULL,\n" +
                "\t`dimen2`\tINTEGER NOT NULL,\n" +
                "\t`dimen3`\tINTEGER NOT NULL,\n" +
                "\t`land_benefit`\tTEXT NOT NULL,\n" +
                "\t`land_location`\tTEXT NOT NULL,\n" +
                "\t`land_tax`\tTEXT NOT NULL,\n" +
                "\t`land_rent`\tTEXT NOT NULL,\n" +
                "\t`distributor`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population_asset_animal` (\n" +
                "\t`animal_running`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`animal_regis`\tTEXT NOT NULL,\n" +
                "\t`animal_amount`\tINTEGER NOT NULL,\n" +
                "\t`atype_id`\tINTEGER NOT NULL,\n" +
                "\t`infection`\tTEXT NOT NULL,\n" +
                "\t`infection_detail`\tTEXT NOT NULL,\n" +
                "\t`shelter`\tTEXT NOT NULL,\n" +
                "\t`diseasecontrol`\tTEXT NOT NULL,\n" +
                "\t`diseasecontrol_by`\tTEXT NOT NULL,\n" +
                "\t`disease_shelter`\tTEXT NOT NULL,\n" +
                "\t`market`\tTEXT NOT NULL,\n" +
                "\t`market_place`\tTEXT NOT NULL,\n" +
                "\t`distributor`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `population` (\n" +
                "\t`population_running`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`population_idcard`\tINTEGER NOT NULL,\n" +
                "\t`prename`\tTEXT NOT NULL,\n" +
                "\t`firstname`\tTEXT NOT NULL,\n" +
                "\t`lastname`\tTEXT NOT NULL,\n" +
                "\t`birthdate`\tTEXT NOT NULL,\n" +
                "\t`height`\tINTEGER NOT NULL,\n" +
                "\t`weight`\tINTEGER NOT NULL,\n" +
                "\t`sex`\tTEXT NOT NULL,\n" +
                "\t`bloodgroup`\tTEXT NOT NULL,\n" +
                "\t`living`\tTEXT NOT NULL,\n" +
                "\t`maritalstatus`\tTEXT NOT NULL,\n" +
                "\t`tel`\tINTEGER NOT NULL,\n" +
                "\t`nationality`\tTEXT NOT NULL,\n" +
                "\t`house_id`\tINTEGER NOT NULL,\n" +
                "\t`currentaddr`\tTEXT NOT NULL,\n" +
                "\t`currentaddr_province`\tTEXT NOT NULL,\n" +
                "\t`currentaddr_country`\tTEXT NOT NULL,\n" +
                "\t`dwellerstatus`\tTEXT NOT NULL,\n" +
                "\t`income`\tTEXT NOT NULL,\n" +
                "\t`income_money`\tINTEGER NOT NULL,\n" +
                "\t`dept`\tTEXT NOT NULL,\n" +
                "\t`saving`\tTEXT NOT NULL,\n" +
                "\t`allergichis`\tTEXT NOT NULL,\n" +
                "\t`allergichis_detail`\tTEXT NOT NULL,\n" +
                "\t`disadvantage`\tTEXT NOT NULL,\n" +
                "\t`sub_al`\tTEXT NOT NULL,\n" +
                "\t`education`\tTEXT NOT NULL,\n" +
                "\t`education_class`\tTEXT NOT NULL,\n" +
                "\t`literacy`\tTEXT NOT NULL,\n" +
                "\t`technology`\tTEXT NOT NULL,\n" +
                "\t`expertise`\tTEXT NOT NULL,\n" +
                "\t`expertise_name`\tTEXT NOT NULL,\n" +
                "\t`expertise_detail`\tTEXT NOT NULL,\n" +
                "\t`religion`\tTEXT NOT NULL,\n" +
                "\t`religion_another`\tTEXT NOT NULL,\n" +
                "\t`participation`\tTEXT NOT NULL,\n" +
                "\t`election`\tTEXT NOT NULL,\n" +
                "\t`residence_status`\tTEXT NOT NULL,\n" +
                "\t`latentpop_province`\tTEXT NOT NULL,\n" +
                "\t`latentpop_country`\tTEXT NOT NULL,\n" +
                "\t`distributor`\tTEXT NOT NULL,\n" +
                "\t`survey_status`\tTEXT NOT NULL,\n" +
                "\t`upload_status`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `nationality` (\n" +
                "\t`nationality_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`nationality_detail`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_problem` (\n" +
                "\t`problem_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`prob1`\tTEXT NOT NULL,\n" +
                "\t`prob2`\tTEXT NOT NULL,\n" +
                "\t`prob3`\tTEXT NOT NULL,\n" +
                "\t`prob4`\tTEXT NOT NULL,\n" +
                "\t`prob5`\tTEXT NOT NULL,\n" +
                "\t`prob6`\tTEXT NOT NULL,\n" +
                "\t`prob7`\tTEXT NOT NULL,\n" +
                "\t`prob8`\tTEXT NOT NULL,\n" +
                "\t`prob9`\tTEXT NOT NULL,\n" +
                "\t`prob10`\tTEXT NOT NULL,\n" +
                "\t`problem_another`\tTEXT NOT NULL,\n" +
                "\t`house_id`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_envyprob` (\n" +
                "\t`envyprob_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`envyprob_type`\tTEXT NOT NULL,\n" +
                "\t`ep1`\tTEXT NOT NULL,\n" +
                "\t`ep2`\tTEXT NOT NULL,\n" +
                "\t`ep3`\tTEXT NOT NULL,\n" +
                "\t`ep4`\tTEXT NOT NULL,\n" +
                "\t`ep5`\tTEXT NOT NULL,\n" +
                "\t`ep6`\tTEXT NOT NULL,\n" +
                "\t`ep7`\tTEXT NOT NULL,\n" +
                "\t`house_id`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_disaster` (\n" +
                "\t`disaster_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`disaster_type`\tTEXT NOT NULL,\n" +
                "\t`dis1`\tTEXT NOT NULL,\n" +
                "\t`dis2`\tTEXT NOT NULL,\n" +
                "\t`dis3`\tTEXT NOT NULL,\n" +
                "\t`dis4`\tTEXT NOT NULL,\n" +
                "\t`dis5`\tTEXT NOT NULL,\n" +
                "\t`dis6`\tTEXT NOT NULL,\n" +
                "\t`dis7`\tTEXT NOT NULL,\n" +
                "\t`dis8`\tTEXT NOT NULL,\n" +
                "\t`dis9`\tTEXT NOT NULL,\n" +
                "\t`dis10`\tTEXT NOT NULL,\n" +
                "\t`dis11`\tTEXT NOT NULL,\n" +
                "\t`dis12`\tTEXT NOT NULL,\n" +
                "\t`dis13`\tTEXT NOT NULL,\n" +
                "\t`dis14`\tTEXT NOT NULL,\n" +
                "\t`house_id`\tINTEGER NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house` (\n" +
                "\t`house_id`\tINTEGER NOT NULL,\n" +
                "\t`house_no`\tINTEGER NOT NULL,\n" +
                "\t`vilage_id`\tINTEGER NOT NULL,\n" +
                "\t`house_location_lat`\tTEXT NOT NULL,\n" +
                "\t`house_location_lng`\tTEXT NOT NULL,\n" +
                "\t`house_in_registry`\tTEXT NOT NULL,\n" +
                "\t`house_status`\tTEXT NOT NULL,\n" +
                "\t`house_family_type`\tTEXT NOT NULL,\n" +
                "\t`distributor`\tTEXT NOT NULL,\n" +
                "\t`survey_status`\tTEXT NOT NULL,\n" +
                "\t`upload_status`\tTEXT NOT NULL,\n" +
                "\t`distributor_img`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT NOT NULL,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        /*sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `house_img` (\n" +
                "\t`house_id`\tINTEGER NOT NULL PRIMARY KEY,\n" +
                "\t`distributor_img`\tBLOB NOT NULL\n" +
                ");");*/

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `asset_vehicle` (\n" +
                "\t`vtype_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`vtype_name`\tTEXT NOT NULL,\n" +
                "\t`vtype_detail`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `asset_animal` (\n" +
                "\t`atype_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`atype_name`\tTEXT NOT NULL,\n" +
                "\t`cr_by`\tTEXT NOT NULL,\n" +
                "\t`cr_date`\tTEXT NOT NULL,\n" +
                "\t`upd_by`\tTEXT,\n" +
                "\t`upd_date`\tTEXT,\n" +
                "\t`ACTIVE`\tTEXT NOT NULL\n" +
                ");");

        Log.d("MYLOG", "Create Table Successfully.");

        sqLiteDatabase.execSQL("INSERT INTO `nationality` VALUES (1,'ไทย','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");

        sqLiteDatabase.execSQL("INSERT INTO `prename` VALUES (1,'นาย','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `prename` VALUES (2,'นาง','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `prename` VALUES (3,'นางสาว','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `prename` VALUES (4,'เด็กชาย','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        sqLiteDatabase.execSQL("INSERT INTO `prename` VALUES (5,'เด็กหญิง','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");

        sqLiteDatabase.execSQL("INSERT INTO `asset_vehicle` VALUES (1,'ประเภทยานพาหนะทั่วไป','รถจักรยานยนต์','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (2,'ประเภทยานพาหนะทั่วไป','รถกะบะ รับจ้าง/ให้เช่า ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (3,'ประเภทยานพาหนะทั่วไป','รถกะบะ ส่วนบุคคล','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (4,'ประเภทยานพาหนะทั่วไป','รถเก๋ง รับจ้าง/ให้เช่า ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (5,'ประเภทยานพาหนะทั่วไป','รถเก๋ง ส่วนบุคคล','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (6,'ประเภทยานพาหนะทั่วไป','รถตู้ รับจ้าง/ให้เช่า','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (7,'ประเภทยานพาหนะทั่วไป','รถตู้ ส่วนบุคคล','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (8,'ประเภทยานพาหนะทั่วไป','รถโดยสาร รับจ้าง/ให้เช่า','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (9,'ประเภทยานพาหนะทั่วไป','รถโดยสาร ส่วนบุคคล','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (10,'ประเภทยานพาหนะทั่วไป','รถยนต์เพื่อการเกษตร','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (11,'ประเภทยานพาหนะทั่วไป','เรือยนต์','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (12,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถบรรทุกเครื่องมือ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (13,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถยนต์บรรทุกเครื่องจักรแบบท้ายลาก ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (14,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถยนต์บรรทุกเทท้าย','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (15,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถยนต์หัวลากและเทรลเลอร์หางพ่วง','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (16,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถขุดดินตีนตะขาบและรถขุดดินล้อยาง','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (17,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถแทรคเตอร์','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (18,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถไสผิวถนน','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (19,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถปูยางมะตอย   ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (20,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถตักหน้าขุกหลัง','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (21,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถบดถนน','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (22,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถเกลี่ยดิน','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (23,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถตักตีนตะขาบและรถตักล้อยาง','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (24,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถกระเช้าซ่อมไฟฟ้า','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (25,'ประเภทยานพาหนะด้านโครงสร้างพื้นฐาน','รถกระเช้าขนาดสูง 24 เมตร','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (26,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถตรวจมลพิษทางอากาศเคลื่อนที่','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (27,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถสุขาเคลื่อนที่','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (28,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถเก็บขยะมูลฝอย','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (29,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถยนต์สูบสิ่งปฏิกูล','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (30,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถยนต์บรรทุกขยะติดเชื้อ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (31,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถกระเช้าตัดต้นไม้','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (32,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถดูดล้างท่อ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (33,'ประเภทยานพาหนะด้านบรรเทาสาธารณภัย','รถกู้ชีพกู้ภัย','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (34,'ประเภทยานพาหนะด้านสิ่งแวดล้อม','รถดับเพลิง ขนาด 5,000 ล. และ 1,000 ล.','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (35,'ประเภทยานพาหนะด้านสิ่งแวดล้อม','รถยนต์บรรทุกน้ำ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (36,'ประเภทยานพาหนะด้านสิ่งแวดล้อม','รถไฟฟ้าส่องสว่าง','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (37,'ประเภทยานพาหนะด้านสิ่งแวดล้อม','รถติดตั้งเครื่องกำเนิดไฟฟ้าเคลื่อนที่','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (38,'ประเภทยานพาหนะด้านสิ่งแวดล้อม','รถหอน้ำ','ADMIN','2018-01-04 00:00:00',NULL,NULL,''),\n" +
                " (39,'ประเภทยานพาหนะด้านการศึกษา','รถห้องสมุดเคลื่อนที่','ADMIN','2018-01-04 00:00:00',NULL,NULL,'');");

        sqLiteDatabase.execSQL("INSERT INTO `asset_animal` VALUES (1,'โค','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y'),\n" +
                " (2,'กระบือ','ADMIN','2018-01-04 00:00:00',NULL,NULL,'Y');");
        Log.d("MYLOG", "Insert Table Successfully.");
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
            //Log.d("MYLOG", "Selected List: " + MyArrList);
            return MyArrList;

        } catch (Exception e) {
            return null;
        }
    }

    // Insert Data
    public long InsertData(String TableName, ContentValues Val) {
        // TODO Auto-generated method stub

        try {
            SQLiteDatabase db = this.getWritableDatabase(); // Write Data
            long rows = db.insert(TableName, null, Val);
            Log.d("MYLOG", TableName + " Insert: " + Val);
            Log.d("MYLOG", " rows: " + rows);
            //db.close();

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
            String where = strPK + " = ?";
            String[] whereArgs = new String[]{String.valueOf(strPKValue)};
            long rows = db.update(TableName, Val, where, whereArgs);
            Log.d("MYLOG", TableName + " Update: " + Val);
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
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
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

    // Select IMG
    public byte[] SelectImg(String tableName, String strKey, String strValue) {
        // TODO Auto-generated method stub

        try {
            byte[] image = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT * FROM " + tableName + " WHERE " + strKey + " = " + strValue;
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        image = cursor.getBlob(1);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return image;

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

    public ArrayList<String> CheckTable(){
        SQLiteDatabase db;
        db = this.getReadableDatabase(); // Read Data
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                arrTblNames.add( c.getString( c.getColumnIndex("name")) );
                c.moveToNext();
            }
        }
        return arrTblNames;
    }

}
