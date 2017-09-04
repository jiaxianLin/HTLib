package com.ht.htlibrary.ui.activity.login;

import com.ht.htlibrary.ui.activity.BaseActivity;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public abstract class BaseLoginActivity extends BaseActivity implements ILogin {

	/**
	 * 登录
	 * @param username
	 * @param password
	 */
	protected void login(String username, String password){
		if(isCheckSuccess(username, password)){
			onLogin(username, password);
		}
	}
}
