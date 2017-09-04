package com.ht.htlib.presenter;

import com.ht.htlib.contract.LoginContract;
import com.ht.htlib.model.LoginModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class LoginPresenter implements LoginContract.Presenter {

	LoginContract.View loginView;

	LoginModel loginModel;

	@Inject
	public LoginPresenter(LoginContract.View loginView, LoginModel loginModel) {
		this.loginView = loginView;
		this.loginModel = loginModel;
	}

	@Override
	public void start() {

	}

	@Override
	public void login(String username, String password) {
		loginView.showProgress();

		loginView.dismissProgress();
	}
}
