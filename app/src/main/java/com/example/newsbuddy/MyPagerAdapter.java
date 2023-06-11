package com.example.newsbuddy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new HomeFragment();

            case 1:
                return new TechnologyFragment();

            case 2:
                return new SportsFragment();

            case 3:
                return new HealthFragment();

            case 4:
                return new EntertainmentFragment();

            case 5:
                return new ScienceFragment();

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
