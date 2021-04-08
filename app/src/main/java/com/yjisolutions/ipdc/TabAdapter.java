package com.yjisolutions.ipdc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {
int touchItem;

    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        touchItem = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                home Home = new home();
                return Home;
            case 1 :
                notes Notes = new notes();
                return Notes;
        }
        return null;
    }

    @Override
    public int getCount() {
        return touchItem;
    }
}