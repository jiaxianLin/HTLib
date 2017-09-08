package com.ht.htlib.model;

import com.ht.htlib.bean.ListData;
import com.ht.htlib.net.NetClient;
import com.ht.htlibrary.net.BaseResponse;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DemoListModel {

	public interface DataCallBack<T> {
		void onstart();

		void onSuccess(T result);

		void onFailure(Throwable e);

		void onFinish();
	}

	public void loadData(String rp, String page, final DataCallBack callBack) {

		NetClient.getTestApi()
				.getPrisoner(rp, page)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<BaseResponse<ListData>>() {
					@Override
					public void onCompleted() {
						callBack.onFinish();
					}

					@Override
					public void onError(Throwable e) {
						callBack.onFailure(e);
					}

					@Override
					public void onNext(BaseResponse<ListData> response) {
						callBack.onSuccess(response.getResult());
					}
				});
	}


}
