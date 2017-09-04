package com.ht.htlibrary.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.ht.htlibrary.R;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public abstract class BaseFragmentActivity extends BaseActivity {
	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected int getLayout() {
		return R.layout.activity_base_fragment;
	}

	@Override
	protected void initView() {
		super.initView();
		if (getFragment() != null) {
			loadRootFragment(R.id.base_fragment_container, getFragment());
		}

		registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
			// 可以监听该Activity下的所有Fragment的18个 生命周期方法

			@Override
			public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState) {
				Log.i("BaseFragmentActivity", "onFragmentCreated--->" + fragment.getClass().getSimpleName());
			}
		});
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	protected abstract SupportFragment getFragment();
}
