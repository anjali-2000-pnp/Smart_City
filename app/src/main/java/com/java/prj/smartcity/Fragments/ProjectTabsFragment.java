package com.java.prj.smartcity.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.prj.smartcity.Adapters.ProjectCategoryAdapter;
import com.java.prj.smartcity.R;

public class ProjectTabsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ProjectTabsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_tabs, container, false);

        tabLayout = (TabLayout)view.findViewById(R.id.project_tabs_fragment_tablayout);
        viewPager = (ViewPager)view.findViewById(R.id.project_tabs_fragment_viewpager);

        ProjectCategoryAdapter adapter=new ProjectCategoryAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
