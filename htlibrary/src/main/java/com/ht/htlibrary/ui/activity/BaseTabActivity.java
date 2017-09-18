package com.ht.htlibrary.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ht.htlibrary.R;
import com.ht.htlibrary.ui.adapter.TabAdapter;
import com.ht.htlibrary.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public abstract class BaseTabActivity extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {

	protected CommonTabLayout mTabLayout;

	protected ArrayList<CustomTabEntity> mTabs;

	protected ViewPager mViewPager;

	protected List<BaseFragment> mFragments;

	protected TabAdapter mTabAdapter;

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
		mTabLayout = (CommonTabLayout) findViewById(R.id.base_tab);
		mViewPager = (ViewPager) findViewById(R.id.tab_vp);
		mTabs = getTabs();
		mFragments = getFragments();
	}

	@Override
	protected void initData() {
		mTabAdapter = new TabAdapter(getSupportFragmentManager(), mFragments, mTabs);
		mViewPager.setAdapter(mTabAdapter);
		mTabLayout.setTabData(mTabs);
		mTabLayout.setOnTabSelectListener(this);
		mViewPager.addOnPageChangeListener(this);
	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	/**
	 * 动态获取标签
	 * @return
	 */
	public abstract ArrayList<CustomTabEntity> getTabs();

	public abstract List<BaseFragment> getFragments();

	@Override
	public void onTabSelect(int position) {
		mViewPager.setCurrentItem(position);
	}

	@Override
	public void onTabReselect(int position) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		mTabLayout.setCurrentTab(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

}
