package com.ht.htlibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ht.htlibrary.base.RxBus;

import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public abstract class BaseFragment extends SupportFragment {

	private Toast mToast;
	private Subscription subscription;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = getLayoutInflater(savedInstanceState).inflate(getLayout(), null);
		initRxBus();
		initView(view);
		initData();
		return view;
	}

	protected void initRxBus(){
		subscription = RxBus.toObserverable()
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Object>() {
					@Override
					public void call(Object o) {
						doOnNext(o);
					}
				});
	}

	protected abstract void doOnNext(Object o);

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

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (subscription != null){
			subscription.unsubscribe();
		}

	}
}
