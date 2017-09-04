package com.ht.htlibrary.base;

import android.app.Application;

import com.ht.htlibrary.util.Utils;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class App extends Application {

	static App app;

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		//工具类初始化
		Utils.init(this);
		//错误日志收集
//		CustomCrash.getInstance().setCustomCrashInfo(this);

		Toasty.Config.getInstance().apply();
//		Stetho.initializeWithDefaults(this);
	}

	public static App getApp() {
		return app;
	}
}
