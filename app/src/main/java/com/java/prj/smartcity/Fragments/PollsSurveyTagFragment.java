package com.java.prj.smartcity.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.java.prj.smartcity.Adapters.PollsSurveyTabAdapter;
import com.java.prj.smartcity.R;


public class PollsSurveyTagFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public PollsSurveyTagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_polls_survey_tag, container, false);

        tabLayout = (TabLayout)view.findViewById(R.id.pollssurvey_tabs_fragment_tablayout);
        viewPager = (ViewPager)view.findViewById(R.id.pollssurvey_tabs_fragment_viewpager);

        PollsSurveyTabAdapter adapter = new PollsSurveyTabAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
