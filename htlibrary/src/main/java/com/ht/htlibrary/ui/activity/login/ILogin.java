package com.ht.htlibrary.ui.activity.login;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public interface ILogin {

	/**
	 * 校验信息是否符合格式
	 * @param username
	 * @param password
	 * @return
	 */
	boolean isCheckSuccess(String username, String password);

	/**
	 * 当格式验证成功后的登录处理
	 * @param username
	 * @param password
	 */
	void onLogin(String username, String password);
}
