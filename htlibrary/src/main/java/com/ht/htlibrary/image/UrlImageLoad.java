package com.ht.htlibrary.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public interface UrlImageLoad {

	/**
	 * 加载网络图片
	 * @param url
	 * @param view
	 */
	void loadImageInto(Context context, String url, ImageView view);



}
