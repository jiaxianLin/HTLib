package com.ht.htlib.model;

import com.ht.htlib.contract.LoginContract;
import com.ht.htlibrary.base.BaseModel;
import com.ht.htlibrary.base.IRepositoryManager;

/**
 * Created by Administrator on 2017/9/1 0001.
 */

public class LoginModel extends BaseModel implements LoginContract.Model{


	public LoginModel(IRepositoryManager repositoryManager) {
		super(repositoryManager);
	}
}
