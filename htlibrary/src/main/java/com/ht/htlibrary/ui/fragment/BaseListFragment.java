package com.ht.htlibrary.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ht.htlibrary.R;
import com.ht.htlibrary.ui.adapter.load.IOKLoad;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public abstract class BaseListFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, IOKLoad {

	protected List<T> mList;

	RecyclerView mRecyclerView;

	protected RecyclerView.LayoutManager manager;

	protected ListAdapter mAdapter;

	protected SwipeRefreshLayout mRefreshLayout;

	LoadMoreView mLoadMoreView;

	/**
	 * 必须最先声明
	 * 在initParams里
	 */
	boolean isOpenRefresh;

	/**
	 * 必须最先声明
	 * 在initParams里
	 */
	boolean isLoadMore;

	protected LoadMoreView loadMoreView;
	private int page;
	private int mPageSize = 10;

	@Override
	protected void initData() {
		manager = getLayoutMananger();
		if (manager == null) {
			throw new RuntimeException("recyclerview.manager is null");
		}

		mList = getList(page, mPageSize);

		mAdapter = new ListAdapter(getItemLayoutId(), mList);
		mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
		mAdapter.setLoadMoreView(getLoadMoreView());

		if (isLoadMore) {
			mAdapter.setOnLoadMoreListener(this, mRecyclerView);
		}

		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	protected void initView(View view) {
		mRecyclerView = (RecyclerView) view.findViewById(R.id.base_list);
		mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.base_refreshlayout);
		if (isOpenRefresh) {
			mRefreshLayout.setOnRefreshListener(this);
		}
	}

	@Override
	protected int getLayout() {
		return R.layout.activity_base_list;
	}

	@Override
	public void onRefresh() {
		page = 1;
		mRefreshLayout.setRefreshing(false);
		mAdapter.setEnableLoadMore(true);
	}

	@Override
	public void onLoadMoreRequested() {

	}

	protected class ListAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

		public ListAdapter(int layoutResId, List<T> data) {
			super(layoutResId, data);
		}

		@Override
		protected void convert(BaseViewHolder baseViewHolder, T t) {
			MyHolder(baseViewHolder, t);
		}
	}

	public abstract List<T> getList(int page, int rp);

	public abstract RecyclerView.LayoutManager getLayoutMananger();

	protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);

	protected abstract int getItemLayoutId();

	public void setOpenRefresh(boolean openRefresh) {
		isOpenRefresh = openRefresh;
	}

	public void setLoadMore(boolean loadMore) {
		isLoadMore = loadMore;
	}

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
			//false为显示加载结束，true为不显示
			mAdapter.loadMoreEnd(false);
		}
	}

	@Override
	public void loadMoreFailure() {
		mRefreshLayout.setEnabled(true);
		mAdapter.loadMoreFail();
	}
}
