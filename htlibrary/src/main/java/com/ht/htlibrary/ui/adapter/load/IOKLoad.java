package com.ht.htlibrary.ui.adapter.load;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public interface IOKLoad {

	/**
	 * 刷新完成
	 */
	void refreshOk();

	/**
	 * 加载更多完成
	 */
	void loadMoreOk(boolean hasNext);

	/**
	 * 加载更多失败
	 */
	void loadMoreFailure();

}
