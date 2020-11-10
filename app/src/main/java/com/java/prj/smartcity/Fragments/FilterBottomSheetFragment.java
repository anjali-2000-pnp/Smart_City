package com.java.prj.smartcity.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.java.prj.smartcity.R;


public class FilterBottomSheetFragment extends BottomSheetDialogFragment {


    public FilterBottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_bottom_sheet, container, false);

        return view;
    }

}
