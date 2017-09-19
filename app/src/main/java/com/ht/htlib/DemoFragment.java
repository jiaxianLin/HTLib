package com.ht.htlib;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ht.htlibrary.ui.fragment.BaseFragment;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class DemoFragment extends BaseFragment {

	public static DemoFragment newInstance() {

		Bundle args = new Bundle();

		DemoFragment fragment = new DemoFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	protected void doOnNext(Object o) {

	}

	@Override
	protected void initData() {


	}

	@Override
	protected void initView(View view) {
		TextView tv = (TextView) view.findViewById(R.id.tv_demo);
		Bundle bundle = getArguments();
		String title = bundle.getString("title");
		tv.setText(title + "");
	}

	@Override
	protected int getLayout() {
		return R.layout.fragment_demo;
	}
}
