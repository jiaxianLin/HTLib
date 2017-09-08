package com.ht.htlib.di.module.model;

import com.ht.htlib.model.DemoListModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
@Module
public class DemoListModelModule {

	@Provides
	DemoListModel providerDemoListModel(){
		return new DemoListModel();
	}
}
