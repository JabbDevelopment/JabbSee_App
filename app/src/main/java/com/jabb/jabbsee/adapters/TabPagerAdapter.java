package com.jabb.jabbsee.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.jabb.jabbsee.Constants;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + TabPagerAdapter.class.getSimpleName();
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mTitleList = new ArrayList<>();

    //private final String[] TAB_NAMES = new String[]{"AKTIVA", "ALLA", "TIPS"};


    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.d(TAG, "I TabPagerAdapter");
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mTitleList.add(title);

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
