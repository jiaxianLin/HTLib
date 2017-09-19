package com.ht.htlibrary.base;

import android.app.Application;
import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class RepositoryManager implements IRepositoryManager {

	Retrofit mRetrofit;

	Application mApplication;

	public RepositoryManager(Retrofit retrofit, Application application) {
		mRetrofit = retrofit;
		mApplication = application;
	}

	@Override
	public <T> T obtainRetrofitService(Class<T> service) {
		T retrofitService = mRetrofit.create(service);
		return retrofitService;
	}

	@Override
	public <T> T obtainCacheService(Class<T> cache) {
		return null;
	}

	@Override
	public void clearAllCache() {

	}

	@Override
	public Context getContext() {
		return null;
	}
}
