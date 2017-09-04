package com.ht.htlibrary.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ht.htlibrary.R;
import com.ht.htlibrary.ui.adapter.TabAdapter;
import com.ht.htlibrary.ui.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public abstract class BaseTabActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

	protected TabLayout mTabLayout;

	List<TabLayout.Tab> mTabs;

	ViewPager mViewPager;

	List<BaseFragment> mFragments;

	TabAdapter mTabAdapter;

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected int getLayout() {
		return R.layout.activity_base_tab;
	}

	@Override
	protected void initView() {
		super.initView();
		mTabLayout = (TabLayout) findViewById(R.id.base_tab);
		mViewPager = (ViewPager) findViewById(R.id.tab_vp);
		mTabs = getTabs();
		mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		mTabLayout.addOnTabSelectedListener(this);


	}

	@Override
	protected void initData() {
		mTabAdapter = new TabAdapter(getSupportFragmentManager(), getFragments(), getTabs());
		mViewPager.setAdapter(mTabAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	/**
	 * 动态获取标签
	 * @return
	 */
	public abstract List<TabLayout.Tab> getTabs();

	@Override
	public void onTabSelected(TabLayout.Tab tab) {

	}

	@Override
	public void onTabUnselected(TabLayout.Tab tab) {

	}

	@Override
	public void onTabReselected(TabLayout.Tab tab) {

	}

	public abstract List<BaseFragment> getFragments();
}
