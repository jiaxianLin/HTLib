package com.ht.htlibrary.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ht.htlibrary.R;
import com.ht.htlibrary.ui.adapter.load.IOKLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public abstract class BaseListActivity<T> extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
		BaseQuickAdapter.RequestLoadMoreListener, IOKLoad {

	protected RecyclerView mRecyclerView;

	protected RecyclerView.LayoutManager manager;

	protected ListAdapter mAdapter;

	protected SwipeRefreshLayout mRefreshLayout;

	protected LoadMoreView loadMoreView;

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

	@Override
	protected int getLayout() {
		return R.layout.activity_base_list;
	}

	@Override
	protected void initView() {
		super.initView();
		mRecyclerView = (RecyclerView) findViewById(R.id.base_list);
		mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_refreshlayout);
		if (isOpenRefresh()) {
			mRefreshLayout.setOnRefreshListener(this);
		}
	}

	@Override
	protected void initData() {
		manager = getLayoutMananger();
		if (manager == null) {
			throw new RuntimeException("recyclerview.manager is null");
		}

		mAdapter = new ListAdapter(getItemLayoutId(), new ArrayList<T>());
		mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
		mAdapter.setLoadMoreView(getLoadMoreView());

		if (isOpenLoadMore()) {
			mAdapter.setOnLoadMoreListener(this, mRecyclerView);
		}

		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setAdapter(mAdapter);
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

	@Override
	protected int getToolBarId() {
		return 0;
	}

	/**
	 * 是否开启下拉刷新
	 * @return
	 */
	protected abstract boolean isOpenRefresh();

	/**
	 * 是否开启上拉加载
	 * @return
	 */
	protected abstract boolean isOpenLoadMore();

	public abstract RecyclerView.LayoutManager getLayoutMananger();

	protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);

	protected abstract int getItemLayoutId();

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

	@Override
	public void onRefresh() {
		mAdapter.setEnableLoadMore(false);
		refresh();
	}

	/**
	 * 刷新时调用此方法，在此方法中获取数据
	 */
	public abstract void refresh();

	/**
	 * 进入自动加载
	 */
	protected void autoRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (null != mRefreshLayout) {
					mRefreshLayout.setRefreshing(true);
					onRefresh();
				}
			}
		}, 500);
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
