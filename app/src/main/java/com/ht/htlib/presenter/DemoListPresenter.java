package com.ht.htlib.presenter;

import com.ht.htlib.bean.ListData;
import com.ht.htlib.contract.DemoListContract;
import com.ht.htlib.model.DemoListModel;
import com.ht.htlibrary.base.BasePresenter;
import com.ht.htlibrary.net.BaseResponse;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DemoListPresenter extends BasePresenter<DemoListModel, DemoListContract.View> {

	DemoListContract.View mView;

	DemoListModel listModel;


	public DemoListPresenter(DemoListModel model, DemoListContract.View view) {
		super(model, view);
		mView = view;
		listModel = model;
	}


	public void loadingData(String rp, String page) {
		listModel.loadData(rp, page)
				.subscribe(new Subscriber<BaseResponse<ListData>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						System.out.println(e.getMessage());
					}

					@Override
					public void onNext(BaseResponse<ListData> response) {
						System.out.println(response.getResult().getTotal());
						mView.showData(response.getResult());
					}
				});
	}
}
