package com.example.jujiiz.mis.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jujiiz.mis.R;

/**
 * Created by JuJiiz on 21/12/2560.
 */

public class HouseholdFormFragment extends android.support.v4.app.Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("แบบฟอร์ม้อมูลครัวเรือน");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup contrainer, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_household_form,contrainer,false);
    }
}
