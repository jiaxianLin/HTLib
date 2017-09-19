package com.ht.htlibrary.base;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public interface IRepositoryManager {

	/**
	 * 根据传入的 Class 获取对应的 Retrofit service
	 *
	 * @param service
	 * @param <T>
	 * @return
	 */
	<T> T obtainRetrofitService(Class<T> service);

	/**
	 * 根据传入的 Class 获取对应的 RxCache service
	 *
	 * @param cache
	 * @param <T>
	 * @return
	 */
	<T> T obtainCacheService(Class<T> cache);

	/**
	 * 清理所有缓存
	 */
	void clearAllCache();

	Context getContext();
}
