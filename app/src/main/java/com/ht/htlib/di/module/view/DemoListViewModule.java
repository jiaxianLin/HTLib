package com.ht.htlib.di.module.view;

import com.ht.htlib.contract.DemoListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
@Module
public class DemoListViewModule {

	DemoListContract.View mView;

	public DemoListViewModule(DemoListContract.View view) {
		mView = view;
	}

	@Provides
	DemoListContract.View providerDemoListView() {
		return mView;
	}
}
