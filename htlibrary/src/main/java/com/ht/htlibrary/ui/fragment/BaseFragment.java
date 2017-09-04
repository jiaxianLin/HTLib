package com.ht.htlibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public abstract class BaseFragment extends SupportFragment {

	private Toast mToast;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = getLayoutInflater(savedInstanceState).inflate(getLayout(), null);
		initView(view);
		initData();
		return view;
	}

	protected abstract void initData();

	protected abstract void initView(View view);

	protected abstract int getLayout();

	protected void showSuccessToast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toasty.success(getActivity(), msg);
		mToast.show();
	}

	protected void showErrorToast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toasty.error(getActivity(), msg);
		mToast.show();
	}

	protected void showNormalToast(String msg){
		if(mToast != null){
			mToast.cancel();
		}
		mToast = Toasty.normal(getActivity(), msg);
		mToast.show();
	}
}
