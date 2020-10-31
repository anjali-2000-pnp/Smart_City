package com.java.prj.smartcity.Adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.java.prj.smartcity.Fragments.ProjectType1Fragment;
import com.java.prj.smartcity.Fragments.ProjectType2Fragment;

public class ProjectCategoryAdapter extends FragmentPagerAdapter {

    public ProjectCategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ProjectType1Fragment();
        }  else {
            return new ProjectType2Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Type 1";
        }  else {
            return "Type 2";
        }
    }

}
