package com.ht.htlib.net;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class NetClient extends com.ht.htlibrary.net.NetClient {

	static TestApi testApi;

	public static TestApi getTestApi() {
		if(mRetrofit == null){
			return null;
		}
		testApi = mRetrofit.create(TestApi.class);
		return testApi;
	}

}
