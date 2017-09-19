package com.ht.htlib.contract;

import com.ht.htlibrary.base.BaseView;
import com.ht.htlibrary.base.IModel;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public interface LoginContract {

	public interface Model extends IModel{

	}

	public interface View extends BaseView {

		void showProgress();

		void dismissProgress();
	}
}
