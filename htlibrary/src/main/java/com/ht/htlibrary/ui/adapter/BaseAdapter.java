package com.ht.htlibrary.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 * 给listview和gridview用的adapter
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter{

	List<T> mList;

	Context mContext;

	public List<T> getItems(){
		return null;
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = getLayout(getItemViewType(position), position);
		}

		T t = (T) getItem(position);
		initView(position, getItemViewType(position), convertView, parent, t);

		return convertView;
	}

	protected abstract View getLayout(int itemViewType, int position);

	protected abstract void initView(int position, int itemViewType, View v, ViewGroup parent, T item);
}
