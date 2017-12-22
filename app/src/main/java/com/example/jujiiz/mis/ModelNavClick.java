package com.example.jujiiz.mis;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by JuJiiz on 21/12/2560.
 */

public class ModelNavClick {

    public static void displaySelectedScreen(Context context,int id){
        //android.support.v4.app.Fragment fragment = null;
        Intent intent = null;
        switch (id){
            case R.id.nav_Household:
                if(context instanceof HouseholdActivity){
                    Toast.makeText(context,context.toString(),Toast.LENGTH_SHORT).show();
                }else{
                    intent = new Intent(context, HouseholdActivity.class);
                    context.startActivity(intent);
                }
                //fragment = new HouseholdFragment();
                break;
            /*case R.id.nav_People:
                fragment = new PeopleFragment();
                break;
            case R.id.nav_Property:
                fragment = new PropertyFragment();
                break;*/
        }
        /*if (fragment != null) {
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
    }
}
