package com.java.prj.smartcity.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.java.prj.smartcity.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyReportsFragment} factory method to
 * create an instance of this fragment.
 */
public class MyReportsFragment extends Fragment {

    public MyReportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_reports, container, false);
    }

}
