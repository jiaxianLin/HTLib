package com.ht.htlib.contract;

import com.ht.htlibrary.base.BasePresenter;
import com.ht.htlibrary.base.BaseView;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public interface LoginContract {

	interface Presenter extends BasePresenter {

		void login(String username, String password);

	}

	interface View extends BaseView<Presenter> {

		void showProgress();

		void dismissProgress();
	}
}
