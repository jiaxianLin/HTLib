package com.ht.htlib;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.ht.htlib.contract.LoginContract;
import com.ht.htlib.di.component.DaggerLoginPresenterCom;
import com.ht.htlib.di.module.model.LoginModelModule;
import com.ht.htlib.di.module.view.LoginViewModule;
import com.ht.htlib.presenter.LoginPresenter;
import com.ht.htlibrary.ui.activity.login.BaseLoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rinkousen on 2017/8/31 0031.
 * 调用baselogin里的login方法进行验证和登录
 */

public class DemoLoginActivity extends BaseLoginActivity implements LoginContract.View {

	@Inject
	LoginPresenter mPresenter;

	@BindView(R.id.et_username)
	EditText et_username;

	@BindView(R.id.et_password)
	EditText et_password;

	@BindView(R.id.btn_login)
	Button btn_login;


	@Override
	public boolean isCheckSuccess(String username, String password) {
		if (TextUtils.isEmpty(username)) {
			return false;
		}

		if (TextUtils.isEmpty(password)) {
			return false;
		}

		return true;
	}

	@Override
	public void onLogin(String username, String password) {
		mPresenter.login(username, password);
	}

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected int getLayout() {
		return R.layout.activity_login;
	}

	@Override
	protected void initView() {
		super.initView();
		ButterKnife.bind(this);
	}

	@Override
	protected void initData() {
		DaggerLoginPresenterCom.builder()
				.loginModelModule(new LoginModelModule())
				.loginViewModule(new LoginViewModule(this))
				.build()
				.inject(this);
	}

	@Override
	protected int getToolBarId() {
		return 0;
	}

	@Override
	public void setPresenter(LoginContract.Presenter presenter) {

	}

	@Override
	public void showProgress() {
		showNormalToast("登录中");
	}

	@Override
	public void dismissProgress() {
		showSuccessToast("登录成功");
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.btn_login)
	public void onBtnClick(){
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();

		login(username, password);
	}
}
