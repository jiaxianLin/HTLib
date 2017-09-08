package com.ht.htlib.presenter;

import com.ht.htlib.bean.ListData;
import com.ht.htlib.contract.DemoListContract;
import com.ht.htlib.model.DemoListModel;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class DemoListPresenter implements DemoListContract.Presenter{

	DemoListContract.View mView;

	DemoListModel listModel;

	@Inject
	public DemoListPresenter(DemoListContract.View view, DemoListModel model) {
		mView = view;
		listModel = model;
	}

	@Override
	public void start() {

	}

	@Override
	public void loadingData(String rp, String page){
		listModel.loadData(rp, page, new DemoListModel.DataCallBack<ListData>() {
			@Override
			public void onstart() {

			}

			@Override
			public void onSuccess(ListData result) {
				System.out.println(result.getTotal());
				mView.showData(result);
			}

			@Override
			public void onFailure(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onFinish() {

			}
		});
	}
}
