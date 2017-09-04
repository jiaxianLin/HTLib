package com.ht.htlib.di.module.view;

import com.ht.htlib.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
@Module
public class LoginViewModule {

	LoginContract.View loginView;

	public LoginViewModule(LoginContract.View loginView) {
		this.loginView = loginView;
	}

	@Provides
	LoginContract.View provideLoginView(){
		return loginView;
	}

}
