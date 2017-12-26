package com.example.jujiiz.mis.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jujiiz.mis.R;
import com.example.jujiiz.mis.models.ModelToken;

/**
 * Created by JuJiiz on 20/12/2560.
 */

public class HouseholdFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {
    ListView listViewHousehold;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("ข้อมูลครัวเรือน");

        ModelToken.checkToken((MainActivity) getActivity().getApplicationContext());
        init();
    }

    void init() {

        listViewHousehold = (ListView) getView().findViewById(R.id.listHousehold);
        listViewHousehold.setOnItemClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup contrainer, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_household, contrainer, false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
