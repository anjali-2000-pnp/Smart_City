package com.java.prj.smartcity.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.java.prj.smartcity.Fragments.AlertsDetailFragment;
import com.java.prj.smartcity.Fragments.VolunteerDetailsFragment;

public class AlertTabsAdapter extends FragmentPagerAdapter {


    public AlertTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            return new AlertsDetailFragment();
        }else
        {
            return new VolunteerDetailsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Details";
        }  else {
            return "Volunteers";
        }
    }
}

