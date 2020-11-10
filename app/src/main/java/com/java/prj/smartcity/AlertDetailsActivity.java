package com.java.prj.smartcity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.java.prj.smartcity.Adapters.AlertTabsAdapter;

public class AlertDetailsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);

        tabLayout = (TabLayout)findViewById(R.id.alert_details_tablayout);
        viewPager = (ViewPager)findViewById(R.id.alert_details_viewpager);

        AlertTabsAdapter adapter=new AlertTabsAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}

