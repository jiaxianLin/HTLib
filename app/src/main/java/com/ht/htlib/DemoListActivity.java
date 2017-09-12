package com.ht.htlib;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.ht.htlib.bean.ListData;
import com.ht.htlib.contract.DemoListContract;
import com.ht.htlib.di.component.DaggerDemoListPresenterCom;
import com.ht.htlib.di.module.model.DemoListModelModule;
import com.ht.htlib.di.module.view.DemoListViewModule;
import com.ht.htlib.presenter.DemoListPresenter;
import com.ht.htlibrary.ui.activity.BaseListActivity;
import com.ht.htlibrary.ui.adapter.ILayoutLoad;

import javax.inject.Inject;

/**
 * Created by rinkousen on 2017/8/14 0014.
 * dagger2 + mvp + retrofit2 + rxjava + baserecycleAdapterHelper
 */

public class DemoListActivity extends BaseListActivity<ListData.UserData> implements DemoListContract.View {

	@Inject
	DemoListPresenter mPresenter;

	@Override
	public RecyclerView.LayoutManager getLayoutMananger() {
		return new LinearLayoutManager(this);
	}

	@Override
	public void refresh() {
		isRefresing = true;
		mPresenter.loadingData(PAGE_SIZE + "", "1");
	}

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected void initData() {
		super.initData();
		DaggerDemoListPresenterCom.builder()
				.demoListModelModule(new DemoListModelModule())
				.demoListViewModule(new DemoListViewModule(this))
				.build()
				.inject(this);
		mPresenter.start();
		//初始加载
		mPresenter.loadingData(PAGE_SIZE + "", page + "");

	}

	@Override
	protected ListAdapter getAdapter() {
		return new ListAdapter(R.layout.item_demo_list, new ILayoutLoad<ListData.UserData, BaseViewHolder>() {
			@Override
			public void doInConstructor() {

			}

			@Override
			public void doInConvert(BaseViewHolder baseViewHolder, ListData.UserData data) {
				TextView tv = baseViewHolder.getView(R.id.tv_demo);
				tv.setText(data.getUserName());
			}
		});
	}

	@Override
	protected boolean isOpenRefresh() {
		return true;
	}

	@Override
	protected boolean isOpenLoadMore() {
		return true;
	}

	@Override
	public void onLoadMoreRequested() {
		mRefreshLayout.setEnabled(false);
		if (mAdapter.getData().size() < PAGE_SIZE) {
			mAdapter.loadMoreEnd(true);
		} else {
			if (mAdapter.getData().size() >= total) {
				mAdapter.loadMoreEnd(true);//true is gone,false is visible
			} else {
				isRefresing = false;
				mPresenter.loadingData(PAGE_SIZE + "", (page+1) + "");

			}
			mRefreshLayout.setEnabled(true);
		}
	}

	@Override
	public void setPresenter(DemoListContract.Presenter presenter) {

	}

	@Override
	public void showData(ListData data) {
		try {
			setTotal(Integer.valueOf(data.getTotal()));
			if (isRefresing) {
				refreshOk();
				mAdapter.setNewData(data.getResult());
			} else {
				loadMoreOk(PAGE_SIZE * page <= Integer.valueOf(data.getTotal()) ? true : false);
				mAdapter.addData(data.getResult());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}
}
