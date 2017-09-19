package com.ht.htlib.presenter;

import com.ht.htlib.contract.LoginContract;
import com.ht.htlib.model.LoginModel;
import com.ht.htlibrary.base.BasePresenter;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class LoginPresenter extends BasePresenter<LoginModel,LoginContract.View> {

	LoginContract.View loginView;

	LoginModel loginModel;

	public LoginPresenter(LoginContract.View loginView, LoginModel loginModel) {
		super(loginModel, loginView);
		this.loginView = loginView;
		this.loginModel = loginModel;
	}

	public void login(String username, String password) {
		loginView.showProgress();

		loginView.dismissProgress();
	}
}
