package com.ht.htlibrary.ui.adapter;

/**
 * Created by rinkousen on 2017/9/12 0012.
 */

public interface ILayoutLoad<T, BaseViewHolder> {

	/**
	 * 构造方法中回调
	 */
	void doInConstructor();

	/**
	 * 绘制子项回调
	 * @param holder
	 * @param t
	 */
	void doInConvert(BaseViewHolder holder, T t);
}
