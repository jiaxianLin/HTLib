package com.ht.htlibrary.base;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class BasePresenter<M extends BaseModel, V extends BaseView> implements IPresenter{

	protected M model;

	protected V view;


	public BasePresenter(M model, V view) {
		this.model = model;
		this.view = view;
		onStart();
	}

	public BasePresenter(V view) {
		this.view = view;
		onStart();
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onDestroy() {
		if(model != null){
			model.onDestroy();
		}

		this.model = null;
		this.view = null;

	}
}
