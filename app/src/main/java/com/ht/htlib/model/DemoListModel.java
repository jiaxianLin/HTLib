package com.ht.htlib.model;

import com.ht.htlib.bean.ListData;
import com.ht.htlib.contract.DemoListContract;
import com.ht.htlib.net.NetClient;
import com.ht.htlibrary.base.BaseModel;
import com.ht.htlibrary.base.IRepositoryManager;
import com.ht.htlibrary.net.BaseResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DemoListModel extends BaseModel implements DemoListContract.Model {

	public DemoListModel() {
	}

	public DemoListModel(IRepositoryManager repositoryManager) {
		super(repositoryManager);
	}

	@Override
	public void onDestroy() {

	}

	@Override
	public Observable<BaseResponse<ListData>> loadData(String rp, String page) {
		return NetClient.getTestApi()
				.getPrisoner(rp, page)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

}
