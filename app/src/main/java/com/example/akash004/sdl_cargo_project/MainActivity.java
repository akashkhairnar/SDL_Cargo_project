package com.example.akash004.sdl_cargo_project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private android.support.v7.widget.Toolbar mtoolbar;


    private ViewPager mViewpager;
    private FirebaseAuth mAuth;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mtoolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Cargo Shipping");


        mViewpager=(ViewPager)findViewById(R.id.main_tab_pager);
        pagerAdapter=new PagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(pagerAdapter);


        tabLayout=(TabLayout)findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(mViewpager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendtostart();
        }
    }

    private void sendtostart() {
        Intent startIntent = new Intent(MainActivity.this, beginActivity.class);
        startActivity(startIntent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.main_logout){
            FirebaseAuth.getInstance().signOut();
            sendtostart();
        }
        if(item.getItemId()==R.id.main_setting)
        {
            Intent settingIntent=new Intent(MainActivity.this,Setting_Activity.class);
            startActivity(settingIntent);
        }
        if(item.getItemId()==R.id.main_myprofile)
        {
            Intent profileIntent=new Intent(MainActivity.this,Profile_activity.class);
            startActivity(profileIntent);
        }




        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}

