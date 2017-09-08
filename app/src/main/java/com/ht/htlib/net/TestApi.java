package com.ht.htlib.net;

import com.ht.htlib.bean.ListData;
import com.ht.htlibrary.net.BaseResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public interface TestApi {

	@GET("jfp_prisoner!Test.action")
	Observable<BaseResponse<ListData>> getPrisoner(@Query("rp") String rp, @Query("page") String page);
}
