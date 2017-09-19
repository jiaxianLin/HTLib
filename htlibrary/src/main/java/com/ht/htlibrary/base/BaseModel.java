package com.ht.htlibrary.base;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class BaseModel implements IModel {

	IRepositoryManager mRepositoryManager;

	public BaseModel() {
	}

	public BaseModel(IRepositoryManager repositoryManager) {
		mRepositoryManager = repositoryManager;
	}

	@Override
	public void onDestroy() {
		mRepositoryManager = null;
	}
}
