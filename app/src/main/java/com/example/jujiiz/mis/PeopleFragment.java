package com.example.jujiiz.mis;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by JuJiiz on 20/12/2560.
 */

public class PeopleFragment extends android.support.v4.app.Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("ข้อมูลประชากร");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup contrainer, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_people,contrainer,false);
    }
}
