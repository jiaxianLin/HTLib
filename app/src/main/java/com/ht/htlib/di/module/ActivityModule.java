package com.ht.htlib.di.module;

import com.ht.htlib.model.UserModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
@Module
public class ActivityModule {

	@Provides
	public UserModel provideUserModel(){
		return new UserModel("1", "bob", "man");
	}
}
