package com.java.prj.smartcity.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.java.prj.smartcity.Fragments.PollsFragment;
import com.java.prj.smartcity.Fragments.SurveyFragment;

public class PollsSurveyTabAdapter extends FragmentPagerAdapter {

    public PollsSurveyTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PollsFragment();
        }  else {
            //return new PollsFragment();
            return new SurveyFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Polls";
        }  else {
            return "Survey";
        }
    }

}
