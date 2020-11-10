package com.java.prj.smartcity.Fragments;

import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.java.prj.smartcity.R;
import com.java.prj.smartcity.ReportFillActivity;


public class ReportListFragment extends Fragment {


    public ReportListFragment() {
        // Required empty public constructor
    }

    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);

        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.fragment_report_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ReportFillActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}
