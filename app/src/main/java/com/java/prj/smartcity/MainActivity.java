package com.java.prj.smartcity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.java.prj.smartcity.Fragments.AlertFragment;
import com.java.prj.smartcity.Fragments.MainFragment;
import com.java.prj.smartcity.Fragments.NewsFragment;
import com.java.prj.smartcity.Fragments.PollsSurveyTagFragment;
import com.java.prj.smartcity.Fragments.ProjectType1Fragment;
import com.java.prj.smartcity.HelperClasses.AppConstants;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    FragmentManager fm;
    String backStageName;

    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawer;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Main activity created ");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       /* if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        else
        {
            SharedPreferences prefs = getSharedPreferences(AppConstants.LOGIN_PREFS, MODE_PRIVATE);
            String form_status = prefs.getString(firebaseAuth.getCurrentUser().getEmail(), "0");

            if (form_status.equals("0"))
            {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
            }
        }


        */


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.nav_header_email);
        ImageView navImage = (ImageView) headerView.findViewById(R.id.nav_header_image);

        if (savedInstanceState==null)
        {
            fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            MainFragment mainFragment = MainFragment.newInstance();
            transaction.replace(R.id.main_fragment_container, mainFragment).commit();
        }

        try{
            navUsername.setText(firebaseAuth.getCurrentUser().getDisplayName());
            navEmail.setText(firebaseAuth.getCurrentUser().getEmail());
            Picasso.get().load(firebaseAuth.getCurrentUser().getPhotoUrl()).into(navImage);
        }catch (Exception e) {        }
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int itemId = item.getItemId();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (itemId == R.id.nav_home){
            boolean isFragmentInStack = fm.popBackStackImmediate(backStageName, 0);
            if (!isFragmentInStack) {
                MainFragment fragment = MainFragment.newInstance();
                fragmentTransaction.replace(R.id.main_fragment_container, fragment);
                backStageName = fragment.getClass().getName();
                fragmentTransaction.addToBackStack(backStageName).commit();
            }
        }
        else if (itemId == R.id.nav_projects){
            getSupportFragmentManager().popBackStackImmediate();
            fragmentTransaction.replace(R.id.main_fragment_container, new ProjectType1Fragment());
            fragmentTransaction.addToBackStack(null).commit();
        }
        else if (itemId == R.id.nav_alerts){
            getSupportFragmentManager().popBackStackImmediate();
            fragmentTransaction.replace(R.id.main_fragment_container,new AlertFragment());
            ((FragmentTransaction) fragmentTransaction).addToBackStack(null).commit();
        }
        else if (itemId == R.id.nav_innovation){
            Intent intent = new Intent(MainActivity.this, InnovationFillActivity.class);
            startActivity(intent);
        }
        else if (itemId == R.id.nav_news){

            NewsFragment newsFragment=new NewsFragment();

            Fade enterFade = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                enterFade=new Fade();
                enterFade.setStartDelay(1000);
                enterFade.setDuration(2000);
                newsFragment.setEnterTransition(enterFade);
            }

            getSupportFragmentManager().popBackStackImmediate();
            fragmentTransaction.replace(R.id.main_fragment_container, new NewsFragment());
            fragmentTransaction.addToBackStack(null).commit();
        }
        else if (itemId == R.id.nav_surveys){
            getSupportFragmentManager().popBackStackImmediate();
            fragmentTransaction.replace(R.id.main_fragment_container, new PollsSurveyTagFragment());
            fragmentTransaction.addToBackStack(null).commit();
        }
        else if (itemId == R.id.nav_reporting) {
            Intent intent = new Intent(MainActivity.this, ReportFillActivity.class);
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        }
        else if (itemId == R.id.nav_myaccount){
            Intent intent = new Intent(MainActivity.this, MyAccountActivity.class);
            startActivity(intent);
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}