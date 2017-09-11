package com.ht.htlibrary.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.ht.htlibrary.ui.fragment.BaseFragment;

import java.util.List;

/**
 * Created by rinkousen on 2017/8/24 0024.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

	List<BaseFragment> mFragments;

	List<CustomTabEntity> mTabs;

	public TabAdapter(FragmentManager fm, List<BaseFragment> fragments, List<CustomTabEntity> tabs) {
		super(fm);
		mFragments = fragments;
		mTabs = tabs;
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTabs.get(position).getTabTitle();
	}
}
