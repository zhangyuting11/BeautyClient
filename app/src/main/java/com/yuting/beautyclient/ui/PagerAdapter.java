package com.yuting.beautyclient.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页分类导航适配器
 * Created by yuting on 2016/8/5.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> tabTitles;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        tabTitles = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String tabTitle) {
        this.fragments.add(fragment);
        this.tabTitles.add(tabTitle);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabTitles.get(position);
    }
}
