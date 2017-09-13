package com.ht.htlibrary.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ht.htlibrary.R;
import com.ht.htlibrary.ui.adapter.ILayoutLoad;
import com.ht.htlibrary.ui.adapter.load.IOKLoad;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, IOKLoad {

	RecyclerView mRecyclerView;

	protected RecyclerView.LayoutManager manager;

	protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;

	protected SwipeRefreshLayout mRefreshLayout;

	public static final int PAGE_SIZE = 10;

	/**
	 * 当前页数
	 */
	protected int page = 1;

	/**
	 * 加载更多还是刷新
	 */
	protected boolean isRefresing;
	/**
	 * 总数
	 */
	protected int total;

	protected LoadMoreView loadMoreView;

	@Override
	protected int getLayout() {
		return R.layout.activity_base_list;
	}

	@Override
	protected void initView(View view) {
		mRecyclerView = (RecyclerView) view.findViewById(R.id.base_list);
		mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.base_refreshlayout);
		if (isOpenRefresh()) {
			mRefreshLayout.setOnRefreshListener(this);
		}
	}

	@Override
	protected void initData() {

		mAdapter = getAdapter();
		mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
		mAdapter.setLoadMoreView(getLoadMoreView());

		if (isOpenLoadMore()) {
			mAdapter.setOnLoadMoreListener(this, mRecyclerView);
		}

		manager = getLayoutMananger();

		if (manager == null) {
			throw new RuntimeException("recyclerview.manager is null");
		}

		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public void onRefresh() {
		mAdapter.setEnableLoadMore(false);
		refresh();
	}

	protected abstract BaseQuickAdapter<T, BaseViewHolder> getAdapter();

	protected class ListAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

		ILayoutLoad<T, BaseViewHolder> load;


		public ListAdapter(int layoutResId, ILayoutLoad<T, BaseViewHolder> load) {
			super(layoutResId, new ArrayList<T>());
			this.load = load;
		}

		@Override
		protected void convert(BaseViewHolder baseViewHolder, T t) {
			if(load != null){
				load.doInConvert(baseViewHolder, t);
			}
		}
	}

	/**
	 * 是否开启下拉刷新
	 *
	 * @return
	 */
	protected abstract boolean isOpenRefresh();

	/**
	 * 是否开启上拉加载
	 *
	 * @return
	 */
	protected abstract boolean isOpenLoadMore();

	public abstract RecyclerView.LayoutManager getLayoutMananger();

	public LoadMoreView getLoadMoreView() {
		return loadMoreView == null ? new LoadMoreView() {
			@Override
			public int getLayoutId() {
				return R.layout.quick_view_load_more;
			}

			@Override
			protected int getLoadingViewId() {
				return R.id.load_more_loading_view;
			}

			@Override
			protected int getLoadFailViewId() {
				return R.id.load_more_load_fail_view;
			}

			@Override
			protected int getLoadEndViewId() {
				return R.id.load_more_load_end_view;
			}
		} : loadMoreView;
	}

	/**
	 * 刷新时调用此方法，在此方法中获取数据
	 */
	public abstract void refresh();

	@Override
	public void refreshOk() {
		page = 1;
		mRefreshLayout.setRefreshing(false);
		mAdapter.setEnableLoadMore(true);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void loadMoreOk(boolean hasNext) {
		if (mRefreshLayout != null) {
			page++;
			mRefreshLayout.setEnabled(true);
		}
		if (hasNext) {
			mAdapter.loadMoreComplete();
		} else {
			mAdapter.loadMoreEnd();
		}
	}

	@Override
	public void loadMoreFailure() {
		mRefreshLayout.setEnabled(true);
		mAdapter.loadMoreFail();
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
