package com.ht.htlibrary.base;

import android.content.Intent;

/**
 * Created by rinkousen on 2017/8/10 0010.
 */

public interface BaseView {

	void showProgress();

	void dismissProgress();

	void showMessage(String msg);

	void luanchActivity(Intent intent);

	void killMyself();

}
