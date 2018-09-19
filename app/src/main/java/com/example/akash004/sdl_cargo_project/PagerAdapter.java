package com.example.akash004.sdl_cargo_project;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: {
                Home_Fragment home_fragment = new Home_Fragment();
                return home_fragment;

            }

            case 1: {
                Track_Fragment track_fragment = new Track_Fragment();
                return track_fragment;
            }
            case 2 : {
                Orders_Fragment orders_fragment = new Orders_Fragment();
                return orders_fragment;
            }
            default:
                return null;


        }


    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:return "HOME";
            case 1: return "TRACK";
            case 2: return "ORDERS";
            default:return null;
        }
    }
}
