package com.ht.htlib;

import com.ht.htlibrary.ui.activity.BaseFragmentActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class DemoFragmentAtivity extends BaseFragmentActivity{

	@Override
	protected SupportFragment getFragment() {
		return DemoFragment.newInstance();
	}


}
