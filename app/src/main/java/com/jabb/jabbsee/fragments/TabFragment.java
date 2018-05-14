package com.jabb.jabbsee.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.adapters.TabPagerAdapter;

public class TabFragment extends Fragment {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + TabFragment.class.getSimpleName();
    TabPagerAdapter mTabPagerAdapter;
    private ViewPager mViewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "I TabFragment");
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        mTabPagerAdapter = new TabPagerAdapter(getFragmentManager());
        mViewPager = view.findViewById(R.id.viewContainer);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        setupViewPager();

        return view;

    }

    private void setupViewPager(){
        TabContentFragment activeFragment = new TabContentFragment();
       // activeFragment.setSerieList(SerieListHelper.getActiveSerieList());


       // mTabPagerAdapter.addFragment(activeFragment, "AKTIVA");
       // mTabPagerAdapter.addFragment(activeFragment, "ALLA");
       // mTabPagerAdapter.addFragment(activeFragment, "TIPS");

        mViewPager.setAdapter(mTabPagerAdapter);

    }
}
