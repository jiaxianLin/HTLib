package com.ht.htlib;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ht.htlib.adapter.ExpandableItemAdapter;
import com.ht.htlib.bean.Level0Item;
import com.ht.htlib.bean.Level1Item;
import com.ht.htlib.bean.ListData;
import com.ht.htlib.bean.Person;
import com.ht.htlib.contract.DemoListContract;
import com.ht.htlibrary.ui.activity.BaseListActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rinkousen on 2017/8/14 0014.
 * multitypeList
 */

public class DemoMultiListActivity extends BaseListActivity<MultiItemEntity> implements DemoListContract.View {

	@Override
	public RecyclerView.LayoutManager getLayoutMananger() {
		final GridLayoutManager tmpmanager = new GridLayoutManager(this, 1);
		tmpmanager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return mAdapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_PERSON ? 1 : tmpmanager.getSpanCount();
			}
		});
		return tmpmanager;
	}


	@Override
	public void refresh() {
		isRefresing = true;
//		mPresenter.loadingData(PAGE_SIZE + "", "1");
	}

	@Override
	protected void initParams(Bundle bundle) {

	}

	@Override
	protected void initData() {
		super.initData();


	}

	@Override
	protected BaseQuickAdapter<MultiItemEntity, BaseViewHolder> getAdapter() {
		return new ExpandableItemAdapter(generateData());
	}

	@Override
	protected boolean isOpenRefresh() {
		return false;
	}

	@Override
	protected boolean isOpenLoadMore() {
		return false;
	}

	@Override
	public void onLoadMoreRequested() {

	}

	@Override
	public void setPresenter(DemoListContract.Presenter presenter) {

	}

	@Override
	public void showData(ListData data) {

	}

	private ArrayList<MultiItemEntity> generateData() {
		int lv0Count = 9;
		int lv1Count = 3;
		int personCount = 5;

		String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
		Random random = new Random();

		ArrayList<MultiItemEntity> res = new ArrayList<>();
		for (int i = 0; i < lv0Count; i++) {
			Level0Item lv0 = new Level0Item("This is " + i + "th item in Level 0", "subtitle of " + i);
			for (int j = 0; j < lv1Count; j++) {
				Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
				for (int k = 0; k < personCount; k++) {
					lv1.addSubItem(new Person(nameList[k], random.nextInt(40)));
				}
				lv0.addSubItem(lv1);
			}
			res.add(lv0);
		}
		return res;
	}
}
