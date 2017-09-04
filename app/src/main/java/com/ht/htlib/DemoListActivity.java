package com.ht.htlib;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.ht.htlibrary.ui.activity.BaseListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class DemoListActivity extends BaseListActivity<String> {

	public static int TOTAL_COUNT = 30;
	private boolean isErr;

	@Override
	public RecyclerView.LayoutManager getLayoutMananger() {
		return new LinearLayoutManager(this);
	}

	@Override
	protected void MyHolder(BaseViewHolder baseViewHolder, String s) {
//		baseViewHolder.setText(R.id.tv_demo, s);
		TextView tv = baseViewHolder.getView(R.id.tv_demo);
		tv.setText(s);
	}

	@Override
	protected int getItemLayoutId() {
		return R.layout.item_demo_list;
	}

	@Override
	public void refresh() {

		refreshOk();
	}

	@Override
	protected void initParams(Bundle bundle) {
		setOpenRefresh(true);
		setLoadMore(true);
	}

	@Override
	protected void initData() {
		super.initData();


	}

	@Override
	public List<String> getList(int page, int rp) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("current：" + i);
		}

		return list;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public void setPage(int page) {

	}

	@Override
	public void onLoadMoreRequested() {
		mRefreshLayout.setEnabled(false);
		if (mAdapter.getData().size() < mPageSize) {
			mAdapter.loadMoreEnd(true);
		} else {
			if (mAdapter.getData().size() >= TOTAL_COUNT) {
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
				mAdapter.loadMoreEnd(true);//true is gone,false is visible
			} else {
				if (isErr) {
					List<String> list = new ArrayList<>();
					for (int i = 0; i < 10; i++) {
						list.add("current：" + page * mPageSize + i);
					}
					mAdapter.addData(list);
//					mCurrentCounter = mAdapter.getData().size();
					page ++;
					mAdapter.loadMoreComplete();
				} else {
					isErr = true;
//					Toast.makeText(this, R.string.network_err, Toast.LENGTH_LONG).show();
					mAdapter.loadMoreFail();

				}
			}
			mRefreshLayout.setEnabled(true);
		}
	}
}
