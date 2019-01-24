package com.yunqilai.delivery.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wangjun on 2016/7/19.
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListFragments;
    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> listFragments) {
        super(fm);
        mListFragments = listFragments;
    }

    @Override
    public int getCount() {
        return mListFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }
}
