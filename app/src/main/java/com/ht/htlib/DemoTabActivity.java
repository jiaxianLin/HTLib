package com.ht.htlib;

import android.os.Bundle;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.ht.htlibrary.ui.activity.BaseTabActivity;
import com.ht.htlibrary.ui.fragment.BaseFragment;
import com.ht.htlibrary.ui.tab.TabEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class DemoTabActivity extends BaseTabActivity {

	String[] arr;

	@Override
	protected void initParams(Bundle bundle) {
		super.initParams(bundle);
		arr = getResources().getStringArray(R.array.channel);
	}

	@Override
	public ArrayList<CustomTabEntity> getTabs() {
		ArrayList<CustomTabEntity> list = new ArrayList<>();
		for (String s : arr) {
			TabEntity tab = new TabEntity(s, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
			list.add(tab);
		}
		return list;
	}

	@Override
	public List<BaseFragment> getFragments() {
		List<BaseFragment> fragments = new ArrayList<>();
		for (String s : arr) {
			DemoFragment fragment = new DemoFragment();
			Bundle bundle = new Bundle();
			bundle.putString("title", s);
			fragment.setArguments(bundle);
			fragments.add(fragment);
		}
		return fragments;
	}

}
