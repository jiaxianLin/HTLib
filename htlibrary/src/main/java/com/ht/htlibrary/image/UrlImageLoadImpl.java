package com.ht.htlibrary.image;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class UrlImageLoadImpl implements UrlImageLoad {

	@Override
	public void loadImageInto(Context context, String url, ImageView view) {
		Glide.with(context).load(url).into(view);
	}
}
